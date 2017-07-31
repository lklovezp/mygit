/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.facade.domain;

import java.io.Serializable;

/**
 * ִ�н��
 * 
 * @author wumi
 * @version $Id: Result.java, v 0.1 Jan 5, 2012 6:37:13 AM Administrator Exp $
 */
public class Result implements Serializable {
    /**  */
    private static final long serialVersionUID = 3900861154835087949L;

    /**�Ƿ�ɹ�*/
    private boolean           ok;
    /**���ص�ҵ���ʾ��id*/
    private String            id;
    /**��ʾ��Ϣ*/
    private String            note;

    public Result() {
    }

    public Result(boolean ok) {
        this.ok = ok;
        if (this.ok) {
            this.note = "�����ɹ�";
        } else {
            this.note = "����ʧ��";
        }
    }

    public Result(boolean ok, String note) {
        this.ok = ok;
        this.note = note;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}