package com.hnjz.app.data.po;

import java.util.Date;

import com.hnjz.app.work.po.BaseObj;
/**
 * 邮件基本信息
 * likun
 * */
@SuppressWarnings("serial")
public class TDataMail extends BaseObj{
	private String id; //
	private String type;  //邮件类型：1：短消息；2：邮件；
	private String userId;  //发送人
	private String topic;  //主题
	private String content;  //主题内容
	private Date sendDate; //发送时间
	private String state;  //0.未发送1.已发送2.已删除
	private String pmailId; //回复源（是回复邮件的话，记录回复的是谁）
	private String chaoSong;  //抄送
	private String fileId; //附件
	private String opinion;  //意见
	private String recList; //接收人列表
	private String fxDxjg;  //短信发送结果
	private String fuJian;  //是否有附件Y是，N否
	private String areaId;  //区域
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
	
	
}
