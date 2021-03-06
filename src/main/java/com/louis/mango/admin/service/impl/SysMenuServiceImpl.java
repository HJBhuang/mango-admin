package com.louis.mango.admin.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.louis.common.page.MybatisPageHelper;
import com.louis.common.page.PageRequest;
import com.louis.common.page.PageResult;
import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.mapper.SysMenuMapper;
import com.louis.mango.admin.model.SysMenu;
import com.louis.mango.admin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
        if(record.getId() == null || record.getId() == 0) {
            return sysMenuMapper.insertSelective(record);
        }
        if(record.getParentId() == null) {
            record.setParentId(0L);
        }
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysMenu record) {
        return sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysMenu> records) {
        for(SysMenu record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysMenuMapper);
    }

    @Override
    public List<SysMenu> findByUser(String userName) {
        if(!StringUtil.isEmpty(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)){
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }

    @Override
    public List<SysMenu> findTree(String userName, int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = findByUser(userName);
        for (SysMenu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if(!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }
        //TODO lambda表达式需要了解一下
        sysMenus.sort(new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                return o1.getOrderNum().compareTo(o2.getOrderNum());
            }
        });
        findChildren(sysMenus, menus, menuType);
        return null;
    }
    private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu SysMenu : SysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if(menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue ;
                }
                if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(SysMenu.getName());
                    menu.setLevel(SysMenu.getLevel() + 1);
                    if(!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            SysMenu.setChildren(children);
            children.sort(new Comparator<com.louis.mango.admin.model.SysMenu>() {
                @Override
                public int compare(com.louis.mango.admin.model.SysMenu o1, com.louis.mango.admin.model.SysMenu o2) {
                    return o1.getOrderNum().compareTo(o2.getOrderNum());
                }
            });
            findChildren(children, menus, menuType);
        }
    }
    private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for(SysMenu menu:sysMenus) {
            if(menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }
}
