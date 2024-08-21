package com.ruoyi.model.service;

import java.util.List;

import com.ruoyi.brand.domain.CarBrand;
import com.ruoyi.model.domain.CarModel;

/**
 * 车型管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public interface ICarModelService 
{
    /**
     * 查询车型管理
     * 
     * @param id 车型管理主键
     * @return 车型管理
     */
    public CarModel selectCarModelById(Long id);

    /**
     * 查询车型管理列表
     * 
     * @param carModel 车型管理
     * @return 车型管理集合
     */
    public List<CarModel> selectCarModelList(CarModel carModel);

    /**
     * 新增车型管理
     * 
     * @param carModel 车型管理
     * @return 结果
     */
    public int insertCarModel(CarModel carModel);

    /**
     * 修改车型管理
     * 
     * @param carModel 车型管理
     * @return 结果
     */
    public int updateCarModel(CarModel carModel);

    /**
     * 批量删除车型管理
     * 
     * @param ids 需要删除的车型管理主键集合
     * @return 结果
     */
    public int deleteCarModelByIds(Long[] ids);

    /**
     * 删除车型管理信息
     * 
     * @param id 车型管理主键
     * @return 结果
     */
    public int deleteCarModelById(Long id);

    List<CarBrand> brandList();
}
