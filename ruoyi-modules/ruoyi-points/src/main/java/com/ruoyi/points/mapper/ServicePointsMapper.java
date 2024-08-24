package com.ruoyi.points.mapper;


import com.ruoyi.points.domain.ServicePoints;

import java.util.List;

/**
 * 服务分Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
public interface ServicePointsMapper 
{
    /**
     * 查询服务分
     * 
     * @param id 服务分主键
     * @return 服务分
     */
    public ServicePoints selectServicePointsById(Long id);

    /**
     * 查询服务分列表
     * 
     * @param servicePoints 服务分
     * @return 服务分集合
     */
    public List<ServicePoints> selectServicePointsList(ServicePoints servicePoints);

    /**
     * 新增服务分
     * 
     * @param servicePoints 服务分
     * @return 结果
     */
    public int insertServicePoints(ServicePoints servicePoints);

    /**
     * 修改服务分
     * 
     * @param servicePoints 服务分
     * @return 结果
     */
    public int updateServicePoints(ServicePoints servicePoints);

    /**
     * 删除服务分
     * 
     * @param id 服务分主键
     * @return 结果
     */
    public int deleteServicePointsById(Long id);

    /**
     * 批量删除服务分
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServicePointsByIds(Long[] ids);
}
