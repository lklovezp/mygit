package com.hnjz.app.jxkh.tasktrace;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	任务痕迹管理 控制层
 *
 */
@Controller
public class TaskTraceController {

	 /**日志*/
    private static final Log log = LogFactory.getLog(TaskTraceController.class);
	@Autowired
	private TaskTraceManager taskTraceManager;

	/**
	 * 
	 * 函数介绍：列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTraceList.htm")
	public String taskTraceList(ModelMap model,String title,String areaid){
		model.put("title", title);
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		return "app/jxkh/tasktrace/taskTraceList";
	}
	
	/**
	 * 
	 * 函数介绍：统计列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryTaskTraceList.json")
	public void queryTaskTraceList(ModelMap model,String areaid,String rwmc,String rwly,String starttime,String endtime,String tasktype,String pfrid,String czlx,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = taskTraceManager.queryTaskTraceList(areaid,rwmc, rwly, starttime, endtime, tasktype, pfrid, czlx, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
}
