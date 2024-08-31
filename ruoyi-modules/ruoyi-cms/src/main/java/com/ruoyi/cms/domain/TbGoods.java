package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 【请填写功能名称】对象 tb_goods
 * 
 * @author ruoyi
 * @date 2024-01-11
 */
public class TbGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 商家ID */
    @Excel(name = "商家ID")
    private String sellerId;

    /** SPU名 */
    @Excel(name = "SPU名")
    private String goodsName;

    /** 默认SKU */
    @Excel(name = "默认SKU")
    private Long defaultItemId;

    /** 状态 */
    @Excel(name = "状态")
    private String auditStatus;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private String isMarketable;

    /** 品牌 */
    @Excel(name = "品牌")
    private Integer brandId;

    /** 副标题 */
    @Excel(name = "副标题")
    private String caption;

    /** 一级类目 */
    @Excel(name = "一级类目")
    private Long category1Id;

    /** 二级类目 */
    @Excel(name = "二级类目")
    private Long category2Id;

    /** 三级类目 */
    @Excel(name = "三级类目")
    private Long category3Id;

    /** 小图 */
    @Excel(name = "小图")
    private String smallPic;

    /** 商城价 */
    @Excel(name = "商城价")
    private BigDecimal price;

    /** 分类模板ID */
    @Excel(name = "分类模板ID")
    private Long typeTemplateId;

    /** 是否启用规格 */
    @Excel(name = "是否启用规格")
    private String isEnableSpec;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSellerId(String sellerId) 
    {
        this.sellerId = sellerId;
    }

    public String getSellerId() 
    {
        return sellerId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setDefaultItemId(Long defaultItemId) 
    {
        this.defaultItemId = defaultItemId;
    }

    public Long getDefaultItemId() 
    {
        return defaultItemId;
    }
    public void setAuditStatus(String auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() 
    {
        return auditStatus;
    }
    public void setIsMarketable(String isMarketable) 
    {
        this.isMarketable = isMarketable;
    }

    public String getIsMarketable() 
    {
        return isMarketable;
    }
    public void setBrandId(Integer brandId) 
    {
        this.brandId = brandId;
    }

    public Integer getBrandId()
    {
        return brandId;
    }
    public void setCaption(String caption) 
    {
        this.caption = caption;
    }

    public String getCaption() 
    {
        return caption;
    }
    public void setCategory1Id(Long category1Id) 
    {
        this.category1Id = category1Id;
    }

    public Long getCategory1Id() 
    {
        return category1Id;
    }
    public void setCategory2Id(Long category2Id)
    {
        this.category2Id = category2Id;
    }

    public Long getCategory2Id()
    {
        return category2Id;
    }
    public void setCategory3Id(Long category3Id)
    {
        this.category3Id = category3Id;
    }

    public Long getCategory3Id()
    {
        return category3Id;
    }
    public void setSmallPic(String smallPic) 
    {
        this.smallPic = smallPic;
    }

    public String getSmallPic() 
    {
        return smallPic;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setTypeTemplateId(Long typeTemplateId) 
    {
        this.typeTemplateId = typeTemplateId;
    }

    public Long getTypeTemplateId() 
    {
        return typeTemplateId;
    }
    public void setIsEnableSpec(String isEnableSpec) 
    {
        this.isEnableSpec = isEnableSpec;
    }

    public String getIsEnableSpec() 
    {
        return isEnableSpec;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sellerId", getSellerId())
            .append("goodsName", getGoodsName())
            .append("defaultItemId", getDefaultItemId())
            .append("auditStatus", getAuditStatus())
            .append("isMarketable", getIsMarketable())
            .append("brandId", getBrandId())
            .append("caption", getCaption())
            .append("category1Id", getCategory1Id())
            .append("category2Id", getCategory2Id())
            .append("category3Id", getCategory3Id())
            .append("smallPic", getSmallPic())
            .append("price", getPrice())
            .append("typeTemplateId", getTypeTemplateId())
            .append("isEnableSpec", getIsEnableSpec())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
