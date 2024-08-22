package com.ruoyi.approve.mapper;

import java.util.List;
import com.ruoyi.approve.domain.TbCar;

/**
 * 车辆管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
public interface CarApproveMapper
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
     * 删除车辆管理
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    public int deleteCarById(Long id);

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarByIds(Long[] ids);

    int updateCarStatus(TbCar car);
}
