package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ��鵥ģ��
 * @author zn
 * @version $Id: JcdTemplateBean.java, v 0.1 2013-3-20 ����12:47:02 zn Exp $
 */
public class JcdTemplateBean implements Serializable {

    /**  */
    private static final long        serialVersionUID     = 1L;
    /** ��� */
    private String                   id;
    /** ���� */
    private String                   name;
    /** ���𣬴�0��ʼ */
    private int                      level;
    /** ��ţ���0��ʼ */
    private int                      order;
    /** ��ģ�� */
    private Set<JcdTemplateBean>     jcdTemplateBeans     = new LinkedHashSet<JcdTemplateBean>();
    /** �� */
    private Set<JcdTemplateItemBean> jcdTemplateItemBeans = new LinkedHashSet<JcdTemplateItemBean>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JcdTemplateItemBean> getJcdTemplateItemBeans() {
        return jcdTemplateItemBeans;
    }

    public void setJcdTemplateItemBeans(Set<JcdTemplateItemBean> jcdTemplateItemBeans) {
        this.jcdTemplateItemBeans = jcdTemplateItemBeans;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<JcdTemplateBean> getJcdTemplateBeans() {
        return jcdTemplateBeans;
    }

    public void setJcdTemplateBeans(Set<JcdTemplateBean> jcdTemplateBeans) {
        this.jcdTemplateBeans = jcdTemplateBeans;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}