package com.hnjz.app.data.xxgl.lawdocdir;


/**
 * T_DATA_LAWDOCDIR ʵ����
 * ���ߣ�renzhengquan
 * ����������ִ���ļ�Ŀ¼form
 */ 

public class LawdocdirForm{
	
	private String id;
	/** ���� */
	private String name;
	/** ��Id */
	private String pid;

	/**��ע*/
	private String describe;

	/**����*/
	private Integer orderby;

	/**�Ƿ���Ч*/
	private String isActive;

	private String tasktypeid;
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getTasktypeid() {
		return tasktypeid;
	}
	public void setTasktypeid(String tasktypeid) {
		this.tasktypeid = tasktypeid;
	}
}
