/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.wf.entity;

/**
 * 工作流的代办任务
 * 
 * @author wumi
 * @version $Id: Wfdb.java, v 0.1 Jun 17, 2013 9:42:56 AM wumi Exp $
 */
public class Wfdb {

    private String  id;

    /**任务id*/
    private String  workId;
    
    /**jbpm id*/
    private Integer taskId;

    /** 检测的次数（“到期任务检测时使用”）*/
    private int                detecCount;

    public int getDetecCount() {
        return detecCount;
    }

    public void setDetecCount(int detecCount) {
        this.detecCount = detecCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

}
