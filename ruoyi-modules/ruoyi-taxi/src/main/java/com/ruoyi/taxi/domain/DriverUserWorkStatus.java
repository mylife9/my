package com.ruoyi.taxi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.annotation.Excels;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class DriverUserWorkStatus {
    /**
     * 司机工作id
     * **/
    private Long id;

    /**
     * 司机id
     * **/
    private Long driverId;

    /**
     * 收车：0；出车：1，暂停：2，接单中：3
     * **/
    @Excel(name = "收车：0；出车：1，暂停：2，接单中：3")
    private Integer workStatus;

    /**
     * 创建时间
     * **/
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     * **/
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 是否开启回家模式1:是2:不是
     * **/
    private Integer isHeadingHome;

    /**
     * 司机当前位置经度
     * **/
    private Double currentLongitude;

    /**
     * 司机当前位置纬度
     * **/
    @Excel(name = "司机当前位置纬度")
    private Double currentLatitude;

    /**
     * 司机家庭地址经度
     * **/
    private Double addrLongitude;

    /**
     * 司机家庭地址纬度
     * **/
    private Double addrLatitude;

    /**
     *是否有预约1:有2:没有
     * **/
    private Integer isReserve;

    /**
     * 预约时间
     * **/
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date reserveTime;
}
