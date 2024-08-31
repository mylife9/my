package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.DriverUser;
import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where openid=#{openid}")
    PassengerUser selectPassenger(String openid);

    @Select("select * from order_info where openid =#{openid} and order_status!=8")
    OrderInfo selectOrder(String openid);

    int saveOrder(PassengerVo passengerVo);

    @Select("select * from driver_user_work_status")
    List<DriverUserWorkStatus> selectDriverWork();

    @Select("select * from passenger_user where id=#{id}")
    PassengerUser selectById(Integer id);


    OrderInfo selectOrderInfo(@Param("driverId") Long driverId, @Param("currentTime") Date currentTime, @Param("endTime") Date endTime);


    @Select("UPDATE passenger_user SET chargeback_number = 0 WHERE id = #{id}")
    void updateChargebackNumber(Integer passengerId, Integer chargebackNumber);

    @Select("select * from  driver_user")
    List<DriverUser> driverSelect();

    @Select("select * from  driver_user where id = #{aLong}")
    DriverUser driverSelectPhone(Long aLong);
}
