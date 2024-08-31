package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 订单信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@Mapper
public interface OrderInfoMapper 
{
    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    public OrderInfo selectOrderInfoById(Long id);

    /**
     * 查询订单信息列表
     * 
     * @param orderInfo 订单信息
     * @return 订单信息集合
     */
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo);

    /**
     * 新增订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    /**
     * 修改订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 删除订单信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    public int deleteOrderInfoById(Long id);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderInfoByIds(Long[] ids);

    @Select("select  * from order_info where passenger_phone=#{tel} and order_status=5")
    OrderInfo getselectOrderInfo(String tel);

    @Update("UPDATE order_info SET order_status = 4  WHERE passenger_phone = #{tel} and order_status=3")
    void updateOrderInfoStatus(String tel);

    @Update("UPDATE order_info SET order_status = 5  WHERE passenger_phone = #{tel} and order_status=4")
    void updatstatusone(String tel);
    @Update("UPDATE order_info SET destination=#{destination}   WHERE id = #{id}")
    void updateInfo(@Param("id") String id,@Param("destination") String destination);
    @Update("UPDATE order_info SET passenger_getoff_longitude=#{longitude},passenger_getoff_latitude=#{latitude},order_status=6,price=#{price}  WHERE passenger_phone = #{message} and order_status=5 ")
    void updateInfoposition(@Param("message")String message, @Param("longitude")String longitude,@Param("latitude") String latitude,@Param("price")double price);
    @Select("select  * from order_info where passenger_phone=#{tel} and order_status=5")
    OrderInfo getselectOrderInfoBytel(String message);
    @Select("select  * from order_info where passenger_phone=#{tel} and order_status=6")
    OrderInfo selectorderInfo(String message);
    @Update("UPDATE order_info SET order_status = 3  WHERE passenger_phone = #{tel} and order_status=2")
    void updateInfo3(String tel);

    //修改支付成功
    @Update("UPDATE order_info SET order_status=8   WHERE passenger_phone = #{tel} and order_status=7")
    void updateOrderStatus(String tel);
    //修改发起收款
    @Update("UPDATE order_info SET order_status=7   WHERE passenger_phone = #{tel} and order_status=6")
    void updateOrderStatusa(String tel);
    @Select("select  * from order_info where passenger_phone=#{tel} and order_status=7")
    OrderInfo selectorderInfoeight(String tel);
}
