package com.hnjz.app.data.rcbg;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class RecMailForm {
	private String id;     //  
	private String mailId; // �ʼ�id
	private String userId;  //������
	private String isRead;  //�Ƿ��Ķ�Y/N
	private Date readDate;  //��������
	private String isDel;  //�Ƿ�ɾ��Y/N
	private String areaId;
	 /**�汾��*/
    private Integer           version;
    /**�Ƿ���Ч*/
    private String            isActive;
    /**����������*/
    private TSysUser              creater;
    /**�޸�������*/
    private TSysUser              updateby;
    /**����ʱ��*/
    private Date              updateTime;
    /**�޸�ʱ��*/
    private Date              createTime;
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public TSysUser getCreater() {
		return creater;
	}
	public void setCreater(TSysUser creater) {
		this.creater = creater;
	}
	public TSysUser getUpdateby() {
		return updateby;
	}
	public void setUpdateby(TSysUser updateby) {
		this.updateby = updateby;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}