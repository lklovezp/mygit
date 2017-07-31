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
 * WorkNode的代理
 * 
 * @author wumi
 * @version $Id: WorkProxy.java, v 0.1 Jan 29, 2013 9:26:56 AM wumi Exp $
 */
public class WorkProxy {
    /**日志*/
    private static final Log      log   = LogFactory.getLog(WorkProxy.class);

    /**key为各个任务节点的标示，WorkNode为节点对应的实现*/
    private Map<String, WorkNode> nodes = new HashMap<String, WorkNode>();

    /**
     * 定义任务(work)各个节点的执行
     * 
     * @param applyId 任务(work)的主键
     * @param taskId 工作流的id
     * @param note 意见
     * @param ctx 其他参数
     * @throws AppException
     */
    public void execute(String key, String applyId, String taskId, String note,
                        Map<String, Object> ctx, TSysUser cur) throws AppException, Exception {
        WorkNode node = nodes.get(key);
        if (null == node) {
            throw new AppException(key + "没有对应的实现");
        }
        try {
            node.execute(applyId, taskId, note, ctx, cur);
        } catch (Exception e) {
            log.error("执行流程操作时发生错误", e);
            throw e;
        }
    }
    
    
    public void setNodes(Map<String, WorkNode> nodes) {
        this.nodes = nodes;
    }

}
