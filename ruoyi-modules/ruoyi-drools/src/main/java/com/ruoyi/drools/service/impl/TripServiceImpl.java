package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.mapper.TripMapper;
import com.ruoyi.drools.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:41
 * @description :
 **/
@Service
public class TripServiceImpl implements TripService {
    @Autowired
    TripMapper tripMapper;
    @Override
    public void update(Double scope, Integer num, Integer hour, Integer driverId) {
        tripMapper.update(scope,num,hour,driverId);
    }
}
