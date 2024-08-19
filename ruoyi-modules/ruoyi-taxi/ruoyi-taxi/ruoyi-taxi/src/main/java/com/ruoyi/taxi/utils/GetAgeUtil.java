package com.ruoyi.taxi.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: rouyi
 * @author: xlk
 * @description:
 * @create: 2024-08-20 20:53
 */
public class GetAgeUtil {
    public static String dateStyle;

    /**
     *
     * 根据出生年月日计算年龄
     * @module
     * @author SJT
     * @date 2022/8/31
     * @param birthday
     * @return: int
     */
    public static int getAgeByBirth(Date birthday){

        //获取当前时间
        Calendar cal = Calendar.getInstance();

        //获取出生日期的Calendar对象
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        //如果出生日期大于当前日期，则返回0
        if(cal.before(birthday)){
            return 0;
        }
        //取出当前年月日
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
        int nowDay = cal.get(Calendar.DAY_OF_MONTH);

        //取出出生日期的年月日
        int birthYear = bir.get(Calendar.YEAR);
        int birthMonth = bir.get(Calendar.MONTH);
        int birthDay = bir.get(Calendar.DAY_OF_MONTH);

        //计算年份
        int age = nowYear - birthYear;

        //计算月份和日，看看是否大于当前月日，如果小于则减去一岁
        if(nowMonth < birthMonth || (nowMonth == birthMonth && nowDay < birthDay)){
            age--;
        }
        return age;
    }

}
