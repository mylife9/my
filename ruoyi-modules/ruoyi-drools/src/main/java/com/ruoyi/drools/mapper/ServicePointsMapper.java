package com.ruoyi.drools.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:03
 * @description :
 **/
public interface ServicePointsMapper {

    @Update("update service_points set service_points = service_points + #{servicePoints1} where driver_id=#{driverId}")
    void updateServiceScope(@Param("servicePoints1") Double servicePoints1,@Param("driverId") Integer driverId);

}
