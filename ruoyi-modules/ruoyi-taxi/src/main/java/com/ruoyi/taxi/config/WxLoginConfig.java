package com.ruoyi.taxi.config;

import org.springframework.stereotype.Component;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-17 16:13
 */
@Component
public class WxLoginConfig {
    public static String appid = "wxb6c1737fc0824e5c";
    public static String secret = "cf6bca4a2f083b026235d16d6f51ab55";

    public static String getOpenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

}
