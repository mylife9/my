package com.ruoyi.taxi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;

import com.ruoyi.taxi.domain.PassengerIdcard;
import com.ruoyi.taxi.service.PassengerIdcardService;
import com.ruoyi.taxi.utils.OcrUtil;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-22 18:52
 */
@RestController
@RequestMapping("/idcard")
public class PassengerIdcardController extends BaseController {
    @Autowired
    PassengerIdcardService idcardService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    OcrUtil ocrUtil;


    @GetMapping("/selectIdcard")
    public AjaxResult selectIdcard(@RequestParam String openid){

        PassengerIdcard idcard = idcardService.getIdcardInfo(openid);
        return success(idcard);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody PassengerIdcard idcard){
        Integer result = idcardService.updateIdcard(idcard);
        return result>0?success():error();
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody PassengerIdcard idcard){
        String idcardKey = "idcard:"+idcard.getOpenid();
        String redisIdcard = stringRedisTemplate.opsForValue().get(idcardKey);
        PassengerIdcard passengerIdcard = JSONObject.parseObject(redisIdcard, PassengerIdcard.class);

        Integer result = idcardService.saveIdcard(passengerIdcard);
        return result>0?success():error();
    }

    @PostMapping("/saveRedis")
    public AjaxResult redisPutIdcardInfo(@RequestBody @Validated PassengerIdcard idcard){
        String idcardKey = "idcard:"+idcard.getOpenid();
        String redisIdcard = JSON.toJSONString(idcard);
        stringRedisTemplate.opsForValue().set(idcardKey,redisIdcard,5, TimeUnit.MINUTES);

        return success();
    }

    @PostMapping("/ocrFace")
    public AjaxResult ocrFace(@RequestParam(value = "url") String url) throws IOException {
        Map map = ocrUtil.ocrFace(url);
        return success(map);
    }
}
