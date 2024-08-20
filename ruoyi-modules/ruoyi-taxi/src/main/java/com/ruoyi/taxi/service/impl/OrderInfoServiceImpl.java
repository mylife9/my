package com.ruoyi.taxi.service.impl;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.mapper.OrderMapper;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.OrderInfoService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TaxiMapper taxiMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public AjaxResult cancelOrder(Integer id, Integer passengerId) {
        //检查乘客今天取消次数是否已达三次
        PassengerUser passenger = taxiMapper.selectById(id);
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        //判断今天的取消次数
        if (passenger.getChargebackNumber()>=3 && localDate.equals(LocalDate.now())){
            return AjaxResult.error("今天已经取消三次,无法再取消");
        }
        OrderInfo info1 = orderMapper.selectById(id);
        //订单刚开始可以直接取消
        if (info1.getOrderStatus()==1 && info1.getOrderId()!=null && info1.getDriverPhone()!=null){
            //改为取消订单
            taxiMapper.updateChargebackNumber(passengerId,passenger.getChargebackNumber());
            //乘客今天取消次数加一
            passenger.setChargebackNumber(passenger.getChargebackNumber()+1);
            return AjaxResult.error("订单取消成功");
        }
        //检查订单状态查看司机是否已经接单,如果司机已经接单则联系司机取消订单
        if (info1.getOrderStatus()==2 && info1.getOrderId()!=null && info1.getDriverPhone()!=null){
            //返回司机手机号
            log.info("司机手机号:"+info1.getDriverPhone());
            return AjaxResult.error("请联系司机取消订单");
        }
        //更新订单状态已取消
        orderMapper.updateOrderStatus(info1);
        //更新乘客今天取消订单的次数
        passenger.setChargebackNumber(passenger.getChargebackNumber()+1);
        if (!localDate.equals(LocalDate.now())){
            taxiMapper.updateChargebackNumber(passengerId,passenger.getChargebackNumber());
        }
        return AjaxResult.success("订单取消成功");
    }
}
