package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.common.page.PageRequest;
import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.admin.service.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 15:01:37
 */
@Api("登录日志")
@RestController
@Slf4j
@RequestMapping("/loginlog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation("分页查询")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        log.debug("登录日志-分页查询,请求参数为:{}",pageRequest);
        return HttpResult.ok(sysLoginLogService.findPage(pageRequest));
    }

    @ApiOperation("删除操作日志")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        log.debug("登录日志-分页查询,请求参数为:{}",records);
        return HttpResult.ok(sysLoginLogService.delete(records));
    }

}
