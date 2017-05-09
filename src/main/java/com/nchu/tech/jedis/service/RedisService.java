package com.nchu.tech.jedis.service;

import java.util.Map;

/**
 * Created by fujianjian on 2017/5/5.
 */
public interface RedisService {

    void set(String key, String value);

    int setnx(String key, String value);

    int del(String key);

    void hset(String key, String field, String value);

    void hmset(String key, Map<byte[], byte[]> pairs);

    /***
     * 获取redis中湖北消金的地区编码
     * @return
     */
    Map getHBXJRegionOnRedis();
}
