package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_CHECKLISTITEMANS ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ������������鵥������Ӧ�������ô�
 */ 

@SuppressWarnings("serial")
public class TDataChecklistitemans extends BaseObject {
	/** �����ID */
	private String itemid;
	/** �� */
	private String answer;
	/** �����쳣Y/N */
	private String isnormal;
	/** �𰸵Ķ�Ӧ���� */
	private String answerdesc;

	public void setItemid(String itemid){
		this.itemid = itemid;
	}
	public String getItemid(){
		return itemid;
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public String getAnswer(){
		return answer;
	}
	public void setIsnormal(String isnormal){
		this.isnormal = isnormal;
	}
	public String getIsnormal(){
		return isnormal;
	}
	public void setAnswerdesc(String answerdesc){
		this.answerdesc = answerdesc;
	}
	public String getAnswerdesc(){
		return answerdesc;
	}
}
