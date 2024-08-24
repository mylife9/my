package com.ruoyi.drools.utils;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author : 暴龙兽
 * @date : 2024-08-19 20:39
 * @description :
 **/
@Component
public class Test {
        @XxlJob("testHandler")
        public void testHandler() throws Exception {
            System.out.println("我执行了------------");
        }
    }


