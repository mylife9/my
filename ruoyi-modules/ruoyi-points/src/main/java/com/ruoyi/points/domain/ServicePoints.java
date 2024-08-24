package com.ruoyi.points.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 服务分对象 service_points
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class ServicePoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 服务描述 */
    @Excel(name = "服务描述")
    private String description;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String servicename;

    /** 服务分 */
    @Excel(name = "服务分")
    private BigDecimal servicePoints;

    /** 加分ID */
    @Excel(name = "加分ID")
    private Long bonusPointId;

    /** 减分ID */
    @Excel(name = "减分ID")
    private Long subtractPointsId;

    /** 分数 */
    @Excel(name = "分数")
    private BigDecimal subtractPoints;

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
    public void setServicePoints(BigDecimal servicePoints) 
    {
        this.servicePoints = servicePoints;
    }

    public BigDecimal getServicePoints() 
    {
        return servicePoints;
    }
    public void setBonusPointId(Long bonusPointId) 
    {
        this.bonusPointId = bonusPointId;
    }

    public Long getBonusPointId() 
    {
        return bonusPointId;
    }
    public void setSubtractPointsId(Long subtractPointsId) 
    {
        this.subtractPointsId = subtractPointsId;
    }

    public Long getSubtractPointsId() 
    {
        return subtractPointsId;
    }
    public void setSubtractPoints(BigDecimal subtractPoints) 
    {
        this.subtractPoints = subtractPoints;
    }

    public BigDecimal getSubtractPoints() 
    {
        return subtractPoints;
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
            .append("description", getDescription())
            .append("servicename", getServicename())
            .append("servicePoints", getServicePoints())
            .append("bonusPointId", getBonusPointId())
            .append("subtractPointsId", getSubtractPointsId())
            .append("subtractPoints", getSubtractPoints())
            .append("foundTime", getFoundTime())
            .toString();
    }
}
