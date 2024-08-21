package com.ruoyi.dirver.service.impl;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.dirver.domain.Driver;
import com.ruoyi.dirver.mapper.DriverMapper;
import com.ruoyi.dirver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 秦耀东请
 * @version 1.0
 * @description: TODO
 * @date 2024/8/20 20:24
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String sendCode(String driverPhone) {
        if(StringUtils.isEmpty(driverPhone)){
            throw new ServiceException("手机号不能为空");
        }

        Random random = new Random();
        int num=1000+random.nextInt(9000);
        String code=""+num;

        String codeKey="code:"+driverPhone;
        stringRedisTemplate.opsForValue().set(codeKey,code,5, TimeUnit.MINUTES);
        return code;
    }


    @Override
    public Integer RegDriver(Driver driver) {
        if(StringUtils.isEmpty(driver.getDriverPhone())){
            throw new ServiceException("手机号不能为空");
        }

        String codeKey = "code:"+driver.getDriverPhoto();
        String code = stringRedisTemplate.opsForValue().get(codeKey);
        if(!driver.getCode().equals(code)){
            throw new ServiceException("验证码错误");
        }

        Driver login = driverMapper.isRegister(driver.getDriverPhoto());
        if(login != null){
            throw new ServiceException("该用户已经注册，不能重复注册");
        }

        Integer integer = driverMapper.save(driver);

        return integer;
    }
}
