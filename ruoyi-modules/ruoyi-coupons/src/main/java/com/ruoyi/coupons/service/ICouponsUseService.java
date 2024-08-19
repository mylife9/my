package com.ruoyi.coupons.service;

/**
 * @Author: M
 * @Date: 2024/8/18 17:38:37
 * @Version: v1.0.0
 **/
public interface ICouponsUseService {
    int getReceivedCountForUser(Long userId, Long couponId);

    int associateUserWithCoupon(Long userId, Long couponId);

    String getUserCoupon(Long couponId, Long userId);
}
