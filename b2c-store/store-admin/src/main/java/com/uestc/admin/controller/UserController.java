package com.uestc.admin.controller;

import com.uestc.admin.service.UserService;
import com.uestc.param.CartListParam;
import com.uestc.param.PageParam;
import com.uestc.pojo.User;
import com.uestc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam) {

        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userList(CartListParam cartListParam) {

        return userService.userRemove(cartListParam);
    }

    @PostMapping("update")
    public R update(User user) {

        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R save(User user) {

        return userService.save(user);
    }
}
