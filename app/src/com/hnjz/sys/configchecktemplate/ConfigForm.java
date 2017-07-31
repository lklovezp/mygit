/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configchecktemplate;

import org.springframework.web.multipart.MultipartFile;

/**
 * ���ü��ģ���Form
 * 
 * @author wumi
 * @version $Id: VersionForm.java, v 0.1 2013-3-25 ����03:33:13 wumi Exp $
 */
public class ConfigForm {
	/** ���ü��ģ��id */
	private String id;
	/** ִ����������
	01	��ҵ��ȾԴ
	02	������Ŀ
	03	ҽԺ
	04	��¯
	05	��������
	06	����
	07	������ֳ */
	private String lawobjtype;
	/**  */
	private String tasktypeid;
	
	private String isexecchecklist;
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