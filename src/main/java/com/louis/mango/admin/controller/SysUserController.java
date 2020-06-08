package com.louis.mango.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.common.utils.FileUtils;
import com.louis.common.utils.PasswordUtils;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.mapper.SysUserMapper;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:25:59
 */
@Api("系统用户")
@RestController
@Slf4j
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @ApiOperation("新增或修改用户信息")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysUser record) {
        log.info("系统用户-新增或修改用户信息,请求参数为：{}", record);
        SysUser user = sysUserService.findById(record.getId());
        if (user != null) {
            if (SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        if (record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if (user == null) {
                // 新增用户
                if (sysUserService.findByName(record.getName()) != null) {
                    return HttpResult.error("用户名已存在!");
                }
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            } else {
                // 修改用户, 且修改了密码
                if (!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }
        }
        return HttpResult.ok(sysUserService.save(record));
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysUser> records) {
        log.debug("系统用户-删除用户,请求参数为：{}",records);
        for (SysUser record : records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if (SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return HttpResult.error("超级管理员不允许删除!");
            }
        }
        return HttpResult.ok(sysUserService.delete(records));
    }

    @ApiOperation("通过用户名查询相关信息")
    @GetMapping("/findByName")
    public HttpResult findByName(@RequestParam String name) {
        log.debug("系统用户-通过用户名查询相关信息,请求参数为：{}",name);
        return HttpResult.ok(sysUserService.findByName(name));
    }

    @ApiOperation("通过姓名查找权限")
    @GetMapping("/findPermissions")
    public HttpResult findPermissions(@RequestParam String name){
        log.debug("系统用户-通过姓名查找权限,请求参数为：{}",name);
        return HttpResult.ok(sysUserService.findPermissions(name));
    }

    @ApiOperation("通过用户名查找角色")
    @GetMapping(value="/findUserRoles")
    public HttpResult findUserRoles(@RequestParam Long userId) {
        log.debug("系统用户-通过用户名查找角色,请求参数为：{}",userId);
        return HttpResult.ok(sysUserService.findUserRoles(userId));
    }


    @ApiOperation("以excel形式导出用户资料")
    @PostMapping(value="/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) {
        log.debug("系统用户-以excel形式导出用户资料,请求参数为：{}",pageRequest);
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res, file, file.getName());
    }

    @ApiOperation("查询所有用户")
    @GetMapping("findAll")
    public Object findAll() {
        log.info("系统用户-查询所有用户，请求参数为：");
        return sysUserService.findAll();
    }

    @ApiOperation("带条件分页查询用户")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        log.info("系统用户-带条件分页查询用户，请求参数为：{}", pageRequest);
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }


    @ApiOperation("测试pagehelper")
    @PostMapping("/pagehelper")
    public HttpResult pagehelper(@RequestBody PageRequest pageRequest) {
        log.info("带条件分页查询用户，请求参数为：{}", pageRequest);
        return HttpResult.ok(sysUserService.testPagehelper(pageRequest));
    }


}
