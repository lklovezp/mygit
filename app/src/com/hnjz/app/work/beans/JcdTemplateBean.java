package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 检查单模板
 * @author zn
 * @version $Id: JcdTemplateBean.java, v 0.1 2013-3-20 上午12:47:02 zn Exp $
 */
public class JcdTemplateBean implements Serializable {

    /**  */
    private static final long        serialVersionUID     = 1L;
    /** 编号 */
    private String                   id;
    /** 名称 */
    private String                   name;
    /** 级别，从0开始 */
    private int                      level;
    /** 序号，从0开始 */
    private int                      order;
    /** 子模板 */
    private Set<JcdTemplateBean>     jcdTemplateBeans     = new LinkedHashSet<JcdTemplateBean>();
    /** 项 */
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
