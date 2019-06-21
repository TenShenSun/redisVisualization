package com.redis.visual.controller;

import com.redis.visual.model.SetModel;
import com.redis.visual.redis.SetService;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SetController {
    @Autowired
    private SetService setService;

    /**
     * 增加set
     * @param setModel
     * @return
     */
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Result add(@RequestBody SetModel setModel) {
        return setService.sadd(setModel.getKey(), setModel.getValue().toArray(new String[setModel.getValue().size()]));
    }

    /**
     * 根据key获取set
     * @param key
     * @return
     */
    @RequestMapping(value = "/set/{key}", method = RequestMethod.GET)
    public Result get(@PathVariable String key) {
        return setService.get(key, "set");
    }

    /**
     * 根据key将整个set删除
     * @param key
     * @return
     */
    @RequestMapping(value = "/set/{key}", method = RequestMethod.DELETE)
    public Result deleteAll(@PathVariable String key) {
        return setService.del(key);
    }

    /**
     * 根据key和value将set中部分value删除
     * @param setModel
     * @return
     */
    @RequestMapping(value = "/set", method = RequestMethod.DELETE)
    public Result deleteValue(@RequestBody SetModel setModel) {
        return setService.srem(setModel.getKey(), setModel.getValue().toArray(new String[setModel.getValue().size()]));
    }

}
