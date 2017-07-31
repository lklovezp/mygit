package com.hnjz.app.work.beans;

import java.io.Serializable;


public class ProvinceTaskWorkTypeCountBean implements Serializable {
    /**  */
    private static final long  serialVersionUID = 1L;
    private String areaid;
    private String areaName;
    private String workTypeId;
    private String workTypeName;
    private int workTypeAllCount;
    private int workTypeOverCount;
    private int workTypeOutDateOverCount;
    private int workTypeNotOverCount;
    private int workTypeOutDateNotOverCount;
    
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getWorkTypeAllCount() {
		return workTypeAllCount;
	}
	public void setWorkTypeAllCount(int workTypeAllCount) {
		this.workTypeAllCount = workTypeAllCount;
	}
	public int getWorkTypeOverCount() {
		return workTypeOverCount;
	}
	public void setWorkTypeOverCount(int workTypeOverCount) {
		this.workTypeOverCount = workTypeOverCount;
	}
	public int getWorkTypeOutDateOverCount() {
		return workTypeOutDateOverCount;
	}
	public void setWorkTypeOutDateOverCount(int workTypeOutDateOverCount) {
		this.workTypeOutDateOverCount = workTypeOutDateOverCount;
	}
	public int getWorkTypeNotOverCount() {
		return workTypeNotOverCount;
	}
	public void setWorkTypeNotOverCount(int workTypeNotOverCount) {
		this.workTypeNotOverCount = workTypeNotOverCount;
	}
	public int getWorkTypeOutDateNotOverCount() {
		return workTypeOutDateNotOverCount;
	}
	public void setWorkTypeOutDateNotOverCount(int workTypeOutDateNotOverCount) {
		this.workTypeOutDateNotOverCount = workTypeOutDateNotOverCount;
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
	
}
