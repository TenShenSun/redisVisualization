package com.redis.visual.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * String 根据key获得vlaue
     */
    /*public <T> T get(String key,Class<T> clazz){
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        String str = jedis.get(key);
        return (T)str;
    }*/
    public String get(String key) {
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        String str = jedis.get(key);
        return str;
    }
}
