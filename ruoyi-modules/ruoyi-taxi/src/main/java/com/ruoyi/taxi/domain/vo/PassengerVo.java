package com.ruoyi.taxi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @program: rouyitaxi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 18:25
 */
@Data
public class PassengerVo implements Serializable {

    @Schema(description = "乘客id")
    private String openid;

    @Schema(description = "乘客手机号")
    private String passengerPhone;

    @Schema(description = "司机Id")
    private Long driverId;

    @Schema(description = "司机手机号")
    private Long driverPhone;

    @Schema(description = "订单id")
    private String orderId;

    @NotNull(message = "出发地不能为空")
    @Pattern(regexp ="^[-+]?((180(\\.\\d{1,6})?)|((1[0-7]\\d)|([1-9]\\d?))(\\.\\d{1,6})?)$")
    @Schema(description = "预计出发地点经度")
    private Double depLongitude;

    @NotNull(message = "出发地不能为空")
    @Pattern(regexp = "^[-+]?((90(\\.\\d{1,6})?)|([1-8]?\\d(\\.\\d{1,6})?))$")
    @Schema(description = "预计出发地维度")
    private Double depLatitude;

    @Schema(description = "预计出发地点详细地址")
    private String departure;

    @NotNull(message = "目的地不能为空")
    @Pattern(regexp ="^[-+]?((180(\\.\\d{1,6})?)|((1[0-7]\\d)|([1-9]\\d?))(\\.\\d{1,6})?)$")
    @Schema(description = "预计目的地经度")
    private Double destLongitude;

    @NotNull(message = "目的地不能为空")
    @Pattern(regexp = "^[-+]?((90(\\.\\d{1,6})?)|([1-8]?\\d(\\.\\d{1,6})?))$")
    @Schema(description = "预计目的地维度")
    private Double destLatitude;

    @Schema(description = "预计目的地")
    private String destination;

    @Schema(description = "预计公里数")
    private Double expectDistance;

    @Schema(description = "车辆类型1:新能源2:舒适型3:豪华型4:商务型")
    private Integer carType;

    @Schema(description = "预计金额")
    private Double aboutPrice;

    @Schema(description = "预计时长(分钟)")
    private Double estimatedDuration;

    @Schema(description = "用车方式1:普通叫车2:半日租3:日租")
    private Integer vehicleType;

    @Schema(description = "预付金额")
    private Double advanceAmount;

    @Schema(description = "是否预约用车1:预约用车2:实时用车")
    private Integer isReserve;

    @Schema(description = "预约时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date departTime;

    @Schema(description = "订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'")
    private Integer orderStatus;


}
