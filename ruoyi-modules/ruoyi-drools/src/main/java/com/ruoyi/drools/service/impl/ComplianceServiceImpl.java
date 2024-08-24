package com.ruoyi.drools.service.impl;

import com.ruoyi.drools.domain.Compliance;
import com.ruoyi.drools.mapper.ComPlianceMapper;
import com.ruoyi.drools.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Compliance> list() {
        return comPlianceMapper.list();
    }

    @Override
    public void updateCompliance(Double complianceScope, Integer driverId) {
        comPlianceMapper.updateCompliance(complianceScope,driverId);
    }
}
