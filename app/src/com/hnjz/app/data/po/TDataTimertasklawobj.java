package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TIMERTASKLAWOBJ ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:02 CST 2015
 * ������������ʱ��������ִ������
 */ 

@SuppressWarnings("serial")
public class TDataTimertasklawobj extends BaseObject {
	/**  */
	private String pid;
	/**  */
	private String lawobjid;
	/**  */
	private String lawobjname;

	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setLawobjid(String lawobjid){
		this.lawobjid = lawobjid;
	}
	public String getLawobjid(){
		return lawobjid;
	}
	public void setLawobjname(String lawobjname){
		this.lawobjname = lawobjname;
	}
	public String getLawobjname(){
		return lawobjname;
	}
}
