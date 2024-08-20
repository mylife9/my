package com.ruoyi.coupons.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 优惠券的使用表
* @TableName coupons_log
*/
public class CouponsLog extends BaseEntity {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer id;
    /**
    * 用户的id
    */
    @ApiModelProperty("用户的id")
    private Integer userId;
    /**
    * 优惠券的id
    */
    @ApiModelProperty("优惠券的id")
    private Integer couponId;
    /**
    * 订单ID
    */
    @ApiModelProperty("订单ID")
    private Long orderId;
    /**
    * 使用时间
    */
    @ApiModelProperty("使用时间")
    private Date receiveTime;
    /**
    * 是否使用，0 表示未使用，1 表示已使用
    */
    @ApiModelProperty("是否使用，0 表示未使用，1 表示已使用")
    private Integer used;


    public @NotNull(message = "[]不能为空") Integer getId() {
        return id;
    }

    public void setId(@NotNull(message = "[]不能为空") Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
