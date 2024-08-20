package com.ruoyi.coupons.mapper;

import com.ruoyi.coupons.domain.TbCoupons;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rouyi
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-19 15:58
 */
public interface CouponsMapper {
    @Select("select a.*,b.type_name from tb_coupons a,coupons_type b where a.coupons_type = b.id")
    List<TbCoupons> couponsList();
    @Select("select a.*,b.type_name from tb_coupons a,coupons_type b where a.coupons_type = b.id and a.id = #{id}")
    TbCoupons couponsInfo(Long id);
}
