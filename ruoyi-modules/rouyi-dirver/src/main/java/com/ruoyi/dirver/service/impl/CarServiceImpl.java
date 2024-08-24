package com.ruoyi.dirver.service.impl;

import com.ruoyi.dirver.domain.Car;
import com.ruoyi.dirver.mapper.CarMapper;
import com.ruoyi.dirver.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/21 12:48
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public Integer saveCar(Car car) {
        return carMapper.saveCar(car);
    }
}
