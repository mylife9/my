package com.ruoyi.coupons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: ruoyi
 * @author: M
 * @description:
 * @create: 2024-08-19 17:15
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

//    @Value("${spring.redis.password}")
//    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

//    @Value("${spring.redis.lettice.pool.max-wait}")
//    private long maxWaitMillis;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
//        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
}
