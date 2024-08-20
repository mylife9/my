package com.ruoyi.coupons.mapper;

import com.ruoyi.coupons.domain.CouponsType;
import com.ruoyi.coupons.domain.TbCoupons;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: M
 * @Date: 2024/8/16 22:07:13
 * @Version: v1.0.0
 **/
@Mapper
public interface CouponsTypeMapper {

    public List<CouponsType> selectListType();

    void insertCoupon(Integer id, Long couponsId);

    @Select("SELECT * FROM tb_coupons WHERE id= #{id}")
    TbCoupons selectCoupons(Long id);
}
