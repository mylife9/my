package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.mapper.ModelPointsMapper;
import com.ruoyi.drools.service.ModelPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 18:30
 * @description :
 **/
@Service
@Transactional
public class ModelPointsServiceImpl implements ModelPointsService {

    @Autowired
    ModelPointsMapper modelPointsMapper;
    @Override
    public void updateModelPoints(Double modelPoints1, Integer vehicleTypeId) {
        modelPointsMapper.updateModelPoints(modelPoints1,vehicleTypeId);
    }
}
