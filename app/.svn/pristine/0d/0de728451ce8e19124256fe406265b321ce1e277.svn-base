package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 检查单
 * @author zn
 * @version $Id: JcdBean.java, v 0.1 2013-3-20 上午12:46:39 zn Exp $
 */
public class JcdBean implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** 名称 */
    private String            name;
    /** 模板 */
    private JcdTemplateBean   jcdTemplateBean  = new JcdTemplateBean();

    public String toString() {
        StringBuffer str = new StringBuffer();
        recursionTemplate(str, getJcdTemplateBean());
        return str.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JcdTemplateBean getJcdTemplateBean() {
        return jcdTemplateBean;
    }

    public void setJcdTemplateBean(JcdTemplateBean jcdTemplateBean) {
        this.jcdTemplateBean = jcdTemplateBean;
    }

    /**
     * 递归得到检查单输出文本
     * @param strBuffer
     * @param root
     */
    private void recursionTemplate(StringBuffer strBuffer, JcdTemplateBean root) {
        if (root.getLevel() > 0) {
            strBuffer.append(getBlank(root.getLevel())).append(root.getLevel()).append(".")
                .append(root.getOrder() + 1).append(" ");
        }
        strBuffer.append(root.getName()).append("\r\n");
        //问题项
        if (root.getJcdTemplateItemBeans().size() > 0) {
            Iterator<JcdTemplateItemBean> jcdTemplateItemBeanIt = root.getJcdTemplateItemBeans()
                .iterator();
            JcdTemplateItemBean jcdTemplateItemBean = null;
            JcdTemplateItemResultBean jcdTemplateItemResultBean = null;
            while (jcdTemplateItemBeanIt.hasNext()) {
                jcdTemplateItemBean = jcdTemplateItemBeanIt.next();
                strBuffer.append(getBlank(root.getLevel() + 2))
                    .append(jcdTemplateItemBean.getName()).append("\r\n");
                //答案
                Iterator<JcdTemplateItemResultBean> jcdTemplateItemResultBeanIt = jcdTemplateItemBean
                    .getJcdTemplateItemResultBeans().iterator();
                while (jcdTemplateItemResultBeanIt.hasNext()) {
                    jcdTemplateItemResultBean = jcdTemplateItemResultBeanIt.next();
                    strBuffer.append(getBlank(root.getLevel() + 4))
                        .append(jcdTemplateItemResultBean.getName()).append("\r\n");
                }
            }
        }
        if (root.getJcdTemplateBeans().size() > 0) {
            Iterator<JcdTemplateBean> jcdTemplateBeanIt = root.getJcdTemplateBeans().iterator();
            JcdTemplateBean jcdTemplateBean = null;
            while (jcdTemplateBeanIt.hasNext()) {
                jcdTemplateBean = jcdTemplateBeanIt.next();
                recursionTemplate(strBuffer, jcdTemplateBean);
            }
        }
    }

    private String getBlank(int num) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < num; i++) {
            str.append(" ");
        }
        return str.toString();
    }
}
