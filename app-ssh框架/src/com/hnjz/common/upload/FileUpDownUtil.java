/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.ImgUtil;

/**
 * 文件上传下载的工具类
 * 
 * @author 李静芬
 * @version $Id: FileUpDownUtil.java, v 0.1 Jan 24, 2013 2:36:37 PM
 *          Administrator Exp $
 */
public class FileUpDownUtil {
	/** 日志 */
	private static final Log log = LogFactory.getLog(FileUpDownUtil.class);

	public static String path;

	/**
	 * 将用户上传的文件写到本地
	 * 
	 * @param in
	 * @param fileName
	 * @param type
	 * @param ars
	 * @return
	 * @throws IOException
	 */
	public static String copyFile(InputStream in, String fileName,
			String type, String... ars) throws IOException {
		String p = path.concat(type);
		if (null != ars) {
			for (String ele : ars) {
				p = p.concat(ele).concat(File.separator);
			}
		}
		// 得到项目的工作目录
		File f1 = new File(p);
		// 如果没有的话就创建目录
		if (!f1.exists()) {
			f1.mkdirs();
		}
		p = p.concat(fileName);
		FileOutputStream fs = new FileOutputStream(p);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		while ((byteread = in.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		in.close();
		// 生成缩略图  如果文件不是图片方法自动返回，程序不会终止
		final String pp = p;
		new Thread(new Runnable() {
			public void run() {
				try {
					ImgUtil.thumbnailImage(new File(pp), 100, 150, ImgUtil.DEFAULT_BACKFIX, ImgUtil.DEFAULT_FORCE);
				} catch (Exception e) {
					//System.out.println("========================不是图片文件========================");
				}
			}
		}).start();
		
		return p;
	}

	public String copyToStatic(InputStream in, String fileName,
			String type) throws IOException {
		String path = this.getClass().getClassLoader().getResource("/").getPath().replaceAll("%20", " ");
		path = path.substring(0, path.lastIndexOf("WEB-INF/classes/")) + "static//temp//" + type + File.separator;
		try {
			// 得到项目的工作目录
			File f1 = new File(path);
			// 如果没有的话就创建目录
			if (!f1.exists()) {
				f1.mkdirs();
			} else {
				FileUtil.removeFolder(path);
				f1.mkdirs();
			}
			path = path.concat(fileName);
			
			FileOutputStream fs = new FileOutputStream(path);
			byte[] buffer = new byte[1024 * 1024];
			int byteread = 0;
			while ((byteread = in.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletResponse response, String filepath,
			String fileName) throws IOException {

		try {
			File file = new File(path.concat(filepath));
			if (StringUtils.isBlank(fileName)) {
				fileName = file.getName();
			}
			response.addHeader("Content-Disposition","attachment; filename="+ new String(fileName.getBytes("GB2312"), "ISO-8859-1")); 
			response.setContentType("application/x-msdownload");
			response.setContentLength((int) file.length());
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
		} catch (FileNotFoundException e) {
			log.error("文件找不到", e);
		} catch (IOException e) {
			log.error("io异常", e);
		}
	}
	
	/**
	 * 导出固定模板
	 * 
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void exportFile(HttpServletResponse response, String filepath,
			String fileName) throws IOException {

		try {
			File file = new File(filepath);
			if (StringUtils.isBlank(fileName)) {
				fileName = file.getName();
			}
			response.addHeader("Content-Disposition","attachment; filename="+ new String(fileName.getBytes("GB2312"), "ISO-8859-1")); 
			response.setContentType("application/x-msdownload");
			response.setContentLength((int) file.length());
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
		} catch (FileNotFoundException e) {
			log.error("文件找不到", e);
		} catch (IOException e) {
			log.error("io异常", e);
		}
	}

	/**
	 * 下载缩略图
	 * 
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void downThumbnailImage(HttpServletResponse response, String filepath,
			String fileName) throws IOException {

		try {
			File file = new File(path.concat(filepath).concat(ImgUtil.DEFAULT_BACKFIX));
			if (StringUtils.isBlank(fileName)) {
				fileName = file.getName();
			}
			String de = "attachment;filename=".concat(URLEncoder.encode(
					fileName, "utf-8"));
			response.setHeader("Content-Disposition", de);
			response.setContentType("application/x-msdownload");
			response.setContentLength((int) file.length());
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
		} catch (FileNotFoundException e) {
			log.error("文件找不到", e);
		} catch (IOException e) {
			log.error("io异常", e);
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param 文件路径
	 */
	public static void delFile(String path) throws IOException {
		File file = new File(path);
		file.delete();
		// 删除缩略图
		File thumbnail = new File(path + ImgUtil.DEFAULT_BACKFIX);
		thumbnail.delete();
	}
	
	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param fileName
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletResponse response,
			String fileName) throws IOException {
		String pathstr = fileName;
		int l = fileName.lastIndexOf("\\");
		if (l == -1) {
			l = fileName.lastIndexOf("/");
		}
		String fileNameStr = fileName.substring(l + 1, fileName.length());
		downloadFile(response, pathstr, fileNameStr);
	}

}
