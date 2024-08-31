package com.ruoyi.taxi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-24 09:10
 */
@Slf4j
@Component
public class OrderUtil {

    public static String createOrderId(String openid){
        //第一个参数 2 表示数据中心ID（datacenterId）。通常用于区分不同的数据中心。
        //第二个参数 1 表示工作机器ID（workerId）。在同一数据中心内，不同工作机器有不同的ID。
        SnowFlake snowFlake = new SnowFlake(2, 1);

        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            //System.out.println(snowFlake.nextId());
//            log.info(String.valueOf(snowFlake.nextId()));
//        }

        // System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        log.info("总耗时：" + (System.currentTimeMillis() - start));

        String orderId = null;
//
//        String openidEnd = openid.substring(openid.length() - 2);
//
//        Random random = new Random();
//        int num = 1000 + random.nextInt(9000);
//
//        orderId = "CC-"+openidEnd+System.currentTimeMillis()+""+num;

         orderId = String.valueOf(snowFlake.nextId());


        return orderId;
    }
}
