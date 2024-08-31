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
    public static String appid = "wx792ccc22586f6e6f";
    public static String secret = "32756f6f9a0f1c5563cb6b50005e9d8f";

    public static String getOpenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

}
