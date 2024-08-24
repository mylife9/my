package com.ruoyi.brand.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 品牌管理对象 tb_car_brand
 * 
 * @author ruoyi
 * @date 2024-08-18
 */

public class CarBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆品牌id */
    private Long id;

    /** 车辆品牌名称 */
    @Excel(name = "车辆品牌名称")
    private String carBrandName;

    /** 所属id */
    @Excel(name = "所属id")
    private Long pid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCarBrandName(String carBrandName) 
    {
        this.carBrandName = carBrandName;
    }

    public String getCarBrandName() 
    {
        return carBrandName;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carBrandName", getCarBrandName())
            .append("pid", getPid())
            .toString();
    }
}
