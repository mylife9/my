package com.ruoyi.cms.domain;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/23 23:06
 * @注释
 */

public class TbUser {
    private  Long id;
    private  String account;
    private  String username;
    private  String password;
    private  String phone;
    private  String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
