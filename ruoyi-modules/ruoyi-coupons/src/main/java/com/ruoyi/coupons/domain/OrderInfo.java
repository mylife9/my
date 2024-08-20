package com.ruoyi.coupons.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 订单表
* @TableName order_info
*/
public class OrderInfo extends BaseEntity {

    /**
    * 订单ID
    */
    @NotNull(message="[订单ID]不能为空")
    @ApiModelProperty("订单ID")
    private Long id;
    /**
    * 乘客ID
    */
    @ApiModelProperty("乘客ID")
    private Long passengerId;
    /**
    * 乘客手机号
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("乘客手机号")
    @Length(max= 16,message="编码长度不能超过16")
    private String passengerPhone;
    /**
    * 司机ID
    */
    @ApiModelProperty("司机ID")
    private Long driverId;
    /**
    * 司机手机号
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("司机手机号")
    @Length(max= 16,message="编码长度不能超过16")
    private String driverPhone;
    /**
    * 车辆Id
    */
    @ApiModelProperty("车辆Id")
    private Long carId;
    /**
    * 大约金额
    */
    @ApiModelProperty("大约金额")
    private BigDecimal aboutPrice;
    /**
    * 发起地行政区划代码
    */
    @ApiModelProperty("发起地行政区划代码")
    private String address;
    /**
    * 订单发起时间
    */
    @ApiModelProperty("订单发起时间")
    private Date orderTime;
    /**
    * 预计用车时间
    */
    @ApiModelProperty("预计用车时间")
    private Date departTime;
    /**
    * 预计出发地点详细地址
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("预计出发地点详细地址")
    @Length(max= 128,message="编码长度不能超过128")
    private String departure;
    /**
    * 预计出发地点经度
    */
    @ApiModelProperty("预计出发地点经度")
    private Double depLongitude;
    /**
    * 预计出发地点纬度
    */
    @ApiModelProperty("预计出发地点纬度")
    private Double depLatitude;
    /**
    * 预计目的地
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("预计目的地")
    @Length(max= 128,message="编码长度不能超过128")
    private String destination;
    /**
    * 预计目的地经度
    */
    @ApiModelProperty("预计目的地经度")
    private Double destLongitude;
    /**
    * 预计目的地纬度
    */
    @ApiModelProperty("预计目的地纬度")
    private Double destLatitude;
    /**
    * 坐标加密标识1:GCJ-02测绘局标准2:WGS84 GPS标准3:BD-09 百度标准4:CGCS2000 北斗标准0:其他
    */
    @ApiModelProperty("坐标加密标识 1:GCJ-02测绘局标准 2:WGS84 GPS标准 3:BD-09 百度标准 4:CGCS2000 北斗标准 0:其他")
    private Integer encrypt;
    /**
    * 运价类型编码
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("运价类型编码")
    @Length(max= 16,message="编码长度不能超过16")
    private String fareType;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer fareVersion;
    /**
    * 接单时车辆经度
    */
    @ApiModelProperty("接单时车辆经度")
    private Double receiveOrderCarLongitude;
    /**
    * 接单时车辆纬度
    */
    @ApiModelProperty("接单时车辆纬度")
    private Double receiveOrderCarLatitude;
    /**
    * 接单时间，派单成功时间
    */
    @ApiModelProperty("接单时间，派单成功时间")
    private Date receiveOrderTime;
    /**
    * 机动车驾驶证号
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("机动车驾驶证号")
    @Length(max= 128,message="编码长度不能超过128")
    private String licenseId;
    /**
    * 车辆号牌
    */
    @Size(max= 8,message="编码长度不能超过8")
    @ApiModelProperty("车辆号牌")
    @Length(max= 8,message="编码长度不能超过8")
    private String vehicleNo;
    /**
    * 司机去接乘客出发时间
    */
    @ApiModelProperty("司机去接乘客出发时间")
    private Date toPickUpPassengerTime;
    /**
    * 去接乘客时，司机的经度
    */
    @ApiModelProperty("去接乘客时，司机的经度")
    private Double toPickUpPassengerLongitude;
    /**
    * 去接乘客时，司机的纬度
    */
    @ApiModelProperty("去接乘客时，司机的纬度")
    private Double toPickUpPassengerLatitude;
    /**
    * 去接乘客时，司机的地点
    */
    @Size(max= 128,message="编码长度不能超过128")
    @ApiModelProperty("去接乘客时，司机的地点")
    @Length(max= 128,message="编码长度不能超过128")
    private String toPickUpPassengerAddress;
    /**
    * 司机到达上车点时间
    */
    @ApiModelProperty("司机到达上车点时间")
    private Date driverArrivedDepartureTime;
    /**
    * 接到乘客，乘客上车时间
    */
    @ApiModelProperty("接到乘客，乘客上车时间")
    private Date pickUpPassengerTime;
    /**
    * 接到乘客，乘客上车经度
    */
    @ApiModelProperty("接到乘客，乘客上车经度")
    private Double pickUpPassengerLongitude;
    /**
    * 接到乘客，乘客上车纬度
    */
    @ApiModelProperty("接到乘客，乘客上车纬度")
    private Double pickUpPassengerLatitude;
    /**
    * 乘客下车时间
    */
    @ApiModelProperty("乘客下车时间")
    private Date passengerGetoffTime;
    /**
    * 乘客下车经度
    */
    @ApiModelProperty("乘客下车经度")
    private Double passengerGetoffLongitude;
    /**
    * 乘客下车纬度
    */
    @ApiModelProperty("乘客下车纬度")
    private Double passengerGetoffLatitude;
    /**
    * 订单撤销时间
    */
    @ApiModelProperty("订单撤销时间")
    private Date cancelTime;
    /**
    * 撤销发起者：1:乘客2:驾驶员3:平台公司
    */
    @ApiModelProperty("撤销发起者：1:乘客 2:驾驶员 3:平台公司")
    private Integer cancelOperator;
    /**
    * 撤销类型代码1:乘客提前撤销2:驾驶员提前撤销3:平台公司撤销4;乘客违约撤销5:驾驶员违约撤销
    */
    @ApiModelProperty("撤销类型代码 1:乘客提前撤销 2:驾驶员提前撤销 3:平台公司撤销 4;乘客违约撤销 5:驾驶员违约撤销")
    private Integer cancelTypeCode;
    /**
    * 载客里程（米）
    */
    @ApiModelProperty("载客里程（米）")
    private Long driveMile;
    /**
    * 载客时间(分)
    */
    @ApiModelProperty("载客时间(分)")
    private Long driveTime;
    /**
    * 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
    */
    @ApiModelProperty("订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'")
    private Integer orderStatus;
    /**
    * 
    */
    @ApiModelProperty("")
    private Double price;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date gmtCreate;
    /**
    * 修改时间
    */
    @ApiModelProperty("修改时间")
    private Date gmtModified;
    /**
    * 车辆类型1:新能源2:舒适型3:豪华型4:商务型
    */
    @ApiModelProperty("车辆类型1:新能源2:舒适型3:豪华型4:商务型")
    private Integer carType;
    /**
    * 预计公里数
    */
    @ApiModelProperty("预计公里数")
    private Double expectDistance;
    /**
    * 预计时长(分钟)
    */
    @ApiModelProperty("预计时长(分钟)")
    private Integer estimatedDuration;
    /**
    * 用车方式1:普通叫车2:半日租3:日租
    */
    @ApiModelProperty("用车方式1:普通叫车2:半日租3:日租")
    private Integer vehicleType;
    /**
    * 预付金额
    */
    @ApiModelProperty("预付金额")
    private Double advanceAmount;
    /**
    * 是否预约用车1:预约2:实时订单
    */
    @ApiModelProperty("是否预约用车1:预约2:实时订单")
    private Integer isReserve;


    public @NotNull(message = "[订单ID]不能为空") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "[订单ID]不能为空") Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public @Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(@Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public @Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(@Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public BigDecimal getAboutPrice() {
        return aboutPrice;
    }

    public void setAboutPrice(BigDecimal aboutPrice) {
        this.aboutPrice = aboutPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public @Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String getDeparture() {
        return departure;
    }

    public void setDeparture(@Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String departure) {
        this.departure = departure;
    }

    public Double getDepLongitude() {
        return depLongitude;
    }

    public void setDepLongitude(Double depLongitude) {
        this.depLongitude = depLongitude;
    }

    public Double getDepLatitude() {
        return depLatitude;
    }

    public void setDepLatitude(Double depLatitude) {
        this.depLatitude = depLatitude;
    }

    public @Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String getDestination() {
        return destination;
    }

    public void setDestination(@Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String destination) {
        this.destination = destination;
    }

    public Double getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(Double destLongitude) {
        this.destLongitude = destLongitude;
    }

    public Double getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(Double destLatitude) {
        this.destLatitude = destLatitude;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    public @Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String getFareType() {
        return fareType;
    }

    public void setFareType(@Size(max = 16, message = "编码长度不能超过16") @Length(max = 16, message = "编码长度不能超过16") String fareType) {
        this.fareType = fareType;
    }

    public Integer getFareVersion() {
        return fareVersion;
    }

    public void setFareVersion(Integer fareVersion) {
        this.fareVersion = fareVersion;
    }

    public Double getReceiveOrderCarLongitude() {
        return receiveOrderCarLongitude;
    }

    public void setReceiveOrderCarLongitude(Double receiveOrderCarLongitude) {
        this.receiveOrderCarLongitude = receiveOrderCarLongitude;
    }

    public Double getReceiveOrderCarLatitude() {
        return receiveOrderCarLatitude;
    }

    public void setReceiveOrderCarLatitude(Double receiveOrderCarLatitude) {
        this.receiveOrderCarLatitude = receiveOrderCarLatitude;
    }

    public Date getReceiveOrderTime() {
        return receiveOrderTime;
    }

    public void setReceiveOrderTime(Date receiveOrderTime) {
        this.receiveOrderTime = receiveOrderTime;
    }

    public @Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(@Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String licenseId) {
        this.licenseId = licenseId;
    }

    public @Size(max = 8, message = "编码长度不能超过8") @Length(max = 8, message = "编码长度不能超过8") String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(@Size(max = 8, message = "编码长度不能超过8") @Length(max = 8, message = "编码长度不能超过8") String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Date getToPickUpPassengerTime() {
        return toPickUpPassengerTime;
    }

    public void setToPickUpPassengerTime(Date toPickUpPassengerTime) {
        this.toPickUpPassengerTime = toPickUpPassengerTime;
    }

    public Double getToPickUpPassengerLongitude() {
        return toPickUpPassengerLongitude;
    }

    public void setToPickUpPassengerLongitude(Double toPickUpPassengerLongitude) {
        this.toPickUpPassengerLongitude = toPickUpPassengerLongitude;
    }

    public Double getToPickUpPassengerLatitude() {
        return toPickUpPassengerLatitude;
    }

    public void setToPickUpPassengerLatitude(Double toPickUpPassengerLatitude) {
        this.toPickUpPassengerLatitude = toPickUpPassengerLatitude;
    }

    public @Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String getToPickUpPassengerAddress() {
        return toPickUpPassengerAddress;
    }

    public void setToPickUpPassengerAddress(@Size(max = 128, message = "编码长度不能超过128") @Length(max = 128, message = "编码长度不能超过128") String toPickUpPassengerAddress) {
        this.toPickUpPassengerAddress = toPickUpPassengerAddress;
    }

    public Date getDriverArrivedDepartureTime() {
        return driverArrivedDepartureTime;
    }

    public void setDriverArrivedDepartureTime(Date driverArrivedDepartureTime) {
        this.driverArrivedDepartureTime = driverArrivedDepartureTime;
    }

    public Date getPickUpPassengerTime() {
        return pickUpPassengerTime;
    }

    public void setPickUpPassengerTime(Date pickUpPassengerTime) {
        this.pickUpPassengerTime = pickUpPassengerTime;
    }

    public Double getPickUpPassengerLongitude() {
        return pickUpPassengerLongitude;
    }

    public void setPickUpPassengerLongitude(Double pickUpPassengerLongitude) {
        this.pickUpPassengerLongitude = pickUpPassengerLongitude;
    }

    public Double getPickUpPassengerLatitude() {
        return pickUpPassengerLatitude;
    }

    public void setPickUpPassengerLatitude(Double pickUpPassengerLatitude) {
        this.pickUpPassengerLatitude = pickUpPassengerLatitude;
    }

    public Date getPassengerGetoffTime() {
        return passengerGetoffTime;
    }

    public void setPassengerGetoffTime(Date passengerGetoffTime) {
        this.passengerGetoffTime = passengerGetoffTime;
    }

    public Double getPassengerGetoffLongitude() {
        return passengerGetoffLongitude;
    }

    public void setPassengerGetoffLongitude(Double passengerGetoffLongitude) {
        this.passengerGetoffLongitude = passengerGetoffLongitude;
    }

    public Double getPassengerGetoffLatitude() {
        return passengerGetoffLatitude;
    }

    public void setPassengerGetoffLatitude(Double passengerGetoffLatitude) {
        this.passengerGetoffLatitude = passengerGetoffLatitude;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getCancelOperator() {
        return cancelOperator;
    }

    public void setCancelOperator(Integer cancelOperator) {
        this.cancelOperator = cancelOperator;
    }

    public Integer getCancelTypeCode() {
        return cancelTypeCode;
    }

    public void setCancelTypeCode(Integer cancelTypeCode) {
        this.cancelTypeCode = cancelTypeCode;
    }

    public Long getDriveMile() {
        return driveMile;
    }

    public void setDriveMile(Long driveMile) {
        this.driveMile = driveMile;
    }

    public Long getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(Long driveTime) {
        this.driveTime = driveTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Double getExpectDistance() {
        return expectDistance;
    }

    public void setExpectDistance(Double expectDistance) {
        this.expectDistance = expectDistance;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Integer getIsReserve() {
        return isReserve;
    }

    public void setIsReserve(Integer isReserve) {
        this.isReserve = isReserve;
    }
}
