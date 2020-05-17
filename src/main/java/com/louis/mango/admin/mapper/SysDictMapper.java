package com.louis.mango.admin.mapper;


import com.louis.mango.admin.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 分页查找
     * @return
     */
    List<SysDict> findPage();

    /**
     * 根据标签名查找
     * @param label
     * @return
     */
    List<SysDict> findByLabel(@Param(value = "label") String label);

    /**
     * 根据标签名称分页查找
     * @param label
     * @return
     */
    List<SysDict> findPageByLabel(@Param(value = "label") String label);
}