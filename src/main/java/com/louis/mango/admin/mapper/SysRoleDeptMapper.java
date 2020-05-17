package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysRoleDept;

/**
 * 角色机构
 */
public interface SysRoleDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleDept record);

    int insertSelective(SysRoleDept record);

    SysRoleDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleDept record);

    int updateByPrimaryKey(SysRoleDept record);
}