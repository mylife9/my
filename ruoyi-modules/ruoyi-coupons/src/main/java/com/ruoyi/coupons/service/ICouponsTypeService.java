package com.ruoyi.coupons.service;

import com.ruoyi.coupons.domain.CouponsType;
import com.ruoyi.coupons.domain.TbCoupons;

import java.util.List;

/**
 * @Author: M
 * @Date: 2024/8/16 22:08:04
 * @Version: v1.0.0
 **/

public interface ICouponsTypeService {


    List<CouponsType> selectListType();


    TbCoupons selectCoupons(Long id);
}
