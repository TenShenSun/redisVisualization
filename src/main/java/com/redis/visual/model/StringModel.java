package com.redis.visual.model;

/**
 * String类型的model
 */
public class StringModel {

    /** 键值 **/
    private String key;
    /** value **/
    private String value;
    /** 存活时间 单位(s) **/
    private int ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
