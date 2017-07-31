package com.hnjz.app.data.xxgl.lawdoc;

import java.util.List;

import org.json.JSONArray;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.FyWebResult;



/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager接口
 *
 */
public interface LawdocManager {
	
	/**
	 * 
	 * 函数介绍：查询新上传的文件列表（新建执法文件使用）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<LawdocForm> queryNewUploadLawdoc(String pid);
	
	/**
	 * 
	 * 函数介绍：获得当前目录下最大的排序值
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Integer getMaxorder(String pid);
	
	/**
	 * 
	 * 函数介绍：
	
	 * 输入参数：pid-目录id、uuid-临时目录id、data-json字符串[id-执法文件id数值、keywords-执法文件关键字数值、orderby-排序数组]
	
	 * 返回值：
	 */
	public void saveLawdoc(String pid,String uuid,String data);
	
	/**
	 * 
	 * 函数介绍：删除单个执法文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeLawdoc(String id);

	/**
	 * 
	 * 函数介绍：根据pid删除目录下的所有执法文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeLawdocByPid(String pid);
	
	/**
	 * 
	 * 函数介绍：分页查询执法文件列表
	
	 * 输入参数：
	 * @param pid:目录id
	 * @param title:标题
	 * @param keywords:关键字
	 * @param canDel:是否可删除
	 * @param page:当前页
	 * @param pageSize:每页显示条数
	
	 * 返回值：
	 */
	public FyWebResult queryLawdocList(String pid,String title,String keywords,String canDel,String page,String pageSize);

	/**
	 * 
	 * 函数介绍：获取执法文件详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public LawdocForm getLawdocInfo(String id);
	
	/**
	 * 
	 * 函数介绍：更新单个执法文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void updateLawdoc(LawdocForm lawdocForm,MultipartFile file);
	
	/**
	 * 
	 * 函数介绍：终端查询执法文件通用接口
	
	 * 输入参数：pid-目录id、title-标题、keywords-关键词、page-当前页、pageSize-每页显示条数
	
	 * 返回值：
	 */
	public FyWebResult queryLawdocListForMobile(String pid, String title, String keywords, String page, String pageSize);

	 /**
     * 
     * 函数介绍：保存选择的执法文件到任务相关附件
    
     * 输入参数：附件类型、任务id、选择的文件id（用逗号隔开）
    
     * 返回值：
     */
	public void saveChooseeLawdoc(String fileType,String applyId,String fileid);

	/**
	 * 
	 * 函数介绍：根据任务类型查询执法文件
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryLawdocListByTasktype(String tasktype, String title, String keywords, String page, String pageSize);

	String getmysqlDir(String dirid);
}
