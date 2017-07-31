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
 * 定义任务(work)各个节点的执行(派发单独实现)
 * 
 * @author wumi
 * @version $Id: WorkNode.java, v 0.1 Jan 29, 2013 9:09:33 AM wumi Exp $
 */
public interface WorkNode {

    /**工作流的名称*/
    String key = ProcessEnum.GENERAL_TASK.getProcessKey();

    /**
     * 定义任务(work)各个节点的执行
     * 
     * @param applyId 任务(work)的主键
     * @param taskId 工作流的id
     * @param note 意见
     * @param ctx 其他参数
     * @throws AppException
     */
    void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                     throws AppException,
                                                                                     Exception;
}
