package com.ruoyi.order.mapper;

import com.ruoyi.order.pojo.Driver;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.pojo.PriceRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author : Gao
 * @date : 2024-08-21 12:58
 * @description :
 **/
@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM order_info where id = #{id}")
    OrderInfo orderSelectOne(Long id);

    @Update("UPDATE order_info set dep_longitude = #{depLongitude} , dep_latitude = #{depLatitude} , dest_longitude = #{destLongitude} , dest_latitude = #{destLatitude} where id = #{id}")
    void orderUpdate(OrderInfo orderInfo);

    @Select("select * from driver_user where id = #{id}")
    Driver selectDriverOne(Long driverId);

    @Select("select * from price_rule where city_code = #{address}")
    PriceRule selectPrice(String address);

    @Select("select * from order_info where driver_id = #{driverId}")
    List<OrderInfo> selectOrderByDriver(Long driverId);

    @Select("select * from order_info where driver_id = #{driverId} and order_time > #{from} and order_time < #{date}")
    List<OrderInfo> orderSelectDate(@Param("driverId") Long driverId,@Param("from") Date from, @Param("date") Date date);

    @Select("select * from driver_user")
    List<Driver> driverList();

    @Select("select * from order_info where driver_id = #{id} and order_status != 1")
    List<OrderInfo> list(Long id);

    @Select("select openid, COUNT(openid) as count from order_info where order_time >= #{nowTime} and order_time <= #{date} GROUP BY openid")
    List<OrderInfo> countList(@Param("nowTime") Date nowTime, @Param("date") Date date);

    @Select("select * from order_info where order_time > #{nowTime} and order_time <= #{date}")
    List<OrderInfo> selectDate(@Param("nowTime") Date nowTime, @Param("date") Date date);

    @Update("update order_info set order_status = #{i} where id = #{id}")
    void userCancelOrder(@Param("id") Long id,@Param("i") int i);
}
