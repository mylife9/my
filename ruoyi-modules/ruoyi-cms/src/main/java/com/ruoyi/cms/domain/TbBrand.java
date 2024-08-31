package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 品牌管理对象 tb_brand
 * 
 * @author ruoyi
 * @date 2024-06-20
 */
public class TbBrand extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品牌编号 */
    private Long id;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** LOGO地址 */
    @Excel(name = "LOGO地址")
    private String logo;

    /** 首字母 */
    @Excel(name = "首字母")
    private String firstChar;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setFirstChar(String firstChar) 
    {
        this.firstChar = firstChar;
    }

    public String getFirstChar() 
    {
        return firstChar;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("brandName", getBrandName())
            .append("logo", getLogo())
            .append("firstChar", getFirstChar())
            .append("createBy", getCreateBy())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
