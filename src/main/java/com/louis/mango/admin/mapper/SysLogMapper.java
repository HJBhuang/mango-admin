package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysLog;

/**
 * 系统操作日志
 */
public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}