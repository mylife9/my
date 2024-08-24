package com.ruoyi.driver.domain;


import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.web.domain.BaseEntity;


/**
 * 地区表
 * @TableName dic_district
 */
@TableName("dic_district")
public class DicDistrict  extends BaseEntity {
    /**
     * 地区编码
     */
    private String addressCode;

    /**
     * 地区名称
     */
    private String addressName;

    /**
     * 父地区编码
     */
    private String parentAddressCode;

    private List<DicDistrict> children;

    public List<DicDistrict> getChildren() {
        return children;
    }

    public void setChildren(List<DicDistrict> children) {
        this.children = children;
    }

    /**
     * 级别
     */
    private Integer level;

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getParentAddressCode() {
        return parentAddressCode;
    }

    public void setParentAddressCode(String parentAddressCode) {
        this.parentAddressCode = parentAddressCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}