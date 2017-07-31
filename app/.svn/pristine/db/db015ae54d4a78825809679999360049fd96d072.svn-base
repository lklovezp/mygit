package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.hnjz.app.work.po.Work;

/**
 * 任务分析
 * @author zn
 * @version $Id: WorkAnalyseBean.java, v 0.1 2013-4-8 上午12:22:36 zn Exp $
 */
public class WorkAnalyseBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    //任务基本信息
    /** 任务编号 */
    private String            workId;
    /** 任务名称 */
    private String            workName;
    /** 任务类型 */
    private String            workType;
    /** 紧急程度 */
    private String            urgencyDegree;
    /** 任务来源 */
    private String            workSource;
    /** 创建人 */
    private String            createUser;
    /** 创建时间 */
    private String            createTime;
    /** 开始时间 */
    private String            startTime;
    /** 结束时间 */
    private String            endTime;
    //当前任务状态
    /** 当前状态 */
    private String            curState;
    /** 是否已经执行 */
    private Boolean           isExecute;
    /** 执行时间 */
    private String            execTime;
    /** 执行人 */
    private String            execUser;
    /** 待操作人 */
    private String            toOperUser;
    //用时最长阶段
    /** 最长阶段步骤 */
    private String            longestSectionStep;
    /** 最长阶段开始时间 */
    private String            longestStartTime;
    /** 最长阶段结束时间 */
    private String            longestEndTime;
    /** 最长阶段执行人 */
    private String            longestExecUser;
    /** 最长阶段用时 */
    private String            longestUseTime;
    //逾期点分析
    /** 逾期阶段步骤 */
    private String            overdueSectionStep;
    /** 逾期阶段开始时间 */
    private String            overdueStartTime;
    /** 逾期阶段结束时间 */
    private String            overdueEndTime;
    /** 逾期阶段执行人 */
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
        //当前
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
