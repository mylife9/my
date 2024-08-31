package com.ruoyi.order.config;

import com.ruoyi.order.mapper.OrderMapper;
import com.ruoyi.order.pojo.Driver;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.utils.KilometreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @program: ruoyi-car
 * @author: gtx
 * @description:
 * @create: 2024-08-24 10:45
 */
@Component
@Slf4j
public class ClickFarmingConfig {


    @Autowired
    OrderMapper orderMapper;

    @Autowired
    KilometreUtils kilometreUtils;

    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/24 10:46
     *检测司机是否有刷单行为
     * @return: void
     *
     */
    @Scheduled(cron = " 0/30 * * * * ? ")
    public void clickFarmingScheduled(){


        List<Driver> list = orderMapper.driverList();

        for (Driver driver : list) {

            //根据司机id查询订单
            List<OrderInfo> orders = orderMapper.list(driver.getId());

            for (OrderInfo order : orders) {

                Date orderTime = order.getOrderTime();
                Date date = new Date();
                Instant instant = orderTime.toInstant();
                Instant instant2 = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
                LocalDateTime localDateTime1 = instant2.atZone(zoneId).toLocalDateTime();

                LocalDateTime time = localDateTime.minusMinutes(1);
                LocalDateTime now = localDateTime1.minusMinutes(24);

                Instant instant1 = time.atZone(zoneId).toInstant();
                Instant instant3 = now.atZone(zoneId).toInstant();

                Date from = Date.from(instant1);
                Date nowTime = Date.from(instant3);

                //根据时间判断是否刷单
                List<OrderInfo> orderInfos = orderMapper.orderSelectDate(driver.getId(), from, orderTime);

                if (orderInfos.size() > 10){
                    //假设1小时内超过10个订单可能是刷单
                    log.info("可能存在刷单行为....");
                }

                //根据下单次数最多的乘客及其下单次数
                List<OrderInfo> countList = orderMapper.countList(nowTime,date);

                for (OrderInfo orderInfo : countList) {

                    //判断用户下单次数超过一定阈值（例如5次），则可能存在刷单行为
                    if (orderInfo.getCount() > 5){
                        log.info("可能存在刷单行为....");
                    }

                }

                //根据司机在一个较短的时间段内取消了大量的订单，这可能表明司机在尝试刷单
                List<OrderInfo> orderList = orderMapper.selectDate(nowTime,date);

                Integer orderCount = 0;
                for (OrderInfo orderInfo : orderList) {

                    if (orderInfo.getOrderStatus() == 9){
                        orderCount++;
                    }

                }

                if (orderCount > 6){
                    log.info("可能存在刷单行为....");
                }

            }

        }

    }

}
