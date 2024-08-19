package com.ruoyi.coupons.service.impl;

import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.CouponsUseMapper;
import com.ruoyi.coupons.mapper.TbCouponsMapper;
import com.ruoyi.coupons.service.ICouponsUseService;
import com.ruoyi.coupons.utils.RedissonUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: M
 * @Date: 2024/8/18 17:38:50
 * @Version: v1.0.0
 **/
@Service
public class ICouponsUseServiceImpl implements ICouponsUseService {
    @Resource
    private CouponsUseMapper couponsUseMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TbCouponsMapper tbCouponsMapper;
    @Resource
    private RedissonUtils redissonUtils;

    @Override
    public int getReceivedCountForUser(Long userId, Long couponId) {
        return couponsUseMapper.getReceivedCountForUser(userId, couponId);
    }

    @Override
    public int associateUserWithCoupon(Long userId, Long couponId) {
        return couponsUseMapper.associateUserWithCoupon(userId, couponId);
    }

    @Override
    public String getUserCoupon(Long couponId, Long userId) {
        // 假设通过某种方式获取到当前用户的 ID
   /*     Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();*/
//        Long userId = 2L;  // 实际应用中应从登录信息等获取
        //查询redis缓存是否存在优惠券
        String couponsKey = "coupons:" + couponId;
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(couponsKey);
        //防止缓存穿透（如果优惠券不存在，生成一个空对象）
        if (entries.size() <= 0 || entries == null) {
            TbCoupons coupon = tbCouponsMapper.selectOne(couponId);
            if (coupon == null) {
                String nullKey = "couponsNull:" + couponId;
                stringRedisTemplate.opsForHash().put(nullKey, "不存在的优惠券ID", couponId+"");
                stringRedisTemplate.opsForHash().getOperations().expire(nullKey, 60, TimeUnit.SECONDS);
                return "优惠券不存在";
            }
        }
        // 检查用户是否已领取过该优惠券以及领取次数是否达到限制
        String couponsSetKey = "couponsSet:" + couponId;
        Set<String> couponsSet = stringRedisTemplate.opsForSet().members(couponsSetKey);
        if (entries.size() > 0 || entries != null) {
            boolean userFlag = couponsSet.contains(userId + "");
            if (userFlag) {
                return "您已领取，请勿领取";
            }
        }
        //获取redis中的数量
        Integer count = Integer.valueOf(String.valueOf(entries.get("count")));
        if (count <= 0) {
            return "库存不足";
        }
        RLock lock = redissonUtils.getLock("coupons-lock:" + couponId);
        try {
            boolean b = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (!b) {
                return "优惠券已被领取";
            }
            // 业务逻辑：更新优惠券的领取数量
            Long couponCount = stringRedisTemplate.opsForHash().increment(couponsKey, "count", -1);
            // 将优惠券与用户关联（例如在数据库中记录用户领取的优惠券）
            //存入数据
            int insertFlag = couponsUseMapper.associateUserWithCoupon(userId, couponId);
            //领取成功后向缓存添加领取记录
            stringRedisTemplate.opsForSet().add(couponsSetKey, "userId", userId + "");
            //如果插入的数据条数是1或者是>1的数 那么就说明优惠券领取成功了
            if (insertFlag >= 1) {
                //如果能优惠券能领取成功 ， 那么数据库中的值就得和redis中的值是一致的
                tbCouponsMapper.updateCountById(couponId, couponCount);
            }
            return "领取成功";
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
}
