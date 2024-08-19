package com.ruoyi.taxi.domain;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class DriverUserWorkStatus {
    private Integer id;

    private Integer driverId;

    private Integer workStatus;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isHeadingHome;

    private Double currentLongitude;

    private Double currentLatitude;

    private Double addrLongitude;

    private Double addrLatitude;
}
