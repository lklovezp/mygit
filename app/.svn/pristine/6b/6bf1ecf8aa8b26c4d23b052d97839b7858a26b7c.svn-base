/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.hnjz.sys.po.TSysUser;

/**
 * 操作上下文工具类
 * 
 * @author wumi
 * @version $Id: CtxUtil.java, v 0.1 Jan 8, 2013 11:36:11 AM wumi Exp $
 */
public class CtxUtil {
    
    /**
     * 获取当前登录用户的区域Id
     * 
     * @return 获取当前登录用户的区域Id
     */
    public static String getAreaId() {
        return ((TSysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getAreaId();
    }
    

    /**
     * 获取当前登录用户的id
     * 
     * @return 当前登录用户的id
     */
    public static String getUserId() {
        return ((TSysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getId();
    }

    /**
     * 得到当前用户
     * 
     * @return 当前登录用户对应的{@link User}对象
     */
    public static TSysUser getCurUser() {
    	TSysUser user = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            user = (TSysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    /**
     * 得到当前用户
     * 
     * @return 当前登录用户对应的用户对象
     */
    public static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
