package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 部门统计
 * @author zn
 * @version $Id: WorkDeptStatisticsBean.java, v 0.1 2013-4-17 上午02:07:39 zn Exp $
 */
public class WorkDeptStatisticsBean implements Serializable {
    /**  */
    private static final long               serialVersionUID = 1L;
    private String                          orgId;
    private String                          orgName;
    /** 父部门 */
    private String                          fOrgName;
    /** 总下达 */
    private String                          zxd;
    /** 总完成 */
    private String                          zwc;
    /** 总逾期完成 */
    private String                          zyqwc;
    /** 总执行 */
    private String                          zzx;
    /** 总逾期未完成 */
    private String                          zyqwwc;
    /** 任务类型编号集合 */
    private String[]                        workTypeIds;
    /** 任务类型名称 */
    private String[]                        workTypeNames;
    private Set<WorkDeptTypeStatisticsBean> typeBeans        = new LinkedHashSet<WorkDeptTypeStatisticsBean>();

    public WorkDeptStatisticsArrayBean toArrayBean() {
        WorkDeptStatisticsArrayBean bean = new WorkDeptStatisticsArrayBean();
        bean.setForgName(getfOrgName());
        bean.setOrgName(getOrgName());
        bean.setXd0(getZxd());
        bean.setWc0(getZwc());
        bean.setYqwc0(getZyqwc());
        bean.setZx0(getZzx());
        bean.setYqwwc0(getZyqwwc());
        int i = 1;
        Iterator<WorkDeptTypeStatisticsBean> typeIt = typeBeans.iterator();
        WorkDeptTypeStatisticsBean typeBean = null;
        while (typeIt.hasNext()) {
            typeBean = typeIt.next();
            switch (i) {
                case 1:
                    bean.setXd1(typeBean.getXd());
                    bean.setWc1(typeBean.getWc());
                    bean.setYqwc1(typeBean.getYqwc());
                    bean.setZx1(typeBean.getZx());
                    bean.setYqwwc1(typeBean.getYqwwc());
                    break;
                case 2:
                    bean.setXd2(typeBean.getXd());
                    bean.setWc2(typeBean.getWc());
                    bean.setYqwc2(typeBean.getYqwc());
                    bean.setZx2(typeBean.getZx());
                    bean.setYqwwc2(typeBean.getYqwwc());
                    break;
                case 3:
                    bean.setXd3(typeBean.getXd());
                    bean.setWc3(typeBean.getWc());
                    bean.setYqwc3(typeBean.getYqwc());
                    bean.setZx3(typeBean.getZx());
                    bean.setYqwwc3(typeBean.getYqwwc());
                    break;
                case 4:
                    bean.setXd4(typeBean.getXd());
                    bean.setWc4(typeBean.getWc());
                    bean.setYqwc4(typeBean.getYqwc());
                    bean.setZx4(typeBean.getZx());
                    bean.setYqwwc4(typeBean.getYqwwc());
                    break;
                case 5:
                    bean.setXd5(typeBean.getXd());
                    bean.setWc5(typeBean.getWc());
                    bean.setYqwc5(typeBean.getYqwc());
                    bean.setZx5(typeBean.getZx());
                    bean.setYqwwc5(typeBean.getYqwwc());
                    break;

                default:
                    break;
            }
            i++;
        }
        return bean;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getfOrgName() {
        return fOrgName;
    }

    public void setfOrgName(String fOrgName) {
        this.fOrgName = fOrgName;
    }

    public String getZxd() {
        return zxd;
    }

    public void setZxd(String zxd) {
        this.zxd = zxd;
    }

    public String getZwc() {
        return zwc;
    }

    public void setZwc(String zwc) {
        this.zwc = zwc;
    }

    public String getZyqwc() {
        return zyqwc;
    }

    public void setZyqwc(String zyqwc) {
        this.zyqwc = zyqwc;
    }

    public String getZzx() {
        return zzx;
    }

    public void setZzx(String zzx) {
        this.zzx = zzx;
    }

    public String getZyqwwc() {
        return zyqwwc;
    }

    public void setZyqwwc(String zyqwwc) {
        this.zyqwwc = zyqwwc;
    }

    public Set<WorkDeptTypeStatisticsBean> getTypeBeans() {
        return typeBeans;
    }

    public void setTypeBeans(Set<WorkDeptTypeStatisticsBean> typeBeans) {
        this.typeBeans = typeBeans;
    }

    public String[] getWorkTypeIds() {
        return workTypeIds;
    }

    public void setWorkTypeIds(String[] workTypeIds) {
        this.workTypeIds = workTypeIds;
    }

    public String[] getWorkTypeNames() {
        return workTypeNames;
    }

    public void setWorkTypeNames(String[] workTypeNames) {
        this.workTypeNames = workTypeNames;
    }

}
