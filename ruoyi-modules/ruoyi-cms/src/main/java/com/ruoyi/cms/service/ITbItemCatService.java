package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.TbItemCat;

import java.util.List;

/**
 * 商品类目管理Service接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
public interface ITbItemCatService 
{
    /**
     * 查询商品类目管理
     * 
     * @param id 商品类目管理主键
     * @return 商品类目管理
     */
    public TbItemCat selectTbItemCatById(Long id);

    /**
     * 查询商品类目管理列表
     * 
     * @param tbItemCat 商品类目管理
     * @return 商品类目管理集合
     */
    public List<TbItemCat> selectTbItemCatList(TbItemCat tbItemCat);

    /**
     * 新增商品类目管理
     * 
     * @param tbItemCat 商品类目管理
     * @return 结果
     */
    public int insertTbItemCat(TbItemCat tbItemCat);

    /**
     * 修改商品类目管理
     * 
     * @param tbItemCat 商品类目管理
     * @return 结果
     */
    public int updateTbItemCat(TbItemCat tbItemCat);

    /**
     * 批量删除商品类目管理
     * 
     * @param ids 需要删除的商品类目管理主键集合
     * @return 结果
     */
    public int deleteTbItemCatByIds(Long[] ids);

    /**
     * 删除商品类目管理信息
     * 
     * @param id 商品类目管理主键
     * @return 结果
     */
    public int deleteTbItemCatById(Long id);

    Long getTemplateId(String name);
}
