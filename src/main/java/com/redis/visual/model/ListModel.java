package com.redis.visual.model;

import java.util.ArrayList;

public class ListModel {
    private String key;
    private ArrayList<String> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }
}
