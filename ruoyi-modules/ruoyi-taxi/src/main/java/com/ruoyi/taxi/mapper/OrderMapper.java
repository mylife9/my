package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.CouponsGet;
import com.ruoyi.taxi.domain.CouponsLog;
import com.ruoyi.taxi.domain.DriverUser;
import com.ruoyi.taxi.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from passenger_user where id=#{id}")
    OrderInfo selectById(Integer id);

    @Update("UPDATE order_info SET order_status = 9 WHERE id = #{id}")
    void updateOrderStatus(OrderInfo info1);

    @Select("select * from driver_user where id = #{id}")
    DriverUser showDriverById(Long id);


    @Select("select * from order_info where passenger_phone = #{tel} and order_status = 2")
    OrderInfo selectOrderInfoId(String tel);


    @Select("select * from order_info where passenger_phone=#{tel} and order_status=2")
    OrderInfo getstatu(String tel);

    @Select("SELECT * FROM coupons_get ORDER BY RAND() LIMIT 1")
    List<CouponsGet> showCoupons();
}
