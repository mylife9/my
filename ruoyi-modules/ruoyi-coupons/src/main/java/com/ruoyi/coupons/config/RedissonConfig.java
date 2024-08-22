package com.ruoyi.coupons.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: rouyi
 * @author: M
 * @description:
 * @create: 2024-08-19 20:39
 */
@Component
public class RedissonConfig {
    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
