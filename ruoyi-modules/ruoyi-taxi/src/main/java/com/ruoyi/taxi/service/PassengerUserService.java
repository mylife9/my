package com.ruoyi.taxi.service;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.taxi.domain.PassengerUser;

import javax.xml.transform.Result;

public interface PassengerUserService {
    Integer registerUser(PassengerUser user);

    String sendCode(String mobile);
}
