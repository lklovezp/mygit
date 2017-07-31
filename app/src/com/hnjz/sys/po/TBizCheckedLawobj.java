package com.hnjz.sys.po;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.work.po.Work;

/**
 * 
 * 作者：时秋寒
 * 生成日期：2015-12-16
 * 功能描述：抽查比例实体类
 *
 */
public class TBizCheckedLawobj extends BaseObject {

	private static final long serialVersionUID = 1517474713052732108L;

	private String year;	//年份

	private String quarter;		//季度
	
	private TDataLawobj lawobj;	//抽查对象
	private TDataLawobjf lawobjf;	//抽查对象new
	public TDataLawobjf getLawobjf() {
		return lawobjf;
	}

	public void setLawobjf(TDataLawobjf lawobjf) {
		this.lawobjf = lawobjf;
	}

	private String lawobjName;
	private String lawobjType;
	
	private Work task;//关联任务
	
	private String type;//类型：1重点企业 ，1一般企业
    
	private String areaId;
	private String sfypf;
	
	private String month;
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public TDataLawobj getLawobj() {
		return lawobj;
	}

	public void setLawobj(TDataLawobj lawobj) {
		this.lawobj = lawobj;
	}

	public Work getTask() {
		return task;
	}

	public void setTask(Work task) {
		this.task = task;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getSfypf() {
		return sfypf;
	}

	public void setSfypf(String sfypf) {
		this.sfypf = sfypf;
	}

	public String getLawobjName() {
		return lawobjName;
	}

	public void setLawobjName(String lawobjName) {
		this.lawobjName = lawobjName;
	}

	public String getLawobjType() {
		return lawobjType;
	}

	public void setLawobjType(String lawobjType) {
		this.lawobjType = lawobjType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	

}
