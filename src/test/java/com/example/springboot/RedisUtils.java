package com.example.springboot;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

    public static JedisPool jedisPool;

    static {
        //redis连接池基本配置信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);//最大连接数

        jedisPoolConfig.setMaxIdle(5);//最大空闲数
        String host = "192.168.91.128";
        int port = 6379;
        jedisPool = new JedisPool(jedisPoolConfig, host, port);

    }


    public static Jedis getRedis() {

        Jedis jedis = jedisPool.getResource();
        jedis.auth("huangtao");
        return jedis;
    }

    public static void closeRedis(Jedis jedis) {

        jedis.close();
    }
}
