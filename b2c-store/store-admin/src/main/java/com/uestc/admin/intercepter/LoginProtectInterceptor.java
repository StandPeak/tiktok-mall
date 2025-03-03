package com.uestc.admin.intercepter;

/**
 *
 * @author llf
 * @school uestc
 */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录保护拦截器
 * 1.
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        }
        // false 拦截 | true 放行

    }
}
