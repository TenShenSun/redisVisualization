package com.redis.visual.model;

import java.util.Set;

public class SetModel {
    private String key;
    private Set<String> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<String> getValue() {
        return value;
    }

    public void setValue(Set<String> value) {
        this.value = value;
    }
}
