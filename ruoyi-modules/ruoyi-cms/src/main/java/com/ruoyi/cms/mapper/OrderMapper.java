package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Order;
import com.ruoyi.cms.domain.OrderItem;
import com.ruoyi.cms.domain.TbAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/29 10:38
 * @注释
 */
@Mapper
public interface OrderMapper {
    @Select("select * from tb_address where user_id=#{id} order by times desc limit 0,3")
    List<TbAddress> getAddress(Long id);

    @Select("select  * from tb_provinces")
    List<Map> getProvince();

    @Select("select  * from tb_cities where provinceid=#{provinceId}")
    List<Map> getCity(Integer provinceId);

    @Select("select  * from tb_areas where cityid=#{cityId}")
    List<Map> getArea(Integer cityId);


    @Select("select province from tb_provinces where provinceid=#{provinceId}")
    String getProvinceName(String provinceId);
    @Select("select city from tb_cities where cityid=#{cityId}")
    String getCityName(String cityId);
    @Select("select area from tb_areas where areaid=#{townId}")
    String getAreaName(String townId);

    @Insert("insert  into tb_address set user_id=#{userId},province_id=#{provinceId},city_id=#{cityId},town_id=#{townId},mobile=#{mobile},address=#{address},contact=#{contact},notes=#{notes},create_date=now(),alias=#{alias},times=0")
    void saveAddress(TbAddress address);

    @Insert("insert  into tb_order set payment =#{payment},status=0,create_time = now(),update_time = now(),user_id =#{userId}," +
            "buyer_nick =#{buyerNick},receiver_area_name = #{receiverAreaName},receiver_mobile=#{receiverMobile},receiver=#{receiver}," +
            "source_type=#{sourceType} ")
    @Options(useGeneratedKeys = true,keyProperty = "orderId")
    void saveOrder(Order order);

    @Insert("insert  into tb_order_item set item_id = #{itemId},order_id = #{orderId},title=#{title},price=#{price},num=#{num}," +
            "total_fee =#{totalFee},pic_path=#{picPath} ")

    void saveOrderItem(OrderItem item);

    @Select("select * from tb_order where order_id=#{id}")
    Order findOederById(String id);
}
