/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.sys.function;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * �˵����ܵ�form����
 * 
 * @author wumi
 * @version $Id: FunForm.java, v 0.1 Jan 10, 2012 12:59:51 AM Administrator Exp $
 */
public class FunForm {

    /**���ܱ��*/
    private String id;
    /**��������*/
    private String funcName;
    /**��������*/
    private String funcDesc;
    /**�Ƿ�ĩ�ڵ�*/
    private String isLast;
    /**��������*/
    private String linkaddr;
    /**�Ƿ񿪷�*/
    private String isOpen;
    /**����*/
    private String orderby;
    /**����*/
    private String parent;
    /**��������*/
    private String parentName;

    /**��������*/
    private String data;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    public String getIsLast() {
        return isLast;
    }

    public void setIsLast(String isLast) {
        this.isLast = isLast;
    }

    public String getLinkaddr() {
        return linkaddr;
    }

    public void setLinkaddr(String linkaddr) {
        this.linkaddr = linkaddr;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}