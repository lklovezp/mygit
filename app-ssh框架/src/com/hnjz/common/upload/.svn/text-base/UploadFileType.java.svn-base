/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.upload;

import java.io.File;

/**
 * 
 * @author 李静芬
 * @version $Id: UploadFileType.java, v 0.1 Jan 24, 2013 8:29:58 AM
 *          Administrator Exp $
 */
public enum UploadFileType {

	XXGL("xxgl/"),
	/** 执法文件（法律法规） */
	ZFWJ("zfwj/"),
	/** 任务附件 */
	WORK("work/"),
	/** 稿件附件 */
	GJFJ("gjfj/"),
	/** 报表文件 */
	BBWJ("bbwj/"),
	/** 任务附件 */
	EXPORT("export/"),
	/** 检查单 */
	JCD("jcd" + File.separator),
	/** 客户端 */
	CLIENT("client" + File.separator),
	/** 临时文件 */
	TEMP("temp" + File.separator),
	//离线版打包的时候用
	/** 其他附件 */
	QITA("qita" + File.separator),
	/** 其他附件 */
	JCJLSMJ("jcjlsmj" + File.separator),
	/** 检查记录文件目录 */
	JCJL("jcjlfj" + File.separator),
	/** 勘察笔录附件 */
	KCBL("kcbl" + File.separator),
	/** 询问笔录附件 */
	XWBL("xwbl" + File.separator),
	/** 准备资料附件 */
	ZBZL("zbzl" + File.separator);

	private String path;

	public static String getPathByEnumName(String enumName){
		String path = "";
		for (UploadFileType ele : values()) {
			if (enumName.equals(ele.name())){
				path = ele.getPath();
			}
		}
		return path;
	}
	
	private UploadFileType(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
