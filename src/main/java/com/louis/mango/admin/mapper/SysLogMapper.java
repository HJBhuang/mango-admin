package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<SysLog> findPage();

    List<SysLog> findPageByUserName(@Param(value="userName") String userName);
}