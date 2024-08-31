package com.ruoyi.order.service;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.OrderInfo;

import java.util.List;

/**
* @author DELL
* @description 针对表【order_info】的数据库操作Service
* @createDate 2024-08-17 14:45:25
*/
public interface OrderInfoService {

    List<OrderInfo> getOrderInfoList();

    int orderInsert(OrderInfo orderInfo);

    AjaxResult InsertOrder(OrderInfo orderInfo);

    AjaxResult orderById(Long driverId,String id);
}
