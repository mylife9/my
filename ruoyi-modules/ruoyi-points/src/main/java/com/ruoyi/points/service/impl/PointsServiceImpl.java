package com.ruoyi.points.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.points.mapper.PointsMapper;
import com.ruoyi.points.domain.Points;
import com.ruoyi.points.service.IPointsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@Service
@Transactional
public class PointsServiceImpl implements IPointsService 
{
    @Autowired
    private PointsMapper pointsMapper;

    /**
     * 查询积分
     * 
     * @param id 积分主键
     * @return 积分
     */
    @Override
    public Points selectPointsById(Long id)
    {
        return pointsMapper.selectPointsById(id);
    }

    /**
     * 查询积分列表
     * 
     * @param points 积分
     * @return 积分
     */
    @Override
    public List<Points> selectPointsList(Points points)
    {
        return pointsMapper.selectPointsList(points);
    }

    /**
     * 新增积分
     * 
     * @param points 积分
     * @return 结果
     */
    @Override
    public int insertPoints(Points points)
    {
        return pointsMapper.insertPoints(points);
    }

    /**
     * 修改积分
     * 
     * @param points 积分
     * @return 结果
     */
    @Override
    public int updatePoints(Points points)
    {
        return pointsMapper.updatePoints(points);
    }

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的积分主键
     * @return 结果
     */
    @Override
    public int deletePointsByIds(Long[] ids)
    {
        return pointsMapper.deletePointsByIds(ids);
    }

    /**
     * 删除积分信息
     * 
     * @param id 积分主键
     * @return 结果
     */
    @Override
    public int deletePointsById(Long id)
    {
        return pointsMapper.deletePointsById(id);
    }



    @Override
    public void inter(Points points) {
        pointsMapper.inter(points);
    }

    @Override
    public Double selectNum(Points points) {
        return pointsMapper.selectNum(points);
    }

    @Override
    public int update(Points points, Double selectNum) {
        return pointsMapper.update(points,selectNum);
    }

    @Override
    public void update1(Points points) {
        pointsMapper.update1(points);
    }


}
