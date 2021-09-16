package com.example.redis.controller;

import com.example.redis.common.CommonResult;
import com.example.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:redis测试controller
 * @Author songxl
 * @Date 2021/9/16
 */
@Api(tags = "RedisController", value = "redis测试")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @ApiOperation("字符串类型")
    @GetMapping("/string")
    public CommonResult<Object> stringTest(@RequestParam String key, @RequestParam String data) {
        redisService.set(key, data);
        return CommonResult.success(redisService.get(key));
    }

    @ApiOperation("hash类型")
    @GetMapping("/hash")
    public CommonResult<Object> hashTest(@RequestParam String key, @RequestParam String hkey, @RequestParam String data) {
        redisService.hSet(key, hkey, data);
        return CommonResult.success(redisService.hGetAll(key));
    }

    @ApiOperation("set类型")
    @GetMapping("/set")
    public CommonResult<Object> setTest(@RequestParam String key, @RequestParam List data) {
        redisService.sAdd(key, data);
        return CommonResult.success(redisService.sMembers(key));
    }

    @ApiOperation("list类型")
    @GetMapping("/list")
    public CommonResult<Object> listTest(@RequestParam String key, @RequestParam List data) {
        redisService.lPushAll(key,data);
        return CommonResult.success(redisService.lRange(key,0,3));
    }
}
