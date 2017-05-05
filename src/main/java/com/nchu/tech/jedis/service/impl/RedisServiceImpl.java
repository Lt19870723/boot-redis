package com.nchu.tech.jedis.service.impl;

import com.nchu.tech.jedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by fujianjian on 2017/5/5.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public int del(String key) {
        RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        return 0;
    }

    @Override
    public void set(String key, String value) {
        RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        redisConnection.set(key.getBytes(), value.getBytes());
    }

    @Override
    public int setnx(String key, String value) {
        return 0;
    }
}
