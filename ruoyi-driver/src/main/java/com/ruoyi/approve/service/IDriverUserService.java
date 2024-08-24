package com.ruoyi.approve.service;

import java.util.List;


import com.ruoyi.approve.domain.DriverUser;

/**
 * 司机入驻Service接口
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
public interface IDriverUserService 
{
    /**
     * 查询司机入驻
     * 
     * @param id 司机入驻主键
     * @return 司机入驻
     */
    public DriverUser selectDriverUserById(Long id);

    /**
     * 查询司机入驻列表
     * 
     * @param driverUser 司机入驻
     * @return 司机入驻集合
     */
    public List<DriverUser> selectDriverUserList(DriverUser driverUser);

    /**
     * 新增司机入驻
     * 
     * @param driverUser 司机入驻
     * @return 结果
     */
    public int insertDriverUser(DriverUser driverUser);

    /**
     * 修改司机入驻
     * 
     * @param driverUser 司机入驻
     * @return 结果
     */
    public int updateDriverUser(DriverUser driverUser);

    /**
     * 批量删除司机入驻
     * 
     * @param ids 需要删除的司机入驻主键集合
     * @return 结果
     */
    public int deleteDriverUserByIds(Long[] ids);

    /**
     * 删除司机入驻信息
     * 
     * @param id 司机入驻主键
     * @return 结果
     */
    public int deleteDriverUserById(Long id);

    int updateCarStatus(DriverUser driverUser);
}
