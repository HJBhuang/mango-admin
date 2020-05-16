package com.louis.mangoadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 18:24:48
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello(){
        return "Hello Mango";
    }
}
