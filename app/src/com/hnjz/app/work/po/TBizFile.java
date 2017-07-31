package com.hnjz.app.work.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_BIZ_FILE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:13:04 CST 2015
 * ����������ҵ�񸽼�
 */ 

@SuppressWarnings("serial")
public class TBizFile extends BaseObject {
	/** ��������ʾ�����ƣ�32λ�ַ��� */
	private String osname;
	/** ҵ���ʶ */
	private String pid;
	/** ��ʵ�ļ����ƣ�����չ���� */
	private String name;
	/** ��չ����������.",��.docx�� */
	private String extension;
	/** ��С���ֽڣ� */
	private Long size;
	/** �������� */
	private String type;
	/** ��չ��Ϣ�洢json���� */
	private String extinfo;

	public void setOsname(String osname){
		this.osname = osname;
	}
	public String getOsname(){
		return osname;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setExtension(String extension){
		this.extension = extension;
	}
	public String getExtension(){
		return extension;
	}
	public void setSize(Long size){
		this.size = size;
	}
	public Long getSize(){
		return size;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setExtinfo(String extinfo){
		this.extinfo = extinfo;
	}
	public String getExtinfo(){
		return extinfo;
	}
}
