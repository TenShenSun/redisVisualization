package com.redis.visual.controller;

import com.redis.visual.model.ListModel;
import com.redis.visual.redis.ListService;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListController {
    @Autowired
    private ListService listService;

    /**
     * 插入一个列表，从头插
     *
     * @param listModel
     * @return
     */
    @RequestMapping(value = "/list/left", method = RequestMethod.POST)
    public Result ladd(@RequestBody ListModel listModel) {
        return listService.lpush(listModel.getKey(), listModel.getValue().toArray(new String[listModel.getValue().size()]));
    }

    /**
     * 插入一个列表，从尾插
     *
     * @param listModel
     * @return
     */
    @RequestMapping(value = "/list/right", method = RequestMethod.POST)
    public Result radd(@RequestBody ListModel listModel) {
        return listService.rpush(listModel.getKey(), listModel.getValue().toArray(new String[listModel.getValue().size()]));
    }

    /**
     * 从列表头弹出一个元素
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/list/left/{key}", method = RequestMethod.GET)
    public Result lget(@PathVariable String key) {
        return listService.lpop(key);
    }

    /**
     * 从列表尾弹出一个元素
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/list/right/{key}", method = RequestMethod.GET)
    public Result rget(@PathVariable String key) {
        return listService.rpop(key);
    }

    /**
     * 获取列表，保存为一个(key,list)格式map
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/list/{key}", method = RequestMethod.GET)
    public Result get(@PathVariable String key) {
        return listService.get(key, "list");
    }

    /**
     * 删除一个列表
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/list/{key}", method = RequestMethod.DELETE)
    public Result del(@PathVariable String key) {
        return listService.del(key);
    }
}
