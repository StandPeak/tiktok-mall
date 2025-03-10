package com.uestc.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author llf
 * @school uestc
 */
@Controller
@RequestMapping
public class CaptchaController {


    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * 自动生成验证码图片写回！
         * 并且：将验证码图片存储到session, key = capthca 默认4个字=字母
         */
        CaptchaUtil.out(request, response);

    }
}
