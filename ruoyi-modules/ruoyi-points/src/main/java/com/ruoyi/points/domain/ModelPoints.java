package com.ruoyi.points.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 车型ID */
    @Excel(name = "车型ID")
    private Long vehicleTypeId;

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
    private BigDecimal modelPoints;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date foundTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDriverId(Long driverId) 
    {
        this.driverId = driverId;
    }

    public Long getDriverId() 
    {
        return driverId;
    }
    public void setVehicleTypeId(Long vehicleTypeId) 
    {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Long getVehicleTypeId() 
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
    public void setModelPoints(BigDecimal modelPoints) 
    {
        this.modelPoints = modelPoints;
    }

    public BigDecimal getModelPoints() 
    {
        return modelPoints;
    }
    public void setFoundTime(Date foundTime) 
    {
        this.foundTime = foundTime;
    }

    public Date getFoundTime() 
    {
        return foundTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("vehicleTypeId", getVehicleTypeId())
            .append("typeName", getTypeName())
            .append("seats", getSeats())
            .append("description", getDescription())
            .append("modelPoints", getModelPoints())
            .append("foundTime", getFoundTime())
            .toString();
    }
}
