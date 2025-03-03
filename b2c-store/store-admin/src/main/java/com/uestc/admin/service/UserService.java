package com.uestc.admin.service;

import com.uestc.param.CartListParam;
import com.uestc.param.PageParam;
import com.uestc.pojo.User;
import com.uestc.utils.R;

/**
 * @author llf
 * @school uestc
 */
public interface UserService {

    /**
     * 用户展示
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}
