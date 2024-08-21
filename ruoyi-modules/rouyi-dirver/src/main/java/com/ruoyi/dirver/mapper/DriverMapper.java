package com.ruoyi.dirver.mapper;

import com.ruoyi.dirver.domain.Driver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DriverMapper {
    @Select("select * from driver_user where driver_phone=#{driverPhone}")
    Driver isRegister(String driverPhoto);

    @Insert("insert into driver_user set driver_name=#{driverName},driver_phone=#{driverPhone},driver_gender=#{driverGender},driver_birthday=#{driverBirthday},driver_contact_address=#{driverAddress},license_id=#{licenseId},driver_photo=#{driverPhoto}")
    Integer save(Driver driver);
}
