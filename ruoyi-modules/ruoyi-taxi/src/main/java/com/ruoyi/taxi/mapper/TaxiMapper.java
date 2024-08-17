package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where passenger_id=#{passengerId}")
    PassengerUser selectPassenger(String passengerId);

    @Select("select * from order_info where passenger_id =#{passengerId}")
    OrderInfo selectOrder(Integer passengerId);

}
