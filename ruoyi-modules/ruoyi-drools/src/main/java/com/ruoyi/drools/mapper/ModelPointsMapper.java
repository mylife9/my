package com.ruoyi.drools.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 18:29
 * @description :
 **/
public interface ModelPointsMapper {
    @Update("update model_points set model_points = model_points + #{modelPoints1} where vehicle_type_id=#{vehicleTypeId}")
    void updateModelPoints(@Param("modelPoints1") Double modelPoints1,@Param("vehicleTypeId") Integer vehicleTypeId);

}
