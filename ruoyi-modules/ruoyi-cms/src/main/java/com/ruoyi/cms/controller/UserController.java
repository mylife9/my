package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.TbUser;
import com.ruoyi.cms.service.impl.UserServiceImpl;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/23 23:05
 * @注释
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("login")
    public AjaxResult login(@RequestBody TbUser user){
        TbUser u = userService.findUser(user);
        if (u==null){
            return   error("账号不存在");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(user.getPassword(),u.getPassword())){
            return error("密码错误");
        }
        //生成JWT
        Map claims=new HashMap();
        claims.put("id",u.getId());
        claims.put("username",u.getUsername());
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "pinxixi").setClaims(claims).compact();

        Map result=new HashMap();
        result.put("token",jwt);
        result.put("username",u.getUsername());

        stringRedisTemplate.opsForValue().set("user:"+u.getId(),jwt,30, TimeUnit.MINUTES);

        return  success(result);
    }

}
