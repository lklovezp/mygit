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
 * ��������ҵ�����
 * 
 * @author zn
 * @version $Id: ProcessManager.java, v 0.1 2013-1-9 ����04:01:20 Administrator
 *          Exp $
 */
public interface ProcessManager {
    /******************** ����������� ********************/
    /**
     * �õ�����ִ��ҳ�����Ƽ���
     * 
     * @return
     * @throws Exception
     */
    List<String> getStepNameList(String realPath, String deploymentId) throws Exception;

    /**
     * ������������
     * 
     * @throws Exception
     */
    void savePdSet(String json) throws Exception;

    /**
     * ��������KEYֵ��ѯ��Ӧҵ��
     * 
     * @param deploymentId
     * @return
     * @throws Exception
     */
    WfProcessBusiness findProcessBusinessFromProcessKey(String key) throws Exception;

    /**
     * ���������ż�����KEYֵ��ѯ����
     * 
     * @param taskId
     * @param key
     * @return
     * @throws Exception
     */
    WfProcessStep findProcessStepFromTask(String taskId, String key) throws Exception;

    /******************* ������� *******************/
    /**
     * ��ѯ�������̶���
     * 
     * @return
     * @throws Exception
     */
    List<ProcessDefinition> getAllPdList() throws Exception;

    /**
     * �õ���������ʵ��
     * @return
     * @throws Exception
     */
    List<ProcessInstance> getAllPiList() throws Exception;

    /**
     * �õ�ȫ�����񼯺�
     * 
     * @return
     * @throws Exception
     */
    List<Task> getAllTaskList() throws Exception;

    /**
     * ��������
     * 
     * @param key
     * @throws Exception
     */
    void saveProcess(String key) throws Exception;

    /**
     * ɾ������
     * 
     * @param key
     * @throws Exception
     */
    void removeProcess(String deploymentId) throws Exception;

    /**
     * ɾ������ʵ��
     * 
     * @param piId
     * @throws Exception
     */
    void removeProcessIns(String piId) throws Exception;

    /**
     * ��ѯһ�����̶���
     * 
     * @param deploymentId
     * @return
     * @throws Exception
     */
    ProcessDefinition findPdByDeploymentId(String deploymentId) throws Exception;

    /**
     * ��ʼһ������ʵ��
     * 
     * @param key
     * @param owner
     * @return ==null-δ�������̣�!=null-ͳ��ʵ��
     * @throws Exception
     */
    ProcessInstance startProcess(String key, String owner,
                                 Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                           throws Exception;

    /**
     * ִ������
     * 
     * @param taskId
     * @param user
     * @param varMap
     * @throws Exception
     */
    void execProcess(String taskId, String opinion, Map<String, Object> varMap) throws Exception;

    /**
     * ��ѯ�û�����
     * 
     * @param userName
     *            ��Ա����
     * @return
     * @throws Exception
     */
    List<ProcessInfoBean> getUserTask(String userName) throws Exception;

    /**
     * �������̱�ŵõ���ǰ�����
     * 
     * @param piId
     *            ���̱��
     * @return
     * @throws Exception
     */
    Task getActTaskFromPiId(String piId) throws Exception;

    /**
     * �õ�����ͼ
     * 
     * @param key
     *            ���̶���KEYֵ
     * @return
     * @throws Exception
     */
    InputStream getFlowChart(String key) throws Exception;

    /**
     * �õ���ǰ��ڵ�λ��
     * 
     * @param piId ���̱��
     * @return
     * @throws Exception
     */
    Map<String, Object> getActPosition(String piId) throws Exception;

    /**
     * �õ�����ִ����(�û���)
     * 
     * @param piId
     *            ���̱��
     * @return
     * @throws Exception
     */
    List<String> getAssignee(String piId) throws Exception;

    /******************* ������� *******************/

    /**
     * �������뵥��ѯ���벽��
     * 
     * @param applyId
     * @return
     * @throws Exception
     */
    List<WfApplyStep> findApplyStepWithApplyId(String applyId) throws Exception;

    /**
     * ����������ѯ���뵥
     * 
     * @param applyClass
     * @param applyId
     * @return
     * @throws Exception
     */
    IApplyCommonPo getApply(Class<? extends ApplyCommonPo> applyClass, String applyId)
                                                                                      throws Exception;
}