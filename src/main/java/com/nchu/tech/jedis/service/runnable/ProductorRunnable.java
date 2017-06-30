package com.nchu.tech.jedis.service.runnable;

import com.nchu.tech.jedis.main.RedisHelper;

/**
 * Created by fujianjian on 2017/6/30.
 */
public class ProductorRunnable implements Runnable {

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
        for (int index = 0; index < 100; index++) {
            System.out.println(String.format("threadname %s product good%02d", Thread.currentThread().getName(), index));
            helper.lpush(RedisHelper.redisQueue, String.format("threadname %s product good%3d", Thread.currentThread().getName(), index));
        }
    }
}
