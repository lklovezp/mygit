package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DISCRECASECLASS ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * �������������η���
 */ 

public class TDataDiscrecaseclass extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 115329370758801838L;
	/** �������ݱ�ʶ */
	private TDataDiscremerit tDataDiscremerit;
	/** ���� */
	private String name;
	/** ���� */
	private String content;
	
	public TDataDiscremerit gettDataDiscremerit() {
		return tDataDiscremerit;
	}
	public void settDataDiscremerit(TDataDiscremerit tDataDiscremerit) {
		this.tDataDiscremerit = tDataDiscremerit;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
}
