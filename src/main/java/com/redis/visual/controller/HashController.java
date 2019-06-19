package com.redis.visual.controller;

import com.redis.visual.model.HashModel;
import com.redis.visual.redis.HashService;
import com.redis.visual.result.CodeMsg;
import com.redis.visual.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HashController {

    @Autowired
    private HashService hashService;

    /**
     * 增加一个hash类型
     * @param hashModel
     * @return
     */
    @RequestMapping(value = "/hash",method = RequestMethod.PUT)
    public Result addHash(@RequestBody HashModel hashModel){
        //key不能为空
        if(hashModel.getKey()==null){
            return Result.error(CodeMsg.KEY_ERROR);
        }
        //hash类型的values不能为空
        if(hashModel.getValues()==null){
            return Result.error(CodeMsg.KEY_FIELD_VALUE_ERROR);
        }
        return hashService.hset(hashModel.getKey(),hashModel.getValues(),hashModel.getTtl());
    }


    /**
     * 刪除一个hash的key
     * @param key
     * @return
     */

    @RequestMapping(value="/hash/delKey/{key}",method = RequestMethod.DELETE)
    public Result delHashKey(@PathVariable  String key){
        return hashService.hdelKey(key);
    }

    /**
     * 删除一个hash的key对应的field
     * @param key
     * @param field
     * @return
     */
    @RequestMapping(value="/hash/delField/{key}/{field}",method = RequestMethod.DELETE)
    public Result delHashFeild(@PathVariable String key,@PathVariable String field){
        return hashService.hdel(key,field);
    }

    /**
     * 根据key获取hash的所有的field和value
     * @param key
     * @return
     */
    @RequestMapping(value="/hash/{key}",method = RequestMethod.GET)
    public Result getHash(@PathVariable  String key){
        return Result.success(hashService.hgetall(key));
    }



}
