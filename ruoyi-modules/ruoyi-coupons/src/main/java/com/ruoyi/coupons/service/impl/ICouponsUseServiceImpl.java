package com.ruoyi.coupons.service.impl;

import com.ruoyi.coupons.mapper.CouponsUseMapper;
import com.ruoyi.coupons.service.ICouponsUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: M
 * @Date: 2024/8/18 17:38:50
 * @Version: v1.0.0
 **/
@Service
public class ICouponsUseServiceImpl implements ICouponsUseService {
    @Autowired
    CouponsUseMapper couponsUseMapper;

    @Override
    public int getReceivedCountForUser(Long userId, Long couponId) {
        return couponsUseMapper.getReceivedCountForUser(userId, couponId);
    }

    @Override
    public int associateUserWithCoupon(Long userId, Long couponId) {


        return couponsUseMapper.associateUserWithCoupon(userId, couponId);
    }
}
