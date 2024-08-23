package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.vo.PassengerVo;

public interface OrderInfoService {

    AjaxResult cancelOrder(Integer id, Integer passengerId);

    AjaxResult computeAmount(PassengerVo passengerVo);

}
