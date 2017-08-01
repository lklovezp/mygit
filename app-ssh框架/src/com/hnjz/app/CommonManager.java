package com.hnjz.app;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.data.po.TDataFile;

/**
 * 
 * 作者：时秋寒
 *
 */
public interface CommonManager {

	/**
	 * 函数介绍：保存上传的文件

	 * 输入参数：
	 * @param filePo
	 * @param file
	 * @param pid
	 * @param fileenumtype
	 * @param path
 
	 * 返回值：无
	 */
	public TDataFile saveFile(TDataFile filePo, MultipartFile file, String pid, String fileenumtype, UploadFileType path);

	/**
	 * 函数介绍：保存生成的检查单文件
	 * 
	 * @param filePo
	 * @param file
	 * @param oName
	 * @param pid
	 * @param fileenumtype
	 * @param path
	 * @return
	 */
	public TDataFile saveFile(TDataFile filePo, File file, String oName, String pid, String fileenumtype, String filePath);
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
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size);

	/**
	 * 
	 * 函数介绍：上传文件
	
	 * 输入参数：multipartFile 上传的文件
	 * request http请求
	
	 * 返回值：
	 * @throws AppException 
	 */
	public TDataFile uploadFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException;

	/**
	 * 
	 * 函数介绍：根据附件类型获取扩展名
	
	 * 输入参数：fileType 附件类型
	
	 * 返回值：扩展名
	 */
	public String getExtension(String fileType);

	/**
	 * 
	 * 函数介绍：文件下载共用方法
	
	 * 输入参数：
	
	 * 返回值：
	 * @param applyId 
	 */
	public void downloadFile(String id, HttpServletResponse res);
	/**
	 * 
	 * 函数介绍：删除单个文件共用方法
	
	 * 输入参数：文件id
	
	 * 返回值：
	 */
	public void removeFile(String id);
	
	/**
	 * 
	 * 函数介绍：删除多个文件共用方法
	
	 * 输入参数：pid
	
	 * 返回值：
	 */
	public void removeFileByPid(String pid);
	
	/**
	 * 
	 * 函数介绍：删除任务办理所有文件（办理过程中所有附件，即除任务派发转派的所有任务附件）
	
	 * 输入参数：pid:任务id;
	
	 * 返回值：
	 */
	public void removeAllBlFileByPid(String pid);
	
	/**
	 * 
	 * 函数介绍：
	多附件删除（办理过程中的附件）
	 * 输入参数：
	enumtypeNames：附件类型枚举名称
	 * 返回值：
	 */
	public void removeBlFileByPidAndEnumtypeNames(String pid,String enumtypeNames);
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid、page当前页、rows每页显示条数
	
	 * 返回值：
	 * @param rows2 
	 */
	public FyWebResult queryFileList(String pid, String canDel,String fileType, String page, String rows);

	/**
	 * 
	 * 函数介绍：通过pid查询附件列表,无分页。
	
	 * 输入参数：pid
	
	 * 返回值：
	 * @param
	 */
	public FyWebResult queryFileList(String pid, String canDel,String fileType,String tableId);
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid、page当前页、rows每页显示条数
	
	 * 返回值：
	 * @param rows2 
	 */
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType, String page, String rows);
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid、page当前页、rows每页显示条数
	
	 * 返回值：
	 * @param rows2 
	 */
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType,String tableId, String page, String rows);

	/**
	 * 
	 * 函数介绍：单附件上传
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataFile uploadSingleFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException;

	/**
	 * 
	 * 函数介绍：打包文件并下载
	
	 * 输入参数：ids：文件id们
	
	 * 返回值：
	 */
	public void downZipFile(List<String> ids, HttpServletResponse res);

	/**
	 * 
	 * 函数介绍：预览文件
	 * 	查询文件并返回文件流
	 * 输入参数：ids：文件id们
	 * 返回值：
	 * @param request 
	 */
	public HashMap<String, Object> preview(String id, HttpServletRequest request);


	/**
	 *
	 * 函数介绍：预览图片
	 * 	查询图片并返回图片地址 及上一份附件和下一份附件。
	 * 输入参数：id：文件id
	 * 返回值：图片路径
	 * @param request
	 */
	public HashMap<String, Object> imgView(String id, HttpServletRequest request);

	/**
	 *
	 * 函数介绍：预览Doc、docx、excel文件
	 * 	查询图片并返回图片地址 及上一份附件和下一份附件。
	 * 输入参数：id：文件id
	 * 返回值：图片路径
	 * @param request
	 */
	public HashMap<String, Object> docView(String id, HttpServletRequest request);

	/**
	 *
	 * 函数介绍：预览PDF文件
	 * 	查询图片并返回图片地址
	 * 输入参数：id：文件id
	 * 返回值：图片路径
	 * @param request
	 */
	public HashMap<String,Object> pdfView(String id, HttpServletRequest request);

	/**
	 *
	 * 函数介绍：获取上一个附件和下一个附件的数据
	 * 	查询并返回上下两个文件的数据
	 * 输入参数：id：文件id
	 * 返回值：
	 * @param id
	 */
	public HashMap<String,String> getFjPageById(String id,TDataFile po);

	/**
	 * ftp下载
	 * 
	 * @param url ftp地址 如：192.168.0.1
	 * @param path 文件所在相对于ftp根目录的完整路径 如：folder1/folder2/folder3
	 * @param fileName 文件名 如：file.jpg
	 * @param userName ftp用户名
	 * @param passWord ftp密码
	 * @param passWord ftp端口
	 * @return
	 */
	public InputStream downFtpFile(String url, String path, String fileName, String userName, String passWord, int port);

	/**
	 * 按附件类型和业务id查询附件
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryFileList(String pid,String fileType);
	
	/**
	 * 按业务id查询附件
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryFileList(String pid);
	
	/**
	 * 
	 * 函数介绍：根据执法对象生成excel文件并下载
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void downTemplate(HttpServletResponse res);

	/**
	 * 
	 * 函数介绍：导入执法对象excel数据
	
	 * 输入参数：lawObjType 执法对象类型
	 * file excel文件
	
	 * 返回值：
	 * @throws AppException 
	 * @throws ParseException 
	 */
	public void importTemplate(MultipartFile file) throws AppException, ParseException;
	
	/**
	 * 
	 * 函数介绍：通用状态下拉列表 Y：有效 N：无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryZtList();

	LinkedHashMap<String, List<LinkedHashMap<String, Object>>> getExeclData();
}
