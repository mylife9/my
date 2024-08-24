package com.ruoyi.drools.domain;/**
 * @program: rouyi-drools
 * @author: xlk
 * @description:
 * @create: 2024-08-21 14:10
 */

import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author : 成长
 * @date : 2024-08-21 14:10
 * @description :
 *
 **/

public class TripPoints extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer tripHour;
    private Integer num;
    private Integer driverId;
    private Double nowScope;
    private Double tripScope;
    private Integer bonusPointId;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripHour() {
        return tripHour;
    }

    public void setTripHour(Integer tripHour) {
        this.tripHour = tripHour;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Double getTripScope() {
        return tripScope;
    }

    public void setTripScope(Double tripScope) {
        this.tripScope = tripScope;
    }

    public Integer getBonusPointId() {
        return bonusPointId;
    }

    public Double getNowScope() {
        return nowScope;
    }

    public void setNowScope(Double nowScope) {
        this.nowScope = nowScope;
    }

    public void setBonusPointId(Integer bonusPointId) {
        this.bonusPointId = bonusPointId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TripPoints{" +
                "id=" + id +
                ", tripHour=" + tripHour +
                ", num=" + num +
                ", driverId=" + driverId +
                ", nowScope=" + nowScope +
                ", tripScope=" + tripScope +
                ", bonusPointId=" + bonusPointId +
                ", createTime=" + createTime +
                '}';
    }
}
