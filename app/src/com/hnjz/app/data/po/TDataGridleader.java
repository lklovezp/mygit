package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_GRIDLEADER ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ��������������������
 */ 

@SuppressWarnings("serial")
public class TDataGridleader extends BaseObject {
	/** ִ�������ʶ */
	private String lawobjid;
	/**  */
	private String userid;
	/** ��������ʵ���� */
	private String username;

	public void setLawobjid(String lawobjid){
		this.lawobjid = lawobjid;
	}
	public String getLawobjid(){
		return lawobjid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getUserid(){
		return userid;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
}
