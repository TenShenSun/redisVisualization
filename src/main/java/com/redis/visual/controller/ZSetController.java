package com.redis.visual.controller;

import com.redis.visual.model.ZSetModel;
import com.redis.visual.redis.ZSetService;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ZSetController {
    @Autowired
    private ZSetService zSetService;

    /**
     * 增加一个zset
     *
     * @param zSetModel
     * @return
     */
    @RequestMapping(value = "/zset", method = RequestMethod.POST)
    public Result add(@RequestBody ZSetModel zSetModel) {
        return zSetService.zadd(zSetModel.getKey(), zSetModel.getScoreMember());
    }

    /**
     * 根据key删除zset
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/zset/{key}", method = RequestMethod.DELETE)
    public Result deleteAll(@PathVariable String key) {
        return zSetService.del(key);
    }

    /**
     * 根据key获取zset
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/zset/{key}", method = RequestMethod.GET)
    public Result get(@PathVariable String key) {
        return zSetService.get(key, "zset");
    }

    /**
     * 根据key和member删除zset中的部分数据
     *
     * @param zSetModel
     * @return
     */
    @RequestMapping(value = "/zset", method = RequestMethod.DELETE)
    public Result deleteValue(@RequestBody ZSetModel zSetModel) {
        return zSetService.zrem(zSetModel.getKey(), zSetModel.getMember().toArray(new String[zSetModel.getMember().size()]));
    }
}
