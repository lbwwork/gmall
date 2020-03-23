package com.xiaobao.gmall.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis工具类
 * @author lbw
 */
public class RedisUtil {
    /**
     * 创建连接池
     */
    private JedisPool jedisPool;

    /**
     * 初始化连接池
     * 默认0号库
     */
    public void initJedisPool(String host,int port,int database){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(200);
        //设置等待时间
        jedisPoolConfig.setMaxWaitMillis(10*1000);
        //设置最小剩余数
        jedisPoolConfig.setMinIdle(10);
        //当用户获取到一个连接池之后，自检查是否可以使用
        jedisPoolConfig.setTestOnBorrow(true);
        //开启缓冲池
        jedisPoolConfig.setBlockWhenExhausted(true);
        //连接池配置类 host,port,timeout,password
        jedisPool = new JedisPool(jedisPoolConfig,host,port,20*1000,null);
    }

    /**
     * 获取连接
     * @return
     */
    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
