package com.hnjz.wf;

import java.util.Date;

import com.hnjz.wf.entity.CommonPo;

/**
 * ���̹���ʵ���ֶ�
 * 
 * @author zn
 * @version $Id: ApplyCommonPo.java, v 0.1 2013-1-10 ����07:35:47 Administrator
 *          Exp $
 */
public class ApplyCommonPo extends CommonPo implements IApplyCommonPo {
    /**  */
    private static final long serialVersionUID = 1L;

    /** ���ƣ���Ӧ�����뵥�������ֶΣ��磺Work�е�WORK_NAME_;������ʱ�����ֶ�ӦӳΪname�� */
    private String            name;

    /** ���̱�� */
    private String            processId;

    /** ������ */
    private String            taskId;

    /** �Ƿ���� */
    private Boolean           isOver;
   
    /**��������*/
    private Date              endTime;

    /** �²����� */
    private String            nextActions;

    /** �²������� */
    private String            nextOper;

    /** �������͵ı�ʾ���μ�ö��WorkTypeCode */
    private String            code;
    
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsOver() {
        return isOver;
    }

    public void setIsOver(Boolean isOver) {
        this.isOver = isOver;
    }

    public String getNextActions() {
        return nextActions;
    }

    public void setNextActions(String nextActions) {
        this.nextActions = nextActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextOper() {
        return nextOper;
    }

    public void setNextOper(String nextOper) {
        this.nextOper = nextOper;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}