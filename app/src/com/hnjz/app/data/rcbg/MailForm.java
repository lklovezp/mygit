package com.hnjz.app.data.rcbg;

import java.util.Date;

import com.hnjz.sys.po.TSysUser;

public class MailForm {
	private String id; //
	private String type;  //�ʼ����ͣ�1������Ϣ��2���ʼ���
	private String userId;  //������
	private String topic;  //����
	private String content;  //��������
	private Date sendDate; //����ʱ��
	private String state;  //0.δ����1.�ѷ���2.��ɾ��
	private String pmailId; //�ظ�Դ���ǻظ��ʼ��Ļ�����¼�ظ�����˭��
	private String chaoSong;  //����
	private String fileId; //����
	private String opinion;  //���
	private String recList; //�������б�
	private String fxDxjg;  //���ŷ��ͽ��
	private String fuJian;  //�Ƿ��и���Y�ǣ�N��
	private String areaId;  //����
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPmailId() {
		return pmailId;
	}
	public void setPmailId(String pmailId) {
		this.pmailId = pmailId;
	}
	public String getChaoSong() {
		return chaoSong;
	}
	public void setChaoSong(String chaoSong) {
		this.chaoSong = chaoSong;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getRecList() {
		return recList;
	}
	public void setRecList(String recList) {
		this.recList = recList;
	}
	public String getFxDxjg() {
		return fxDxjg;
	}
	public void setFxDxjg(String fxDxjg) {
		this.fxDxjg = fxDxjg;
	}
	public String getFuJian() {
		return fuJian;
	}
	public void setFuJian(String fuJian) {
		this.fuJian = fuJian;
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