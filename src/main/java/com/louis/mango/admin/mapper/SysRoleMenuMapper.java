package com.louis.mango.admin.mapper;

import com.louis.mango.admin.model.SysRoleMenu;

/**
 * 角色菜单
 */
public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);
}