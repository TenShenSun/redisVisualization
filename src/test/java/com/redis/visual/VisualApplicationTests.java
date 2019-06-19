package com.redis.visual;

import com.redis.visual.redis.RedisService;
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

	@Test
	public void contextLoads() {
		System.out.println(redisService.get("taotao"));
		System.out.println(redisService.get("*"));
	}



}
