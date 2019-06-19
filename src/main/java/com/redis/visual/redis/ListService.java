package com.redis.visual.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

@Service
public class ListService {
    @Autowired
    private JedisPool jedisPool;

    //public Result
}
