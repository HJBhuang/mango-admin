package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:25:59
 */
@Api(value = "系统用户")
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @ApiOperation("查询所有用户")
    @GetMapping("findAll")
    public Object findAll(){
        return sysUserService.findAll();
    }

    @ApiOperation("带条件分页查询用户")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }
}
