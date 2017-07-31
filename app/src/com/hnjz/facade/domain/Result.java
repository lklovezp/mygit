/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.facade.domain;

import java.io.Serializable;

/**
 * 执行结果
 * 
 * @author wumi
 * @version $Id: Result.java, v 0.1 Jan 5, 2012 6:37:13 AM Administrator Exp $
 */
public class Result implements Serializable {
    /**  */
    private static final long serialVersionUID = 3900861154835087949L;

    /**是否成功*/
    private boolean           ok;
    /**返回的业务标示的id*/
    private String            id;
    /**提示信息*/
    private String            note;

    public Result() {
    }

    public Result(boolean ok) {
        this.ok = ok;
        if (this.ok) {
            this.note = "操作成功";
        } else {
            this.note = "操作失败";
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
