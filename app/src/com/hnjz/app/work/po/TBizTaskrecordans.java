package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKRECORDANS ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * �������������񿱲�ѯ�ʱ�¼��
 */ 

@SuppressWarnings("serial")
public class TBizTaskrecordans extends BaseObject {
	/** ����ID */
	private String taskid;
	/** ������ID */
	private String recordid;
	/** ���������� */
	private String content;
	/** ������ */
	private String danr;
	/** ����ѯ�����ֱ�� 0���� 1ѯ�� */
	private String kcxwbj;
	/** �������ͣ�0 ϵͳ 1���䣩 */
	private String wttype;
	/** �Ƿ��ɾ�� */ 
	private String isdel;

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setRecordid(String recordid){
		this.recordid = recordid;
	}
	public String getRecordid(){
		return recordid;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setDanr(String danr){
		this.danr = danr;
	}
	public String getDanr(){
		return danr;
	}
	public void setKcxwbj(String kcxwbj){
		this.kcxwbj = kcxwbj;
	}
	public String getKcxwbj(){
		return kcxwbj;
	}
	public void setWttype(String wttype){
		this.wttype = wttype;
	}
	public String getWttype(){
		return wttype;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getIsdel() {
		return isdel;
	}
}
