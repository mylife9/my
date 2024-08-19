package com.ruoyi.coupons.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.coupons.domain.TbCoupons;
import com.ruoyi.coupons.mapper.CouponsMapper;
import com.ruoyi.coupons.service.CouponsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: rouyi
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-19 15:57
 */
@Service
public class CouponsServiceImpl implements CouponsService {
    @Resource
    private CouponsMapper couponsMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    //缓存中存储所有优惠券信息的KEY
    private final String COUPONSALL_KEY = "coupons-all";
    private final String COUPONSINFO_KEY = "coupons:";
    private final String LOCK_KEY = "lock:";

    @Override
    public AjaxResult couponsList() {
        //查询缓存的优惠券信息
        String redisCouponsData = stringRedisTemplate.opsForValue().get(COUPONSALL_KEY);
        //如果缓存中有数据，直接查询缓存数据
        if (redisCouponsData != null) {
            //解析redis的优惠券信息
            List<TbCoupons> couponsList = JSON.parseArray(redisCouponsData, TbCoupons.class);
            return AjaxResult.success(couponsList);
        }
        //如果没有，查询数据库的优惠券信息
        List<TbCoupons> couponsList = couponsMapper.couponsList();
        //将数据转为字符串
        String jsonCouponsList = JSON.toJSONString(couponsList);
        //设置过期时间  15分钟
        stringRedisTemplate.opsForValue().set(COUPONSALL_KEY, jsonCouponsList, 15, TimeUnit.MINUTES);
        return AjaxResult.success(couponsList);
    }

    private boolean tryLock(String key){
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return b;
    }
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
    @Override
    public AjaxResult couponsInfo(Long id) {
        //根据id查询优惠券信息
        String redisCouponsInfo = stringRedisTemplate.opsForValue().get(COUPONSINFO_KEY + id);
        if (redisCouponsInfo != null) {
            //解析redis的优惠券信息
            TbCoupons couponsInfo = JSON.parseObject(redisCouponsInfo, TbCoupons.class);
            return AjaxResult.success(couponsInfo);
        }
        TbCoupons couponsInfo = null;
        try {
            boolean b = tryLock(LOCK_KEY + id);
            if (!b) {
                //是redis的setnx锁防止缓存击穿
                Thread.sleep(500);
                return couponsInfo(id);
            }
            //如果没有，查询数据库的优惠券信息
            couponsInfo = couponsMapper.couponsInfo(id);
            //防止缓存穿透
            //如果查询的优惠券不存在，创建一个空对象||或者使用布隆过滤器
            if (couponsInfo == null) {
                //将空对象存到缓存中
                //设置过期时间，防止占用缓存数据
                stringRedisTemplate.opsForValue().set(COUPONSINFO_KEY + id, "", 10, TimeUnit.MINUTES);
                return AjaxResult.error("优惠券不存在");
            }
            //将数据转为字符串
            String jsonCouponsInfo = JSON.toJSONString(couponsInfo);
            //设置过期时间  15分钟
            stringRedisTemplate.opsForValue().set(COUPONSINFO_KEY + id, jsonCouponsInfo, 15, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            unLock(LOCK_KEY+id);
        }
        return AjaxResult.success(couponsInfo);
    }

}
