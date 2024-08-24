package com.ruoyi.points.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 合规分对象 compliance
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class Compliance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 驾驶证 */
    @Excel(name = "驾驶证")
    private String driverCard;

    /** 身份证 */
    @Excel(name = "身份证")
    private String idCard;

    /** 合规分 */
    @Excel(name = "合规分")
    private BigDecimal complianceScope;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 0:为空 1:不为空 */
    @Excel(name = "0:为空 1:不为空")
    private String ifNull;

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
    public void setDriverCard(String driverCard) 
    {
        this.driverCard = driverCard;
    }

    public String getDriverCard() 
    {
        return driverCard;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setComplianceScope(BigDecimal complianceScope)
    {
        this.complianceScope = complianceScope;
    }

    public BigDecimal getComplianceScope()
    {
        return complianceScope;
    }
    public void setDriverId(Long driverId) 
    {
        this.driverId = driverId;
    }

    public Long getDriverId() 
    {
        return driverId;
    }
    public void setIfNull(String ifNull) 
    {
        this.ifNull = ifNull;
    }

    public String getIfNull() 
    {
        return ifNull;
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
            .append("driverCard", getDriverCard())
            .append("idCard", getIdCard())
            .append("complianceScope", getComplianceScope())
            .append("driverId", getDriverId())
            .append("ifNull", getIfNull())
            .append("foundTime", getFoundTime())
            .toString();
    }
}
