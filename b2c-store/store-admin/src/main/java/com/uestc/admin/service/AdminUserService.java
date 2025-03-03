package com.uestc.admin.service;

import com.uestc.admin.param.AdminUserParam;
import com.uestc.admin.pojo.AdminUser;

/**
 * @author llf
 * @school uestc
 */
public interface AdminUserService {
    AdminUser login(AdminUserParam adminUserParam);
}
