package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysDic;

/**
 * T_DATA_DISCREACTS ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * �������������ɲ���Υ����Ϊ
 */ 

public class TDataDiscreacts extends BaseObject {
	private static final long serialVersionUID = 1412588498686591491L;
	/** ���� */
	private String content;
	/** �ƶȷ��� */
	private TSysDic type;
	/** Υ�����ͱ�ʶ */
	private TDataIllegaltype illegaltype;

	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public TSysDic getType() {
		return type;
	}
	public void setType(TSysDic type) {
		this.type = type;
	}
	public TDataIllegaltype getIllegaltype() {
		return illegaltype;
	}
	public void setIllegaltype(TDataIllegaltype illegaltype) {
		this.illegaltype = illegaltype;
	}
}
