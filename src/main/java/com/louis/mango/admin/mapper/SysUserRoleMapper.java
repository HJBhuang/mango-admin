package com.louis.mango.admin.mapper;

import com.louis.mango.admin.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色
 */
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    void deleteByUserId(@Param(value="userId") Long userId);

    List<SysUserRole> findUserRoles(Long userId);
}