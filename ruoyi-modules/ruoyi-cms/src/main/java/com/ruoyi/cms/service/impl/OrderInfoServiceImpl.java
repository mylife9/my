package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.OrderInfo;
import com.ruoyi.cms.mapper.OrderInfoMapper;
import com.ruoyi.cms.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@Service
public  class   OrderInfoServiceImpl implements IOrderInfoService
{
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    @Override
    public OrderInfo selectOrderInfoById(Long id)
    {
        return orderInfoMapper.selectOrderInfoById(id);
    }

    /**
     * 查询订单信息列表
     * 
     * @param orderInfo 订单信息
     * @return 订单信息
     */
    @Override
    public List<OrderInfo> selectOrderInfoList(OrderInfo orderInfo)
    {
        return orderInfoMapper.selectOrderInfoList(orderInfo);
    }

    /**
     * 新增订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo)
    {
        return orderInfoMapper.insertOrderInfo(orderInfo);
    }

    /**
     * 修改订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo)
    {
        return orderInfoMapper.updateOrderInfo(orderInfo);
    }

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoByIds(Long[] ids)
    {
        return orderInfoMapper.deleteOrderInfoByIds(ids);
    }

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderInfoById(Long id)
    {
        return orderInfoMapper.deleteOrderInfoById(id);
    }

    @Override
    public OrderInfo getselectOrderInfo(String tel) {

        return orderInfoMapper.getselectOrderInfo(tel);
    }

    @Override
    public void updateOrderInfoStatus(String tel) {
        orderInfoMapper.updateOrderInfoStatus(tel);
    }

    @Override
    public void updatstatusone(String tel) {
        orderInfoMapper.updatstatusone(tel);
    }

    @Override
    public void updateInfo(String id, String destination) {
        orderInfoMapper.updateInfo(id,destination);
    }




    @Override
    public void updateInfoposition(String message, String longitude, String latitude,double price) {
        orderInfoMapper.updateInfoposition(message,longitude,latitude,price);
    }

    @Override
    public OrderInfo selectorderInfo(String message) {
        return orderInfoMapper.selectorderInfo(message);
    }

    @Override
    public void updateInfo3(String tel) {
        orderInfoMapper.updateInfo3(tel);
    }

    @Override
    public void updateOrderStatus(String tel) {
        orderInfoMapper.updateOrderStatus(tel);
    }

    @Override
    public void updateOrderStatusa(String tel) {
        orderInfoMapper.updateOrderStatusa(tel);
    }

    @Override
    public OrderInfo selectorderInfoeight(String tel) {
        return orderInfoMapper.selectorderInfoeight(tel);
    }


    @Override
    public OrderInfo getselectOrderInfoBytel(String message) {
        return orderInfoMapper.getselectOrderInfoBytel(message);
    }




}
