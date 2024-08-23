package com.ruoyi.coupons.mapper;

import com.ruoyi.coupons.domain.TbCoupons;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: M
 * @Date: 2024/8/18 17:39:37
 * @Version: v1.0.0
 **/
@Mapper
public interface CouponsGetMapper {

    int associateUserWithCoupon(@Param("userId") Long userId, @Param("couponId") Long couponId);

    List<TbCoupons> usableCoupon(Long userId);

    void updateCouponStatus(@Param("couponId") Integer couponsId,@Param("userId") Integer userId);


    void couponsReceive(@Param("userId") Long userId, @Param("couponId") Long couponId);
}
