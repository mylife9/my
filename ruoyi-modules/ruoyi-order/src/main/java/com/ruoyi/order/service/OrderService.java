package com.ruoyi.order.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.OrderInfo;

import java.text.ParseException;

/**
 * @author : Gao
 * @date : 2024-08-21 12:59
 * @description :
 **/
public interface OrderService {
    AjaxResult orderUpdate(OrderInfo orderInfo);

    AjaxResult orderUpdateOk(OrderInfo orderInfo);


    AjaxResult detectionMethod2(Long driverId) throws ParseException;

    AjaxResult saveOrderInfoToRedis(OrderInfo orderInfo) throws ParseException;

    AjaxResult checkOrderAddr(OrderInfo newOrder) throws ParseException;

    AjaxResult detectionMethod1(OrderInfo orderInfo);

    AjaxResult detectionMethod3(OrderInfo orderInfo);

    AjaxResult userCancelOrder(OrderInfo orderInfo);
}
