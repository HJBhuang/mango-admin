package com.louis.mango.admin.service.impl;

import com.louis.common.page.MybatisPageHelper;
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
    public List<SysDict> findByLable(String lable) {
        return sysDictMapper.findByLabel(lable);
    }

    @Override
    public int save(SysDict record) {
        if(record.getId() == null || record.getId() == 0){
            sysDictMapper.insertSelective(record);
        }
        return sysDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDict record) {
        return sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for(SysDict sysDict:records){
            delete(sysDict);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysDictMapper);
    }
}
