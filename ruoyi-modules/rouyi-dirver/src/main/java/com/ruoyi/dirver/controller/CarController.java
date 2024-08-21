package com.ruoyi.dirver.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.Car;
import com.ruoyi.dirver.service.CarService;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ruoyi.common.core.web.domain.AjaxResult.error;
import static com.ruoyi.common.core.web.domain.AjaxResult.success;
import static org.bouncycastle.asn1.cmc.CMCStatus.success;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/21 12:49
 */
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/saveCar")
    public AjaxResult saveCar(@RequestBody Car car){
        Integer integer = carService.saveCar(car);
        return integer>0?success():error();
    }


}
