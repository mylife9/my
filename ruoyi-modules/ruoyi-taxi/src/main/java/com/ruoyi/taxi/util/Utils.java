
package com.ruoyi.taxi.util;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.taxi.domain.vo.PassengerVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Utils {
    //地球半径
    private static final double EARTH_RADIUS = 6378137;


    public static final Date currentDateTime = new Date();

    /**
     * 新能源型费用计算
     **/
    public static Double calculatePriceForNewEnergy(PassengerVo passengerVo) {
        Double expectDistance = passengerVo.getExpectDistance();
        Double estimatedDuration = passengerVo.getEstimatedDuration();

        if (expectDistance <= 3) {
            return 10.0;
        } else if (expectDistance <= 15) {
            return 10 + (estimatedDuration * 0.3) + ((expectDistance - 3) * 2.3);
        } else {
            return 10 + (estimatedDuration * 0.3) + ((expectDistance - 3) * 2.3) + (expectDistance * 1.2);
        }
    }

    /**
     * 舒适型费用计算
     **/
    public static Double calculatePriceForComfort(PassengerVo passengerVo) {
        // ... 实现舒适型的计费逻辑
        Double expectDistance = passengerVo.getExpectDistance();
        Double estimatedDuration = passengerVo.getEstimatedDuration();

        if (expectDistance <= 3) {
            return 14.0;
        } else if (expectDistance <= 15) {
            return 14 + (estimatedDuration * 0.5) + ((expectDistance - 3) * 2.8);
        } else {
            return 10 + (estimatedDuration * 0.5) + ((expectDistance - 3) * 2.8) + (expectDistance * 1.4);
        }
    }

    /**
     * 豪华型费用计算
     **/
    public static Double calculatePriceForLuxury(PassengerVo passengerVo) {
        // ... 实现豪华型的计费逻辑
        Double expectDistance = passengerVo.getExpectDistance();
        Double estimatedDuration = passengerVo.getEstimatedDuration();

        if (expectDistance <= 3) {
            return 20.0;
        } else if (expectDistance <= 15) {
            return 20 + (estimatedDuration * 0.8) + ((expectDistance - 3) * 3.5);
        } else {
            return 20 + (estimatedDuration * 0.8) + ((expectDistance - 3) * 3.5) + (expectDistance * 1.75);
        }
    }

    /**
     * 商务型费用计算
     **/
    public static Double calculatePriceForBusiness(PassengerVo passengerVo) {
        // ... 实现商务型的计费逻辑
        Double expectDistance = passengerVo.getExpectDistance();
        Double estimatedDuration = passengerVo.getEstimatedDuration();

        if (expectDistance <= 3) {
            return 23.0;
        } else if (expectDistance <= 15) {
            return 23 + (estimatedDuration * 0.8) + ((expectDistance - 3) * 4.0);
        } else {
            return 23 + (estimatedDuration * 0.8) + ((expectDistance - 3) * 4.0) + (expectDistance * 2.0);
        }
    }


    public static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        double newLongitude = lat2 - lat1;


        double newLatitude = lon2 - lon1;


        double newDistance = Math.asin(Math.sqrt(Math.pow(Math.sin(newLongitude / 2), 2) + Math.cos(lat2) * Math.cos(lat1) * Math.pow(Math.sin(newLatitude / 2), 2)));


        newDistance = newDistance * EARTH_RADIUS / 1000;

        //保留小数点后两位
        String s = String.format("%.2f", newDistance);
        newDistance = Double.parseDouble(s);

        return newDistance;
    }

    public static String getAMapByLngAndLat(Double getLng, Double getLat) throws Exception {
        String url;
        try {
            url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + getLng + ","
                    + getLat + "&key=" + "1ecb10ee8837f9a355719170286d5e3e" + "&radius=0&extensions=base";
            String queryResult = getResponse(url); // 高德接品返回的是JSON格式的字符串
            // 将获取结果转为json数据
            JSONObject obj = JSONObject.parseObject(queryResult);
            System.out.println("obj为：" + obj);
            if (obj.get("status").toString().equals("1")) {
                // 如果没有返回-1
                JSONObject regeocode = obj.getJSONObject("regeocode");
                if (regeocode.size() > 0) {
                    // 在regeocode中拿到 formatted_address 具体位置
                    return regeocode.get("formatted_address").toString();
                } else {
                    throw new RuntimeException("未找到相匹配的地址！");
                }
            } else {
                throw new RuntimeException("请求错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }


    private static String getResponse(String serverUrl) {
        // 用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}