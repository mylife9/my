package com.ruoyi.points.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 积分对象 points
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class Points extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录编号 */
    private Long id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 当前积分 */
    @Excel(name = "当前积分")
    private BigDecimal currentScore;

    /** 出行分 */
    @Excel(name = "出行分")
    private BigDecimal tripScore;

    /** 服务分 */
    @Excel(name = "服务分")
    private BigDecimal serviceScore;

    /** 车型分 */
    @Excel(name = "车型分")
    private BigDecimal carTypePoints;

    /** 合规分 */
    @Excel(name = "合规分")
    private BigDecimal complianceScore;

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

    public Long getDriverId() {
        return driverId;
    }

    public void setCurrentScore(BigDecimal currentScore)
    {
        this.currentScore = currentScore;
    }

    public BigDecimal getCurrentScore() 
    {
        return currentScore;
    }
    public void setTripScore(BigDecimal tripScore) 
    {
        this.tripScore = tripScore;
    }

    public BigDecimal getTripScore() 
    {
        return tripScore;
    }
    public void setServiceScore(BigDecimal serviceScore) 
    {
        this.serviceScore = serviceScore;
    }

    public BigDecimal getServiceScore() 
    {
        return serviceScore;
    }
    public void setCarTypePoints(BigDecimal carTypePoints) 
    {
        this.carTypePoints = carTypePoints;
    }

    public BigDecimal getCarTypePoints()
    {
        return carTypePoints;
    }
    public void setComplianceScore(BigDecimal complianceScore)
    {
        this.complianceScore = complianceScore;
    }

    public BigDecimal getComplianceScore()
    {
        return complianceScore;
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
            .append("currentScore", getCurrentScore())
            .append("tripScore", getTripScore())
            .append("serviceScore", getServiceScore())
            .append("carTypePoints", getCarTypePoints())
            .append("complianceScore", getComplianceScore())
            .append("foundTime", getFoundTime())
            .toString();
    }
}
