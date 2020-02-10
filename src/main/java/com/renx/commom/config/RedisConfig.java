package com.renx.commom.config;

import com.renx.commom.redis.RedisClient;
import com.renx.commom.redis.RedisClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Bean
    public JedisPool jedisPool(@Value("${jedis.host}") String host,
                               @Value("${jedis.port}")int port,
                               @Value("${jedis.password}") String password,
                               @Value("${jedis.maxIdle}")int maxIdle,
                               @Value("${jedis.maxTotal}")int maxTotal,
                               @Value("${jedis.maxWaitMillis}")int maxWaitMillis){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        JedisPool pool=new JedisPool(config,host,port,3000,password);
        return pool;
    }

    @Bean
    public RedisClient redisClient(){
        return new RedisClientImpl();
    }
}
