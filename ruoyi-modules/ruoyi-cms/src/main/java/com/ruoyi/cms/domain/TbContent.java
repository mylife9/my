package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 广告信息对象 tb_content
 * 
 * @author ruoyi
 * @date 2024-07-22
 */
public class TbContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告编号 */
    private Long id;

    /** 内容类目编号 */
    @Excel(name = "内容类目编号")
    private Long categoryId;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String title;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 图片的路径 */
    @Excel(name = "图片的路径")
    private String pic;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setPic(String pic) 
    {
        this.pic = pic;
    }

    public String getPic() 
    {
        return pic;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSortOrder(Long sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder() 
    {
        return sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("title", getTitle())
            .append("url", getUrl())
            .append("pic", getPic())
            .append("status", getStatus())
            .append("sortOrder", getSortOrder())
            .toString();
    }
}
