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
}
