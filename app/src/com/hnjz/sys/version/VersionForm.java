/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import org.springframework.web.multipart.MultipartFile;

/**
 * �汾��Form
 * 
 * @author wumi
 * @version $Id: VersionForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class VersionForm {
	/** �汾id */
	private String id;
	/** �汾�ţ���20150101 */
	private String code;
	/** �汾���ƣ���V2.0.0 */
	private String name;
	/** �Ƿ�ǿ�ư�װ������ Y/N */
	private String isforce;
	/**
	 * ��װ������ 0 �ն� 1 ������ 2 �������ݰ�
	 */
	private String type;
	/** ���� */
	private Integer orderby;
	/** ���� */
	private String describe;
	/** ״̬ */
	private String isActive;
	/** �ļ�·�� */
	private String filePath;
	/** �ļ� */
	private MultipartFile file;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsforce() {
		return isforce;
	}
	public void setIsforce(String isforce) {
		this.isforce = isforce;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}