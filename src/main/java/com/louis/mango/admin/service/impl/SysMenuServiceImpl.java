package com.louis.mango.admin.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.mapper.SysMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/18/0018
 * @time 15:32:30
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public int save(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(List<SysMenu> records) {
        return 0;
    }

    @Override
    public SysMenu findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<SysMenu> findByUser(String userName) {
        if(!StringUtil.isEmpty(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)){
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }
}
