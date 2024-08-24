package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.domain.TripPoints;
import com.ruoyi.drools.mapper.TripMapper;
import com.ruoyi.drools.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void update(Double tripScope, Integer driverId1) {
        tripMapper.update(tripScope,driverId1);
    }

    @Override
    public List<TripPoints> list() {
        return tripMapper.list();
    }

    @Override
    public void insert(Double tripScope, Integer hour) {
        tripMapper.insert(tripScope,hour);
    }


}
