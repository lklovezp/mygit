package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * TBizAutomoniter 实体类
 * 作者：zhangqingfeng
 * 生成日期：Dec 23 15:13:03 CST 2015
 * 功能描述：在线制作问题项
 */ 

@SuppressWarnings("serial")
public class TBizAutomoniter extends BaseObject {
	private String id;//对应id
	private String taskId;//任务ID
	private String templateCode;//废气	101	污染源自动监控设施例行检查表 类似的检查模板类型Code
	private String queseCode;//问题编号（第一个编号10000001，依次+1，参照JAVA枚举类，可将模板中的问题整合到JAVA枚举文件中
	private String ansType;//"1 填空 2 单选 3 多选"
	private String ans;//答案。填空题存储文本，选择题存储答案对应的编码，多个答案时以分号隔开。
	private String remark;//备注
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
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getQueseCode() {
		return queseCode;
	}
	public void setQueseCode(String queseCode) {
		this.queseCode = queseCode;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

