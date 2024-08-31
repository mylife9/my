package com.ruoyi.taxi.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @Author: M
 * @Date: 2024/8/16 22:02:07
 * @Version: v1.0.0
 **/
public class CouponsType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表的id
     */
    private Long id;

    /**
     * 类型名称
     */
    @Excel(name = "优惠券的名称")
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
