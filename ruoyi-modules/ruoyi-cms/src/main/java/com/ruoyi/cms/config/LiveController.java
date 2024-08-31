package com.ruoyi.cms.config;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/19 22:04
 * @注释
 */
@RestController
@RequestMapping("/live")
@CrossOrigin
public class LiveController extends BaseController {
    @PostMapping("/login")
    public AjaxResult login(String username,String password){
        if (!username.equals("huawei")||!password.equals("123")){
            return  AjaxResult.error();
        }
        //webrtc://202405.push.tlivecloud.com/live/peiqi?txSecret=534e70f6350ca144bc0d295073724fec&txTime=669BC654
        String  txSecret="";
        String txTime="";

      long time=System.currentTimeMillis() /1000+3600;
      txTime=Long.toHexString(time).toUpperCase();

        String param="3e754a87505d644f8b3e44697477f566"+username+txTime;
         txSecret = DigestUtils.md5Hex(param);

        Map map = new HashMap<>();
        map.put("txTime",txTime);
        map.put("txSecret",txSecret);
        return  AjaxResult.success(map);
    }
}
