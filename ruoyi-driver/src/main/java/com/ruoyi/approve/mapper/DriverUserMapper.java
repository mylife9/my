package com.ruoyi.approve.mapper;

import java.util.List;
import com.ruoyi.approve.domain.DriverUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 司机入驻Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
@Mapper
public interface DriverUserMapper 
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
     * 删除司机入驻
     * 
     * @param id 司机入驻主键
     * @return 结果
     */
    public int deleteDriverUserById(Long id);

    /**
     * 批量删除司机入驻
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDriverUserByIds(Long[] ids);

    int updateCarStatus(DriverUser driverUser);
}
