package com.redis.visual.controller;


import com.redis.visual.model.StringModel;
import com.redis.visual.redis.StringService;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StringController {
    @Autowired
    private StringService stringService;


    /**
     * 增加一个string类型键值对
     *
     * @param stringModel
     * @return
     */
    @RequestMapping(value = "/string", method = RequestMethod.PUT)
    public Result add(@RequestBody StringModel stringModel) {
        return stringService.set(stringModel.getKey(), stringModel.getValue(), stringModel.getTtl());
    }

    /**
     * 删除一个string类型的键值对
     *
     * @param stringModel
     * @return
     */
    @RequestMapping(value = "/string", method = RequestMethod.DELETE)
    public Result del(@RequestBody StringModel stringModel) {
        return stringService.del(stringModel.getKey());
    }


    /**
     * 通过key获取value
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/string/{key}", method = RequestMethod.GET)
    public Result get(@PathVariable String key) {
//        return stringService.get(key);
        return new Result("nulllllllllllllllllllll");
    }

}
