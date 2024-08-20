package com.ruoyi.taxi.service.impl;

import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.taxi.domain.DriverUserWorkStatus;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.TaxiService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TaxiServiceImpl implements TaxiService {
    @Autowired
    TaxiMapper taxiMapper;

    //经度正则表达式
    private static final String LONGITUDE = "^[-+]?((180(\\.\\d{1,6})?)|((1[0-7]\\d)|([1-9]\\d?))(\\.\\d{1,6})?)$";


    //纬度正则表达式
    private static final String LATITUDE = "^[-+]?((90(\\.\\d{1,6})?)|([1-8]?\\d(\\.\\d{1,6})?))$";


    //地球半径
    private static final double EARTH_RADIUS = 6378137;

    //当前时间
    public static final LocalTime now = LocalTime.now();
    private static final LocalTime start = LocalTime.of(23, 0);
    private static final LocalTime end = LocalTime.of(5, 0);

    @Override
    @Transactional
    public AjaxResult saveOrder(String token, PassengerVo passengerVo) {

        //判断用户是否输入起始位置
        if (passengerVo.getDepLongitude() == 0 && passengerVo.getDepLatitude() == 0) {
            return AjaxResult.error("请先输入您的起始位置");
        }

        //判断起始位置的经纬度是否合规
        boolean depLongitudePattern = Pattern.matches(LONGITUDE, passengerVo.getDepLongitude().toString());
        boolean depLatitudePattern = Pattern.matches(LATITUDE, passengerVo.getDestLatitude().toString());
        if (!depLongitudePattern || !depLatitudePattern) {
            System.out.println("您输入的起始位置不符合规范");
            return AjaxResult.error("您输入的起始位置不符合规范");
        }

        //判断用户是否输入目的地
        if (passengerVo.getDestLongitude() == 0 && passengerVo.getDestLatitude() == 0) {
            return AjaxResult.error("请您输入您的目的地");
        }

        //判断起始位置的经纬度是否合规
        boolean destLongitudePattern = Pattern.matches(LONGITUDE, passengerVo.getDepLongitude().toString());
        boolean destLatitudePattern = Pattern.matches(LATITUDE, passengerVo.getDepLatitude().toString());
        if (!destLongitudePattern || !destLatitudePattern) {
            System.out.println("您输入的目的地不符合规范");
            return AjaxResult.error("您输入的目的地不符合规范");
        }

//        解析token获取用户id
        Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws(token).getBody();
        String passengerId = claims.get("passengerId", String.class);

        //根据id获取用户信息
        PassengerUser user = taxiMapper.selectPassenger(passengerId);

        //判断用户年龄小于16岁，提示用户不能单独乘车
        if(user.getPassengerAge()<16){
            AjaxResult.error("未成年不可独自乘车");
        }

        //查询出用户最新的上一个订单
        OrderInfo orderInfo = taxiMapper.selectOrder(user.getPassengerId());

        //如果有进行判断是否未支付
        if(orderInfo!=null ){
                return AjaxResult.error("您上一单金额未支付,请先支付后在下单");
        }

        //经度
        double depLong = Math.toRadians(passengerVo.getDepLongitude());
        double destLong = Math.toRadians(passengerVo.getDestLongitude());

        //经度之差
        double a = destLong - depLong;
        //纬度
        double depLat = Math.toRadians(passengerVo.getDepLatitude());
        double destLat = Math.toRadians(passengerVo.getDestLatitude());

        //纬度之差
        double b = destLat - depLat;

        //计算两点距离的公式
        double expectDistance = Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(depLong) * Math.cos(destLong) * Math.pow(Math.sin(b / 2), 2)));


        expectDistance = expectDistance * EARTH_RADIUS / 1000;

        //保留小数点后两位
        String format = String.format("%.2f", expectDistance);
        expectDistance = Double.parseDouble(format);

        //初始化预付金额
        double aboutPrice = 0;

        //普通叫车服务
        if (passengerVo.getVehicleType() == 1) {

            //夜间时间
            boolean isNight = now.isAfter(start) || now.isBefore(end);

            if (isNight) {
                aboutPrice +=  passengerVo.getExpectDistance() ;
            }
            //新能源
            if (passengerVo.getCarType() == 1) {
                //小于3公里收费计算
                if (expectDistance <= 3) {
                    aboutPrice = 10;

                    passengerVo.setAboutPrice(aboutPrice);
                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        passengerVo.setAdvanceAmount(30.0);
                    }
                    //大于3公里小于15公里收费计算
                } else if (expectDistance > 3 && expectDistance <= 15) {
                    aboutPrice = 10 + (passengerVo.getEstimatedDuration() * 0.3) + ((passengerVo.getExpectDistance() - 3) * 2.3);

                    passengerVo.setAboutPrice(aboutPrice);
                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        passengerVo.setAdvanceAmount(Math.max(aboutPrice, 30.0));
                    }
                    //大于15公里收费计算
                } else {
                    aboutPrice = 10 + (passengerVo.getEstimatedDuration() * 0.3) + ((passengerVo.getExpectDistance() - 3) * 2.3) + (passengerVo.getExpectDistance() * 1.2);
                    //判断如果是夜间，额外计费
                    passengerVo.setAboutPrice(aboutPrice);
                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        passengerVo.setAdvanceAmount(Math.max(aboutPrice, 30.0));
                    }
                }
            }

            //舒适型
            if (passengerVo.getCarType() == 2) {
                //小于三公里费用计算
                if (expectDistance <= 3) {
                    aboutPrice = 14;

                    //小于3公里费用
                    passengerVo.setAboutPrice(aboutPrice);

                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        if (aboutPrice < 30) {
                            aboutPrice = 30.0;
                        }
                        passengerVo.setAdvanceAmount(aboutPrice);
                    }
                }
                //大于3公里小于15公里费用计算
                else if (expectDistance > 3 && expectDistance <= 15) {
                    aboutPrice = 10 + (passengerVo.getEstimatedDuration() * 0.5) + ((passengerVo.getExpectDistance() - 3) * 2.8);
                }
                //舒适型大于3公里小于15公里
                passengerVo.setAboutPrice(aboutPrice);

                //判断是否预约用车
                if (passengerVo.getIsReserve() == 1) {
                    if (aboutPrice < 30) {
                        aboutPrice = 30.0;
                    }
                    passengerVo.setAdvanceAmount(aboutPrice);
                }
            } else if (expectDistance > 15) {
                //时长费+里程费+远途费
                aboutPrice = 10 + (passengerVo.getEstimatedDuration() * 0.3) + ((passengerVo.getExpectDistance() - 3) * 2.3) + (passengerVo.getExpectDistance() * 1.4);

                passengerVo.setAboutPrice(aboutPrice);

                //判断是否预约用车
                if (passengerVo.getIsReserve() == 1) {
                    if (aboutPrice < 30) {
                        aboutPrice = 30.0;
                    }
                    passengerVo.setAdvanceAmount(aboutPrice);
                }
            }
            //豪华型
            if (passengerVo.getCarType() == 3) {
                //判断三公里以内
                if (expectDistance <= 3) {
                    aboutPrice = 20;

                    //小于3公里
                    passengerVo.setAboutPrice(aboutPrice);

                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        if (aboutPrice < 30) {
                            aboutPrice = 30.0;
                        }
                        passengerVo.setAdvanceAmount(aboutPrice);
                    }
                }
                //判断是否是15公里以内
                else if (expectDistance > 3 && expectDistance <= 15) {
                    aboutPrice = 20 + (passengerVo.getEstimatedDuration() * 0.8) + ((passengerVo.getExpectDistance() - 3) * 3.5);

                    //豪华型小于15公里按起步费收费
                    passengerVo.setAboutPrice(aboutPrice);

                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        if (aboutPrice < 30) {
                            aboutPrice = 30.0;
                        }
                        passengerVo.setAdvanceAmount(aboutPrice);
                    }
                }
            } else if (expectDistance > 15) {
                //时长费+里程费+远途费
                aboutPrice = 20 + (passengerVo.getEstimatedDuration() * 0.8) + ((passengerVo.getExpectDistance() - 3) * 3.5) + (passengerVo.getExpectDistance() * 1.75);
                //大于15公里
                passengerVo.setAboutPrice(aboutPrice);

                //判断是否预约用车
                if (passengerVo.getIsReserve() == 1) {
                    if (aboutPrice < 30) {
                        aboutPrice = 30.0;
                    }
                    passengerVo.setAdvanceAmount(aboutPrice);
                }
            }

            //商务型
            if (passengerVo.getCarType() == 4) {
                if (expectDistance <= 3) {
                    aboutPrice = 23;

                    //小于三公里
                    passengerVo.setAboutPrice(aboutPrice);

                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        if (aboutPrice < 30) {
                            aboutPrice = 30.0;
                        }
                        passengerVo.setAdvanceAmount(aboutPrice);
                    }
                }
                //判断是否是15公里以内
                else if (expectDistance > 3 && expectDistance <= 15) {
                    aboutPrice = 23 + (passengerVo.getEstimatedDuration() * 0.8) + ((passengerVo.getExpectDistance() - 3) * 4.0);

                    //商务型大于3公里小于15公里计费
                    passengerVo.setAboutPrice(aboutPrice);

                    //判断是否预约用车
                    if (passengerVo.getIsReserve() == 1) {
                        if (aboutPrice < 30) {
                            aboutPrice = 30.0;
                        }
                        passengerVo.setAdvanceAmount(aboutPrice);
                    }
                }
            } else {
                //时长费+里程费+远途费
                aboutPrice = 23 + (passengerVo.getEstimatedDuration() * 0.8) + ((passengerVo.getExpectDistance() - 3) * 4.0) + (passengerVo.getExpectDistance() * 2.0);

                //判断如果是夜间，额外计费

                //大于15公里
                passengerVo.setAboutPrice(aboutPrice);

                //判断是否预约用车
                if (passengerVo.getIsReserve() == 1) {
                    if (aboutPrice < 30) {
                        aboutPrice = 30.0;
                    }
                    passengerVo.setAdvanceAmount(aboutPrice);
                }
            }


            //进行司机家庭地址和目的地的计算
            List<DriverUserWorkStatus> driverWorkList=taxiMapper.selectDriverWork();

            ArrayList<Integer> integers = new ArrayList<>();

            for (DriverUserWorkStatus driverWork : driverWorkList) {

                //司机家经度
                Double addrLongitude = driverWork.getAddrLongitude();
                //目的地经度
                Double destLongitude = passengerVo.getDestLongitude();

                double newLongitude = addrLongitude-destLongitude;

                //司机家纬度
                Double addrLatitude = driverWork.getAddrLatitude();
                //目的地纬度
                Double destLatitude = passengerVo.getDestLatitude();

                double newLatitude = addrLatitude - destLatitude;


                double newDistance = Math.asin(Math.sqrt(Math.pow(Math.sin(newLongitude / 2), 2) +
                        Math.cos(addrLongitude) * Math.cos(destLongitude) * Math.pow(Math.sin(newLatitude / 2), 2)));


                newDistance = newDistance * EARTH_RADIUS / 1000;

                //保留小数点后两位
                String s = String.format("%.2f", newDistance);
                newDistance = Double.parseDouble(s);


                //查询所有司机1.5个小时内有没有服务
                Date endTime = new Date();

                // 增加1.5小时（1.5小时 = 1.5 * 60 * 60 * 1000 毫秒）
                long millisToAdd = (long) (1.5 * 60 * 60 * 1000);
                endTime = new Date(endTime.getTime() + millisToAdd);

                Date currentTime = new Date();
               Boolean hasOrders = taxiMapper.selectOrderInfo(driverWork.getDriverId(),currentTime,endTime);
               //有订单
                if(hasOrders){
                    //跳出这个司机
                    continue;
                }
                    //判断5公里以内的
                    if(newDistance<5){
                        //判断是否开启回家模式
                        if(driverWork.getIsHeadingHome()==1){
                            //判断司机在接单状态
                            if(driverWork.getWorkStatus()==3){
                                integers.add(driverWork.getDriverId());
                            }
                        }
                        //如果没有随机派给3公里以内司机
                    }else {
                        if(driverWork.getWorkStatus()==3){
                            //司机所外位置经度
                            Double currentLongitude = driverWork.getCurrentLongitude();
                            //乘客上车位置
                            Double depLongitude = passengerVo.getDepLongitude();

                            double distanceLong = depLongitude - currentLongitude;


                            //司机所在位置纬度
                            Double currentLatitude = driverWork.getCurrentLatitude();
                            //乘客下车位置
                            Double depLatitude = passengerVo.getDepLatitude();

                            double distanceLat = currentLatitude - depLatitude;

                            //获取下车位置和司机所在位置路径
                            double distance = Math.asin(Math.sqrt(Math.pow(Math.sin(distanceLong / 2), 2) +
                                    Math.cos(currentLongitude) * Math.cos(depLongitude) * Math.pow(Math.sin(distanceLat / 2), 2)));

                            //获取小于三公里司机
                            if(distance<3){
                                integers.add(driverWork.getDriverId());
                            }
                        }
                    }
            }
            if(integers.size()>0){
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

            // 新能源
            if (passengerVo.getCarType() == 1) {
                basePrice = 550.0;
                durationRate = 0.3;
                distanceRate = 2.3;
            }
            // 舒适型
            else if (passengerVo.getCarType() == 2) {
                basePrice = 650.0;
                durationRate = 0.6;
                distanceRate = 4.0;
            }
            // 豪华型
            else if (passengerVo.getCarType() == 3) {
                basePrice = 750.0;
                durationRate = 0.7;
                distanceRate = 5.0;
            }
            // 商务型
            else if (passengerVo.getCarType() == 4) {
                basePrice = 950.0;
                durationRate = 1.0; // 注意：这里假设每超出一分钟加收1.0元，具体数值根据实际情况调整
                distanceRate = 5.5;
            }

            //没有超出
            if(passengerVo.getEstimatedDuration() <= 560 && passengerVo.getExpectDistance() <= 100){
                aboutPrice = basePrice;
            }else if(passengerVo.getEstimatedDuration() > 560){ //时间超出8小时
                aboutPrice = basePrice + ((passengerVo.getEstimatedDuration() - 560) * durationRate);
            }else if(passengerVo.getExpectDistance() > 100){//距离超出100公里

                aboutPrice = aboutPrice + ((passengerVo.getExpectDistance() - 100) * distanceRate);
            }
            passengerVo.setAboutPrice(aboutPrice);
        }

        taxiMapper.saveOrder(passengerVo,user);
        return AjaxResult.success("添加成功");
    }
}