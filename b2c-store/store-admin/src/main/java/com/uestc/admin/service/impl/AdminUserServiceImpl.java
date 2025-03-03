package com.uestc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.admin.mapper.AdminUserMapper;
import com.uestc.admin.param.AdminUserParam;
import com.uestc.admin.pojo.AdminUser;
import com.uestc.admin.service.AdminUserService;
import com.uestc.constansts.UserConstants;
import com.uestc.utils.MD5Util;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_account", adminUserParam.getUserAccount());
        //密码
        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword() + UserConstants.USER_SLAT));

        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}", user);

        return user;
    }
}
