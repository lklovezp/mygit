/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.po;

import java.util.Date;


/**
 * 
 * @author wangliang
 * @version $Id: Sms.java, v 0.1 Aug 21, 2013 3:12:26 PM wangliang Exp $
 */
public class Sms {

    private String      id;                    // ����Ψһ��ʶ
    private String      jsrName;         // ����������
    private String      jsrPhone;        // �����˵绰
    private String      smsContent;   // ��������
    private String      memo;            // ��ע
    private int           sendCount;    // ���ŷ��ʹ�����0��δ���ͣ�1������һ�Σ�2����������  ... �Դ�����
    private Date        uploadTime;   // ���ż�¼���ʱ��
    private Date        sendTime;      // ���ż�¼���ʱ��
    
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Date getUploadTime() {
        return uploadTime;
    }
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJsrName() {
        return jsrName;
    }
    public void setJsrName(String jsrName) {
        this.jsrName = jsrName;
    }
    public String getJsrPhone() {
        return jsrPhone;
    }
    public void setJsrPhone(String jsrPhone) {
        this.jsrPhone = jsrPhone;
    }
    public String getSmsContent() {
        return smsContent;
    }
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public int getSendCount() {
        return sendCount;
    }
    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }
}