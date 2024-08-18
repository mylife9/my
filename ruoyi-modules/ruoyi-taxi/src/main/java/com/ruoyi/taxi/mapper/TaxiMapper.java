package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where passenger_id=#{passengerId}")
    PassengerUser selectPassenger(String passengerId);

    @Select("select * from order_info where passenger_id =#{passengerId}")
    OrderInfo selectOrder(Integer passengerId);

    @Insert("insert into order_info(passenger_id,passenger_phone,passenger_phone,dep_longitude,dep_latitude,dest_longitude,dest_latitude,order_status,expect_distance,order_time)values (#{passengerId},#{passengerPhone},#{depLongitude},#{depLatitude},#{destLongitude},#{destLatitude},1,#{expectDistance},now())")
    int saveOrder(PassengerVo passengerVo, PassengerUser user);
}
