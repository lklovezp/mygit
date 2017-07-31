/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.hjgl;

import java.util.Date;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;

/**
 * 
 * �ۼ���������ͼ����
 * 
 * @author wumi
 * @version $Id: Vhjgl.java, v 0.1 Apr 7, 2013 4:14:31 PM wumi Exp $
 */
public class Vhjgl {

    private String id;
    /**����*/
    private String work;
    /**����������*/
    private String czrId;
    /**����������*/
    private String czrName;
    /**��ʱ����״̬*/
    private String workSate;
    /**��������*/
    private String operateType;
    /**����ʱ��*/
    private Date   czsj;
    /**��ע*/
    private String note;
    /**��������*/
    private String workName;
    /**������*/
    private String cjr;
    /**����������*/
    private String cname;
    /**ִ��������*/
    private String zxrName;
    /**����ʱ��*/
    private Date   cjsj;
    /** ��ʼʱ�� */
    private Date   rwStartTime;
    /** ����ʱ�� */
    private Date   rwEndTime;
    /** ����Id */
    private String areaId;
    /** �������� */
    private String workType;
    /**��ǰ����״̬*/
    private String sate;
    
    public String getOperateTypeNote(){
        return WorkLogType.getNote(this.operateType);
    }
    
    public String getWorkSateNote(){
        return WorkState.getNote(this.workSate);
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
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

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getRwStartTime() {
        return rwStartTime;
    }

    public void setRwStartTime(Date rwStartTime) {
        this.rwStartTime = rwStartTime;
    }

    public Date getRwEndTime() {
        return rwEndTime;
    }

    public void setRwEndTime(Date rwEndTime) {
        this.rwEndTime = rwEndTime;
    }

    public String getSate() {
        return sate;
    }

    public void setSate(String sate) {
        this.sate = sate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getZxrName() {
        return zxrName;
    }

    public void setZxrName(String zxrName) {
        this.zxrName = zxrName;
    }

}