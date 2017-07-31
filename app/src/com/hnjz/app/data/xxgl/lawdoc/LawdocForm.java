package com.hnjz.app.data.xxgl.lawdoc;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_LAWDOC ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ����������ִ���ļ�
 */ 

@SuppressWarnings("serial")
public class LawdocForm extends BaseObject {
	private String id;
	/**����*/
	private Integer orderby;
	/**�Ƿ���Ч*/
	private String isActive;
	/** ִ���������� */
	private String name;
	/** ִ������Ŀ¼��ʶ */
	private String dirid;
	/** �ؼ��� */
	private String keywords;
	/** ������ʶ */
	private String fileid;
	/** ������ʶ */
	private String filename;
	
		public LawdocForm(){
		
	}
	
	public LawdocForm(String id,String name,String dirid,String keywords,String fileid,String isActive,Integer orderby){
		this.id = id;
		this.name = name;
		this.dirid = dirid;
		this.keywords = keywords;
		this.fileid = fileid;
		this.isActive = isActive;
		this.orderby = orderby;
	}
	
	public LawdocForm(String id,String name,String dirid,String keywords,String fileid,String filename,String isActive,Integer orderby){
		this.id = id;
		this.name = name;
		this.dirid = dirid;
		this.keywords = keywords;
		this.fileid = fileid;
		this.filename = filename;
		this.isActive = isActive;
		this.orderby = orderby;
	}
	

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setDirid(String dirid){
		this.dirid = dirid;
	}
	public String getDirid(){
		return dirid;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setFileid(String fileid){
		this.fileid = fileid;
	}
	public String getFileid(){
		return fileid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
