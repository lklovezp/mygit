/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager;

import java.util.Map;

import com.hnjz.common.AppException;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.enums.ProcessEnum;

/**
 * ��������(work)�����ڵ��ִ��(�ɷ�����ʵ��)
 * 
 * @author wumi
 * @version $Id: WorkNode.java, v 0.1 Jan 29, 2013 9:09:33 AM wumi Exp $
 */
public interface WorkNode {

    /**������������*/
    String key = ProcessEnum.GENERAL_TASK.getProcessKey();

    /**
     * ��������(work)�����ڵ��ִ��
     * 
     * @param applyId ����(work)������
     * @param taskId ��������id
     * @param note ���
     * @param ctx ��������
     * @throws AppException
     */
    void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                     throws AppException,
                                                                                     Exception;
}