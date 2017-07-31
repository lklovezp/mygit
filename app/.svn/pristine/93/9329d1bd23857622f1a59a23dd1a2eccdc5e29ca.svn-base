package com.hnjz.app.jxkh.quarterstatistics;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：
	按季度统计控制层
 *
 */
@Controller
public class QuarterStatisticsController {

	@Autowired
	private QuarterStatisticsManager quarterStatisticsManager;

	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/quarterStatisticsList.htm")
	public String quarterStatisticsList(ModelMap model,String title,String year,String quarter,String areaid,String orgid,String orgname,String starttime,String endtime){
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		if(!StringUtil.isNotBlank(quarter)){
			Calendar calendar = Calendar.getInstance();
			quarter = String.valueOf(DateUtil.getCurQuarter()==1?"4":String.valueOf(DateUtil.getCurQuarter()-1));
			if(quarter.equals("4")){
				year = String.valueOf(calendar.get(Calendar.YEAR)-1);
			}else{
				year = String.valueOf(calendar.get(Calendar.YEAR));
			}
			if(quarter.equals("1")){
				starttime = year + "-01-01";
				endtime = year + "-03-31";
			}else if(quarter.equals("2")){
				starttime = year + "-04-01";
				endtime = year + "-06-30";
			}else if(quarter.equals("3")){
				starttime = year + "-07-01";
				endtime = year + "-09-30";
			}else if(quarter.equals("4")){
				starttime = year + "-10-01";
				endtime = year + "-12-31";
			}
		}
		List<QuarterForm> list = quarterStatisticsManager.statisticsQuarterList(areaid,orgid, starttime, endtime);
		model.put("title", title);
		model.put("year", year);
		model.put("quarter", quarter);
		model.put("quarterList", list);
		model.put("orgid", orgid);
		model.put("orgname", orgname);
		model.put("starttime", starttime);
		model.put("endtime", endtime);
		
		QuarterZfqkForm quarterZfqkForm = quarterStatisticsManager.queryQuarterZfqk(areaid,orgid, starttime, endtime);
		model.put("quarterZfqkForm", quarterZfqkForm);
		return "app/jxkh/quarterstatistics/quarterStatisticsList";
	}
	
	
	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportQuarterStatistics.json")
	public void exportQuarterStatistics(ModelMap model,String year,String quarter,String areaid,String orgid,String orgname,String starttime,String endtime,HttpServletResponse res){
		quarterStatisticsManager.exportQuarterStatistics( year, quarter, areaid, orgid, orgname, starttime, endtime, res);
	}
	
	@RequestMapping(value = "/queryWccs.htm")
	public String queryWccs(ModelMap model,String tasktype,String lawobjtype,String areaid,String zbOrgId,String yqwcStarttime,String yqwcEndtime){
		model.addAttribute("tasktype", tasktype);
		model.addAttribute("lawobjtype", lawobjtype);
		model.addAttribute("areaid", areaid);
		model.addAttribute("zbOrgId", zbOrgId);
		model.addAttribute("yqwcStarttime", yqwcStarttime);
		model.addAttribute("yqwcEndtime", yqwcEndtime);
		return "app/jxkh/quarterstatistics/queryWccs";
	}
	
}
