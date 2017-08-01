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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.ContextLoader;

import com.hnjz.sys.po.TSysUser;

/**
 * 用户登录失败的处理
 * 
 * @author wumi
 * @version $Id: LoginFail.java, v 0.1 2013-9-10 上午9:42:21 wumi Exp $
 */
public class LoginFail implements AuthenticationFailureHandler {

    /**日志*/
    private static final Log log = LogFactory.getLog(LoginFail.class);

    private String           url;

    /** 
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @SuppressWarnings("deprecation")
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException,
                                                                          ServletException {
        if (log.isDebugEnabled()) {
            log.debug("用户登录失败：", exception);
        }
        //只提示用户名不存在，用户名密码错误
        if (exception.getExtraInformation() instanceof TSysUser){
        	TSysUser u = (TSysUser) exception.getExtraInformation();
        	ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        }
        
        if (exception instanceof BadCredentialsException
            || exception instanceof UsernameNotFoundException) {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
