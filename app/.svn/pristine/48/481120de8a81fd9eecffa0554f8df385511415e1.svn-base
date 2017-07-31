package com.hnjz.sys.eachStatistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.jxkh.areastatistics.StatisticsAreaManager;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.permission.PermissionController;
import com.hnjz.sys.po.TSysUser;
/**
 * 各师工作量统计的controller
 * @author Administrator
 *
 */
@Controller
public class EachStatisticsController {
	

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(PermissionController.class);
	
	@Autowired
	private EachStatisticsManager eManager;
	@Autowired
	private StatisticsAreaManager statisticsAreaManager;
	
	@Autowired
	private CommonManager commonManager;
	/**
	 * 各师登录次数、办理任务数和检查笔录份数的页面初始化
	 * @param model
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/handEcach.htm")
	public String configList(ModelMap model, String title,String areaid,String starttime,String endtime,String date) {
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		List<Combobox> list2 = commonManager.queryAreaComboWithCur(areaid);
		JSONArray array = JSONArray.fromObject(list2);
		model.addAttribute("areaid", areaid);
		model.addAttribute("array",array);
		model.addAttribute("title",title);
		return "sys/eacehStatistics/eacehStatistics";
	}
	
	@RequestMapping(value = "queryEachStatisticsList.json" , produces = "application/json")
	public void queryEachStatisticsList(ModelMap model, String title,String areaid,String starttime,String endtime,String date,String cid) {
		try {
			
			//默认当前区域
			TSysUser u = CtxUtil.getCurUser();
			if(StringUtils.isBlank(areaid)){
				areaid = u.getAreaId();
				Calendar a=Calendar.getInstance();
				starttime = String.valueOf(a.get(Calendar.YEAR));
			}
			endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			String emList =eManager.findEachStatisticsManager(areaid,date,cid);
			String emrw = eManager.findEachStatisticsManagerRW(areaid,date,cid);
			List<StatisticsDocForm> list = statisticsAreaManager.statisticsDocByAreaList(null,null, null, null, null, null, date, endtime);
			String embl = eManager.findEachStatisticsManagerBL(areaid,list,cid);
			model.addAttribute("emList",emList);
			model.addAttribute("emrw",emrw);
			model.addAttribute("embl",embl);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
}
