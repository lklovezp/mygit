package com.hnjz.wf.entity;

/**
 * ���뵥����<br>
 * ����������ִ��ʱÿ���Ĳ������
 * 
 * @author zn
 * @version $Id: WfApplyStep.java, v 0.1 2013-1-15 ����07:30:36 Administrator Exp
 *          $
 */
public class WfApplyStep extends CommonPo {
    /**  */
    private static final long serialVersionUID = 1L;
    /** ���뵥��� */
    private String applyId;
    /** ִ���� */
    private String execUser;
    /** ������ */
    private String taskId;
    /** �������� */
    private String taskName;
    /** ��� */
    private String opinion;
    /** ��� */
    private String result;
    /** ��ע */
    private String note;

    public String getExecUser() {
	return execUser;
    }

    public void setExecUser(String execUser) {
	this.execUser = execUser;
    }

    public String getTaskName() {
	return taskName;
    }

    public void setTaskName(String taskName) {
	this.taskName = taskName;
    }

    public String getOpinion() {
	return opinion;
    }

    public void setOpinion(String opinion) {
	this.opinion = opinion;
    }

    public String getResult() {
	return result;
    }

    public void setResult(String result) {
	this.result = result;
    }

    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    public String getApplyId() {
	return applyId;
    }

    public void setApplyId(String applyId) {
	this.applyId = applyId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}