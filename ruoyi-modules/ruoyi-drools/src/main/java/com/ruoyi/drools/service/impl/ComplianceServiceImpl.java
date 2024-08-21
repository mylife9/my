package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.mapper.ComPlianceMapper;
import com.ruoyi.drools.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:16
 * @description :
 **/
@Service
public class ComplianceServiceImpl implements ComplianceService {
    @Autowired
    ComPlianceMapper comPlianceMapper;
    @Override
    public void inserts(Double scope, Integer driverId, String driverCard, String idCard) {
        comPlianceMapper.inserts(scope,driverId,driverCard,idCard);
    }

    @Override
    public void insert(Double scope, Integer driverId, String driverCard) {
        comPlianceMapper.insert(scope,driverId,driverCard);
    }
}
