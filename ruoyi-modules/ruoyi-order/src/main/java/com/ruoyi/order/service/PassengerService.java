package com.ruoyi.order.service;

import com.ruoyi.order.pojo.Passenger;

/**
 * @author : 成长
 * @date : 2024-08-22 16:07
 * @description :
 **/
public interface PassengerService {
    Passenger selectByPassengerId(Long passengerId);

    void update(Double scope, Long passengerId);

    void updates(Double scope, Long passengerId, Integer num);
}
