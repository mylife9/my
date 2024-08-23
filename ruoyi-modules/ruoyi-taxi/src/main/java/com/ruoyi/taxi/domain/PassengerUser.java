package com.ruoyi.taxi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 11:59
 */
@Data
public class PassengerUser {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;
    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phone;
    private String passengerName;
    private String sex;
    private Integer passengerAge;
    private Integer state;
    private String profilePhoto;
    @NotBlank(message = "验证码不能为空")
    private String code;
    @NotBlank(message = "未获取到openid")
    private String openid;
    private Integer isRemind;
    private String idCardFront;
    private String idCardAfter;
    private String name;
    private String birthday;
    private String address;
    private Integer chargebackNumber;

    private String passengerCard;
    private double passengerPrice;

    private Integer reputation;

    private String passengerService;

    private Integer jurisdiction;


}
