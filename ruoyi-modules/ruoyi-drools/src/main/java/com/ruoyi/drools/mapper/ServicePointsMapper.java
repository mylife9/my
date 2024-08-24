package com.ruoyi.drools.mapper;
import com.ruoyi.drools.domain.ServicePoints;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 19:03
 * @description :
 **/
public interface ServicePointsMapper {

    @Update("update service_points set servicePoints = servicePoints + #{servicePoints1} where driverId=#{driverId}")
    void updateServiceScope(@Param("servicePoints1") Double servicePoints1,@Param("driverId") Integer driverId);

    @Select("select * from service_points limit 10;")
    List<ServicePoints> list();


    @Update("update service_points set servicePoints = servicePoints - #{servicePoints1} where driverId=#{driverId}")
    void updateServiceScope1(@Param("servicePoints1") Double servicePoints1, @Param("driverId") Integer driverId);
}
