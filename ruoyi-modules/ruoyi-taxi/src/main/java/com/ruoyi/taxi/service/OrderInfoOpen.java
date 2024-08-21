package com.ruoyi.taxi.service;

import com.ruoyi.taxi.pojo.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "ruoyi-order")
public interface OrderInfoOpen {

//    @PostMapping("/order/info/getInsert")
//    public int getInsert(String tel,String qi,String mu);

    @PostMapping("/order/info/getInsert/{tel}/{qi}/{mu}")
    public int getInsert(@RequestParam String tel,
                         @RequestParam String qi,
                         @RequestParam String mu);

}
