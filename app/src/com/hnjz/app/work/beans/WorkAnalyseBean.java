package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.hnjz.app.work.po.Work;

/**
 * �������
 * @author zn
 * @version $Id: WorkAnalyseBean.java, v 0.1 2013-4-8 ����12:22:36 zn Exp $
 */
public class WorkAnalyseBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    //���������Ϣ
    /** ������ */
    private String            workId;
    /** �������� */
    private String            workName;
    /** �������� */
    private String            workType;
    /** �����̶� */
    private String            urgencyDegree;
    /** ������Դ */
    private String            workSource;
    /** ������ */
    private String            createUser;
    /** ����ʱ�� */
    private String            createTime;
    /** ��ʼʱ�� */
    private String            startTime;
    /** ����ʱ�� */
    private String            endTime;
    //��ǰ����״̬
    /** ��ǰ״̬ */
    private String            curState;
    /** �Ƿ��Ѿ�ִ�� */
    private Boolean           isExecute;
    /** ִ��ʱ�� */
    private String            execTime;
    /** ִ���� */
    private String            execUser;
    /** �������� */
    private String            toOperUser;
    //��ʱ��׶�
    /** ��׶β��� */
    private String            longestSectionStep;
    /** ��׶ο�ʼʱ�� */
    private String            longestStartTime;
    /** ��׶ν���ʱ�� */
    private String            longestEndTime;
    /** ��׶�ִ���� */
    private String            longestExecUser;
    /** ��׶���ʱ */
    private String            longestUseTime;
    //���ڵ����
    /** ���ڽ׶β��� */
    private String            overdueSectionStep;
    /** ���ڽ׶ο�ʼʱ�� */
    private String            overdueStartTime;
    /** ���ڽ׶ν���ʱ�� */
    private String            overdueEndTime;
    /** ���ڽ׶�ִ���� */
    private String            overdueExecUser;

    public WorkAnalyseBean() {
    }

    public WorkAnalyseBean(Work work) {
        SimpleDateFormat sdfA = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdfB = new SimpleDateFormat("yyyy-MM-dd");
        setWorkId(work.getId());
        setWorkName(work.getName());
//        setWorkType(work.getWorkType().getName());
        setCreateTime(sdfA.format(work.getCreateTime()));
        setCreateUser(work.getCreateUser().getName());
        setStartTime(sdfB.format(work.getStartTime()));
        setEndTime(sdfB.format(work.getEndTime()));
        //��ǰ
        setIsExecute(work.getZxtime() != null);
        setExecTime(work.getZxtime() == null ? "" : sdfA.format(work.getZxtime()));
        setExecUser(work.getZxrName() == null ? "" : work.getZxrName());
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public String getWorkSource() {
        return workSource;
    }

    public void setWorkSource(String workSource) {
        this.workSource = workSource;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCurState() {
        return curState;
    }

    public void setCurState(String curState) {
        this.curState = curState;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getExecUser() {
        return execUser;
    }

    public void setExecUser(String execUser) {
        this.execUser = execUser;
    }

    public String getLongestSectionStep() {
        return longestSectionStep;
    }

    public void setLongestSectionStep(String longestSectionStep) {
        this.longestSectionStep = longestSectionStep;
    }

    public String getLongestStartTime() {
        return longestStartTime;
    }

    public void setLongestStartTime(String longestStartTime) {
        this.longestStartTime = longestStartTime;
    }

    public String getLongestEndTime() {
        return longestEndTime;
    }

    public void setLongestEndTime(String longestEndTime) {
        this.longestEndTime = longestEndTime;
    }

    public String getLongestExecUser() {
        return longestExecUser;
    }

    public void setLongestExecUser(String longestExecUser) {
        this.longestExecUser = longestExecUser;
    }

    public String getOverdueSectionStep() {
        return overdueSectionStep;
    }

    public void setOverdueSectionStep(String overdueSectionStep) {
        this.overdueSectionStep = overdueSectionStep;
    }

    public String getOverdueStartTime() {
        return overdueStartTime;
    }

    public void setOverdueStartTime(String overdueStartTime) {
        this.overdueStartTime = overdueStartTime;
    }

    public String getOverdueEndTime() {
        return overdueEndTime;
    }

    public void setOverdueEndTime(String overdueEndTime) {
        this.overdueEndTime = overdueEndTime;
    }

    public String getOverdueExecUser() {
        return overdueExecUser;
    }

    public void setOverdueExecUser(String overdueExecUser) {
        this.overdueExecUser = overdueExecUser;
    }

    public Boolean getIsExecute() {
        return isExecute;
    }

    public void setIsExecute(Boolean isExecute) {
        this.isExecute = isExecute;
    }

    public String getToOperUser() {
        return toOperUser;
    }

    public void setToOperUser(String toOperUser) {
        this.toOperUser = toOperUser;
    }

    public String getLongestUseTime() {
        return longestUseTime;
    }

    public void setLongestUseTime(String longestUseTime) {
        this.longestUseTime = longestUseTime;
    }

}