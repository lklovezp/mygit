package com.hnjz.app.jxkh.orgstatistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按部门统计控制层
 *
 */
@Controller
public class StatisticsOrgController {
	
	 /**日志*/
    private static final Log log = LogFactory.getLog(StatisticsOrgController.class);
	
	@Autowired
	private StatisticsOrgManager statisticsOrgManager;
	
	@Autowired
	private DicManager dicManager;
	
	@Autowired
	private AreaManager areaManager;

	/**
	 * 
	 * 函数介绍：统计列表界面
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticsOrgList.htm")
	public String statisticsOrgList(ModelMap model,String title,String areaId,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaId)){
			areaId = u.getAreaId();
		}
		model.addAttribute("areaId", areaId);
		List<StatisticsForm> list = statisticsOrgManager.statisticsOrgList(areaId,tasktypeid, rwly, jjcd, starttime, endtime);
		model.put("title", title);
		model.put("list", list);
		
		model.put("tasktypeid", tasktypeid);
		if(StringUtil.isNotBlank(tasktypeid)){
			List<TDataTasktype> tDataTasktypeList =  statisticsOrgManager.find("from TDataTasktype where code = ?", tasktypeid);
			if(tDataTasktypeList.size()>0){
				model.put("tasktypename", tDataTasktypeList.get(0).getName());
			}
		}
		model.put("rwly", rwly);
		model.put("jjcd", jjcd);
		model.put("starttime", starttime);
		model.put("endtime", endtime);
		
		return "app/jxkh/statisticsorg/statisticsOrgList";
	}
	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticalOrgList")
	public void exportStatisticalOrgList(ModelMap model,String areaId,String areaname,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsOrgManager.exportStatisticalOrgList(areaId,areaname,tasktypeid, tasktypename, rwly, rwlyname, jjcd, jjcdname, starttime, endtime, res);
	}
	
	/**
	 * 
	 * 函数介绍：查看统计详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticalOrgInfo.htm")
	public String statisticalOrgInfo(ModelMap model,String areaId,String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		model.put("type", type);
		String title = "按部门统计-";
		if("1".equals(type)){
			title += "下达次数";
		}else if("2".equals(type)){
			title += "完成次数";
		}else if("3".equals(type)){
			title += "逾期完成次数";
		}else if("4".equals(type)){
			title += "正在办理次数";
		}else if("5".equals(type)){
			title += "逾期正在办理次数";
		}
		model.put("title", title);
		model.put("tasktypeid", tasktypeid);
		model.put("type", type);
		if(StringUtils.isNotBlank(tasktypeid)){
			TDataTasktype tDataTasktype = (TDataTasktype) statisticsOrgManager.find("from TDataTasktype t where code = ?", tasktypeid).get(0);
			model.put("tasktypename", tDataTasktype.getName());
		}
		model.put("areaId", areaId);
		if(StringUtils.isNotBlank(areaId)){
			TSysArea area = (TSysArea) areaManager.get(TSysArea.class, areaId);
			model.put("areaname", area.getName());
		}
		model.put("rwly", rwly);
		if(StringUtils.isNotBlank(rwly)){
			model.put("rwlyname", dicManager.getNameByTypeAndCode("1",rwly));
		}
		model.put("jjcd", jjcd);
		if(StringUtils.isNotBlank(jjcd)){
			model.put("jjcdname", dicManager.getNameByTypeAndCode("3",jjcd));
		}
		model.put("starttime", starttime);
		model.put("endtime", endtime);
		String dateTime = "";
		if (StringUtils.isNotBlank(starttime) && StringUtils.isNotBlank(endtime)) {
			dateTime = starttime + "至" + endtime;
		} else if (StringUtils.isNotBlank(starttime)) {
			dateTime = starttime + "至今";
		} else if (StringUtils.isNotBlank(endtime)) {
			dateTime = "至" + endtime;
		}
		model.put("dateTime", dateTime);
		model.put("orgid", orgid);
		String orgname = "全部";
		if(StringUtils.isNotBlank(orgid) && !"sum".equals(orgid) && !"real".equals(orgid)){
			TSysOrg tSysOrg = (TSysOrg) statisticsOrgManager.get(TSysOrg.class, orgid);
			orgname = tSysOrg.getName();
		}
		model.put("orgname", orgname);
		return "app/jxkh/statisticsorg/statisticalOrgInfo";
	}
	
	
	/**
	 * 
	 * 函数介绍：查看统计详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryStatisticalOrgInfo.json")
	public void queryStatisticalOrgInfo(ModelMap model,String areaId,String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime,String page,String rows ){
		try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			FyWebResult re = statisticsOrgManager.queryStatisticalOrgInfo(areaId,orgid, type, tasktypeid, rwly, jjcd, starttime, endtime, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 
	 * 函数介绍：任务执行过程分析
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/rwzxgcfx.htm")
	public String rwzxgcfx(ModelMap model,String id){
		
		
		RwzxgcfxForm rwzxgcfxForm = statisticsOrgManager.getRwzxgcfxFormByRwid(id);
		model.addAttribute("rwzxgcfxForm",rwzxgcfxForm);
		StageAnalysis stageAnalysis = statisticsOrgManager.getMaxStageAnalysis(id);
		model.addAttribute("stageAnalysis",stageAnalysis);
		StageAnalysis yqjdForm = statisticsOrgManager.getYqStageAnalysis(id);
		model.addAttribute("yqjdForm",yqjdForm);
		model.put("id", id);
		return "app/jxkh/statisticsorg/rwzxgcfx";
	}
	
	/**
	 * 
	 * 函数介绍：阶段用时分析列表
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/jdysfxList.htm")
	public String jdysfxList(ModelMap model,String id){
		model.put("id", id);
		return "app/jxkh/statisticsorg/jdysfxList";
	}
	
	
	/**
	 * 
	 * 函数介绍：阶段用时分析列表
	
	 * 输入参数：任务id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryJdysfxList.json")
	@ResponseBody
	public List<StageAnalysis> queryJdysfxList(ModelMap model,String id){
		return statisticsOrgManager.querystageAnalysisList(id);
	}
	
	/**
	 * 
	 * 函数介绍：导出统计（任务列表）
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticalOrgInfoList.json")
	public void exportStatisticalOrgInfoList(ModelMap model,String areaId,String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsOrgManager.exportStatisticalOrgInfoList(areaId,areaname,orgid, orgname, type, tasktypeid, tasktypename, rwly, rwlyname, jjcd, jjcdname, starttime, endtime, res);
	}
	
	/**
	 * 
	 * 函数介绍：统计列表界面
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticsDocByOrgList.htm")
	public String statisticsDocByOrgList(ModelMap model,String title,String areaId,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaId)){
			areaId = u.getAreaId();
		}
		model.addAttribute("areaId", areaId);
		if(StringUtils.isBlank(starttime)){
			Calendar a=Calendar.getInstance();
			starttime = String.valueOf(a.get(Calendar.YEAR));
		}
		model.addAttribute("starttime", starttime);
		List<StatisticsDocForm> list = statisticsOrgManager.statisticsDocByOrgList(areaId,tasktypeid, rwly, jjcd, starttime, endtime);
		model.put("title", title);
		model.put("list", list);
		
		model.put("tasktypeid", tasktypeid);
		if(StringUtil.isNotBlank(tasktypeid)){
			List<TDataTasktype> tDataTasktypeList =  statisticsOrgManager.find("from TDataTasktype where code = ?", tasktypeid);
			if(tDataTasktypeList.size()>0){
				model.put("tasktypename", tDataTasktypeList.get(0).getName());
			}
		}
		model.put("rwly", rwly);
		model.put("jjcd", jjcd);
		model.put("endtime", endtime);
		
		return "app/jxkh/statisticsorg/statisticsDocByOrgList";
	}
	
	/**
	 * 
	 * 函数介绍：导出统计（监察笔录份数列表）
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticalDocInfoList")
	public void exportStatisticalDocInfoList(ModelMap model,String areaId,String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsOrgManager.exportStatisticalDocInfoList(areaId,areaname,orgid, orgname, type, tasktypeid, tasktypename, rwly, rwlyname, jjcd, jjcdname, starttime, endtime, res);
	}
}
