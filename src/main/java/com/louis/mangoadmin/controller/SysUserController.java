package com.louis.mangoadmin.controller;

import com.louis.mangoadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:25:59
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @GetMapping("findAll")
    public Object findAll(){
        return sysUserService.findAll();
    }

}
