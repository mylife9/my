package com.ruoyi.driver.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 行驶证管理对象 driving_license
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@TableName("driving_license")
public class License extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行驶证主键 */
    private Long id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String modelName;

    /** 车辆持有人 */
    @Excel(name = "车辆持有人")
    private String owner;

    //
    //            0:"待审批" 1:"通过",2:"驳回"




    /** 性质 */
    @Excel(name = "性质")
    private String nature;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String modelNumber;

    /** 车辆识别代码 */
    @Excel(name = "车辆识别代码")
    private String identificationCode;

    /** 发动机号码 */
    @Excel(name = "发动机号码")
    private String engineNumber;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationDate;

    /** 发证日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发证日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

    /** 核定人数 */
    @Excel(name = "核定人数")
    private Long authorizedNumber;

    /** 总质量 */
    @Excel(name = "总质量")
    private String grossMass;

    /** 整备质量 */
    @Excel(name = "整备质量")
    private String qualityService;

    /** 外廊尺寸 */
    @Excel(name = "外廊尺寸")
    private String verandaDimension;

    /** 行驶证图片 */
    @Excel(name = "行驶证图片")
    private String drivingImage;


    @Excel(name = "司机状态")
    private Integer state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setModelName(String modelName) 
    {
        this.modelName = modelName;
    }

    public String getModelName() 
    {
        return modelName;
    }
    public void setOwner(String owner) 
    {
        this.owner = owner;
    }

    public String getOwner() 
    {
        return owner;
    }
    public void setNature(String nature) 
    {
        this.nature = nature;
    }

    public String getNature() 
    {
        return nature;
    }
    public void setModelNumber(String modelNumber) 
    {
        this.modelNumber = modelNumber;
    }

    public String getModelNumber() 
    {
        return modelNumber;
    }
    public void setIdentificationCode(String identificationCode) 
    {
        this.identificationCode = identificationCode;
    }

    public String getIdentificationCode() 
    {
        return identificationCode;
    }
    public void setEngineNumber(String engineNumber) 
    {
        this.engineNumber = engineNumber;
    }

    public String getEngineNumber() 
    {
        return engineNumber;
    }
    public void setRegistrationDate(Date registrationDate) 
    {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() 
    {
        return registrationDate;
    }
    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() 
    {
        return issueDate;
    }
    public void setAuthorizedNumber(Long authorizedNumber) 
    {
        this.authorizedNumber = authorizedNumber;
    }

    public Long getAuthorizedNumber() 
    {
        return authorizedNumber;
    }
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public void setGrossMass(String grossMass)
    {
        this.grossMass = grossMass;
    }

    public String getGrossMass() 
    {
        return grossMass;
    }
    public void setQualityService(String qualityService) 
    {
        this.qualityService = qualityService;
    }

    public String getQualityService() 
    {
        return qualityService;
    }
    public void setVerandaDimension(String verandaDimension) 
    {
        this.verandaDimension = verandaDimension;
    }

    public String getVerandaDimension() 
    {
        return verandaDimension;
    }
    public void setDrivingImage(String drivingImage) 
    {
        this.drivingImage = drivingImage;
    }

    public String getDrivingImage() 
    {
        return drivingImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("licensePlate", getLicensePlate())
            .append("modelName", getModelName())
            .append("owner", getOwner())
            .append("nature", getNature())
            .append("modelNumber", getModelNumber())
            .append("identificationCode", getIdentificationCode())
            .append("engineNumber", getEngineNumber())
            .append("registrationDate", getRegistrationDate())
            .append("issueDate", getIssueDate())
            .append("authorizedNumber", getAuthorizedNumber())
            .append("grossMass", getGrossMass())
            .append("qualityService", getQualityService())
            .append("verandaDimension", getVerandaDimension())
            .append("drivingImage", getDrivingImage())
            .append("state", getState())
            .toString();
    }
}
