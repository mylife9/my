package com.ruoyi.approve.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 司机入驻对象 driver_user
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
public class DriverUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 司机注册地行政区划代码 */
    @Excel(name = "司机注册地行政区划代码")
    private String address;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 司机手机号 */
    @Excel(name = "司机手机号")
    private String driverPhone;

    /** 性别 */
    @Excel(name = "性别")
    private String driverGender;

    /** 出生年月日 */
//    @JsonFormat(pattern = "yyyy-MM-dd"), width = 30, dateFormat = "yyyy-MM-dd"
    @Excel(name = "出生年月日")
    private String driverBirthday;

    /** 驾驶员民族 */
    @Excel(name = "驾驶员民族")
    private String driverNation;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String driverContactAddress;

    /** 机动车驾驶证号 */
    private String licenseId;

    /** 初次领取驾驶证日期 */
    private Date getDriverLicenseDate;

    /** 驾驶证有效期起 */
    private Date driverLicenseOn;

    /** 驾驶证有效期止 */
    private Date driverLicenseOff;

    /** 是否巡游出租汽车 */
    private Integer taxiDriver;

    /** 网络预约出租汽车驾驶员资格证号 */
    private String certificateNo;

    /** 网络预约出租汽车驾驶员发证机构 */
    private String networkCarIssueOrganization;

    /** 资格证发证日期 */
    private Date networkCarIssueDate;

    /** 初次领取资格证日期 */
    private Date getNetworkCarProofDate;

    /** 资格证有效起始日期 */
    private Date networkCarProofOn;

    /** 资格证有效截止日期 */
    private Date networkCarProofOff;

    /** 报备日期 */
    private Date registerDate;

    /** 服务类型 */
    private Integer commercialType;

    /** 驾驶员合同（协议）签署公司 */
    private String contractCompany;

    /** 合同（协议）有效期起 */
    private Date contractOn;

    /** 合同有效期止 */
    private Date contractOff;

    /** 司机状态 */
    @Excel(name = "司机状态")
    private Integer state;

    /** 创建时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** 身份证正面图片 */
    private String cardFrontImage;

    /** 身份证反面图片 */
    private String cardReverseImage;

    /** 驾驶证照片 */
    @Excel(name = "驾驶证照片")
    private String driverPhoto;

    /** 人脸照片 */
    @Excel(name = "人脸照片")
    private String facePhoto;

    public String getDriverBirthday() {
        return driverBirthday;
    }

    public void setDriverBirthday(String driverBirthday) {
        this.driverBirthday = driverBirthday;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setDriverPhone(String driverPhone) 
    {
        this.driverPhone = driverPhone;
    }

    public String getDriverPhone() 
    {
        return driverPhone;
    }
    public void setDriverGender(String driverGender)
    {
        this.driverGender = driverGender;
    }

    public String getDriverGender()
    {
        return driverGender;
    }

    public void setDriverNation(String driverNation) 
    {
        this.driverNation = driverNation;
    }

    public String getDriverNation() 
    {
        return driverNation;
    }
    public void setDriverContactAddress(String driverContactAddress) 
    {
        this.driverContactAddress = driverContactAddress;
    }

    public String getDriverContactAddress() 
    {
        return driverContactAddress;
    }
    public void setLicenseId(String licenseId) 
    {
        this.licenseId = licenseId;
    }

    public String getLicenseId() 
    {
        return licenseId;
    }
    public void setGetDriverLicenseDate(Date getDriverLicenseDate) 
    {
        this.getDriverLicenseDate = getDriverLicenseDate;
    }

    public Date getGetDriverLicenseDate() 
    {
        return getDriverLicenseDate;
    }
    public void setDriverLicenseOn(Date driverLicenseOn) 
    {
        this.driverLicenseOn = driverLicenseOn;
    }

    public Date getDriverLicenseOn() 
    {
        return driverLicenseOn;
    }
    public void setDriverLicenseOff(Date driverLicenseOff) 
    {
        this.driverLicenseOff = driverLicenseOff;
    }

    public Date getDriverLicenseOff() 
    {
        return driverLicenseOff;
    }
    public void setTaxiDriver(Integer taxiDriver) 
    {
        this.taxiDriver = taxiDriver;
    }

    public Integer getTaxiDriver() 
    {
        return taxiDriver;
    }
    public void setCertificateNo(String certificateNo) 
    {
        this.certificateNo = certificateNo;
    }

    public String getCertificateNo() 
    {
        return certificateNo;
    }
    public void setNetworkCarIssueOrganization(String networkCarIssueOrganization) 
    {
        this.networkCarIssueOrganization = networkCarIssueOrganization;
    }

    public String getNetworkCarIssueOrganization() 
    {
        return networkCarIssueOrganization;
    }
    public void setNetworkCarIssueDate(Date networkCarIssueDate) 
    {
        this.networkCarIssueDate = networkCarIssueDate;
    }

    public Date getNetworkCarIssueDate() 
    {
        return networkCarIssueDate;
    }
    public void setGetNetworkCarProofDate(Date getNetworkCarProofDate) 
    {
        this.getNetworkCarProofDate = getNetworkCarProofDate;
    }

    public Date getGetNetworkCarProofDate() 
    {
        return getNetworkCarProofDate;
    }
    public void setNetworkCarProofOn(Date networkCarProofOn) 
    {
        this.networkCarProofOn = networkCarProofOn;
    }

    public Date getNetworkCarProofOn() 
    {
        return networkCarProofOn;
    }
    public void setNetworkCarProofOff(Date networkCarProofOff) 
    {
        this.networkCarProofOff = networkCarProofOff;
    }

    public Date getNetworkCarProofOff() 
    {
        return networkCarProofOff;
    }
    public void setRegisterDate(Date registerDate) 
    {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() 
    {
        return registerDate;
    }
    public void setCommercialType(Integer commercialType) 
    {
        this.commercialType = commercialType;
    }

    public Integer getCommercialType() 
    {
        return commercialType;
    }
    public void setContractCompany(String contractCompany) 
    {
        this.contractCompany = contractCompany;
    }

    public String getContractCompany() 
    {
        return contractCompany;
    }
    public void setContractOn(Date contractOn) 
    {
        this.contractOn = contractOn;
    }

    public Date getContractOn() 
    {
        return contractOn;
    }
    public void setContractOff(Date contractOff) 
    {
        this.contractOff = contractOff;
    }

    public Date getContractOff() 
    {
        return contractOff;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setGmtCreate(Date gmtCreate) 
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() 
    {
        return gmtCreate;
    }
    public void setGmtModified(Date gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() 
    {
        return gmtModified;
    }
    public void setCardFrontImage(String cardFrontImage) 
    {
        this.cardFrontImage = cardFrontImage;
    }

    public String getCardFrontImage() 
    {
        return cardFrontImage;
    }
    public void setCardReverseImage(String cardReverseImage) 
    {
        this.cardReverseImage = cardReverseImage;
    }

    public String getCardReverseImage() 
    {
        return cardReverseImage;
    }
    public void setDriverPhoto(String driverPhoto) 
    {
        this.driverPhoto = driverPhoto;
    }

    public String getDriverPhoto() 
    {
        return driverPhoto;
    }
    public void setFacePhoto(String facePhoto) 
    {
        this.facePhoto = facePhoto;
    }

    public String getFacePhoto() 
    {
        return facePhoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("driverName", getDriverName())
            .append("driverPhone", getDriverPhone())
            .append("driverGender", getDriverGender())
            .append("driverBirthday", getDriverBirthday())
            .append("driverNation", getDriverNation())
            .append("driverContactAddress", getDriverContactAddress())
            .append("licenseId", getLicenseId())
            .append("getDriverLicenseDate", getGetDriverLicenseDate())
            .append("driverLicenseOn", getDriverLicenseOn())
            .append("driverLicenseOff", getDriverLicenseOff())
            .append("taxiDriver", getTaxiDriver())
            .append("certificateNo", getCertificateNo())
            .append("networkCarIssueOrganization", getNetworkCarIssueOrganization())
            .append("networkCarIssueDate", getNetworkCarIssueDate())
            .append("getNetworkCarProofDate", getGetNetworkCarProofDate())
            .append("networkCarProofOn", getNetworkCarProofOn())
            .append("networkCarProofOff", getNetworkCarProofOff())
            .append("registerDate", getRegisterDate())
            .append("commercialType", getCommercialType())
            .append("contractCompany", getContractCompany())
            .append("contractOn", getContractOn())
            .append("contractOff", getContractOff())
            .append("state", getState())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("cardFrontImage", getCardFrontImage())
            .append("cardReverseImage", getCardReverseImage())
            .append("driverPhoto", getDriverPhoto())
            .append("facePhoto", getFacePhoto())
            .toString();
    }
}
