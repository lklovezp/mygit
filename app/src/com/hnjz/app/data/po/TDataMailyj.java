package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.app.work.po.BaseObj;

/**
 * �ʼ����������
 * likun
 * */
@SuppressWarnings("serial")
public class TDataMailyj extends BaseObj{
	private String id;
	private String mailId;  //�ʼ�ID
	private String userId;  //���������
	private String setUserId; //���������
	private String isRead;  //�Ƿ��Ķ�Y�ǣ�N��
	private Date readDate;  //����ʱ��
	private String yiJianContent; //�������
	private String fuJianId;  //����id
	private String areaId;
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
	public String getSetUserId() {
		return setUserId;
	}
	public void setSetUserId(String setUserId) {
		this.setUserId = setUserId;
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
	public String getYiJianContent() {
		return yiJianContent;
	}
	public void setYiJianContent(String yiJianContent) {
		this.yiJianContent = yiJianContent;
	}
	public String getFuJianId() {
		return fuJianId;
	}
	public void setFuJianId(String fuJianId) {
		this.fuJianId = fuJianId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	} 
	
}