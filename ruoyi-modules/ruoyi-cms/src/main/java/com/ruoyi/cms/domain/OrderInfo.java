package com.ruoyi.cms.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息对象 order_info
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@TableName("order_info")
public class OrderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private String id;

    /** 乘客ID */
    @Excel(name = "乘客ID")
    private Long passengerId;

    /** 乘客手机号 */
    @Excel(name = "乘客手机号")
    private String passengerPhone;

    /** 司机ID */
    @Excel(name = "司机ID")
    private Long driverId;

    /** 司机手机号 */
    @Excel(name = "司机手机号")
    private String driverPhone;

    /** 车辆Id */
    @Excel(name = "车辆Id")
    private Long carId;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 发起地行政区划代码 */
    @Excel(name = "发起地行政区划代码")
    private String address;

    /** 订单发起时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单发起时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** 预计用车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计用车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date departTime;

    /** 预计出发地点详细地址 */
    @Excel(name = "预计出发地点详细地址")
    private String departure;

    /** 预计出发地点经度 */
    @Excel(name = "预计出发地点经度")
    private String depLongitude;

    /** 预计出发地点纬度 */
    @Excel(name = "预计出发地点纬度")
    private String depLatitude;

    /** 预计目的地 */
    @Excel(name = "预计目的地")
    private String destination;

    /** 预计目的地经度 */
    @Excel(name = "预计目的地经度")
    private String destLongitude;

    /** 预计目的地纬度 */
    @Excel(name = "预计目的地纬度")
    private String destLatitude;

    /** 坐标加密标识
1:GCJ-02测绘局标准
2:WGS84 GPS标准
3:BD-09 百度标准
4:CGCS2000 北斗标准
0:其他 */
    @Excel(name = "坐标加密标识")
    private Integer encrypt;

    /** 运价类型编码 */
    @Excel(name = "运价类型编码")
    private String fareType;

    /** 票价版本 */
    @Excel(name = "票价版本")
    private Integer fareVersion;

    /** 接单时车辆经度 */
    @Excel(name = "接单时车辆经度")
    private String receiveOrderCarLongitude;

    /** 接单时车辆纬度 */
    @Excel(name = "接单时车辆纬度")
    private String receiveOrderCarLatitude;

    /** 接单时间，派单成功时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单时间，派单成功时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveOrderTime;

    /** 机动车驾驶证号 */
    @Excel(name = "机动车驾驶证号")
    private String licenseId;

    /** 车辆号牌 */
    @Excel(name = "车辆号牌")
    private String vehicleNo;

    /** 司机去接乘客出发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "司机去接乘客出发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date toPickUpPassengerTime;

    /** 去接乘客时，司机的经度 */
    @Excel(name = "去接乘客时，司机的经度")
    private String toPickUpPassengerLongitude;

    /** 去接乘客时，司机的纬度 */
    @Excel(name = "去接乘客时，司机的纬度")
    private String toPickUpPassengerLatitude;

    /** 去接乘客时，司机的地点 */
    @Excel(name = "去接乘客时，司机的地点")
    private String toPickUpPassengerAddress;

    /** 司机到达上车点时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "司机到达上车点时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date driverArrivedDepartureTime;

    /** 接到乘客，乘客上车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接到乘客，乘客上车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pickUpPassengerTime;

    /** 接到乘客，乘客上车经度 */
    @Excel(name = "接到乘客，乘客上车经度")
    private String pickUpPassengerLongitude;

    /** 接到乘客，乘客上车纬度 */
    @Excel(name = "接到乘客，乘客上车纬度")
    private String pickUpPassengerLatitude;

    /** 乘客下车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "乘客下车时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date passengerGetoffTime;

    /** 乘客下车经度 */
    @Excel(name = "乘客下车经度")
    private String passengerGetoffLongitude;

    /** 乘客下车纬度 */
    @Excel(name = "乘客下车纬度")
    private String passengerGetoffLatitude;

    /** 订单撤销时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单撤销时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cancelTime;

    /** 撤销发起者：1:乘客
2:驾驶员
3:平台公司 */
    @Excel(name = "撤销发起者")
    private Integer cancelOperator;

    /** 撤销类型代码
1:乘客提前撤销
2:驾驶员提前撤销
3:平台公司撤销
4;乘客违约撤销
5:驾驶员违约撤销 */
    @Excel(name = "撤销类型代码")
    private Integer cancelTypeCode;

    /** 载客里程（米） */
    @Excel(name = "载客里程", readConverterExp = "米=")
    private Long driveMile;

    /** 载客时间(分) */
    @Excel(name = "载客时间(分)")
    private Long driveTime;

    /** 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消' */
    @Excel(name = "订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'")
    private Integer orderStatus;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal price;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDepLongitude() {
        return depLongitude;
    }

    public void setDepLongitude(String depLongitude) {
        this.depLongitude = depLongitude;
    }

    public String getDepLatitude() {
        return depLatitude;
    }

    public void setDepLatitude(String depLatitude) {
        this.depLatitude = depLatitude;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(String destLongitude) {
        this.destLongitude = destLongitude;
    }

    public String getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(String destLatitude) {
        this.destLatitude = destLatitude;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    public String getFareType() {
        return fareType;
    }

    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    public Integer getFareVersion() {
        return fareVersion;
    }

    public void setFareVersion(Integer fareVersion) {
        this.fareVersion = fareVersion;
    }

    public String getReceiveOrderCarLongitude() {
        return receiveOrderCarLongitude;
    }

    public void setReceiveOrderCarLongitude(String receiveOrderCarLongitude) {
        this.receiveOrderCarLongitude = receiveOrderCarLongitude;
    }

    public String getReceiveOrderCarLatitude() {
        return receiveOrderCarLatitude;
    }

    public void setReceiveOrderCarLatitude(String receiveOrderCarLatitude) {
        this.receiveOrderCarLatitude = receiveOrderCarLatitude;
    }

    public Date getReceiveOrderTime() {
        return receiveOrderTime;
    }

    public void setReceiveOrderTime(Date receiveOrderTime) {
        this.receiveOrderTime = receiveOrderTime;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Date getToPickUpPassengerTime() {
        return toPickUpPassengerTime;
    }

    public void setToPickUpPassengerTime(Date toPickUpPassengerTime) {
        this.toPickUpPassengerTime = toPickUpPassengerTime;
    }

    public String getToPickUpPassengerLongitude() {
        return toPickUpPassengerLongitude;
    }

    public void setToPickUpPassengerLongitude(String toPickUpPassengerLongitude) {
        this.toPickUpPassengerLongitude = toPickUpPassengerLongitude;
    }

    public String getToPickUpPassengerLatitude() {
        return toPickUpPassengerLatitude;
    }

    public void setToPickUpPassengerLatitude(String toPickUpPassengerLatitude) {
        this.toPickUpPassengerLatitude = toPickUpPassengerLatitude;
    }

    public String getToPickUpPassengerAddress() {
        return toPickUpPassengerAddress;
    }

    public void setToPickUpPassengerAddress(String toPickUpPassengerAddress) {
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

    public String getPickUpPassengerLongitude() {
        return pickUpPassengerLongitude;
    }

    public void setPickUpPassengerLongitude(String pickUpPassengerLongitude) {
        this.pickUpPassengerLongitude = pickUpPassengerLongitude;
    }

    public String getPickUpPassengerLatitude() {
        return pickUpPassengerLatitude;
    }

    public void setPickUpPassengerLatitude(String pickUpPassengerLatitude) {
        this.pickUpPassengerLatitude = pickUpPassengerLatitude;
    }

    public Date getPassengerGetoffTime() {
        return passengerGetoffTime;
    }

    public void setPassengerGetoffTime(Date passengerGetoffTime) {
        this.passengerGetoffTime = passengerGetoffTime;
    }

    public String getPassengerGetoffLongitude() {
        return passengerGetoffLongitude;
    }

    public void setPassengerGetoffLongitude(String passengerGetoffLongitude) {
        this.passengerGetoffLongitude = passengerGetoffLongitude;
    }

    public String getPassengerGetoffLatitude() {
        return passengerGetoffLatitude;
    }

    public void setPassengerGetoffLatitude(String passengerGetoffLatitude) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
