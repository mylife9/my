package com.ruoyi.dirver.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.Car;
import com.ruoyi.dirver.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ruoyi.common.core.web.domain.AjaxResult.error;
import static com.ruoyi.common.core.web.domain.AjaxResult.success;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/22 19:35
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 司机车辆信息添加
     * @param car
     * @return
     */
    @PostMapping("/saveCar")
    public AjaxResult saveCar(@RequestBody Car car){
        Integer integer = carService.saveCar(car);
        return integer>0?success():error();
    }
}
