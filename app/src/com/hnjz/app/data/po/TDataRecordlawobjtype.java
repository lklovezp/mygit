package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_RECORDLAWOBJTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ��������������ѯ�ʱ�¼����ִ����������
 */ 

@SuppressWarnings("serial")
public class TDataRecordlawobjtype extends BaseObject {
	/** ��¼ID */
	private String recordid;
	/** ִ���������� */
	private String lawobjtype;
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getLawobjtype() {
		return lawobjtype;
	}
	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}
	
}
