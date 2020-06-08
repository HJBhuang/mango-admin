package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 14:40:48
 */
public interface SysConfigService extends CurdService<SysConfig> {

    /**
     * 根据名称查询
     * @param lable
     * @return
     */
    List<SysConfig> findByLable(String lable);
}
