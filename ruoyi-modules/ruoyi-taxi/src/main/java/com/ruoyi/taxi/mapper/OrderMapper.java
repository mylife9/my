package com.ruoyi.taxi.mapper;

import com.ruoyi.taxi.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {

    @Select("select * from passenger_user where id=#{id}")
    OrderInfo selectById(Integer id);

    @Update("UPDATE order_info SET order_status = 9 WHERE id = #{id}")
    void updateOrderStatus(OrderInfo info1);
}
