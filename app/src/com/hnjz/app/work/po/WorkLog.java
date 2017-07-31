/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.common.util.DateDifferenceUtil;

/**
 * ������־��
 * 
 * @author wumi
 * @version $Id: WorkLog.java, v 0.1 Feb 19, 2013 2:36:03 PM wumi Exp $
 */
public class WorkLog {

    private String id;
    /**����*/
    private Work   work;
    /**����������*/
    private String czrId;
    /**����������*/
    private String czrName;
    /**����״̬*/
    private String workSate;
    /**��������*/
    private String operateType;
    /**����ʱ��*/
    private Date   czsj;
    /**��ע*/
    private String note;
    /** ��ʼʱ�� */
    private Date   startTime;
    /** ʹ��ʱ�� */
    private Long   userTime;

    public Map<String, Object> toAnalysisMap() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("operateType",
            getOperateType() == null ? "" : WorkLogType.getNote(getOperateType()));
        map.put("czrName", getCzrName());
        map.put("startTime", sdf.format(getStartTime()));
        map.put("endTime", sdf.format(getCzsj()));
        map.put("userTime", getUserTime() == null ? "" : DateDifferenceUtil.getTimeConvert(getUserTime()));
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getCzrId() {
        return czrId;
    }

    public void setCzrId(String czrId) {
        this.czrId = czrId;
    }

    public String getCzrName() {
        return czrName;
    }

    public void setCzrName(String czrName) {
        this.czrName = czrName;
    }

    public String getWorkSate() {
        return workSate;
    }

    public void setWorkSate(String workSate) {
        this.workSate = workSate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getUserTime() {
        return userTime;
    }

    public void setUserTime(Long userTime) {
        this.userTime = userTime;
    }
    
    public String getOperateTypeNote(){
        return WorkLogType.getNote(this.operateType);
    }
    
    public String getTimes(){
       return DateDifferenceUtil.getTimeConvert(getUserTime());
    }

}