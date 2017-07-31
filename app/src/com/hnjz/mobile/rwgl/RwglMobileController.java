/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.rwgl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkSh;
import com.hnjz.app.work.hjgl.HjglManager;
import com.hnjz.app.work.jcd.JcdManager;
import com.hnjz.app.work.kcbl.KcblForm;
import com.hnjz.app.work.kcbl.KcblManager;
import com.hnjz.app.work.manager.WorkReportManager;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.manager.nodes.ZxNode;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.app.work.wszz.HjwfxwtzForm;
import com.hnjz.app.work.wszz.JdssdhzForm;
import com.hnjz.app.work.wszz.TzgzssdhzForm;
import com.hnjz.app.work.wszz.WszzManager;
import com.hnjz.app.work.xfdj.XfdjManager;
import com.hnjz.app.work.xwbl.XwblForm;
import com.hnjz.app.work.xwbl.XwblManager;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.BlZxxdForm;
import com.hnjz.app.work.zx.BlZxxdZrwMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.app.work.zx.ZxWorkManager;
import com.hnjz.app.work.zxzz.ZxzzManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.mobile.common.CommonMobileManager;
import com.hnjz.mobile.data.xxcx.LawobjMobileManager;
import com.hnjz.quartz.JobType;
import com.hnjz.quartz.service.JobManager;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;
import com.hnjz.sys.role.RoleController;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.AbsJbpmController;

/**
 * 任务管理Controller
 * 作者：xb
 * 生成日期：Mar 9, 2015
 * 功能描述：
包括：任务派发、已办任务、待办任务
 *
 */
@Controller
public class RwglMobileController extends AbsJbpmController{

    /**日志*/
    private static final Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private RwglManager    rwglManager;
    
    /**任务派发**/
    @Autowired
    private WorkPf           workPf;
    
    @Autowired
    private JobManager      jobManager;
    
    @Autowired
	private UserManager userManager;
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    private HjglManager      hjglManager;
    
    @Autowired
    protected ZxNode zxNode;
    
    @Autowired
    private CommWorkManager    commWorkManager;
    
    @Autowired
    private XwblManager    xwblManager;
    
    @Autowired
    private KcblManager    kcblManager;
    
    @Autowired
	private JcdManager jcdManager;
    
    @Autowired
	private CommonMobileManager commonMobileManager;
    
    @Autowired
    private ZxWorkManager    zxWorkManager;
    
    @Autowired
    private WorkReportManager workReportManager;
    
    @Autowired
    private ZxzzManager    zxzzManager;
    
    @Autowired
    private LawobjMobileManager lawobjMobileManager;
    
    @Autowired
	private WszzManager wszzManager;
    
    @Autowired
	private AreaManager areaManager;
    
    @Autowired
    protected WorkDao             workDao;
    
    @Autowired
	private XfdjManager xfdjManager;
	
	 /** 部门管理 */
    @Autowired
    private OrgManager              orgManager;
    
    @Autowired
	private Dao dao;
    
/////////////////////////////////////////任务派发模块//////////////////////////////////////////////
    /**
     * 
     * 函数介绍：查询：任务派发列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源。
     * 返回值：
     */
    @RequestMapping(value = "/getRwpfList.mo", produces = "application/json")
    public void getRwpfList(ModelMap model, String rwmc,String rwly,String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRwpfList(rwmc,rwly, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 进入任务派发查看页面
     * @param applyId   申请单编号
     * @param model
     */
    @RequestMapping(value = "/pfPageInfo.mo")
    public void pfPageInfo(String applyId,ModelMap model) {
        try {
        	Work work = null;
        	String str1="";
        	String str2="";
        	String str3="";
        	//派发
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		if(StringUtils.isNotBlank(work.getJsr())){
        			TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getJsr());
        			work.setJsrName(jsrObj.getName());
        		}else{
        			work.setJsrName("");
        		}
        		if(StringUtils.isNotBlank(work.getJsrIds())){
        			String [] jsrIds = work.getJsrIds().split(",");
        			String jsrNames = "";
        			for(String jsr : jsrIds){
        				TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, jsr);
        				jsrNames = jsrNames+jsrObj.getName() + ",";
        			}
        			jsrNames = jsrNames.substring(0,jsrNames.length()-1);
        			work.setJsrNames(jsrNames);
        		}
        		
        		//任务来源名称
        		if(work.getSource()!=null){
        			str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
            		//work.setSource(str1);
        		}
        		//任务密级名称
        		if(work.getSecurity()!=null){
        			str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
            		//work.setSecurity(str2);
        		}
        		//紧急程度名称
        		if(work.getEmergency()!=null){
        			str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
            		//work.setEmergency(str3);
        		}
        		
        		//获取执法对象名称
        		String zfdxmcs = "";
        		String zfdxmcsId = "";
        		List<Map<String, String>> listMap = commWorkManager
    					.zfdxTableData(applyId);
        		for(int i = 0; i < listMap.size(); i++){
        			if(i<listMap.size()-1){
        				zfdxmcs += listMap.get(i).get("lawobjname")+',';
        				zfdxmcsId += listMap.get(i).get("lawobjid")+',';
        			}else{
        				zfdxmcs += listMap.get(i).get("lawobjname");
        				zfdxmcsId += listMap.get(i).get("lawobjid");
        			}
        		}
        		work.setZfdxmc(zfdxmcs);
        		
        		// 任务类型列表
    			List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(applyId);
    			model.addAttribute("rwlxlistMap", rwlxlistMap);
    			String rwlxIds = "";
    			String rwlxNames = "";
    			for (int i = 0; i < rwlxlistMap.size(); i++) {
    				if (i < rwlxlistMap.size() - 1) {
    					rwlxIds += rwlxlistMap.get(i).get("id") + ",";
    					rwlxNames += rwlxlistMap.get(i).get("name") + ",";
    				} else {
    					rwlxIds += rwlxlistMap.get(i).get("id");
    					rwlxNames += rwlxlistMap.get(i).get("name");
    				}
    			}
    			if(StringUtils.isNotBlank(work.getRwmcgs())){
    				work.setRwmcgs(work.getRwmcgs());
    			}else{
    				work.setRwmcgs("0");
    			}
    			model.addAttribute("rwlxIds", rwlxIds);
    			model.addAttribute("rwlxNames", rwlxNames);
    			model.addAttribute("zfdxmcsId", zfdxmcsId);
        	}
        	////封装数据///
        	Map<String,String> workMap = new HashMap<String, String>();//任务信息map
        	workMap.put("id", work.getId());//任务id
        	workMap.put("name", work.getName());//任务名称
        	workMap.put("workNote", work.getWorkNote());//主要内容
        	workMap.put("source", work.getSource());//任务来源
        	workMap.put("sourceName", str1);//任务来源name
        	workMap.put("security", work.getSecurity());//任务密级
        	workMap.put("securityName", str2);//任务密级name
        	workMap.put("emergency", work.getEmergency());//紧急程度
        	workMap.put("emergencyName", str3);//紧急程度name
        	workMap.put("djrName", work.getDjrName());//登记人
        	workMap.put("djrId", work.getDjrId());//登记人id
        	workMap.put("createTime", DateUtil.getDateTime("yyyy-MM-dd", work.getCreateTime()));//任务生成时间
        	workMap.put("endTime", DateUtil.getDateTime("yyyy-MM-dd", work.getEndTime()));//要求完成时限
        	workMap.put("jsrName", work.getJsrName());//接受人
        	workMap.put("jsrNames", work.getJsrNames());//接受人
        	workMap.put("jsrIds", work.getJsrIds());//接受人
        	workMap.put("jsrId", work.getJsr());//接受人id
        	workMap.put("pfrName", CtxUtil.getCurUser().getName());//派发人
        	workMap.put("psyj", work.getPsyj());//批示意见
        	workMap.put("rwmcgs", work.getRwmcgs());//任务名称格式
        	workMap.put("zfdxType", work.getZfdxType());//执法对象类型
        	//String zfdxTypeName = commonManager.getDicNameByTypeAndCode(DicTypeEnum.LAWTYPE.getCode(),work.getZfdxType()==null?"":work.getZfdxType());
    		TDataLawobjtype lawobjtype=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, work.getZfdxType()==null?"":work.getZfdxType());
        	workMap.put("zfdxTypeName", lawobjtype.getName());//执法对象类型name
        	workMap.put("rwmcrq", DateUtil.getDateTime("yyyy-MM-dd", work.getRwmcrq()));//任务名称日期
        	workMap.put("zfdxmc", work.getZfdxmc());//执法对象名称
        	model.addAttribute("workMap", workMap);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    
    /**
     * 任务生成
     * @param model
     */
    @RequestMapping(value = "/sc.mo", produces = "application/json")
    public void sc(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds,String zfdxmc) {
        try {
        	Work w=workPf.savePfInfo(frm, frm.getJsrId());
        	String applyId = w.getId();//任务ID
        	TSysUser curUser = CtxUtil.getCurUser();
        	if(("24").equals(checkedNodesIds)){//日常办公
    			commWorkManager.saveTaskTypeMultiple(applyId, TaskTypeCode.RCBG.getCode(), curUser);
    		}else{
    			commWorkManager.saveDelRWLX(applyId);
    		}
        	if("0".equals(frm.getRwmcgs())){
        		if(StringUtils.isNotBlank(applyId)){
            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
            		if(StringUtils.isNotBlank(w.getZfdxType())){
            			commWorkManager.saveZfdxTypeOnChange(applyId, w.getZfdxType(),rwlxIds);
            			commWorkManager.saveZfdxType(applyId, w.getZfdxType());
            		}
            		if(StringUtils.isNotBlank(checkedNodesIds)){
            			commWorkManager.saveTaskTypeMultiple(applyId, checkedNodesIds, curUser);
            		}
        			if(StringUtils.isNotBlank(zfdxmc)){
        					lawobjMobileManager.saveChoseeLawobj(applyId, w.getZfdxType(), zfdxmc);
        			}
            	}
        	}
        	model.put("id", w.getId());
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    
    /**
     * 任务派发保存
     * @param model
     */
    @RequestMapping(value = "/pf.mo", produces = "application/json")
    public void pf(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds,String zfdxmc) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("派发表单：" + LogUtil.m(frm));
            }
            if (StringUtils.equals(frm.getXptype(), "ds")) {
                workPf.savePfXpds(frm, areaId);
            } else if(StringUtils.isNotBlank(frm.getJsrId())){
            	String [] jsrIds = frm.getJsrId().split(",");
            	TSysUser curUser = CtxUtil.getCurUser();
            	if(jsrIds.length>0){
        			Work w1 = workPf.savePf(frm, jsrIds[0], CtxUtil.getCurUser(), "zfjc");
        			if(("24").equals(checkedNodesIds)){//日常办公
	        			commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.RCBG.getCode(), curUser);
	        		}else{
	        			commWorkManager.saveDelRWLX(w1.getId());
	         		}
        			if(("0").equals(frm.getRwmcgs())){
         				if(StringUtils.isNotBlank(w1.getId())){
         					//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
         					if(StringUtils.isNotBlank(w1.getZfdxType())){
         						commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
         						commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
         					}
         					commWorkManager.saveTaskTypeMultiple(w1.getId(), checkedNodesIds, curUser);
         					lawobjMobileManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), zfdxmc);
         				}
         			 }
	                 for(int i=1;i<jsrIds.length;i++){
	            		 frm.setId("");
	            		 Work w = workPf.savePf(frm, jsrIds[i], CtxUtil.getCurUser(), "zfjc");
	            		//派发附件
	         			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
	         			if(pfFileList!=null && pfFileList.size()>0){
	         				for(int k=0;k<pfFileList.size();k++){
	         					TDataFile t= new TDataFile(w.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
	         					commonManager.saveFile(t);
	             			}
	         			}
	         			if(("24").equals(checkedNodesIds)){//日常办公
	            			commWorkManager.saveTaskTypeMultiple(w.getId(), TaskTypeCode.RCBG.getCode(), curUser);
	            		 }else{
	 	        			commWorkManager.saveDelRWLX(w.getId());
	 	         		 }
	                     if(("0").equals(frm.getRwmcgs())){
	             				if(StringUtils.isNotBlank(w.getId())){
	             					//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
	             					if(StringUtils.isNotBlank(w.getZfdxType())){
	             						commWorkManager.saveZfdxTypeOnChange(w.getId(), w.getZfdxType(),rwlxIds);
	             						commWorkManager.saveZfdxType(w.getId(), w.getZfdxType());
	             					}
	             					commWorkManager.saveTaskTypeMultiple(w.getId(), checkedNodesIds, curUser);
	             					lawobjMobileManager.saveChoseeLawobj(w.getId(), w.getZfdxType(), zfdxmc);
	             				}
	             			}
	            	}
                }
            	
            }
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 
     * 函数介绍：查询：待办任务列表。
     * 输入参数：rwmc：任务名称；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getYbrwList.mo", produces = "application/json")
    public void getYbrwList(ModelMap model, String zfdxType ,String rwmc,String rwly,String rwzt,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String tasktype, String pfrId, String zbrId, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getYbrwList(zfdxType, rwmc, rwly,rwzt, pfStarttime, pfEndtime, gdStarttime, gdEndtime, tasktype, pfrId, zbrId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 任务派发-删除
     * @param model
     */
    @RequestMapping(value = "/delpf.mo", produces = "application/json")
    public void delpf(String applyId,ModelMap model) {
    	try {
    		rwglManager.saveDelwork(applyId);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 任务重派
     * @param model
     */
    @RequestMapping(value = "/cp.mo", produces = "application/json")
    public void cp(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds, String zfdxmc) {
        try { 
        	if(StringUtils.isNotBlank(frm.getJsrId())){
        		String [] jsrIds = frm.getJsrId().split(",");
        		if(jsrIds!=null && jsrIds.length>0){
        			TSysUser curUser = CtxUtil.getCurUser();
        			Work w1 = workManager.saveCp(frm, jsrIds[0], CtxUtil.getCurUser());
        			//任务类型
        			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(frm.getId());
        			String rwlxids = "";
        			for (int i = 0; i < rwlxlistMap.size(); i++) {
        				if (i < rwlxlistMap.size() - 1) {
        					rwlxids += rwlxlistMap.get(i).get("id") + ",";
        				} else {
        					rwlxids += rwlxlistMap.get(i).get("id");
        				}
        			}
        			if("Y".equals(frm.getRcbg()) && !"24".equals(rwlxids)){//日常办公
            			commWorkManager.saveTaskTypeMultiple(frm.getId(), TaskTypeCode.RCBG.getCode(), curUser);
            		}
        			if(("0").equals(frm.getRwmcgs())){
                		if(StringUtils.isNotBlank(frm.getId())){
                    		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
                    		if(StringUtils.isNotBlank(frm.getZfdxType())){
                    			Work work = (Work) commWorkManager.get(Work.class, frm.getId());
                    			if(work!=null && !StringUtils.equals(work.getZfdxType(), frm.getZfdxType())){
                    				commWorkManager.saveZfdxTypeOnChange(frm.getId(), frm.getZfdxType(),rwlxIds);
                        			commWorkManager.saveZfdxType(frm.getId(), frm.getZfdxType());
                    			}
                    		}
                    		if(StringUtils.isNotBlank(checkedNodesIds)){
                    			if(StringUtils.isBlank(rwlxids) || !StringUtils.equals(rwlxids, checkedNodesIds)){
                    				commWorkManager.saveTaskTypeMultiple(frm.getId(), checkedNodesIds, curUser);
                    			}
                    		}
                    		String zfdx="";
                    		List<Map<String, String>> listMap = commWorkManager
                					.zfdxTableData(frm.getId());
                    		for(int i = 0; i < listMap.size(); i++){
                    			if(i<listMap.size()-1){
                    				zfdx += listMap.get(i).get("lawobjname")+',';
                    			}else{
                    				zfdx += listMap.get(i).get("lawobjname");
                    			}
                    		}
                			if(StringUtils.isNotBlank(zfdxmc) && StringUtils.equals(zfdxmc, zfdx)){
                				lawobjMobileManager.saveChoseeLawobj(frm.getId(), frm.getZfdxType(), zfdxmc);
                			}
                    	}
                	}else{
                		// 清空数据
                		// 1、执法对象
                		commWorkManager.saveDelZFDX(frm.getId());
                		// 2、任务类型
                		if(!"13".equals(rwlxids) && !"24".equals(rwlxids)){
                			commWorkManager.saveDelRWLX(frm.getId());
                		}
                		// 3、办理页面以及子任务
                		commWorkManager.saveDelBL(frm.getId());
                		// 4、执法对象类型
                		commWorkManager.saveDelZFDXLX(frm.getId());
                	}
            		for(int i=1;i<jsrIds.length;i++){
            			frm.setId("");
            			Work w2 = workManager.saveCp(frm, jsrIds[i], CtxUtil.getCurUser());
            			//派发附件
            			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
            			if(pfFileList!=null && pfFileList.size()>0){
            				for(int k=0;k<pfFileList.size();k++){
            					TDataFile t= new TDataFile(w2.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
            					commonManager.saveFile(t);
                			}
            			}
            			if(("0").equals(frm.getRwmcgs())){
                			if(StringUtils.isNotBlank(w2.getId())){
                        		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
                        		if(StringUtils.isNotBlank(w1.getZfdxType())){
                        			commWorkManager.saveZfdxTypeOnChange(w2.getId(), w2.getZfdxType(),rwlxIds);
                        			commWorkManager.saveZfdxType(w2.getId(), w2.getZfdxType());
                        		}
                        		
                        		if(StringUtils.isNotBlank(checkedNodesIds)){
                        			commWorkManager.saveTaskTypeMultiple(w2.getId(), checkedNodesIds, curUser);
                        		}
                    			if(StringUtils.isNotBlank(zfdxmc)){
                    				lawobjMobileManager.saveChoseeLawobj(frm.getId(), frm.getZfdxType(), zfdxmc);
                    			}
                        	}
                		}
                		if("Y".equals(frm.getRcbg())){//日常办公
                			commWorkManager.saveTaskTypeMultiple(w2.getId(), TaskTypeCode.RCBG.getCode(), curUser);
                		}
            		}
        		}
        	}
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 任务详情
     * @param applyId   申请单编号
     * @param model
     */
    @RequestMapping(value = "/taskDetail.mo")
    public void taskDetail(String applyId,ModelMap model) {
        try {
            Map<String, Object> jbxxMap=rwglManager.taskDetailJBXX(applyId);
            model.addAttribute("jbxxMap", jbxxMap);//基本信息map
            Map<String,Object> tuyjMap = new HashMap<String, Object>();//退回意见map
            tuyjMap=rwglManager.taskDetailRWLZJL_THYJ(applyId);
        	model.addAttribute("tuyjMap", tuyjMap);
        	TSysUser cur = CtxUtil.getCurUser();
    		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
    		List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
    		if(rczydata.size() != 0){
    			model.addAttribute("isRczy", "0");
    		}else{
    			model.addAttribute("isRczy", "");
    		}
    		if(curOrg != null){
    			if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
    				model.addAttribute("isZd", "0");
    			}else{
    				model.addAttribute("isZd", "");
    			}
    		}else{
    			model.addAttribute("isZd", "1");
    		}
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 详情页面：任务流转记录
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/taskDetailRWLZJL.mo")
    public void taskDetailRWLZJL(String applyId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            Map<String, Object> rwlzjlMap=rwglManager.taskDetailRWLZJL(applyId);
            model.addAttribute("rwlzjlMap", rwlzjlMap);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 详情页面：报告详情
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/taskDetailBGXQ.mo")
    public void taskDetailBGXQ(String applyId, String taskId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            model.addAttribute("Pid", work.getPid());
            
            Map<String, Object> bgxqMap=rwglManager.taskDetailBGXQ(applyId);
            model.addAttribute("bgxqMap", bgxqMap);
            
            //协办人员
            List<Map<String, String>> xbryMap = commWorkManager.xbryTableData(applyId);
            model.addAttribute("xbryMap", xbryMap);
            
            //执法对象
            List<Map<String, String>> zfdxMap = commWorkManager.zfdxTableData(applyId);
            model.addAttribute("zfdxMap", zfdxMap);
            
            //任务类型列表
            List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
            model.put("rwlxlistMap", rwlxlistMap);
            
            
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 
     * 函数介绍：查询：待办任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getDbrwList.mo", produces = "application/json")
    public void getDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", "", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 
     * 函数介绍：查询：现场监察任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getXcjcrwList.mo", produces = "application/json")
    public void getXcjcrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXcjcrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 
     * 函数介绍：查询：信访投诉任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getXftsrwList.mo", produces = "application/json")
    public void getXftsrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjbId, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXftsrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, xfdjbId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 任务转派
     * @param model
     */
    @RequestMapping(value = "/zp.mo", produces = "application/json")
    public void zp(String applyId, String taskId, String jsrId,String psyj, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
        	workManager.saveZp(applyId, taskId, jsrId, psyj, cur);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 任务接受
     * @param model
     */
    @RequestMapping(value = "/js.mo", produces = "application/json")
    public void js(String applyId, String taskId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveJs(applyId, taskId, cur);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 任务下派
     * @param model
     */
    @RequestMapping(value = "/xp.mo", produces = "application/json")
    public void xp(String applyId, String taskId, String areaId,String psyj,String xpfjIds,String xplspsIds, String jsrId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
    		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
        	if(StringUtils.isNotBlank(jsrId)){
				TSysUser xfzy = userManager.getUser(jsrId);
				//先进行任务的派发工作进入流程，然后执行下派的操作
				//workManager.saveZpXpds(applyId, taskId, xfzy.getAreaId(),psyj,xpfjIds,xplspsIds);
				//上面先进行正常的下派到区域负责人，然后再执行转派给选择的接收人（方便后续的流程中审核人添加）
				//Work work = workManager.getXpRw(applyId);
				//workManager.saveZp(work.getId(), taskId, jsrId, psyj, cur);
	    		if(StringUtils.isNotBlank(applyId)){
        			if(StringUtils.isNotBlank(xfzy.getAreaId())){
        				Work w1 = workManager.get(applyId);
    					String taskIds = xfdjManager.gettaskId(w1.getProcessId());
    					if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
    						//先按正常的下派，审核添加上区域负责人
    						workManager.saveZpXpds(w1.getId(), taskIds, xfzy.getAreaId(),w1.getPsyj(),xpfjIds,xplspsIds,jsrId);
    						//查出来产生的地市任务
    						Work w2 = workManager.getXpRw(applyId);
    						String taskIdss = xfdjManager.gettaskId(w2.getProcessId());
    						//获取下派地市的支队长
    						//String zdz = w2.getJsr();
    						TSysUser pfr = userManager.getLeaderByAreaId(xfzy.getAreaId());
    						//通过负责人转派给总队选的专员
    						workManager.saveZp(w2.getId(), taskIdss, jsrId, w1.getPsyj(), pfr);
                    	}else{
                    		//专员通过下派的页面实现同区域的转派
                        	workManager.saveZp(w1.getId(), taskIds, jsrId, w1.getPsyj(), cur);
                        }
    				}
	            }
        	}else{
        		if (StringUtils.isNotBlank(areaId)) {
                    workManager.saveZpXpds(applyId, taskId, areaId,psyj,xpfjIds,xplspsIds,"");
                }
        	}
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 任务报告
     * @param model
     */
    @RequestMapping(value = "/bg.mo", produces = "application/json")
    public void bg(String applyId, String taskId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveZx(applyId, taskId, cur);
            //任务类型
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
			String rwlxIds = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxIds += rwlxlistMap.get(i).get("id") + ",";
				} else {
					rwlxIds += rwlxlistMap.get(i).get("id");
				}
			}
			model.addAttribute("rwlxIds", rwlxIds);
			if(!"24".equals(rwlxIds)){	//非日常办公任务
	            //若附件列表中有处理意见书则自动派发后督察任务到主办人员的待办列表中
	            rwglManager.saveHdcTask(applyId);
			}
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 审核操作
     * @param applyId
     * @param taskId
     * @param passed
     * @param model
     */
    @RequestMapping(value = "/sh.mo", produces = "application/json")
    public void sh(String applyId, String taskId, Boolean passed, String opinion, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveSh(applyId, taskId, passed, opinion, cur);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 退回操作
     * @param applyId
     * @param taskId
     * @param opinion
     * @param model
     */
    @RequestMapping(value = "/th.mo", produces = "application/json")
    public void th(String applyId, String taskId, String opinion, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveTh(applyId, taskId, opinion, cur);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 退回
     * @param applyId
     * @param taskId
     * @param model
     */
    @RequestMapping(value = "/report_th.mo", produces = "application/json")
    public void report_th(String applyId, String taskId, String opinion, ModelMap model) {
        try {
            workReportManager.saveTh(applyId, taskId,opinion);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /////////////////////////////////////任务办理-准备start/////////////////////////////////////////
    /**
     * 通用任务的执行界面-step1：准备
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/commworkzxZB.mo")
    public void commworkzxZB(String applyId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            //登记人
            if(StringUtils.isNotBlank(work.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
    			work.setDjrName(djrObj.getName());
    		}else{
    			work.setDjrName("");
    		}
            
            ////封装数据///
        	Map<String,String> workMap = new HashMap<String, String>();//任务信息map
        	workMap.put("id", work.getId());//任务id
        	workMap.put("name", work.getName());//任务名称
        	workMap.put("workNote", work.getWorkNote());//主要内容
        	workMap.put("djrName", work.getDjrName());//登记人
        	workMap.put("endTime", DateUtil.getDateTime("yyyy-MM-dd", work.getEndTime()));//要求完成时限
        	workMap.put("zfdxType", work.getZfdxType());//执法对象类型
        	//String zfdxTypeName = commonManager.getDicNameByTypeAndCode(DicTypeEnum.LAWTYPE.getCode(),work.getZfdxType()==null?"":work.getZfdxType());
        	TDataLawobjtype lawobjtype=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, work.getZfdxType()==null?"":work.getZfdxType());
        	workMap.put("zfdxTypeName", lawobjtype.getName());//执法对象类型name
        	//workMap.put("zfdxTypeName", zfdxTypeName);//执法对象类型name
        	
        	//工作流中用到的taskId
            String taskId=rwglManager.getTaskIdByWorkId(work.getId());
            workMap.put("taskId", taskId);//执法对象类型name
            workMap.put("pId", work.getPid());//pid
        	
            model.addAttribute("workMap", workMap);
            
            //任务类型列表
            List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
            model.put("rwlxlistMap", rwlxlistMap);
            String rwlxIds="";
            String rwlxNames="";
            for(int i=0;i<rwlxlistMap.size();i++){
            	if(i<rwlxlistMap.size()-1){
            		rwlxIds+=rwlxlistMap.get(i).get("id")+",";
            		rwlxNames+=rwlxlistMap.get(i).get("name")+",";
            	}else{
            		rwlxIds+=rwlxlistMap.get(i).get("id");
            		rwlxNames+=rwlxlistMap.get(i).get("name");
            	}
            }
            model.put("rwlxIds", rwlxIds);
            model.put("rwlxNames", rwlxNames);
            
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 
     * 函数介绍：执法对象table（准备）
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/zfdxTable.mo", produces = "application/json")
    public void zfdxTable(String applyId,ModelMap model) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, String>> listMap = commWorkManager.zfdxTableData(applyId);
            map.put("total", listMap.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
	 * 
	 * 函数介绍：任务类型下拉树（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	 * 输入参数：无
	 * 返回值：
	 */
    @RequestMapping(value = "/taskTypeTreeComboByTaskId.mo", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx,String zfdx, ModelMap model) {
    	if(StringUtils.isNotBlank(zfdx)){
    		String[]  a = zfdx.split(",");
    		if(a.length > 1){
    			zfdxlx = "zxbs";
    		}
    	}
		return commWorkManager.taskTypeTreeComboByTaskId(applyId, zfdxlx);
	}
    
    /**
	 * 
	 * 函数介绍：任务类型下拉树过滤掉信访投诉（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
    @RequestMapping(value = "/taskTypeTreeComboByTaskIdWithoutXf.mo", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx,String zfdx, ModelMap model) {
    	if(StringUtils.isNotBlank(zfdx)){
    		String[]  a = zfdx.split(",");
    		if(a.length > 1){
    			zfdxlx = "zxbs";
    		}
    	}
		return commWorkManager.taskTypeTreeComboByTaskIdWithoutXf(applyId, zfdxlx);
	}
    
    /**
     * 执法对象类型保存
     * @param model
     */
    @RequestMapping(value = "/zfdxTypeSave.mo", produces = "application/json")
    public void zfdxTypeSave(String applyId,String zfdxType,String rwlxIds,ModelMap model) {
    	try {
    		commWorkManager.saveZfdxTypeOnChange(applyId,zfdxType,rwlxIds);
    		commWorkManager.saveZfdxType(applyId,zfdxType);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存执法对象类型错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * 保存任务类型
     * @param model
     */
    @RequestMapping(value = "/taskTypeSaveMultiple.mo", produces = "application/json")
    public void taskTypeSaveMultiple(String applyId,String checkedNodesIds,ModelMap model) {
    	try {
    		TSysUser curUser = CtxUtil.getCurUser();
    		commWorkManager.saveTaskTypeMultiple(applyId,checkedNodesIds,curUser);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存执法对象类型错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * 
     * 函数介绍：人员规划table（准备）
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/ryghTable.mo", produces = "application/json")
    public void ryghTable(String applyId,ModelMap model) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, String>> listMap = commWorkManager.ryghTableData(applyId);
            map.put("total", listMap.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 人员保存(多个)
     * @param model
     */
    @RequestMapping(value = "/rySaveMul.mo", produces = "application/json")
    public void rySaveMul(String applyId,String ryid,String type,ModelMap model) {
    	try {
    		commWorkManager.saveRyMulXbrForMobile(applyId,ryid,type);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存人员错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /////////////////////////////////////任务办理-准备end/////////////////////////////////////////
    
    /////////////////////////////////////任务办理-办理start/////////////////////////////////////////
    /**
     * 通用任务的执行界面-step2：办理
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/commworkzxBL.mo")
    public void commworkzxBL(String applyId, String taskId, String oper, ModelMap model,BlMainForm blMainForm) {
        try {
            blMainForm=commWorkManager.getBlMainFormData(applyId);
            model.addAttribute("blMainForm", blMainForm);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 保存办理
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveWorkzxBL.mo")
    public void saveWorkzxBL(String applyId,ModelMap model,BlMainForm blMainForm){
    	try {
    		commWorkManager.saveWorkzxBL(applyId,blMainForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 询问笔录
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/xwbl.mo")
    public void xwbl(String applyId,String wflx, ModelMap model,XwblForm xwblForm) {
        try {
        	Work work = workManager.get(applyId);
//            model.addAttribute("work", work);
            xwblForm=xwblManager.getXwblFormData(applyId,wflx);
            
            model.addAttribute("wflx", wflx);
            
            ArrayList<HashMap<String, String>> morekcxwblwtlist = xwblManager.getMoreKcxwblWtList(applyId, "1");
            
            if (morekcxwblwtlist.size() == 0){
            	morekcxwblwtlist = xwblManager.getBlwt(applyId, wflx);
            }
            model.addAttribute("kcxwblwtlist", morekcxwblwtlist);
            
            String title = "法定代表人（负责人）：";
            if (work.getZfdxType().equals("06")){
            	title = "经营者";
            }
            model.addAttribute("title", title);
            
            model.addAttribute("xwblForm", xwblForm);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 保存询问笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveXwbl.mo", produces = "application/json")
    public void saveXwbl(String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel, ModelMap model, XwblForm xwblForm) {
    	try {
    		//保存询问笔录及问题项
    		xwblManager.saveXwbl(xwblForm,applyId,wflx,ids,content, danr, wttype, isdel);
    		//生成询问笔录doc文件
    		xwblManager.saveShengchengXwbl(applyId, wflx);
    		
    		Map<String, String> xwblFileMap = null;
    		FyWebResult re = commonMobileManager.queryFileList(applyId, FileTypeEnums.WFAJDCXWBL.getCode(), "1", null);
    		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
    		rowsList=re.getRows();
    		if(rowsList.size()>0){
    			xwblFileMap=rowsList.get(0);
    		}
    		model.addAttribute("xwblFileMap", xwblFileMap);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * 删除勘察笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/delRecord.mo", produces = "application/json")
    public void delKcbl(String applyId, String wflx,ModelMap model) {
    	try {
    		
    		String wflxs = kcblManager.getWflx(applyId);
    		
    		if(StringUtils.isNotBlank(wflxs) && StringUtils.isNotBlank(wflx)){
    			//比较该任务的违法案件类型及前端页面变化后的违法案件类型，若不相同，删除勘察询问笔录问题记录
    			if(!wflxs.contains(wflx)){
    				//删除勘察询问笔录问题记录
        			kcblManager.delKcbl(applyId);
    			}
    		}
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * 勘察笔录
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/kcbl.mo")
    public void kcbl(String applyId,String wflx, ModelMap model,KcblForm kcblForm) {
        try {
//            Work work = workManager.get(applyId);
//            model.addAttribute("work", work);
            kcblForm=kcblManager.getKcblFormData(applyId);
            model.addAttribute("kcblForm", kcblForm);
            
            model.addAttribute("wflx", wflx);
            
            ArrayList<HashMap<String, String>> morekcxwblwtlist = xwblManager.getMoreKcxwblWtList(applyId, "0");
            
            if (morekcxwblwtlist.size() == 0){
            	morekcxwblwtlist = kcblManager.getBlwt(applyId, wflx);
            }
            model.addAttribute("kcxwblwtlist", morekcxwblwtlist);
            
           /* //问题列表
            List kcxwblwtlist = kcblManager.getKcxwblWtList(applyId,TaskTypeCode.WFAJ.getCode(), wflx, "0");
            model.addAttribute("kcxwblwtlist", kcxwblwtlist);
            
            //追加问题列表
            List morekcxwblwtlist = kcblManager.getMoreKcxwblWtList(applyId, "0");
            model.addAttribute("morekcxwblwtlist", morekcxwblwtlist);*/
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 保存勘察笔录
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveKcbl.mo", produces = "application/json")
    public void saveKcbl(String applyId, String wflx, String[] ids, String[] content, String[] danr, String[] wttype, String[] isdel, ModelMap model,KcblForm kcblForm) {
    	try {
    		//保存勘察笔录及问题项
    		kcblManager.saveKcbl(kcblForm, applyId, wflx, ids, content, danr, wttype, isdel);
    		//生成询问笔录doc文件
    		kcblManager.saveShengchengKcbl(applyId, wflx);
    		
    		Map<String, String> kcblFileMap = null;
    		FyWebResult re = commonMobileManager.queryFileList(applyId, FileTypeEnums.WFAJDCKCBL.getCode(), "1", null);
    		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
    		rowsList=re.getRows();
    		if(rowsList.size()>0){
    			kcblFileMap=rowsList.get(0);
    		}
    		model.addAttribute("kcblFileMap", kcblFileMap);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 保存修改问题
     * @param model
     */
    @RequestMapping(value = "/saveWT.mo", produces = "application/json")
    public void saveWT(String applyId,String wtid,String wtcontent, ModelMap model) {
        try {
        	String id=xwblManager.saveWT(applyId,wtid,wtcontent);
        	
        	model.put("id", id);
            JsonResultUtil.suncess(model, "操作成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryBlFileTypeCombo.mo", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo) {
		return commWorkManager.queryBlFileTypeCombo(rwlx,zfdxInfo);
	}
	
	/**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表（专项行动子任务）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryBlFileTypeComboZXXDZRW.mo", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeComboZXXDZRW() {
		return commWorkManager.queryBlFileTypeComboZXXDZRW();
	}
	
	/**
	 * 生成j监察报告并下载
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param subdata
	 */
	@RequestMapping(value = "buildJcbg.mo", produces = "application/json")
	public void buildJcbg(ModelMap model, String applyId, String taskType, String jcmbId, String jcjl,String fileid) {
		try {
			jcdManager.buildJcbl(applyId, taskType, jcmbId, jcjl,fileid);
			
			Map<String, String> commonFileMap = null;
			
			String newTaskType="";
			if("15".equals(taskType)){//专项子任务的单独处理
				newTaskType=FileTypeEnums.ZXXDZRWJCBG.getCode();
			}else{
				newTaskType=taskType + "02";
			}
			
    		FyWebResult re = commonMobileManager.queryFileList(applyId, newTaskType, "1", null);
    		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
    		rowsList=re.getRows();
    		if(rowsList.size()>0){
    			commonFileMap=rowsList.get(0);
    		}
    		model.addAttribute("commonFileMap", commonFileMap);
			
			JsonResultUtil.suncess(model, "生成成功。");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.suncess(model, "生成失败。");
		}
	}
    
    /////////////////////////////////////任务办理-办理end/////////////////////////////////////////
	
    /////////////////////////////////////任务审核start/////////////////////////////////////////
	/**
     * 审核页面操作
     * @param applyId   申请单编号
     * @param taskId    任务编号
     * @param model
     */
    @RequestMapping(value = "/getShPageOper.mo")
    public void getShPageOper(String applyId, ModelMap model) {
        try {
            List<WorkSh> acs = workManager.getShActions(applyId);
            model.addAttribute("acs", acs);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /////////////////////////////////////任务审核end/////////////////////////////////////////
    
    
    /////////////////////////////////////办理后台校验start/////////////////////////////////////////
    /**
     * 验证"准备"【true、通过；false、不通过】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/checkBlZB.mo", produces = "application/json")
    public void checkBlZB(String applyId, ModelMap model) {
        try {
            ResultBean rb = commWorkManager.checkBlZB(applyId);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    /**
     * 验证"办理"【true、通过；false、不通过】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/checkBlBL.mo", produces = "application/json")
    public void checkBlBL(String applyId, String zfdxInfo, ModelMap model) {
        try {
            ResultBean rb = commWorkManager.checkBlBL(applyId,zfdxInfo);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    /**
     * 验证办理页面跳转【true、专项；false、其他】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/showBlPage.mo", produces = "application/json")
    public void showBlPage(String applyId, ModelMap model) {
        try {
            ResultBean rb = commWorkManager.showBlPage(applyId);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    /////////////////////////////////////办理后台校验end/////////////////////////////////////////
    
    
    /////////////////////////////////////专项办理start/////////////////////////////////////////
    /**
     * 专项任务的执行界面-step2：办理
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxworkzxBL.mo")
    public void zxworkzxBL(String applyId, ModelMap model,BlZxxdForm blZxxdForm) {
        try {
            blZxxdForm=zxWorkManager.getBlZxxdFormData(applyId);
            model.addAttribute("blZxxdForm", blZxxdForm);
            
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, String>> listMap = zxWorkManager.zxZfdxTableData(applyId);
            map.put("total", listMap.size());
            map.put("rows", listMap);
            JsonResultUtil.show(model, map);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 保存办理
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxWorkzxBL.mo")
    public void saveZxWorkzxBL(String applyId,ModelMap model,BlZxxdForm blZxxdForm){
    	try {
    		zxWorkManager.saveZxWorkzxBL(applyId,blZxxdForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 专项任务分工页面
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxrwfg.mo")
    public void zxrwfg(String applyId, ModelMap model) {
        try {
        	Work work = workManager.get(applyId);
        	Map<String,String> workMap = new HashMap<String, String>();//任务信息map
        	workMap.put("id", work.getId());//任务id
        	workMap.put("startTime", DateUtil.getDateTime("yyyy-MM-dd", work.getStartTime()));//开始时间
        	workMap.put("endTime", DateUtil.getDateTime("yyyy-MM-dd", work.getEndTime()));//要求完成时限
        	model.addAttribute("workMap", workMap);
        	
            List<Map<String, String>> listMap = zxWorkManager.zxZfdxTableData_wfp(applyId);
            model.addAttribute("listMap", listMap);
            
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 分派任务
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxrwfg.mo")
    public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx,ModelMap model){
    	try {
    		zxWorkManager.saveZxrwfg(applyId,zfdxid,zbry,yqwcsx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存分派任务错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 专项子任务的执行界面：办理
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/zxzrw_zxPage.mo")
    public void zxzrw_zxPage(String applyId, String taskId, ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm) {
        try {
            
            blZxxdZrwMainForm=zxWorkManager.getBlZxxdZrwMainFormData(applyId);
            model.addAttribute("blZxxdZrwMainForm", blZxxdZrwMainForm);
            
        } catch (Exception e) {
            log.error("", e);
        }
    }
    /**
     * 保存办理
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPage.mo")
    public void saveZxzrw_zxPage(String applyId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    /**
     * 办理完毕
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveZxzrw_zxPageBlwb.mo")
    public void saveZxzrw_zxPageBlwb(String applyId,String taskId,ModelMap model,BlZxxdZrwMainForm blZxxdZrwMainForm){
    	try {
    		zxWorkManager.saveZxzrw_zxPage(applyId,blZxxdZrwMainForm);
    		zxWorkManager.saveZxzrw_zxPageBlwb(applyId,taskId);
    		
    		//专项子任务办理完毕附件打个zip包，分别保存两份到子任务、父任务附件中去。
    		zxWorkManager.saveZxzrw_zip(applyId);
    		
    		//若附件列表中有处理意见书则自动派发后督察任务到主办人员的待办列表中
            rwglManager.saveHdcTask(applyId);
    		
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    /////////////////////////////////////专项办理start/////////////////////////////////////////
    /**
     * 任务派发默认值
     * @param 
     * @param model
     */
    @RequestMapping(value = "/pfPage.mo")
    public void pfPage(ModelMap model) {
        try {
            ////封装数据///
        	Map<String,String> workMap = new HashMap<String, String>();//任务信息map
        	workMap.put("djrId", CtxUtil.getCurUser().getId());//登记人id
        	workMap.put("djrName", CtxUtil.getCurUser().getName());//登记人name
        	Calendar endC = Calendar.getInstance();
            endC.add(Calendar.DATE, 20);//默认紧急程度一般20天
        	workMap.put("endTime", DateUtil.getDateTime("yyyy-MM-dd", endC.getTime()));//要求完成时限
        	workMap.put("source", "8");//任务来源
        	workMap.put("sourceName", "现场检查");//任务来源name
        	workMap.put("security", "1");//任务密级
        	workMap.put("securityName", "不涉密");//任务密级name
        	workMap.put("emergency", "1");//紧急程度
        	workMap.put("emergencyName", "一般");//紧急程度name
        	workMap.put("pfrId", CtxUtil.getCurUser().getId());//派发人id
        	workMap.put("pfrName", CtxUtil.getCurUser().getName());//派发人name
        	
        	model.addAttribute("workMap", workMap);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
    /**
     * 验证子任务"办理"【true、通过；false、不通过】
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/checkZxZrwBlBL.mo", produces = "application/json")
    public void checkZxZrwBlBL(String applyId, ModelMap model) {
        try {
            ResultBean rb = zxWorkManager.checkZxZrwBlBL(applyId);
            model.addAttribute("state", rb.getResult());
            model.addAttribute("msg", rb.getMsg());
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 开始办理状态改为“办理中”
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveRwzxStart.mo", produces = "application/json")
    public void saveRwzxStart(String applyId, ModelMap model) {
    	try {
    		rwglManager.saveRwzxStart(applyId);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 创建任务上报文件
     * @param workId
     * @param model  ids上报附件ids
     */
    @RequestMapping(value = "/create_report.mo", produces = "application/json")
    public void createReport(String applyId, String taskId, String opinion,String ids, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            jobManager.saveJob(JobType.UPLOAD_FILE, applyId);
            
            //处理上报任务类型保存+附件保存+上报批示信息
            workManager.saveSbRwlxAndFile(applyId,ids,opinion);
            
            workManager.saveSh(applyId, taskId, Boolean.TRUE, opinion, cur);
            JsonResultUtil.suncess(model, "操作成功");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 获取执法证号
     * @param model
     */
    @RequestMapping(value = "/getLwnumber.mo", produces = "application/json")
    public void getLwnumber(String userIds,ModelMap model) {
    	try {
            String lwnumbers = kcblManager.getLwnumber(userIds);
            model.put("lwnumbers", lwnumbers);
		} catch (Exception e) {
			log.error("查询执法证号错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }

    /**
	 * 保存监察结论
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param jcjl
	 */
    @RequestMapping(value = "/jcjl.mo", produces = "application/json")
    public void jcjl(ModelMap model, String taskid, String tasktype) {
        try {
            String jcjl = commWorkManager.getJcjl(taskid, tasktype);
            model.addAttribute("jcjl", StringUtil.isBlank(jcjl) ? "" : jcjl);
            JsonResultUtil.suncess(model, "查询成功");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    /**
     * 查询日常办公备注
     * @param model
     * @param taskId
     * @param tasktype
     * @param desc
     */
    @RequestMapping(value = "/queryDesc.mo", produces = "application/json")
    public void queryDesc(ModelMap model, String taskId, String tasktype) {
    	try {
    		String desc = commWorkManager.getRcbgDesc(taskId, tasktype);
    		model.addAttribute("desc", StringUtil.isBlank(desc) ? "" : desc);
    		JsonResultUtil.suncess(model, "查询成功");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	/**
	 * 保存监察结论
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param jcjl
	 */
    @RequestMapping(value = "/saveJcjl.mo", produces = "application/json")
    public void saveJcjl(ModelMap model, String taskid, String tasktype, String jcjl) {
        try {
            commWorkManager.saveJcjl(taskid, tasktype, jcjl);
            JsonResultUtil.suncess(model, "保存成功。");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }
    /**
     * 保存备注
     * @param model
     * @param taskId
     * @param tasktype
     * @param desc
     */
    @RequestMapping(value = "/saveDesc.mo", produces = "application/json")
    public void saveDesc(ModelMap model, String taskId, String tasktype, String desc) {
    	try {
    		commWorkManager.saveRcbg(taskId, tasktype, desc);
    		JsonResultUtil.suncess(model, "保存成功。");
    	} catch (Exception e) {
    		log.error("", e);
    		JsonResultUtil.fail(model, e.getMessage());
    	}
    }
    
    /**
	 * 
	 * 函数介绍：生成模板
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/saveDownJcbgmb.mo")
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType, HttpServletResponse res) {
		commWorkManager.saveDownJcbgmb(jcmbId, applyId, taskType, res);
	}
	
	/**
	 * 获取在线制作的附件接口
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getZxzzFile.mo", produces = "application/json")
	public void getKcxwblFile(ModelMap model, String applyId, String canDel, String fileType, String page, String rows) {
		try {
			FyWebResult kcxwblFileMap = null;
			kcxwblFileMap = zxzzManager.queryZxzzFileList(applyId, canDel, fileType, page, rows);
			//model.put("id", kcxwblFileMap.getRows().get(0).get("id"));
			//model.put("filename", kcxwblFileMap.getRows().get(0).get("filename"));
			//model.put("url", kcxwblFileMap.getRows().get(0).get("url"));
			model.put("kcxwblFileMap", kcxwblFileMap);
		} catch (Exception e) {
			log.error("查询勘察询问笔录错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 监察笔录附件信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getJcblFile.mo", produces = "application/json")
	public void getJcblFile(String applyId, String fileTypeEnumName,String moreCheckFiletype,String tasktypeCode,ModelMap model) {
		try {
			List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
			if(StringUtils.equals(tasktypeCode, "10")){//现场检查
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "RCJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "RCJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.RCJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "RCJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "RCJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "11")){//年度核查
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "NDHCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "NDHCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.NDHCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "NDHCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "NDHCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "12")){//后督察
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "HDCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "HDCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.HDCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "HDCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "HDCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "14")){//排污许可证检查
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "PWXKZJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "PWXKZJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.PWXKZJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "PWXKZJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "PWXKZJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "15")){//专项行动
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZXXDZRWJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(0).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.ZXXDZRWMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(k).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "17")){//限期治理
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "XQZLJCJL");
					rowsList.get(0).put("moreCheckFiletype", "XQZLMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.XQZLMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "XQZLJCJL");
					rowsList.get(k).put("moreCheckFiletype", "XQZLMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "18")){//跟踪检查
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "GZJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "GZJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.GZJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "GZJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "GZJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "20")){//危险废物
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "WXFWJCJL");
					rowsList.get(0).put("moreCheckFiletype", "WXFWMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.WXFWMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "WXFWJCJL");
					rowsList.get(k).put("moreCheckFiletype", "WXFWMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "22")){//辐射安全
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "FSAQJCJL");
					rowsList.get(0).put("moreCheckFiletype", "FSAQMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.FSAQMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "FSAQJCJL");
					rowsList.get(k).put("moreCheckFiletype", "FSAQMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "23")){//污染事故现场调查
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "WRSGXCDCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "WRSGXCDCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.WRSGXCDCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "WRSGXCDCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "WRSGXCDCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}
			model.put("jcjlListFileMap", jcjlFileMap);
		} catch (Exception e) {
			log.error("查询附件错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 生成再次检查监察情况
	 * @param model
	 * @param applyId
	 * @param jcmbId
	 * @param jcqk
	 */
	@RequestMapping(value = "/buildJcbl.mo", produces = "application/json")
	public void buildJcbl(ModelMap model, String applyId, String taskType, String jcmbId, String jcqk,String fileid) {
		try {
			jcdManager.buildJcbl(applyId, taskType, jcmbId, jcqk,fileid);
			JsonResultUtil.suncess(model, "生成成功。");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			JsonResultUtil.fail(model, "生成失败。");
		}
	}
	
	/**
	 * 多次检查时通过附件id得到监察情况
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getJcqk.mo", produces = "application/json")
	public void getJcqk(String fileId,ModelMap model) {
		try {
			if(commWorkManager.getMoreCheck(fileId)!=null){
				model.put("jcqk",commWorkManager.getMoreCheck(fileId).getContent());
			}else{
				model.put("jcqk","");
			}
		} catch (Exception e) {
			log.error("查询附件错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 合并文档的功能（对多个）
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getCopyFiles.mo", produces = "application/json")
	public void saveCopyFile(String applyId, String bllx, ModelMap model) {
		try {
			zxzzManager.saveCopyFile(applyId, bllx);
			JsonResultUtil.suncess(model, "合并成功！");
		} catch (Exception e) {
			log.error("合并文档失败：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 获取待办任务数量的接口
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getTaskCount.mo", produces = "application/json")
	public void getTaskCount(ModelMap model) {
		try {
			int a = rwglManager.getDbrwCount(CtxUtil.getCurUser());
			model.put("dbnum", a);
			JsonResultUtil.suncess(model, "查询成功");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * 获取环境违法行为限期治理的接口
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/gztzList.mo", produces = "application/json")
	public void getgztzList(ModelMap model,String taskId,String taskTypeId) {
		try {
		    	HjwfxwtzForm hjwfxwtzForm=new HjwfxwtzForm();
	    		BlMainForm bm=commWorkManager.getBlMainFormData(taskId);
		    	hjwfxwtzForm.setCorpName(bm.getBlZfdxForm().getLawobjname());
				TSysOrg o = wszzManager.getOrgbyUser(CtxUtil.getUserId());
				hjwfxwtzForm.setTaskId(taskId);
				hjwfxwtzForm.setTaskTypeId(taskTypeId);
				hjwfxwtzForm.setTitle(o.getUnitname());	
				model.addAttribute("hjwfxwtzForm", hjwfxwtzForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 获取行政处罚决定书送达回执的接口
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/jdssdhzAdd.mo", produces = "application/json")
	public void jdssdhzAdd(ModelMap model,String taskId,String taskTypeId, String sysVer) {
		try {
			 	Calendar a=Calendar.getInstance();
				JdssdhzForm jdssdhzForm= new JdssdhzForm();
				TSysOrg o = wszzManager.getOrgbyUser(CtxUtil.getUserId());
				jdssdhzForm.setTaskId(taskId);
				jdssdhzForm.setTaskTypeId(taskTypeId);
				jdssdhzForm.setTimeName(String.valueOf(a.get(Calendar.YEAR)));
				TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
				jdssdhzForm.setTitle(ta.getUnitname());
				model.addAttribute("jdssdhzForm", jdssdhzForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 获取（听证）告知书送达回证的接口
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/tzgzssdhzAdd.mo", produces = "application/json")
	public void tzgzssdhzAdd(ModelMap model,String taskId,String taskTypeId) {
		try {
				Calendar a=Calendar.getInstance();
				TzgzssdhzForm tzgzssdhzForm=new TzgzssdhzForm();
				tzgzssdhzForm=new TzgzssdhzForm();
				TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
				tzgzssdhzForm.setTaskId(taskId);
				tzgzssdhzForm.setTaskTypeId(taskTypeId);
				tzgzssdhzForm.setTimeName(String.valueOf(a.get(Calendar.YEAR)));
				TSysArea ta=areaManager.getAreaByUser(CtxUtil.getUserId());
				tzgzssdhzForm.setTitle(ta.getUnitname());	
				model.addAttribute("tzgzssdhzForm", tzgzssdhzForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
     * 修改环境违法行为限期改正通知单
     * @param model
     */
    @RequestMapping(value = "/editHjwfxwtzForm.mo", produces = "application/json")
    public void saveHjwfxwtzForm(ModelMap model,HjwfxwtzForm hjwfxwtzForm,String Androw) {
        try {
        	wszzManager.saveHjwfxwtzFormById(hjwfxwtzForm);
			hjwfxwtzForm=wszzManager.findHjwfxwtzFormById(hjwfxwtzForm.getTaskId(), hjwfxwtzForm.getTaskTypeId());
			wszzManager.buildTzd(hjwfxwtzForm);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "保存成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
	
    /**
     * （听证）告知书
     * @param model
     */
    @RequestMapping(value = "/editTzgzssdhzForm.mo", produces = "application/json")
    public void saveTzgzssdhzForm(ModelMap model,TzgzssdhzForm tzgzssdhzForm,String Androw) {
        try {
        	wszzManager.saveTzgzssdhzFormById(tzgzssdhzForm);
			TzgzssdhzForm jf=wszzManager.findTzgzssdhzFormById(tzgzssdhzForm.getTaskId(), tzgzssdhzForm.getTaskTypeId());
			wszzManager.buildTzgzssdhz(jf);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "保存成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 修改行政处罚决定书送达回执
     * @param model
     */
    @RequestMapping(value = "/editJdssdhzForm.mo", produces = "application/json")
    public void saveJdssdhzForm(ModelMap model,JdssdhzForm jdssdhzForm,String Androw) {
        try {
        	wszzManager.saveJdssdhzFormById(jdssdhzForm);
			JdssdhzForm jf=wszzManager.findJdssdhzFormById(jdssdhzForm.getTaskId(), jdssdhzForm.getTaskTypeId());
			wszzManager.buildJdssdhz(jf);
			model.addAttribute("androw", Androw);
			JsonResultUtil.suncess(model, "保存成功！");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "操作失败！");
        }
    }
    
    /**
     * 保存任务延期
     * @param model
     */
    @RequestMapping(value = "/rwyq.mo", produces = "application/json")
    public void rwyq(ModelMap model, WorkDto frm) {
        try {
        	Work work = workDao.get(frm.getId());
        	Work xpWork = rwglManager.getXpWork(work.getId());//任务的下派任务
        	Date end = DateUtil.getEndDatetime(frm.getEndTime());
            work.setEndTime(end);//要求完成时间
            if(xpWork!=null){
            	xpWork.setEndTime(end);
            	rwglManager.save(xpWork);
            }
            rwglManager.save(work);
            JsonResultUtil.suncess(model, "保存成功！");
        	
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "保存失败！");
        }
    }
    
    
}
