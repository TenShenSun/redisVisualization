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
     * 重命名一个key，如果key或者value不存在时，返回error。<br/>
     * 如果key和value相同，返回error。<br/>
     * 如果key不存在数据库中，返回error。<br/>
     * 操作成功返回success。
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
     * 根据名称和类型约束选取数据，什么类型都能取
     * 两个参数，第一个是对key名称的约束，第二个是对key类型的约束，<br/>
     * key支持glob风格通配符格式：<br/>
     * ？　　匹配一个字符<br/>
     * *　　 匹配任意字符<br/>
     * []      匹配中括号内的任一字符，可以用 - 来表示范围<br/>
     * \x     匹配字符x，用于转义符号<br/>
     *  type可以为*或者类型的字符串。
     * 返回结果在data中以一个hashmap的形式出现
     * @param key
     * @param type
     * @return
     */
    public Result get(String key, String type) {
        if (key==null){
            return Result.error(CodeMsg.KEY_ERROR);
        }
        if ("*".equals(type)||"string".equals(type)||"list".equals(type)||"set".equals(type)
                ||"zset".equals(type)||"hash".equals(type)){

        }else {
            return Result.error(CodeMsg.NO_SUCH_TYPE_ERROR);
        }
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
