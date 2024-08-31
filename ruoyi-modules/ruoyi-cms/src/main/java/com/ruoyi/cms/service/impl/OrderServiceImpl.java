package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.Order;
import com.ruoyi.cms.domain.OrderEntity;
import com.ruoyi.cms.domain.OrderItem;
import com.ruoyi.cms.domain.TbAddress;
import com.ruoyi.cms.mapper.OrderMapper;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/29 10:37
 * @注释
 */
@Service
public class OrderServiceImpl {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public List<TbAddress> getAddress(Long id) {
        return  orderMapper.getAddress(id);
    }

    public List<Map> getProvince() {
        return  orderMapper.getProvince();
    }

    public List<Map> getCity(Integer provinceId) {
        return  orderMapper.getCity(provinceId);
    }

    public List<Map> getArea(Integer cityId) {
        return  orderMapper.getArea(cityId);
    }

    public void saveAddres(TbAddress address) {
        String provinceName=orderMapper.getProvinceName(address.getProvinceId());
        String cityName=orderMapper.getCityName(address.getCityId());
        String areaName=orderMapper.getAreaName(address.getTownId());

        String nodes="";
        nodes+=provinceName;
        if (!cityName.equals("县")&&!cityName.equals("市辖区")){
            nodes+=cityName;
        }
        nodes+=areaName;
        nodes+=" "+address.getAddress();
        address.setNotes(nodes);
        orderMapper.saveAddress(address);
    }

    @Transactional
    public AjaxResult createOrder(OrderEntity orderEntity) {
        //创建延迟消息，以订单信息作为延迟消息内容，延迟一分钟，检查订单是否入库，如果没有入库成功，释放锁定库存
        //雪花算法

        BigDecimal payment=new BigDecimal(0);

        List<OrderItem> items = orderEntity.getItems();
        for (OrderItem item : items) {
            Map<Object, Object> cache = stringRedisTemplate.opsForHash().entries("goods:" + item.getItemId());

            Integer saleNum =Integer.parseInt((String) cache.get("saleNum"));
            if (saleNum<item.getNum()){
                items.remove(item);
                continue;
            }

            BigDecimal price = new BigDecimal((String)cache.get("price")) ;
            item.setPrice(price);

            BigDecimal totalFee = price.multiply(new BigDecimal(item.getNum()));
            item.setTotalFee(totalFee);

           payment= payment.add(totalFee);
            //扣减可售库存，增加锁定库存
            stringRedisTemplate.opsForHash().increment("goods:"+item.getItemId(),"lockNum",item.getNum());
            stringRedisTemplate.opsForHash().increment("goods:"+item.getItemId(),"saleNum",item.getNum()*-1);

        }
        //创建延迟消息，以订单信息作为延迟消息内容，延迟一分钟，检查订单是否入库，如果没有入库成功，释放锁定库存
        //雪花算法
        if (items.size()==0){
            return AjaxResult.error("商品库存不足");
        }

        Order order = orderEntity.getOrder();
        order.setPayment(payment);

        orderMapper.saveOrder(order);

        for (OrderItem item : items) {
            item.setOrderId(order.getOrderId());
            orderMapper.saveOrderItem(item);
        }
        Object orderId = String.valueOf(order.getOrderId());
        return  AjaxResult.success(orderId);
    }

    public Order findOederById(String id) {
        return  orderMapper.findOederById(id);
    }
}
