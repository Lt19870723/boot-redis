package com.nchu.tech.jedis.dto;

/**
 * Created by fujianjian on 2017/5/10.
 */
public class Naught {
    private String email;
    private String name;
    private Integer age;

    public Naught(Integer age, String email, String name) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
