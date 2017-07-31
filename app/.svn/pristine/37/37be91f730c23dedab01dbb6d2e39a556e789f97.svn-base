package com.hnjz.app.work.unzip;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.AppException;
import com.hnjz.common.util.FileZipUtil;

public interface UnzipManager {
	/**
	 * 
	 * 函数介绍：上传文件
	
	 * 输入参数：multipartFile 上传的文件
	 * request http请求
	
	 * 返回值：
	 * @throws AppException 
	 */
	public TDataFile uploadFile(MultipartFile multipartFile, HttpServletRequest request)throws AppException;
	
	/**
	 * 函数介绍：上传并保存文件

	 * 输入参数：
	 * @param is 文件输入流
	 * @param pid 父id
	 * @param fileenumtype 附件枚举类型
	 * @param path 存放路径（安装文件、检查单等）
	 * @param filename 文件真实名称 有扩展名的
	 * @param size 文件大小
 
	 * 返回值：无
	 */	
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype,
			String path, String filename, Long size);
	/**
	 * 解压路径 zipPath 下的所有文件到 outputPath
	 * 
	 * @param td
	 *            TDataFile
	 * @param outUnZipPath
	 *            解压后的输出路径
	 * @return TDataFile 返回解压后数据
	 * 
	 */


	public TDataFile unZipAndGetData(TDataFile td, String outUnZipPath,
			MultipartFile multipartFile) throws AppException;
	public String findCompany(String fileName)throws AppException;

}
