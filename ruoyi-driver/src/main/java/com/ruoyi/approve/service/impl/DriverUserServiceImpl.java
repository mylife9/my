package com.ruoyi.approve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.approve.mapper.DriverUserMapper;
import com.ruoyi.approve.domain.DriverUser;
import com.ruoyi.approve.service.IDriverUserService;

import javax.annotation.Resource;

/**
 * 司机入驻Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
@Service
public class DriverUserServiceImpl implements IDriverUserService 
{
    @Resource
    private DriverUserMapper driverUserMapper;

    /**
     * 查询司机入驻
     * 
     * @param id 司机入驻主键
     * @return 司机入驻
     */
    @Override
    public DriverUser selectDriverUserById(Long id)
    {
        return driverUserMapper.selectDriverUserById(id);
    }

    /**
     * 查询司机入驻列表
     * 
     * @param driverUser 司机入驻
     * @return 司机入驻
     */
    @Override
    public List<DriverUser> selectDriverUserList(DriverUser driverUser)
    {
        return driverUserMapper.selectDriverUserList(driverUser);
    }

    /**
     * 新增司机入驻
     * 
     * @param driverUser 司机入驻
     * @return 结果
     */
    @Override
    public int insertDriverUser(DriverUser driverUser)
    {
        return driverUserMapper.insertDriverUser(driverUser);
    }

    /**
     * 修改司机入驻
     * 
     * @param driverUser 司机入驻
     * @return 结果
     */
    @Override
    public int updateDriverUser(DriverUser driverUser)
    {
        return driverUserMapper.updateDriverUser(driverUser);
    }

    /**
     * 批量删除司机入驻
     * 
     * @param ids 需要删除的司机入驻主键
     * @return 结果
     */
    @Override
    public int deleteDriverUserByIds(Long[] ids)
    {
        return driverUserMapper.deleteDriverUserByIds(ids);
    }

    /**
     * 删除司机入驻信息
     * 
     * @param id 司机入驻主键
     * @return 结果
     */
    @Override
    public int deleteDriverUserById(Long id)
    {
        return driverUserMapper.deleteDriverUserById(id);
    }

    @Override
    public int updateCarStatus(DriverUser driverUser) {
        return driverUserMapper.updateCarStatus(driverUser);
    }
}
