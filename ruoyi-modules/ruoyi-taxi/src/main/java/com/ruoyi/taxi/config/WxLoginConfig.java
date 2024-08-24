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
    public static String appid = "wx015737294f3c46ce";
    public static String secret = "28800f1a93e51a74bb9515df28f81686";

    public static String getOpenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

}
