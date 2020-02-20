package com.renx.redisserver.controller;

import com.renx.redisserver.service.RedisCatchLose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RedisCatchLoseController {
    @Autowired
    private RedisCatchLose redisCatchLose;

    @ResponseBody
    @RequestMapping(value = "/CatchLoseTest")
    public Object test(){
        return redisCatchLose.test(1);
    }
}
