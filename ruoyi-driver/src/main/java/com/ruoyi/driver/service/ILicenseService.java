package com.ruoyi.driver.service;

import java.util.List;
import com.ruoyi.driver.domain.License;

/**
 * 行驶证管理Service接口
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
public interface ILicenseService 
{
    /**
     * 查询行驶证管理
     * 
     * @param id 行驶证管理主键
     * @return 行驶证管理
     */
    public License selectLicenseById(Long id);

    /**
     * 查询行驶证管理列表
     * 
     * @param license 行驶证管理
     * @return 行驶证管理集合
     */
    public List<License> selectLicenseList(License license);

    /**
     * 新增行驶证管理
     * 
     * @param license 行驶证管理
     * @return 结果
     */
    public int insertLicense(License license);

    /**
     * 修改行驶证管理
     * 
     * @param license 行驶证管理
     * @return 结果
     */
    public int updateLicense(License license);

    /**
     * 批量删除行驶证管理
     * 
     * @param ids 需要删除的行驶证管理主键集合
     * @return 结果
     */
    public int deleteLicenseByIds(Long[] ids);

    /**
     * 删除行驶证管理信息
     * 
     * @param id 行驶证管理主键
     * @return 结果
     */
    public int deleteLicenseById(Long id);

    int updateLicenseStatus(License license);
}
