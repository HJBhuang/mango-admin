package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysDept;

import java.util.List;

/**
 * 机构管理
 */
public interface SysDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> findPage();

    List<SysDept> findAll();
}