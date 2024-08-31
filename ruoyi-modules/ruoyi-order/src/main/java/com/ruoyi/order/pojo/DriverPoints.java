package com.ruoyi.order.pojo;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 11:42
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author : 成长
 * @date : 2024-08-22 11:42
 * @description :
 **/
public class DriverPoints {
    private Long id;
    private Long driver_id;
    private Double driver_scope;
    private Double service_scope;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD HH:mm:ss")
    private Date create_time;

   private String  score_name;
   private Double score_value;

   private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driver_id;
    }

    public void setDriverId(Long driverId) {
        this.driver_id = driverId;
    }

    public Double getDriverScope() {
        return driver_scope;
    }

    public void setDriverScope(Double driverScope) {
        this.driver_scope = driverScope;
    }


    public Double getServiceScope() {
        return service_scope;
    }

    public void setServiceScope(Double serviceScope) {
        this.service_scope = serviceScope;
    }

    public Date getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Date createTime) {
        this.create_time = createTime;
    }

    public String getScoreName() {
        return score_name;
    }

    public void setScoreName(String scoreName) {
        this.score_name = scoreName;
    }

    public Double getScoreValue() {
        return score_value;
    }

    public void setScoreValue(Double scoreValue) {
        this.score_value = scoreValue;
    }

    @Override
    public String toString() {
        return "DriverPoints{" +
                "id=" + id +
                ", driver_id=" + driver_id +
                ", driver_scope=" + driver_scope +
                ", service_scope=" + service_scope +
                ", create_time=" + create_time +
                ", score_name='" + score_name + '\'' +
                ", score_value=" + score_value +
                ", sid=" + sid +
                '}';
    }
}
