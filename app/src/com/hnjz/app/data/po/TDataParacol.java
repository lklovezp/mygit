package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_PARACOL ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ��������������ִ��ģ�������Ӧ�����ݱ���

1����������������ѡ��ִ������Ľ���ʱ�����ظ��Ե��ֶ��
 */ 

@SuppressWarnings("serial")
public class TDataParacol extends BaseObject {
	/** ������ʶ */
	private String paraid;
	/** ִ���������� */
	private String lawobjtypeid;
	/** ִ��������е��ֶα��� */
	private String colcode;

	public void setParaid(String paraid){
		this.paraid = paraid;
	}
	public String getParaid(){
		return paraid;
	}
	public void setLawobjtypeid(String lawobjtypeid){
		this.lawobjtypeid = lawobjtypeid;
	}
	public String getLawobjtypeid(){
		return lawobjtypeid;
	}
	public void setColcode(String colcode){
		this.colcode = colcode;
	}
	public String getColcode(){
		return colcode;
	}
}
