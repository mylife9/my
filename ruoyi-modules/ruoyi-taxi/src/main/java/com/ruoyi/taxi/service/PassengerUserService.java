package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.PassengerIdcard;
import com.ruoyi.taxi.domain.PassengerUser;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.IOException;
import java.util.Map;

public interface PassengerUserService {
    Integer register(PassengerUser user);
    String sendCode(String mobile);
    PassengerUser login(String code);
    Integer updateUser(PassengerUser user);
    Map ocr(MultipartFile file) throws Exception;
    PassengerUser getUserInfo(String openid);

}
