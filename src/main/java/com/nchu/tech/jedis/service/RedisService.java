package com.nchu.tech.jedis.service;

/**
 * Created by fujianjian on 2017/5/5.
 */
public interface RedisService {

    void set(String key, String value);

    int setnx(String key, String value);

    int del(String key);
}
