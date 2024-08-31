package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.TbBrand;

import java.util.List;

/**
 * 品牌管理Service接口
 * 
 * @author ruoyi
 * @date 2024-06-20
 */
public interface ITbBrandService 
{
    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    public TbBrand selectTbBrandById(Long id);

    /**
     * 查询品牌管理列表
     * 
     * @param tbBrand 品牌管理
     * @return 品牌管理集合
     */
    public List<TbBrand> selectTbBrandList(TbBrand tbBrand);

    /**
     * 新增品牌管理
     * 
     * @param tbBrand 品牌管理
     * @return 结果
     */
    public int insertTbBrand(TbBrand tbBrand);

    /**
     * 修改品牌管理
     * 
     * @param tbBrand 品牌管理
     * @return 结果
     */
    public int updateTbBrand(TbBrand tbBrand);

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的品牌管理主键集合
     * @return 结果
     */
    public int deleteTbBrandByIds(Long[] ids);

    /**
     * 删除品牌管理信息
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    public int deleteTbBrandById(Long id);

    TbBrand selectTbBrandByBrandName(String brandName);
}
