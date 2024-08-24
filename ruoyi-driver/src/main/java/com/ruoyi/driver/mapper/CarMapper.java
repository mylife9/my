package com.ruoyi.driver.mapper;

import java.util.List;
import com.ruoyi.driver.domain.Car;

/**
 * 车辆Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public interface CarMapper 
{
    /**
     * 查询车辆
     * 
     * @param id 车辆主键
     * @return 车辆
     */
    public Car selectCarById(Long id);


    /**
     * 查询车辆列表
     * 
     * @param car 车辆
     * @return 车辆集合
     */

    public List<Car> selectCarList(Car car);

    /**
     * 新增车辆
     * 
     * @param car 车辆
     * @return 结果
     */
    public int insertCar(Car car);

    /**
     * 修改车辆
     * 
     * @param car 车辆
     * @return 结果
     */
    public int updateCar(Car car);

    /**
     * 删除车辆
     * 
     * @param id 车辆主键
     * @return 结果
     */
    public int deleteCarById(Long id);

    /**
     * 批量删除车辆
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarByIds(Long[] ids);
}
