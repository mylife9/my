package com.ruoyi.order.utils;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.OrderInfo;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @program: ruoyi-car
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-23 11:32
 */
public class AddressUtils {
    // MIN_DISTANCE_THRESHOLD 是判断刷单行为的距离阈值，单位米
    private static final double MIN_DISTANCE_THRESHOLD = 1000; // 假设1000米为阈值
    // 地球半径，单位米
    private static final double R = 6371000;
    // 简单的距离计算（这里使用Haversine公式简化版）
    private static double calculateDistance(OrderInfo lastOrder, OrderInfo newOrder) {
        //判断上个订单的状态是否是正常状态
        //正常结束订单

        if( !StringUtils.isEmpty(lastOrder.getPickUpPassengerLatitude())&&
                !StringUtils.isEmpty(newOrder.getPassengerGetoffLatitude()) &&
                !StringUtils.isEmpty(lastOrder.getPickUpPassengerLongitude())&&
                !StringUtils.isEmpty(newOrder.getPassengerGetoffLongitude())&&
                lastOrder.getOrderStatus()!=9){
            double latDistance = Math.toRadians(Double.valueOf(newOrder.getPassengerGetoffLatitude()) - Double.valueOf(lastOrder.getPickUpPassengerLatitude()));
            double lonDistance = Math.toRadians(Double.valueOf(newOrder.getPassengerGetoffLongitude()) - Double.valueOf(lastOrder.getPickUpPassengerLongitude()));
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                    Math.cos(Math.toRadians(Double.valueOf(lastOrder.getPickUpPassengerLatitude()))) * Math.cos(Math.toRadians(Double.valueOf(newOrder.getToPickUpPassengerLatitude()))) *
                            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            return R * c; // 返回单位为米
        }else if( !StringUtils.isEmpty(lastOrder.getPickUpPassengerLatitude())&&
                !StringUtils.isEmpty(newOrder.getPassengerGetoffLatitude()) &&
                !StringUtils.isEmpty(lastOrder.getPickUpPassengerLongitude())&&
                !StringUtils.isEmpty(newOrder.getPassengerGetoffLongitude())){
                //订单是异常状态结束的（取消）
                double latDistance = Math.toRadians(Double.valueOf(newOrder.getToPickUpPassengerLatitude()) - Double.valueOf(lastOrder.getPickUpPassengerLatitude()));
                double lonDistance = Math.toRadians(Double.valueOf(newOrder.getToPickUpPassengerLongitude()) - Double.valueOf(lastOrder.getPickUpPassengerLongitude()));
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                        Math.cos(Math.toRadians(Double.valueOf(lastOrder.getToPickUpPassengerLatitude()))) * Math.cos(Math.toRadians(Double.valueOf(newOrder.getToPickUpPassengerLatitude()))) *
                                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                return R * c; // 返回单位为米
        }
        return -1;
    }
    // 接收新订单并判断是否存在刷单行为
    public static AjaxResult receiveOrder(OrderInfo lastOrder, OrderInfo newOrder) {
        if (lastOrder != null) {
            double distance = calculateDistance(lastOrder, newOrder);
            if(distance==-1){
                return AjaxResult.error(404,"订单信息尚未完善");
            }
            if(distance < MIN_DISTANCE_THRESHOLD){
                return AjaxResult.error();
            }
        }
        //如果上个订单为null，说明未接单
        return AjaxResult.success();
    }
}
