package com.ruoyi.drools.mapper;

import com.ruoyi.drools.domain.Compliance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author : 暴龙兽
 * @date : 2024-08-21 19:17
 * @description :
 **/
public interface ComPlianceMapper {



    @Select("select * from compliance limit 10")
    List<Compliance> list();

    @Update("update compliance set complianceScope = complianceScope + #{complianceScope} where driverId=#{driverId}")

    void updateCompliance(@Param("complianceScope") Double complianceScope,@Param("driverId") Integer driverId);
}
