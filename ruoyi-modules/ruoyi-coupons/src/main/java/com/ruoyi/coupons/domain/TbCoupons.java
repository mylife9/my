package com.ruoyi.coupons.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券对象 tb_coupons
 *
 * @author ruoyi
 * @date 2024-08-16
 */
public class TbCoupons extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表的id
     */
    private Long id;

    /**
     * 优惠券的编号
     */
    @Excel(name = "优惠券的编号")
    private String couponsNumber;
    /**
     * 优惠券的编号
     */

    private Integer status;

    /**
     * 优惠券的名称(什么活动用的优惠券)
     */
    @Excel(name = "优惠券的名称(什么活动用的优惠券)")
    private String couponsName;

    /**
     * 优惠券的面值
     */
    @Excel(name = "优惠券的面值")
    private BigDecimal couponsAmount;

    private int days;


    /**
     * 优惠券的最低消费
     */
    @Excel(name = "优惠券的最低消费")
    private BigDecimal couponHold;

    /**
     * 优惠券的类型
     */
    @Excel(name = "优惠券的类型")
    private Long couponsType;

    /**
     * 发出的数量
     */
    @Excel(name = "发出的数量")
    private Long receiveCount;


    /**
     * 优惠券的创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "优惠券的创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date couponsCreateDate;

    /**
     * 优惠券的过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "优惠券的过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date couponsExpirationDate;

    /**
     * 优惠券的状态 （1开始发放 0停止发放  2 删除）
     */
    @Excel(name = "优惠券的状态 ", readConverterExp = "1=开始发放,0=停止发放")
    private Long couponsStatus;

    /**
     * 优惠券的类型
     */
    @Excel(name = "优惠券的类型")
    private String typeName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date receiveTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expirationDate;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "TbCoupons{" +
                "id=" + id +
                ", couponsNumber='" + couponsNumber + '\'' +
                ", couponsName='" + couponsName + '\'' +
                ", couponsAmount=" + couponsAmount +
                ", couponHold=" + couponHold +
                ", couponsType=" + couponsType +
                ", receiveCount=" + receiveCount +
                ", couponsCreateDate=" + couponsCreateDate +
                ", couponsExpirationDate=" + couponsExpirationDate +
                ", typeName='" + typeName + '\'' +
                ", couponsStatus=" + couponsStatus +
                '}';
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponsNumber() {
        return couponsNumber;
    }

    public void setCouponsNumber(String couponsNumber) {
        this.couponsNumber = couponsNumber;
    }

    public String getCouponsName() {
        return couponsName;
    }

    public void setCouponsName(String couponsName) {
        this.couponsName = couponsName;
    }

    public BigDecimal getCouponsAmount() {
        return couponsAmount;
    }

    public void setCouponsAmount(BigDecimal couponsAmount) {
        this.couponsAmount = couponsAmount;
    }

    public BigDecimal getCouponHold() {
        return couponHold;
    }

    public void setCouponHold(BigDecimal couponHold) {
        this.couponHold = couponHold;
    }

    public Long getCouponsType() {
        return couponsType;
    }

    public void setCouponsType(Long couponsType) {
        this.couponsType = couponsType;
    }

    public Long getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Long receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Date getCouponsCreateDate() {
        return couponsCreateDate;
    }

    public void setCouponsCreateDate(Date couponsCreateDate) {
        this.couponsCreateDate = couponsCreateDate;
    }

    public Date getCouponsExpirationDate() {
        return couponsExpirationDate;
    }

    public void setCouponsExpirationDate(Date couponsExpirationDate) {
        this.couponsExpirationDate = couponsExpirationDate;
    }

    public Long getCouponsStatus() {
        return couponsStatus;
    }

    public void setCouponsStatus(Long couponsStatus) {
        this.couponsStatus = couponsStatus;
    }

}
