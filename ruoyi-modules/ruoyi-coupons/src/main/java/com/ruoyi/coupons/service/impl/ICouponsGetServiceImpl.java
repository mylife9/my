package com.ruoyi.coupons.service.impl;

import com.ruoyi.coupons.mapper.CouponsGetMapper;
import com.ruoyi.coupons.service.ICouponsGetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: M
 * @Date: 2024/8/18 17:38:50
 * @Version: v1.0.0
 **/
@Service
public class ICouponsGetServiceImpl implements ICouponsGetService {
    @Resource
    private CouponsGetMapper couponsUseMapper;


    @Override
    public int associateUserWithCoupon(Long userId, Long couponId) {
        return couponsUseMapper.associateUserWithCoupon(userId, couponId);
    }

}
