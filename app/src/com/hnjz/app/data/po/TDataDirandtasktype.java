package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DIRANDTASKTYPE 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:04 CST 2015
 * 功能描述：执法文件目录和任务类型关联（任务办理准备阶段选择时使用）
 */ 

@SuppressWarnings("serial")
public class TDataDirandtasktype extends BaseObject {
	/**  */
	private String dirid;
	/**  */
	private String tasktypeid;

	public void setDirid(String dirid){
		this.dirid = dirid;
	}
	public String getDirid(){
		return dirid;
	}
	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
}

