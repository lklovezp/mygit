package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPEATTACHMENTTYPE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * �����������������Ͷ�Ӧ�ĸ�������
 */ 

@SuppressWarnings("serial")
public class TDataTasktypeattachmenttype extends BaseObject {
	/** �������� */
	private String tasktypeid;
	/** �������ͱ�� */
	private String attachmenttype;

	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
	public void setAttachmenttype(String attachmenttype){
		this.attachmenttype = attachmenttype;
	}
	public String getAttachmenttype(){
		return attachmenttype;
	}
}
