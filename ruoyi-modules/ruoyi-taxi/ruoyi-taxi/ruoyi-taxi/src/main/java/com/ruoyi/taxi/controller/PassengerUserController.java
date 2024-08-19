package com.ruoyi.taxi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.ServiceException;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
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
import com.ruoyi.taxi.utils.OcrUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.http.client.HttpClient;
import org.aspectj.weaver.loadtime.Aj;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    OcrUtil ocrUtil;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody PassengerUser user){
        Integer i = userService.register(user);
        return i>0?success():error();
    }

    @GetMapping("/getCode")
    public AjaxResult getCode(@RequestParam(value = "mobile") String mobile){
        String code = userService.sendCode(mobile);
        return success("验证码发送成功："+code);
    }

    @PostMapping("/updateUser")
    public AjaxResult updateUser(@RequestBody PassengerUser user){
        Integer update = userService.updateUser(user);
        return update>0?success():error();
    }

    @GetMapping("/login")
    public AjaxResult login(@RequestParam(value = "code") String code){
        PassengerUser user = userService.login(code);
        return success(user);
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
    public AjaxResult ocr(@RequestBody MultipartFile file) throws Exception {
        Map map = userService.ocr(file);
        return success(map);
    }

    //驾驶证orc
    @RequestMapping("/driverOcr")
    public AjaxResult driverOcr(@RequestBody MultipartFile file) throws IOException, ParseException {
        Map map = userService.driverOcr(file);
        return success(map);
    }

    @GetMapping("/getUserInfo")
    public AjaxResult getUserInfo(@RequestParam(value = "openid") String openid){
        PassengerUser user = userService.getUserInfo(openid);
        return success(user);
    }

    @RequestMapping("/faceOcr")
    public AjaxResult faceOcr(MultipartFile file) throws IOException {
        String post = ocrUtil.ocrFace(file);

        System.out.println(post);

        return success(post);
    }
}
