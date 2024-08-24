package com.ruoyi.driver.mapper;

import com.ruoyi.driver.domain.Car;
import com.ruoyi.driver.domain.DicDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 车辆Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Mapper
public interface DictionMapper
{
    List<DicDistrict> selectDic();
}
