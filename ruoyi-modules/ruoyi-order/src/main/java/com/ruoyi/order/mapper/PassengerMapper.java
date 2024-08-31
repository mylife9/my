package com.ruoyi.order.mapper;

import com.ruoyi.order.pojo.Passenger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author : 成长
 * @date : 2024-08-22 16:08
 * @description :
 **/
@Mapper
public interface PassengerMapper {
    @Select("select  * from passenger_points where passenger_id=#{passengerId}")
    Passenger selectByPassengerId(@Param("passengerId") Long passengerId);

    @Update("update passenger_points set passenger_scope=#{scope} where passenger_id=#{passengerId}")
    void update(@Param("scope") Double scope,@Param("passengerId") Long passengerId);
    @Update("update passenger_points set passenger_scope=#{scope},passenger_time=NOW(),num=#{num} where passenger_id=#{passengerId}")
    void updates(@Param("scope") Double scope,@Param("passengerId") Long passengerId,@Param("num")Integer num);
}
