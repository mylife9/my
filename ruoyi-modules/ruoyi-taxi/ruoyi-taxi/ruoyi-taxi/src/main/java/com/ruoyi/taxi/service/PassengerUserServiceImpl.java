package com.ruoyi.taxi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.config.ShuMaiConfig;
import com.ruoyi.taxi.config.WxLoginConfig;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.UserVo;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.taxi.utils.GetAgeUtil;
import com.ruoyi.taxi.utils.HttpClientUtil;
import com.ruoyi.taxi.utils.OSSFileUtil;
import com.ruoyi.taxi.utils.OcrUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 14:30
 */
@Service
public class PassengerUserServiceImpl implements PassengerUserService {
    @Autowired
    PassengerUserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    OSSFileUtil ossFileUtil;
    @Autowired
    OcrUtil ocrUtil;

    @Override
    public Integer register(@Validated PassengerUser user) {
        String codeKey = "code:"+user.getPhone();
        String code = stringRedisTemplate.opsForValue().get(codeKey);
        if(!user.getCode().equals(code)){
            throw new ServiceException("验证码错误");
        }

        PassengerUser login = userMapper.selectPassengerByOpenid(user.getPhone());
        if(login != null){
            throw new ServiceException("手机号已被使用");
        }

        Integer i = userMapper.registerUser(user);

        return i;
    }

    @Override
    public String sendCode(String mobile) {
        if(StringUtils.isEmpty(mobile)){
            throw new ServiceException("手机号不能为空");
        }

        Random random = new Random();
        int num = 1000 + random.nextInt(9000);
        String code = ""+num;

        String codeKey = "code:"+mobile;
        stringRedisTemplate.opsForValue().set(codeKey,code,5, TimeUnit.MINUTES);

        return code;
    }

    @Override
    public PassengerUser login(String code) {
        String url = WxLoginConfig.getOpenUrl
                .replace("APPID", WxLoginConfig.appid)
                .replace("SECRET",WxLoginConfig.secret)
                .replace("JSCODE",code);
        String result = HttpClientUtil.get(url, null);

        JSONObject jsonObject = JSONObject.parseObject(result);

        String openid = jsonObject.get("openid").toString();

        PassengerUser login = userMapper.selectPassengerByOpenid(openid);

        if(login == null){
            PassengerUser user = new PassengerUser();
            user.setOpenid(openid);
            //发送新手大礼包短信
            return user;
        }
        return login;
    }

    @Override
    public Integer updateUser(@Validated PassengerUser user) {
        Integer update = userMapper.updateUser(user);

        if(update <= 0){
            register(user);
        }

        return update;
    }

    @Override
    public Map ocr(MultipartFile file) throws Exception {
        Map map = ocrUtil.ocrIdCard(file);
        String result = map.get("result").toString();
        if(StringUtils.isEmpty(result)){
            throw new ServiceException("上传图片格式错误");
        }
        //控制台输出身份证信息
        System.out.println(result);

        JSONObject ocrData = JSONObject.parseObject(result);
        JSONObject idcardInfo = JSONObject.parseObject(ocrData.get("data").toString());
        Map<String, String> idcardMap = JSONObject.parseObject(idcardInfo.get("info").toString(), Map.class);
        map.put("idCardInfo",idcardMap);

        //获取正反面
        String side = idcardInfo.get("side").toString();
        map.put("side",side);

        return map;
    }

    @Override
    public PassengerUser getUserInfo(String openid) {
        PassengerUser user = userMapper.selectPassengerByOpenid(openid);
        return user;
    }

    @Override
    public Map driverOcr(MultipartFile file) throws IOException {
        Map map = ocrUtil.ocrDriver(file);
        String result = map.get("result").toString();
        if(StringUtils.isEmpty(result)){
            throw new ServiceException("上传图片格式错误");
        }
        //控制台输出身份证信息
        System.out.println(result);

        JSONObject ocrData = JSONObject.parseObject(result);
        JSONObject idcardInfo = JSONObject.parseObject(ocrData.get("data").toString());
        Map<String, String> idcardMap = JSONObject.parseObject(idcardInfo.get("info").toString(), Map.class);
        map.put("idCardInfo",idcardMap);

        //获取正反面
        String side = idcardInfo.get("side").toString();
        map.put("side",side);

        return map;
    }
}
