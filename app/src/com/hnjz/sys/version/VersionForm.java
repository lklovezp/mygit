/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import org.springframework.web.multipart.MultipartFile;

/**
 * 版本的Form
 * 
 * @author wumi
 * @version $Id: VersionForm.java, v 0.1 2013-3-25 下午03:33:13 wumi Exp $
 */
public class VersionForm {
	/** 版本id */
	private String id;
	/** 版本号，如20150101 */
	private String code;
	/** 版本名称，如V2.0.0 */
	private String name;
	/** 是否强制安装或重新 Y/N */
	private String isforce;
	/**
	 * 安装包类型 0 终端 1 单机版 2 单机数据包
	 */
	private String type;
	/** 排序 */
	private Integer orderby;
	/** 描述 */
	private String describe;
	/** 状态 */
	private String isActive;
	/** 文件路径 */
	private String filePath;
	/** 文件 */
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
