package com.hnjz.app.data.xxgl.lawdocdir;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;


/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager接口
 *
 */
public interface LawdocdirManager extends Manager{
	

	/**
	 * 
	 * 函数介绍：获得执法文件目录树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String queryLawdicdirTree();
	
	/**
	 * 
	 * 函数介绍：通过任务类型获得执法文件目录树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String queryLawdicdirTreeByTasktype(String tasktypeCode);
	
	/**
	 * 
	 * 函数介绍：保存或更新执法文件目录
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveOrUpdateLawdocdir(LawdocdirForm lawdocdirForm);
	
	/**
	 * 
	 * 函数介绍：获取执法文件目录详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public LawdocdirForm getLawdocdirInfo(LawdocdirForm lawdocdirForm);

	/**
	 * 
	 * 函数介绍：获得目录 （下拉树）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> queryLawdicdirComboTree();
	
	/**
	 * 
	 * 函数介绍：获得根级目录列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray getLawdocDirListByPid(String pid);
	
	/**
	 * 
	 * 函数介绍：删除单个目录
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeLawdocdir(String id);
}
