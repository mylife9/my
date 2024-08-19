package com.ruoyi.taxi.service.impl;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.mapper.OrderMapper;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TaxiMapper taxiMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public AjaxResult CancelAnOrder(Integer id, Integer passengerId) {

        //从数据库中查询订单id信息
        OrderInfo orid = orderMapper.getOrderId(id);
        if (orid==null || !orid.getPassengerId().equals(passengerId)){
            return AjaxResult.error("订单不存在或乘客ID不匹配");
        }
        //判断是今天是否存在取消状态
        if (!stringRedisTemplate.hasKey("DATE"+passengerId)){
            taxiMapper.bunoreder1(id,passengerId);
            return AjaxResult.success("更新取消次数");
        }
        //更新订单状态为已取消
        Integer reslut =  orderMapper.updateOrderStatusByOrderId(id,orid.getOrderStatus());
        if (reslut>0){
            //记录订单次数
            PassengerUser count = taxiMapper.bunoreder(id,passengerId);
            LocalDate now = LocalDate.now();
            stringRedisTemplate.opsForValue().set("DATE"+passengerId, String.valueOf(now),15,TimeUnit.MINUTES);
            //判断订单是否超过次数
            if (count.getChargebackNumber()<4 && !stringRedisTemplate.hasKey("DATE"+passengerId)){
                //记录订单次数,成功加一
                taxiMapper.bunoreder(id,passengerId);
                return AjaxResult.error("订单取消成功");
            }else {
                //订单取消失败
                return AjaxResult.error("订单取消失败,您今天取消订单的次数已达上限");
            }

        }
        return AjaxResult.error("订单取消失败");

    }

    @Override
    public AjaxResult ContactTheDriver(Integer id, Integer passengerId) {
        OrderInfo orderInfo = orderMapper.ContactTheDriver(id);
        //检查订单状态
        if (orderInfo==null){
            return AjaxResult.error("订单不存在");
        }
        //判断是今天是否存在取消状态
        if (!stringRedisTemplate.hasKey("DATE"+passengerId)){
            taxiMapper.bunoreder1(id,passengerId);
            return AjaxResult.success("更新取消次数");
        }
        //记录订单次数
        PassengerUser count = taxiMapper.bunoreder(id,passengerId);
        //检查订单是否已被司机接单
        if (orderInfo.getOrderStatus()==2){
            //司机同意取消
            Integer reslut =  orderMapper.updateOrderStatusByOrderId1(id,orderInfo.getOrderStatus());
            if (reslut==1){
                LocalDate now = LocalDate.now();
                stringRedisTemplate.opsForValue().set("DATE"+passengerId, String.valueOf(now));
                //判断订单是否超过次数
                if (count.getChargebackNumber()<4 && !stringRedisTemplate.hasKey("DATE"+passengerId)){
                    //记录订单次数,成功加一
                    taxiMapper.bunoreder(id,passengerId);
                    return AjaxResult.error("订单取消成功");
                }
            }else {
                //返回信息给乘客
                return AjaxResult.error("订单取消失败.您今天的取消次数已达上限");
            }
        }
        // 如果"，则返回错误或要求乘客重新确认
        return AjaxResult.error("请先与司机联系并获得其同意后再取消订单");
    }

    @Override
    public AjaxResult refund(Integer id, Integer passengerId) {
        //从数据库中查询订单id信息
        OrderInfo orid = orderMapper.getOrderId(id);
        if (orid==null || orid.getOrderStatus()==3){
            return AjaxResult.error("订单不存在或存在未支付的订单,无法进行退款");
        }
        //判断是今天是否存在取消状态
        if (!stringRedisTemplate.hasKey("DATE"+passengerId)){
            taxiMapper.bunoreder1(id,passengerId);
            return AjaxResult.success("更新取消次数");
        }
        //更新订单状态为已取消
        Integer reslut =  orderMapper.updateOrderStatusByOrderId(id,orid.getOrderStatus());
        if (reslut>0){
            //记录订单次数
            PassengerUser count = taxiMapper.bunoreder(id,passengerId);
            LocalDate now = LocalDate.now();
            stringRedisTemplate.opsForValue().set("DATE"+passengerId, String.valueOf(now),15,TimeUnit.MINUTES);
            //判断订单是否超过次数
            if (count.getChargebackNumber()<4 && !stringRedisTemplate.hasKey("DATE"+passengerId)){
                //记录订单次数,成功加一
                taxiMapper.bunoreder(id,passengerId);
                orderMapper.updatetk(id,orid.getOrderStatus());
                return AjaxResult.error("订单取消成功,退款成功请注意查看账户");
            }else {
                //订单取消失败
                return AjaxResult.error("订单取消失败,您今天取消订单的次数已达上限");
            }

        }
        return AjaxResult.error("订单取消失败");

    }
}
