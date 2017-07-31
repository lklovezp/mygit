package com.hnjz.app.common;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		公共功能Manager层
 *
 */
public interface CommonManager {

	/**
	 * 
	 * 函数介绍：查询控制类型下拉列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryKzlxList();

	/**
	 * 
	 * 函数介绍：通用状态下拉列表 Y：有效 N：无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryZtList();
	
	/**
	 * 
	 * 函数介绍：企业状态下拉列表0:运营中、1:已关闭、2:已停产
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryQyztList();

	/**
	 * 
	 * 函数介绍：获得行业类型下拉列表
	
	 * 输入参数：执法对象类型
	
	 * 返回值：
	 */
	public List<Combobox> queryIndustryList(String lawobjType);

	/**
	 * 
	 * 函数介绍：附件类型下拉列表
	
	 * 输入参数：附件类型编号，支持模糊查询
	
	 * 返回值：
	 */
	public List<Combobox> queryFjlxList(String enumName);

	/**
	 * 
	 * 函数介绍：获取行政区树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> queryRegionTree();
	
	/**
	 * 获取执法对象类型树
	 */
	public List<Combobox> queryLawobjtypeTree();
	/**
	 * 
	 * 函数介绍：获取部门树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> queryOrgTree();
	
	/**
	 * 
	 * 函数介绍：获取部门树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryWgTree();
	

	/**
	 * 
	 * 函数介绍：获取违法类型下拉框
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> queryIllegalTypeList(String ids);
	
	/**
	 * 根据任务类型获取违法类型下拉树
	 * @return
	 */
	public List<ComboboxTree> queryIllegalTypeByTasktypeList(String ids,String tasktype);
	
	/**
	 * 
	 * 函数介绍：查询字典中数据列表
	
	 * 输入参数：字典类型
	
	 * 返回值：
	 */
	public List<Combobox> queryTSysDicList(String type);
	
	/**
	 * 
	 * 函数介绍：根据type和code查询字典中字段名称
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getDicNameByTypeAndCode(String type,String code);

	/**
	 * 
	 * 函数介绍：查询任务类型下拉框数据
	
	 * 输入参数：无
	
	 * 返回值：任务类型下拉框
	 */
	public List<Combobox> queryTaskTypeCombo();

	public List<ComboboxTree> queryTaskTypePubTree(String markId);

	public List<Combobox> queryAreaCombo(String areaId);
	
	public List<Combobox> queryAreaComboByAreaId(String areaId);

	/**
	 * 
	 * 函数介绍：是否状态
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> querySfList();
	
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
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size,TSysUser giveUser);
	
	/**
	 * 
	 * 函数介绍：获取任务状态下拉列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryRwztCombo();
	
	/**
	 * 
	 * 函数介绍：根据执法对象生成excel文件并下载
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void downTemplate(String lawObjType, HttpServletResponse res);

	/**
	 * 
	 * 函数介绍：导入执法对象excel数据
	
	 * 输入参数：lawObjType 执法对象类型
	 * file excel文件
	
	 * 返回值：
	 * @throws AppException 
	 */
	public void importTemplate(String lawObjType, MultipartFile file) throws AppException;

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
	 * 函数介绍：下载缩略图
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void downThumbnailImage(String id, HttpServletResponse res);
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
	 * 函数介绍：删除多个文件共用方法（办理过程中附件）
	
	 * 输入参数：pid:任务id;fileenumtype:附件类型;
	
	 * 返回值：
	 */
	public void removeFileByPidAndFileType(String pid,String fileenumtype);
	
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
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid、page当前页、rows每页显示条数
	
	 * 返回值：
	 * @param rows2 
	 */
	public FyWebResult queryFileList(String pid, String canDel,String fileType,String tableId, String page, String rows);
	
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
	 * 函数介绍：保存任务选择的执法对象
	
	 * 输入参数：成功success或错误信息
	
	 * 返回值：
	 */
	public String saveChoseeLawobj(String rwid, String lawobjtype, JSONArray array, TSysUser user);
	
	/**
	 * 
	 * 函数介绍：获取任务选中的执法对象列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<TaskandlawobjForm> querySelectLawobjList(String rwid,String lawobjtype);

	/**
	 * 
	 * 函数介绍：保存文件（指定文件id）
	
	 * 输入参数：lawobj-执法文件对象，is-文件流，fileenumtype-附件类型，path-文件路径，filename-文件真实名称，size-文件大小
	
	 * 返回值：
	 */
	public TDataFile saveFile(TDataLawdoc lawobj, InputStream is, String fileenumtype, String path, String filename, Long size);

	/**
	 * 
	 * 函数介绍：保存执法文件
	
	 * 输入参数：id-文件id，is-文件流，pid-父id，fileenumtype-附件类型，path-文件路径，filename-文件真实名称，size-文件大小
	
	 * 返回值：
	 */
	public TDataFile saveLawdoc(InputStream is, String pid, String fileenumtype, String path, String filename, Long size);

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
	 * 函数介绍：获得执法对象某一字段的所有值（例如：工业污染源的所有名称）
	
	 * 输入参数：lawobjtype-执法对象类型，enumCode-字段枚举值
	
	 * 返回值：
	 */
	public List<String> getColumnValue(String lawobjtype,String enumCode);

	public List<String> findBySql(String sql, Object... canshu);
	

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
	 * 
	 * 函数介绍：判断附件类型是否必填
	
	 * 输入参数：枚举名称（用,隔开）
	
	 * 返回值：
	 */
	public List<Map<String, String>> getIsBtlist(String fileType);

	public List<Combobox> getJcmbByTaskType(String tasktype);
	/**
	 * 查询当前用户及以下的区域
	 * 
	 * @return
	 */
	public List<Combobox> queryAreaComboWithCur(String areaId);
	
	/**
	 * 查询当前用的区域,如果是admin查询所有的区域
	 */
	public List<Combobox> queryAreaCur(String areaId);
	/**
	 * 
	 * 函数介绍：执法对象名称下拉列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryzfdxmcList();
	/**
	 * 
	 * 函数介绍：执法对象名称下拉列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public LinkedHashMap<String, List<LinkedHashMap<String, Object>>> getExeclData(String lawObjType);
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
	 * 按业务id查询附件,去掉某种类型附件
	 * @param pid
	 * @param fileType
	 * @return
	 */
	public List<TDataFile> queryNoSomeFileList(String pid,String fileType1,String fileType2);
	
	public TDataFile saveFile(TDataFile t);
	
	/**
	 * 
	 * 函数介绍：文件导出带基本信息的共用方法
	 * 输入参数：
	 * 返回值：
	 * @param applyId 
	 */
	public void exportFile(String id, String applyId, HttpServletResponse res);
	
	/**
	 *
	 * 函数介绍：预览图片
	 * 	查询图片并返回图片地址 及上一份附件和下一份附件。
	 * 输入参数：id：文件id
	 * 返回值：图片路径
	 * @param request
	 */
	public HashMap<String, Object> imgView(String id, HttpServletRequest request);

}
