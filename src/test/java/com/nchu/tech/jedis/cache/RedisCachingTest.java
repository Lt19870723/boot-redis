package com.nchu.tech.jedis.cache;

import com.nchu.tech.jedis.dto.Naught;
import com.nchu.tech.jedis.service.common.CommonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * redis 缓存测试
 * Created by fujianjian on 2017/5/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCachingTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CommonService commonService;

    @Test
    public void testSimpleCache() {
        this.stringRedisTemplate.opsForValue().set("aaa", "simple cache demo");
        Assert.assertEquals("simple cache demo", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObjCache() {
        Naught naught = new Naught(10, "nchu@nchu.com", "nchu");
        ValueOperations<String, Naught> operations = this.redisTemplate.opsForValue();
        operations.set("shenma:naught:v", naught);
        operations.set("shenma:naught:n", naught, 1, TimeUnit.SECONDS);
        boolean exist = redisTemplate.hasKey("naught");
        if (exist) {
            System.out.println(redisTemplate.opsForValue().get("naught"));
        } else {
            System.out.println("the object cache is not success");
        }
    }

    @Test
    public void testGetAllHBXJRegion() {
        this.commonService.getAllHBXJRegion();
    }

}
