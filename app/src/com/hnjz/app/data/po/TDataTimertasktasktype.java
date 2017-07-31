package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TIMERTASKTASKTYPE 实体类 
 * 作者：张少卫 
 * 生成日期：Mon Mar 16 13:35:31 CST 2015
 * 功能描述：定时任务设置任务类型
 */ 

@SuppressWarnings("serial")
public class TDataTimertasktasktype extends BaseObject {
	/**  */
	private TDataTimertask timertask;
	/** 任务类型标识 */
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

