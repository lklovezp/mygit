/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.timertask;

/**
 * �Զ��ɷ���Form
 * 
 * @author wumi
 * @version $Id: TimerTaskForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class TimerTaskForm {
	/** ����id */
	private String id;
	/** �������� */
	private String name;
	/** ��Ҫ���� */
	private String content;
	/** ������Դ */
	private String source;
	/** �����ܼ� */
	private String security;
	/** �����̶� 0һ�� 1���� 2����*/
	private String emergency;
	/** �Ǽ��� */
	private String register;
	private String registerName;
	/** �ɷ��� */
	private String hander;
	private String handerName;
	/** ������ */
	private String accepter;
	private String accepterName;
	/** ��ʾ��� */
	private String opinion;
	/**
	 * �Զ��ɷ�����: 1��ʱ��1�Σ� 2���� 3���꣨1�Σ�
	 */
	private String type;
	/** �ɷ�Ƶ�Σ�����ʱ��д�� */
	private Integer times;
	/** �����ɷ�ʱ�䣨��ʱ������ʱ��д�� */
	private String taskstarted;
	/** Ҫ�����ʱ�ޣ���ʱ������ʱ���ݽ����̶ȼ������ */
	private String taskended;
	/**
	 * ִ���������� 01 ��ҵ��ȾԴ 02 ������Ŀ 03 ҽԺ 04 ��¯ 05 �������� 06 ���� 07 ������ֳ
	 */
	private String lawobjtype;
	/** �������� */
	private String tasktype;
	/** ִ������ */
	private String lawobj;
	private String lawobjName;
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getEmergency() {
		return emergency;
	}
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getHander() {
		return hander;
	}
	public void setHander(String hander) {
		this.hander = hander;
	}
	public String getHanderName() {
		return handerName;
	}
	public void setHanderName(String handerName) {
		this.handerName = handerName;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public String getAccepterName() {
		return accepterName;
	}
	public void setAccepterName(String accepterName) {
		this.accepterName = accepterName;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getTaskstarted() {
		return taskstarted;
	}
	public void setTaskstarted(String taskstarted) {
		this.taskstarted = taskstarted;
	}
	public String getTaskended() {
		return taskended;
	}
	public void setTaskended(String taskended) {
		this.taskended = taskended;
	}
	public String getLawobjtype() {
		return lawobjtype;
	}
	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public String getLawobj() {
		return lawobj;
	}
	public void setLawobj(String lawobj) {
		this.lawobj = lawobj;
	}
	public String getLawobjName() {
		return lawobjName;
	}
	public void setLawobjName(String lawobjName) {
		this.lawobjName = lawobjName;
	}
}