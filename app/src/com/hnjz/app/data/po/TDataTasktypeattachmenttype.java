package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_TASKTYPEATTACHMENTTYPE 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:15:03 CST 2015
 * 功能描述：任务类型对应的附件类型
 */ 

@SuppressWarnings("serial")
public class TDataTasktypeattachmenttype extends BaseObject {
	/** 任务类型 */
	private String tasktypeid;
	/** 附件类型编号 */
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

