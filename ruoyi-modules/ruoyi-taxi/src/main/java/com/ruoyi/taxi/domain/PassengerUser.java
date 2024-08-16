package com.ruoyi.taxi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 11:59
 */
public class PassengerUser {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    private Date gmtModified;
    private String passengerPhone;
    private String passengerName;
    private Integer passengerGender;
    private Integer state;
    private String profilePhoto;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(Integer passengerGender) {
        this.passengerGender = passengerGender;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public PassengerUser() {
    }

    public PassengerUser(Integer id, Date gmtCreate, Date gmtModified, String passengerPhone, String passengerName, Integer passengerGender, Integer state, String profilePhoto) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.passengerPhone = passengerPhone;
        this.passengerName = passengerName;
        this.passengerGender = passengerGender;
        this.state = state;
        this.profilePhoto = profilePhoto;
    }
}
