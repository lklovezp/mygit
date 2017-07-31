package com.hnjz.mobile.data.jxkh;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.jxkh.orgstatistics.RwzxgcfxForm;
import com.hnjz.app.jxkh.orgstatistics.StageAnalysis;
import com.hnjz.app.jxkh.orgstatistics.StatisticsOrgManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	任务痕迹管理 控制层(手机端)
 *
 */
@Controller
public class TaskTraceMobileController {

	 /**日志*/
    private static final Log log = LogFactory.getLog(TaskTraceMobileController.class);
    
	@Autowired
	private TaskTraceMobileManager taskTraceMobileManager;
	
	@Autowired
	private StatisticsOrgManager statisticsOrgManager;

	/**
	 * 
	 * 函数介绍：统计列表数据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryTaskTraceList.mo")
	public void queryTaskTraceList(ModelMap model,String areaid,String rwmc,String czlx,String starttime,String endtime,String tasktype,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = taskTraceMobileManager.queryTaskTraceList(areaid,rwmc, starttime, endtime, tasktype, czlx, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	
	/**
	 * 
	 * 函数介绍：任务执行过程分析
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/rwzxgcfx.mo")
	public void rwzxgcfx(ModelMap model,String id){
		RwzxgcfxForm rwzxgcfxForm = statisticsOrgManager.getRwzxgcfxFormByRwid(id);
		model.addAttribute("rwzxgcfx",rwzxgcfxForm);
		StageAnalysis stageAnalysis = statisticsOrgManager.getMaxStageAnalysis(id);
		model.addAttribute("stageAnalysis",stageAnalysis);
		StageAnalysis yqjdForm = statisticsOrgManager.getYqStageAnalysis(id);
		model.addAttribute("yqjdForm",yqjdForm);
	}
	
	/**
	 * 
	 * 函数介绍：阶段用时分析列表
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryJdysfxList.mo")
	@ResponseBody
	public List<StageAnalysis> queryJdysfxList(ModelMap model,String id){
		return statisticsOrgManager.querystageAnalysisList(id);
	}
	
	
}
