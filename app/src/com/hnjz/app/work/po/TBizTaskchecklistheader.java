package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_TASKCHECKLISTHEADER ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:03 CST 2015
 * ������������鵥ͷ��Ϣ
 */ 

@SuppressWarnings("serial")
public class TBizTaskchecklistheader extends BaseObject {
	/** �����ʶ */
	private String taskid;
	/** JSON�����ַ��� */
	private String jsonobjstr;

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setJsonobjstr(String jsonobjstr){
		this.jsonobjstr = jsonobjstr;
	}
	public String getJsonobjstr(){
		return jsonobjstr;
	}
}
