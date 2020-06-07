package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysDept;
import com.louis.mango.core.service.CurdService;

import java.util.List;


public interface SysDeptService extends CurdService<SysDept> {

    /**
     * 查询机构树
     * @param
     * @return
     */
    List<SysDept> findTree();
}