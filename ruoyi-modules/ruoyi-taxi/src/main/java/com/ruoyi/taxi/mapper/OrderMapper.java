package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    @Select("select * from order_info WHERE id=#{id}")
    OrderInfo getOrderId(Integer id);

    @Update("update order_info set order_status = 1 WHERE id = #{id}")
    int updateOrderStatusByOrderId(Integer id, Integer orderStatus);

    @Select("select * from order_info WHERE driverId=#{id}")
    String getDriverContactInfoByOrderId(Long driverId);

    @Select("select * from order_info WHERE id=#{id}")
    OrderInfo ContactTheDriver(Integer id);
    @Update("update order_info set order_status = 2 WHERE id = #{id}")
    Integer updateOrderStatusByOrderId1(Integer id, Integer orderStatus);
    @Update("update order_info set order_status = 4 WHERE id = #{id}")
    void updatetk(Integer id, Integer orderStatus);
}
