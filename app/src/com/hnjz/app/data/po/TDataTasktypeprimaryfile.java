package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPEPRIMARYFILE ʵ����
 * ���ߣ�������
 * �������ڣ�Thu Mar 05 15:46:45 CST 2015
 * ����������������������Ҫ����
 */ 

@SuppressWarnings("serial")
public class TDataTasktypeprimaryfile extends BaseObject {
	/** �������� */
	private String tasktypeid;
	/** �������ͱ��� */
	private String attachmenttype;
	/** �Ƿ���Ҫ����Y/N��Ĭ��Y */
	private String isparimay;

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
	public void setIsparimay(String isparimay){
		this.isparimay = isparimay;
	}
	public String getIsparimay(){
		return isparimay;
	}
}
