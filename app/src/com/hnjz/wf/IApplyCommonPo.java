package com.hnjz.wf;

import com.hnjz.app.work.po.WorkType;

/**
 * ���뵥���ýӿ�
 * 
 * @author zn
 * @version $Id: IApplyCommonPo.java, v 0.1 2013-1-14 ����08:41:43 Administrator
 *          Exp $
 */
public interface IApplyCommonPo {
    /**
     * �õ����
     * @return
     */
    String getId();

    /**
     * �õ�����
     * @return
     */
    String getName();

    /**
     * �õ����̱��
     * @return
     */
    String getProcessId();

    /**
     * �õ���һ���
     * @return
     */
    String getNextActions();

    /**
     * �������͵ı�ʾ���μ�ö��WorkTypeCode
     * @return
     */
    String getCode();
    /**
     * �������͵ı�ʾ���μ�ö��WorkTypeCode
     * @return
     */
    void setCode(String code);

    /**
     * �������̱��
     * @param processId
     */
    void setProcessId(String processId);

    /**
     * ����������
     * @param taskId
     */
    void setTaskId(String taskId);

    /**
     * ���������Ƿ����
     * @param isOver
     */
    void setIsOver(Boolean isOver);

    /**
     * �����²��
     * @param nextActions
     */
    void setNextActions(String nextActions);

    /**
     * �����²�������
     * @param nextOper
     */
    void setNextOper(String nextOper);
}