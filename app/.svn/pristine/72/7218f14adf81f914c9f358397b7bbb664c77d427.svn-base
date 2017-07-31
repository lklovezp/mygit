/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.context.ContextLoader;

import com.hnjz.mobile.LoginManager;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.timertask.TimerTaskManager;

/**
 * 用户登录成功的处理
 * 
 * @author wumi
 * @version $Id: loginSuccess.java, v 0.1 2013-9-10 上午9:43:18 wumi Exp $
 */
public class loginSuccess extends SimpleUrlAuthenticationSuccessHandler {
    /**日志*/
    private static final Log log = LogFactory.getLog(loginSuccess.class);

    private String           url;

    private LoginManager loginManager;
    /** 
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException,
                                                                      ServletException {
        response.sendRedirect(url);
        clearAuthenticationAttributes(request);
        TSysUser u = (TSysUser) authentication.getPrincipal();
        String username = u.getUsername();
        ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        loginManager = (LoginManager) ac.getBean("loginManager");
        loginManager.saveLoginLog(request, username, "0", "Y");
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
