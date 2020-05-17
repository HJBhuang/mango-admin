package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysConfig;
import org.apache.ibatis.annotations.Mapper;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}