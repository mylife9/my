package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.TbSpecification;
import com.ruoyi.cms.domain.TbSpecificationOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规格管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-21
 */
@Mapper
public interface TbSpecificationMapper
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
     * 删除规格管理
     * 
     * @param id 规格管理主键
     * @return 结果
     */
    public int deleteTbSpecificationById(Long id);

    /**
     * 批量删除规格管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSpecificationByIds(Long[] ids);

    void insertTbSpecificationOption(TbSpecificationOption option);

    void deleteTbSpecificationOptionByIds(Long[] ids);

    List<TbSpecificationOption> selectTbSpecificationOptionsBySpecId(Long id);

    void deleteTbSpecificationOptionByIdsAndSpecId(TbSpecification tbSpecification);

    void updateTbSpecificationOptionById(TbSpecificationOption option);
}
