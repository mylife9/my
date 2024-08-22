package com.ruoyi.approve.service;

import java.util.List;


import com.ruoyi.approve.domain.TbCar;

/**
 * 车辆管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
public interface ICarService 
{
    /**
     * 查询车辆管理
     * 
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    public TbCar selectCarById(Long id);

    /**
     * 查询车辆管理列表
     * 
     * @param car 车辆管理
     * @return 车辆管理集合
     */
    public List<TbCar> selectCarList(TbCar car);

    /**
     * 新增车辆管理
     * 
     * @param car 车辆管理
     * @return 结果
     */
    public int insertCar(TbCar car);

    /**
     * 修改车辆管理
     * 
     * @param car 车辆管理
     * @return 结果
     */
    public int updateCar(TbCar car);

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的车辆管理主键集合
     * @return 结果
     */
    public int deleteCarByIds(Long[] ids);

    /**
     * 删除车辆管理信息
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    public int deleteCarById(Long id);

    int updateCarStatus(TbCar car);
}
