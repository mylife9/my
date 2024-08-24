package com.ruoyi.brand.service;

import java.util.List;
import com.ruoyi.brand.domain.CarBrand;

/**
 * 品牌管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-18
 */
public interface ICarBrandService 
{
    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    public CarBrand selectCarBrandById(Long id);

    /**
     * 查询品牌管理列表
     * 
     * @param carBrand 品牌管理
     * @return 品牌管理集合
     */
    public List<CarBrand> selectCarBrandList(CarBrand carBrand);

    /**
     * 新增品牌管理
     * 
     * @param carBrand 品牌管理
     * @return 结果
     */
    public int insertCarBrand(CarBrand carBrand);

    /**
     * 修改品牌管理
     * 
     * @param carBrand 品牌管理
     * @return 结果
     */
    public int updateCarBrand(CarBrand carBrand);

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的品牌管理主键集合
     * @return 结果
     */
    public int deleteCarBrandByIds(Long[] ids);

    /**
     * 删除品牌管理信息
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    public int deleteCarBrandById(Long id);

    List<CarBrand> selectBrandOne();
}
