package com.hnjz.app.work.beans;

import java.io.Serializable;

/**
 * ����ͳ��-������ͳ��
 * @author zn
 * @version $Id: WorkDeptTypeStatisticsBean.java, v 0.1 2013-4-17 ����02:05:28 zn Exp $
 */
public class WorkDeptTypeStatisticsBean implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    /** �������ͱ�� */
    private String            workTypeId;
    /** ������������ */
    private String            workTypeName;
    /** �´� */
    private String            xd;
    /** ��� */
    private String            wc;
    /** ������� */
    private String            yqwc;
    /** ִ�� */
    private String            zx;
    /** ����δ��� */
    private String            yqwwc;

    public WorkDeptTypeStatisticsBean() {
    }

    public WorkDeptTypeStatisticsBean(String workTypeId, String workTypeName, String xd, String wc,
                                      String yqwc, String zx, String yqwwc) {
        this.workTypeId = workTypeId;
        this.workTypeName = workTypeName;
        this.xd = xd;
        this.wc = wc;
        this.yqwc = yqwc;
        this.zx = zx;
        this.yqwwc = yqwwc;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }

    public String getWc() {
        return wc;
    }

    public void setWc(String wc) {
        this.wc = wc;
    }

    public String getYqwc() {
        return yqwc;
    }

    public void setYqwc(String yqwc) {
        this.yqwc = yqwc;
    }

    public String getZx() {
        return zx;
    }

    public void setZx(String zx) {
        this.zx = zx;
    }

    public String getYqwwc() {
        return yqwwc;
    }

    public void setYqwwc(String yqwwc) {
        this.yqwwc = yqwwc;
    }

}