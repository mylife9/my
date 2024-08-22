package com.ruoyi.dirver.mapper;

import com.ruoyi.dirver.domain.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper {

    @Insert("insert into car set address=#{address},vehicle_no=#{vehicleNo},plate_color=#{plateColor},seats=#{seats},brand=#{brand},model=#{model},vehicle_type=#{vehicleType},vehicle_color=#{vehicleColor},car_picture=#{carPicture},certify_date_a=now(),vehicle_picture=#{vehiclePicture}")
    Integer saveCar(Car car);
}
