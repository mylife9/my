package com.ruoyi.driver.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.driver.mapper.LicenseMapper;
import com.ruoyi.driver.domain.License;
import com.ruoyi.driver.service.ILicenseService;

/**
 * 行驶证管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@Service
public class LicenseServiceImpl implements ILicenseService 
{
    @Autowired
    private LicenseMapper licenseMapper;

    /**
     * 查询行驶证管理
     * 
     * @param id 行驶证管理主键
     * @return 行驶证管理
     */
    @Override
    public License selectLicenseById(Long id)
    {
        return licenseMapper.selectLicenseById(id);
    }

    /**
     * 查询行驶证管理列表
     * 
     * @param license 行驶证管理
     * @return 行驶证管理
     */
    @Override
    public List<License> selectLicenseList(License license)
    {
        return licenseMapper.selectLicenseList(license);
    }

    /**
     * 新增行驶证管理
     * 
     * @param license 行驶证管理
     * @return 结果
     */
    @Override
    public int insertLicense(License license)
    {
        return licenseMapper.insertLicense(license);
    }

    /**
     * 修改行驶证管理
     * 
     * @param license 行驶证管理
     * @return 结果
     */
    @Override
    public int updateLicense(License license)
    {
        return licenseMapper.updateLicense(license);
    }

    /**
     * 批量删除行驶证管理
     * 
     * @param ids 需要删除的行驶证管理主键
     * @return 结果
     */
    @Override
    public int deleteLicenseByIds(Long[] ids)
    {
        return licenseMapper.deleteLicenseByIds(ids);
    }

    /**
     * 删除行驶证管理信息
     * 
     * @param id 行驶证管理主键
     * @return 结果
     */
    @Override
    public int deleteLicenseById(Long id)
    {
        return licenseMapper.deleteLicenseById(id);
    }

    @Override
    public int updateLicenseStatus(License license) {
        return licenseMapper.updateLicenseStatus(license);
    }
}
