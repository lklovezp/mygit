/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hnjz.common.dao.Dao;
import com.hnjz.sys.po.TSysUser;

/**
 * 根据用户名得到用户信息
 * 
 * @author wumi
 * @version $Id: UserDetailsServiceImpl.java, v 0.1 Jan 6, 2013 5:21:55 PM wumi Exp $
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    /**日志*/
    private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    private Dao dao;

    /** 
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        String hsql = "from TSysUser where isActive = 'Y' and username = ?";
        List<TSysUser> re = dao.find(hsql, name);
        if (re.isEmpty()) {
            throw new UsernameNotFoundException("用户： " + name + "不存在");
        }
        //用户具有的角色
        TSysUser user = re.get(0);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        user.setAuthorities(authorities);
        return user;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

}
