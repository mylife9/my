package com.ruoyi.coupons.service.impl;

import com.ruoyi.coupons.domain.CouponsType;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.CouponsTypeMapper;
import com.ruoyi.coupons.service.ICouponsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: M
 * @Date: 2024/8/16 22:10:12
 * @Version: v1.0.0
 **/
@Service
public class CouponsTypeServiceImpl implements ICouponsTypeService {

    @Resource
    private CouponsTypeMapper couponsTypeMapper;

    @Override
    public List<CouponsType> selectListType() {
        return couponsTypeMapper.selectListType();
    }


}