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
 * ��ԴԴ���ݶ��壬������ĳһ��Դ���Ա���Щ��ɫ����
 * <p>
 * �ṩĳ����Դ����Щ��ɫ���Բ�����<br>
 * ��ǰϵͳ��˵����Ҫ��Ҫʵ�ֵ��ǵ�ǰ���url����Щ��ɫ���Բ���
 * </p>
 * 
 * @author wumi
 * @version $Id: DbInvocationSecurityMetadataSource.java, v 0.1 Jan 6, 2013 4:47:52 PM wumi Exp $
 */
public class DbInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**��־*/
    private static final Log log = LogFactory.getLog(DbInvocationSecurityMetadataSource.class);

    private SecurityData     securityData;

    /** 
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest req = ((FilterInvocation) object).getHttpRequest();
//        //��session�з���˵���ID
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
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /** 
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public void setSecurityData(SecurityData securityData) {
        this.securityData = securityData;
    }

}