package com.ruoyi.points.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 出行分对象 travel_points
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public class TravelPoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 出行时常(小时) */
    @Excel(name = "出行时常(小时)")
    private Long tripHour;

    /** 接单量 */
    @Excel(name = "接单量")
    private Long num;

    /** 添加出行分 */
    @Excel(name = "添加出行分")
    private BigDecimal tripScope;

    /** 加分ID */
    @Excel(name = "加分ID")
    private Long bonusPointId;

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
    public void setTripHour(Long tripHour) 
    {
        this.tripHour = tripHour;
    }

    public Long getTripHour() 
    {
        return tripHour;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setTripScope(BigDecimal tripScope) 
    {
        this.tripScope = tripScope;
    }

    public BigDecimal getTripScope() 
    {
        return tripScope;
    }
    public void setBonusPointId(Long bonusPointId) 
    {
        this.bonusPointId = bonusPointId;
    }

    public Long getBonusPointId() 
    {
        return bonusPointId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("tripHour", getTripHour())
            .append("num", getNum())
            .append("tripScope", getTripScope())
            .append("bonusPointId", getBonusPointId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
