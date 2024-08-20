package com.ruoyi.coupons.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.druid.support.json.JSONUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ruoyi
 * @author: MaoYiFan
 * @description:
 * @create: 2024-08-20 08:57
 */
@Configuration
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(500);
        String jsonStr = JSONUtils.toJSONString(AjaxResult.error(500, "服务器忙碌，请稍后再试（限流）"));
        httpServletResponse.getWriter().println(jsonStr);
    }
}
