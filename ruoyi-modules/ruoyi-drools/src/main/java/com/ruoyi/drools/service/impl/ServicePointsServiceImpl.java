package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.mapper.ServicePointsMapper;
import com.ruoyi.drools.service.ServicePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:04
 * @description :
 **/
@Service
@Transactional
public class ServicePointsServiceImpl implements ServicePointsService {

    @Autowired
    ServicePointsMapper servicePointsMapper;


    @Override
    public void updateServiceScope(Double servicePoints1,Integer driverId) {
        servicePointsMapper.updateServiceScope(servicePoints1,driverId);
    }









}
