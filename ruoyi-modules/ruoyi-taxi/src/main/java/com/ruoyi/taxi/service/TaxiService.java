package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.vo.PassengerVo;

public interface TaxiService {
    AjaxResult saveOrder(String token, PassengerVo passengerVo);

}
