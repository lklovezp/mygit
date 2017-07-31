package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_CHECKLIST ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:04 CST 2015
 * ������������鵥���
 */ 

@SuppressWarnings("serial")
public class TBizChecklist extends BaseObject {
	/** ����ID */
	private String taskid;
	/** ��鵥ģ��ID�������ڵ�ID�� */
	private String templateid;
	/** �����ID */
	private String itemid;
	/** ��������� */
	private Integer itemorderby;
	/** �û���д�Ĵ𰸣��������⣩��ע */
	private String answerid;
	/** ׷���������� */
	private String itemcontent;
	
	/** �Ƿ����ؼ����*/
	private String ishidden;
	/** �Ƿ��������ļ����*/
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
