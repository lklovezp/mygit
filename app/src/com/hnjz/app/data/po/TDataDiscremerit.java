package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DISCREMERIT ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * �������������ɲ�����������
 */ 

public class TDataDiscremerit extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7119054100115482123L;
	/** Υ����ΪID */
	private TDataDiscreacts tDataDiscreacts;
	/** ���� */
	private String alias;
	/** ���� */
	private String content;

	public TDataDiscreacts gettDataDiscreacts() {
		return tDataDiscreacts;
	}
	public void settDataDiscreacts(TDataDiscreacts tDataDiscreacts) {
		this.tDataDiscreacts = tDataDiscreacts;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	public String getAlias(){
		return alias;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
}
