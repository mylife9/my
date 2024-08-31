package com.ruoyi.order.pojo;

/**
 * @program: RuoYi-Cloud
 * @author: gtx
 * @description:
 * @create: 2024-08-21 22:18
 */

public class PriceRule {
    private  String     cityCode;
    private  double     startFare;
    private  Integer start_mile;
    private  double unit_price_per_mile;
    private  double unit_price_per_minute;

    @Override
    public String toString() {
        return "PriceRule{" +
                "cityCode='" + cityCode + '\'' +
                ", startFare=" + startFare +
                ", startMile=" + start_mile +
                ", unitPricePerMile=" + unit_price_per_mile +
                ", unitPricePerMinute=" + unit_price_per_minute +
                ", fareVersion=" + fareVersion +
                '}';
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public double getStartFare() {
        return startFare;
    }

    public void setStartFare(double startFare) {
        this.startFare = startFare;
    }

    public Integer getStartMile() {
        return start_mile;
    }

    public void setStartMile(Integer startMile) {
        this.start_mile = startMile;
    }

    public double getUnitPricePerMile() {
        return unit_price_per_mile;
    }

    public void setUnitPricePerMile(double unitPricePerMile) {
        this.unit_price_per_mile = unitPricePerMile;
    }

    public double getUnitPricePerMinute() {
        return unit_price_per_minute;
    }

    public void setUnitPricePerMinute(double unitPricePerMinute) {
        this.unit_price_per_minute = unitPricePerMinute;
    }

    public Integer getFareVersion() {
        return fareVersion;
    }

    public void setFareVersion(Integer fareVersion) {
        this.fareVersion = fareVersion;
    }

    private  Integer fareVersion;
}
