package com.ruoyi.taxi.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.taxi.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: rouyitaxi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 20:10
 */
@RestController
@RequestMapping("/passengerUser")
public class PassengerUserController extends BaseController {
    @Autowired
    PassengerUserService userService;

        @PostMapping("/register")
    public AjaxResult register(@RequestBody PassengerUser user){
        Integer i = userService.registerUser(user);

        return i>0?success():error();
    }

    @GetMapping("/getCode/{mobile}")
    public AjaxResult getCode(@PathVariable(value = "mobile") String mobile){
        String code = userService.sendCode(mobile);

        return success("验证码发送成功："+code);
    }
}
