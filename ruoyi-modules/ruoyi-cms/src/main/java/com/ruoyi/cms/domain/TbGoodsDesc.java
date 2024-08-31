package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 tb_goods_desc
 * 
 * @author ruoyi
 * @date 2024-01-11
 */
public class TbGoodsDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** SPU_ID */
    private Long goodsId;

    /** 描述 */
    @Excel(name = "描述")
    private String introduction;

    /** 规格结果集，所有规格，包含isSelected */
    @Excel(name = "规格结果集，所有规格，包含isSelected")
    private String specificationItems;

    /** 自定义属性（参数结果） */
    @Excel(name = "自定义属性", readConverterExp = "参=数结果")
    private String customAttributeItems;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String itemImages;

    /** 包装列表 */
    @Excel(name = "包装列表")
    private String packageList;

    /** 售后服务 */
    @Excel(name = "售后服务")
    private String saleService;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setSpecificationItems(String specificationItems) 
    {
        this.specificationItems = specificationItems;
    }

    public String getSpecificationItems() 
    {
        return specificationItems;
    }
    public void setCustomAttributeItems(String customAttributeItems) 
    {
        this.customAttributeItems = customAttributeItems;
    }

    public String getCustomAttributeItems() 
    {
        return customAttributeItems;
    }
    public void setItemImages(String itemImages) 
    {
        this.itemImages = itemImages;
    }

    public String getItemImages() 
    {
        return itemImages;
    }
    public void setPackageList(String packageList) 
    {
        this.packageList = packageList;
    }

    public String getPackageList() 
    {
        return packageList;
    }
    public void setSaleService(String saleService) 
    {
        this.saleService = saleService;
    }

    public String getSaleService() 
    {
        return saleService;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("introduction", getIntroduction())
            .append("specificationItems", getSpecificationItems())
            .append("customAttributeItems", getCustomAttributeItems())
            .append("itemImages", getItemImages())
            .append("packageList", getPackageList())
            .append("saleService", getSaleService())
            .toString();
    }
}
