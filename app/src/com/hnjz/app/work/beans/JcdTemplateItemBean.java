package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ��鵥ģ����
 * @author zn
 * @version $Id: JcdTemplateItemBean.java, v 0.1 2013-3-20 ����12:49:13 zn Exp $
 */
public class JcdTemplateItemBean implements Serializable {

    /**  */
    private static final long              serialVersionUID           = 1L;
    /** ��� */
    private String                         id;
    /** ���� */
    private String                         name;
    /** ��� */
    private Set<JcdTemplateItemResultBean> jcdTemplateItemResultBeans = new LinkedHashSet<JcdTemplateItemResultBean>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JcdTemplateItemResultBean> getJcdTemplateItemResultBeans() {
        return jcdTemplateItemResultBeans;
    }

    public void setJcdTemplateItemResultBeans(Set<JcdTemplateItemResultBean> jcdTemplateItemResultBeans) {
        this.jcdTemplateItemResultBeans = jcdTemplateItemResultBeans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}