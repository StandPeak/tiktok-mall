package com.uestc.admin.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.admin.mapper.AdminRoleMapper;
import com.uestc.admin.mapper.AdminUserMapper;
import com.uestc.admin.pojo.AdminUser;
import com.uestc.admin.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {

        String userId = (String) o;
        AdminUser adminUser = adminUserMapper.selectById(userId);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("role_user_name", adminUser.getUserAccount());
        Role role = adminRoleMapper.selectOne(queryWrapper);

        List<String> list = new ArrayList<>();
        if("admin".equals(role.getRoleName())) {
            list.add("admin");
        } else if("user".equals(role.getRoleName())) {
            list.add("user");
        }

        return list;
    }
}
