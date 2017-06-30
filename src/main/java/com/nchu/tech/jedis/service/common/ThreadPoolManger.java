package com.nchu.tech.jedis.service.common;

import com.nchu.tech.jedis.service.runnable.TestRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by fujianjian on 2017/6/23.
 */
public class ThreadPoolManger {

    private static ExecutorService testPoolService;

    public static synchronized ExecutorService getService() {
        if (testPoolService == null) {
            testPoolService = Executors.newFixedThreadPool(2);
        }
        return testPoolService;
    }

    public static void addTask(Runnable task) {
        testPoolService.submit(task);
        testPoolService.execute(task);
    }

    public static void main(String[] args) throws InterruptedException {
        getService();
        TestRunnable test1 = new TestRunnable();
        TestRunnable test2 = new TestRunnable();
        addTask(test1);
        TimeUnit.SECONDS.sleep(3);
        addTask(test2);

    }
}
