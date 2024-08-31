
package com.ruoyi.taxi.util;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.taxi.domain.vo.PassengerVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
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


    public static Double calculateDistance(Double lat1, Double lat2, Double lon1, Double lon2) {
//        double newLongitude = lat2 - lat1;
//
//
//        double newLatitude = lon2 - lon1;
//
//
////        double newDistance = Math.asin(Math.sqrt(Math.pow(Math.sin(newLongitude / 2), 2) + Math.cos(lat2) * Math.cos(lat1) * Math.pow(Math.sin(newLatitude / 2), 2)));
//        double newDistance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(newLongitude / 2), 2) +
//                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(newLatitude / 2), 2)));
//
//
//        newDistance = newDistance * EARTH_RADIUS / 1000;
//
//        //保留小数点后两位
//        String s = String.format("%.2f", newDistance);
//        newDistance = Double.parseDouble(s);


        // 计算起始点和目标点的经度差和纬度差

//        double a = lat1 - lon1;
//        double b = lat2 - lon2;
//
//        // 根据经纬度差计算两点间的距离
//        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
//                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
//
//        // 将弧长乘以地球半径，得到距离，单位为米
//        s = s * EARTH_RADIUS;
//
//        // 格式化距离输出为两位小数
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        // 输出距离，单位为公里
//         Double aa = Double.valueOf(df.format(s / 1000));
//
//        return aa;

        //a 和 b 都是从度数转换到弧度的过程。a 代表纬度之差，b 代表经度之差。
        //lat1 和 lat2 是两个点的纬度坐标，lon1 和 lon2 是对应的经度坐标。
        //(Math.PI / 180) 是将度数转换为弧度的常数因子
        double a = (lat1 - lat2) * (Math.PI / 180); //计算纬度之差，并将其转换为弧度
        double b = (lon1 - lon2) * (Math.PI / 180); //计算经度之差，并将其转换为弧度

        // 根据经纬度差计算两点间的距离
        //这里使用了一个公式（哈弗赛恩）来计算地球上两点之间的大圆距离。该公式基于球面三角学原理，a 是两半弦长度的平方和。
        //s 是中心角的距离（以弧度表示）
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * Math.pow(Math.sin(b / 2), 2)));

        // 将弧长乘以地球半径，得到距离，单位为米
        //其中 EARTH_RADIUS 是地球的平均半径（或者说是球体的半径）。对于地球来说，平均半径大约为6,371千米
        s = s * EARTH_RADIUS;

        // 返回距离，单位为公里
        return s / 1000;


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