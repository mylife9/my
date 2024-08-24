package com.ruoyi.taxi.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-24 09:10
 */
@Component
public class OrderUtil {

    public static String createOrderId(String openid){
        String orderId = null;

        String openidEnd = openid.substring(openid.length() - 2);

        Random random = new Random();
        int num = 1000 + random.nextInt(9000);

        orderId = "CC-"+openidEnd+System.currentTimeMillis()+""+num;

        return orderId;
    }
}
