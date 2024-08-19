package com.ruoyi.taxi.domain;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName order_info
*/
@Data
public class OrderInfo implements Serializable {

    /**
    * 订单ID
    */
    @NotNull(message="[订单ID]不能为空")
    @ApiModelProperty("订单ID")
    private Long orderId;
    /**
    * 乘客ID
    */
    @NotNull(message="[乘客ID]不能为空")
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

    @ApiModelProperty("大约金额")
    private double aboutPrice;
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
    * 
    */
    @Size(max= 8,message="编码长度不能超过8")
    @ApiModelProperty("")
    @Length(max= 8,message="编码长度不能超过8")
    private String vehicleType;
    /**
    * 发起地行政区划代码
    */
    @ApiModelProperty("发起地行政区划代码")
    private String address;
    /**
    * 订单发起时间
    */
    @ApiModelProperty("订单发起时间")
    private LocalDateTime orderTime;
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
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("预计出发地点经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String depLongitude;
    /**
    * 预计出发地点纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("预计出发地点纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String depLatitude;
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
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("预计目的地经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String destLongitude;
    /**
    * 预计公里数
    */
    @ApiModelProperty("预计公里数")
    private BigDecimal expectDistance;
    /**
    * 预计目的地纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("预计目的地纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String destLatitude;
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
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("接单时车辆经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String receiveOrderCarLongitude;
    /**
    * 接单时车辆纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("接单时车辆纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String receiveOrderCarLatitude;
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
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("去接乘客时，司机的经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String toPickUpPassengerLongitude;
    /**
    * 去接乘客时，司机的纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("去接乘客时，司机的纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String toPickUpPassengerLatitude;
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
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("接到乘客，乘客上车经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String pickUpPassengerLongitude;
    /**
    * 接到乘客，乘客上车纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("接到乘客，乘客上车纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String pickUpPassengerLatitude;
    /**
    * 乘客下车时间
    */
    @ApiModelProperty("乘客下车时间")
    private Date passengerGetoffTime;
    /**
    * 乘客下车经度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("乘客下车经度")
    @Length(max= 16,message="编码长度不能超过16")
    private String passengerGetoffLongitude;
    /**
    * 乘客下车纬度
    */
    @Size(max= 16,message="编码长度不能超过16")
    @ApiModelProperty("乘客下车纬度")
    @Length(max= 16,message="编码长度不能超过16")
    private String passengerGetoffLatitude;
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
    @ApiModelProperty("支付金额")
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
    * 实际公里数
    */
    @ApiModelProperty("实际公里数")
    private BigDecimal realDistance;
    @ApiModelProperty("退单次数")
    private Integer chargebackNumber;
}
