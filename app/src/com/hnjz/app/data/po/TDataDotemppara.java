package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DOTEMPPARA ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * ������������������ִ��ģ�����
 */ 

@SuppressWarnings("serial")
public class TDataDotemppara extends BaseObject {
	/**  */
	private String tempid;
	/** ������ʶ */
	private String paracode;
	/** �������� */
	private String paraname;

	public void setTempid(String tempid){
		this.tempid = tempid;
	}
	public String getTempid(){
		return tempid;
	}
	public void setParacode(String paracode){
		this.paracode = paracode;
	}
	public String getParacode(){
		return paracode;
	}
	public void setParaname(String paraname){
		this.paraname = paraname;
	}
	public String getParaname(){
		return paraname;
	}
}
