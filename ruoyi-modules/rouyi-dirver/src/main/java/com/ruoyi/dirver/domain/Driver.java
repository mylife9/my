package com.ruoyi.dirver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/18 14:28
 */

public class Driver {
    private Long id;
    private String driverName;
    private String driverPhone;
    private String driverNumber;
    private String driverGender;
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String driverBirthday;
    private String driverAddress;
    private String licenseId;
//    private String frontImage;
    private String driverPhoto;
    private String code;
    private String driverNation;
    private Long state;

    public String getDriverBirthday() {
        return driverBirthday;
    }

    public void setDriverBirthday(String driverBirthday) {
        this.driverBirthday = driverBirthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverGender() {
        return driverGender;
    }

    public void setDriverGender(String driverGender) {
        this.driverGender = driverGender;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDriverNation() {
        return driverNation;
    }

    public void setDriverNation(String driverNation) {
        this.driverNation = driverNation;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", driverGender='" + driverGender + '\'' +
                ", driverBirthday=" + driverBirthday +
                ", driverAddress='" + driverAddress + '\'' +
                ", licenseId='" + licenseId + '\'' +
                ", code='" + code + '\'' +
                ", driverNation='" + driverNation + '\'' +
                ", state=" + state +
                '}';
    }
}
