package com.ruoyi.taxi.service;

import com.ruoyi.taxi.domain.PassengerIdcard;

public interface PassengerIdcardService {
    PassengerIdcard getIdcardInfo(String openid);
    Integer updateIdcard(PassengerIdcard idcard);
    Integer saveIdcard(PassengerIdcard passengerIdcard);
}
