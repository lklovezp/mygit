package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TIMERTASKTASKTYPE ʵ���� 
 * ���ߣ������� 
 * �������ڣ�Mon Mar 16 13:35:31 CST 2015
 * ������������ʱ����������������
 */ 

@SuppressWarnings("serial")
public class TDataTimertasktasktype extends BaseObject {
	/**  */
	private TDataTimertask timertask;
	/** �������ͱ�ʶ */
	private TDataTasktype tasktype;

	public void setTimertask(TDataTimertask timertask){
		this.timertask = timertask;
	}
	public TDataTimertask getTimertask(){
		return timertask;
	}
	public void setTasktype(TDataTasktype tasktype){
		this.tasktype = tasktype;
	}
	public TDataTasktype getTasktype(){
		return tasktype;
	}
}
