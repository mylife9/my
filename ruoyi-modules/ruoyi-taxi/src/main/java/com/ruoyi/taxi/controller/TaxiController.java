package com.ruoyi.taxi.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.service.OrderInfoService;
import com.ruoyi.taxi.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.common.core.web.domain.AjaxResult.error;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    @Autowired
    TaxiService taxiService;

    @Autowired
    OrderInfoService orderInfoService;


    /**
     * 生成订单接口
     * **/
    @PostMapping("/saveOrder")
    public AjaxResult saveOrder(
            @RequestBody PassengerVo passengerVo
    ){
        return taxiService.saveOrder(passengerVo);
    }


    /**
     * 取消订单
     * 直接取消订单接口
     * **/
    @PostMapping("/cancelOrder/{id}/{passengerId}")
    public AjaxResult cancelOrder(@PathVariable(name = "id")Integer id
            ,@PathVariable(name = "passengerId")Integer passengerId)
    {
        return orderInfoService.cancelOrder(id,passengerId);
    }

    @PostMapping("/computeAmount")
    public AjaxResult computeAmount(@RequestBody PassengerVo passengerVo){
        return orderInfoService.computeAmount(passengerVo);
    }


    /**
     * @Description:
     * @Author: 李紫颖
     * @Date: 2024/8/22 8:39
     * @param :
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *
     */
    @PostMapping("/showDriverById")
    public AjaxResult showDriverById(@RequestBody String tel){


        return orderInfoService.showDriverById(tel);
    }


    @PostMapping("/getstate")
    public AjaxResult getstate(String tel){
        OrderInfo getstate = orderInfoService.getstate(tel);
        if (ObjectUtils.isEmpty(getstate)){
            return error();
        }
        return AjaxResult.success();
    }
}
