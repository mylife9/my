
package com.ruoyi.taxi.util;

import com.ruoyi.taxi.domain.vo.PassengerVo;

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
}