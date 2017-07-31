package com.hnjz.sys.po;

import java.io.Serializable;

/**
 * 角色功能操作关系,视图
 * 
 * @author wumi
 * @version $Id: RoleFuncOperV.java, v 0.1 Jan 6, 2013 11:49:37 AM wumi Exp $
 */
public class RoleFuncOperV implements Serializable {

    private static final long serialVersionUID = 5641090619442721819L;

    private String            operateId;                              // 编号

    private String            roleId;                                 // 角色编号

    private String            functionId;                             // 功能；

    private String            onclickEvent;                           // 点击事件；

    private String            linkAddr;                               // 链接；

    private String            note;                                   // 备注

    private String            funLink;                                // 菜单的链接

    private String            fashion;                                //是否在类表中显示

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