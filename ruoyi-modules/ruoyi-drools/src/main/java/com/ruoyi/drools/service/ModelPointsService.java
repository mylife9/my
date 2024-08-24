package com.ruoyi.drools.service;

import com.ruoyi.drools.domain.ModelPoints;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 18:29
 * @description :
 **/
public interface ModelPointsService {
    void updateModelPoints(Double modelPoints1, Integer vehicleTypeId);

    List<ModelPoints> list();
}
