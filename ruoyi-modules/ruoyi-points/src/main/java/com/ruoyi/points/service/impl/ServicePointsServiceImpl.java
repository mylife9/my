package com.ruoyi.points.service.impl;


import com.ruoyi.points.domain.ServicePoints;
import com.ruoyi.points.mapper.ServicePointsMapper;
import com.ruoyi.points.service.IServicePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@Service
@Transactional
public class ServicePointsServiceImpl implements IServicePointsService
{
    @Autowired
    private ServicePointsMapper servicePointsMapper;

    /**
     * 查询服务分
     * 
     * @param id 服务分主键
     * @return 服务分
     */
    @Override
    public ServicePoints selectServicePointsById(Long id)
    {
        return servicePointsMapper.selectServicePointsById(id);
    }

    /**
     * 查询服务分列表
     * 
     * @param servicePoints 服务分
     * @return 服务分
     */
    @Override
    public List<ServicePoints> selectServicePointsList(ServicePoints servicePoints)
    {
        return servicePointsMapper.selectServicePointsList(servicePoints);
    }

    /**
     * 新增服务分
     * 
     * @param servicePoints 服务分
     * @return 结果
     */
    @Override
    public int insertServicePoints(ServicePoints servicePoints)
    {
        return servicePointsMapper.insertServicePoints(servicePoints);
    }

    /**
     * 修改服务分
     * 
     * @param servicePoints 服务分
     * @return 结果
     */
    @Override
    public int updateServicePoints(ServicePoints servicePoints)
    {
        return servicePointsMapper.updateServicePoints(servicePoints);
    }

    /**
     * 批量删除服务分
     * 
     * @param ids 需要删除的服务分主键
     * @return 结果
     */
    @Override
    public int deleteServicePointsByIds(Long[] ids)
    {
        return servicePointsMapper.deleteServicePointsByIds(ids);
    }

    /**
     * 删除服务分信息
     * 
     * @param id 服务分主键
     * @return 结果
     */
    @Override
    public int deleteServicePointsById(Long id)
    {
        return servicePointsMapper.deleteServicePointsById(id);
    }
}
