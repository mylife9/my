package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品对象 tb_item
 * 
 * @author ruoyi
 * @date 2024-01-11
 */
public class TbItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id，同时也是商品编号 */
    private Long id;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String title;

    /** 商品卖点 */
    @Excel(name = "商品卖点")
    private String sellPoint;

    /** 商品价格，单位为：元 */
    @Excel(name = "商品价格，单位为：元")
    private BigDecimal price;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer stockCount;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Integer num;

    /** 商品条形码 */
    @Excel(name = "商品条形码")
    private String barcode;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String image;

    /** 所属类目，叶子类目 */
    @Excel(name = "所属类目，叶子类目")
    private Integer categoryId;

    /** 商品状态，1-正常，2-下架，3-删除 */
    @Excel(name = "商品状态，1-正常，2-下架，3-删除")
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String itemSn;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal costPirce;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal marketPrice;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isDefault;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long goodsId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sellerId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cartThumbnail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String category;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String brand;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String spec;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String seller;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setSellPoint(String sellPoint) 
    {
        this.sellPoint = sellPoint;
    }

    public String getSellPoint() 
    {
        return sellPoint;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setStockCount(Integer stockCount) 
    {
        this.stockCount = stockCount;
    }

    public Integer getStockCount() 
    {
        return stockCount;
    }
    public void setNum(Integer num) 
    {
        this.num = num;
    }

    public Integer getNum() 
    {
        return num;
    }
    public void setBarcode(String barcode) 
    {
        this.barcode = barcode;
    }

    public String getBarcode() 
    {
        return barcode;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setCategoryId(Integer categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() 
    {
        return categoryId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setItemSn(String itemSn) 
    {
        this.itemSn = itemSn;
    }

    public String getItemSn() 
    {
        return itemSn;
    }
    public void setCostPirce(BigDecimal costPirce) 
    {
        this.costPirce = costPirce;
    }

    public BigDecimal getCostPirce() 
    {
        return costPirce;
    }
    public void setMarketPrice(BigDecimal marketPrice) 
    {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMarketPrice() 
    {
        return marketPrice;
    }
    public void setIsDefault(String isDefault) 
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault() 
    {
        return isDefault;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setSellerId(String sellerId) 
    {
        this.sellerId = sellerId;
    }

    public String getSellerId() 
    {
        return sellerId;
    }
    public void setCartThumbnail(String cartThumbnail) 
    {
        this.cartThumbnail = cartThumbnail;
    }

    public String getCartThumbnail() 
    {
        return cartThumbnail;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
    }
    public void setSeller(String seller) 
    {
        this.seller = seller;
    }

    public String getSeller() 
    {
        return seller;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("sellPoint", getSellPoint())
            .append("price", getPrice())
            .append("stockCount", getStockCount())
            .append("num", getNum())
            .append("barcode", getBarcode())
            .append("image", getImage())
            .append("categoryId", getCategoryId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("itemSn", getItemSn())
            .append("costPirce", getCostPirce())
            .append("marketPrice", getMarketPrice())
            .append("isDefault", getIsDefault())
            .append("goodsId", getGoodsId())
            .append("sellerId", getSellerId())
            .append("cartThumbnail", getCartThumbnail())
            .append("category", getCategory())
            .append("brand", getBrand())
            .append("spec", getSpec())
            .append("seller", getSeller())
            .toString();
    }
}
