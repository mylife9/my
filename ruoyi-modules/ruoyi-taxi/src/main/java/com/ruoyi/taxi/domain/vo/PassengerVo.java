package com.ruoyi.taxi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
public class PassengerVo {

    @Schema(description = "乘客id")
    private Integer passengerId;

    @Schema(description = "司机Id")
    private Integer driverId;

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "预计出发地点经度")
    private Double depLongitude;

    @Schema(description = "预计出发地维度")
    private Double depLatitude;

    @Schema(description = "预计目的地经度")
    private Double destLongitude;

    @Schema(description = "预计目的地维度")
    private Double destLatitude;

    @Schema(description = "预计公里数")
    private Integer expectDistance;

    @Schema(description = "车辆类型1:新能源2:舒适型3:豪华型4:商务型")
    private Integer carType;

    @Schema(description = "预计金额")
    private Double aboutPrice;

    @Schema(description = "预计时长(分钟)")
    private Integer estimatedDuration;

    @Schema(description = "用车方式1:普通叫车2:半日租3:日租")
    private Integer vehicleType;

    @Schema(description = "预付金额")
    private Double advanceAmount;

    @Schema(description = "是否预约用车1:预约用车2:实时用车")
    private Integer isReserve;
}
