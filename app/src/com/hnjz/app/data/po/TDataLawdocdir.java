package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWDOCDIR ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ����������ִ���ļ�Ŀ¼
 */ 

@SuppressWarnings("serial")
public class TDataLawdocdir extends BaseObject {
	/** ���� */
	private String name;
	/** ��Id */
	private String pid;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
}
