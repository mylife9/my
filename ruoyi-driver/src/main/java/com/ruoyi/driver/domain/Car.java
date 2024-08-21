package com.ruoyi.driver.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 车辆对象 car
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public class Car extends BaseEntity
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

    /** 车牌颜色（1：蓝色，2：黄色，3：黑色，4：白色，5：绿色，9：其他） */
    @Excel(name = "车牌颜色", readConverterExp = "1=：蓝色，2：黄色，3：黑色，4：白色，5：绿色，9：其他")
    private String plateColor;

    /** 核定载客位 */
    @Excel(name = "核定载客位")
    private Integer seats;

    /** 车辆品牌 */
    @Excel(name = "车辆品牌")
    private String brand;

    /** 车辆品牌ID */
    @Excel(name = "车辆品牌ID")
    private Long brandId;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String model;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 车辆所有人 */
    @Excel(name = "车辆所有人")
    private String ownerName;

    /** 车辆颜色（1：白色，2：黑色） */
    @Excel(name = "车辆颜色", readConverterExp = "1=：白色，2：黑色")
    private String vehicleColor;

    /** 发动机号 */
    @Excel(name = "发动机号")
    private String engineId;

    /** 车辆vin识别代码 */
    @Excel(name = "车辆vin识别代码")
    private String vin;

    /** 车辆注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date certifyDateA;

    /** 燃料类型(1：汽油，2：柴油，3：天然气，4：液化气，5：电动，9：其他） */
    @Excel(name = "燃料类型(1：汽油，2：柴油，3：天然气，4：液化气，5：电动，9：其他）")
    private String fueType;

    /** 发动机排量（毫升） */
    @Excel(name = "发动机排量", readConverterExp = "毫=升")
    private String engineDisplace;

    /** 车辆运输证发证机构 */
    @Excel(name = "车辆运输证发证机构")
    private String transAgency;

    /** 车辆经验区域 */
    @Excel(name = "车辆经验区域")
    private String transArea;

    /** 车辆运输证有效期起 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆运输证有效期起", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transDateStart;

    /** 车辆运输证有效期止 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆运输证有效期止", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transDateEnd;

    /** 车辆初次登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆初次登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date certifyDateB;

    /** 车辆的检修状态(0：未检修，1：已检修，2：未知） */
    @Excel(name = "车辆的检修状态(0：未检修，1：已检修，2：未知）")
    private String fixState;

    /** 下次年检时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次年检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextFixDate;

    /** 年度审验状态（0：未年审，1：年审合格，2：年审不合格） */
    @Excel(name = "年度审验状态", readConverterExp = "0=：未年审，1：年审合格，2：年审不合格")
    private String checkState;

    /** 发票打印设备序列号 */
    @Excel(name = "发票打印设备序列号")
    private String feePrintId;

    /** 卫星定位装置品牌 */
    @Excel(name = "卫星定位装置品牌")
    private String gpsBrand;

    /** 卫星型号 */
    @Excel(name = "卫星型号")
    private String gpsModel;

    /** 卫星定位设备安装日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "卫星定位设备安装日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gpsInstallDate;

    /** 报备日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报备日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerDate;

    /** 服务类型：1：网络预约出租车，2：巡游出租车，3：私人小客车合乘 */
    @Excel(name = "服务类型：1：网络预约出租车，2：巡游出租车，3：私人小客车合乘")
    private Integer commercialType;

    /** 运价编码 关联计价规则 */
    @Excel(name = "运价编码 关联计价规则")
    private String fareType;

    /** 状态：0:有效，1：失效 */
    @Excel(name = "状态：0:有效，1：失效")
    private Integer state;

    /** 终端Id */
    @Excel(name = "终端Id")
    private String tid;

    /** 轨迹ID */
    @Excel(name = "轨迹ID")
    private String trid;

    /** 轨迹名称 */
    @Excel(name = "轨迹名称")
    private String trname;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    /** 车辆图片 */
    @Excel(name = "车辆图片")
    private String carPicture;

    /** 持有方 (0:平台，1：司机个人) */
    @Excel(name = "持有方 (0:平台，1：司机个人)")
    private Long owerStatus;


    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyinCar;

    @Excel(name = "品牌名")
    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setPinyinCar(String pinyinCar) {
        this.pinyinCar = pinyinCar;
    }

    public String getPinyinCar() {
        return pinyinCar;
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
    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
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
            .append("brandId", getBrandId())
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
            .append("owerStatus", getOwerStatus())
            .toString();
    }
}
