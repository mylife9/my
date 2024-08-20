package com.ruoyi.drools.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;


/**
 * @author : 暴龙兽
 * @date : 2024-08-19 09:30
 * @description :
 **/
public class BonusPoint extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String reputation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    @Override
    public String toString() {
        return "BonusPoint{" +
                "id=" + id +
                ", reputation='" + reputation + '\'' +
                '}';
    }
}
