package com.ruoyi.drools.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 车型分对象 model_points
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class ModelPoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** 车型ID */
    @Excel(name = "车型ID")
    private Integer vehicleTypeId;

    /** 车型名称 */
    @Excel(name = "车型名称")
    private String typeName;

    /** 座位数 */
    @Excel(name = "座位数")
    private String seats;

    /** 车型描述 */
    @Excel(name = "车型描述")
    private String description;

    /** 车型分 */
    @Excel(name = "车型分")
    private Double modelPoints;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setVehicleTypeId(Integer vehicleTypeId) 
    {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getVehicleTypeId() 
    {
        return vehicleTypeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setSeats(String seats) 
    {
        this.seats = seats;
    }

    public String getSeats() 
    {
        return seats;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setModelPoints(Double modelPoints) 
    {
        this.modelPoints = modelPoints;
    }

    public Double getModelPoints() 
    {
        return modelPoints;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleTypeId", getVehicleTypeId())
            .append("typeName", getTypeName())
            .append("seats", getSeats())
            .append("description", getDescription())
            .append("modelPoints", getModelPoints())
            .toString();
    }
}
