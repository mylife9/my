package com.ruoyi.drools.mapper;

import com.ruoyi.drools.domain.ModelPoints;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 18:29
 * @description :
 **/
public interface ModelPointsMapper {
    @Update("update model_points set modelPoints = modelPoints + #{modelPoints1} where vehicleTypeId=#{vehicleTypeId}")
    void updateModelPoints(@Param("modelPoints1") Double modelPoints1,@Param("vehicleTypeId") Integer vehicleTypeId);
    @Select("select * from model_points limit 10")
    List<ModelPoints> list();
}
