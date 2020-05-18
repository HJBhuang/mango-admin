package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.core.service.CurdService;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/18/0018
 * @time 15:32:20
 */
public interface SysMenuService extends CurdService<SysMenu> {
    /**
     * 通过用户名查询权限
     * @param userName
     * @return
     */
    List<SysMenu> findByUser(String userName);
}
