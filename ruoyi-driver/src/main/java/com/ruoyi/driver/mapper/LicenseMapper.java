package com.ruoyi.driver.mapper;

import java.util.List;
import com.ruoyi.driver.domain.License;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行驶证管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@Mapper
public interface LicenseMapper 
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
     * 删除行驶证管理
     * 
     * @param id 行驶证管理主键
     * @return 结果
     */
    public int deleteLicenseById(Long id);

    /**
     * 批量删除行驶证管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLicenseByIds(Long[] ids);

    int updateLicenseStatus(License license);
}
