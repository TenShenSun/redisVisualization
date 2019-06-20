package com.redis.visual;

import com.redis.visual.redis.RedisService;
import com.redis.visual.redis.StringService;
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
        Result result=redisService.rename("zxc","qwe");
        Result result2=redisService.rename("ahalo","aha");
    }


}
