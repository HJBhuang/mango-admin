package com.louis.mango.admin.service.impl;

import com.louis.common.page.MybatisPageHelper;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.common.utils.StringUtils;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.mapper.SysMenuMapper;
import com.louis.mango.admin.mapper.SysRoleMapper;
import com.louis.mango.admin.mapper.SysRoleMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysRole;
import com.louis.mango.admin.model.SysRoleMenu;
import com.louis.mango.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysRole record) {
        if(record.getId() == null || record.getId() ==0){
            return sysRoleMapper.insertSelective(record);
        }
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for(SysRole sysRole: records){
            delete(sysRole);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label =pageRequest.getParamValue("name");
        if(label !=null){
            return MybatisPageHelper.findPage(pageRequest,sysRoleMapper,"findPageByName",label);
        }
        return MybatisPageHelper.findPage(pageRequest,sysRoleMapper);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findRoleMenus(roleId);
    }

    //TODO 为什么这里要加事务
    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if(records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        sysRoleMenuMapper.deleteByPrimaryKey(roleId);
        for(SysRoleMenu sysRoleMenu: records){
            sysRoleMenuMapper.insertSelective(sysRoleMenu);
        }

        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        return sysRoleMapper.findByName(name);
    }
}
