package com.ruoyi.taxi.service;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.RegVo;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.xml.transform.Result;
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
    PassengerUserMapper passengerUserMapper;
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer registerUser(PassengerUser user) {
        if(StringUtils.isEmpty(user.getPassengerPhone())){
            throw new ServiceException("手机号不能为空");
        }

        if(StringUtils.isEmpty(user.getCode())){
            throw new ServiceException("验证码不能为空");
        }

        String codeKey = "code:"+user.getPassengerPhone();
        String code = stringRedisTemplate.opsForValue().get(codeKey);
        if(!user.getCode().equals(code)){
            throw new ServiceException("验证码错误");
        }

        PassengerUser login = passengerUserMapper.login(user.getPassengerPhone());
        if(login != null){
            throw new ServiceException("该用户已经注册，不能重复注册");
        }

        Integer integer = passengerUserMapper.registerUser(user);

        return integer;
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

}
