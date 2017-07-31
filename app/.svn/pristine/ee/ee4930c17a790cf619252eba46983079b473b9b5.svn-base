package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class WorkDcStatisticsBean implements Serializable {
    /**  */
    private static final long           serialVersionUID = 1L;
    private String                      orgId;
    private String                      orgName;
    private String                      fOrgName;
    private String                      totalCount;
    private String[]                    workCount;
    private String[]                    workTypeId;
    private String[]                    workTypeName;
    private Set<WorkTypeStatisticsBean> typeBeans        = new LinkedHashSet<WorkTypeStatisticsBean>();

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgId", orgId);
        map.put("orgName", orgName);
        map.put("fOrgName", fOrgName);
        map.put("totalCount", totalCount);
        map.put("workCount", workCount);
        map.put("workTypeId", workTypeId);
        map.put("workTypeName", workTypeName);
        return map;
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

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String[] getWorkCount() {
        return workCount;
    }

    public void setWorkCount(String[] workCount) {
        this.workCount = workCount;
    }

    public String[] getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String[] workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String[] getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String[] workTypeName) {
        this.workTypeName = workTypeName;
    }

    public Set<WorkTypeStatisticsBean> getTypeBeans() {
        return typeBeans;
    }

    public void setTypeBeans(Set<WorkTypeStatisticsBean> typeBeans) {
        this.typeBeans = typeBeans;
    }
}
