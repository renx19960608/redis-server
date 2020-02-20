package com.renx.redisserver;


import com.renx.redisserver.service.RedisCatchLose;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServerApplicationTests {
	@Resource
	private RedisCatchLose redisCatchLose;
	private int threadNum=2000;
	private CountDownLatch cdl =new CountDownLatch(threadNum);
	private long startTime;
	@Before
	public void before(){
		System.out.println("开始测试");
		startTime=System.currentTimeMillis();
	}
	@Test
	public void contextLoads() throws InterruptedException{
		Thread[] threads=new Thread[threadNum];
		for(int i=0;i<threadNum;i++){
			Thread thread=new Thread(new ThreadTest());
			threads[i]=thread;
			thread.start();
			cdl.countDown();
		}
		for(Thread thread:threads){
			thread.join();
		}
	}
	@After
	public void after(){
		String time=String.valueOf(System.currentTimeMillis()-startTime);
		System.out.println("耗时："+time);
	}
	private class ThreadTest implements Runnable{
		public void run(){
			try {
				cdl.await();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			//redisCatchLose.testLock(1);
			//redisCatchLose.testLock(1);
			redisCatchLose.mapLock(1);
		}
	}
}
