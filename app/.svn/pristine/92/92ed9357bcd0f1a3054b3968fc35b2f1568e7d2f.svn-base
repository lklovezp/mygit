/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.rcbg.ConsultationManager;
import com.hnjz.app.jxkh.orgstatistics.StatisticsForm;
import com.hnjz.app.jxkh.userstatistical.StatisticsUserManager;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.user.UserManager;

/**
 * 主页控制器
 * 
 * @author wumi
 * @version $Id: IndexController.java, v 0.1 Dec 27, 2011 10:22:04 AM Administrator Exp $
 */
@Controller
public class IndexController {
    /**日志*/
    private static final Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    private IndexManager     indexManager;

    @Autowired
    private RwglManager    rwglManager;
    
    @Autowired
    private StatisticsUserManager    statisticsUserManager;
    
    @Autowired
    private ConsultationManager consultationManager;  
    
    @Autowired
	private UserManager userManager;
    @Value("#{settings['WHETHER_THE_JOB_RUN']}")
    private String           whetherToRun;                                  //作业是否执行 0执行1不执行
    
    /**
     * 展示主页，主要是查询出左侧的菜单<br>
     * 主要解决ie10点击兼容性视图时，会重新登录
     * 
     * @return
     */
    @RequestMapping(value = "/ire.htm")
    public String ire() {
        return "ire";
    }

    /**
     * 展示主页，主要是查询出左侧的菜单
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/i.htm")
    public String i(ModelMap model) {
    	JSONObject map= indexManager.i();
        model.addAttribute("map", map);
        //model.addAttribute("name", CtxUtil.getCurUser().getName());
        //model.addAttribute("rwCount", rwglManager.getDbrwCount(CtxUtil.getCurUser()));
        if (log.isDebugEnabled()) {
            log.debug("当前登录用户为：" + CtxUtil.getCurUser().getName());
        }
        //model.addAttribute("sysVer", indexManager.sysVer);
        return "i";
    }

    /**
     * 展示主页，主要是查询出左侧的菜单
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/home.htm")
    public String toHome(ModelMap model) {
    	DecimalFormat df=new DecimalFormat("0.0");//确定输出格式以“0.0”格式输出结果
		//待办任务条数
    	model.addAttribute("rwCount", rwglManager.getDbrwCount(CtxUtil.getCurUser()));
    	//未读会商数
		model.addAttribute("hsCount", consultationManager.getwdhsCount());
    	//已下发、完成任务条数
    	StatisticsForm statisticsForm = statisticsUserManager.statisticsRwCount();
    	if(statisticsForm!=null){
    		model.addAttribute("xdCount", statisticsForm.getXd());
        	model.addAttribute("wcCount", statisticsForm.getWc());
        	int yb=Integer.valueOf(statisticsForm.getWc());
        	int zs=Integer.valueOf(rwglManager.getDbrwCount(CtxUtil.getCurUser()))+Integer.valueOf(statisticsForm.getWc());
        	//int yqrw=Integer.valueOf(rwglManager.getYqrwCount(CtxUtil.getCurUser()));
        	model.addAttribute("zsCount",String.valueOf(zs));
        	model.addAttribute("yqCount",String.valueOf(statisticsForm.getYqzzbl()));
        	String bjlzf="";
        	if(zs>0){
        		float bjl=(float)yb/zs;
        		if((int)bjl==1){
        			//bjl=100;
        			bjlzf=String.valueOf("100");
        		}else{
        			//float a=bjl*100;
        			String a=df.format(bjl*100);
        			bjlzf= String.valueOf(a);
        		}
        		model.addAttribute("bjl",bjlzf);
        	}else{
        		model.addAttribute("bjl","0");
        	}
        	
    	}else{
    		model.addAttribute("xdCount", "0");
        	model.addAttribute("wcCount", "0");
    		model.addAttribute("zsCount","0");
    		model.addAttribute("bjl","0");
    		model.addAttribute("yqCount","0");
    	}
    	String page ="1";
    	//执法检查
    	//FyWebResult re = overdueTaskManager.queryYqrwByUser(CtxUtil.getUserId(),CtxUtil.getAreaId(),"", "", "", "", "", "", "", page, "20");
		//String total=String.valueOf(re.getTotal());
		//model.addAttribute("total",total);
		//信访投诉
		//FyWebResult xftsyql = overdueTaskManager.queryYqrwByUser(CtxUtil.getUserId(),CtxUtil.getAreaId(),"", "", "", "", "", "", "13", page, "20");
		//String xftsyqltotal=String.valueOf(xftsyql.getTotal());
		//model.addAttribute("xftsyqltotal",xftsyqltotal);
		//日常办公
		//FyWebResult rcbgyql = overdueTaskManager.queryYqrwByUser(CtxUtil.getUserId(),CtxUtil.getAreaId(),"", "", "", "", "", "", "24", page, "20");
		//String rcbgyqltotal=String.valueOf(rcbgyql.getTotal());
		//model.addAttribute("rcbgyqltotal",rcbgyqltotal);
        try {
        	//信访投诉
			FyWebResult xftsdb = rwglManager.getXftsrwList("","","", "", "", "", "", "", "", "", "", page, "20");
			model.addAttribute("xftsdb",xftsdb.getTotal());
			FyWebResult xftsbj = statisticsUserManager.queryRwInfo("2", "13", page, "20");
			model.addAttribute("xftsbj",xftsbj.getTotal());
			model.addAttribute("xftstotal",String.valueOf(xftsdb.getTotal()+xftsbj.getTotal()));
			float bjlxf=(float)xftsbj.getTotal()/(xftsdb.getTotal()+xftsbj.getTotal());
			if(bjlxf>0){
				if((int)bjlxf==1){
					model.addAttribute("xftsbjl","100");
				}else{
						String a=df.format(bjlxf*100);
						model.addAttribute("xftsbjl",String.valueOf(a));
				}
			}else{
				model.addAttribute("xftsbjl","0");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	//日常办公
			FyWebResult rcbgdb = rwglManager.getRcbgrwList("", "", "", "", "", "", "", "", "", "", "", page, "20");
			model.addAttribute("rcbgdb",rcbgdb.getTotal());
			FyWebResult rcbgbj = statisticsUserManager.queryRwInfo("2", "24", page, "20");
			model.addAttribute("rcbgbj",rcbgbj.getTotal());
			model.addAttribute("rcbgtotal",String.valueOf(rcbgdb.getTotal()+rcbgbj.getTotal()));
			float bjlrcbg=(float) rcbgbj.getTotal()/(rcbgbj.getTotal()+rcbgdb.getTotal());
			if(bjlrcbg>0){
				
				if((int)bjlrcbg==1){
					model.addAttribute("rcbgbjl","100");
				}else{
						String a=df.format(bjlrcbg*100);
						model.addAttribute("rcbgbjl",String.valueOf(a));
					
				}
			}else{
				model.addAttribute("rcbgbjl","0");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	//本月登录次数
    	//model.addAttribute("theMonthLogTimes", loginLogManager.queryZjTheMonthLogTimesList());
    	//所有登录次数
    	//model.addAttribute("allLogTimes", loginLogManager.queryZjAllLogTimesList());
        model.addAttribute("name", CtxUtil.getCurUser().getName());//姓名
        if(!"a0000000000000000000000000000000".equals(CtxUtil.getCurUser().getId())){
        	TSysOrg o = userManager.getOrgbyUser(CtxUtil.getCurUser().getId());
    		if(null != o) {
    			model.addAttribute("orgname", o.getName());//部门
    		}
    		List<TSysRole> role = userManager.getRolebyUser(CtxUtil.getCurUser().getId());
    		if(role.size() > 0) {
    			model.addAttribute("postion", role.get(0).getName());//职位
    		}
            model.addAttribute("zfzh", CtxUtil.getCurUser().getLawnumber());//执法证号
        }
        return "home";
    }

    /**
     * 开发帮助界面，显示最近执行的Controller
     * 
     * @return
     */
    @RequestMapping(value = "/help.htm")
    public void help(ModelMap model, HttpServletResponse res) throws AppException {
    	try {
    		indexManager.downHelpDoc(res, FileTypeEnums.APPWEBHELP.getCode());
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    @RequestMapping(value = "/isExistsHelpDoc.json", produces = "application/json")
    public void isExistsHelpDoc(ModelMap model) throws AppException {
    	try {
    		indexManager.isExistsHelpDoc(FileTypeEnums.APPWEBHELP.getCode());
    		model.addAttribute("state", true);
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
}
