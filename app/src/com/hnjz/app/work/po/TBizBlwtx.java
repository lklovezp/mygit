package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKINVERECORD ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * ��������������������
 */ 

@SuppressWarnings("serial")
public class TBizBlwtx extends BaseObject {
	/** ����ID */
	private String rwid;
	/** ������ID */
	private String wtxid;
	/** ���������� */
	private String content;
	
	public String getRwid() {
		return rwid;
	}
	public void setRwid(String rwid) {
		this.rwid = rwid;
	}
	public String getWtxid() {
		return wtxid;
	}
	public void setWtxid(String wtxid) {
		this.wtxid = wtxid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
