package com.redis.visual.redis;

import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

@Service
public class SetService extends RedisService {
    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     * 当 key 不是集合类型时，返回一个错误。
     *
     * @param key
     * @param value
     * @return
     */
    public Result sadd(String key, String... value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            try {
                Jedis jedis = jedisPool.getResource();
                jedis.sadd(key, value);
                return Result.success(CodeMsg.SUCCESS);
            } catch (JedisDataException exception) {
                return Result.error(CodeMsg.NOT_SUIT_TYPE_ERROR);
            }
        }
    }

    /**
     * 返回集合 key 的基数(集合中元素的数量)。
     *
     * @param key
     * @return
     */
    public Result slen(String key) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.scard(key));
            return result;
        }
    }

    /**
     * 删除元素，格式是：srem (set的key,item项的值)，值可以为多项
     * @param key
     * @param value
     * @return
     */
    public Result srem(String key, String... value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            jedis.srem(key, value);
            return Result.success(CodeMsg.SUCCESS);
        }
    }
}
