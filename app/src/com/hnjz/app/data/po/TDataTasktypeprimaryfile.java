package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPEPRIMARYFILE 实体类
 * 作者：张少卫
 * 生成日期：Thu Mar 05 15:46:45 CST 2015
 * 功能描述：任务类型主次要附件
 */ 

@SuppressWarnings("serial")
public class TDataTasktypeprimaryfile extends BaseObject {
	/** 任务类型 */
	private String tasktypeid;
	/** 附件类型编码 */
	private String attachmenttype;
	/** 是否主要附件Y/N，默认Y */
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

