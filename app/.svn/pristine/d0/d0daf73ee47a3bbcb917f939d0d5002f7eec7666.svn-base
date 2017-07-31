/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.service;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;

/**
 * 
 * @author wangliang
 * @version $Id: MaturityTaskDetectionManager.java, v 0.1 Aug 21, 2013 3:29:36
 *          PM wangliang Exp $
 */
@Service("deleteTaskManager")
public class DeleteTaskManager extends ManagerImpl {

	/** 日志 */
	private static final Log log = LogFactory.getLog(DeleteTaskManager.class);

	/**
	 * 获取到期任务列表
	 * 
	 * @return
	 */
	public void delete() {
		String path = log.getClass().getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("WEB-INF")) + "static/temp/preview/";

		try {
			removeFiles(path);
			log.debug("===================== tomcat下预览临时文件已删除 =======================");
			removeFiles(FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath());
			log.debug("===================== 文件上传目录下临时文件已删除 =======================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归删除文件夹和文件
	 * @param path
	 * @throws Exception
	 */
	public static void removeFiles(String path) throws Exception {
		File fo = new File(path);

		for (File f : fo.listFiles()) {
			if (f.isDirectory()) {
				if (!f.delete()) {
					removeFiles(f.getPath());
				}
			} else {
				f.deleteOnExit();
			}
			f.delete();
		}
	}
}
