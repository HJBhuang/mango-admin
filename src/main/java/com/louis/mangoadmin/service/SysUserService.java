package com.louis.mangoadmin.service;

import com.louis.mangoadmin.model.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:23:19
 */
public interface SysUserService {

    /**
     * 查找所有用户
     * @return
     */
    List<SysUser> findAll();
}
