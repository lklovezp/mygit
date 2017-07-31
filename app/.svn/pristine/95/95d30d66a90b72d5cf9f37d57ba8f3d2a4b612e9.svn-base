package com.hnjz.wf.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程信息,用于待办任务显示
 * 
 * @author zn
 * @version $Id: ProcessInfoBean.java, v 0.1 2013-1-15 上午12:30:59 Administrator
 *          Exp $
 */
public class ProcessInfoBean implements Serializable {
    /**  */
    private static final long    serialVersionUID = 1L;
    /** 申请单编号 */
    private String               applyId;
    /** 申请单名称 */
    private String               applyName;
    /** 申请单类型 */
    private String               applyType;
    /** 流程编号 */
    private String               processId;
    /** 流程KEY值 */
    private String               processKey;
    /** 任务编号 */
    private String               taskId;
    /** 任务名称 */
    private String               taskName;
    /** 任务类型的标示，参见枚举WorkTypeCode  */
    private String               code;
    /** 操作 */
    private List<NextActionBean> nextActions;
    /**结束日期*/
    private Date                 endTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ProcessInfoBean(String applyId, String applyName, String applyType, String processId,
                           String processKey, String taskId, String taskName,
                           List<NextActionBean> nextActions, String code,Date endTime) {
        this.applyId = applyId;
        this.applyName = applyName;
        this.applyType = applyType;
        this.processId = processId;
        this.processKey = processKey;
        this.taskId = taskId;
        this.taskName = taskName;
        this.nextActions = nextActions;
        this.code = code;
        this.endTime=endTime;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyId", getApplyId());
        map.put("applyName", getApplyName());
        map.put("applyType", getApplyType());
        map.put("processId", getProcessId());
        map.put("processKey", getProcessKey());
        map.put("taskId", getTaskId());
        map.put("taskName", getTaskName());
        //操作
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < nextActions.size(); i++) {
            str.append("<a href=\"#\" onclick=\"actionOper('").append(nextActions.get(i).getCode())
                .append("','").append(nextActions.get(i).getAction()).append("','")
                .append(nextActions.get(i).getActionType()).append("','").append(getApplyId())
                .append("','").append(getTaskId()).append("','")
                .append(nextActions.get(i).getText()).append("')\">")
                .append(nextActions.get(i).getText()).append("</a>&nbsp;");
        }
        str.append("<a href=\"#\" onclick=\"flowChart('").append(getProcessId()).append("')\">")
            .append("流程图").append("</a>&nbsp;");
        map.put("nextActions", str.toString());
        return map;
    }

    public Map<String, Object> toMobileMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyId", getApplyId());
        map.put("applyName", getApplyName());
        map.put("applyType", getApplyType());
        map.put("taskId", getTaskId());
        map.put("taskName", getTaskName());
        map.put("taskType", getCode());
        map.put("nextActions", getNextActions());
        return map;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

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

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<NextActionBean> getNextActions() {
        return nextActions;
    }

    public void setNextActions(List<NextActionBean> nextActions) {
        this.nextActions = nextActions;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
