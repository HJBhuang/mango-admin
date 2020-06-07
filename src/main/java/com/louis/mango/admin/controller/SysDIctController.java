package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.mango.admin.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjiabao
 * @data 2020/5/17/0017
 * @time 15:14:46
 */
@Api("数字字典")
@RestController
@Slf4j
@RequestMapping("/dict")
public class SysDIctController {
    @Autowired
    SysDictService sysDictService;

    @ApiOperation("通过标签查询数据字典")
    @GetMapping("findPageByLabel")
    public HttpResult findPageByLabel(@RequestParam String label){
        log.info("数字字典-查询数据字典，请求参数为：{}",label);
        return HttpResult.ok(sysDictService.findPageByLabel(label));
    }
}
