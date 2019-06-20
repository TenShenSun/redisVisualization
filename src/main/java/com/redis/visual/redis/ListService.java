package com.redis.visual.redis;


import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

@Service
public class ListService extends RedisService {

    //public Result

    /**
     * 将一个或多个值添加到列表头部,如果有多个 value 值，
     * 那么各个 value 值按从左到右的顺序依次插入到表头，
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * 如果key不是list类型，会返回错误
     *
     * @param key
     * @param value
     * @return
     */
    public Result lpush(String key, String... value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            try {
                Jedis jedis = jedisPool.getResource();
                jedis.lpush(key, value);
                return Result.success(CodeMsg.SUCCESS);
            } catch (JedisDataException exception) {
                return Result.error(CodeMsg.NOT_SUIT_TYPE_ERROR);
            }
        }
    }

    /**
     * 将一个或多个值添加到列表尾部,如果有多个 value 值，
     * 那么各个 value 值按从左到右的顺序依次插入到表尾，
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
     * 如果key不是list类型，会返回错误
     *
     * @param key
     * @param value
     * @return
     */
    public Result rpush(String key, String... value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);

        } else {
            try {
                Jedis jedis = jedisPool.getResource();
                jedis.rpush(key, value);
                return Result.success(CodeMsg.SUCCESS);
            } catch (JedisDataException exception) {
                return Result.error(CodeMsg.NOT_SUIT_TYPE_ERROR);
            }
        }
    }

    /**
     * LRANGE key start stop
     * 返回列表 key 中指定区间内的元素，区间以偏移量 startIdx 和 endIdx 指定。
     * 下标(index)参数 startIdx 和 endIdx 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @param startIdx
     * @param endIdx
     * @return
     */
    public Result lrange(String key, int startIdx, int endIdx) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.lrange(key, startIdx, endIdx));
            return result;
        }
    }

    /**
     * 移除并返回列表 key 的头元素。
     *
     * @param key
     * @return
     */
    public Result lpop(String key) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.lpop(key));
            return result;
        }
    }

    /**
     * 移除并返回列表 key 的尾元素。
     *
     * @param key
     * @return
     */
    public Result rpop(String key) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.rpop(key));
            return result;
        }
    }

    /**
     * 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * 如果 key 不是列表类型，返回一个错误。
     *
     * @param key
     * @return
     */
    public Result llen(String key) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            Result result = new Result(jedis.llen(key));
            return result;
        }
    }

    /**
     * 设置指定索引的值，格式是：list的key 索引 新的值
     * @param key
     * @param index
     * @param value
     * @return
     */
    public Result lset(String key, int index, String value) {
        if (key == null) {
            return Result.error(CodeMsg.KEY_ERROR);
        } else {
            Jedis jedis = jedisPool.getResource();
            jedis.lset(key, index, value);
            return Result.success(CodeMsg.SUCCESS);
        }
    }
}
