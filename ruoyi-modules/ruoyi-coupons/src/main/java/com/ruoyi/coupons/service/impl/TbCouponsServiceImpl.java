package com.ruoyi.coupons.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.coupons.config.RedisConfig;
import com.ruoyi.coupons.domain.OrderInfo;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.CouponsGetMapper;
import com.ruoyi.coupons.mapper.CouponsLogMapper;
import com.ruoyi.coupons.mapper.OrderInfoMapper;
import com.ruoyi.coupons.mapper.TbCouponsMapper;
import com.ruoyi.coupons.service.ITbCouponsService;
import com.ruoyi.coupons.utils.RedissonUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 优惠券Service业务层处理
 *
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class TbCouponsServiceImpl implements ITbCouponsService {
    //开放优惠券的key
    private final String STARTKEY = "startCouponsKey:";
    //缓存中存储所有优惠券信息的KEY
    private final String COUPONSALL_KEY = "coupons-all";
    //缓存中存储优惠券详细信息的KEY
    private final String COUPONSINFO_KEY = "coupons-info:";
    //redis锁的KEY
    private final String LOCK_KEY = "lock-coupons:";
    private final String LOCK_START_KEY = "lock-start-coupons:";
    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisConfig redisConfig;
    @Resource
    private TbCouponsMapper tbCouponsMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedissonUtils redissonUtils;
    @Resource
    private CouponsGetMapper couponsGetMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private CouponsLogMapper couponsLogMapper;

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
        int insertFlag = tbCouponsMapper.insertTbCoupons(tbCoupons);
        stringRedisTemplate.delete(COUPONSALL_KEY);
        return insertFlag;
    }

    /**
     * 修改优惠券
     *
     * @param tbCoupons 优惠券
     * @return 结果
     */
    @Override
    public int updateTbCoupons(TbCoupons tbCoupons) {
        int updateFlag = tbCouponsMapper.updateTbCoupons(tbCoupons);
        stringRedisTemplate.delete(COUPONSALL_KEY);
        return updateFlag;
    }

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsByIds(Long[] ids) {
        int deleted = tbCouponsMapper.deleteTbCouponsByIds(ids);
        stringRedisTemplate.delete(COUPONSALL_KEY);
        return deleted;
    }

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券主键
     * @return 结果
     */
    @Override
    public int deleteTbCouponsById(Long id) {
        int deleted = tbCouponsMapper.deleteTbCouponsById(id);
        stringRedisTemplate.delete(COUPONSALL_KEY);
        return deleted;
    }

    /**
     * @Description:查询所有优惠券
     * @Author: M
     * @Date: 2024/8/20 星期二 14:20
     * <p>
     * * @return: com.ruoyi.common.core.web.domain.AjaxResult
     */
    @Override
    public AjaxResult couponsList(TbCoupons tbCoupons) {
        //查询缓存的优惠券信息
        String redisCouponsData = stringRedisTemplate.opsForValue().get(COUPONSALL_KEY);
        //如果缓存中有数据，直接查询缓存数据
        if (redisCouponsData != null) {
            //解析redis的优惠券信息
            List<TbCoupons> couponsList = JSON.parseArray(redisCouponsData, TbCoupons.class);
            //判断查询条件是否为空
            if (StringUtils.isEmpty(tbCoupons.getCouponsName())) {
                return AjaxResult.success(couponsList);
            }
            List<TbCoupons> filterList = couponsList.stream().filter(coupons -> coupons.getCouponsName().equals(tbCoupons.getCouponsName())).collect(Collectors.toList());


            return AjaxResult.success(filterList);
        }
        //如果没有，查询数据库的优惠券信息
        List<TbCoupons> couponsList = tbCouponsMapper.couponsList(tbCoupons);
        //将数据转为字符串
        String jsonCouponsList = JSON.toJSONString(couponsList);
        //设置过期时间  15分钟
        stringRedisTemplate.opsForValue().set(COUPONSALL_KEY, jsonCouponsList, 5, TimeUnit.MINUTES);
        return AjaxResult.success(couponsList);
    }

    /**
     * @Description:获取setnx锁
     * @Author: M
     * @Date: 2024/8/20 星期二 14:19
     * * @param key:
     * * @return: boolean
     */
    private boolean tryLock(String key) {
        //将键与值关联起来 如果key不存在 那么就设置过期时间为10秒钟
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return b;
    }

    /**
     * @Description:释放setnx锁
     * @Author: M
     * @Date: 2024/8/20 星期二 14:19
     * * @param key:
     * * @return: void
     */
    private void unLock(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * @Description:查询优惠券详细信息
     * @Author: M
     * @Date: 2024/8/20 星期二 14:20
     * * @param id:
     * * @return: com.ruoyi.common.core.web.domain.AjaxResult
     */
    @Override
    public AjaxResult couponsInfo(Long id) {
        //根据id查询优惠券信息
        String redisCouponsInfo = stringRedisTemplate.opsForValue().get(COUPONSINFO_KEY + id);
        if (redisCouponsInfo != null) {
            //解析redis的优惠券信息
            TbCoupons couponsInfo = JSON.parseObject(redisCouponsInfo, TbCoupons.class);
            return AjaxResult.success(couponsInfo);
        }
        TbCoupons couponsInfo = null;
        try {
            boolean b = tryLock(LOCK_KEY + id);
            if (!b) {
                //是redis的setnx锁防止缓存击穿
                Thread.sleep(500);
                return couponsInfo(id);
            }
            //如果没有，查询数据库的优惠券信息
            couponsInfo = tbCouponsMapper.couponsInfo(id);
            //防止缓存穿透
            //如果查询的优惠券不存在，创建一个空对象||或者使用布隆过滤器
            if (couponsInfo == null) {
                //将空对象存到缓存中
                //设置过期时间，防止占用缓存数据
                stringRedisTemplate.opsForValue().set(COUPONSINFO_KEY + id, "", 1, TimeUnit.MINUTES);
                return AjaxResult.error("优惠券不存在");
            }
            //将数据转为字符串
            String jsonCouponsInfo = JSON.toJSONString(couponsInfo);
            //设置过期时间  15分钟
            stringRedisTemplate.opsForValue().set(COUPONSINFO_KEY + id, jsonCouponsInfo, 5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unLock(LOCK_KEY + id);
        }
        return AjaxResult.success(couponsInfo);
    }

    @Override
    public AjaxResult getUserCoupon(Long couponId, Long userId) {
        // 假设通过某种方式获取到当前用户的 ID
   /*     Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();*/
        //  Long userId = 2L;  实际应用中应从登录信息等获取
        //查询redis缓存是否存在优惠券
        String couponsKey = STARTKEY + couponId;
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(couponsKey);
        //防止缓存穿透（如果优惠券不存在，生成一个空对象）
        if (entries.size() <= 0 && entries == null) {
            //缓存中不存在查询数据库
            TbCoupons coupon = tbCouponsMapper.couponsInfo(couponId);
            //数据库不存在生成一个空对象
            if (coupon == null) {
                String nullKey = "couponsNull:" + couponId;
                stringRedisTemplate.opsForHash().put(nullKey, "不存在的优惠券ID", couponId + "");
                stringRedisTemplate.opsForHash().getOperations().expire(nullKey, 60, TimeUnit.SECONDS);
                return AjaxResult.error("优惠券不存在");
            }
        }
        RLock lock = redissonUtils.getLock("coupons-lock:" + couponId);
        try {
            boolean b = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!b) {
                return AjaxResult.error("优惠券已被领取");
            }
            // 检查用户是否已领取过该优惠券以及领取次数是否达到限制
            String couponsSetKey = "couponsSet:" + couponId;
            Set<String> couponsSet = stringRedisTemplate.opsForSet().members(couponsSetKey);
            if (couponsSet.size() > 0 && couponsSet != null) {
                boolean userFlag = couponsSet.contains(String.valueOf(userId));
                if (userFlag) {
                    return AjaxResult.error("您已领取，请勿重复领取");
                }
            }
            //获取redis中的数量
            Integer count = Integer.valueOf(String.valueOf(entries.get("count")));
            if (count <= 0) {
                return AjaxResult.error("库存不足");
            }
            // 业务逻辑：更新优惠券的领取数量
            Long couponCount = stringRedisTemplate.opsForHash().increment(couponsKey, "count", -1);
            // 将优惠券与用户关联（例如在数据库中记录用户领取的优惠券）
            //存入数据
            int insertFlag = couponsGetMapper.associateUserWithCoupon(userId, couponId);
            //领取成功后向缓存添加领取记录
            stringRedisTemplate.opsForSet().add(couponsSetKey, "userId", String.valueOf(userId));
            //如果插入的数据条数是1或者是>1的数 那么就说明优惠券领取成功了
            if (insertFlag >= 1) {
                //如果能优惠券能领取成功 ， 那么数据库中的值就得和redis中的值是一致的
                //使用乐观锁防止超卖
                //修改后删除，保证mysql 和 redis 数据一致
                tbCouponsMapper.updateCountById(couponId, couponCount);
                stringRedisTemplate.delete(COUPONSALL_KEY);
            }
            return AjaxResult.success("领取成功");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //判断锁的状态
            if (lock.isLocked()) {
                //释放锁
                lock.unlock();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult startCoupons(Long[] ids) {
        //1.查询指定发放优惠券的信息
        List<TbCoupons> startCoupons = tbCouponsMapper.findStartCouponsByIds(ids);
        if (startCoupons.size() > 0 && startCoupons != null) {
            for (TbCoupons coupon : startCoupons) {
                //判断优惠券是否开放
                if (coupon.getCouponsCreateDate().after(new Date())) {
                    return AjaxResult.error("活动未开始");
                } else if (coupon.getCouponsExpirationDate().before(new Date())) {
                    return AjaxResult.error("活动已结束");
                } else {
                    //优惠券状态(0停止发放 1开始发放)
                    int couponsStatus = 1;
                    //2.修改优惠券的发放状态
                    int updateRows = tbCouponsMapper.startCoupons(ids, couponsStatus);
                    if (updateRows > 0) {
                        //开放优惠券存储redis的key
                        String couponsKey = STARTKEY + coupon.getId();
                        //3.将信息存到redis当中

                        //如果优惠券的数量小于0，不进行存储
                        if (coupon.getReceiveCount() <= 0) {
                            return AjaxResult.error(coupon.getCouponsName() + "的优惠券库存不足");
                        }
                        //存入优惠券的数量
                        stringRedisTemplate.opsForHash().put(couponsKey, "count", String.valueOf(coupon.getReceiveCount()));
                        //存入优惠券的名称
                        stringRedisTemplate.opsForHash().put(couponsKey, "name", coupon.getCouponsName());
                        //存入优惠券的编号（唯一）
                        stringRedisTemplate.opsForHash().put(couponsKey, "number", coupon.getCouponsNumber());
                        //设置优惠券开放的过期时间
                        long timeout = coupon.getCouponsExpirationDate().getTime() - coupon.getCouponsCreateDate().getTime();
                        stringRedisTemplate.expire(couponsKey, timeout, TimeUnit.SECONDS);
                    }
                    stringRedisTemplate.delete(COUPONSALL_KEY);
                    return AjaxResult.success("活动已开放");
                }
            }

        }
        return AjaxResult.error("优惠券不存在");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult closeCoupons(Long[] ids) {
        //优惠券状态(0停止发放 1开始发放)
        int couponsStatus = 0;
        //1.关闭优惠券的发放
        int updateRows = tbCouponsMapper.startCoupons(ids, couponsStatus);
        if (updateRows > 0) {
            for (Long id : ids) {
                //获取要开放的优惠券的key
                String couponsKey = STARTKEY + id;
                //查询redis优惠券的信息
                Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(couponsKey);
                //获取优惠券的库存
                Long count = Long.valueOf((String) entries.get("count"));
                //2.将缓存的优惠券库存信息重新同步到数据库
                tbCouponsMapper.updateCountById(id, count);
                //3.根据开放的优惠券的key删除
                stringRedisTemplate.delete(couponsKey);
            }
            stringRedisTemplate.delete(COUPONSALL_KEY);
            return AjaxResult.success("优惠券已关闭发放");
        }
        return AjaxResult.error("优惠券不存在");
    }

    @Override
    public AjaxResult usableCoupon(Long userId) {
        //获取锁
        RLock lock = redissonUtils.getLock("usableCoupon-lock:" + userId);
        try {
            //在这个时间内获取锁
            boolean b = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (b) {
                //查询用户可用的优惠券
                List<TbCoupons> usableCouponList = couponsGetMapper.usableCoupon(userId);

                return AjaxResult.success(usableCouponList);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return AjaxResult.error();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized AjaxResult useCoupon(Long orderId, Integer userId, Integer couponId) {
        //判断支付的状态
        OrderInfo orderInfo = orderInfoMapper.findOrderStatus(orderId);
        //使用java8使用Optional类型的对象包含不包含值
        if (!Optional.ofNullable(orderInfo).isPresent()) {
            return AjaxResult.error("订单还未创建");
        }
//        if(orderInfo==null){
//            return AjaxResult.error("订单还未创建");
//        }
        if (orderInfo.getOrderStatus() == 8) {
            //支付成功后添加优惠券使用记录
            couponsLogMapper.insertLog(orderId, userId, couponId);
            //修改用户优惠券的使用状态
            couponsGetMapper.updateCouponStatus(couponId, userId);
            return AjaxResult.error("使用记录创建成功");
        }
        return AjaxResult.error("订单还未支付");
    }

    @Override
    public AjaxResult redisAcquireLockLock(Long couponId, Long userId) {

        // 从数据库查询优惠券信息
        String tokenKey = "CouponKey-" + couponId;
        TbCoupons tbCoupons = tbCouponsMapper.selectCouponsById(couponId);
        stringRedisTemplate.opsForHash().put(tokenKey, "couponsName", tbCoupons.getCouponsName());
        stringRedisTemplate.opsForHash().put(tokenKey, "receiveCount", String.valueOf(tbCoupons.getReceiveCount()));

        // 本次优惠券领取唯一标记
        String eventsPromoCodeMark = tbCoupons.getCouponsNumber();

        // 如何连接池没有关闭，则直接获取连接池资源
        if (!jedisPool.isClosed()) {
            try (Jedis jedis = jedisPool.getResource()) {
                // 调用方法
                return getRedisResult(eventsPromoCodeMark, couponId, tokenKey, userId);
            }
        } else {
            // 如果连接池已关闭，重新初始化
            jedisPool = new JedisPool(redisConfig.jedisPoolConfig(), "localhost", 6379);
            // 调用方法
            return getRedisResult(eventsPromoCodeMark, couponId, tokenKey, userId);
        }
    }

    // 调用方法来获取Redis连接池进行判断
    private AjaxResult getRedisResult(String eventsPromoCodeMark, Long couponId, String tokenKey, Long userId) {
        // 从 Redis 连接池 (jedisPool) 中获取一个 Jedis 实例的方法。这个连接池用于管理与 Redis 服务器的连接，通过复用连接来提高性能。
        Jedis jedisResources = jedisPool.getResource();
        // 执行 Redis 操作
        String lock = userId + eventsPromoCodeMark;//设置本次优惠券锁（保证幂等性）：userId + 优惠码活动的key
        String userKey = lock + "receiveCoupon";//设置用户优惠券领取key（自定义，只要保证一个用户在一个活动的唯一性即可）

        // 获取缓存数量
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(tokenKey);
        Integer count = Integer.valueOf((String) entries.get("receiveCount"));

        //优惠码领取库存校验
        if (count <= 0) {
            System.out.println("优惠券已经领取完了");
            jedisResources.close();
            return AjaxResult.error("优惠券已经领取完了");
        }

        //为了避免竞争（同一个用户，多个设备同时领取）.使用Redis.setNX() 实现分布式锁（重复数据插入可用其来实现排他锁）
        boolean flag = acquireLock(lock);
        if (flag) {
            //1,获取锁成功,进行用户已领取标记，查询用户是否已经领取过
            Boolean isExists = jedisResources.exists(userKey);
            if (!isExists) {
                //2,先使用redis的decr可以实现原子性的递增递减操作控制优惠券不超送,
                Long success = jedisResources.decrBy(String.valueOf(count), -1);
                // 先减库存后发券（减库存后返回的现有库存数量大于等于0说明本次抢券成功，再进行发送优惠券，否则库存已经空了就不进行发送优惠券）
                if (success >= 0L) {
                    //3,再进行优惠券分发（可异步执行通过MQ进行发放，如果减库存成功，发放失败，进行发放补偿性操作）
                    jedisResources.set(userKey, "received");
                    stringRedisTemplate.opsForHash().increment(tokenKey, "receiveCount", -1);
                    tbCouponsMapper.updateByReceiveCount(couponId);
                    System.out.println("领取成功");

                    // 存储到优惠券领取表 coupons_get
                    couponsGetMapper.couponsReceive(userId, couponId);

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
     * @param lock: 锁的k
     * @Description: 获取锁方法
     * @Author: M
     * @Date: 2024/8/19 22:58
     * @return: boolean 本次获锁是否成功
     */
    public boolean acquireLock(String lock) {
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
