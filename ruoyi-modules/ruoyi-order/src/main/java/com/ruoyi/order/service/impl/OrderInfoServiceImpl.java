package com.ruoyi.order.service.impl;


import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.mapper.OrderInfoMapper;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author DELL
* @description 针对表【order_info】的数据库操作Service实现
* @createDate 2024-08-17 14:45:25
*/
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private static final double EARTH_RADIUS = 6378137;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<OrderInfo> getOrderInfoList() {
        // 获取用户ID
        // 解析token
        // 钩子函数调用

        // 定义操作列表的键名
        String key = "Order";
        // 初始化ListOperations对象，用于操作Redis中的列表
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        // 从Redis中获取指定键的列表数据
        List<String> range = opsForList.range(key, 0, -1);

        // 准备接收处理后的数据
        List<OrderInfo> list = new ArrayList<>();

        // 如果从Redis获取的列表为空
        if (range.isEmpty()) {
            // 从数据库中查询订单信息列表
            List<OrderInfo> orderInfoList = orderInfoMapper.getOrderInfoList();

            // 遍历订单信息列表，进行处理
            for (OrderInfo orderInfo : orderInfoList) {
                // 将起始经度转换为弧度
                double lat1 = Math.toRadians(Double.parseDouble(orderInfo.getDepLongitude()));
                // 将目标经度转换为弧度
                double lat2 = Math.toRadians(139.11);
                // 将起始纬度转换为弧度
                double lng1 = Math.toRadians(Double.parseDouble(orderInfo.getDepLatitude()));
                // 将目标纬度转换为弧度
                double lng2 = Math.toRadians(39.12);

                // 计算起始点和目标点的经度差和纬度差
                double a = lat1 - lat2;
                double b = lng1 - lng2;

                // 根据经纬度差计算两点间的距离
                double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                        Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));

                // 将弧长乘以地球半径，得到距离，单位为米
                s = s * EARTH_RADIUS;

                // 格式化距离输出为两位小数
                DecimalFormat df = new DecimalFormat("0.00");

                // 输出距离，单位为公里
                System.out.println(df.format(s / 1000) + "公里");

                // 根据距离判断订单是否在3km以外
                if (Double.valueOf(df.format(s / 1000)) > 3) {
                    // 如果订单距离超过3km，则加入到结果列表中
                    list.add(orderInfo);
                }
            }

            // 将处理后的结果列表推入Redis的列表的左侧
            opsForList.leftPush(key, JSON.toJSONString(list));
        } else {
            // 如果Redis中已有数据，则直接从Redis中获取并解析
            list = JSON.parseArray(range.get(0), OrderInfo.class);
        }

        // 返回处理后的订单信息列表
        return list;
    }

    @Override
    public int orderInsert(OrderInfo orderInfo) {

        return orderInfoMapper.orderInsert(orderInfo);
    }

    @Override
    public AjaxResult InsertOrder(OrderInfo orderInfo) {

        //获取用户id

        //redis key值
        String key = "Order";
        orderInfo.setOrderStatus(1);
        orderInfo.setGmtCreate(new Date());
        orderInfo.setDepLatitude("39.91");
        orderInfo.setDepLongitude("116.40");
        int i = orderInfoMapper.orderInsert(orderInfo);

        //删除缓存
        stringRedisTemplate.delete(key);


        return AjaxResult.success("下单成功");
    }
}




