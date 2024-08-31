package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.DriverUser;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.vo.PassengerVo;

public interface OrderInfoService {

    AjaxResult cancelOrder(Integer id, Integer passengerId);

    AjaxResult computeAmount(PassengerVo passengerVo);

    AjaxResult showDriverById(String tel);

    OrderInfo getstate(String tel);
}
