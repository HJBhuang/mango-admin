package com.louis.mango.admin.service.impl;

import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.mango.admin.mapper.SysDictMapper;
import com.louis.mango.admin.model.SysDict;
import com.louis.mango.admin.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/17/0017
 * @time 15:12:28
 */
@Service
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findPageByLabel(String label) {
        return sysDictMapper.findPageByLabel(label);
    }

    @Override
    public int save(SysDict record) {
        return 0;
    }

    @Override
    public int delete(SysDict record) {
        return 0;
    }

    @Override
    public int delete(List<SysDict> records) {
        return 0;
    }

    @Override
    public SysDict findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
