package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbBrand;
import com.ruoyi.cms.mapper.TbBrandMapper;
import com.ruoyi.cms.service.ITbBrandService;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-20
 */
@Service
public class TbBrandServiceImpl implements ITbBrandService 
{
    @Autowired
    private TbBrandMapper tbBrandMapper;

    /**
     * 查询品牌管理
     * 
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    @Override
    public TbBrand selectTbBrandById(Long id)
    {
        return tbBrandMapper.selectTbBrandById(id);
    }

    /**
     * 查询品牌管理列表
     * 
     * @param tbBrand 品牌管理
     * @return 品牌管理
     */
    @Override
    public List<TbBrand> selectTbBrandList(TbBrand tbBrand)
    {
        return tbBrandMapper.selectTbBrandList(tbBrand);
    }

    /**
     * 新增品牌管理
     * 
     * @param tbBrand 品牌管理
     * @return 结果
     */
    @Override
    public int insertTbBrand(TbBrand tbBrand)
    {
        tbBrand.setCreateTime(DateUtils.getNowDate());
        return tbBrandMapper.insertTbBrand(tbBrand);
    }

    /**
     * 修改品牌管理
     * 
     * @param tbBrand 品牌管理
     * @return 结果
     */
    @Override
    public int updateTbBrand(TbBrand tbBrand)
    {
        tbBrand.setUpdateTime(DateUtils.getNowDate());
        return tbBrandMapper.updateTbBrand(tbBrand);
    }

    /**
     * 批量删除品牌管理
     * 
     * @param ids 需要删除的品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteTbBrandByIds(Long[] ids)
    {
        return tbBrandMapper.deleteTbBrandByIds(ids);
    }

    /**
     * 删除品牌管理信息
     * 
     * @param id 品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteTbBrandById(Long id)
    {
        return tbBrandMapper.deleteTbBrandById(id);
    }

    @Override
    public TbBrand selectTbBrandByBrandName(String brandName) {
        return tbBrandMapper.selectTbBrandByBrandName(brandName);
    }
}
