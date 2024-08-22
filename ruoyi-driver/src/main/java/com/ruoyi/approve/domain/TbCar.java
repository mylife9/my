package com.ruoyi.approve.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 车辆管理对象 car
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
public class TbCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 车辆所在城市 */
    @Excel(name = "车辆所在城市")
    private String address;

    /** 车辆号牌 */
    @Excel(name = "车辆号牌")
    private String vehicleNo;

    /** 车牌颜色 */
    @Excel(name = "车牌颜色")
    private String plateColor;

    /** 核定载客位 */
    @Excel(name = "核定载客位")
    private Integer seats;

    /** 车辆厂牌 */
    private String brand;

    /** 车辆型号 */
    private String model;

    /** 车辆类型 */
    private String vehicleType;

    /** 车辆所有人 */
    @Excel(name = "车辆所有人")
    private String ownerName;

    /** 车辆颜色 */
    private String vehicleColor;

    /** 发动机号 */
    private String engineId;

    /** 汽车vin码 */
    @Excel(name = "汽车vin码")
    private String vin;

    /** 车辆注册日期 */
    private Date certifyDateA;

    /** 燃料类型 */
    private String fueType;

    /** 发动机排量（毫升） */
    private String engineDisplace;

    /** 车辆运输证发证机构 */
    private String transAgency;

    /** 车辆经验区域 */
    @Excel(name = "车辆经验区域")
    private String transArea;

    /** 车辆运输证有效期起 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆运输证有效期起", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transDateStart;

    /** 车辆运输证有效期止 */
    private Date transDateEnd;

    /** 车辆初次登记日期 */
    private Date certifyDateB;

    /** 车辆的检修状态 */
    private String fixState;

    /** 下次年检时间 */
    private Date nextFixDate;

    /** 年度审验状态 */
    private String checkState;

    /** 发票打印设备序列号 */
    private String feePrintId;

    /** 卫星定位装置品牌 */
    private String gpsBrand;

    /** 卫星型号 */
    private String gpsModel;

    /** 卫星定位设备安装日期 */
    private Date gpsInstallDate;

    /** 报备日期 */
    private Date registerDate;

    /** 服务类型 */
    private Integer commercialType;

    /** 运价编码 关联计价规则 */
    private String fareType;

    /** 状态 */
//            0:"待审批" 1:"通过",2:"驳回"
    private Integer state;

    /** 终端Id */
    private String tid;

    /** 轨迹ID */
    private String trid;

    /** 轨迹名称 */
    private String trname;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** 车辆图片 */
    private String carPicture;

    /** 品牌名 */
    @Excel(name = "品牌名")
    private String brandName;

    /** 品牌id */
    @Excel(name = "品牌id")
    private Long brandId;

    /** 车辆拼音 */
    private String pinyinCar;

    /** 品牌拼音 */
    private String pinyinBrand;


    /** 持有方状态 */
    @Excel(name = "持有方状态")
    private Long owerStatus;

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
    public void setVehicleNo(String vehicleNo) 
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() 
    {
        return vehicleNo;
    }
    public void setPlateColor(String plateColor) 
    {
        this.plateColor = plateColor;
    }

    public String getPlateColor() 
    {
        return plateColor;
    }
    public void setSeats(Integer seats) 
    {
        this.seats = seats;
    }

    public Integer getSeats() 
    {
        return seats;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setOwnerName(String ownerName) 
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName() 
    {
        return ownerName;
    }
    public void setVehicleColor(String vehicleColor) 
    {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleColor() 
    {
        return vehicleColor;
    }
    public void setEngineId(String engineId) 
    {
        this.engineId = engineId;
    }

    public String getEngineId() 
    {
        return engineId;
    }
    public void setVin(String vin) 
    {
        this.vin = vin;
    }

    public String getVin() 
    {
        return vin;
    }
    public void setCertifyDateA(Date certifyDateA) 
    {
        this.certifyDateA = certifyDateA;
    }

    public Date getCertifyDateA() 
    {
        return certifyDateA;
    }
    public void setFueType(String fueType) 
    {
        this.fueType = fueType;
    }

    public String getFueType() 
    {
        return fueType;
    }
    public void setEngineDisplace(String engineDisplace) 
    {
        this.engineDisplace = engineDisplace;
    }

    public String getEngineDisplace() 
    {
        return engineDisplace;
    }
    public void setTransAgency(String transAgency) 
    {
        this.transAgency = transAgency;
    }

    public String getTransAgency() 
    {
        return transAgency;
    }
    public void setTransArea(String transArea) 
    {
        this.transArea = transArea;
    }

    public String getTransArea() 
    {
        return transArea;
    }
    public void setTransDateStart(Date transDateStart) 
    {
        this.transDateStart = transDateStart;
    }

    public Date getTransDateStart() 
    {
        return transDateStart;
    }
    public void setTransDateEnd(Date transDateEnd) 
    {
        this.transDateEnd = transDateEnd;
    }

    public Date getTransDateEnd() 
    {
        return transDateEnd;
    }
    public void setCertifyDateB(Date certifyDateB) 
    {
        this.certifyDateB = certifyDateB;
    }

    public Date getCertifyDateB() 
    {
        return certifyDateB;
    }
    public void setFixState(String fixState) 
    {
        this.fixState = fixState;
    }

    public String getFixState() 
    {
        return fixState;
    }
    public void setNextFixDate(Date nextFixDate) 
    {
        this.nextFixDate = nextFixDate;
    }

    public Date getNextFixDate() 
    {
        return nextFixDate;
    }
    public void setCheckState(String checkState) 
    {
        this.checkState = checkState;
    }

    public String getCheckState() 
    {
        return checkState;
    }
    public void setFeePrintId(String feePrintId) 
    {
        this.feePrintId = feePrintId;
    }

    public String getFeePrintId() 
    {
        return feePrintId;
    }
    public void setGpsBrand(String gpsBrand) 
    {
        this.gpsBrand = gpsBrand;
    }

    public String getGpsBrand() 
    {
        return gpsBrand;
    }
    public void setGpsModel(String gpsModel) 
    {
        this.gpsModel = gpsModel;
    }

    public String getGpsModel() 
    {
        return gpsModel;
    }
    public void setGpsInstallDate(Date gpsInstallDate) 
    {
        this.gpsInstallDate = gpsInstallDate;
    }

    public Date getGpsInstallDate() 
    {
        return gpsInstallDate;
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
    public void setFareType(String fareType) 
    {
        this.fareType = fareType;
    }

    public String getFareType() 
    {
        return fareType;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }
    public void setTid(String tid) 
    {
        this.tid = tid;
    }

    public String getTid() 
    {
        return tid;
    }
    public void setTrid(String trid) 
    {
        this.trid = trid;
    }

    public String getTrid() 
    {
        return trid;
    }
    public void setTrname(String trname) 
    {
        this.trname = trname;
    }

    public String getTrname() 
    {
        return trname;
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
    public void setCarPicture(String carPicture) 
    {
        this.carPicture = carPicture;
    }

    public String getCarPicture() 
    {
        return carPicture;
    }
    public void setBrandName(String brandName) 
    {
        this.brandName = brandName;
    }

    public String getBrandName() 
    {
        return brandName;
    }
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }
    public void setPinyinCar(String pinyinCar) 
    {
        this.pinyinCar = pinyinCar;
    }

    public String getPinyinCar() 
    {
        return pinyinCar;
    }
    public void setPinyinBrand(String pinyinBrand) 
    {
        this.pinyinBrand = pinyinBrand;
    }

    public String getPinyinBrand() 
    {
        return pinyinBrand;
    }
    public void setOwerStatus(Long owerStatus) 
    {
        this.owerStatus = owerStatus;
    }

    public Long getOwerStatus() 
    {
        return owerStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("address", getAddress())
            .append("vehicleNo", getVehicleNo())
            .append("plateColor", getPlateColor())
            .append("seats", getSeats())
            .append("brand", getBrand())
            .append("model", getModel())
            .append("vehicleType", getVehicleType())
            .append("ownerName", getOwnerName())
            .append("vehicleColor", getVehicleColor())
            .append("engineId", getEngineId())
            .append("vin", getVin())
            .append("certifyDateA", getCertifyDateA())
            .append("fueType", getFueType())
            .append("engineDisplace", getEngineDisplace())
            .append("transAgency", getTransAgency())
            .append("transArea", getTransArea())
            .append("transDateStart", getTransDateStart())
            .append("transDateEnd", getTransDateEnd())
            .append("certifyDateB", getCertifyDateB())
            .append("fixState", getFixState())
            .append("nextFixDate", getNextFixDate())
            .append("checkState", getCheckState())
            .append("feePrintId", getFeePrintId())
            .append("gpsBrand", getGpsBrand())
            .append("gpsModel", getGpsModel())
            .append("gpsInstallDate", getGpsInstallDate())
            .append("registerDate", getRegisterDate())
            .append("commercialType", getCommercialType())
            .append("fareType", getFareType())
            .append("state", getState())
            .append("tid", getTid())
            .append("trid", getTrid())
            .append("trname", getTrname())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("carPicture", getCarPicture())
            .append("brandName", getBrandName())
            .append("brandId", getBrandId())
            .append("pinyinCar", getPinyinCar())
            .append("pinyinBrand", getPinyinBrand())
            .append("owerStatus", getOwerStatus())
            .toString();
    }
}
