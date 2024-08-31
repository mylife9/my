package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.domain.TbUser;
import com.ruoyi.cms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/24 8:02
 * @注释
 */

@Service
public class UserServiceImpl {

    @Autowired
    UserMapper userMapper;

    public TbUser findUser(TbUser user) {
        return userMapper.findUser(user);
    }
}
