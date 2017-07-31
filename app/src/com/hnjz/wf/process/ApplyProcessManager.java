package com.hnjz.wf.process;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.hnjz.wf.IApplyCommonPo;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 业务调用工作流
 * @author zn
 * @version $Id: ApplyProcessManager.java, v 0.1 2013-1-22 上午08:54:55 zn Exp $
 */
public interface ApplyProcessManager {
    /**
     * 开始一个流程
     * @param key   流程KEY
     * @param applyId   申请单编号
     * @param paraMap   传入的参数集合
     * @throws Exception
     */
    ProcessInstance saveStart(String key, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                       throws Exception;

    /**
     * 执行下一步
     * @param key   流程KEY
     * @param taskId    任务编号
     * @param applyId   申请单编号
     * @param paraMap   传入的参数集合
     * @throws Exception
     */
    void saveNext(String key, String taskId, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                          throws Exception;

    /**
     * 得到指定流程的当前活动任务
     * @param piId  流程编号
     * @return
     * @throws Exception
     */
    Task getActTaskFromPiId(String piId) throws Exception;

    /**
     * 为某个任务添加一个组用户
     * @param taskId    JBPM任务编号
     * @param userNames  用户名集合
     * @throws Exception
     */
    void saveAddGroupUser(String taskId, String[] oldUserNames, String[] newUserNames)
                                                                                      throws Exception;
    
    
    public String getNextOperName(IApplyCommonPo applyPo)throws Exception;
    
}
