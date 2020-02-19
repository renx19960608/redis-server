package com.renx.redisserver;

import com.renx.commom.redis.RedisClient;
import com.renx.redisserver.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisServerApplicationTests {
	@Resource
	RedisClient redisClient;
		@Resource
		ProductDao productMapper;
		@Test
		void contextLoads() {
			//redisClient.set(0,"renxqwqw","sasasa");
			System.out.println(productMapper.getProductPoById(1).getProductName());
	}

}
