package com.ruoyi.dirver.domain;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/21 11:39
 */

public class Car {
    //车辆主键
    private Long id;
    //车辆所在城市
    private String address;
    //车牌号
    private String vehicleNo;
    //车牌颜色
    private Integer plateColor;
    //核定载客数
    private Integer seats;
    //车辆厂牌
    private String brand;
    //车辆型号
    private String model;
    //车辆类型
    private String vehicleType;
    //车辆颜色
    private Integer vehicleColor;
    //车辆图片
    private String carPicture;
    //车辆注册日期
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date certifyDateA;

    private String vehiclePicture;

    private Integer owerStatus;

    public Integer getOwerStatus() {
        return owerStatus;
    }

    public void setOwerStatus(Integer owerStatus) {
        this.owerStatus = owerStatus;
    }

    public String getVehiclePicture() {
        return vehiclePicture;
    }

    public void setVehiclePicture(String vehiclePicture) {
        this.vehiclePicture = vehiclePicture;
    }

    public Date getCertifyDateA() {
        return certifyDateA;
    }

    public void setCertifyDateA(Date certifyDateA) {
        this.certifyDateA = certifyDateA;
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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(Integer vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", plateColor=" + plateColor +
                ", seats=" + seats +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleColor=" + vehicleColor +
                ", carPicture='" + carPicture + '\'' +
                ", certifyDateA=" + certifyDateA +
                '}';
    }
}
