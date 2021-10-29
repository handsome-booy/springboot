package com.example.demo.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static JedisPool pool;

    private static void initpool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        pool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379,1000*2);
    }

    static{
        initpool();
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("xuxiaojie","xxj");
        returnResource(jedis);
    }

}
