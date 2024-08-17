package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.PassengerUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaxiMapper {
    @Select("select * from passenger_user where user_id=#{userId}")
    PassengerUser selectPassenger(String userId);
}
