package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbItemCat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品类目管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Mapper
public interface TbItemCatMapper 
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
     * 删除商品类目管理
     * 
     * @param id 商品类目管理主键
     * @return 结果
     */
    public int deleteTbItemCatById(Long id);

    /**
     * 批量删除商品类目管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbItemCatByIds(Long[] ids);

    @Select("select type_id from tb_item_cat where name=#{name}")
    Long getTemplateId(String name);
}
