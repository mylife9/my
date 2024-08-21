package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;

public interface OrderInfoService {

    AjaxResult cancelOrder(Integer id, Integer passengerId);
}
