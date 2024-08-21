package com.ruoyi.dirver.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.Driver;

public interface DriverService {
    String sendCode(String driverPhone);

    Integer RegDriver(Driver driver);
}
