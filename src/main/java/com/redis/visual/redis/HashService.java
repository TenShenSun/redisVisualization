package com.redis.visual.redis;

import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class HashService extends RedisService {

    /**
     * 设置一个值
     * 对应命令为 ：hset keyName fieldName1 value1 [fileldName1] [value2]
     *
     * @param key
     * @param values
     * @param ttl
     * @return
     */
    public Result hset(String key, HashMap<String, String> values, int ttl) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        }
        Jedis jedis = jedisPool.getResource();
        if (ttl == 0) {
            for (String field : values.keySet()
            ) {
                jedis.hset(key, field, values.get(field));
            }
        } else {
            for (String field : values.keySet()
            ) {
                jedis.hset(key, field, values.get(field));
            }
            //hash没有hsetex()函数，通过expire()函数设置key的过期时间
            jedis.expire(key, ttl);
        }
        return Result.success(CodeMsg.SUCCESS);
    }


    /**
     * 删除一个key中的field (命令支持）
     * 对应命令为 ：hdel keyName fieldName1 [fieldName1]
     *
     * @param key
     * @param field
     * @return
     */
    public Result hdel(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        return new Result(jedis.hdel(key, field));
    }


    /**
     * 删除一个hash的key,(命令不支持，通过删除所有的field实现删除key）
     *
     * @param key
     * @return
     */
    public Result hdelKey(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> fields = jedis.hkeys(key);
        for (String field : fields
        ) {
            jedis.hdel(key, field);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 获取一个hash的key值对应的所有的field和value值
     *
     * @param key
     * @return
     */
    public Result hgetall(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> values = jedis.hgetAll(key);
        return new Result(values);

    }

}
