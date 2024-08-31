package com.ruoyi.order.service.impl;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 16:08
 */

import com.ruoyi.order.mapper.PassengerMapper;
import com.ruoyi.order.pojo.Passenger;
import com.ruoyi.order.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 成长
 * @date : 2024-08-22 16:08
 * @description :
 *
 **/
@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerMapper passengerMapper;
    @Override
    public Passenger selectByPassengerId(Long passengerId) {
         return passengerMapper.selectByPassengerId(passengerId);
    }

    @Override
    public void update(Double scope, Long passengerId) {
        passengerMapper.update(scope,passengerId);
    }

    @Override
    public void updates(Double scope, Long passengerId, Integer num) {
        passengerMapper.updates(scope,passengerId,num);
    }
}
