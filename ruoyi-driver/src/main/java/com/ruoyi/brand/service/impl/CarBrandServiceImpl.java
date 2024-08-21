package com.ruoyi.brand.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.brand.mapper.CarBrandMapper;
import com.ruoyi.brand.domain.CarBrand;
import com.ruoyi.brand.service.ICarBrandService;

/**
 * 品牌管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-18
 */
@Service
public class CarBrandServiceImpl implements ICarBrandService 
{
    @Autowired
    private CarBrandMapper carBrandMapper;

    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    @Override
    public CarBrand selectCarBrandById(Long id)
    {
        return carBrandMapper.selectCarBrandById(id);
    }

    /**
     * 查询品牌管理列表
     * 
     * @param carBrand 品牌管理
     * @return 品牌管理
     */
    @Override
    public List<CarBrand> selectCarBrandList(CarBrand carBrand)
    {
        return carBrandMapper.selectCarBrandList(carBrand);
    }

    /**
     * 新增品牌管理
     * 
     * @param carBrand 品牌管理
     * @return 结果
     */
    @Override
    public int insertCarBrand(CarBrand carBrand)
    {
        return carBrandMapper.insertCarBrand(carBrand);
    }

    /**
     * 修改品牌管理
     * 
     * @param carBrand 品牌管理
     * @return 结果
     */
    @Override
    public int updateCarBrand(CarBrand carBrand)
    {
        return carBrandMapper.updateCarBrand(carBrand);
    }

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteCarBrandByIds(Long[] ids)
    {
        return carBrandMapper.deleteCarBrandByIds(ids);
    }

    /**
     * 删除品牌管理信息
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteCarBrandById(Long id)
    {
        return carBrandMapper.deleteCarBrandById(id);
    }

    @Override
    public List<CarBrand> selectBrandOne() {
        return carBrandMapper.selectBrandOne();
    }
}
