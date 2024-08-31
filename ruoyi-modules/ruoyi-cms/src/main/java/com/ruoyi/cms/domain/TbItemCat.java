package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 商品类目管理对象 tb_item_cat
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public class TbItemCat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类目编号 */
    private Long id;

    /** 父类目编号 */
    @Excel(name = "父类目编号")
    private Long parentId;

    /** 类目名称 */
    @Excel(name = "类目名称")
    private String name;

    /** 模版编号 */
    @Excel(name = "模版编号")
    private Long typeId;

    private List<TbItemCat> children;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    public List<TbItemCat> getChildren() {
        return children;
    }

    public void setChildren(List<TbItemCat> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("name", getName())
            .append("typeId", getTypeId())
            .toString();
    }
}
