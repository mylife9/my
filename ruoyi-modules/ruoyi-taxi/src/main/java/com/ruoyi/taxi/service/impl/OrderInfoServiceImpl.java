
package com.ruoyi.taxi.service.impl;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.mapper.OrderMapper;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.OrderInfoService;
import com.ruoyi.taxi.util.Utils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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



    //当前时间
    public static final LocalTime now = LocalTime.now();
    private static final LocalTime start = LocalTime.of(23, 0);
    private static final LocalTime end = LocalTime.of(5, 0);


    @Override
    public AjaxResult cancelOrder(Integer id, Integer passengerId) {
        //检查乘客今天取消次数是否已达三次
        PassengerUser passenger = taxiMapper.selectById(id);
        //获取当前日期
        LocalDate localDate = LocalDate.now();
        //判断今天的取消次数
        if (passenger.getChargebackNumber() >= 3 && localDate.equals(LocalDate.now())) {
            return AjaxResult.error("今天已经取消三次,无法再取消");
        }
        OrderInfo info1 = orderMapper.selectById(id);
        //订单刚开始可以直接取消
        if (info1.getOrderStatus() == 1 && info1.getOrderId() != null && info1.getDriverPhone() != null) {
            //改为取消订单
            taxiMapper.updateChargebackNumber(passengerId, passenger.getChargebackNumber());
            //乘客今天取消次数加一
            passenger.setChargebackNumber(passenger.getChargebackNumber() + 1);
            return AjaxResult.error("订单取消成功");
        }
        //检查订单状态查看司机是否已经接单,如果司机已经接单则联系司机取消订单
        if (info1.getOrderStatus() == 2 && info1.getOrderId() != null && info1.getDriverPhone() != null) {
            //返回司机手机号
            log.info("司机手机号:" + info1.getDriverPhone());
            return AjaxResult.error("请联系司机取消订单");
        }
        //更新订单状态已取消
        orderMapper.updateOrderStatus(info1);
        //更新乘客今天取消订单的次数
        passenger.setChargebackNumber(passenger.getChargebackNumber() + 1);
        if (!localDate.equals(LocalDate.now())) {
            taxiMapper.updateChargebackNumber(passengerId, passenger.getChargebackNumber());
        }
        return AjaxResult.success("订单取消成功");
    }

    @Override
    public AjaxResult computeAmount(PassengerVo passengerVo) {

        //初始化预付金额
        double aboutPrice = 0;

        //普通叫车服务
        if (passengerVo.getVehicleType() == 1) {

            switch (passengerVo.getCarType()) {
                case 1: // 新能源
                    aboutPrice = Utils.calculatePriceForNewEnergy(passengerVo);
                    break;
                case 2: // 舒适型
                    aboutPrice = Utils.calculatePriceForComfort(passengerVo);
                    break;
                case 3: // 豪华型
                    aboutPrice = Utils.calculatePriceForLuxury(passengerVo);
                    break;
                case 4: // 商务型
                    aboutPrice = Utils.calculatePriceForBusiness(passengerVo);
                    break;
                default:
                    aboutPrice = 0; // 或者设置为其他默认值，或者抛出异常
            }

            //如果是夜间
            boolean isNight = now.isAfter(start) || now.isBefore(end);
            if (isNight) {
                aboutPrice += passengerVo.getExpectDistance();
            }

            //判断是否预约用车
            if (passengerVo.getIsReserve() == 1) {
                if (aboutPrice < 30) {
                    aboutPrice = 30.0;
                }

                Double expectedPrice = aboutPrice;
            }
        }
        // 半日租
        if (passengerVo.getVehicleType() == 2) {
            double basePrice = 0;
            double timeRate = 0;
            double distanceRate = 0;

            switch (passengerVo.getCarType()) {
                case 1: // 新能源
                    basePrice = 300.0;
                    timeRate = 0.3;
                    distanceRate = 2.3;
                    break;
                case 2: // 舒适型
                    basePrice = 350.0;
                    timeRate = 0.6;
                    distanceRate = 4.0;
                    break;
                case 3: // 豪华型
                    basePrice = 400.0;
                    timeRate = 0.7;
                    distanceRate = 5.0;
                    break;
                case 4: // 商务型
                    basePrice = 500.0;
                    timeRate = 1.0; // 注意这里我假设时间超出费用是按每分钟全额计算的
                    distanceRate = 5.5;
                    break;
                default:
                    // 处理未知车辆类型
                    break;
            }

            double extraTimeCost = 0;
            double extraDistanceCost = 0;

            //时间的费用
            if (passengerVo.getEstimatedDuration() > 240) {
                extraTimeCost = (passengerVo.getEstimatedDuration() - 240) * timeRate;
            }

            //计算距离费用
            if (passengerVo.getExpectDistance() > 50) {
                extraDistanceCost = (passengerVo.getExpectDistance() - 50) * distanceRate;
            }

            //核算总价
            aboutPrice = basePrice + extraTimeCost + extraDistanceCost;
        }


        // 日租价格计算逻辑
        if (passengerVo.getVehicleType() == 3) {
            double basePrice = 0;
            double timeRate = 0;
            double distanceRate = 0;

            switch (passengerVo.getCarType()) {
                case 1: // 新能源
                    basePrice = 550.0;
                    timeRate = 0.3;
                    distanceRate = 2.3;
                    break;
                case 2: // 舒适型
                    basePrice = 650.0;
                    timeRate = 0.6;
                    distanceRate = 4.0;
                    break;
                case 3: // 豪华型
                    basePrice = 750.0;
                    timeRate = 0.7;
                    distanceRate = 5.0;
                    break;
                case 4: // 商务型
                    basePrice = 950.0;
                    timeRate = 1.0; // 注意：这里假设每超出一分钟加收1.0元，具体数值根据实际情况调整
                    distanceRate = 5.5;
                    break;
                default:
                    // 处理未知车辆类型
                    break;
            }
            double extraTimeCost = 0;
            double extraDistanceCost = 0;

            //时间的费用
            if (passengerVo.getEstimatedDuration() > 560) {
                extraTimeCost = (passengerVo.getEstimatedDuration() - 560) * timeRate;
            }

            //计算距离费用
            if (passengerVo.getExpectDistance() > 100) {
                extraDistanceCost = (passengerVo.getExpectDistance() - 100) * distanceRate;
            }

            //核算总价
            aboutPrice = basePrice + extraTimeCost + extraDistanceCost;
        }


            System.out.println(aboutPrice);

            return AjaxResult.success(aboutPrice);

    }
}