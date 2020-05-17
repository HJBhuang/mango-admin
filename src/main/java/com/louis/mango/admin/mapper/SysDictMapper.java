package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysDict;

/**
 * 字典表
 */
public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
}