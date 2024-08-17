package com.ruoyi.taxi.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/taxi")
public class TaxiController {
    @Autowired
    TaxiService taxiService;

    /**
     * 生成订单接口
     * **/
    @PostMapping("/saveOrder")
    public AjaxResult saveOrder(
            @RequestHeader String token,
            @RequestBody PassengerVo passengerVo
    ){
        return taxiService.saveOrder(token,passengerVo);
    }
}
