package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_CHECKLISTTEMPLATE ʵ����
 * ���ߣ�������
 * �������ڣ�Tue Mar 17 18:49:34 CST 2015
 * ������������鵥ģ��
 */ 

@SuppressWarnings("serial")
public class TDataChecklisttemplate extends BaseObject {
	/** ��鵥ģ������ */
	private String name;
	/** ������ģ�� */
	private String pid;
	/** ��ҵ��ʶ��ֻ��ģ��ĸ��ڵ���Ҫ��������ҵ�ϣ� */
	private String industryid;
	/** �������ͣ�ֻ�и��ڵ���Ҫ�������������ͣ� */
	private String tasktypeid;
	/** �Ƿ��ѡY/N */
	private String isrequired;
	/** �汾 */
	private Integer release;
	/** �Ƿ�ǰ�汾��Y/N�� */
	private String iscurver;
	/** �ӽڵ���� */
	private Integer childnum;

	private String namevisiable;
	
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
	public void setIndustryid(String industryid){
		this.industryid = industryid;
	}
	public String getIndustryid(){
		return industryid;
	}
	public void setTasktypeid(String tasktypeid){
		this.tasktypeid = tasktypeid;
	}
	public String getTasktypeid(){
		return tasktypeid;
	}
	public void setIsrequired(String isrequired){
		this.isrequired = isrequired;
	}
	public String getIsrequired(){
		return isrequired;
	}
	public void setRelease(Integer release){
		this.release = release;
	}
	public Integer getRelease(){
		return release;
	}
	public void setIscurver(String iscurver){
		this.iscurver = iscurver;
	}
	public String getIscurver(){
		return iscurver;
	}
	public void setChildnum(Integer childnum){
		this.childnum = childnum;
	}
	public Integer getChildnum(){
		return childnum;
	}
	public void setNamevisiable(String namevisiable){
		this.namevisiable = namevisiable;
	}
	public String getNamevisiable(){
		return namevisiable;
	}
}
