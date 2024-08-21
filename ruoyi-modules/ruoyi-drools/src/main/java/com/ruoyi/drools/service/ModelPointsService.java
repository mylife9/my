package com.ruoyi.drools.service;

import org.apache.ibatis.annotations.Update;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 18:29
 * @description :
 **/
public interface ModelPointsService {
    void updateModelPoints(Double modelPoints1, Integer vehicleTypeId);

}
