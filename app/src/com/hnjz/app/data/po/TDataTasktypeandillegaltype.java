package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPEANDILLEGALTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ������������������-Υ������
 */ 

@SuppressWarnings("serial")
public class TDataTasktypeandillegaltype extends BaseObject {
	/**  */
	private String tasktypeid;
	/**  */
	private String illegaltypeid;

	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
	public void setIllegaltypeid(String illegaltypeid){
		this.illegaltypeid = illegaltypeid;
	}
	public String getIllegaltypeid(){
		return illegaltypeid;
	}
}
