package com.louis.mango.admin.controller;

import com.louis.common.http.HttpResult;
import com.louis.mango.admin.service.SysDictService;
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
@RestController
@RequestMapping("/dict")
public class SysDIctController {
    @Autowired
    SysDictService sysDictService;
    @GetMapping("findPageByLabel")
    public HttpResult findPageByLabel(@RequestParam String label){
        return HttpResult.ok(sysDictService.findPageByLabel(label));
    }
}
