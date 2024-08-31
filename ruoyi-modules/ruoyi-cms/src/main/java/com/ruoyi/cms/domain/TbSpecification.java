package com.ruoyi.cms.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 规格管理对象 tb_specification
 * 
 * @author ruoyi
 * @date 2024-06-21
 */
public class TbSpecification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规格编号 */
    private Long id;

    /** 规格名称 */
    @Excel(name = "规格名称")
    private String specName;

    @Excel(name = "规格参数")
    private String optionName;

    private List<TbSpecificationOption> options;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSpecName(String specName) 
    {
        this.specName = specName;
    }

    public String getSpecName() 
    {
        return specName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public List<TbSpecificationOption> getOptions() {
        return options;
    }

    public void setOptions(List<TbSpecificationOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("specName", getSpecName())
            .append("optionName", getOptionName())
            .toString();
    }
}
