package com.louis.mango.admin.service;

import com.louis.mango.admin.model.SysLoginLog;
import com.louis.mango.core.service.CurdService;

/**
 * @author huangjiabao
 * @data 2020/6/8/0008
 * @time 15:04:14
 */
public interface SysLoginLogService extends CurdService<SysLoginLog> {
    /**
     * 记录登录日志
     * @param userName
     * @param ip
     * @return
     */
    int writeLoginLog(String userName, String ip);
}
