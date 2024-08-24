package com.ruoyi.drools.domain;/**
 * @program: rouyi-drools
 * @author: xlk
 * @description:
 * @create: 2024-08-21 18:05
 */

import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @author : 成长
 * @date : 2024-08-21 18:05
 * @description :
 *
 **/
public class Compliance extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer driverId;
    private Double complianceScope;
    private String idCard;
    private String driverCard;
    private Integer ifNull;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Double getComplianceScope() {
        return complianceScope;
    }

    public void setComplianceScope(Double complianceScope) {
        this.complianceScope = complianceScope;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDriverCard() {
        return driverCard;
    }

    public void setDriverCard(String driverCard) {
        this.driverCard = driverCard;
    }

    public Integer getIfNull() {
        return ifNull;
    }

    public void setIfNull(Integer ifNull) {
        this.ifNull = ifNull;
    }

    @Override
    public String toString() {
        return "Compliance{" +
                "id=" + id +
                ", driverId=" + driverId +
                ", complianceScope=" + complianceScope +
                ", idCard='" + idCard + '\'' +
                ", driverCard='" + driverCard + '\'' +
                ", ifNull=" + ifNull +
                '}';
    }
}
