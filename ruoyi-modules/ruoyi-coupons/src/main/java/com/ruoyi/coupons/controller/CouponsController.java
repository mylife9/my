package com.ruoyi.coupons.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.coupons.service.CouponsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: rouyi
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-19 15:54
 */
@RestController
@RequestMapping("/coupons1")
public class CouponsController {

    @Resource
    private CouponsService couponsService;
    /**
     * 查询优惠券列表
     */
//    @RequiresPermissions("coupons:coupons:list")
    @GetMapping("/couponsList")
    public AjaxResult couponsList(){
        return couponsService.couponsList();
    }
    /**
     * 查询优惠券信息
     */
//    @RequiresPermissions("coupons:coupons:list")
    @GetMapping("/couponsInfo/{id}")
    public AjaxResult couponsInfo(@PathVariable("id")Long id){
        return couponsService.couponsInfo(id);
    }
}
