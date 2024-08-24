package com.ruoyi.taxi.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.taxi.controller.WebSocketController;
import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.TaxiService;
import com.ruoyi.taxi.util.Utils;
import com.ruoyi.taxi.utils.OrderUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class TaxiServiceImpl implements TaxiService {
    @Autowired
    TaxiMapper taxiMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WebSocketController webSocketController;



    @Override
    @Transactional
    public synchronized AjaxResult saveOrder( @Validated PassengerVo passengerVo) {

        //redis key值
        String key = "Order";
        //根据id获取用户信息
        PassengerUser passengerUser = taxiMapper.selectPassenger(passengerVo.getOpenid());


        //查询出用户最新的上一个订单
        OrderInfo orderStatus = taxiMapper.selectOrder(passengerUser.getOpenid());

        //如果有进行判断是否未支付
        if (Optional.ofNullable(orderStatus).isPresent()) {
            return AjaxResult.error("您有未支付订单");
        }

        //进行司机家庭地址和目的地的计算
        List<DriverUserWorkStatus> driverWorkList = taxiMapper.selectDriverWork();

        ArrayList<Long> integers = new ArrayList<>();

        for (DriverUserWorkStatus driverWork : driverWorkList) {

            //使用工具类计算上车位置和加位加位置的距离
            Double newDistance = Utils.calculateDistance(passengerVo.getDestLongitude(), passengerVo.getDestLatitude(), driverWork.getAddrLongitude(), driverWork.getAddrLatitude());

            // 增加1.5小时（1.5小时 = 1.5 * 60 * 60 * 1000 毫秒）
            long millisToAdd = (long) (1.5 * 60 * 60 * 1000);
            Date endDataTime = new Date(Utils.currentDateTime.getTime() + millisToAdd);


            OrderInfo orderInfo = taxiMapper.selectOrderInfo(driverWork.getDriverId(), Utils.currentDateTime, endDataTime);
            //有订单
            //java8 optional查询对象是否包含值
            if (Optional.ofNullable(orderInfo).isPresent()) {
                //跳出这个司机
                continue;
            }
            //判断5公里以内的开启回家模式司机并且而在接单状态
            if (newDistance < 5 && driverWork.getIsHeadingHome() == 1 && driverWork.getWorkStatus() == 3) {
                integers.add(driverWork.getDriverId());
            }
            //没有就距离用户3公里以内的
            Double distance = Utils.calculateDistance(passengerVo.getDestLongitude(), passengerVo.getDestLatitude(), driverWork.getAddrLongitude(), driverWork.getAddrLatitude());
            if (driverWork.getWorkStatus() == 3 && distance < 3) {
                integers.add(driverWork.getDriverId());
            }
        }
        if (integers.size() > 0) {
            //默认获取第一个司机
            passengerVo.setDriverId(integers.get(0));
        }


        passengerVo.setPassengerPhone(passengerUser.getPhone());

        String orderId = OrderUtil.createOrderId(passengerVo.getOpenid());
        passengerVo.setOrderId(orderId);

        taxiMapper.saveOrder(passengerVo);

        //删除缓存

        stringRedisTemplate.delete(key);

        //使用websocket推送消息
        webSocketController.sendMessage(JSON.toJSONString(passengerVo));


        return AjaxResult.success("添加成功");
    }
}