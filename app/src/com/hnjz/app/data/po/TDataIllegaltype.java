package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_ILLEGALTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ����������Υ������
 */ 

@SuppressWarnings("serial")
public class TDataIllegaltype extends BaseObject {
	/**  */
	private String name;
	/**  */
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
