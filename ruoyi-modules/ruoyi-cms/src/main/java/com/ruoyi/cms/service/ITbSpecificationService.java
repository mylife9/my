package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.TbSpecification;
import com.ruoyi.cms.domain.TbSpecificationOption;

import java.util.List;

/**
 * 规格管理Service接口
 * 
 * @author ruoyi
 * @date 2024-06-21
 */
public interface ITbSpecificationService
{
    /**
     * 查询规格管理
     * 
     * @param id 规格管理主键
     * @return 规格管理
     */
    public TbSpecification selectTbSpecificationById(Long id);

    /**
     * 查询规格管理列表
     * 
     * @param tbSpecification 规格管理
     * @return 规格管理集合
     */
    public List<TbSpecification> selectTbSpecificationList(TbSpecification tbSpecification);

    /**
     * 新增规格管理
     * 
     * @param tbSpecification 规格管理
     * @return 结果
     */
    public int insertTbSpecification(TbSpecification tbSpecification);

    /**
     * 修改规格管理
     * 
     * @param tbSpecification 规格管理
     * @return 结果
     */
    public int updateTbSpecification(TbSpecification tbSpecification);

    /**
     * 批量删除规格管理
     * 
     * @param ids 需要删除的规格管理主键集合
     * @return 结果
     */
    public int deleteTbSpecificationByIds(Long[] ids);

    /**
     * 删除规格管理信息
     * 
     * @param id 规格管理主键
     * @return 结果
     */
    public int deleteTbSpecificationById(Long id);

    List<TbSpecificationOption> selectTbSpecificationOptionsById(Long id);

}
