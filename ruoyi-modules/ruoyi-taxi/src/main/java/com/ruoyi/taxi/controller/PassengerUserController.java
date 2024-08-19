package com.ruoyi.taxi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.sign.Base64;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.config.ShuMaiConfig;
import com.ruoyi.taxi.config.WxLoginConfig;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.ImgVo;
import com.ruoyi.taxi.domain.vo.UserVo;
import com.ruoyi.taxi.mapper.PassengerUserMapper;
import com.ruoyi.taxi.service.PassengerUserService;
import com.ruoyi.taxi.utils.HttpClientUtil;
import com.ruoyi.taxi.utils.ImageUtil;
import com.ruoyi.taxi.utils.OSSFileUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.client.HttpClient;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: rouyitaxi
 * @author: xlk
 * @description:
 * @create: 2024-08-16 20:10
 */
@RestController
@RequestMapping("passengerUser")
public class PassengerUserController extends BaseController {
    @Autowired
    PassengerUserService userService;
    @Autowired
    OSSFileUtil ossFileUtil;
    @Autowired
    PassengerUserMapper userMapper;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody PassengerUser user){
        Integer i = userService.registerUser(user);

        return i>0?success():error();
    }

    @GetMapping("/getCode")
    public AjaxResult getCode(@RequestParam(value = "mobile") String mobile){
        String code = userService.sendCode(mobile);

        return success("验证码发送成功："+code);
    }

    @GetMapping("/login")
    public AjaxResult login(@RequestParam(value = "code") String code){
        System.out.println(code);

        String url = WxLoginConfig.getOpenUrl.replace("APPID", WxLoginConfig.appid).replace("SECRET",WxLoginConfig.secret).replace("JSCODE",code);

        System.out.println(url);

//        String result = restTemplate.getForEntity(url, String.class).getBody();
        String result = HttpClientUtil.get(url, null);

        System.out.println("======================================");
        System.out.println(result);

        JSONObject jsonObject = JSONObject.parseObject(result);

        UserVo userVo = new UserVo();
        userVo.setName("test");
        userVo.setOpenId(jsonObject.get("openid").toString());

        PassengerUser login = userMapper.login(userVo.getOpenId());
        if(login == null){

        }

        userVo.setCode(code);

        System.out.println(userVo.toString());
        return success(userVo);
    }

    @RequestMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String url = ossFileUtil.uploadFile(file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("url",url);
        return success(map);
    }

    //身份证ocr验证
    @RequestMapping("/ocr")
    public void ocr(@RequestBody MultipartFile file) throws IOException {
        long timestamp = System.currentTimeMillis();
        String signStr = ShuMaiConfig.appid+"&"+timestamp+"&"+ShuMaiConfig.appSecurity;
        String sign = DigestUtils.md5Hex(signStr.getBytes());

        String ossUrl = ossFileUtil.uploadFile(file);

        String url = ShuMaiConfig.idcarOcrUrl+"?appid="+ShuMaiConfig.appid+"&timestamp="+timestamp+"&sign="+sign+"&url="+ossUrl;

        String post = HttpClientUtil.post(url, null,null);

        System.out.println(post);
    }

    //驾驶证orc
    @RequestMapping("/driverOcr")
    public void driverOcr(@RequestBody MultipartFile file) throws IOException {
        long timestamp = System.currentTimeMillis();
        String signStr = ShuMaiConfig.appid+"&"+timestamp+"&"+ShuMaiConfig.appSecurity;
        String sign = DigestUtils.md5Hex(signStr.getBytes());

        String ossUrl = ossFileUtil.uploadFile(file);

        String url = ShuMaiConfig.driverOcrUrl+"?appid="+ShuMaiConfig.appid+"&timestamp="+timestamp+"&sign="+sign+"&url="+ossUrl;

        String post = HttpClientUtil.post(url, null,null);

        System.out.println(post);
    }
}
