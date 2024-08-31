package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbTypeTemplate;
import com.ruoyi.cms.mapper.TbTypeTemplateMapper;
import com.ruoyi.cms.service.ITbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模版管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
@Service
public class TbTypeTemplateServiceImpl implements ITbTypeTemplateService 
{
    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    /**
     * 查询模版管理
     * 
     * @param id 模版管理主键
     * @return 模版管理
     */
    @Override
    public TbTypeTemplate selectTbTypeTemplateById(Long id)
    {
        return tbTypeTemplateMapper.selectTbTypeTemplateById(id);
    }

    /**
     * 查询模版管理列表
     * 
     * @param tbTypeTemplate 模版管理
     * @return 模版管理
     */
    @Override
    public List<TbTypeTemplate> selectTbTypeTemplateList(TbTypeTemplate tbTypeTemplate)
    {
        return tbTypeTemplateMapper.selectTbTypeTemplateList(tbTypeTemplate);
    }

    /**
     * 新增模版管理
     * 
     * @param tbTypeTemplate 模版管理
     * @return 结果
     */
    @Override
    public int insertTbTypeTemplate(TbTypeTemplate tbTypeTemplate)
    {
        return tbTypeTemplateMapper.insertTbTypeTemplate(tbTypeTemplate);
    }

    /**
     * 修改模版管理
     * 
     * @param tbTypeTemplate 模版管理
     * @return 结果
     */
    @Override
    public int updateTbTypeTemplate(TbTypeTemplate tbTypeTemplate)
    {
        return tbTypeTemplateMapper.updateTbTypeTemplate(tbTypeTemplate);
    }

    /**
     * 批量删除模版管理
     * 
     * @param ids 需要删除的模版管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTypeTemplateByIds(Long[] ids)
    {
        return tbTypeTemplateMapper.deleteTbTypeTemplateByIds(ids);
    }

    /**
     * 删除模版管理信息
     * 
     * @param id 模版管理主键
     * @return 结果
     */
    @Override
    public int deleteTbTypeTemplateById(Long id)
    {
        return tbTypeTemplateMapper.deleteTbTypeTemplateById(id);
    }
}
