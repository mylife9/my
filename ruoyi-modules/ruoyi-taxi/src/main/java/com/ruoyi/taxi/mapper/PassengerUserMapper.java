package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.service.PassengerUserService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PassengerUserMapper {

    PassengerUser isRegister(String openid);

    Integer registerUser(PassengerUser user);

    Integer updateUser(PassengerUser user);
}
