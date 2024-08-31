package com.ruoyi.order.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: ruoyi-car
 * @author: 段帅虎
 * @description:
 * @create: 2024-08-22 15:06
 */
public class DateUtils {
    /**
     * @Description:日期格式化
     * @Author: dsh
     * @Date: 2024/8/22 星期四 16:13
     *
     * * @return: java.lang.String
     *
     */
    public static String today() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:");
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    /**
     * @Description:当天剩余时间（秒）
     * @Author: dsh
     * @Date: 2024/8/22 星期四 16:12
     *
     * * @return: long
     *
     */
    public static long timeRemaining() throws ParseException {
        //获取当前时间
        long now = System.currentTimeMillis();
        //定义日期格式化
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间  距离当天凌晨  秒数 也就是今天过了多少秒
        long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime()))/1000;
        //当前时间  距离当天晚上23:59:59  秒数 也就是今天还剩多少秒
        long TimeNext = 24*60*60 - overTime;
        return TimeNext;
    }
}
