package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.service.PassengerUserService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PassengerUserMapper {
    @Select("select * from passenger_user where passenger_phone = #{passengerPhone}")
    PassengerUser login(String passengerPhone);

    @Insert("insert into passenger_user set" +
            "gmt_create=now(),passenger_phone=#{passengerPhone},passenger_name=#{passengerName}," +
            "passenger_gender=#{passengerGender}")
    Integer registerUser(PassengerUser user);
}
