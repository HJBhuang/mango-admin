package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.admin.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 14:39:37
 */
@Api("系统配置")
@RestController
@Slf4j
@RequestMapping("config")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("保存配置")
    @PreAuthorize("hasAuthority('sys:config:add') AND hasAuthority('sys:config:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody SysConfig record) {
        log.debug("系统配置-保存配置,请求参数为:{}",record);
        return HttpResult.ok(sysConfigService.save(record));
    }

    @ApiOperation("删除字典")
    @PreAuthorize("hasAuthority('sys:config:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysConfig> records) {
        log.debug("系统配置-删除字典,请求参数为:{}",records);
        return HttpResult.ok(sysConfigService.delete(records));
    }

    @ApiOperation("分页查询")
    @PreAuthorize("hasAuthority('sys:config:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        log.debug("系统配置-分页查询,请求参数为:{}",pageRequest);
        return HttpResult.ok(sysConfigService.findPage(pageRequest));
    }

    @ApiOperation("根据标签查询")
    @PreAuthorize("hasAuthority('sys:config:view')")
    @GetMapping(value="/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        return HttpResult.ok(sysConfigService.findByLable(lable));
    }

}
