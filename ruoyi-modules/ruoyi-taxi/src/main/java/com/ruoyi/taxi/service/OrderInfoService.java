package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;

public interface OrderInfoService {

    AjaxResult CancelAnOrder(Integer id, Integer passengerId);

    AjaxResult ContactTheDriver(Integer id, Integer passengerId);

    AjaxResult refund(Integer id, Integer passengerId);
}
