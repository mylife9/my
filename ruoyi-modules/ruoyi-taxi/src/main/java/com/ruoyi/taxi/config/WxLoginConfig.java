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
    public static String appid = "wx92417dd01b5dcc7c";

    public static String secret = "39e0760e0663018e4de4d1268befa9bf";

    public static String getOpenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

}
