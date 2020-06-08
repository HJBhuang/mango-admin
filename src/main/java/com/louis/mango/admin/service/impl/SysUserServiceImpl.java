package com.louis.mango.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.common.page.MybatisPageHelper;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.common.utils.DateTimeUtils;
import com.louis.common.utils.PoiUtils;
import com.louis.mango.admin.mapper.SysUserMapper;
import com.louis.mango.admin.mapper.SysUserRoleMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.admin.service.SysMenuService;
import com.louis.mango.admin.service.SysUserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author huangjiabao
 * @data 2020/5/16/0016
 * @time 20:24:45
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus =sysMenuService.findByUser(userName);
        for(SysMenu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
     return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return sysUserRoleMapper.findUserRoles(userId);
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    @Override
    public List<SysUser> testPagehelper(PageRequest pageRequest) {
         int pageNum = pageRequest.getPageNum();
         int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userInfo = sysUserMapper.findAll();
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(userInfo);
        return sysUserPageInfo.getList();
    }

    @Override
    public int save(SysUser record) {
         Long id = null;
         if(record.getId() == null || record.getId() ==0){
             //新增用户
             sysUserMapper.insertSelective(record);
             id=record.getId();
         }else {
             //更新用户信息
             sysUserMapper.updateByPrimaryKeySelective(record);
         }
        // 更新用户角色
        if(id != null && id == 0) {
            return 1;
        }
        if(id != null) {
            for(SysUserRole sysUserRole:record.getUserRoles()) {
                sysUserRole.setUserId(id);
            }
        } else {
            sysUserRoleMapper.deleteByUserId(record.getId());
        }
        for(SysUserRole sysUserRole:record.getUserRoles()) {
            sysUserRoleMapper.insertSelective(sysUserRole);
        }
        return 1;
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUser> records) {
        for(SysUser sysUser:records){
            delete(records);
        }
        return 1;
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysUserMapper);
    }
    public static File createUserExcelFile(List<?> records) {
        if (records == null) {
            records = new ArrayList<>();
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row0 = sheet.createRow(0);
        int columnIndex = 0;
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("机构");
        row0.createCell(++columnIndex).setCellValue("角色");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");
        for (int i = 0; i < records.size(); i++) {
            SysUser user = (SysUser) records.get(i);
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnIndex + 1; j++) {
                row.createCell(j);
            }
            columnIndex = 0;
            row.getCell(columnIndex).setCellValue(i + 1);
            row.getCell(++columnIndex).setCellValue(user.getId());//ID
            row.getCell(++columnIndex).setCellValue(user.getName());//用户名
            row.getCell(++columnIndex).setCellValue(user.getNickName());//昵称
            row.getCell(++columnIndex).setCellValue(user.getDeptName());//机构
            row.getCell(++columnIndex).setCellValue(user.getRoleNames());//角色
            row.getCell(++columnIndex).setCellValue(user.getEmail());//邮箱
            row.getCell(++columnIndex).setCellValue(user.getMobile()); //手机号
            row.getCell(++columnIndex).setCellValue(user.getStatus());//状态
            row.getCell(++columnIndex).setCellValue(user.getAvatar());//头像
            row.getCell(++columnIndex).setCellValue(user.getCreateBy());//创建人
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getCreateTime()));//创建时间
            row.getCell(++columnIndex).setCellValue(user.getLastUpdateBy());//最后更新人
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(user.getLastUpdateTime()));//最后更新时间
        }
        return PoiUtils.createExcelFile(workbook, "download_user");
    }

}
