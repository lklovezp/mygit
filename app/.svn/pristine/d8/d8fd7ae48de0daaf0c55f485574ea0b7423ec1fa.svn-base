package com.hnjz.wf.process;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.hnjz.wf.ApplyCommonPo;
import com.hnjz.wf.IApplyCommonPo;
import com.hnjz.wf.bean.ProcessInfoBean;
import com.hnjz.wf.entity.WfApplyStep;
import com.hnjz.wf.entity.WfProcessBusiness;
import com.hnjz.wf.entity.WfProcessStep;

/**
 * 工作流程业务管理
 * 
 * @author zn
 * @version $Id: ProcessManager.java, v 0.1 2013-1-9 上午04:01:20 Administrator
 *          Exp $
 */
public interface ProcessManager {
    /******************** 流程设置相关 ********************/
    /**
     * 得到流程执行页面名称集合
     * 
     * @return
     * @throws Exception
     */
    List<String> getStepNameList(String realPath, String deploymentId) throws Exception;

    /**
     * 保存流程设置
     * 
     * @throws Exception
     */
    void savePdSet(String json) throws Exception;

    /**
     * 根据流程KEY值查询对应业务
     * 
     * @param deploymentId
     * @return
     * @throws Exception
     */
    WfProcessBusiness findProcessBusinessFromProcessKey(String key) throws Exception;

    /**
     * 根据任务编号及流程KEY值查询步骤
     * 
     * @param taskId
     * @param key
     * @return
     * @throws Exception
     */
    WfProcessStep findProcessStepFromTask(String taskId, String key) throws Exception;

    /******************* 流程相关 *******************/
    /**
     * 查询所有流程定义
     * 
     * @return
     * @throws Exception
     */
    List<ProcessDefinition> getAllPdList() throws Exception;

    /**
     * 得到所有流程实例
     * @return
     * @throws Exception
     */
    List<ProcessInstance> getAllPiList() throws Exception;

    /**
     * 得到全部任务集合
     * 
     * @return
     * @throws Exception
     */
    List<Task> getAllTaskList() throws Exception;

    /**
     * 发布流程
     * 
     * @param key
     * @throws Exception
     */
    void saveProcess(String key) throws Exception;

    /**
     * 删除流程
     * 
     * @param key
     * @throws Exception
     */
    void removeProcess(String deploymentId) throws Exception;

    /**
     * 删除流程实例
     * 
     * @param piId
     * @throws Exception
     */
    void removeProcessIns(String piId) throws Exception;

    /**
     * 查询一个流程定义
     * 
     * @param deploymentId
     * @return
     * @throws Exception
     */
    ProcessDefinition findPdByDeploymentId(String deploymentId) throws Exception;

    /**
     * 开始一个流程实例
     * 
     * @param key
     * @param owner
     * @return ==null-未发布流程，!=null-统程实例
     * @throws Exception
     */
    ProcessInstance startProcess(String key, String owner,
                                 Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                           throws Exception;

    /**
     * 执行流程
     * 
     * @param taskId
     * @param user
     * @param varMap
     * @throws Exception
     */
    void execProcess(String taskId, String opinion, Map<String, Object> varMap) throws Exception;

    /**
     * 查询用户任务
     * 
     * @param userName
     *            人员名称
     * @return
     * @throws Exception
     */
    List<ProcessInfoBean> getUserTask(String userName) throws Exception;

    /**
     * 根据流程编号得到当前活动任务
     * 
     * @param piId
     *            流程编号
     * @return
     * @throws Exception
     */
    Task getActTaskFromPiId(String piId) throws Exception;

    /**
     * 得到流程图
     * 
     * @param key
     *            流程定义KEY值
     * @return
     * @throws Exception
     */
    InputStream getFlowChart(String key) throws Exception;

    /**
     * 得到当前活动节点位置
     * 
     * @param piId 流程编号
     * @return
     * @throws Exception
     */
    Map<String, Object> getActPosition(String piId) throws Exception;

    /**
     * 得到任务执行人(用户名)
     * 
     * @param piId
     *            流程编号
     * @return
     * @throws Exception
     */
    List<String> getAssignee(String piId) throws Exception;

    /******************* 申请相关 *******************/

    /**
     * 根据申请单查询申请步骤
     * 
     * @param applyId
     * @return
     * @throws Exception
     */
    List<WfApplyStep> findApplyStepWithApplyId(String applyId) throws Exception;

    /**
     * 根据主键查询申请单
     * 
     * @param applyClass
     * @param applyId
     * @return
     * @throws Exception
     */
    IApplyCommonPo getApply(Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                      throws Exception;
}