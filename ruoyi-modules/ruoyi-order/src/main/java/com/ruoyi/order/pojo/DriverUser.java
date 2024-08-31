package com.ruoyi.order.pojo;

/**
 * @author : Li
 * @date : 2024-08-26 10:31
 * @description :
 **/
public class DriverUser {
    private Integer id;
    private String driverName;
    private String driverPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @Override
    public String toString() {
        return "DriverUser{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                '}';
    }
}
