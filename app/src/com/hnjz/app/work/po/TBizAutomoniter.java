package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * TBizAutomoniter ʵ����
 * ���ߣ�zhangqingfeng
 * �������ڣ�Dec 23 15:13:03 CST 2015
 * ������������������������
 */ 

@SuppressWarnings("serial")
public class TBizAutomoniter extends BaseObject {
	private String id;//��Ӧid
	private String taskId;//����ID
	private String templateCode;//����	101	��ȾԴ�Զ������ʩ���м��� ���Ƶļ��ģ������Code
	private String queseCode;//�����ţ���һ�����10000001������+1������JAVAö���࣬�ɽ�ģ���е��������ϵ�JAVAö���ļ���
	private String ansType;//"1 ��� 2 ��ѡ 3 ��ѡ"
	private String ans;//�𰸡������洢�ı���ѡ����洢�𰸶�Ӧ�ı��룬�����ʱ�ԷֺŸ�����
	private String remark;//��ע
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
