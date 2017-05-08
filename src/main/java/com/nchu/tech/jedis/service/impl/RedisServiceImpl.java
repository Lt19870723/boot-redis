package com.nchu.tech.jedis.service.impl;

import com.nchu.tech.jedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by fujianjian on 2017/5/5.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final String DEFAULT_HASH_KEY = "shenma:region";
    private static final String DEFAULT_HBXJ_REGION = "shenma:fund:hbxj:region";

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

    @Override
    public void hset(String key, String field, String value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        String hkey = StringUtils.isEmpty(key) ? DEFAULT_HASH_KEY : key;
        connection.hSet(hkey.getBytes(), field.getBytes(), value.getBytes());
    }

    @Override
    public void hmset(String key, Map<byte[], byte[]> pairs) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        String hkey = StringUtils.isEmpty(key) ? DEFAULT_HBXJ_REGION : key;
        connection.hMSet(hkey.getBytes(), pairs);
    }
}
