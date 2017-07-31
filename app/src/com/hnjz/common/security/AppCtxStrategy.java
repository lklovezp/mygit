/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �û��Ե�ǰ�˵������еĲ����Ĵ洢��
 * ���磺��������Ա���û������������ӣ�ɾ�����޸ĵ�Ȩ��
 * 
 * @author wumi
 * @version $Id: AppCtxStrategy.java, v 0.1 Feb 5, 2013 10:43:12 AM wumi Exp $
 */
public class AppCtxStrategy {

    /**��־*/
    private static final Log                     log           = LogFactory
                                                                   .getLog(AppCtxStrategy.class);
    
    private static final ThreadLocal<String>     fid           = new ThreadLocal<String>();

    public static void clearContext() {
        log.debug("AppCtxStrategy clear");
        fid.remove();
    }
    
    public static void setFid(String funid) {
        fid.set(funid);
    }

    public static String getFid() {
        return fid.get();
    }

}