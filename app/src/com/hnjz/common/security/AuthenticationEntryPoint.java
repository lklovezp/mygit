/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 用户没有登录时，跳转到登录界面。处理ajax方式
 * 
 * @author wumi
 * @version $Id: AuthenticationEntryPoint.java, v 0.1 Feb 22, 2013 3:30:46 PM wumi Exp $
 */
@SuppressWarnings("deprecation")
public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    /**
     * @see org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException,
                                                               ServletException {
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            //设置超时
            response.setStatus(911);
        } else {
            super.commence(request, response, authException);

        }

    }

}
