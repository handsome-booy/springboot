package com.example.demo.util;

import com.example.demo.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;


@Slf4j
public class RedisPoolUtil {
    public static String set(String key,String value){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.set(key,value);
        }catch (Exception e){
            log.error("set key:{} value:{} error",key,value,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        }catch (Exception e){
            log.error("get key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        }catch (Exception e){
            log.error("del key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long expire(String key,Long time){
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, time);
        }catch (Exception e){
            log.error("del key:{} error",key,e);
            RedisPool.returnBrokenResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();
        RedisPoolUtil.set("key","value");
        System.out.println(RedisPoolUtil.get("key"));
        RedisPoolUtil.del("key");
        System.out.println(RedisPoolUtil.get("key"));
    }
}
