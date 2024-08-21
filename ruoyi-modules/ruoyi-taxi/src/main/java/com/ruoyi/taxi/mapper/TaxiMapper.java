package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where open_id=#{openId}")
    PassengerUser selectPassenger(Long openId);

    @Select("select * from order_info where open_id =#{openId} and order_status=6")
    OrderInfo selectOrder(Long openId);

    int saveOrder(PassengerVo passengerVo);

    @Select("select * from driver_user_work_status")
    List<DriverUserWorkStatus> selectDriverWork();

    @Select("select * from passenger_user where id=#{id}")
    PassengerUser selectById(Integer id);


    OrderInfo selectOrderInfo(@Param("driverId") Long driverId, @Param("currentTime") Date currentTime, @Param("endTime") Date endTime);


    @Select("UPDATE passenger_user SET chargeback_number = 0 WHERE id = #{id}")
    void updateChargebackNumber(Integer passengerId, Integer chargebackNumber);
}
