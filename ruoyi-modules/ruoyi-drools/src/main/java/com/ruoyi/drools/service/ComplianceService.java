package com.ruoyi.drools.service;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:16
 * @description :
 **/
public interface ComplianceService {
    void inserts(Double scope, Integer driverId, String driverCard, String idCard);

    void insert(Double scope, Integer driverId, String driverCard);
}
