package com.ruoyi.taxi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-22 18:19
 */
@Data
@ToString
public class PassengerIdcard {
    private Integer id;
    private String name;
    private String sex;
    private String birthday;
    private Integer age;
    private String address;
    private String idNumber;
    @NotBlank(message = "请上传身份证正面照片")
    private String idCardFront;
    @NotBlank(message = "请上传身份证反面照片")
    private String idCardAfter;
    @NotBlank(message = "未检测到用户登录，请先登录")
    private String openid;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
