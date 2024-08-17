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

    @Schema(description = "订单id")
    private Integer orderId;

    @Schema(description = "预计出发地点经度")
    private String depLongitude;

    @Schema(description = "预计出发地维度")
    private String depLatitude;

    @Schema(description = "预计目的地经度")
    private String destLongitude;

    @Schema(description = "预计目的地维度")
    private String destLatitude;

    @Schema(description = "预计公里数")
    private BigDecimal expectDistance;
}
