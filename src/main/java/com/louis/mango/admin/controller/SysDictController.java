package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/17/0017
 * @time 15:14:46
 */
@Api("数字字典")
@RestController
@Slf4j
@RequestMapping("/dict")
public class SysDictController {
    @Autowired
    SysDictService sysDictService;

    @ApiOperation("保存字典")
    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysDict record){
        log.debug("数字字典-保存字典,请求参数为:{}",record);
        return HttpResult.ok(sysDictService.save(record));
    }

    @ApiOperation("删除字典")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysDict> records){
        log.debug("数字字典-删除字典,请求参数为:{}",records);
        return HttpResult.ok(sysDictService.delete(records));
    }

    @ApiOperation("分页查询")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        log.debug("数字字典-分页查询,请求参数为:{}",pageRequest);
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    @ApiOperation("根据标签查询")
    @PreAuthorize("hasAuthority('sys:dict:view')")
    @GetMapping(value="/findByLable")
    public HttpResult findByLable(@RequestParam String lable) {
        log.debug("数字字典-根据标签查询,请求参数为:{}",lable);
        return HttpResult.ok(sysDictService.findByLable(lable));
    }

    @ApiOperation("通过标签查询数据字典")
    @GetMapping("findPageByLabel")
    public HttpResult findPageByLabel(@RequestParam String label){
        log.info("数字字典-查询数据字典，请求参数为：{}",label);
        return HttpResult.ok(sysDictService.findPageByLabel(label));
    }
}
