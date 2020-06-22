package com.louis.mango.admin.service.impl;

import com.louis.common.page.MybatisPageHelper;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.mango.admin.mapper.SysLoginLogMapper;
import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.admin.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 15:06:04
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int save(SysLoginLog record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysLoginLogMapper.insertSelective(record);
        }
        return sysLoginLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLoginLog record) {
        return sysLoginLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLoginLog> records) {
        for(SysLoginLog record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysLoginLog findById(Long id) {
        return sysLoginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object userName = pageRequest.getParam("userName");
        if(userName != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageByUserName", userName);
        }
        Object status = pageRequest.getParam("status");
        if(status != null) {
            return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageByStatus", status);
        }
        return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper);
    }

    @Transactional
    @Override
    public int writeLoginLog(String userName, String ip) {
        List<SysLoginLog> sysLoginLogs = sysLoginLogMapper.findByUserNameAndStatus(userName, SysLoginLog.STATUS_ONLINE);
        for(SysLoginLog sysLoginLog:sysLoginLogs) {
            sysLoginLog.setStatus(SysLoginLog.STATUS_LOGIN);
            sysLoginLogMapper.updateByPrimaryKey(sysLoginLog);
        }
        SysLoginLog record = new SysLoginLog();
        record.setUserName(userName);
        record.setIp(ip);
        record.setStatus(SysLoginLog.STATUS_LOGOUT);
        sysLoginLogMapper.insertSelective(record);
        record.setStatus(SysLoginLog.STATUS_ONLINE);
        sysLoginLogMapper.insertSelective(record);
        return 0;
    }
}
