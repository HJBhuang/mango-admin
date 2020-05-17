package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysLoginLog;

/**
 * 系统登录日志
 */
public interface SysLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);
}