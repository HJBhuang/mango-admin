package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.mapper.SysRoleMapper;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 */
@Api("角色控制器")
@RestController
@Slf4j
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @ApiOperation("保存角色")
    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysRole record) {
        log.debug("角色控制器-保存角色,请求参数为：{}",record);
        SysRole role = sysRoleService.findById(record.getId());
        if(role != null) {
            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
                return HttpResult.error("超级管理员不允许修改!");
            }
        }
        // 新增角色
        if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
            return HttpResult.error("角色名已存在!");
        }
        return HttpResult.ok(sysRoleService.save(record));
    }

    @ApiOperation("删除角色")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        log.debug("角色控制器-删除角色,请求参数为：{}",records);
        return HttpResult.ok(sysRoleService.delete(records));
    }

    @ApiOperation("分页查找")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        log.debug("角色控制器-分页查找,请求参数为：{}",pageRequest);
        return HttpResult.ok(sysRoleService.findPage(pageRequest));
    }


    @ApiOperation("查询全部")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findAll")
    public HttpResult findAll() {
        log.debug("角色控制器-查询全部,请求参数为：{}");
        return HttpResult.ok(sysRoleService.findAll());
    }

    @ApiOperation("查询角色菜单")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        log.debug("角色控制器-查询角色菜单,请求参数为：{}");
        return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
    }

    @ApiOperation("保存角色菜单")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        log.debug("角色控制器-保存角色菜单,请求参数为：{}");
        for(SysRoleMenu record:records) {
            //TODO 不允许在controller 直接使用mapper 需要改动
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok(sysRoleService.saveRoleMenus(records));
    }
}
