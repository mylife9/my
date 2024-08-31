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
    public AjaxResult getUserCoupon(Long couponId, String openid) {
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
                boolean userFlag = couponsSet.contains(String.valueOf(openid));
                if (userFlag) {
                    return AjaxResult.error("请勿重复领取");
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
            int insertFlag = couponsGetMapper.associateUserWithCoupon(openid, couponId);
            //领取成功后向缓存添加领取记录
            stringRedisTemplate.opsForSet().add(couponsSetKey, "userId", String.valueOf(openid));
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
        if (startCoupons.size() <= 0 && startCoupons == null) {
            return AjaxResult.error("未查到优惠券信息");
        }
        for (TbCoupons coupon : startCoupons) {
            //判断优惠券是否开放
            if (coupon.getCouponsCreateDate().after(new Date())) {
                return AjaxResult.error("活动未开始");
            }
            if (coupon.getCouponsExpirationDate().before(new Date())) {
                return AjaxResult.error("活动已结束");
            }
            //优惠券状态(0停止发放 1开始发放)
            int couponsStatus = 1;
            //2.修改优惠券的发放状态
            int updateRows = tbCouponsMapper.startCoupons(ids, couponsStatus);
            if (updateRows <= 0) {
                return AjaxResult.error("数量不足");
            }
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
    public AjaxResult usableCoupon(String openid) {
        //获取锁
        RLock lock = redissonUtils.getLock("usableCoupon-lock:" + openid);
        try {
            //在这个时间内获取锁
            boolean b = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (b) {
                //查询用户可用的优惠券
                List<TbCoupons> usableCouponList = couponsGetMapper.usableCoupon(openid);

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
    public synchronized AjaxResult useCoupon( Long orderId, Integer userId, Integer couponId) {
        //判断支付的状态
        OrderInfo orderInfo = orderInfoMapper.findOrderStatus(orderId);
        //使用java8使用Optional类型的对象包含不包含值
        if (!Optional.ofNullable(orderInfo).isPresent()) {
            return AjaxResult.error("订单还未创建");
        }

        if (orderInfo.getOrderStatus() == 8) {
            //支付成功后添加优惠券使用记录
            couponsLogMapper.insertLog(orderId, userId, couponId);
            //修改用户优惠券的使用状态
            couponsGetMapper.updateCouponStatus(couponId, userId);
            return AjaxResult.error("使用记录创建成功");
        }
        return AjaxResult.error("订单还未支付");
    }


}
