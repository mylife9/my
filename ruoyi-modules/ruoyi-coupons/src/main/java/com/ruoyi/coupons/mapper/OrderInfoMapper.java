package com.ruoyi.coupons.mapper;

import com.ruoyi.coupons.domain.OrderInfo;

/**
 * @program: rouyi
 * @author: M
 * @description:
 * @create: 2024-08-20 21:43
 */
public interface OrderInfoMapper {
    OrderInfo findOrderStatus(Long orderId);
}
