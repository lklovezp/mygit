package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * �����Υ������ T_BIZ_TASKILLEGALTYPE
 * ���ߣ�xb
 * �������ڣ�Apr 9, 2015
 * ����������

 *
 */

@SuppressWarnings("serial")
public class TBizTaskillegaltype extends BaseObject {
	/** �����ʶ */
	private String taskid;
	/** Υ�����ͱ�ʶ */
	private String illegaltypeid;
	
	public TBizTaskillegaltype(){
		
	}
	
	public TBizTaskillegaltype(String taskid,String illegaltypeid){
		this.taskid = taskid;
		this.illegaltypeid = illegaltypeid;
	}

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public String getIllegaltypeid() {
		return illegaltypeid;
	}
	public void setIllegaltypeid(String illegaltypeid) {
		this.illegaltypeid = illegaltypeid;
	}
}
