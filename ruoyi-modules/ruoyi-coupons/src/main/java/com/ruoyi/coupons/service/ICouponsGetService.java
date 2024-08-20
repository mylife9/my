package com.ruoyi.coupons.service;

/**
 * @Author: M
 * @Date: 2024/8/18 17:38:37
 * @Version: v1.0.0
 **/
public interface ICouponsGetService {

    int associateUserWithCoupon(Long userId, Long couponId);
}
