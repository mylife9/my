package com.ruoyi.taxi.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName order_info
 */

public class OrderInfo implements Serializable {
    /**
     * 订单ID
     */

    private Long id;

    /**
     * 乘客ID
     */
    private Long passengerId;

    /**
     * 乘客手机号
     */
    private String passenger_phone;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 司机手机号
     */
    private String driverPhone;

    /**
     * 车辆Id
     */
    private Long carId;

    /**
     * 
     */
    private String vehicleType;

    /**
     * 发起地行政区划代码
     */
    private String address;

    /**
     * 订单发起时间
     */
    private Date orderTime;

    /**
     * 预计用车时间
     */
    private Date departTime;

    /**
     * 预计出发地点详细地址
     */
    private String departure;

    /**
     * 预计出发地点经度
     */
    private String depLongitude;

    /**
     * 预计出发地点纬度
     */
    private String depLatitude;

    /**
     * 预计目的地
     */
    private String destination;

    /**
     * 预计目的地经度
     */
    private String destLongitude;

    /**
     * 预计目的地纬度
     */
    private String destLatitude;

    /**
     * 坐标加密标识
1:GCJ-02测绘局标准
2:WGS84 GPS标准
3:BD-09 百度标准
4:CGCS2000 北斗标准
0:其他
     */
    private Integer encrypt;

    /**
     * 运价类型编码
     */
    private String fareType;

    /**
     * 
     */
    private Integer fareVersion;

    /**
     * 接单时车辆经度
     */
    private String receiveOrderCarLongitude;

    /**
     * 接单时车辆纬度
     */
    private String receiveOrderCarLatitude;

    /**
     * 接单时间，派单成功时间
     */
    private Date receiveOrderTime;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车辆号牌
     */
    private String vehicleNo;

    /**
     * 司机去接乘客出发时间
     */
    private Date toPickUpPassengerTime;

    /**
     * 去接乘客时，司机的经度
     */
    private String toPickUpPassengerLongitude;

    /**
     * 去接乘客时，司机的纬度
     */
    private String toPickUpPassengerLatitude;

    /**
     * 去接乘客时，司机的地点
     */
    private String toPickUpPassengerAddress;

    /**
     * 司机到达上车点时间
     */
    private Date driverArrivedDepartureTime;

    /**
     * 接到乘客，乘客上车时间
     */
    private Date pickUpPassengerTime;

    /**
     * 接到乘客，乘客上车经度
     */
    private String pickUpPassengerLongitude;

    /**
     * 接到乘客，乘客上车纬度
     */
    private String pickUpPassengerLatitude;

    /**
     * 乘客下车时间
     */
    private Date passengerGetoffTime;

    /**
     * 乘客下车经度
     */
    private String passengerGetoffLongitude;

    /**
     * 乘客下车纬度
     */
    private String passengerGetoffLatitude;

    /**
     * 订单撤销时间
     */
    private Date cancelTime;

    /**
     * 撤销发起者：1:乘客
2:驾驶员
3:平台公司
     */
    private Integer cancelOperator;

    /**
     * 撤销类型代码
1:乘客提前撤销
2:驾驶员提前撤销
3:平台公司撤销
4;乘客违约撤销
5:驾驶员违约撤销
     */
    private Integer cancelTypeCode;

    /**
     * 载客里程（米）
     */
    private Long driveMile;

    /**
     * 载客时间(分)
     */
    private Long driveTime;

    /**
     * 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
     */
    private Integer orderStatus;

    /**
     * 
     */
    private Double price;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;




    /**
     * 订单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 订单ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 乘客ID
     */
    public Long getPassengerId() {
        return passengerId;
    }

    /**
     * 乘客ID
     */
    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * 乘客手机号
     */
    public String getPassengerPhone() {
        return passenger_phone;
    }

    /**
     * 乘客手机号
     */
    public void setPassengerPhone(String passengerPhone) {
        this.passenger_phone = passengerPhone;
    }

    /**
     * 司机ID
     */
    public Long getDriverId() {
        return driverId;
    }

    /**
     * 司机ID
     */
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    /**
     * 司机手机号
     */
    public String getDriverPhone() {
        return driverPhone;
    }

    /**
     * 司机手机号
     */
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    /**
     * 车辆Id
     */
    public Long getCarId() {
        return carId;
    }

    /**
     * 车辆Id
     */
    public void setCarId(Long carId) {
        this.carId = carId;
    }

    /**
     * 
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * 
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * 发起地行政区划代码
     */
    public String getAddress() {
        return address;
    }

    /**
     * 发起地行政区划代码
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 订单发起时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 订单发起时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 预计用车时间
     */
    public Date getDepartTime() {
        return departTime;
    }

    /**
     * 预计用车时间
     */
    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    /**
     * 预计出发地点详细地址
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * 预计出发地点详细地址
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * 预计出发地点经度
     */
    public String getDepLongitude() {
        return depLongitude;
    }

    /**
     * 预计出发地点经度
     */
    public void setDepLongitude(String depLongitude) {
        this.depLongitude = depLongitude;
    }

    /**
     * 预计出发地点纬度
     */
    public String getDepLatitude() {
        return depLatitude;
    }

    /**
     * 预计出发地点纬度
     */
    public void setDepLatitude(String depLatitude) {
        this.depLatitude = depLatitude;
    }

    /**
     * 预计目的地
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 预计目的地
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * 预计目的地经度
     */
    public String getDestLongitude() {
        return destLongitude;
    }

    /**
     * 预计目的地经度
     */
    public void setDestLongitude(String destLongitude) {
        this.destLongitude = destLongitude;
    }

    /**
     * 预计目的地纬度
     */
    public String getDestLatitude() {
        return destLatitude;
    }

    /**
     * 预计目的地纬度
     */
    public void setDestLatitude(String destLatitude) {
        this.destLatitude = destLatitude;
    }

    /**
     * 坐标加密标识
1:GCJ-02测绘局标准
2:WGS84 GPS标准
3:BD-09 百度标准
4:CGCS2000 北斗标准
0:其他
     */
    public Integer getEncrypt() {
        return encrypt;
    }

    /**
     * 坐标加密标识
1:GCJ-02测绘局标准
2:WGS84 GPS标准
3:BD-09 百度标准
4:CGCS2000 北斗标准
0:其他
     */
    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    /**
     * 运价类型编码
     */
    public String getFareType() {
        return fareType;
    }

    /**
     * 运价类型编码
     */
    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    /**
     * 
     */
    public Integer getFareVersion() {
        return fareVersion;
    }

    /**
     * 
     */
    public void setFareVersion(Integer fareVersion) {
        this.fareVersion = fareVersion;
    }

    /**
     * 接单时车辆经度
     */
    public String getReceiveOrderCarLongitude() {
        return receiveOrderCarLongitude;
    }

    /**
     * 接单时车辆经度
     */
    public void setReceiveOrderCarLongitude(String receiveOrderCarLongitude) {
        this.receiveOrderCarLongitude = receiveOrderCarLongitude;
    }

    /**
     * 接单时车辆纬度
     */
    public String getReceiveOrderCarLatitude() {
        return receiveOrderCarLatitude;
    }

    /**
     * 接单时车辆纬度
     */
    public void setReceiveOrderCarLatitude(String receiveOrderCarLatitude) {
        this.receiveOrderCarLatitude = receiveOrderCarLatitude;
    }

    /**
     * 接单时间，派单成功时间
     */
    public Date getReceiveOrderTime() {
        return receiveOrderTime;
    }

    /**
     * 接单时间，派单成功时间
     */
    public void setReceiveOrderTime(Date receiveOrderTime) {
        this.receiveOrderTime = receiveOrderTime;
    }

    /**
     * 机动车驾驶证号
     */
    public String getLicenseId() {
        return licenseId;
    }

    /**
     * 机动车驾驶证号
     */
    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    /**
     * 车辆号牌
     */
    public String getVehicleNo() {
        return vehicleNo;
    }

    /**
     * 车辆号牌
     */
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    /**
     * 司机去接乘客出发时间
     */
    public Date getToPickUpPassengerTime() {
        return toPickUpPassengerTime;
    }

    /**
     * 司机去接乘客出发时间
     */
    public void setToPickUpPassengerTime(Date toPickUpPassengerTime) {
        this.toPickUpPassengerTime = toPickUpPassengerTime;
    }

    /**
     * 去接乘客时，司机的经度
     */
    public String getToPickUpPassengerLongitude() {
        return toPickUpPassengerLongitude;
    }

    /**
     * 去接乘客时，司机的经度
     */
    public void setToPickUpPassengerLongitude(String toPickUpPassengerLongitude) {
        this.toPickUpPassengerLongitude = toPickUpPassengerLongitude;
    }

    /**
     * 去接乘客时，司机的纬度
     */
    public String getToPickUpPassengerLatitude() {
        return toPickUpPassengerLatitude;
    }

    /**
     * 去接乘客时，司机的纬度
     */
    public void setToPickUpPassengerLatitude(String toPickUpPassengerLatitude) {
        this.toPickUpPassengerLatitude = toPickUpPassengerLatitude;
    }

    /**
     * 去接乘客时，司机的地点
     */
    public String getToPickUpPassengerAddress() {
        return toPickUpPassengerAddress;
    }

    /**
     * 去接乘客时，司机的地点
     */
    public void setToPickUpPassengerAddress(String toPickUpPassengerAddress) {
        this.toPickUpPassengerAddress = toPickUpPassengerAddress;
    }

    /**
     * 司机到达上车点时间
     */
    public Date getDriverArrivedDepartureTime() {
        return driverArrivedDepartureTime;
    }

    /**
     * 司机到达上车点时间
     */
    public void setDriverArrivedDepartureTime(Date driverArrivedDepartureTime) {
        this.driverArrivedDepartureTime = driverArrivedDepartureTime;
    }

    /**
     * 接到乘客，乘客上车时间
     */
    public Date getPickUpPassengerTime() {
        return pickUpPassengerTime;
    }

    /**
     * 接到乘客，乘客上车时间
     */
    public void setPickUpPassengerTime(Date pickUpPassengerTime) {
        this.pickUpPassengerTime = pickUpPassengerTime;
    }

    /**
     * 接到乘客，乘客上车经度
     */
    public String getPickUpPassengerLongitude() {
        return pickUpPassengerLongitude;
    }

    /**
     * 接到乘客，乘客上车经度
     */
    public void setPickUpPassengerLongitude(String pickUpPassengerLongitude) {
        this.pickUpPassengerLongitude = pickUpPassengerLongitude;
    }

    /**
     * 接到乘客，乘客上车纬度
     */
    public String getPickUpPassengerLatitude() {
        return pickUpPassengerLatitude;
    }

    /**
     * 接到乘客，乘客上车纬度
     */
    public void setPickUpPassengerLatitude(String pickUpPassengerLatitude) {
        this.pickUpPassengerLatitude = pickUpPassengerLatitude;
    }

    /**
     * 乘客下车时间
     */
    public Date getPassengerGetoffTime() {
        return passengerGetoffTime;
    }

    /**
     * 乘客下车时间
     */
    public void setPassengerGetoffTime(Date passengerGetoffTime) {
        this.passengerGetoffTime = passengerGetoffTime;
    }

    /**
     * 乘客下车经度
     */
    public String getPassengerGetoffLongitude() {
        return passengerGetoffLongitude;
    }

    /**
     * 乘客下车经度
     */
    public void setPassengerGetoffLongitude(String passengerGetoffLongitude) {
        this.passengerGetoffLongitude = passengerGetoffLongitude;
    }

    /**
     * 乘客下车纬度
     */
    public String getPassengerGetoffLatitude() {
        return passengerGetoffLatitude;
    }

    /**
     * 乘客下车纬度
     */
    public void setPassengerGetoffLatitude(String passengerGetoffLatitude) {
        this.passengerGetoffLatitude = passengerGetoffLatitude;
    }

    /**
     * 订单撤销时间
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * 订单撤销时间
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * 撤销发起者：1:乘客
2:驾驶员
3:平台公司
     */
    public Integer getCancelOperator() {
        return cancelOperator;
    }

    /**
     * 撤销发起者：1:乘客
2:驾驶员
3:平台公司
     */
    public void setCancelOperator(Integer cancelOperator) {
        this.cancelOperator = cancelOperator;
    }

    /**
     * 撤销类型代码
1:乘客提前撤销
2:驾驶员提前撤销
3:平台公司撤销
4;乘客违约撤销
5:驾驶员违约撤销
     */
    public Integer getCancelTypeCode() {
        return cancelTypeCode;
    }

    /**
     * 撤销类型代码
1:乘客提前撤销
2:驾驶员提前撤销
3:平台公司撤销
4;乘客违约撤销
5:驾驶员违约撤销
     */
    public void setCancelTypeCode(Integer cancelTypeCode) {
        this.cancelTypeCode = cancelTypeCode;
    }

    /**
     * 载客里程（米）
     */
    public Long getDriveMile() {
        return driveMile;
    }

    /**
     * 载客里程（米）
     */
    public void setDriveMile(Long driveMile) {
        this.driveMile = driveMile;
    }

    /**
     * 载客时间(分)
     */
    public Long getDriveTime() {
        return driveTime;
    }

    /**
     * 载客时间(分)
     */
    public void setDriveTime(Long driveTime) {
        this.driveTime = driveTime;
    }

    /**
     * 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderInfo other = (OrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPassengerId() == null ? other.getPassengerId() == null : this.getPassengerId().equals(other.getPassengerId()))
            && (this.getPassengerPhone() == null ? other.getPassengerPhone() == null : this.getPassengerPhone().equals(other.getPassengerPhone()))
            && (this.getDriverId() == null ? other.getDriverId() == null : this.getDriverId().equals(other.getDriverId()))
            && (this.getDriverPhone() == null ? other.getDriverPhone() == null : this.getDriverPhone().equals(other.getDriverPhone()))
            && (this.getCarId() == null ? other.getCarId() == null : this.getCarId().equals(other.getCarId()))
            && (this.getVehicleType() == null ? other.getVehicleType() == null : this.getVehicleType().equals(other.getVehicleType()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getDepartTime() == null ? other.getDepartTime() == null : this.getDepartTime().equals(other.getDepartTime()))
            && (this.getDeparture() == null ? other.getDeparture() == null : this.getDeparture().equals(other.getDeparture()))
            && (this.getDepLongitude() == null ? other.getDepLongitude() == null : this.getDepLongitude().equals(other.getDepLongitude()))
            && (this.getDepLatitude() == null ? other.getDepLatitude() == null : this.getDepLatitude().equals(other.getDepLatitude()))
            && (this.getDestination() == null ? other.getDestination() == null : this.getDestination().equals(other.getDestination()))
            && (this.getDestLongitude() == null ? other.getDestLongitude() == null : this.getDestLongitude().equals(other.getDestLongitude()))
            && (this.getDestLatitude() == null ? other.getDestLatitude() == null : this.getDestLatitude().equals(other.getDestLatitude()))
            && (this.getEncrypt() == null ? other.getEncrypt() == null : this.getEncrypt().equals(other.getEncrypt()))
            && (this.getFareType() == null ? other.getFareType() == null : this.getFareType().equals(other.getFareType()))
            && (this.getFareVersion() == null ? other.getFareVersion() == null : this.getFareVersion().equals(other.getFareVersion()))
            && (this.getReceiveOrderCarLongitude() == null ? other.getReceiveOrderCarLongitude() == null : this.getReceiveOrderCarLongitude().equals(other.getReceiveOrderCarLongitude()))
            && (this.getReceiveOrderCarLatitude() == null ? other.getReceiveOrderCarLatitude() == null : this.getReceiveOrderCarLatitude().equals(other.getReceiveOrderCarLatitude()))
            && (this.getReceiveOrderTime() == null ? other.getReceiveOrderTime() == null : this.getReceiveOrderTime().equals(other.getReceiveOrderTime()))
            && (this.getLicenseId() == null ? other.getLicenseId() == null : this.getLicenseId().equals(other.getLicenseId()))
            && (this.getVehicleNo() == null ? other.getVehicleNo() == null : this.getVehicleNo().equals(other.getVehicleNo()))
            && (this.getToPickUpPassengerTime() == null ? other.getToPickUpPassengerTime() == null : this.getToPickUpPassengerTime().equals(other.getToPickUpPassengerTime()))
            && (this.getToPickUpPassengerLongitude() == null ? other.getToPickUpPassengerLongitude() == null : this.getToPickUpPassengerLongitude().equals(other.getToPickUpPassengerLongitude()))
            && (this.getToPickUpPassengerLatitude() == null ? other.getToPickUpPassengerLatitude() == null : this.getToPickUpPassengerLatitude().equals(other.getToPickUpPassengerLatitude()))
            && (this.getToPickUpPassengerAddress() == null ? other.getToPickUpPassengerAddress() == null : this.getToPickUpPassengerAddress().equals(other.getToPickUpPassengerAddress()))
            && (this.getDriverArrivedDepartureTime() == null ? other.getDriverArrivedDepartureTime() == null : this.getDriverArrivedDepartureTime().equals(other.getDriverArrivedDepartureTime()))
            && (this.getPickUpPassengerTime() == null ? other.getPickUpPassengerTime() == null : this.getPickUpPassengerTime().equals(other.getPickUpPassengerTime()))
            && (this.getPickUpPassengerLongitude() == null ? other.getPickUpPassengerLongitude() == null : this.getPickUpPassengerLongitude().equals(other.getPickUpPassengerLongitude()))
            && (this.getPickUpPassengerLatitude() == null ? other.getPickUpPassengerLatitude() == null : this.getPickUpPassengerLatitude().equals(other.getPickUpPassengerLatitude()))
            && (this.getPassengerGetoffTime() == null ? other.getPassengerGetoffTime() == null : this.getPassengerGetoffTime().equals(other.getPassengerGetoffTime()))
            && (this.getPassengerGetoffLongitude() == null ? other.getPassengerGetoffLongitude() == null : this.getPassengerGetoffLongitude().equals(other.getPassengerGetoffLongitude()))
            && (this.getPassengerGetoffLatitude() == null ? other.getPassengerGetoffLatitude() == null : this.getPassengerGetoffLatitude().equals(other.getPassengerGetoffLatitude()))
            && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
            && (this.getCancelOperator() == null ? other.getCancelOperator() == null : this.getCancelOperator().equals(other.getCancelOperator()))
            && (this.getCancelTypeCode() == null ? other.getCancelTypeCode() == null : this.getCancelTypeCode().equals(other.getCancelTypeCode()))
            && (this.getDriveMile() == null ? other.getDriveMile() == null : this.getDriveMile().equals(other.getDriveMile()))
            && (this.getDriveTime() == null ? other.getDriveTime() == null : this.getDriveTime().equals(other.getDriveTime()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPassengerId() == null) ? 0 : getPassengerId().hashCode());
        result = prime * result + ((getPassengerPhone() == null) ? 0 : getPassengerPhone().hashCode());
        result = prime * result + ((getDriverId() == null) ? 0 : getDriverId().hashCode());
        result = prime * result + ((getDriverPhone() == null) ? 0 : getDriverPhone().hashCode());
        result = prime * result + ((getCarId() == null) ? 0 : getCarId().hashCode());
        result = prime * result + ((getVehicleType() == null) ? 0 : getVehicleType().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getDepartTime() == null) ? 0 : getDepartTime().hashCode());
        result = prime * result + ((getDeparture() == null) ? 0 : getDeparture().hashCode());
        result = prime * result + ((getDepLongitude() == null) ? 0 : getDepLongitude().hashCode());
        result = prime * result + ((getDepLatitude() == null) ? 0 : getDepLatitude().hashCode());
        result = prime * result + ((getDestination() == null) ? 0 : getDestination().hashCode());
        result = prime * result + ((getDestLongitude() == null) ? 0 : getDestLongitude().hashCode());
        result = prime * result + ((getDestLatitude() == null) ? 0 : getDestLatitude().hashCode());
        result = prime * result + ((getEncrypt() == null) ? 0 : getEncrypt().hashCode());
        result = prime * result + ((getFareType() == null) ? 0 : getFareType().hashCode());
        result = prime * result + ((getFareVersion() == null) ? 0 : getFareVersion().hashCode());
        result = prime * result + ((getReceiveOrderCarLongitude() == null) ? 0 : getReceiveOrderCarLongitude().hashCode());
        result = prime * result + ((getReceiveOrderCarLatitude() == null) ? 0 : getReceiveOrderCarLatitude().hashCode());
        result = prime * result + ((getReceiveOrderTime() == null) ? 0 : getReceiveOrderTime().hashCode());
        result = prime * result + ((getLicenseId() == null) ? 0 : getLicenseId().hashCode());
        result = prime * result + ((getVehicleNo() == null) ? 0 : getVehicleNo().hashCode());
        result = prime * result + ((getToPickUpPassengerTime() == null) ? 0 : getToPickUpPassengerTime().hashCode());
        result = prime * result + ((getToPickUpPassengerLongitude() == null) ? 0 : getToPickUpPassengerLongitude().hashCode());
        result = prime * result + ((getToPickUpPassengerLatitude() == null) ? 0 : getToPickUpPassengerLatitude().hashCode());
        result = prime * result + ((getToPickUpPassengerAddress() == null) ? 0 : getToPickUpPassengerAddress().hashCode());
        result = prime * result + ((getDriverArrivedDepartureTime() == null) ? 0 : getDriverArrivedDepartureTime().hashCode());
        result = prime * result + ((getPickUpPassengerTime() == null) ? 0 : getPickUpPassengerTime().hashCode());
        result = prime * result + ((getPickUpPassengerLongitude() == null) ? 0 : getPickUpPassengerLongitude().hashCode());
        result = prime * result + ((getPickUpPassengerLatitude() == null) ? 0 : getPickUpPassengerLatitude().hashCode());
        result = prime * result + ((getPassengerGetoffTime() == null) ? 0 : getPassengerGetoffTime().hashCode());
        result = prime * result + ((getPassengerGetoffLongitude() == null) ? 0 : getPassengerGetoffLongitude().hashCode());
        result = prime * result + ((getPassengerGetoffLatitude() == null) ? 0 : getPassengerGetoffLatitude().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getCancelOperator() == null) ? 0 : getCancelOperator().hashCode());
        result = prime * result + ((getCancelTypeCode() == null) ? 0 : getCancelTypeCode().hashCode());
        result = prime * result + ((getDriveMile() == null) ? 0 : getDriveMile().hashCode());
        result = prime * result + ((getDriveTime() == null) ? 0 : getDriveTime().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", passengerId=").append(passengerId);
        sb.append(", passengerPhone=").append(passenger_phone);
        sb.append(", driverId=").append(driverId);
        sb.append(", driverPhone=").append(driverPhone);
        sb.append(", carId=").append(carId);
        sb.append(", vehicleType=").append(vehicleType);
        sb.append(", address=").append(address);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", departTime=").append(departTime);
        sb.append(", departure=").append(departure);
        sb.append(", depLongitude=").append(depLongitude);
        sb.append(", depLatitude=").append(depLatitude);
        sb.append(", destination=").append(destination);
        sb.append(", destLongitude=").append(destLongitude);
        sb.append(", destLatitude=").append(destLatitude);
        sb.append(", encrypt=").append(encrypt);
        sb.append(", fareType=").append(fareType);
        sb.append(", fareVersion=").append(fareVersion);
        sb.append(", receiveOrderCarLongitude=").append(receiveOrderCarLongitude);
        sb.append(", receiveOrderCarLatitude=").append(receiveOrderCarLatitude);
        sb.append(", receiveOrderTime=").append(receiveOrderTime);
        sb.append(", licenseId=").append(licenseId);
        sb.append(", vehicleNo=").append(vehicleNo);
        sb.append(", toPickUpPassengerTime=").append(toPickUpPassengerTime);
        sb.append(", toPickUpPassengerLongitude=").append(toPickUpPassengerLongitude);
        sb.append(", toPickUpPassengerLatitude=").append(toPickUpPassengerLatitude);
        sb.append(", toPickUpPassengerAddress=").append(toPickUpPassengerAddress);
        sb.append(", driverArrivedDepartureTime=").append(driverArrivedDepartureTime);
        sb.append(", pickUpPassengerTime=").append(pickUpPassengerTime);
        sb.append(", pickUpPassengerLongitude=").append(pickUpPassengerLongitude);
        sb.append(", pickUpPassengerLatitude=").append(pickUpPassengerLatitude);
        sb.append(", passengerGetoffTime=").append(passengerGetoffTime);
        sb.append(", passengerGetoffLongitude=").append(passengerGetoffLongitude);
        sb.append(", passengerGetoffLatitude=").append(passengerGetoffLatitude);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", cancelOperator=").append(cancelOperator);
        sb.append(", cancelTypeCode=").append(cancelTypeCode);
        sb.append(", driveMile=").append(driveMile);
        sb.append(", driveTime=").append(driveTime);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", price=").append(price);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}