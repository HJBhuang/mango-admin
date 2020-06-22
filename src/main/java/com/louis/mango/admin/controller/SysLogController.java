package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.model.SysLog;
import com.louis.mango.admin.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 15:11:42
 */
@Api("操作日志")
@RestController
@Slf4j
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    @ApiOperation("分页查询")
    @PreAuthorize("hasAuthority('sys:log:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        log.debug("操作日志-分页查询,请求参数为：{}",pageRequest);
        return HttpResult.ok(sysLogService.findPage(pageRequest));
    }
    @ApiOperation("删除日志")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysLog> records) {
        log.debug("操作日志-删除日志,请求参数为：{}",records);
        return HttpResult.ok(sysLogService.delete(records));
    }
}
