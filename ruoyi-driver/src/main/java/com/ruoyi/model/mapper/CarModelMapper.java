package com.ruoyi.model.mapper;

import java.util.List;

import com.ruoyi.brand.domain.CarBrand;
import com.ruoyi.model.domain.CarModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车型管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@Mapper
public interface CarModelMapper 
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
     * 删除车型管理
     * 
     * @param id 车型管理主键
     * @return 结果
     */
    public int deleteCarModelById(Long id);

    /**
     * 批量删除车型管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarModelByIds(Long[] ids);

    List<CarBrand> brandList();
}
