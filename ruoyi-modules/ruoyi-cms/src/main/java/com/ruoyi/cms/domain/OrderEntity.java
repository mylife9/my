package com.ruoyi.cms.domain;

import java.util.List;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/31 14:41
 * @注释
 */
public class OrderEntity {
    private  Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    private List<OrderItem> items;
}
