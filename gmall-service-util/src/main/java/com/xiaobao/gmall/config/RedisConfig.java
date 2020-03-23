package com.xiaobao.gmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置类
 * Configuration相当于xml文件
 * @author lbw
 */
@Configuration
public class RedisConfig {
    /**
     * disable表示如果未从配置文件中获取host,则默认值为disable
     */
    @Value("${spring.redis.host:disable}")
    private String host;

    @Value("${spring.redis.port:0}")
    private int port;

    @Value("${spring.redis.database:0}")
    private int database;

    /**
     * Bean相当于在xml种创建了一个bean标签
     * @return
     */
    @Bean
    public RedisUtil getRedisUtil(){
        String disable = "disable";
        if (disable.equals(host)){
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.initJedisPool(host,port,database);
        return redisUtil;
    }
}
