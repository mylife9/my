package com.ruoyi.order.mapper;

import com.ruoyi.order.pojo.DriverPoints;
import com.ruoyi.order.pojo.vo.PointsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author : 成长
 * @date : 2024-08-22 11:45
 * @description :
 **/
@Mapper
public interface DriverPointsMapper {
    @Update("update driver_points  set driver_scope=#{driverScope},create_time=Now() where driver_id=#{driverId}")
    void update(@Param("driverScope") Double driverScope,@Param("driverId") Long driverId);

    @Select("select * from driver_points where driver_id=#{driverId}")
    DriverPoints selectById(@Param("driverId") Long driverId);

    @Select("  SELECT * FROM driver_points WHERE driver_id=#{driverId}")
    DriverPoints selectByDriverId(@Param("driverId") Long driverId);

    @Select("select score_value,score_name from  score s where id = #{sid}")
    PointsVo selectByPonts(Integer sid);

}
