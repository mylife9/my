package com.ruoyi.drools.mapper;

import com.ruoyi.drools.domain.Points;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:03
 * @description :
 **/
public interface PointsMapper {
    @Select("select * from points")
    List<Points> list();
    @Update("update points set service_score= service_score + #{scope} where driver_id=1")
    void update1(Double scope);
    @Update("update points set compliance_score= compliance_score + #{scope} where driver_id=1")
    void update2(Double scope);
    @Update("update points set trip_score= trip_score + #{scope} where driver_id=1")
    void update3(Double scope);

}
