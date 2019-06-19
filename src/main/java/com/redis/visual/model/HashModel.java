package com.redis.visual.model;

import java.util.HashMap;

/**
 * hash类型的model
 */
public class HashModel {

    /**
     * key
     **/
    private String key;

    /**
     * map类型的values，用来获取redis中hash类型的field和value
     **/
    private HashMap<String, String> values;

    /**
     * 存活时间 单位(s)
     **/
    private int ttl;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
