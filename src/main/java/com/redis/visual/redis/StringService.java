package com.redis.visual.redis;


import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class StringService extends RedisService {
    /**
     * @param key
     * @param value
     * @return
     */
    public Result set(String key, String value, Integer ttl) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            if (ttl == 0) {
                //直接保存
                jedis.set(key, value);
            } else {
                //设置过期时间
                jedis.setex(key, ttl, value);
            }
            return Result.success(CodeMsg.SUCCESS);
        }
    }



}
