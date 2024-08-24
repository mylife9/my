package com.ruoyi.points.mapper;

import java.util.List;
import com.ruoyi.points.domain.Points;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 积分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public interface PointsMapper 
{
    /**
     * 查询积分
     * 
     * @param id 积分主键
     * @return 积分
     */
    public Points selectPointsById(Long id);

    /**
     * 查询积分列表
     * 
     * @param points 积分
     * @return 积分集合
     */
    public List<Points> selectPointsList(Points points);

    /**
     * 新增积分
     * 
     * @param points 积分
     * @return 结果
     */
    public int insertPoints(Points points);

    /**
     * 修改积分
     * 
     * @param points 积分
     * @return 结果
     */
    public int updatePoints(Points points);

    /**
     * 删除积分
     * 
     * @param id 积分主键
     * @return 结果
     */
    public int deletePointsById(Long id);

    /**
     * 批量删除积分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointsByIds(Long[] ids);



    int update(@Param("points")Points points,@Param("selectNum") Double selectNum);

    void inter(Points points);

    Double selectNum(Points points);

    @Update(value = "update points set current_score=#{selectNum} where id=#{id}")
    void update1(Points points);
}
