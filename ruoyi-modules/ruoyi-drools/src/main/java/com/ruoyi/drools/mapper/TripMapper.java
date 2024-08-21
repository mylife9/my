package com.ruoyi.drools.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:41
 * @description :
 **/
public interface TripMapper {
    @Update("update travel_points set trip_scope=#{scope},num=#{num},trip_hour=#{hour},create_time=NOW() where driver_id=#{driverId}")
    void update(@Param("scope") Double scope, @Param("num") Integer num, @Param("hour") Integer hour, @Param("driverId") Integer driverId);
}
