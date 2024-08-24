package com.ruoyi.points.mapper;


import com.ruoyi.points.domain.ModelPoints;

import java.util.List;

/**
 * 车型分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
public interface ModelPointsMapper 
{
    /**
     * 查询车型分
     * 
     * @param id 车型分主键
     * @return 车型分
     */
    public ModelPoints selectModelPointsById(Long id);

    /**
     * 查询车型分列表
     * 
     * @param modelPoints 车型分
     * @return 车型分集合
     */
    public List<ModelPoints> selectModelPointsList(ModelPoints modelPoints);

    /**
     * 新增车型分
     * 
     * @param modelPoints 车型分
     * @return 结果
     */
    public int insertModelPoints(ModelPoints modelPoints);

    /**
     * 修改车型分
     * 
     * @param modelPoints 车型分
     * @return 结果
     */
    public int updateModelPoints(ModelPoints modelPoints);

    /**
     * 删除车型分
     * 
     * @param id 车型分主键
     * @return 结果
     */
    public int deleteModelPointsById(Long id);

    /**
     * 批量删除车型分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteModelPointsByIds(Long[] ids);
}
