package com.renx.redisserver.service;

public interface RedisCatchLose {
    public Object test(int id);
    public Object testLock(int id);
    public Object mapLock(int id);
}
