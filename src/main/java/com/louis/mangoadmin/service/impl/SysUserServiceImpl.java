package com.louis.mangoadmin.service.impl;

import com.louis.mangoadmin.mapper.SysUserMapper;
import com.louis.mangoadmin.model.SysUser;
import com.louis.mangoadmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:24:45
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
}
