package com.ruoyi.drools.service;


import com.ruoyi.drools.domain.ServicePoints;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:04
 * @description :
 **/
public interface ServicePointsService {


    void updateServiceScope(Double servicePoints1,Integer driverId);


    List<ServicePoints> list();

    void updateServiceScope1(Double servicePoints1, Integer driverId);
}
