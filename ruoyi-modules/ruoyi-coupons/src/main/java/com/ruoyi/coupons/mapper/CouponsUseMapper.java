package com.ruoyi.coupons.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: M
 * @Date: 2024/8/18 17:39:37
 * @Version: v1.0.0
 **/
@Mapper
public interface CouponsUseMapper {
    int getReceivedCountForUser(@Param("userId") Long userId, @Param("couponId") Long couponId);

    int associateUserWithCoupon(@Param("userId") Long userId, @Param("couponId") Long couponId);

}
