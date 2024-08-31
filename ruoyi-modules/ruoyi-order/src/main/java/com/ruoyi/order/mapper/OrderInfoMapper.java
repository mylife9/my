package com.ruoyi.order.mapper;


import com.ruoyi.order.pojo.DriverUser;
import com.ruoyi.order.pojo.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author DELL
* @description 针对表【order_info】的数据库操作Mapper
* @createDate 2024-08-17 14:45:25
* @Entity com.ruoyi.order.pojo.OrderInfo
*/
@Mapper
public interface OrderInfoMapper  {

    List<OrderInfo> getOrderInfoList();

        @Insert("insert into order_info (passenger_phone,departure,destination,order_status,gmt_create,dep_latitude,dep_longitude) values(#{passengerPhone},#{departure},#{destination},#{orderStatus},#{gmtCreate},#{depLatitude},#{depLongitude})")
        int orderInsert(OrderInfo orderInfo);
        @Select("select * from order_info where id = #{id}")
        OrderInfo orderById(String id);

        @Select("select * from driver_user where id = #{id}")
        DriverUser driverById(Long driverId);

       void updateOrder(OrderInfo orderInfoById);
}




