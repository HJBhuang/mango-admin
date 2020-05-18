package com.louis.mango.admin.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 18:24:48
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public Object hello(){
        return "Hello Mango";
    }


    /**
     * @RequestParam  使用该注解时 参数必须输
     * @param id
     * @return
     */
    @ApiOperation("测试RequestParam注解")
    @GetMapping("/testRequestParam")
    public Object testRequestParam(@RequestParam String id){
        log.info("测试RequestParam注解，请求参数为：{}",id);
        return "testRequestParam";
    }

    @ApiOperation("测试RequestBody注解")
    @PostMapping("/testRequestBody")
    public Object testRequestBody(@RequestBody String id){
        log.info("测试RequestBody注解，请求参数为：{}",id);
        return "testRequestBody";
    }

}
