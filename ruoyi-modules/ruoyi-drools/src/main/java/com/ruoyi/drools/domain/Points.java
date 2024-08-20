package com.ruoyi.drools.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 积分对象 points
 * 
 * @author ruoyi
 * @date 2024-08-19
 */
public class Points extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录编号 */
    private Integer id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Integer driverId;

    /** 扣分详情 */
    @Excel(name = "扣分详情")
    private Integer eventType;

    /** 当前积分 */
    @Excel(name = "当前积分")
    private double currentScore;

    /** 扣分 */
    @Excel(name = "扣分")
    private double originalIntegral;

    /** 出行分 */
    @Excel(name = "出行分")
    private double tripScore;

    /** 服务分 */
    @Excel(name = "服务分")
    private double serviceScore;

    /** 车型分 */
    @Excel(name = "车型分")
    private double carTypePoints;

    /** 合规分 */
    @Excel(name = "合规分")
    private double complianceScore;

    /** 加分详情 */
    @Excel(name = "加分详情")
    private Integer eventType1;

    /** 加分 */
    @Excel(name = "加分")
    private double originalIntegral1;

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
    public void setEventType(Integer eventType)
    {
        this.eventType = eventType;
    }

    public Integer getEventType()
    {
        return eventType;
    }
    public void setCurrentScore(double currentScore)
    {
        this.currentScore = currentScore;
    }

    public double getCurrentScore()
    {
        return currentScore;
    }
    public void setOriginalIntegral(double originalIntegral)
    {
        this.originalIntegral = originalIntegral;
    }

    public double getOriginalIntegral()
    {
        return originalIntegral;
    }
    public void setTripScore(double tripScore)
    {
        this.tripScore = tripScore;
    }

    public double getTripScore()
    {
        return tripScore;
    }
    public void setServiceScore(double serviceScore)
    {
        this.serviceScore = serviceScore;
    }

    public double getServiceScore()
    {
        return serviceScore;
    }
    public void setCarTypePoints(double carTypePoints)
    {
        this.carTypePoints = carTypePoints;
    }

    public double getCarTypePoints()
    {
        return carTypePoints;
    }
    public void setComplianceScore(double complianceScore)
    {
        this.complianceScore = complianceScore;
    }

    public double getComplianceScore()
    {
        return complianceScore;
    }
    public void setEventType1(Integer eventType1)
    {
        this.eventType1 = eventType1;
    }

    public Integer getEventType1()
    {
        return eventType1;
    }
    public void setOriginalIntegral1(double originalIntegral1)
    {
        this.originalIntegral1 = originalIntegral1;
    }

    public double getOriginalIntegral1()
    {
        return originalIntegral1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("eventType", getEventType())
            .append("currentScore", getCurrentScore())
            .append("originalIntegral", getOriginalIntegral())
            .append("tripScore", getTripScore())
            .append("serviceScore", getServiceScore())
            .append("carTypePoints", getCarTypePoints())
            .append("complianceScore", getComplianceScore())
            .append("eventType1", getEventType1())
            .append("originalIntegral1", getOriginalIntegral1())
            .toString();
    }
}
