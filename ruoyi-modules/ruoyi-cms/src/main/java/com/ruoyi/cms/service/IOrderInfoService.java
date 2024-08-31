package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.OrderInfo;

import java.util.List;

/**
 * 订单信息Service接口
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
public interface IOrderInfoService 
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
     * 批量删除订单信息
     * 
     * @param ids 需要删除的订单信息主键集合
     * @return 结果
     */
    public int deleteOrderInfoByIds(Long[] ids);

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    public int deleteOrderInfoById(Long id);


    OrderInfo getselectOrderInfo(String tel);

    void updateOrderInfoStatus(String tel);

    void updatstatusone(String tel);

    void updateInfo(String id, String destination);



    OrderInfo getselectOrderInfoBytel(String message);

    void updateInfoposition(String message, String j, String w,double price);

    OrderInfo selectorderInfo(String message);

    void updateInfo3(String tel);

    void updateOrderStatus(String tel);

    void updateOrderStatusa(String tel);

    OrderInfo selectorderInfoeight(String tel);
}
