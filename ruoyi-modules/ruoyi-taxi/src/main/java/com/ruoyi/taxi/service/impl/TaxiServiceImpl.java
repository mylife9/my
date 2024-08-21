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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class TaxiServiceImpl implements TaxiService {
    @Autowired
    TaxiMapper taxiMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    WebSocketController webSocketController;

    //当前时间
    public static final LocalTime now = LocalTime.now();
    private static final LocalTime start = LocalTime.of(23, 0);
    private static final LocalTime end = LocalTime.of(5, 0);

    @Override
    @Transactional
    public AjaxResult saveOrder( @Validated PassengerVo passengerVo) {

        //redis key值
        String key = "Order";
        //根据id获取用户信息
        PassengerUser passengerUser = taxiMapper.selectPassenger(passengerVo.getOpenId());

        //判断用户年龄小于16岁，提示用户不能单独乘车
        if (passengerUser.getPassengerAge() < 16) {
            AjaxResult.error("未成年不可独自乘车");
        }

        //查询出用户最新的上一个订单
        OrderInfo orderStatus = taxiMapper.selectOrder(passengerUser.getOpenId());

        //如果有进行判断是否未支付
        if (Optional.ofNullable(orderStatus).isPresent()) {
            return AjaxResult.error("您上一单金额未支付,请先支付后在下单");
        }


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

            passengerVo.setAboutPrice(aboutPrice);
            //判断是否预约用车
            if (passengerVo.getIsReserve() == 1 ) {
                if (aboutPrice < 30) {
                    aboutPrice = 30.0;
                }
                passengerVo.setAdvanceAmount(aboutPrice);
            }
            //添加大概金额
            passengerVo.setAboutPrice(aboutPrice);

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
            passengerVo.setAboutPrice(aboutPrice);
        }



        // 日租价格计算逻辑
        if (passengerVo.getVehicleType() == 3) {
            //初始化价格
            double basePrice = 0.0;
            //初始化超出时间价格
            double durationRate = 0.0;
            //初始化超出距离价格
            double distanceRate = 0.0;

            switch (passengerVo.getCarType()) {
                case 1: // 新能源
                    basePrice = 550.0;
                    durationRate = 0.3;
                    distanceRate = 2.3;
                    break;
                case 2: // 舒适型
                    basePrice = 650.0;
                    durationRate = 0.6;
                    distanceRate = 4.0;
                    break;
                case 3: // 豪华型
                    basePrice = 750.0;
                    durationRate = 0.7;
                    distanceRate = 5.0;
                    break;
                case 4: // 商务型
                    basePrice = 950.0;
                    durationRate = 1.0; // 注意：这里假设每超出一分钟加收1.0元，具体数值根据实际情况调整
                    distanceRate = 5.5;
                    break;
                default:
                    // 处理未知车辆类型
                    break;
            }

           if (passengerVo.getEstimatedDuration() > 560) { //时间超出8小时
                aboutPrice = basePrice + ((passengerVo.getEstimatedDuration() - 560) * durationRate);
            }
           if (passengerVo.getExpectDistance() > 100) {//距离超出100公里
                aboutPrice = aboutPrice + ((passengerVo.getExpectDistance() - 100) * distanceRate);
            }
            passengerVo.setAboutPrice(aboutPrice);
        }


        passengerVo.setOpenId(1584355669008773122L);
        passengerVo.setPassengerPhone(18178101668L);
        taxiMapper.saveOrder(passengerVo);

        //删除缓存

        stringRedisTemplate.delete(key);

        //使用websocket推送消息
        webSocketController.sendMessage(JSON.toJSONString(passengerVo));

        return AjaxResult.success("添加成功");
    }
}