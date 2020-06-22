package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("机构管理")
@RestController
@Slf4j
@RequestMapping("/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation("保存机构")
    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody SysDept record) {
        log.debug("机构管理-保存机构,请求参数为：{}", record);
        return HttpResult.ok(sysDeptService.save(record));
    }

    @ApiOperation("删除机构")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        log.debug("机构管理-删除机构,请求参数为：{}", records);
        return HttpResult.ok(sysDeptService.delete(records));
    }

    @ApiOperation("查询机构树")
    @PreAuthorize("hasAuthority('sys:dept:view')")
    @GetMapping("/findTree")
    public HttpResult findTree() {
        log.debug("机构管理-查询机构树,请求参数为：{}");
        return HttpResult.ok(sysDeptService.findTree());
    }
}
