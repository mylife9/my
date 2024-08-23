package com.ruoyi.drools.service;

import com.ruoyi.drools.domain.Compliance;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:16
 * @description :
 **/
public interface ComplianceService {


    List<Compliance> list();

    void updateCompliance(Double complianceScope, Integer driverId);
}
