package com.ruoyi.order.pojo;/**
 * @program: ruoyi-car
 * @author: xlk
 * @description:
 * @create: 2024-08-22 16:06
 */

import java.util.Date;

/**
 * @author : 成长
 * @date : 2024-08-22 16:06
 * @description :
 *
 **/
public class Passenger {
    private Long id;
    private Long passengerId;
    private String passengerName;
    private Double passengerScope;

    private Integer descNum;
    private Date createTime;
    private Integer status;

    private long day;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getPassenger_id() {
//        return passenger_id;
//    }
//
//    public void setPassenger_id(Long passenger_id) {
//        this.passenger_id = passenger_id;
//    }
//
//    public String getPassenger_name() {
//        return passenger_name;
//    }
//
//    public void setPassenger_name(String passenger_name) {
//        this.passenger_name = passenger_name;
//    }
//
//    public Double getPassenger_scope() {
//        return passenger_scope;
//    }
//
//    public void setPassenger_scope(Double passenger_scope) {
//        this.passenger_scope = passenger_scope;
//    }
//
//    public Integer getNum() {
//        return num;
//    }
//
//    public void setNum(Integer num) {
//        this.num = num;
//    }
//
//    public Integer getDesc_num() {
//        return desc_num;
//    }
//
//    public void setDesc_num(Integer desc_num) {
//        this.desc_num = desc_num;
//    }
//
//    public Date getCreate_time() {
//        return create_time;
//    }
//
//    public void setCreate_time(Date create_time) {
//        this.create_time = create_time;
//    }
//
//    @Override
//    public String toString() {
//        return "Passenger{" +
//                "id=" + id +
//                ", passenger_id=" + passenger_id +
//                ", passenger_name='" + passenger_name + '\'' +
//                ", passenger_scope=" + passenger_scope +
//                ", num=" + num +
//                ", desc_num=" + desc_num +
//                ", create_time=" + create_time +
//                '}';
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Double getPassengerScope() {
        return passengerScope;
    }

    public void setPassengerScope(Double passengerScope) {
        this.passengerScope = passengerScope;
    }

    public Integer getDescNum() {
        return descNum;
    }

    public void setDescNum(Integer descNum) {
        this.descNum = descNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", passengerName='" + passengerName + '\'' +
                ", passengerScope=" + passengerScope +
                ", descNum=" + descNum +
                ", createTime=" + createTime +
                ", status=" + status +
                ", day=" + day +
                '}';
    }
}
