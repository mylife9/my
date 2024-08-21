package com.ruoyi;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/***
 *@Description:司机入驻启动类
 * @return null
 * @author FFwy
 *@date 2024/8/15 20:39
 *
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class DriverEntryApp {
    public static void main(String[] args) {
        SpringApplication.run(DriverEntryApp.class,args);
    }
}