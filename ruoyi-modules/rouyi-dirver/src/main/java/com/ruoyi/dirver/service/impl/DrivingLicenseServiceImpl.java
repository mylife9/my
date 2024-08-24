package com.ruoyi.dirver.service.impl;

import com.ruoyi.dirver.domain.DrivingLicense;
import com.ruoyi.dirver.mapper.DrivingLicenseMapper;
import com.ruoyi.dirver.service.DrivingLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/22 21:48
 */
@Service
public class DrivingLicenseServiceImpl implements DrivingLicenseService {
    @Autowired
    private DrivingLicenseMapper drivingLicenseMapper;

    @Override
    public Integer save(DrivingLicense driving) {
        return drivingLicenseMapper.save(driving);
    }
}
