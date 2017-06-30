package com.nchu.tech.jedis.main;

import java.util.List;

/**
 * Created by fujianjian on 2017/6/30.
 */
public class RedisHelper {
    public static final String redisQueue = "redis:queue";

    public void lpush(String key, String value) {
        RedisJava redisJava = RedisJava.getInstance();
        redisJava.getJedis().lpush(key, value);
    }

    public void lpush(String key, String... values) {
        RedisJava redisJava = RedisJava.getInstance();
        redisJava.getJedis().lpush(key, values);
        //redisJava.getJedis().close();
    }

    public List<String> brpop(String key, int timeout) {
        RedisJava redisJava = RedisJava.getInstance();
        List<String> list = redisJava.getJedis().brpop(timeout, key);
        return list;
    }
}
