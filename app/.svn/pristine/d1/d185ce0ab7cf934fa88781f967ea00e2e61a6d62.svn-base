package com.hnjz.sys.personalStatistics;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.app.jxkh.userstatistical.StatisticsUserManager;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.permission.PermissionController;
import com.hnjz.sys.po.TSysUser;
/**
 * 各人工作量统计的controller
 * @author Administrator
 *
 */
@Controller
public class PersonalStatisticsController {
	
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(PermissionController.class);
	@Autowired
	private PersonalStatisticsManager psManager;
	/**
	 * 各人登录次数、办理任务数和检查笔录份数的页面初始化
	 * @param model
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "personalList.htm")
	public String findPersonalStatisticsList(ModelMap model, String title,String areaid){
		TSysUser u = CtxUtil.getCurUser();
		String userId = CtxUtil.getUserId();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		model.addAttribute("userId", userId);
		model.addAttribute("title", title);
		return "sys/personalStatistics/personalStatistics";
	}
	
	
	
	@RequestMapping(value = "queryPersonalName.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryPersonalName(ModelMap model, String title,String id){
		try {
			return psManager.createZterr(id);
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	@RequestMapping(value = "queryPersonalList.json" , produces = "application/json")
	public void queryPersonalList(ModelMap model, String title,String areaid,String starttime,String endtime,String date,String cid,String userId,String time) {
		try {
		
			//默认当前区域
			TSysUser u = CtxUtil.getCurUser();
			if(StringUtils.isBlank(date)){
				date = u.getAreaId();
				Calendar a=Calendar.getInstance();
				starttime = String.valueOf(a.get(Calendar.YEAR));
			}
			
			endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			String emList =psManager.findEachStatisticsManager(userId,time,cid);//登录次数echarts数据
			String emrw = psManager.findEachStatisticsManagerRW(userId,time,cid);//任务次数echarts数据
			List<StatisticsDocForm> list = psManager.statisticsUser(date,null, null, null, null, null, time, endtime,userId);
			String embl = psManager.findEachStatisticsManagerBL(userId,list,cid);//登录检查笔录echarts数据
			model.addAttribute("emList",emList);
			model.addAttribute("emrw",emrw);
			model.addAttribute("embl",embl);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
}
