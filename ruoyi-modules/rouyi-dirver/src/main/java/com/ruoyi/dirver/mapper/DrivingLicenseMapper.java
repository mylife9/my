package com.ruoyi.dirver.mapper;

import com.ruoyi.dirver.domain.DrivingLicense;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrivingLicenseMapper {
    @Insert("insert into driving_license set license_plate=#{licensePlate},model_name=#{modelName},owner=#{owner},nature=#{nature},model_number=#{modelNumber},identification_code=#{identificationCode},engine_number=#{engineNumber},registration_date=now(),issue_date=#{issueDate},authorized_number=#{authorizedNumber},gross_mass=#{grossMass},quality_service=#{qualityService},veranda_dimension=#{verandaDimension},driving_image=#{drivingImage}")
    Integer save(DrivingLicense driving);
}
