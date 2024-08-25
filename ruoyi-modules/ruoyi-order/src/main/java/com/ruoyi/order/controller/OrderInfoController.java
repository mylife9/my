package com.ruoyi.order.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.order.pojo.OrderInfo;
import com.ruoyi.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/info")
public class OrderInfoController {

    @Autowired
    OrderInfoService orderInfoService;



    /***
     *@Description:用户下单
     * @param orderInfo
     * @return int
     * @author 26440
     *@date 2024/8/20 16:05
     *
     */
    @PostMapping("/InsertOrder")
    public AjaxResult InsertOrder(@RequestBody OrderInfo orderInfo){
        orderInfoService.InsertOrder(orderInfo);
       return AjaxResult.success("下单成功");
    }

    /***
     *@Description:抢单中心
     * @return java.util.List<com.ruoyi.order.pojo.OrderInfo>
     * @author 26440
     *@date 2024/8/20 16:06
     *
     */
    @GetMapping("/getOrderInfoList")
    public List<OrderInfo> getOrderInfoList() throws JsonProcessingException {

        return orderInfoService.getOrderInfoList();
    }

}
