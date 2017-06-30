package com.nchu.tech.jedis.main;

import redis.clients.jedis.Jedis;

/**
 * redis 一些功能 Java 实现
 * Created by fujianjian on 2017/6/30.
 */
public class RedisJava {

    private static RedisJava instance;

    private Jedis jedis;

    private RedisJava() {
        jedis = new Jedis("localhost", 6379, 30);
    }

    public synchronized static RedisJava getInstance() {
        /*if (instance == null) {
            instance = new RedisJava();
        }*/
        instance = new RedisJava();
        return instance;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
