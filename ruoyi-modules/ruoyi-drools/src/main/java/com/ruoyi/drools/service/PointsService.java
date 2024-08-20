package com.ruoyi.drools.service;

import com.ruoyi.drools.domain.Points;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:04
 * @description :
 **/
public interface PointsService {
    List<Points> list();

    void upate1(Double scope);

    void update2(Double scope);

    void upate3(Double scope);


}
