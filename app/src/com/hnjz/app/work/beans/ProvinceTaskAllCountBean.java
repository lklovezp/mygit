package com.hnjz.app.work.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProvinceTaskAllCountBean implements Serializable {
    /**  */
    private static final long           serialVersionUID = 1L;
    private String areaid;
    private String areaName;
    private int allCount;
    private int overCount;
    private int outDateOverCount;
    private int notOverCount;
    private int outDateNotOverCount;
    private List<ProvinceTaskWorkTypeCountBean> ptwtcBeanList = new ArrayList<ProvinceTaskWorkTypeCountBean>();
    
    public ProvinceTaskArrayBean toArrayBean() {
    	ProvinceTaskArrayBean bean = new ProvinceTaskArrayBean();
        bean.setAreaName(getAreaName());
        bean.setAllCount0(String.valueOf(getAllCount()));
        bean.setOverCount0(String.valueOf(getOverCount()));
        bean.setOutDateOverCount0(String.valueOf(getOutDateOverCount()));
        bean.setNotOverCount0(String.valueOf(getNotOverCount()));
        bean.setOutDateNotOverCount0(String.valueOf(getOutDateNotOverCount()));
        int i = 1;
        Iterator<ProvinceTaskWorkTypeCountBean> typeIt = ptwtcBeanList.iterator();
        ProvinceTaskWorkTypeCountBean typeBean = null;
        while (typeIt.hasNext()) {
            typeBean = typeIt.next();
            switch (i) {
                case 1:
                    bean.setAllCount1(String.valueOf(typeBean.getWorkTypeAllCount()));
                    bean.setOverCount1(String.valueOf(typeBean.getWorkTypeOverCount()));
                    bean.setOutDateOverCount1(String.valueOf(typeBean.getWorkTypeOutDateOverCount()));
                    bean.setNotOverCount1(String.valueOf(typeBean.getWorkTypeNotOverCount()));
                    bean.setOutDateNotOverCount1(String.valueOf(typeBean.getWorkTypeOutDateNotOverCount()));
                    break;
                case 2:
                    bean.setAllCount2(String.valueOf(typeBean.getWorkTypeAllCount()));
                    bean.setOverCount2(String.valueOf(typeBean.getWorkTypeOverCount()));
                    bean.setOutDateOverCount2(String.valueOf(typeBean.getWorkTypeOutDateOverCount()));
                    bean.setNotOverCount2(String.valueOf(typeBean.getWorkTypeNotOverCount()));
                    bean.setOutDateNotOverCount2(String.valueOf(typeBean.getWorkTypeOutDateNotOverCount()));
                    break;
                case 3:
                    bean.setAllCount3(String.valueOf(typeBean.getWorkTypeAllCount()));
                    bean.setOverCount3(String.valueOf(typeBean.getWorkTypeOverCount()));
                    bean.setOutDateOverCount3(String.valueOf(typeBean.getWorkTypeOutDateOverCount()));
                    bean.setNotOverCount3(String.valueOf(typeBean.getWorkTypeNotOverCount()));
                    bean.setOutDateNotOverCount3(String.valueOf(typeBean.getWorkTypeOutDateNotOverCount()));
                    break;
                case 4:
                    bean.setAllCount4(String.valueOf(typeBean.getWorkTypeAllCount()));
                    bean.setOverCount4(String.valueOf(typeBean.getWorkTypeOverCount()));
                    bean.setOutDateOverCount4(String.valueOf(typeBean.getWorkTypeOutDateOverCount()));
                    bean.setNotOverCount4(String.valueOf(typeBean.getWorkTypeNotOverCount()));
                    bean.setOutDateNotOverCount4(String.valueOf(typeBean.getWorkTypeOutDateNotOverCount()));
                    break;
                case 5:
                    bean.setAllCount5(String.valueOf(typeBean.getWorkTypeAllCount()));
                    bean.setOverCount5(String.valueOf(typeBean.getWorkTypeOverCount()));
                    bean.setOutDateOverCount5(String.valueOf(typeBean.getWorkTypeOutDateOverCount()));
                    bean.setNotOverCount5(String.valueOf(typeBean.getWorkTypeNotOverCount()));
                    bean.setOutDateNotOverCount5(String.valueOf(typeBean.getWorkTypeOutDateNotOverCount()));
                    break;

                default:
                    break;
            }
            i++;
        }
        return bean;
    }
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getOverCount() {
		return overCount;
	}
	public void setOverCount(int overCount) {
		this.overCount = overCount;
	}
	public int getOutDateOverCount() {
		return outDateOverCount;
	}
	public void setOutDateOverCount(int outDateOverCount) {
		this.outDateOverCount = outDateOverCount;
	}
	public int getNotOverCount() {
		return notOverCount;
	}
	public void setNotOverCount(int notOverCount) {
		this.notOverCount = notOverCount;
	}
	public int getOutDateNotOverCount() {
		return outDateNotOverCount;
	}
	public void setOutDateNotOverCount(int outDateNotOverCount) {
		this.outDateNotOverCount = outDateNotOverCount;
	}
	public List<ProvinceTaskWorkTypeCountBean> getPtwtcBeanList() {
		return ptwtcBeanList;
	}
	public void setPtwtcBeanList(List<ProvinceTaskWorkTypeCountBean> ptwtcBeanList) {
		this.ptwtcBeanList = ptwtcBeanList;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
    
}
