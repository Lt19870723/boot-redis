package com.nchu.tech.jedis.service;

/**
 * Created by fujianjian on 2017/5/5.
 */
public interface BaseModelService<T, ID> {

    T create(T t);

    T get(ID id);

    T modified(T t);
}
