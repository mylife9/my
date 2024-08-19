package com.ruoyi.coupons.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @Author: M
 * @Date: 2024/8/19 10:06:23
 * @Version: v1.0.0
 **/

public class User extends BaseEntity {

    private Long id;
    private String username;
    private Integer coupon_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(Long id, String username, Integer coupon_id) {
        this.id = id;
        this.username = username;
        this.coupon_id = coupon_id;
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }
}
