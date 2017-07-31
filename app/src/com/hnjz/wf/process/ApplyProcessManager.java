package com.hnjz.wf.process;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.hnjz.wf.IApplyCommonPo;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ҵ����ù�����
 * @author zn
 * @version $Id: ApplyProcessManager.java, v 0.1 2013-1-22 ����08:54:55 zn Exp $
 */
public interface ApplyProcessManager {
    /**
     * ��ʼһ������
     * @param key   ����KEY
     * @param applyId   ���뵥���
     * @param paraMap   ����Ĳ�������
     * @throws Exception
     */
    ProcessInstance saveStart(String key, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                       throws Exception;

    /**
     * ִ����һ��
     * @param key   ����KEY
     * @param taskId    ������
     * @param applyId   ���뵥���
     * @param paraMap   ����Ĳ�������
     * @throws Exception
     */
    void saveNext(String key, String taskId, IApplyCommonPo applyPo, ProcessArgsBean bean)
                                                                                          throws Exception;

    /**
     * �õ�ָ�����̵ĵ�ǰ�����
     * @param piId  ���̱��
     * @return
     * @throws Exception
     */
    Task getActTaskFromPiId(String piId) throws Exception;

    /**
     * Ϊĳ����������һ�����û�
     * @param taskId    JBPM������
     * @param userNames  �û�������
     * @throws Exception
     */
    void saveAddGroupUser(String taskId, String[] oldUserNames, String[] newUserNames)
                                                                                      throws Exception;
    
    
    public String getNextOperName(IApplyCommonPo applyPo)throws Exception;
    
}