package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.app.work.po.BaseObj;
/**
 * �ʼ��ӱ��������ˣ�
 * likun
 * */
@SuppressWarnings("serial")
public class TDataRecmail extends BaseObj{
	private String id;     //  
	private String mailId; // �ʼ�id
	private String userId;  //������
	private String isRead;  //�Ƿ��Ķ�Y/N
	private Date readDate;  //��������
	private String isDel;  //�Ƿ�ɾ��Y/N
	private String areaId;
	private String pfrId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getPfrId() {
		return pfrId;
	}
	public void setPfrId(String pfrId) {
		this.pfrId = pfrId;
	}  
	 
	
}