package com.hnjz.app.work.beans;

import java.io.Serializable;

/**
 * �����Ϣ��ҵ������֤�󷵻صĽ����
 * @author zn
 * @version $Id: ResultBean.java, v 0.1 2013-3-21 ����06:33:47 zn Exp $
 */
public class ResultBean implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** ��� */
    private Boolean           result           = false;
    /** ��� */
    private String            msg              = "";

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}