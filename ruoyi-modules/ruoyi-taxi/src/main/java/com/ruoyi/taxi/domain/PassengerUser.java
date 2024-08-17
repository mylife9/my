package com.ruoyi.taxi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 11:59
 */
@Data
public class PassengerUser {
    private Integer passengerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    private Date gmtModified;
    private String passengerPhone;
    private String passengerName;
    private Integer passengerGender;
    private Integer state;
    private String profilePhoto;
    private String code;

    private String passengerCard;

    private Integer passengerAge;


}
