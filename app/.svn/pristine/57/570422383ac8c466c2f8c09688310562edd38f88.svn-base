package com.hnjz.app.work.rwgl;

import java.util.Map;

import javax.servlet.ServletContext;

import org.json.JSONArray;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * 任务管理RwglManager
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
包括：任务派发、已办任务、待办任务
 *
 */
public interface RwglManager extends Manager{
	
////////////////////////////////////////////////////任务派发模块////////////////////////////////////////////////////	
	/**
	 * 
	 * 函数介绍：查询：任务派发列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getRwpfList(String rwmc,String rwly, String page, String pageSize)throws Exception;
	/**
	 * 
	 * 函数介绍：查询：任务派发列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getRcbgRwpfList(String rwmc,String rwly,String rwlx ,String page, String pageSize)throws Exception;
	
	/**
	 * 删除生成的任务
	 * 函数介绍：
	 * 输入参数：
	 * 返回值：
	 */
	public void saveDelwork(String id) throws AppException;
	
	
////////////////////////////////////////////////////待办任务模块////////////////////////////////////////////////////
	/**
	 * 
	 * 函数介绍：
	查询：待办任务列表。
	 * 输入参数：
	rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String lx, String page, String pageSize)throws Exception;
	
////////////////////////////////////////////////////已办任务模块////////////////////////////////////////////////////
	/**
	 * 
	 * 函数介绍：
	查询：已办任务列表。
	 * 输入参数：
	rwmc：任务名称；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getYbrwList(String zfdxType,String rwmc,String rwly,String rwzt,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String tasktype, String pfrId, String zbrId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * 函数介绍：
	工作流中用到的taskId
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getTaskIdByWorkId(String workId);
	
	/**
	 * 
	 * 函数介绍：审核页面基本信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, Object> getShInfo(String applyId);
	
	/**
	 * 
	 * 函数介绍：详情页面基本信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, Object> taskDetailJBXX(String applyId);
	
	/**
	 * 
	 * 函数介绍：详情页面任务流转记录
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, Object> taskDetailRWLZJL(String applyId);
	
	/**
	 * 
	 * 函数介绍：详情页面任务流转记录（退回意见）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, Object> taskDetailRWLZJL_THYJ(String applyId);
	
	/**
	 * 
	 * 函数介绍：详情页面报告详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, Object> taskDetailBGXQ(String applyId);
	
	/**
	 * 获取用户代办任务条数
	 */
	public int getDbrwCount(TSysUser u);
	
	/**
	 * 
	 * 函数介绍：
	若附件列表中有处理意见书则自动派发后督察任务到主办人员的待办列表中
	 * 输入参数：
	 * 返回值：
	 */
	public void saveHdcTask(String applyId);
	
	/**
	 * 
	 * 函数介绍：
	是否上报上级
	 * 输入参数：
	
	 * 返回值：
	 */
	public Boolean isSB(String applyId);
	
	/**
	 * 
	 * 函数介绍：
	获取任务下派人
	 * 输入参数：
	
	 * 返回值：
	 */
	public TSysUser getXpr(String applyId);
	
	/**
	 * 
	 * 函数介绍：
	获取任务的下派任务
	 * 输入参数：
	
	 * 返回值：
	 */
	public Work getXpWork(String applyId);
	
	/**
	 * 
	 * 函数介绍：
	开始办理状态改为“办理中”
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveRwzxStart(String applyId);
	
////////////////////////////////////////////////////新建卷宗模块////////////////////////////////////////////////////
	/**
	 * 
	 * 函数介绍：查询卷宗列表。
	 * 输入参数：rwmc：卷宗名称；createTime：卷宗创建时间。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getJzList(String rwmc, String pfStarttime, String pfEndtime, String page, String pageSize)throws Exception;	
	
	/**
	 * 函数介绍：开始办理状态改为“办理中”
	 * 输入参数：
	 * 返回值：
	 */
	public void saveXml(String applyId);

	void setServletContext(ServletContext arg0);

	FyWebResult queryJcblFileList(String pid, String canDel, String fileType,
			String page, String rows);
	
	/**
	 * 任务重派时得到派发人派发时批示意见
	 * @param applyId
	 * @return
	 */
	public String getPsyj(String applyId);

	/**
	 * 
	 * 函数介绍：查询：现场监察任务列表。
	 * 输入参数：rwmc：任务名称；rwzt：任务状态。
	 * 返回值：FyWebResult
	 * @throws Exception 
	 */
	public FyWebResult getXcjcrwList(String rwmc, String rwly, String pfrId,String rwzt, String tasktype, String zfdxType, String pfStarttime,String pfEndtime, String gdStarttime, String gdEndtime,String page, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：查询：信访投诉任务列表。
	 * 输入参数：rwmc：任务名称；rwzt：任务状态。
	 * 返回值：FyWebResult
	 * @throws Exception
	 */
	public FyWebResult getXftsrwList(String rwmc, String rwly, String pfrId,String rwzt, String tasktype, String zfdxType, String pfStarttime,String pfEndtime, String gdStarttime, String gdEndtime,String xfdjbId,String page, String pageSize) throws Exception;
	/**
	 * 首页查询待办任务前6条
	 * @return
	 */
	public String dbQuery();
	
	/**
	 * 首页查询已办任务前6条
	 * @return
	 */
	public String ybQuery();
	
	/**
	 * 保存任务
	 * @param work
	 * @throws AppException
	 */
	public void savework(Work work) throws AppException;
	/**
	 * 日常办公任务列表查询
	 */
	public FyWebResult getRcbgrwList(String rwmc, String rwly, String pfrId, String rwzt, String tasktype, String zfdxType, String pfStarttime, String pfEndtime, String gdStarttime, String gdEndtime,
			String xfdjbId, String page, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：获取任务派发人
	 * 输入参数：
	 * 返回值：
	 */
	public TSysUser getPfr(String applyId);
	
	/**
	 * 
	 * 函数介绍：查询：执法检查待办任务列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getZfjcDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * 函数介绍：查询：信访投诉待办任务列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getXftsDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * 函数介绍：查询：日常办公待办任务列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getRcbgDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * 函数介绍：查询：任务实例列表。
	 * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
	 * 返回值：FyWebResult
	 */
	public FyWebResult getRwslList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * 函数介绍：查询：获取慧正工作流返回节点方法
	 * 输入参数：
	 * 返回值：FyWebResult
	 */
	public JSONArray preSubmitNodePubQuery();
	
	/**
	 * 获取用户逾期任务条数
	 */
	public int getYqrwCount(TSysUser u);
}
