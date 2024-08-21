package com.ruoyi.coupons.service.impl;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.coupons.config.RedisConfig;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.TbCouponsMapper;
import com.ruoyi.coupons.service.ITbCouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * 优惠券Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class TbCouponsServiceImpl implements ITbCouponsService {
    @Autowired
    private TbCouponsMapper tbCouponsMapper;
    @Autowired
    private JedisPool jedisPool; // Spring 会自动注入这个 Bean
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisConfig redisConfig;


    /**
     * 查询优惠券
     *
     * @param id 优惠券主键
     * @return 优惠券
     */
    @Override
    public TbCoupons selectTbCouponsById(Long id) {
        return tbCouponsMapper.selectTbCouponsById(id);
    }

    /**
     * 查询优惠券列表
     *
     * @param tbCoupons 优惠券
     * @return 优惠券
     */
    @Override
    public List<TbCoupons> selectTbCouponsList(TbCoupons tbCoupons) {
        return tbCouponsMapper.selectTbCouponsList(tbCoupons);
    }

    /**
     * 新增优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    @Override
    public int insertTbCoupons(TbCoupons tbCoupons) {

        tbCoupons.setCouponsStatus(Long.valueOf(0));

        return tbCouponsMapper.insertTbCoupons(tbCoupons);
    }

    /**
     * 修改优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    @Override
    public int updateTbCoupons(TbCoupons tbCoupons) {
        return tbCouponsMapper.updateTbCoupons(tbCoupons);
    }

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsByIds(Long[] ids) {
        return tbCouponsMapper.deleteTbCouponsByIds(ids);
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsById(Long id) {
        return tbCouponsMapper.deleteTbCouponsById(id);
    }

    @Override
    public TbCoupons selectOne(Long id) {


        return tbCouponsMapper.selectOne(id);


    }

    @Override
    public void updateStatus(Long id, Integer status, Integer count) {
        tbCouponsMapper.updateStatus(id, status, count);
    }

    @Override
    public void updateCountById(Long id, Long couponCount) {
        tbCouponsMapper.updateCountById(id, couponCount);

    }

    @Override
    public void updateByReceiveCount(Long id) {
        tbCouponsMapper.updateByReceiveCount(id);
    }

    @Override
    public AjaxResult redisAcquireLockLock(Long id) {

        // 从数据库查询优惠券信息
        String tokenKey = "CouponKey-" + id;
        TbCoupons tbCoupons = tbCouponsMapper.selectCouponsById(id);
        stringRedisTemplate.opsForHash().put(tokenKey,"couposName",tbCoupons.getCouposName());
        stringRedisTemplate.opsForHash().put(tokenKey,"receiveCount",String.valueOf(tbCoupons.getReceiveCount()));

        // 本次优惠券领取唯一标记
        String eventsPromoCodeMark = tbCoupons.getCouponsNumber();

        // 如何连接池没有关闭，则直接获取连接池资源
        if (!jedisPool.isClosed()) {
            try (Jedis jedis = jedisPool.getResource()) {
                // 调用方法
                return getRedisResult(eventsPromoCodeMark, id, tokenKey);
            }
        } else {
            // 如果连接池已关闭，重新初始化
            jedisPool = new JedisPool(redisConfig.jedisPoolConfig(), "localhost", 6379);
            // 调用方法
            return getRedisResult(eventsPromoCodeMark, id, tokenKey);
        }
    }


    // 调用方法来获取Redis连接池进行判断
    private AjaxResult getRedisResult(String eventsPromoCodeMark, Long id, String tokenKey) {
        // 从 Redis 连接池 (jedisPool) 中获取一个 Jedis 实例的方法。这个连接池用于管理与 Redis 服务器的连接，通过复用连接来提高性能。
        Jedis jedisResources = jedisPool.getResource();
        // 执行 Redis 操作
        String lock = 3 + eventsPromoCodeMark;//设置本次优惠券锁（保证幂等性）：userId + 优惠码活动的key
        String userKey = lock + "receiveCoupon";//设置用户优惠券领取key（自定义，只要保证一个用户在一个活动的唯一性即可）

        // 获取缓存数量
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(tokenKey);
        Integer count = Integer.valueOf((String) entries.get("receiveCount")) ;

        //优惠码领取库存校验
        if(count <= 0){
            System.out.println("优惠券已经领取完了");
            jedisResources.close();
            return AjaxResult.error("优惠券已经领取完了");
        }

        //为了避免竞争（同一个用户，多个设备同时领取）.使用Redis.setNX() 实现分布式锁（重复数据插入可用其来实现排他锁）
        boolean flag = acquireLock(lock);
        if(flag){
            //1,获取锁成功,进行用户已领取标记，查询用户是否已经领取过
            Boolean isExists = jedisResources.exists(userKey);
            if(!isExists){
                //2,先使用redis的decr可以实现原子性的递增递减操作控制优惠券不超送,
                Long success = jedisResources.decrBy(String.valueOf(count),-1);
                // 先减库存后发券（减库存后返回的现有库存数量大于等于0说明本次抢券成功，再进行发送优惠券，否则库存已经空了就不进行发送优惠券）
                if(success >= 0L){
                    //3,再进行优惠券分发（可异步执行通过MQ进行发放，如果减库存成功，发放失败，进行发放补偿性操作）
                    jedisResources.set(userKey, "received");
                    stringRedisTemplate.opsForHash().increment(tokenKey, "receiveCount", -1);
                    tbCouponsMapper.updateByReceiveCount(id);
                    System.out.println("领取成功");

                    // 存储到优惠券领取表 coupons_get


                    long oldValue = Long.valueOf(jedisResources.get(lock));//获取锁的旧值
                    if (oldValue > System.currentTimeMillis()) {//检查处理时间是否小于超时时间,释放锁
                        jedisResources.del(lock);
                    }
                    return AjaxResult.success("领取成功");
                }
            } else {
                System.out.println("已经领取过了");
                return AjaxResult.error("已经领取过了");
            }
        } else {
            System.out.println("请重试");
            return AjaxResult.error("请重试");
        }
        jedisResources.close();
        return AjaxResult.success();
    }

    /**
     * @Description: 获取锁方法
     * @Author: MaoYiFan
     * @Date: 2024/8/19 22:58
     * @param lock: 锁的k
     * @return: boolean 本次获锁是否成功
     *
     */
    public  boolean acquireLock(String lock) {
        boolean success;//设置获锁成功标记
        Jedis jedis = jedisPool.getResource();
        int expired = 60 * 1000;//强制过期时间：60 秒
        long value = System.currentTimeMillis() + expired;
        long acquired = jedis.setnx(lock, String.valueOf(value)); //通过SETNX试图获取一个lock
        if (1 == acquired) {//setnx 返回值为1则表示设置成功，则成功获取一个锁
            success = true;
        } else {//setnx 失败，说明锁仍然被其他对象保持，检查其是否已经超时
            long oldValue = Long.valueOf(jedis.get(lock));//获取锁的旧值
            if (oldValue < System.currentTimeMillis()) {//检查是否超时
                String getOldValue = jedis.getSet(lock, String.valueOf(value));//超时，进行锁覆盖，并获取覆盖值之后的获取到的旧值
                // 进行最终获取锁是否成功原子性校验(覆盖旧值之前获取到的旧值 和 覆盖值之后的获取到的旧值 相同 则本次获锁成功，否则 失败已经被别的对象获取到)
                if (Long.valueOf(getOldValue) == oldValue) {
                    success = true;
                } else {// 已被其他进程捷足先登了
                    success = false;
                }
            } else {//未超时，则直接返回获取锁失败
                success = false;
            }
        }
        jedisPool.close();
        return success;
    }


}
