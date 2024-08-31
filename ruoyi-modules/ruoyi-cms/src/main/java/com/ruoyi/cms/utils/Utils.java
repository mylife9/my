package com.ruoyi.cms.utils;

import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

public class Utils {

    private static final double EARTH_RADIUS = 6378137;

    // 计算总费用的方法，接受距离、时长和开始时间作为参数
    public static double calculateTotalFee(double distance, int duration, String startTime) {
        // 调用方法获取起步价
        double startFee = getStartFee(startTime);
        // 调用方法获取里程费
        double distanceFee = getDistanceFee(distance, startTime);
        // 调用方法获取时长费
        double durationFee = getDurationFee(duration, startTime);
        // 调用方法获取远程费（此例中未给出具体实现，假设为0）
        double longDistanceFee = getLongDistanceFee(distance);

        // 返回所有费用的总和
        return startFee + distanceFee + durationFee + longDistanceFee;
    }

    // 根据开始时间获取起步价的方法
    private static double getStartFee(String startTime) {
        // 判断开始时间，并返回对应的起步价
        if (isInTimePeriod(startTime, "00:00", "10:00")) {//00:00-10:00 时间段起步价14.02
            return 14.02;
        } else if (isInTimePeriod(startTime, "16:30", "19:00")) {//16:30-19:00 时间段起步价14
            return 14.00;
        } else if (isInTimePeriod(startTime, "19:00", "21:00")) {//16:30-19:00 时间段起步价13.5
            return 13.50;
        } else if (isInTimePeriod(startTime, "21:00", "24:00") || isInTimePeriod(startTime, "00:00", "00:00")) {//21:00-24:00 时间段起步价14
            return 14.00;
        } else {//其他时间段12.80
            return 12.80;
        }
    }

    // 根据距离和开始时间获取里程费的方法
    private static double getDistanceFee(double distance, String startTime) {
        // 定义起步里程为3.3公里
        double baseDistance = 3.3;
        // 计算超出起步里程的距离
        double extraDistance = distance - baseDistance;
        double feePerKm;

        // 如果未超出起步里程，则返回0
        if (extraDistance <= 0) {
            return 0;
        }

        // 根据开始时间判断每公里的费率
        if (isInTimePeriod(startTime, "00:00", "06:00")) {//00:00-06:00一公里1.69
            feePerKm = 1.69;
        } else if (isInTimePeriod(startTime, "06:00", "10:00")) {//06:00-10:00一公里1.46
            feePerKm = 1.46;
        } else if (isInTimePeriod(startTime, "17:00", "19:00")) {//17:00-19:00一公里1.26
            feePerKm = 1.26;
        } else if (isInTimePeriod(startTime, "21:00", "22:30")) {//21:00-22:30一公里1.76
            feePerKm = 1.76;
        } else if (isInTimePeriod(startTime, "22:30", "24:00") || isInTimePeriod(startTime, "00:00", "06:00")) {//22:30-24:00一公里1.69
            feePerKm = 1.69;
        } else {//其他时间段一公里1.14
            feePerKm = 1.14;
        }

        // 返回超出起步里程的费用
        return extraDistance * feePerKm;
    }

    // 根据时长和开始时间获取时长费的方法（具体实现省略，以保持简洁）
    private static double getDurationFee(int duration, String startTime) {
        int baseDuration = 10; // 起步时长
        // 计算超出起步时长的分钟数
        int extraDuration = duration - baseDuration;
        double feePerMinute;

        // 如果超出起步时长的分钟数小于等于0，则不产生额外费用，返回0
        if (extraDuration <= 0) {
            return 0;
        }

        // 判断开始时间是否在高峰时段（00:00-10:00 或 17:00-24:00），如果是，则每分钟的费用为0.52元，否则为0.26元
        if (isInTimePeriod(startTime, "00:00", "10:00") || isInTimePeriod(startTime, "17:00", "24:00")) {
            feePerMinute = 0.52;
        } else {
            feePerMinute = 0.26;
        }

        // 返回超出起步时长的额外费用
        return extraDuration * feePerMinute;
    }

    // 根据行驶的距离获取远途费
    private static double getLongDistanceFee(double distance) {
        // 如果距离大于40公里，则前20公里免费，20-40公里之间按0.22元/公里计费，超过40公里的部分按0.90元/公里计费
        if (distance > 40) {
            return 20 * 0.22 + (distance - 40) * 0.90;
        } else if (distance > 20) { // 如果距离在20到40公里之间，则前20公里免费，超出部分按0.22元/公里计费
            return (distance - 20) * 0.22;
        } else { // 如果距离小于等于20公里，则不产生远途费，返回0
            return 0;
        }
    }

    private static boolean isInTimePeriod(String currentTime, String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime currentLocalTime = LocalTime.parse(currentTime, formatter);
        LocalTime startLocalTime = LocalTime.parse(startTime, formatter);
        LocalTime endLocalTime = LocalTime.parse(endTime, formatter);

        // 转换为自当天0点开始的总分钟数
        int currentMinutes = currentLocalTime.getHour() * 60 + currentLocalTime.getMinute();
        int startMinutes = startLocalTime.getHour() * 60 + startLocalTime.getMinute();
        int endMinutes = endLocalTime.getHour() * 60 + endLocalTime.getMinute();

        // 如果结束时间小于开始时间，说明是跨天的情况
        if (endMinutes < startMinutes) {
            // 如果当前时间在开始时间之后或者在当前时间在跨天的结束时间之前，则认为是在时间段内
            return !(currentMinutes < startMinutes) || currentMinutes < endMinutes + 24 * 60; // 加上一天的分钟数来处理跨天
        } else {
            // 没有跨天的情况，按照原来的逻辑判断
            return !(currentMinutes < startMinutes) && currentMinutes < endMinutes;
        }
    }


    // 经纬度计算距离
    public static double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
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

    public static int calculateTime(Date startTime, Date endTime) {

        // 将 java.util.Date 转换为 java.time.Instant
        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();

        // 计算时间差
        Duration duration = Duration.between(startInstant, endInstant);

        // 获取时间差中的分钟数
        long minutes = duration.toMinutes();
        minutes = minutes;
        // 输出结果（如果需要）
        System.out.println("时间差为：" + minutes + "分钟");

        // 由于方法签名要求返回 int 类型，确保分钟数不会超过 Integer.MAX_VALUE
        if (minutes > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("时间差超过 Integer.MAX_VALUE 分钟");
        }

        // 返回分钟数（强制转换为 int，因为方法签名要求返回 int 类型）
        return (int) minutes;
    }

    /**
     * @author lafer
     * 高德地图工具类
     */

    private static final String GAO_DE_KEY = "1ecb10ee8837f9a355719170286d5e3e";

    /**
     * 将GPS坐标转换为高德地图坐标
     * @param longitude 经度
     * @param latitude 纬度
     */
    public static Map<String, Object> getLocation(String longitude, String latitude) {
        String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?output=JSON&locations="
                +longitude+","+latitude+"&key="+GAO_DE_KEY+"&coordsys=gps";
        return requestApi(url);
    }

    /**
     * 根据经纬度获取地址信息
     * @param longitude 经度 例：119.183582
     * @param latitude 纬度 例：26.126298
     */
    public static Map<String, Object> getAddressInfo(String longitude, String latitude) {
        String url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + longitude + "," + latitude
                + "&key="+GAO_DE_KEY+"&radius=0&extensions=base";
        return requestApi(url);
    }

    /**
     * 调用 api
     * @param url 请求地址
     */
    private static Map<String, Object> requestApi(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Map.class);
    }
}
