package com.ruoyi.cms.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/8/23 22:22
 * @注释
 */
public class JWUtil {

    public static Map calculateTotalFee() {
        //模拟经纬度信息
        Map map = new HashMap<String, String>();
        map.put("1","116.629779,39.847469");
        map.put("2","118.248742,40.818938");
        map.put("3","114.863639,41.278562");
        map.put("4","117.310479,41.403353");
        map.put("5","116.767759,40.306801");
        map.put("6","117.163301,40.811949");
        map.put("7","118.018775,40.130504");
        map.put("8","117.338075,37.592109");
        map.put("9","115.268379,37.306079");
        map.put("10","117.144904,37.217848");
        Random random = new Random();
        int randomNumber  = random.nextInt(10);
        String aa= String.valueOf(randomNumber);
        String o = (String) map.get(aa);
        // 使用split方法按逗号分隔字符串
        String[] numbers = o.split(",");
        // numbers现在是一个字符串数组，包含两个元素
        String lat = String.valueOf(Double.parseDouble(numbers[0]));  // 第一个元素
        String lon = String.valueOf(Double.parseDouble(numbers[1]));  // 第二个元素
        System.out.println(lat + " " + lon);
        Map JWmap = new HashMap<String, Object>();
        JWmap.put("J",lat);
        JWmap.put("W",lon);
        return JWmap;
    }
}
