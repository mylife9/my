package com.ruoyi.driver.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.driver.mapper.CarMapper;
import com.ruoyi.driver.domain.Car;
import com.ruoyi.driver.service.ICarService;

/**
 * 车辆Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class CarServiceImpl implements ICarService 
{
    @Autowired
    private CarMapper carMapper;

    /**
     * 查询车辆
     * 
     * @param id 车辆主键
     * @return 车辆
     */
    @Override
    public Car selectCarById(Long id)
    {
        return carMapper.selectCarById(id);
    }

    /**
     * 查询车辆列表
     * 
     * @param car 车辆
     * @return 车辆
     */
    @Override
    public List<Car> selectCarList(Car car)
    {
        return carMapper.selectCarList(car);
    }

    /**
     * 新增车辆
     * 
     * @param car 车辆
     * @return 结果
     */
    @Override
    public int insertCar(Car car)
    {

        return carMapper.insertCar(car);
    }

    /**
     * 修改车辆
     * 
     * @param car 车辆
     * @return 结果
     */
    @Override
    public int updateCar(Car car)
    {
        return carMapper.updateCar(car);
    }

    /**
     * 批量删除车辆
     * 
     * @param ids 需要删除的车辆主键
     * @return 结果
     */
    @Override
    public int deleteCarByIds(Long[] ids)
    {
        return carMapper.deleteCarByIds(ids);
    }

    /**
     * 删除车辆信息
     * 
     * @param id 车辆主键
     * @return 结果
     */
    @Override
    public int deleteCarById(Long id)
    {
        return carMapper.deleteCarById(id);
    }
}
