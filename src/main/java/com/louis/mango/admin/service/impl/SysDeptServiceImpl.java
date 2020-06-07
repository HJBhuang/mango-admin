package com.louis.mango.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.common.page.MybatisPageHelper;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.mango.admin.mapper.SysDeptMapper;
import com.louis.mango.admin.model.SysDept;
import com.louis.mango.admin.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public int save(SysDept record) {
        if (record.getId() == null || record.getId() == 0) {
            return sysDeptMapper.insertSelective(record);
        }
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDept record) {
        return sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept sysDept : records) {
            delete(sysDept);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
       /* PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<SysDept> page = sysDeptMapper.findPage();
        PageInfo<SysDept> sysDeptPageInfo = new PageInfo<>(page);*/
        return MybatisPageHelper.findPage(pageRequest,sysDeptMapper);
    }

    @Override
    public List<SysDept> findTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        List<SysDept> depts = sysDeptMapper.findAll();
        for(SysDept sysDept:depts){
            if(sysDept.getParentId() == null || sysDept.getParentId() == 0){
                sysDept.setLevel(0);
                sysDepts.add(sysDept);
            }
        }
        findChildren(sysDepts,depts);
        return sysDepts;
    }

    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
        for(SysDept sysDept:sysDepts){
            List<SysDept> children = new ArrayList<>();
            for(SysDept dept:depts){
                if(sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())){
                    dept.setParentName(sysDept.getName());
                    dept.setLevel(sysDept.getLevel()+1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            findChildren(children,depts);
        }
    }
}
