package com.nchu.tech.jedis.main;

import com.nchu.tech.jedis.service.runnable.ComsumerRunnable;
import com.nchu.tech.jedis.service.runnable.ProductorRunnable;

/**
 * Created by fujianjian on 2017/6/30.
 */
public class RedisDemo {

    public static void main(String[] args) {
        //RedisJava redisJava = RedisJava.getInstance();
        //redisJava.getJedis().set(RedisHelper.redisQueue, "test");

        Thread productThread = new Thread(new ProductorRunnable());
        productThread.start();
        Thread comsumerThread = new Thread(new ComsumerRunnable());
        comsumerThread.start();
        Thread otherComsumerThread = new Thread(new ComsumerRunnable());
        otherComsumerThread.start();
    }
}
