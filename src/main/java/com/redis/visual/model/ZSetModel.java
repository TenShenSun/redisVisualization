package com.redis.visual.model;

import java.util.List;
import java.util.Map;

public class ZSetModel
{
    private String key;
    private Map<String,Double> scoreMember;
    private List<String> member;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Double> getScoreMember() {
        return scoreMember;
    }

    public void setScoreMember(Map<String, Double> scoreMember) {
        this.scoreMember = scoreMember;
    }

    public List<String> getMember() {
        return member;
    }

    public void setMember(List<String> member) {
        this.member = member;
    }
}
