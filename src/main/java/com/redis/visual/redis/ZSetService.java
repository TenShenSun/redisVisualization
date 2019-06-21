package com.redis.visual.redis;

import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class ZSetService extends RedisService {
    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     * score 值可以是整数值或双精度浮点数。
     * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * @param key
     * @param scoreMember
     * @return
     */
    public Result zadd(String key, Map<String,Double> scoreMember){
        if (key==null){
            return Result.error(CodeMsg.KEY_ERROR);
        }else {
            Jedis jedis=jedisPool.getResource();
            jedis.zadd(key,scoreMember);
            return Result.success(CodeMsg.SUCCESS);
        }
    }

    /**
     * 返回有序集 key 的基数
     * @param key
     * @return
     */
    public Result zlen(String key){
        if (key==null){
            return Result.error(CodeMsg.KEY_ERROR);
        }else {
            Jedis jedis=jedisPool.getResource();
            Result result=new Result(jedis.zcard(key));
            return result;
        }
    }

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * @param key
     * @param value
     * @return
     */
    public Result zrem(String key, String... value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            jedis.zrem(key, value);
            return Result.success(CodeMsg.SUCCESS);
        }
    }
}
