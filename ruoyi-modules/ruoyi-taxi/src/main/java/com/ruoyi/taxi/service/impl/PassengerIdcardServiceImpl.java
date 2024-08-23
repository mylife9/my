package com.ruoyi.taxi.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.taxi.domain.PassengerIdcard;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.mapper.PassengerIdcardMapper;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.taxi.service.PassengerIdcardService;
import com.ruoyi.taxi.utils.GetAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-22 18:50
 */
@Service
public class PassengerIdcardServiceImpl implements PassengerIdcardService {
    @Autowired
    PassengerIdcardMapper idcardMapper;
    @Autowired
    PassengerUserMapper userMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public PassengerIdcard getIdcardInfo(String openid) {
        if(StringUtils.isEmpty(openid)){
            throw new ServiceException("请先登录");
        }

        PassengerIdcard idcard = idcardMapper.selectIdcardByOpenid(openid);
        return idcard;
    }

    @Override
    public Integer updateIdcard(@Validated PassengerIdcard idcard) {
        Integer result = idcardMapper.updateIdcardByOpenid(idcard);
        return result;
    }

    @Override
    public Integer saveIdcard(@Validated PassengerIdcard idcard) {
        Integer result = idcardMapper.saveIdcard(idcard);

        PassengerUser user = new PassengerUser();
        user.setOpenid(idcard.getOpenid());
        user.setState(1);

        userMapper.updateUser(user);

        return result;
    }
}
