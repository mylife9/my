package com.ruoyi.order.pojo.vo;

/**
 * @program: ruoyi-car
 * @author: gtx
 * @description:
 * @create: 2024-08-27 14:30
 */
public class PointsVo {

    private Long driver_id;
    private Double driver_scope;
    private Double score_value;

    private String  score_name;

    public Long getDriverId() {
        return driver_id;
    }

    public void setDriverId(Long driverId) {
        this.driver_id = driverId;
    }

    public String getScoreName() {
        return score_name;
    }

    public void setScoreName(String scoreName) {
        this.score_name = scoreName;
    }

    public Double getDriverScope() {
        return driver_scope;
    }

    public void setDriverScope(Double driverScope) {
        this.driver_scope = driverScope;
    }

    public Double getScoreValue() {
        return score_value;
    }

    public void setScoreValue(Double scoreValue) {
        this.score_value = scoreValue;
    }

    @Override
    public String toString() {
        return "PointsVo{" +
                "driver_id=" + driver_id +
                ", driver_scope=" + driver_scope +
                ", score_value=" + score_value +
                ", score_name='" + score_name + '\'' +
                '}';
    }
}
