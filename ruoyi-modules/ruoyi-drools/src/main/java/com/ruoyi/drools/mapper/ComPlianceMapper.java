package com.ruoyi.drools.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:17
 * @description :
 **/
public interface ComPlianceMapper {

    @Insert("insert into compliance (driver_card,id_card,compliance_scope,driver_id) values (#{driverCard},#{idCard},#{scope},#{driverId})")
    void inserts(@Param("scope") Double scope, @Param("driverId") Integer driverId, @Param("driverCard") String driverCard, @Param("idCard")String idCard);

    @Insert("insert into compliance (driver_card,compliance_scope,driver_id,if_null) values (#{driverCard},#{scope},#{driverId},0)")
    void insert(@Param("scope") Double scope, @Param("driverId") Integer driverId, @Param("driverCard") String driverCard);

}
