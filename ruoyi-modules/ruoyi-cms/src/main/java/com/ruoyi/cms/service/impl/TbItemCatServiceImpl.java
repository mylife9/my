package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbItemCat;
import com.ruoyi.cms.mapper.TbItemCatMapper;
import com.ruoyi.cms.service.ITbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-25
 */
@Service
public class TbItemCatServiceImpl implements ITbItemCatService 
{
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    /**
     * 查询商品类目管理
     * 
     * @param id 商品类目管理主键
     * @return 商品类目管理
     */
    @Override
    public TbItemCat selectTbItemCatById(Long id)
    {
        return tbItemCatMapper.selectTbItemCatById(id);
    }

    /**
     * 查询商品类目管理列表
     * 
     * @param tbItemCat 商品类目管理
     * @return 商品类目管理
     */
    @Override
    public List<TbItemCat> selectTbItemCatList(TbItemCat tbItemCat)
    {
        return tbItemCatMapper.selectTbItemCatList(tbItemCat);
    }

    /**
     * 新增商品类目管理
     * 
     * @param tbItemCat 商品类目管理
     * @return 结果
     */
    @Override
    public int insertTbItemCat(TbItemCat tbItemCat)
    {
        return tbItemCatMapper.insertTbItemCat(tbItemCat);
    }

    /**
     * 修改商品类目管理
     * 
     * @param tbItemCat 商品类目管理
     * @return 结果
     */
    @Override
    public int updateTbItemCat(TbItemCat tbItemCat)
    {
        return tbItemCatMapper.updateTbItemCat(tbItemCat);
    }

    /**
     * 批量删除商品类目管理
     * 
     * @param ids 需要删除的商品类目管理主键
     * @return 结果
     */
    @Override
    public int deleteTbItemCatByIds(Long[] ids)
    {
        int i = 0;
        for(Long id : ids){
//            i += tbItemCatMapper.deleteTbItemCatById(id);
            this.deleteTbItemCat(id);
        }
        return i;
    }

    private void deleteTbItemCat(Long id) {
        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setParentId(id);
        List<TbItemCat> children = tbItemCatMapper.selectTbItemCatList(tbItemCat);

        if(children!=null && children.size()>0){
            for(TbItemCat child : children){
                this.deleteTbItemCat(child.getId());
            }
        }
        tbItemCatMapper.deleteTbItemCatById(id);
    }

    /**
     * 删除商品类目管理信息
     * 
     * @param id 商品类目管理主键
     * @return 结果
     */
    @Override
    public int deleteTbItemCatById(Long id)
    {
        return tbItemCatMapper.deleteTbItemCatById(id);
    }

    @Override
    public Long getTemplateId(String name) {
        return tbItemCatMapper.getTemplateId(name);
    }
}
