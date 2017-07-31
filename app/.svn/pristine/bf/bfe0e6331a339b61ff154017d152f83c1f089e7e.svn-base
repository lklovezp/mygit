package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_CHECKLIST 实体类
 * 作者：张少卫
 * 生成日期：Fri Feb 27 15:13:04 CST 2015
 * 功能描述：检查单结果
 */ 

@SuppressWarnings("serial")
public class TBizChecklist extends BaseObject {
	/** 任务ID */
	private String taskid;
	/** 检查单模板ID（顶级节点ID） */
	private String templateid;
	/** 检查项ID */
	private String itemid;
	/** 检查项的序号 */
	private Integer itemorderby;
	/** 用户填写的答案（针对填空题）或备注 */
	private String answerid;
	/** 追加问题内容 */
	private String itemcontent;
	
	/** 是否隐藏检查项*/
	private String ishidden;
	/** 是否是新增的检查项*/
	private String isadd;
	public TBizChecklist(){
		
	}
	
	public TBizChecklist(String taskid,String templateid,String itemid,    Integer itemorderby,String answerid,String describe){
		this.taskid = taskid;
		this.templateid = templateid;
		this.itemid = itemid;
		
		this.itemorderby = itemorderby;
		this.answerid = answerid;
		this.setDescribe(describe);
	}

	public void setTaskid(String taskid){
		this.taskid = taskid;
	}
	public String getTaskid(){
		return taskid;
	}
	public void setTemplateid(String templateid){
		this.templateid = templateid;
	}
	public String getTemplateid(){
		return templateid;
	}
	public void setItemid(String itemid){
		this.itemid = itemid;
	}
	public String getItemid(){
		return itemid;
	}
	
	public String getItemcontent() {
		return itemcontent;
	}

	public void setItemcontent(String itemcontent) {
		this.itemcontent = itemcontent;
	}

	public void setItemorderby(Integer itemorderby){
		this.itemorderby = itemorderby;
	}
	public Integer getItemorderby(){
		return itemorderby;
	}
	public void setAnswerid(String answerid){
		this.answerid = answerid;
	}
	public String getAnswerid(){
		return answerid;
	}
	public String getIshidden() {
		return ishidden;
	}
	public void setIshidden(String ishidden) {
		this.ishidden = ishidden;
	}
	public String getIsadd() {
		return isadd;
	}
	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}
}

