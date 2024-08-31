package com.ruoyi.order.pojo;

/**
 * @program: RuoYi-Cloud
 * @author: gtx
 * @description:
 * @create: 2024-08-21 21:31
 */
public class Driver {

    private Long id;

    private String  address;



    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
