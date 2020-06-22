package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("菜单管理")
@RestController
@Slf4j
@RequestMapping("/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("保存菜单")
    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysMenu record) {
        log.debug("菜单管理-保存菜单,请求参数为：{}",record);
        return HttpResult.ok(sysMenuService.save(record));
    }

    @ApiOperation("删除菜单")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        log.debug("菜单管理-删除菜单,请求参数为：{}",records);
        return HttpResult.ok(sysMenuService.delete(records));
    }
    @ApiOperation("查询导航菜单树")
    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findNavTree")
    public HttpResult findNavTree(@RequestParam String userName) {
        log.debug("菜单管理-查询导航菜单树,请求参数为：{}",userName);
        return HttpResult.ok(sysMenuService.findTree(userName, 1));
    }

    @ApiOperation("查询菜单树")
    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")
    public HttpResult findMenuTree() {
        log.debug("菜单管理-查询菜单树,请求参数为：{}");
        return HttpResult.ok(sysMenuService.findTree(null, 0));
    }

}
