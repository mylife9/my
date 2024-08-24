package com.ruoyi.drools.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * drools对象 service_points
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class ServicePoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Integer driverId;

    /** 服务描述 */
    @Excel(name = "服务描述")
    private String description;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String servicename;

    /** 服务分 */
    @Excel(name = "服务分")
    private Double servicePoints;
    @Excel(name = "减分")
    private Double subtractPoints;

    /** 加分ID */
    @Excel(name = "加分ID")
    private Integer bonusPointId;

    @Excel(name = "减分ID")
    private Integer subtractPointsId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDriverId(Integer driverId) 
    {
        this.driverId = driverId;
    }

    public Integer getDriverId() 
    {
        return driverId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setServicename(String servicename) 
    {
        this.servicename = servicename;
    }

    public String getServicename() 
    {
        return servicename;
    }
    public void setServicePoints(Double servicePoints) 
    {
        this.servicePoints = servicePoints;
    }

    public Double getServicePoints() 
    {
        return servicePoints;
    }
    public void setBonusPointId(Integer bonusPointId) 
    {
        this.bonusPointId = bonusPointId;
    }

    public Integer getBonusPointId() 
    {
        return bonusPointId;
    }

    public Integer getSubtractPointsId() {
        return subtractPointsId;
    }

    public void setSubtractPointsId(Integer subtractPointsId) {
        this.subtractPointsId = subtractPointsId;
    }

    public Double getSubtractPoints() {
        return subtractPoints;
    }

    public void setSubtractPoints(Double subtractPoints) {
        this.subtractPoints = subtractPoints;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("description", getDescription())
            .append("servicename", getServicename())
            .append("servicePoints", getServicePoints())
            .append("bonusPointId", getBonusPointId())
            .append("subtractPointsId",getSubtractPointsId())
            .append("subtractPoints",getSubtractPoints())
            .toString();
    }

}
