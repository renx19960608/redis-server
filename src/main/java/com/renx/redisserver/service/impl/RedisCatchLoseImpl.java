package com.renx.redisserver.service.impl;

import com.renx.commom.redis.RedisClient;
import com.renx.redisserver.dao.ProductDao;
import com.renx.redisserver.model.ProductPo;
import com.renx.redisserver.service.RedisCatchLose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RedisCatchLoseImpl implements RedisCatchLose {
    private static final Logger log= LoggerFactory.getLogger(RedisCatchLoseImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private RedisClient redisClient;
    private Lock lock=new ReentrantLock();
    private Map<String,String> maplock=new ConcurrentHashMap();//实现锁功能
    //模拟缓存雪崩场景
    @Override
    public Object test(int id) {
        String key=String.valueOf(id);
        String value=redisClient.get(0,key);
        if(value!=null){
            log.info(Thread.currentThread().getName()+"缓存中取---------->"+value);
            return value;
        }
        value=productDao.getProductPoById(id).getStock().toString();
        log.info(Thread.currentThread().getName()+"数据库中取---------->"+value);
        redisClient.setex(0,key,120,value);
        return value;
    }

    //粗粒度锁
    @Override
    public Object testLock(int id) {
        String key=String.valueOf(id);
        String value=redisClient.get(0,key);
        if(value!=null){
            log.info(Thread.currentThread().getName()+"缓存中取---------->"+value);;
            return value;
        }
        lock.lock();//控制数据库访问量
        try {
            value=redisClient.get(0,key);
            if(value!=null){
                log.info(Thread.currentThread().getName()+"缓存中取---------->"+value);;
                return value;
            }
            value=productDao.getProductPoById(id).getStock().toString();
            log.info(Thread.currentThread().getName()+"数据库中取---------->"+value);
            redisClient.setex(0,key,120,value);
            return value;
        }finally {
            lock.unlock();//释放锁
        }

    }
    //细粒度锁+缓存降级
    @Override
    public Object mapLock(int id) {
        String key=String.valueOf(id);
        String value=redisClient.get(0,key);
        if(value!=null){
            log.info(Thread.currentThread().getName()+"缓存中取---------->"+value);;
            return value;
        }
        boolean lock=true;//标志位
        try {
            lock=maplock.putIfAbsent(key,key+"")==null;
            if(lock){//获得锁
                value=redisClient.get(0,key);
                if(value!=null){
                    log.info(Thread.currentThread().getName()+"缓存中取---------->"+value);
                    return value;
                }
                value=productDao.getProductPoById(id).getStock().toString();
                log.info(Thread.currentThread().getName()+"数据库中取---------->"+value);
                redisClient.setex(0,key,120,value);
                //数据备份(永久数据)
                redisClient.set(1,key,value);
                return value;
            }else {//缓存降级
                value=redisClient.get(1,String.valueOf(id));//获得备份缓存
                if(value==null){
                    value="0";//返回固定值，异常值
                    log.info(Thread.currentThread().getName()+"缓存降级返回异常值---------->"+value);
                    return value;
                }
                log.info(Thread.currentThread().getName()+"缓存降级返回备份值---------->"+value);//存在数据不一致
                return value;
            }
        }finally {
            maplock.remove(key);//释放锁
        }

    }
}
