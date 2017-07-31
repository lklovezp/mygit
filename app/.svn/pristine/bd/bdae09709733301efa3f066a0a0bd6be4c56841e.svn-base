package com.hnjz.app.jxkh.areastatistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-08-31
 * 功能描述：按区域统计控制层
 *
 */
@Controller
public class StatisticsAreaController {

	@Autowired
	private StatisticsAreaManager statisticsAreaManager;
	
	/**
	 * 
	 * 函数介绍：统计列表界面
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticsDocByAreaList.htm")
	public String statisticsDocByAreaList(ModelMap model,String title,String areaid,String tasktypeid,String rwly,String username,String orgid,String orgname,String jjcd,String starttime,String endtime){
		//默认当前区域
		if(StringUtils.isBlank(starttime)){
			Calendar a=Calendar.getInstance();
			starttime = String.valueOf(a.get(Calendar.YEAR));
		}
		model.addAttribute("starttime", starttime);
		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
		List<StatisticsDocForm> list = statisticsAreaManager.statisticsDocByAreaList(areaid,tasktypeid, rwly, username, orgid, jjcd, starttime, endtime);
		model.put("title", title);
		model.put("orgid", orgid);
		model.put("orgname", orgname);
		model.put("username", username);
		model.put("list", list);
		model.put("tasktypeid", tasktypeid);
		if(StringUtil.isNotBlank(tasktypeid)){
			List<TDataTasktype> tDataTasktypeList =  statisticsAreaManager.find("from TDataTasktype where code = ?", tasktypeid);
			if(tDataTasktypeList.size()>0){
				model.put("tasktypename", tDataTasktypeList.get(0).getName());
			}
		}
		model.put("rwly", rwly);
		model.put("jjcd", jjcd);
		model.put("endtime", endtime);
		return "app/jxkh/statisticsarea/statisticsDocByAreaList";
	}
	
	/**
	 * 
	 * 函数介绍：导出按区域统计监察笔录份数列表
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticsDocByAreaList")
	public void exportStatisticsDocByAreaList(ModelMap model,String areaid,String areaname,String title,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgid,String orgname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsAreaManager.exportStatisticsDocByAreaList(title, areaid,areaname,tasktypeid, tasktypename, rwly, rwlyname, username, orgid, orgname, jjcd, jjcdname, starttime, endtime, res);
	}	
	
}
