package com.ruoyi.points.mapper;

import com.ruoyi.points.domain.TravelPoints;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 出行分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
public interface TravelPointsMapper 
{
    /**
     * 查询出行分
     * 
     * @param id 出行分主键
     * @return 出行分
     */
    public TravelPoints selectTravelPointsById(Long id);

    /**
     * 查询出行分列表
     * 
     * @param travelPoints 出行分
     * @return 出行分集合
     */
    public List<TravelPoints> selectTravelPointsList(TravelPoints travelPoints);

    /**
     * 新增出行分
     * 
     * @param travelPoints 出行分
     * @return 结果
     */
    public int insertTravelPoints(TravelPoints travelPoints);

    /**
     * 修改出行分
     * 
     * @param travelPoints 出行分
     * @return 结果
     */
    public int updateTravelPoints(TravelPoints travelPoints);

    /**
     * 删除出行分
     * 
     * @param id 出行分主键
     * @return 结果
     */
    public int deleteTravelPointsById(Long id);

    /**
     * 批量删除出行分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTravelPointsByIds(Long[] ids);

    @Select("select driver_id trip_scope from travel_points ")
    List<TravelPoints> list();
}
