package com.hnjz.app.work.beans;

import java.io.Serializable;

/**
 * �������
 * @author zn
 * @version $Id: JcdTemplateItemResultBean.java, v 0.1 2013-3-20 ����01:58:31 zn Exp $
 */
public class JcdTemplateItemResultBean implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    /** ���� */
    private String            name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}