package com.redis.visual.redis;


import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Service
public class StringService {

    @Autowired
    private JedisPool jedisPool;

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public Result set(String key,String value,Integer ttl){
        if(key==null){
            return Result.error(CodeMsg.KEY_ERROR);
        }else{
            Jedis jedis = jedisPool.getResource();
            if(ttl == 0) {
                //直接保存
                jedis.set(key, value);
            }else {
                //设置过期时间
                jedis.setex(key, ttl, value);
            }
            return Result.success(CodeMsg.SUCCESS);
        }
    }



    /**
     *
     * @param key
     * @return
     */
    public Result del(String key){
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        Result result = new Result(jedis.del(key));
        return result;
    }

    /**
     *
     * @param key
     * @return
     */
    public Result get(String key){
        Jedis jedis = null;
        jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys(key);
        Map<String,String> map = new HashMap<String,String>();
        for (String k:keys
             ) {
            map.put(k,jedis.get(k));
        }
        Result result = new Result(map);
        return result;
    }


}
