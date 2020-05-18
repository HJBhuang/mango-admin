package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysMenu;

import java.util.List;

/**
 * 菜单管理
 */
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findAll();

    List<SysMenu> findByUserName(String userName);
}