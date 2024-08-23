package com.ruoyi.drools.service;

import com.ruoyi.drools.domain.TripPoints;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:41
 * @description :
 **/
public interface TripService {
    void update(Double tripScope, Integer driverId1);

    List<TripPoints> list();


    void insert(Double tripScope, Integer hour);
}
