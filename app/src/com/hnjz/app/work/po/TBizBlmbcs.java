package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_BLMBCS ʵ����
 * ���ߣ������
 * �������ڣ�2016-03-13
 * ���������������������ɵĸ�������
 */ 

@SuppressWarnings("serial")
public class TBizBlmbcs extends BaseObject {
	/** ����ID */
	private String taskId;
	/** ģ��ID(����ID) */
	private String tmplateId;
	/** ���ɵļ���¼����ID */
	private String fileId;
	/** ���� */
	private int times;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTmplateId() {
		return tmplateId;
	}
	public void setTmplateId(String tmplateId) {
		this.tmplateId = tmplateId;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
}
