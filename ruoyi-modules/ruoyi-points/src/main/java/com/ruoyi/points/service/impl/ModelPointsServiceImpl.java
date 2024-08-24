package com.ruoyi.points.service.impl;

import java.util.List;

import com.ruoyi.points.domain.ModelPoints;
import com.ruoyi.points.mapper.ModelPointsMapper;
import com.ruoyi.points.service.IModelPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 车型分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Service
@Transactional
public class ModelPointsServiceImpl implements IModelPointsService
{
    @Autowired
    private ModelPointsMapper modelPointsMapper;

    /**
     * 查询车型分
     * 
     * @param id 车型分主键
     * @return 车型分
     */
    @Override
    public ModelPoints selectModelPointsById(Long id)
    {
        return modelPointsMapper.selectModelPointsById(id);
    }

    /**
     * 查询车型分列表
     * 
     * @param modelPoints 车型分
     * @return 车型分
     */
    @Override
    public List<ModelPoints> selectModelPointsList(ModelPoints modelPoints)
    {
        return modelPointsMapper.selectModelPointsList(modelPoints);
    }

    /**
     * 新增车型分
     * 
     * @param modelPoints 车型分
     * @return 结果
     */
    @Override
    public int insertModelPoints(ModelPoints modelPoints)
    {
        return modelPointsMapper.insertModelPoints(modelPoints);
    }

    /**
     * 修改车型分
     * 
     * @param modelPoints 车型分
     * @return 结果
     */
    @Override
    public int updateModelPoints(ModelPoints modelPoints)
    {
        return modelPointsMapper.updateModelPoints(modelPoints);
    }

    /**
     * 批量删除车型分
     * 
     * @param ids 需要删除的车型分主键
     * @return 结果
     */
    @Override
    public int deleteModelPointsByIds(Long[] ids)
    {
        return modelPointsMapper.deleteModelPointsByIds(ids);
    }

    /**
     * 删除车型分信息
     * 
     * @param id 车型分主键
     * @return 结果
     */
    @Override
    public int deleteModelPointsById(Long id)
    {
        return modelPointsMapper.deleteModelPointsById(id);
    }
}
