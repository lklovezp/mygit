package com.hnjz.app.jxkh.userstatistical;

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

import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.app.jxkh.orgstatistics.StatisticsForm;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-31
 * 功能描述：按人员统计控制层
 *
 */
@Controller
public class StatisticsUserController {
	
	/**日志*/
    private static final Log log = LogFactory.getLog(StatisticsUserController.class);

	@Autowired
	private StatisticsUserManager statisticsUserManager;
	
	@Autowired
	private DicManager dicManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private AreaManager areaManager;
	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticsUserList.htm")
	public String statisticsUserList(ModelMap model,String title,String areaid,String tasktypeid,String rwly,String username,String orgid,String orgname,String jjcd,String starttime,String endtime){
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		
		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
		List<StatisticsForm> list = statisticsUserManager.statisticsUserList(areaid,tasktypeid, rwly, username, orgid, jjcd, starttime, endtime);
		model.put("title", title);
		model.put("orgid", orgid);
		model.put("orgname", orgname);
		model.put("username", username);
		model.put("list", list);
		model.put("tasktypeid", tasktypeid);
		if(StringUtil.isNotBlank(tasktypeid)){
			List<TDataTasktype> tDataTasktypeList =  statisticsUserManager.find("from TDataTasktype where code = ?", tasktypeid);
			if(tDataTasktypeList.size()>0){
				model.put("tasktypename", tDataTasktypeList.get(0).getName());
			}
		}
		model.put("rwly", rwly);
		model.put("jjcd", jjcd);
		model.put("starttime", starttime);
		model.put("endtime", endtime);
		return "app/jxkh/statisticsuser/statisticsUserList";
	}
	
	/**
	 * 
	 * 函数介绍：导出统计列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticsUserList")
	public void exportStatisticsUserList(ModelMap model,String areaid,String areaname,String title,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgid,String orgname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsUserManager.exportStatisticsUserList(title, areaid,areaname,tasktypeid, tasktypename, rwly, rwlyname, username, orgid, orgname, jjcd, jjcdname, starttime, endtime, res);
	}
	
	
	/**
	 * 
	 * 函数介绍：查看统计详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticalUserInfo.htm")
	public String statisticalUserInfo(ModelMap model,String username,String areaid, String areaname, String userid,String orgid,String orgname,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime){
		model.put("type", type);
		String title = "按人员统计-";
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
		if(StringUtils.isNotBlank(tasktypeid)){
			TDataTasktype tDataTasktype = (TDataTasktype) statisticsUserManager.find("from TDataTasktype t where code = ?", tasktypeid).get(0);
			model.put("tasktypename", tDataTasktype.getName());
		}
		model.put("areaid", areaid);
		if(StringUtils.isNotBlank(areaid)){
			TSysArea area = (TSysArea) areaManager.get(TSysArea.class, areaid);
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
		model.put("userid", userid);
		if(StringUtils.isNotBlank(userid) && !"sum".equals(userid) && !"real".equals(userid)){
			TSysUser tSysUser = (TSysUser) statisticsUserManager.get(TSysUser.class, userid);
			username = tSysUser.getName();
			if(StringUtils.isBlank(orgname)){
				orgname = userManager.getOrgbyUser(userid).getName();
			}
		}
		model.put("username", username);
		model.put("orgname", orgname);
		return "app/jxkh/statisticsuser/statisticalUserInfo";
	}
	
	/**
	 * 
	 * 首页：查看下达、完成任务详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/rwInfo.htm")
	public String rwInfo(ModelMap model,String type,String tasktype){
		model.put("type", type);
		String title = "";
		if("1".equals(type)){
			title += "已下达的任务";
		}else if("2".equals(type)){
			title += "已办理完成的任务";
		}
		model.put("title", title);
		model.put("tasktype", tasktype);
		return "app/jxkh/statisticsuser/rwInfo";
	}
	
	/**
	 * 
	 * 首页：查看任务详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryRwInfo.json")
	public void queryRwInfo(ModelMap model,String type,String tasktype, String page,String rows ){
		try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = statisticsUserManager.queryRwInfo(type,tasktype, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 
	 * 函数介绍：查看统计详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryStatisticalUserInfo.json")
	public void queryStatisticalUserInfo(ModelMap model,String userid,String areaid,String orgid,String type,String tasktypeid,String rwly,String jjcd,String starttime,String endtime,String page,String rows ){
		try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			FyWebResult re = statisticsUserManager.queryStatisticalUserInfo(userid, areaid, orgid, type, tasktypeid, rwly, jjcd, starttime, endtime, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
	}
	
	
	/**
	 * 
	 * 函数介绍：导出统计（任务列表）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticalUserInfoList.json")
	public void exportStatisticalUserInfoList(ModelMap model,String userid,String username,String areaid, String areaname,String orgid,String orgname,String type,String tasktypeid,String tasktypename,String rwly,String rwlyname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsUserManager.exportStatisticalUserInfoList(userid,username, areaid, areaname,orgid,orgname, type, tasktypeid, tasktypename, rwly, rwlyname, jjcd, jjcdname, starttime, endtime, res);
	}
	
	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/statisticsDocByUserList.htm")
	public String statisticsDocByUserList(ModelMap model,String title,String areaid,String tasktypeid,String rwly,String username,String orgid,String orgname,String jjcd,String starttime,String endtime){
		//默认当前区域
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		if(StringUtils.isBlank(starttime)){
			Calendar a=Calendar.getInstance();
			starttime = String.valueOf(a.get(Calendar.YEAR));
		}
		model.addAttribute("starttime", starttime);
		endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
		List<StatisticsDocForm> list = statisticsUserManager.statisticsDocByUserList(areaid,tasktypeid, rwly, username, orgid, jjcd, starttime, endtime);
		model.put("title", title);
		model.put("orgid", orgid);
		model.put("orgname", orgname);
		model.put("username", username);
		model.put("list", list);
		model.put("tasktypeid", tasktypeid);
		if(StringUtil.isNotBlank(tasktypeid)){
			List<TDataTasktype> tDataTasktypeList =  statisticsUserManager.find("from TDataTasktype where code = ?", tasktypeid);
			if(tDataTasktypeList.size()>0){
				model.put("tasktypename", tDataTasktypeList.get(0).getName());
			}
		}
		model.put("rwly", rwly);
		model.put("jjcd", jjcd);
		model.put("endtime", endtime);
		return "app/jxkh/statisticsuser/statisticsDocByUserList";
	}
	
	/**
	 * 
	 * 函数介绍：按人员统计监察笔录份数数据导出
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticsDocByUserList")
	public void exportStatisticsDocByUserList(ModelMap model,String areaid,String areaname,String title,String tasktypeid,String tasktypename,String rwly,String rwlyname,String username,String orgid,String orgname,String jjcd,String jjcdname,String starttime,String endtime,HttpServletResponse res){
		statisticsUserManager.exportStatisticsDocByUserList(title, areaid,areaname,tasktypeid, tasktypename, rwly, rwlyname, username, orgid, orgname, jjcd, jjcdname, starttime, endtime, res);
	}
	
}
