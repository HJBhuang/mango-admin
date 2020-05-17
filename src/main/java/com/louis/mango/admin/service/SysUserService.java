package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:23:19
 */
public interface SysUserService extends CurdService<SysUser> {

    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();
}
