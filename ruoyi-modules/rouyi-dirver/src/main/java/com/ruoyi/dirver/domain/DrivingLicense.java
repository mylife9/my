package com.ruoyi.dirver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/22 22:28
 */
@TableName("driving_license")
public class DrivingLicense {
    /**
     * 行驶证主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌号
     */
    private String licensePlate;

    /**
     * 车辆类型
     */

    private String modelName;

    /**
     * 车辆持有人
     */

    private String owner;

    /**
     * 性质
     */

    private String nature;

    /**
     * 车辆型号
     */

    private String modelNumber;

    /**
     * 车辆识别代码
     */

    private String identificationCode;

    /**
     * 发动机号码
     */

    private String engineNumber;

    /**
     * 注册日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    private LocalDateTime registrationDate;

    /**
     * 发证日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    private LocalDateTime issueDate;

    /**
     * 核定人数
     */
    private Integer authorizedNumber;

    /**
     * 总质量
     */
    private String grossMass;

    /**
     * 整备质量
     */
    private String qualityService;

    /**
     * 外廊尺寸
     */
    private String verandaDimension;


    private String drivingImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getAuthorizedNumber() {
        return authorizedNumber;
    }

    public void setAuthorizedNumber(Integer authorizedNumber) {
        this.authorizedNumber = authorizedNumber;
    }

    public String getGrossMass() {
        return grossMass;
    }

    public void setGrossMass(String grossMass) {
        this.grossMass = grossMass;
    }

    public String getQualityService() {
        return qualityService;
    }

    public void setQualityService(String qualityService) {
        this.qualityService = qualityService;
    }

    public String getVerandaDimension() {
        return verandaDimension;
    }

    public void setVerandaDimension(String verandaDimension) {
        this.verandaDimension = verandaDimension;
    }

    public String getDrivingImage() {
        return drivingImage;
    }

    public void setDrivingImage(String drivingImage) {
        this.drivingImage = drivingImage;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", modelName='" + modelName + '\'' +
                ", owner='" + owner + '\'' +
                ", nature='" + nature + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", identificationCode='" + identificationCode + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", issueDate=" + issueDate +
                ", authorizedNumber=" + authorizedNumber +
                ", grossMass='" + grossMass + '\'' +
                ", qualityService='" + qualityService + '\'' +
                ", verandaDimension='" + verandaDimension + '\'' +
                ", drivingImage='" + drivingImage + '\'' +
                '}';
    }
}
