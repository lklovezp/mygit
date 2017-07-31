package com.hnjz.app.jxkh.overduetask;

import java.util.Date;

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
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	逾期任务 控制层
 *
 */
@Controller
public class OverdueTaskController {

	 /**日志*/
    private static final Log log = LogFactory.getLog(OverdueTaskController.class);
	@Autowired
	private OverdueTaskManager overdueTaskManager;

	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/overdueTaskList.htm")
	public String overdueTaskList(ModelMap model,String areaid,String title){
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		model.put("title", title);
		return "app/jxkh/overduetask/overdueTaskList";
	}
	
	/**
	 * 
	 * 函数介绍：统计列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryOverdueTaskList.json")
	public void queryOverdueTaskList(ModelMap model,String areaid,String starttime,String endtime,String rwly,String jjcd,String isComplete,String rwmc,String tasktype,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			FyWebResult re = overdueTaskManager.queryOverdueTaskList(areaid,starttime, endtime, rwly, jjcd, isComplete, rwmc, tasktype, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	/**
	 * 
	 * 函数介绍：逾期任务
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/yqrwList.htm")
	public String yqrwList(ModelMap model,String rwlx,String title){
		model.put("title", "逾期任务");
		model.put("rwlx", rwlx);
		return "app/work/rwgl/yqrwList";
	}
	/**
	 * 
	 * 函数介绍：统计列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryYqrwByUserList.json")
	public void queryYqrwByUser(ModelMap model,String areaid,String starttime,String endtime,String rwly,String jjcd,String isComplete,String rwmc,String rwlx,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			String userid=CtxUtil.getUserId();
			areaid=CtxUtil.getAreaId();
			FyWebResult re = overdueTaskManager.queryYqrwByUser(userid,areaid,starttime, endtime, rwly, jjcd, isComplete, rwmc, rwlx, page, pageSize);
			//re.getTotal();
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
}
