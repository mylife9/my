package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模版管理对象 tb_type_template
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public class TbTypeTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模版编号 */
    private Long id;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String name;

    /** 关联规格 */
    @Excel(name = "关联规格")
    private String specIds;

    /** 关联品牌 */
    @Excel(name = "关联品牌")
    private String brandIds;

    /** 自定义属性 */
    @Excel(name = "自定义属性")
    private String customAttributeItems;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSpecIds(String specIds) 
    {
        this.specIds = specIds;
    }

    public String getSpecIds() 
    {
        return specIds;
    }
    public void setBrandIds(String brandIds) 
    {
        this.brandIds = brandIds;
    }

    public String getBrandIds() 
    {
        return brandIds;
    }
    public void setCustomAttributeItems(String customAttributeItems) 
    {
        this.customAttributeItems = customAttributeItems;
    }

    public String getCustomAttributeItems() 
    {
        return customAttributeItems;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("specIds", getSpecIds())
            .append("brandIds", getBrandIds())
            .append("customAttributeItems", getCustomAttributeItems())
            .toString();
    }
}
