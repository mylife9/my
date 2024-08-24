package com.ruoyi.drools.mapper;

import com.ruoyi.drools.domain.TripPoints;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:41
 * @description :
 **/
public interface TripMapper {
    @Update("update travel_points set tripScope=#{tripScope} where driverId=#{driverId1}")
    void update(@Param("tripScope") Double tripScope, @Param("driverId1") Integer driverId1);
    @Select("select * from travel_points limit 10;")
    List<TripPoints> list();
    @Insert("insert into travel_points (tripHour,tripScope,bonusPointId,createTime)" +
            "values (#{hour},#{tripScope},1,NOW())")
    void insert(@Param("tripScope") Double tripScope, @Param("hour") Integer hour);
}
