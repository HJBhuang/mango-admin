package com.louis.mango.admin.service;

import com.louis.common.page.PageRequest;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

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

    SysUser findByName(String name);

    Set<String> findPermissions(String name);

    List<SysUserRole> findUserRoles(Long userId);

    File createUserExcelFile(PageRequest pageRequest);

    List<SysUser> testPagehelper(PageRequest pageRequest);
}
