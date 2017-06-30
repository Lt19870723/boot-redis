package com.nchu.tech.jedis.service.runnable;

import com.nchu.tech.jedis.main.RedisHelper;

import java.util.List;

/**
 * Created by fujianjian on 2017/6/30.
 */
public class ComsumerRunnable implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        RedisHelper helper = new RedisHelper();
        while (true) {
            List<String> list = helper.brpop(RedisHelper.redisQueue, 30);
            System.out.println(String.format("线程名:%s 结果:%s", Thread.currentThread().getName(), list.toString()));
        }
    }
}
