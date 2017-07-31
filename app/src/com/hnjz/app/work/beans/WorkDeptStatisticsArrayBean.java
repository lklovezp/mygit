package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 部门统计(报表使用)
 * @author zn
 * @version $Id: WorkDeptStatisticsBean.java, v 0.1 2013-4-17 上午02:07:39 zn Exp $
 */
public class WorkDeptStatisticsArrayBean implements Serializable {
    /**  */
    private static final long serialVersionUID = 1L;
    private String            orgName;
    private String            forgName;

    private String            xd0              = "0";
    private String            wc0              = "0";
    private String            yqwc0            = "0";
    private String            zx0              = "0";
    private String            yqwwc0           = "0";

    private String            xd1              = "0";
    private String            wc1              = "0";
    private String            yqwc1            = "0";
    private String            zx1              = "0";
    private String            yqwwc1           = "0";

    private String            xd2              = "0";
    private String            wc2              = "0";
    private String            yqwc2            = "0";
    private String            zx2              = "0";
    private String            yqwwc2           = "0";

    private String            xd3              = "0";
    private String            wc3              = "0";
    private String            yqwc3            = "0";
    private String            zx3              = "0";
    private String            yqwwc3           = "0";

    private String            xd4              = "0";
    private String            wc4              = "0";
    private String            yqwc4            = "0";
    private String            zx4              = "0";
    private String            yqwwc4           = "0";

    private String            xd5              = "0";
    private String            wc5              = "0";
    private String            yqwc5            = "0";
    private String            zx5              = "0";
    private String            yqwwc5           = "0";

    public WorkDeptStatisticsArrayBean() {
    }

    public WorkDeptStatisticsArrayBean(List<Object> list) {
        WorkDeptStatisticsArrayBean bean = null;
        for (int i = 0; i < list.size(); i++) {
            bean = (WorkDeptStatisticsArrayBean) list.get(i);
            this.forgName = "合计：";
            this.xd0 = String.valueOf(Integer.parseInt(this.xd0)
                                      + Integer.parseInt(bean.getXd0()));
            this.xd1 = String.valueOf(Integer.parseInt(this.xd1)
                                      + Integer.parseInt(bean.getXd1()));
            this.xd2 = String.valueOf(Integer.parseInt(this.xd2)
                                      + Integer.parseInt(bean.getXd2()));
            this.xd3 = String.valueOf(Integer.parseInt(this.xd3)
                                      + Integer.parseInt(bean.getXd3()));
            this.xd4 = String.valueOf(Integer.parseInt(this.xd4)
                                      + Integer.parseInt(bean.getXd4()));
            this.xd5 = String.valueOf(Integer.parseInt(this.xd5)
                                      + Integer.parseInt(bean.getXd5()));

            this.wc0 = String.valueOf(Integer.parseInt(this.wc0)
                                      + Integer.parseInt(bean.getWc0()));
            this.wc1 = String.valueOf(Integer.parseInt(this.wc1)
                                      + Integer.parseInt(bean.getWc1()));
            this.wc2 = String.valueOf(Integer.parseInt(this.wc2)
                                      + Integer.parseInt(bean.getWc2()));
            this.wc3 = String.valueOf(Integer.parseInt(this.wc3)
                                      + Integer.parseInt(bean.getWc3()));
            this.wc4 = String.valueOf(Integer.parseInt(this.wc4)
                                      + Integer.parseInt(bean.getWc4()));
            this.wc5 = String.valueOf(Integer.parseInt(this.wc5)
                                      + Integer.parseInt(bean.getWc5()));

            this.yqwc0 = String.valueOf(Integer.parseInt(this.yqwc0)
                                        + Integer.parseInt(bean.getYqwc0()));
            this.yqwc1 = String.valueOf(Integer.parseInt(this.yqwc1)
                                        + Integer.parseInt(bean.getYqwc1()));
            this.yqwc2 = String.valueOf(Integer.parseInt(this.yqwc2)
                                        + Integer.parseInt(bean.getYqwc2()));
            this.yqwc3 = String.valueOf(Integer.parseInt(this.yqwc3)
                                        + Integer.parseInt(bean.getYqwc3()));
            this.yqwc4 = String.valueOf(Integer.parseInt(this.yqwc4)
                                        + Integer.parseInt(bean.getYqwc4()));
            this.yqwc5 = String.valueOf(Integer.parseInt(this.yqwc5)
                                        + Integer.parseInt(bean.getYqwc5()));

            this.zx0 = String.valueOf(Integer.parseInt(this.zx0)
                                      + Integer.parseInt(bean.getZx0()));
            this.zx1 = String.valueOf(Integer.parseInt(this.zx1)
                                      + Integer.parseInt(bean.getZx1()));
            this.zx2 = String.valueOf(Integer.parseInt(this.zx2)
                                      + Integer.parseInt(bean.getZx2()));
            this.zx3 = String.valueOf(Integer.parseInt(this.zx3)
                                      + Integer.parseInt(bean.getZx3()));
            this.zx4 = String.valueOf(Integer.parseInt(this.zx4)
                                      + Integer.parseInt(bean.getZx4()));
            this.zx5 = String.valueOf(Integer.parseInt(this.zx5)
                                      + Integer.parseInt(bean.getZx5()));

            this.yqwwc0 = String.valueOf(Integer.parseInt(this.yqwwc0)
                                         + Integer.parseInt(bean.getYqwwc0()));
            this.yqwwc1 = String.valueOf(Integer.parseInt(this.yqwwc1)
                                         + Integer.parseInt(bean.getYqwwc1()));
            this.yqwwc2 = String.valueOf(Integer.parseInt(this.yqwwc2)
                                         + Integer.parseInt(bean.getYqwwc2()));
            this.yqwwc3 = String.valueOf(Integer.parseInt(this.yqwwc3)
                                         + Integer.parseInt(bean.getYqwwc3()));
            this.yqwwc4 = String.valueOf(Integer.parseInt(this.yqwwc4)
                                         + Integer.parseInt(bean.getYqwwc4()));
            this.yqwwc5 = String.valueOf(Integer.parseInt(this.yqwwc5)
                                         + Integer.parseInt(bean.getYqwwc5()));
        }
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getXd0() {
        return xd0;
    }

    public void setXd0(String xd0) {
        this.xd0 = xd0;
    }

    public String getWc0() {
        return wc0;
    }

    public void setWc0(String wc0) {
        this.wc0 = wc0;
    }

    public String getYqwc0() {
        return yqwc0;
    }

    public void setYqwc0(String yqwc0) {
        this.yqwc0 = yqwc0;
    }

    public String getZx0() {
        return zx0;
    }

    public void setZx0(String zx0) {
        this.zx0 = zx0;
    }

    public String getYqwwc0() {
        return yqwwc0;
    }

    public void setYqwwc0(String yqwwc0) {
        this.yqwwc0 = yqwwc0;
    }

    public String getXd1() {
        return xd1;
    }

    public void setXd1(String xd1) {
        this.xd1 = xd1;
    }

    public String getWc1() {
        return wc1;
    }

    public void setWc1(String wc1) {
        this.wc1 = wc1;
    }

    public String getYqwc1() {
        return yqwc1;
    }

    public void setYqwc1(String yqwc1) {
        this.yqwc1 = yqwc1;
    }

    public String getZx1() {
        return zx1;
    }

    public void setZx1(String zx1) {
        this.zx1 = zx1;
    }

    public String getYqwwc1() {
        return yqwwc1;
    }

    public void setYqwwc1(String yqwwc1) {
        this.yqwwc1 = yqwwc1;
    }

    public String getXd2() {
        return xd2;
    }

    public void setXd2(String xd2) {
        this.xd2 = xd2;
    }

    public String getWc2() {
        return wc2;
    }

    public void setWc2(String wc2) {
        this.wc2 = wc2;
    }

    public String getYqwc2() {
        return yqwc2;
    }

    public void setYqwc2(String yqwc2) {
        this.yqwc2 = yqwc2;
    }

    public String getZx2() {
        return zx2;
    }

    public void setZx2(String zx2) {
        this.zx2 = zx2;
    }

    public String getYqwwc2() {
        return yqwwc2;
    }

    public void setYqwwc2(String yqwwc2) {
        this.yqwwc2 = yqwwc2;
    }

    public String getXd3() {
        return xd3;
    }

    public void setXd3(String xd3) {
        this.xd3 = xd3;
    }

    public String getWc3() {
        return wc3;
    }

    public void setWc3(String wc3) {
        this.wc3 = wc3;
    }

    public String getYqwc3() {
        return yqwc3;
    }

    public void setYqwc3(String yqwc3) {
        this.yqwc3 = yqwc3;
    }

    public String getZx3() {
        return zx3;
    }

    public void setZx3(String zx3) {
        this.zx3 = zx3;
    }

    public String getYqwwc3() {
        return yqwwc3;
    }

    public void setYqwwc3(String yqwwc3) {
        this.yqwwc3 = yqwwc3;
    }

    public String getXd4() {
        return xd4;
    }

    public void setXd4(String xd4) {
        this.xd4 = xd4;
    }

    public String getWc4() {
        return wc4;
    }

    public void setWc4(String wc4) {
        this.wc4 = wc4;
    }

    public String getYqwc4() {
        return yqwc4;
    }

    public void setYqwc4(String yqwc4) {
        this.yqwc4 = yqwc4;
    }

    public String getZx4() {
        return zx4;
    }

    public void setZx4(String zx4) {
        this.zx4 = zx4;
    }

    public String getYqwwc4() {
        return yqwwc4;
    }

    public void setYqwwc4(String yqwwc4) {
        this.yqwwc4 = yqwwc4;
    }

    public String getXd5() {
        return xd5;
    }

    public void setXd5(String xd5) {
        this.xd5 = xd5;
    }

    public String getWc5() {
        return wc5;
    }

    public void setWc5(String wc5) {
        this.wc5 = wc5;
    }

    public String getYqwc5() {
        return yqwc5;
    }

    public void setYqwc5(String yqwc5) {
        this.yqwc5 = yqwc5;
    }

    public String getZx5() {
        return zx5;
    }

    public void setZx5(String zx5) {
        this.zx5 = zx5;
    }

    public String getYqwwc5() {
        return yqwwc5;
    }

    public void setYqwwc5(String yqwwc5) {
        this.yqwwc5 = yqwwc5;
    }

    public String getForgName() {
        return forgName;
    }

    public void setForgName(String forgName) {
        this.forgName = forgName;
    }

}
