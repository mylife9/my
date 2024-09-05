package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbTypeTemplate;

import java.util.List;

/**
 * 模版管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-24
 */
public interface TbTypeTemplateMapper 
{
    /**
     * 查询模版管理
     * 
     * @param id 模版管理主键
     * @return 模版管理
     */
    public TbTypeTemplate selectTbTypeTemplateById(Long id);

    /**
     * 查询模版管理列表
     * 
     * @param tbTypeTemplate 模版管理
     * @return 模版管理集合
     */
    public List<TbTypeTemplate> selectTbTypeTemplateList(TbTypeTemplate tbTypeTemplate);

    /**
     * 新增模版管理
     * 
     * @param tbTypeTemplate 模版管理
     * @return 结果
     */
    public int insertTbTypeTemplate(TbTypeTemplate tbTypeTemplate);

    /**
     * 修改模版管理
     * 
     * @param tbTypeTemplate 模版管理
     * @return 结果
     */
    public int updateTbTypeTemplate(TbTypeTemplate tbTypeTemplate);

    /**
     * 删除模版管理
     * 
     * @param id 模版管理主键
     * @return 结果
     */
    public int deleteTbTypeTemplateById(Long id);

    /**
     * 批量删除模版管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbTypeTemplateByIds(Long[] ids);
}