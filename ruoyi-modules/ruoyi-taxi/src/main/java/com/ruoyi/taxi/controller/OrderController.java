package com.ruoyi.taxi.controller;

import com.ruoyi.taxi.service.OrderInfoOpen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    OrderInfoOpen orderInfoOpen;



    @GetMapping("/addOrder")
    public String  addOrder(String tel,String qi,String mu) throws IOException {

        //添加订单
        int insert = orderInfoOpen.getInsert(tel,qi,mu);

        //添加成功放入redis
        String key = "Order";

        stringRedisTemplate.delete(key);



        return qi;
    }

}
