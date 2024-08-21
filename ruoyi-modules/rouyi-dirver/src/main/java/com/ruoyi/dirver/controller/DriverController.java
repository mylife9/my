package com.ruoyi.dirver.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.Driver;
import com.ruoyi.dirver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.common.core.web.domain.AjaxResult.error;
import static com.ruoyi.common.core.web.domain.AjaxResult.success;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/20 20:23
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @PostMapping("/sendCode/{driverPhone}")
    public AjaxResult sendCode(@PathVariable String driverPhone){
        String code = driverService.sendCode(driverPhone);
        return success("验证码发送成功"+code);
    }

    @PostMapping("/RegDriver")
    public AjaxResult RegDriver(@RequestBody Driver driver){
        Integer integer = driverService.RegDriver(driver);
        return integer>0?success():error();
    }
}
