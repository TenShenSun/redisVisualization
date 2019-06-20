package com.redis.visual.redis;


import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Redis各种类型之间的通用操作
 */
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 删除给定的一个key 。
     * 不存在的 key 会被忽略。
     * 返回结果中data中为影响的行数
     * @param key
     * @return
     */
    public Result del(String key) {
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        Result result = new Result(jedis.del(key));
        return result;
    }

    /**
     * 查询给定的key是否存在，如果不存在的话返回结果中data中存false。
     * 存在的话返回结果中data中存true
     * @param key
     * @return
     */
    public Result exist(String key) {
        Jedis jedis = jedisPool.getResource();
        Result result = new Result(jedis.exists(key));
        return result;
    }

    /**
     * 查询指定key内存储的类型，如果找不到这个键，在data中返回none，
     * 其余的各种数据类型会以字符串的形式将类型返回
     * @param key
     * @return
     */
    public Result type(String key) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.type(key));
            return result;
        }
    }

    /**
     * 重命名一个key，如果key或者value不存在时，返回error。
     * 如果key和value相同，返回error。
     *
     * @param key
     * @param newkey
     * @return
     */
    public Result rename(String key, String newkey) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else if (newkey == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else if (key.equals(newkey)) {
            return Result.error(CodeMsg.KEY_NEWKEY_EQUAL_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.rename(key, newkey);
                return Result.success(CodeMsg.SUCCESS);
            }catch (JedisDataException exception){
                return Result.error(CodeMsg.NO_SUCH_KEY_ERROR);
            }
        }
    }

    /**
     * @param key
     * @param type
     * @return
     */
    public Result get(String key, String type) {
        Boolean isAll = type.equals("*");
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys(key);
        Map<String, Object> map = new HashMap<String, Object>();
        for (String k : keys
        ) {
            String keyType = jedis.type(k);
            if ((isAll || "string".equals(type)) && "string".equals(keyType)) {
                map.put(k, jedis.get(k));
            } else if ((isAll || "set".equals(type)) && "set".equals(keyType)) {
                map.put(k, jedis.smembers(k));
            } else if ((isAll || "list".equals(type)) && "list".equals(keyType)) {
                map.put(k, jedis.lrange(k, 0, -1));
            } else if ((isAll || "hash".equals(type)) && "hash".equals(keyType)) {
                map.put(k, jedis.hgetAll(k));
            } else if ((isAll || "zset".equals(type)) && "zset".equals(keyType)) {
                map.put(k, jedis.zrange(k, 0, -1));
            }
        }
        Result result = new Result(map);
        return result;
    }
}
