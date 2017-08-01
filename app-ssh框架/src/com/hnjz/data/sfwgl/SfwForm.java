/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.data.sfwgl;

/**
 * 收发文表单
 * @author 时秋寒
 *
 */
public class SfwForm {

	private String id;
	
	/** 文件类型 0 发文 1 收文 */
	private String type; 
	/** 文件标题 */
	private String title;
	/** 文号 */
	private String number;
	/** 签名 */
	private String autograph;
	/** 日期 */
	private String sfwdate;
	/** 收发文类型父id */
	private String sourcepid;
	private String sourcepname;
	/** 收发文类型id */
	private String sourceid;
	private String sourcename;
	/** 存放位置 */
	private String position;
	private String year;
	private String code;

	private String isActive; // 是否有效
	
	private String filecreateby;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getSfwdate() {
		return sfwdate;
	}

	public void setSfwdate(String sfwdate) {
		this.sfwdate = sfwdate;
	}

	public String getSourcepid() {
		return sourcepid;
	}

	public void setSourcepid(String sourcepid) {
		this.sourcepid = sourcepid;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getSourcepname() {
		return sourcepname;
	}

	public void setSourcepname(String sourcepname) {
		this.sourcepname = sourcepname;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFilecreateby() {
		return filecreateby;
	}

	public void setFilecreateby(String filecreateby) {
		this.filecreateby = filecreateby;
	}

}
