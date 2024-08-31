package com.ruoyi.order.controller;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @program: RuoYi-Cloud
 * @author: gtx
 * @description:
 * @create: 2024-08-21 12:59
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/22 14:00
     * @param orderInfo:用户订单中字段，id、用户的起始和目的地经纬度，还有当前修改的起始和目的地经纬度
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *用户改单
     */
    @PostMapping("/orderUpdate")
    public AjaxResult orderUpdate(@RequestBody OrderInfo orderInfo){

        return orderService.orderUpdate(orderInfo);

    }


    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/22 14:06
     * @param orderInfo: 要修改的订单数据
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *司机同意修改订单
     */
    @GetMapping("/orderUpdateOk")
    public AjaxResult orderUpdateOk(OrderInfo orderInfo){

        return orderService.orderUpdateOk(orderInfo);

    }

    /**
     * @Description:自动检测司机是否存在刷单行为
     * @Author: dsh
     * @Date: 2024/8/22 星期四 11:26
     * * @param null:
     * * @return: null
     *
     */
    @GetMapping("/detectionMethod2/{driverId}")
    public AjaxResult detectionMethod2(@PathVariable("driverId") Long driverId) throws ParseException {
        return orderService.detectionMethod2(driverId);
    }
    /**
     * @Description:测试添加订单信息到redis中
     * @Author: dsh
     * @Date: 2024/8/22 星期四 17:11
     * * @param null:
     * * @return: null
     *
     */
    @PostMapping("/saveOrderInfoToRedis")
    public AjaxResult saveOrderInfoToRedis(@RequestBody OrderInfo orderInfo) throws ParseException {
        return orderService.saveOrderInfoToRedis(orderInfo);
    }
    /**
     * @Description:根据上个订单的距离判断是否刷单
     * @Author: dsh
     * @Date: 2024/8/23 星期五 10:35
     * * @param null:
     * * @return: null
     *
     */
    @PostMapping("/checkOrderAddr")
    public AjaxResult checkOrderAddr(@RequestBody OrderInfo newOrder) throws ParseException {
        return orderService.checkOrderAddr(newOrder);
    }


    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/22 15:40
     * @param orderInfo:
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *
     */
    @PostMapping("/detectionMethod1")
    public AjaxResult detectionMethod1(@RequestBody OrderInfo orderInfo){

        return orderService.detectionMethod1(orderInfo);

    }


    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/26 11:18
     * @param orderInfo:
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *小方法判断经纬度
     */
    @PostMapping("/detectionMethod3")
    public AjaxResult detectionMethod3(@RequestBody OrderInfo orderInfo){

        return orderService.detectionMethod3(orderInfo);

    }


    /**
     * @Description:
     * @Author: 86130
     * @Date: 2024/8/26 11:29
     * @param orderInfo:
     * @return: com.ruoyi.common.core.web.domain.AjaxResult
     *用户取消订单
     */
    @PostMapping("/userCancelOrder")
    public AjaxResult userCancelOrder(@RequestBody OrderInfo orderInfo){

        return orderService.userCancelOrder(orderInfo);

    }

}
