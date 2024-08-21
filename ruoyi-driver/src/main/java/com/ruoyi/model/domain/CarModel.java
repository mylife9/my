package com.ruoyi.model.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 车型管理对象 car_model
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class CarModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */

    private Long id;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String model;

    /** 发动机排量 */
    @Excel(name = "发动机排量")
    private String engineDisplace;

    /** 燃油类型 */
    @Excel(name = "燃油类型")
    private String fueType;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 库存量 */
    @Excel(name = "库存量")
    private Long num;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setEngineDisplace(String engineDisplace) 
    {
        this.engineDisplace = engineDisplace;
    }

    public String getEngineDisplace() 
    {
        return engineDisplace;
    }
    public void setFueType(String fueType) 
    {
        this.fueType = fueType;
    }

    public String getFueType() 
    {
        return fueType;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("model", getModel())
            .append("engineDisplace", getEngineDisplace())
            .append("fueType", getFueType())
            .append("vehicleType", getVehicleType())
            .append("num", getNum())
            .toString();
    }
}
