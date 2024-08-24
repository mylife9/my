package com.ruoyi.dirver.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 司机登录对象 tb_user
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public class TbUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 司机编号 */
    private Long id;

    /** 账号 */
    @Excel(name = "账号")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("phone", getPhone())
            .toString();
    }

}
