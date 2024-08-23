package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.PassengerIdcard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassengerIdcardMapper {
    PassengerIdcard selectIdcardByOpenid(String openid);
    Integer saveIdcard(PassengerIdcard idcard);
    Integer updateIdcardByOpenid(PassengerIdcard idcard);
}
