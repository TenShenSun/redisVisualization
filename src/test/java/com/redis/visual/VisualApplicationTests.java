package com.redis.visual;

import com.redis.visual.redis.*;
import com.redis.visual.result.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisualApplicationTests {

    @Autowired
    private RedisService redisService;
    @Autowired
    private StringService stringService;
    @Autowired
    private ListService listService;
    @Autowired
    private SetService setService;
    @Autowired
    private ZSetService zSetService;

    @Test
    public void contextLoads() {
//        Result result = stringService.get("taotao","*");
        Result result1 = stringService.get("*", "*");
//        Result result2 = stringService.set("aha", "tiansheng", 0);
//        Result result3 = redisService.exist("123321");
//        Result result4 = redisService.exist("gouwazi");
//        Result result5 = redisService.rename("gouwazi", "ergouzi");
//        Result result6 = stringService.get("*","*");
//        Result result = redisService.exist("suntiansheng");
//        Result result2 = redisService.exist("ergouzi");
//        Result result=redisService.del("zxc");
//        Result result3=redisService.del("aha");
//        Result result=redisService.type("*");
        Result result = redisService.rename("zxc", "qwe");
        Result result2 = redisService.rename("ahalo", "aha");
    }

    @Test
    public void ListTest() {
        Result result1 = redisService.get("*", "list");
//        Result result=listService.lrange("grade",0,1);
//        Result result=listService.lpush("grade","left");
//        Result result2=listService.rpush("grade","right");
//        Result result=listService.llen("grade");
//        Result result4=listService.lpop("grade");
//        Result result2=listService.rpop("grade");
        Result result = listService.lset("grade", 0, "new val");
        Result result3 = redisService.get("*", "list");
    }

    @Test
    public void SetTest() {
        Result result1 = redisService.get("*", "set");
        setService.sadd("testSet", "first");
        Result result7 = redisService.get("*", "set");
        setService.sadd("testSet", "second", "third");
        Result result = setService.slen("testSet");
        Result result3 = redisService.get("*", "set");
        Result result2 = setService.srem("testSet", "1");
        Result result8 = redisService.get("*", "set");
        Result result9 = setService.srem("testSet", "2", "3");
    }

    @Test
    public void zSetTest() {

    }

}
