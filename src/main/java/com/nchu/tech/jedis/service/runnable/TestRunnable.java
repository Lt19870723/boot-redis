package com.nchu.tech.jedis.service.runnable;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 Task
 * Created by fujianjian on 2017/6/23.
 */
@Slf4j
public class TestRunnable implements Runnable {

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
        log.info("this is test runnable !!!!");
    }
}
