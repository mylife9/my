package com.ruoyi.points.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.points.domain.TravelPoints;
import com.ruoyi.points.mapper.TravelPointsMapper;
import com.ruoyi.points.service.ITravelPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 出行分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Service
@Transactional
public class TravelPointsServiceImpl implements ITravelPointsService
{
    @Autowired
    private TravelPointsMapper travelPointsMapper;

    /**
     * 查询出行分
     * 
     * @param id 出行分主键
     * @return 出行分
     */
    @Override
    public TravelPoints selectTravelPointsById(Long id)
    {
        return travelPointsMapper.selectTravelPointsById(id);
    }

    /**
     * 查询出行分列表
     * 
     * @param travelPoints 出行分
     * @return 出行分
     */
    @Override
    public List<TravelPoints> selectTravelPointsList(TravelPoints travelPoints)
    {
        return travelPointsMapper.selectTravelPointsList(travelPoints);
    }

    /**
     * 新增出行分
     * 
     * @param travelPoints 出行分
     * @return 结果
     */
    @Override
    public int insertTravelPoints(TravelPoints travelPoints)
    {
        travelPoints.setCreateTime(DateUtils.getNowDate());
        return travelPointsMapper.insertTravelPoints(travelPoints);
    }

    /**
     * 修改出行分
     * 
     * @param travelPoints 出行分
     * @return 结果
     */
    @Override
    public int updateTravelPoints(TravelPoints travelPoints)
    {
        return travelPointsMapper.updateTravelPoints(travelPoints);
    }

    /**
     * 批量删除出行分
     * 
     * @param ids 需要删除的出行分主键
     * @return 结果
     */
    @Override
    public int deleteTravelPointsByIds(Long[] ids)
    {
        return travelPointsMapper.deleteTravelPointsByIds(ids);
    }

    /**
     * 删除出行分信息
     * 
     * @param id 出行分主键
     * @return 结果
     */
    @Override
    public int deleteTravelPointsById(Long id)
    {
        return travelPointsMapper.deleteTravelPointsById(id);
    }

    @Override
    public List<TravelPoints> list() {
        return travelPointsMapper.list();
    }


}
