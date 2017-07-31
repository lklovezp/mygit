package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class WorkDcStatisticsCityBean implements Serializable {
    /**  */
    private static final long           serialVersionUID = 1L;
    private String                      cityId;
    private String                      cityName;
    private String                      totalCount;
    private String[]                    workCount;
    private String[]                    workTypeId;
    private String[]                    workTypeName;
    private Set<WorkTypeStatisticsBean> typeBeans        = new LinkedHashSet<WorkTypeStatisticsBean>();

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityId", cityId);
        map.put("cityName", cityName);
        map.put("totalCount", totalCount);
        map.put("workCount", workCount);
        map.put("workTypeId", workTypeId);
        map.put("workTypeName", workTypeName);
        return map;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
