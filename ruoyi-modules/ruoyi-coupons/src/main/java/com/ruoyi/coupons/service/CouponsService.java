package com.ruoyi.coupons.service;

import com.ruoyi.common.core.web.domain.AjaxResult;

import javax.annotation.Resource;

/**
 * @program: rouyi
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-19 15:57
 */
public interface CouponsService {
    AjaxResult couponsList();


    AjaxResult couponsInfo(Long id);

}
