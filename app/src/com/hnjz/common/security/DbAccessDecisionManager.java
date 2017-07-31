/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * ���ʾ�����������ĳ���û����еĽ�ɫ���Ƿ����㹻��Ȩ��ȥ����ĳ����Դ
 * <p>
 * ��������ڶԸ���Դ�Ķ��壬ֱ�ӷ��У���������ҵ���ȷ�Ľ�ɫ������Ϊӵ��Ȩ�ޣ������У�<br>
 * ����throw new AccessDeniedException("no right");�������ͻ���������ᵽ��403.jspҳ��
 * </p>
 * 
 * @author wumi
 * @version $Id: DbAccessDecisionManager.java, v 0.1 Jan 6, 2013 5:11:23 PM wumi Exp $
 */
public class DbAccessDecisionManager implements AccessDecisionManager {

    private AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    /** 
     * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
     */
    @Override
    public void decide(Authentication authentication, Object obj,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
                                                                    InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        //�����û�����������
        if (authenticationTrustResolver.isAnonymous(authentication)) {
            throw new AccessDeniedException("no right");
        }
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig) ca).getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) { //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    /** 
     * @see org.springframework.security.access.AccessDecisionManager#supports(org.springframework.security.access.ConfigAttribute)
     */
    @Override
    public boolean supports(ConfigAttribute arg0) {
        return true;
    }

    /** 
     * @see org.springframework.security.access.AccessDecisionManager#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}