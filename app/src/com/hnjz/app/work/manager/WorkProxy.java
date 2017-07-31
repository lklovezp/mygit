/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hnjz.common.AppException;
import com.hnjz.sys.po.TSysUser;

/**
 * WorkNode�Ĵ���
 * 
 * @author wumi
 * @version $Id: WorkProxy.java, v 0.1 Jan 29, 2013 9:26:56 AM wumi Exp $
 */
public class WorkProxy {
    /**��־*/
    private static final Log      log   = LogFactory.getLog(WorkProxy.class);

    /**keyΪ��������ڵ�ı�ʾ��WorkNodeΪ�ڵ��Ӧ��ʵ��*/
    private Map<String, WorkNode> nodes = new HashMap<String, WorkNode>();

    /**
     * ��������(work)�����ڵ��ִ��
     * 
     * @param applyId ����(work)������
     * @param taskId ��������id
     * @param note ���
     * @param ctx ��������
     * @throws AppException
     */
    public void execute(String key, String applyId, String taskId, String note,
                        Map<String, Object> ctx, TSysUser cur) throws AppException, Exception {
        WorkNode node = nodes.get(key);
        if (null == node) {
            throw new AppException(key + "û�ж�Ӧ��ʵ��");
        }
        try {
            node.execute(applyId, taskId, note, ctx, cur);
        } catch (Exception e) {
            log.error("ִ�����̲���ʱ��������", e);
            throw e;
        }
    }
    
    
    public void setNodes(Map<String, WorkNode> nodes) {
        this.nodes = nodes;
    }

}