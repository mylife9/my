package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where user_id=#{userId}")
    PassengerUser selectPassenger(String userId);

    @Select("select * from order_info where passenger_id =#{passengerId}")
    OrderInfo selectOrder(Integer passengerId);

    @Insert("insert into order_info(passenger_id,passenger_phone,passenger_phone,dep_longitude,dep_latitude,dest_longitude,dest_latitude,order_status,expect_distance,order_time,car_type,about_price,estimated_duration,vehicle_type,advance_amount,is_reserve)values (#{passengerId},#{passengerPhone},#{depLongitude},#{depLatitude},#{destLongitude},#{destLatitude},1,#{expectDistance},now(),#{carType},#{aboutPrice},#{estimatedAuration},#{vehicleType},#{advanceAmount},#{isReserve})")
    int saveOrder(PassengerVo passengerVo, PassengerUser user);

    @Select("select * from driver_user_work_status")
    List<DriverUserWorkStatus> selectDriverWork();

    @Select("select * from passenger_user where id=#{id}")
    PassengerUser selectById(Integer id);


    Boolean selectOrderInfo(@Param("driverId") Long driverId,@Param("currentTime") Date currentTime,@Param("endTime") Date endTime);


    @Select("UPDATE passenger_user SET chargeback_number = 0 WHERE id = #{id}")
    void updateChargebackNumber(Integer passengerId, Integer chargebackNumber);
}
