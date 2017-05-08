package com.nchu.tech.jedis.service;

import com.nchu.tech.jedis.BootRedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by fujianjian on 2017/5/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootRedisApplication.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testSet() {
        int flag = 0;
        do {
            this.redisService.set(String.format("test#%d", flag), String.format("value::%d", flag));
            flag++;
        } while (flag<10);

        System.out.println("======================set 10 redis key completed================================");
    }

    @Test
    public void testTenSet() {
        System.out.println("======================start 10 redis key================================");
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Start set %d key, current time: %s", i, System.nanoTime()));
            this.redisService.set(String.format("test#%d", i), String.format("value::%d", i));
            System.out.println(String.format("End set %d key, current time: %s", i, System.nanoTime()));
        }
        System.out.println("======================completed 10 redis key================================");
    }

    @Test
    public void testSingleSet() {
        this.redisService.set(String.format("test#%d", 1), String.format("value::%d", 1));
    }

    @Test
    public void testHashSet() {
        this.redisService.hset(null, "00021", "上海市");
    }
}
