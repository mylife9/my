package com.ruoyi.taxi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.taxi.domain.PassengerUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {
    PassengerUser selectPassengerByOpenid(String openid);

    Integer registerUser(PassengerUser user);

    Integer updateUser(PassengerUser user);

    PassengerUser isFinish(String openid);
}
