/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import com.hnjz.common.AppException;
import com.hnjz.quartz.po.Job;

/**
 * ��Ҫ���ȵ���ҵҪʵ�ֵĽӿ�
 * @author xuguanghui
 * @version $Id: JobItem.java, v 0.1 2013-4-7 ����03:21:41 admi Exp $
 */
public interface JobItem {
    
    void execute(Job job) throws  AppException;

}