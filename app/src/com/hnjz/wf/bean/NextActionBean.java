package com.hnjz.wf.bean;

import java.io.Serializable;

public class NextActionBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    /** ���� */
    private String            code;
    /** ���� */
    private String            text;
    /** � */
    private String            action;
    /** ����� */
    private String            actionType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

}