/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.interceptor;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hnjz.common.security.AppCtxStrategy;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.SecurityData;
import com.hnjz.common.util.StringUtil;
import com.hnjz.mobile.LoginManager;
import com.hnjz.sys.po.TSysUser;

/**
 * <li>手机端，将已经登录的用户放入到ctxutil中
 * 
 * @author wumi
 * @version $Id: MoHandlerInterceptor.java, v 0.1 Apr 22, 2013 10:52:00 AM wumi Exp $
 */
public class MoHandlerInterceptor implements HandlerInterceptor {

    /**日志*/
    private static final Log              log           = LogFactory
                                                            .getLog(MoHandlerInterceptor.class);

    private LoginManager                  loginManager;

    private SecurityData                  securityData;

    /** 所有已存在的Controller */
    private ConcurrentLinkedQueue<String> allController = new ConcurrentLinkedQueue<String>();

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2)
                                                                                          throws Exception {


        String url = req.getRequestURI();
        if (!StringUtils.endsWith(url, "help.htm")) {
            allController.add(arg2.toString().concat(":").concat(url));
        }
        if (allController.size() >= 10) {
            allController.poll();
        }
        req.setAttribute("allController", allController);
        // 进入登陆页至后请求版本信息
        if (StringUtils.endsWith(url, "getVersion.json") || StringUtils.endsWith(url, "downApk") || StringUtils.endsWith(url, "getMoblieVersion.json") || StringUtils.endsWith(url, "downMoblieApk")) {
        	return true;
        }
        //服务端的请求，需要将权限数据放入到AppCtxStrategy中
        if (!StringUtils.endsWith(url, ".mo")) {
            Object obj = CtxUtil.getPrincipal();
            if (null == obj) {
                return true;
            }
            String key = arg2.toString();
            //在session中放入菜单的ID
            String fid = req.getParameter("fid");
            req.setAttribute("fid", fid);
            if (log.isDebugEnabled()) {
                log.debug("url:" + url);
                log.debug("@Controller:" + arg2.toString());
            }
            if (StringUtils.isBlank(fid)) {
                fid = securityData.getFunctionId(key);
            } else {
                securityData.add(key, fid);
            }
            if (obj instanceof TSysUser) {
            	if (StringUtil.isNotBlank(fid)){
            		AppCtxStrategy.setFid(fid);
            	}
            }
            return true;
        }
        //手机端的登录和更新客户端，不校验用户登录
        if (StringUtils.endsWith(url, "login.mo") || StringUtils.contains(url, "client")) {
            return true;
        }

        //手机端的用户登录后，需要将登录信息放入到SecurityContextHolder中，使manager可以通过
        //CtxUtil.getPrincipal()来获取当前用户
        String uuid = req.getParameter("uuid");
        log.debug("mobileUserUuid: "+uuid);
        TSysUser user = this.loginManager.getLoginUser(uuid);
        //说明用户没有登录
        if (null == user) {
            if (log.isDebugEnabled()) {
                log.debug(uuid + "没有登录");
            }
            //提示用户登录
            res.setStatus(911);
            return false;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("加入用户到authentication 中");
        return true;
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse arg1, Object arg2,
                                Exception arg3) throws Exception {
        //手机端的请求需要清楚验证信息
        String url = req.getRequestURI();
        if (StringUtils.endsWith(url, ".mo")) {
            SecurityContextHolder.clearContext();
        }
        AppCtxStrategy.clearContext();
    }

    /** 
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
                           ModelAndView arg3) throws Exception {
    }

    public void setLoginManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    public void setSecurityData(SecurityData securityData) {
        this.securityData = securityData;
    }

}
