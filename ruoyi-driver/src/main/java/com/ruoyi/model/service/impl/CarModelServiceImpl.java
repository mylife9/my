package com.ruoyi.model.service.impl;

import java.util.List;

import com.ruoyi.brand.domain.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.model.mapper.CarModelMapper;
import com.ruoyi.model.domain.CarModel;
import com.ruoyi.model.service.ICarModelService;

/**
 * 车型管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@Service
public class CarModelServiceImpl implements ICarModelService 
{
    @Autowired
    private CarModelMapper carModelMapper;

    /**
     * 查询车型管理
     * 
     * @param id 车型管理主键
     * @return 车型管理
     */
    @Override
    public CarModel selectCarModelById(Long id)
    {
        return carModelMapper.selectCarModelById(id);
    }

    /**
     * 查询车型管理列表
     * 
     * @param carModel 车型管理
     * @return 车型管理
     */
    @Override
    public List<CarModel> selectCarModelList(CarModel carModel)
    {
        return carModelMapper.selectCarModelList(carModel);
    }

    /**
     * 新增车型管理
     * 
     * @param carModel 车型管理
     * @return 结果
     */
    @Override
    public int insertCarModel(CarModel carModel)
    {

        return carModelMapper.insertCarModel(carModel);
    }

    /**
     * 修改车型管理
     * 
     * @param carModel 车型管理
     * @return 结果
     */
    @Override
    public int updateCarModel(CarModel carModel)
    {
        return carModelMapper.updateCarModel(carModel);
    }

    /**
     * 批量删除车型管理
     * 
     * @param ids 需要删除的车型管理主键
     * @return 结果
     */
    @Override
    public int deleteCarModelByIds(Long[] ids)
    {
        return carModelMapper.deleteCarModelByIds(ids);
    }

    /**
     * 删除车型管理信息
     * 
     * @param id 车型管理主键
     * @return 结果
     */
    @Override
    public int deleteCarModelById(Long id)
    {
        return carModelMapper.deleteCarModelById(id);
    }

    @Override
    public List<CarBrand> brandList() {
        return carModelMapper.brandList();
    }
}
