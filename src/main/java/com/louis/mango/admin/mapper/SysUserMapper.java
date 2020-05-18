package com.louis.mango.admin.mapper;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理
 */
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    /**
     * 查询全部
     * @return
     */
    List<SysUser> findAll();

    List<SysUser> findPage();

    /*@Param 中的value值必须与mapper 中的sql里的名字保持一致*/
    SysUser findByName(@Param(value = "name") String name);

    SysUser findById(Long id);

    List<SysUserRole> findUserRoles(Long userId);
}