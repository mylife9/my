package com.ruoyi.taxi.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.service.OrderInfoService;
import com.ruoyi.taxi.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    @Autowired
    TaxiService taxiService;

    @Autowired
    OrderInfoService orderInfoService;
     //NotificationService
    /**
     * 生成订单,用户验证接口
     * **/
    @PostMapping("/saveOrder")
    public AjaxResult saveOrder(
            @RequestHeader String token,
            @RequestBody PassengerVo passengerVo
    ){
        return taxiService.saveOrder(token,passengerVo);
    }
    /**
     * 取消订单
     * 直接取消订单接口
     * **/
    @PostMapping("/CancelAnOrder/{id}/{passengerId}")
    public AjaxResult CancelAnOrder(@PathVariable(name = "id") Integer id,
                                    @PathVariable(name = "passengerId") Integer passengerId){
        return orderInfoService.CancelAnOrder(id,passengerId);
    }
    /**
     * 联系司机后取消订单
     * **/
    @PostMapping("/ContactTheDriver/{id}/{passengerId}")
    public AjaxResult ContactTheDriver(@PathVariable(name = "id") Integer id,
                                       @PathVariable(name = "passengerId") Integer passengerId){
        return orderInfoService.ContactTheDriver(id,passengerId);
    }
    /**
     * 订单退款
     */
    @PostMapping("/refund/{id}/{passengerId}")
    public AjaxResult refund(@PathVariable(name = "id") Integer id,
                                       @PathVariable(name = "passengerId") Integer passengerId){
        return orderInfoService.refund(id,passengerId);
    }

}
