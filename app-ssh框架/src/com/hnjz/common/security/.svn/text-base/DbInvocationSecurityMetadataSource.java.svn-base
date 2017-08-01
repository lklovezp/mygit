/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 * <p>
 * 提供某个资源有那些角色可以操作。<br>
 * 当前系统来说，主要需要实现的是当前这个url有哪些角色可以操作
 * </p>
 * 
 * @author wumi
 * @version $Id: DbInvocationSecurityMetadataSource.java, v 0.1 Jan 6, 2013 4:47:52 PM wumi Exp $
 */
public class DbInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**日志*/
    private static final Log log = LogFactory.getLog(DbInvocationSecurityMetadataSource.class);

    private SecurityData     securityData;

    /** 
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest req = ((FilterInvocation) object).getHttpRequest();
//        //在session中放入菜单的ID
//        String fid = req.getParameter("fid");
//        if (StringUtils.isNotBlank(fid)) {
//            req.getSession().setAttribute(Constants.FID, fid);
//        }
        String url = req.getRequestURI();
        url = url.replace(req.getContextPath().concat("/"), "");
        Map<String, Collection<ConfigAttribute>> resourceMap = securityData.getResourceMap();
        Collection<ConfigAttribute> re = resourceMap.get(url);
        Object obj = CtxUtil.getPrincipal();
        if (null == re) {
            if (obj instanceof String) {
                re = SecurityConfig.createList("ROLE_ANONYMOUS");
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("url:" + url);
            log.debug("resourceMap:" + resourceMap.get(url));
        }

//        if (obj instanceof User) {
//            User user = (User) obj;
//            fid = (String) req.getSession().getAttribute(Constants.FID);
//            AppCtxStrategy.setContext(securityData.getOper(user.getRole(), fid));
//        }
        return re;
    }

    /** 
     * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
     */
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /** 
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public void setSecurityData(SecurityData securityData) {
        this.securityData = securityData;
    }

}
