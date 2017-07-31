package com.hnjz.sys.po;

import java.io.Serializable;

/**
 * ��ɫ���ܲ�����ϵ,��ͼ
 * 
 * @author wumi
 * @version $Id: RoleFuncOperV.java, v 0.1 Jan 6, 2013 11:49:37 AM wumi Exp $
 */
public class RoleFuncOperV implements Serializable {

    private static final long serialVersionUID = 5641090619442721819L;

    private String            operateId;                              // ���

    private String            roleId;                                 // ��ɫ���

    private String            functionId;                             // ���ܣ�

    private String            onclickEvent;                           // ����¼���

    private String            linkAddr;                               // ���ӣ�

    private String            note;                                   // ��ע

    private String            funLink;                                // �˵�������

    private String            fashion;                                //�Ƿ����������ʾ

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getOnclickEvent() {
        return onclickEvent;
    }

    public void setOnclickEvent(String onclickEvent) {
        this.onclickEvent = onclickEvent;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFunLink() {
        return funLink;
    }

    public void setFunLink(String funLink) {
        this.funLink = funLink;
    }

    public String getFashion() {
        return fashion;
    }

    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

}