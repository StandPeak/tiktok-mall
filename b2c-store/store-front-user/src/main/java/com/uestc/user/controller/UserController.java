package com.uestc.user.controller;

import com.uestc.param.UserCheckParam;
import com.uestc.param.UserLoginParam;
import com.uestc.pojo.User;
import com.uestc.user.service.UserService;
import com.uestc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查账号是否可用的接口
     *
     * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
     * @param result         获取校验结果的实体对象
     * @return 返回封装结果R对象即可
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
        boolean b = result.hasErrors();

        if (b) return R.fail("账号为空，不可使用");

        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常，不可注册！");
        }

        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数异常，登录！");
        }

        return userService.login(userLoginParam);
    }
}
