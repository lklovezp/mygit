/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configchecktemplate;

import org.springframework.web.multipart.MultipartFile;

/**
 * 设置监察模板的Form
 * 
 * @author wumi
 * @version $Id: VersionForm.java, v 0.1 2013-3-25 下午03:33:13 wumi Exp $
 */
public class ConfigForm {
	/** 设置监察模板id */
	private String id;
	/** 执法对象类型
	01	工业污染源
	02	建设项目
	03	医院
	04	锅炉
	05	建筑工地
	06	三产
	07	畜禽养殖 */
	private String lawobjtype;
	/**  */
	private String tasktypeid;
	
	private String isexecchecklist;
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
	public String getLawobjtype() {
		return lawobjtype;
	}
	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}
	public String getTasktypeid() {
		return tasktypeid;
	}
	public void setTasktypeid(String tasktypeid) {
		this.tasktypeid = tasktypeid;
	}
	public String getIsexecchecklist() {
		return isexecchecklist;
	}
	public void setIsexecchecklist(String isexecchecklist) {
		this.isexecchecklist = isexecchecklist;
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
