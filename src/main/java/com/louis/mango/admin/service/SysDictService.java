package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDict;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/17/0017
 * @time 15:09:58
 */
public interface SysDictService extends CurdService<SysDict> {
    /**
     * 根据标签名查找
     * @param label
     * @return
     */
    List<SysDict> findPageByLabel(String label);
}
