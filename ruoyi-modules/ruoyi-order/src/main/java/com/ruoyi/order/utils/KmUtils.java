package com.ruoyi.order.utils;

/**
 * @author : Li
 * @date : 2024-08-26 17:05
 * @description :
 **/
public class KmUtils {

    private static final double EARTH_RADIUS = 6378137;

    public static Double calculateDistance(Double lat1, Double lat2, Double lon1, Double lon2) {

        double a = (lat1 - lat2) * (Math.PI / 180);
        double b = (lon1 - lon2) * (Math.PI / 180);

        // 根据经纬度差计算两点间的距离
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * Math.pow(Math.sin(b / 2), 2)));

        // 将弧长乘以地球半径，得到距离，单位为米
        s = s * EARTH_RADIUS;

        // 返回距离，单位为公里
        return s / 1000;


    }

}
