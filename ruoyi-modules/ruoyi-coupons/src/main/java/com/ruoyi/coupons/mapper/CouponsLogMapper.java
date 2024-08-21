package com.ruoyi.coupons.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @program: rouyi
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-20 22:05
 */
public interface CouponsLogMapper {
    void insertLog(@Param("orderId") Long orderId, @Param("userId")Integer userId, @Param("couponId")Integer couponId);
}
