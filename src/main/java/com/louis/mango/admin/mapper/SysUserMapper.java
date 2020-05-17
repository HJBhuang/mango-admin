package com.louis.mango.admin.mapper;

import com.louis.mango.admin.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

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

    SysUser findByName(String name);
}