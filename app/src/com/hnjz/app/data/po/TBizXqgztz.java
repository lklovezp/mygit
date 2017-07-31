package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.app.work.po.BaseObj;

/*
 * 环境违法行为限期改正通知
 * */
@SuppressWarnings("serial")
public class TBizXqgztz extends BaseObj {
	 private String id; 
	 private String taskId; 
	 private String taskTypeId; 
	 private String title;
	 private String shortName; 
	 private String code; 
	 private String corpName; 
	 private Date   dcDate; 
	 private String content; 
	 private String rules; 
	 private String xqqContent;
	 private Date qxDate; 
	 private String ref;
	 private String areaId;
	 
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getXqqContent() {
		return xqqContent;
	}
	public void setXqqContent(String xqqContent) {
		this.xqqContent = xqqContent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskTypeId() {
		return taskTypeId;
	}
	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public Date getDcDate() {
		return dcDate;
	}
	public void setDcDate(Date dcDate) {
		this.dcDate = dcDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public Date getQxDate() {
		return qxDate;
	}
	public void setQxDate(Date qxDate) {
		this.qxDate = qxDate;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	} 
	 
	 
}
