package com.ruoyi.order.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * @program: RuoYi-Cloud
 * @author: gtx
 * @description:
 * @create: 2024-08-21 15:01
 */
@Slf4j
@Service
public class KilometreUtils {

    private static final double EARTH_RADIUS = 6378137;

    //地球平均半径，单位米
    private static final int R = 6371000;


    public String kilometreCom(String depLongitude , String depLatitude , String destLongitude , String destLatitude){

        // 将起始经度转换为弧度
        double lat1 = Math.toRadians(Double.parseDouble(depLongitude));
        // 将目标经度转换为弧度(当前位置)
        double lat2 = Math.toRadians(Double.parseDouble(destLongitude));
        // 将起始纬度转换为弧度
        double lng1 = Math.toRadians(Double.parseDouble(depLatitude));
        // 将目标纬度转换为弧度（当前位置）
        double lng2 = Math.toRadians(Double.parseDouble(destLatitude));

        // 计算起始点和目标点的经度差和纬度差
        double a = lat1 - lat2;
        double b = lng1 - lng2;

        // 根据经纬度差计算两点间的距离
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));

        // 将弧长乘以地球半径，得到距离，单位为米
        s = s * EARTH_RADIUS;

        // 格式化距离输出为两位小数
        DecimalFormat df = new DecimalFormat("0.00");

        // 输出距离，单位为公里
        log.info(df.format(s / 1000) + "公里");

        return df.format(s / 1000);

    }


    public Boolean isWithinRange(String depLongitude , String depLatitude , String destLongitude , String destLatitude){

        double phi1 = Math.toRadians(Double.parseDouble(depLongitude));
        double phi2 = Math.toRadians(Double.parseDouble(destLongitude));
        double deltaPhi = Math.toRadians(Double.parseDouble(destLongitude) - Double.parseDouble(depLongitude));
        double deltaLambda = Math.toRadians(Double.parseDouble(destLatitude) - Double.parseDouble(depLatitude));

        double a = Math.sin(deltaPhi / 2.0) * Math.sin(deltaPhi / 2.0) +
                Math.cos(phi1) * Math.cos(phi2) *
                           Math.sin(deltaLambda / 2.0) * Math.sin(deltaLambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;

        //检查距离是否小于给定范围
        return distance <= 500;

    }

}
