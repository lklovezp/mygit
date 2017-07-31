/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.work.xfdj;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.app.work.zx.XfbjdForm;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;
import com.hnjz.sys.user.UserManager;

/**
 * 信访登记的Controller
 * @author shiqiuhan
 * @created 2016-3-31,上午08:42:26
 */
@Controller
public class XfdjController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(XfdjController.class);

	@Autowired
	private XfdjManager xfdjManager;
	
	@Autowired
	private OrgManager orgManager;
	
	@Autowired
	private DicManager dicManager;
	
	/**任务派发**/
    @Autowired
    private WorkPf           workPf;
    
    @Autowired
	private CommWorkManager commWorkManager;
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    protected WorkManagerImpl workManager;
    
    @Autowired
    private RwglManager    rwglManager;
    
    @Autowired
	private UserManager userManager;
    
    @Autowired
	private Dao dao;

	/**
	 * 跳转到查询结果页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/xfdjList.htm")
	public String xfdjList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/work/xfdj/xfdjList";
	}
	
	/**
	 * 跳转到查询信访反馈列表页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/xffkList.htm")
	public String xffkList(ModelMap model, String title) {
		model.addAttribute("title", title);
		return "app/work/xfdj/xffkList";
	}
	
	/**
	 * 跳转到查询信访回访页面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 查询结果页面
	 */
	@RequestMapping(value = "/xfhf.htm")
	public String xfhf(ModelMap model, String title, XfbjdForm xfbjdForm,String xfdjId) {
		model.addAttribute("title", "信访回访");
		xfbjdForm = xfdjManager.getXfbjdformByxfdjId(xfdjId);
		model.addAttribute("xfdjId", xfdjId);
		model.addAttribute("xfbjdForm", xfbjdForm);
		return "app/work/xfdj/xfhf";
	}
	
	/**
	 * 判断是否可以回访
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return
	 */
	@RequestMapping(value = "/isShowHf.json")
	public void isShowHf(String xfdjId, ModelMap model) {
		try {
			model.put("isShowHf",xfdjManager.isShowHf(xfdjId));
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("显示回访信息错误！", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}
	
	/**
	 * 保存回访信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return
	 */
	@RequestMapping(value = "/saveHfInfo.json")
	public void saveHfInfo(String xfdjId, ModelMap model,
			XfbjdForm xfbjdForm) {
		try {
			xfdjManager.saveHfInfo(xfbjdForm,xfdjId);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存回访信息错误！", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}
	
	/**
	 * 查询信访登记列表
	 * @param model
	 * @param xfly
	 * @param xfsj1
	 * @param xfsj2
	 * @param zt
	 * @param page
	 * @param pageSize
	 */
	@RequestMapping(value = "/xfdjQuery.json", produces = "application/json")
	public void xfdjQuery(ModelMap model, String xfly, String xfsj1, String xfsj2, String zt, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = xfdjManager.queryXfdj(xfly, xfsj1,xfsj2,zt,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}

	/**
	 * 跳转到信访登记添加修改界面
	 * @param model
	 * @param frm
	 * @return
	 */
	@RequestMapping(value = "/xfdjAdd.htm")
	public String xfdjAdd(ModelMap model, XfdjForm frm) {
		//标题
		String title = "";
		TSysUser cur = CtxUtil.getCurUser();
		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
		/*List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
		if(xfzydata.size() != 0){
			model.put("isXfzy", "0");
		}*/
		if(curOrg != null){
			if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
				model.put("isZd", "0");
			}
		}
		// 标题中xx环境监察支队 可能是当前用户所在部门的名称
		if(curOrg!=null){
			title=curOrg.getUnitname();
		}
		model.addAttribute("title", title+"环境信访登记表");
		model.put("swrList", dicManager.findByType("21"));
		model.put("dqwrList", dicManager.findByType("22"));
		model.put("zswrList", dicManager.findByType("23"));
		model.put("gfwrList", dicManager.findByType("24"));
		model.put("fswrList", dicManager.findByType("25"));
		model.put("sthjwrList", dicManager.findByType("34"));
		if (StringUtils.isBlank(frm.getId())) {
			Calendar endC = Calendar.getInstance();
			frm.setXfsj(DateUtil.getDate(endC.getTime()));//默认信访时间为当天
			frm.setJlsj(DateUtil.getDate(endC.getTime()));//默认记录时间为当天
            endC.add(Calendar.DATE, 60);
            frm.setBjsx(DateUtil.getDate(endC.getTime()));//默认办结时限60天
            frm.setJlr(cur.getName());//默认记录人为当前登录人
            /**
             * 信访编号编号规则：如2015090010002，201 5（2015年）、09（9月份）、001（9月产生信访序号）、0002（2015年信访产生的序号）
             */
            //信访编号
            String xfbh = "";
            int yearNum = xfdjManager.queryNumByYear();
            int monthNum = xfdjManager.queryNumByMonth();
            String yNum = "";
            String mNum = "";
            Calendar a=Calendar.getInstance();
    		int year = a.get(Calendar.YEAR);//获得当前年份
    		int month = a.get(Calendar.MONTH)+1;//获得当前月份
    		String mon = "";
    		if(month>10 || month==10){
    			mon=String.valueOf(month);
    		}else{
    			mon="0"+String.valueOf(month);
    		}
    		//月序列
    		if(monthNum+1<10){
    			mNum = "00"+String.valueOf(monthNum+1);
    		}else if(monthNum+1<100){
    			mNum = "0"+String.valueOf(monthNum+1);
    		}else{
    			mNum = String.valueOf(monthNum+1);
    		}
    		//年序列
    		if(yearNum+1<10){
    			yNum = "000"+String.valueOf(yearNum+1);
    		}else if(yearNum+1<100){
    			yNum = "00"+String.valueOf(yearNum+1);
    		}else if(yearNum+1<1000){
    			yNum = "0"+String.valueOf(yearNum+1);
    		}else{
    			yNum = String.valueOf(yearNum+1);
    		}
    		
    		
    		xfbh=String.valueOf(year)+mon+mNum+yNum;
    		frm.setXfbh(xfbh);
			return "app/work/xfdj/xfdjAdd";
		}
		TBizXfdj po = (TBizXfdj) xfdjManager.get(TBizXfdj.class, frm.getId());
		//添加信访登记页面直接派发的功能
		Work w = xfdjManager.getWorkById(po.getId(),"");
		frm.setId(po.getId());
		frm.setXfly(po.getXfly());
		frm.setXfbh(po.getXfbh());
		frm.setXfnr(po.getXfnr());
		frm.setXfr(po.getXfr());
		frm.setLxdh(po.getLxdh());
		frm.setJlr(po.getJlr());
		frm.setWrlx(po.getWrlx());
		frm.setWrlxqt(po.getWrlxqt());
		frm.setLwmc(po.getLwmc());
		//污染其他
		frm.setSwrqt(po.getSwrqt());
		frm.setDqwrqt(po.getDqwrqt());
		frm.setZswrqt(po.getZswrqt());
		frm.setGfwrqt(po.getGfwrqt());
		frm.setFswrqt(po.getFswrqt());
		frm.setQydz(po.getQydz());
				
		frm.setXfsj(DateUtil.getDate(po.getXfsj()));
		frm.setJlsj(DateUtil.getDate(po.getJlsj()));
        frm.setBjsx(DateUtil.getDate(po.getBjsx()));
        if(w != null){
            if(w.getZfdxType() != null && w.getZfdxType() != ""){
            	frm.setZfdxType(w.getZfdxType());
            }
            if(w.getJsr() != null && w.getJsr() != ""){
            	frm.setJsrId(w.getJsr());
            }
            if(w.getJsrName() != null && w.getJsrName() != ""){
            	frm.setJsrName(w.getJsrName());
            }
            if(w.getXfdjbId() != null && w.getXfdjbId() != ""){
            	frm.setXfdjId(w.getXfdjbId());
            }
            if(w.getPsyj() != null && w.getPsyj() != ""){
            	frm.setPsyj(w.getPsyj());
            }
            if(w.getId() != null && w.getId() != ""){
            	frm.setApplyId(w.getId());
            }
            //处理选择过的执法对象
    		String zfdxmcs = "";
    		List<Map<String, String>> listMap = commWorkManager
					.zfdxTableData(w.getId());
    		for(int i = 0; i < listMap.size(); i++){
    			zfdxmcs = listMap.get(i).get("lawobjname");
    		}
    		if(listMap.size() > 0){
    			String rowString = "";
        		for(int i=0;i<listMap.size();i++){
        			rowString += "{'lawobjid':'"+listMap.get(i).get("lawobjid")+"'";
        			rowString += ",'lawobjname':'"+listMap.get(i).get("lawobjname")+"'";
        			rowString += ",'fddbr':'"+listMap.get(i).get("manager")+"'";
        			rowString += ",'id':'"+listMap.get(i).get("id")+"'";
        			rowString += ",'fddbrlxdh':'"+listMap.get(i).get("managermobile")+"'";
        			rowString += ",'hbfzr':'"+listMap.get(i).get("bjcr")+"'";
        			rowString += ",'hbfzrlxdh':'"+listMap.get(i).get("lxdh")+"'";
        			rowString += ",'regionid':'"+listMap.get(i).get("region")+"'";
        			rowString += ",'address':'"+listMap.get(i).get("address")+"'}";
        			if(i!=listMap.size()-1){
        				rowString += ",";
        			}
        		}
    			model.addAttribute("mcs", '['+rowString+']');//在保存后进入到派发界面把执法对象的数据都带过来
    		}
    		frm.setZfdxmc(zfdxmcs);
        }
        return "app/work/xfdj/xfdjAdd";
	}
	
	/**
	 * 跳转到信访登记查看界面
	 * @param model
	 * @param frm
	 * @return
	 */
	@RequestMapping(value = "/xfdjInfo.htm")
	public String xfdjInfo(ModelMap model, XfdjForm frm) {
		//标题
		String title = "";
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		TSysUser cur = CtxUtil.getCurUser();
		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
		// 标题中xx环境监察支队 可能是当前用户所在部门的名称
		if(curOrg!=null){
			title=curOrg.getUnitname();
		}
		model.addAttribute("title", title+"环境信访登记表");
		TBizXfdj po = (TBizXfdj) xfdjManager.get(TBizXfdj.class, frm.getId());
		frm.setId(po.getId());
		frm.setXfly(dicManager.getNameByTypeAndCode("20", po.getXfly()));
		frm.setXfbh(po.getXfbh());
		frm.setXfnr(po.getXfnr());
		frm.setXfr(po.getXfr());
		frm.setLxdh(po.getLxdh());
		frm.setJlr(po.getJlr());
		//污染类型
		String temp="";
		if(StringUtils.isNotBlank(po.getWrlx())){
			String [] wrlxs = po.getWrlx().split(",");
			for(int i=0;i<wrlxs.length;i++){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
				if(dic!=null){
					if("2104".equals(dic.getCode())){
						if(StringUtils.isNotBlank(po.getSwrqt())){
							temp=temp+"水污染其他("+po.getSwrqt()+"),";
						}
					}
					else if("2205".equals(dic.getCode())){
						if(StringUtils.isNotBlank(po.getDqwrqt())){
							temp=temp+"大气污染其他("+po.getDqwrqt()+"),";
						}
					}
					else if("2304".equals(dic.getCode())){
						if(StringUtils.isNotBlank(po.getZswrqt())){
							temp=temp+"噪声污染其他("+po.getZswrqt()+"),";
						}
					}
					else if("2406".equals(dic.getCode())){
						if(StringUtils.isNotBlank(po.getGfwrqt())){
							temp=temp+"固废污染其他("+po.getGfwrqt()+"),";
						}
					}
					else if("2503".equals(dic.getCode())){
						if(StringUtils.isNotBlank(po.getFswrqt())){
							temp=temp+"辐射污染其他("+po.getFswrqt()+"),";
						}
					}
					else{
						temp=temp+dic.getName()+",";
					}
				}
			}
			temp = temp.substring(0, temp.length()-1);
			if(StringUtils.isNotBlank(po.getWrlxqt())){
				frm.setWrlx(temp+",其他污染("+po.getWrlxqt()+")");
			}else{
				frm.setWrlx(temp);
			}
		}else{
			if(StringUtils.isNotBlank(po.getWrlxqt())){
				frm.setWrlx("其他污染("+po.getWrlxqt()+")");
			}else{
				frm.setWrlx("");
			}
		}
		frm.setLwmc(po.getLwmc());
		if(null != po.getXfsj()){
			frm.setXfsj(sdf.format(po.getXfsj()));
		}
		if(null != po.getJlsj()){
			frm.setJlsj(sdf.format(po.getJlsj()));
		}
		if(null != po.getBjsx()){
			frm.setBjsx(sdf.format(po.getBjsx()));
		}
        return "app/work/xfdj/xfdjInfo";
	}
	
	/**
	 * 跳转到信访登记报出界面
	 * @param model
	 * @param frm
	 * @return
	 */
	@RequestMapping(value = "/bcXfdj.htm")
	public String bcXfdj(ModelMap model, XfdjForm frm) {
		//标题
		String title = "";
		TSysUser cur = CtxUtil.getCurUser();
		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
		// 标题中xx环境监察支队 可能是当前用户所在部门的名称
		if(curOrg!=null){
			title=curOrg.getUnitname();
		}
		model.addAttribute("title", title+"<br/>"+"环境信访办结单");
		TBizXfdj po = (TBizXfdj) xfdjManager.get(TBizXfdj.class, frm.getId());
		
		frm.setId(po.getId());
		frm.setXfly(dicManager.getNameByTypeAndCode("20", po.getXfly()));
		frm.setXfbh(po.getXfbh());
		frm.setXfnr(po.getXfnr());
		frm.setXfr(po.getXfr());
		frm.setLxdh(po.getLxdh());
		frm.setJlr(po.getJlr());
		//污染类型
		String temp="";
		if(StringUtils.isNotBlank(po.getWrlx())){
			String [] wrlxs = po.getWrlx().split(",");
			for(int i=0;i<wrlxs.length;i++){
				TSysDic dic = (TSysDic) dicManager.get(TSysDic.class, wrlxs[i]);
				if(dic!=null){
					temp=temp+dic.getName()+",";
				}
			}
			temp = temp.substring(0, temp.length()-1);
			if(StringUtils.isNotBlank(po.getWrlxqt())){
				frm.setWrlx(temp+","+po.getWrlxqt());
			}else{
				frm.setWrlx(temp);
			}
		}else{
			if(StringUtils.isNotBlank(po.getWrlxqt())){
				frm.setWrlx(po.getWrlxqt());
			}else{
				frm.setWrlx("");
			}
		}
		frm.setLwmc(po.getLwmc());
		frm.setXfsj(DateUtil.getDateTime(po.getXfsj()));
		frm.setJlsj(DateUtil.getDateTime(po.getJlsj()));
        frm.setBjsx(DateUtil.getDateTime(po.getBjsx()));
        
        XfbjdForm xfbjdForm = xfdjManager.queryXfbjd(frm.getId());
        model.put("xfbjdForm", xfbjdForm);
        return "app/work/xfdj/xfdjBc";
	}

	/**
	 * 删除信访登记
	 * @param id
	 * @param model
	 */
	@RequestMapping(value = "/delXfdj.json", produces = "application/json")
	public void delXfdj(String id, ModelMap model) {
		try {
			this.xfdjManager.removeXfdj(id);
			//删除信访登记后，相关的信访任务也删掉（先支持一对一）
			Work w = xfdjManager.getWorkById(id,"");
			if(w != null && StringUtils.isNotBlank(w.getId())){
				rwglManager.saveDelwork(w.getId());
			}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (AppException e) {
			log.debug("删除用户信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}

	}

	/**
	 * 保存信访登记
	 * @param frm
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/xfdjSave.json", produces = "application/json")
	public void xfdjSave(XfdjForm frm, String rwlxIds, String zfdxmcs,ModelMap model) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			TSysUser curUser = CtxUtil.getCurUser();
			TBizXfdj xfdj = xfdjManager.saveXfdj(frm);
			model.put("id",xfdj.getId());
			//任务数据的赋值
			if(StringUtils.isNotBlank(xfdj.getId())){
				WorkDto work =  new WorkDto();
				work.setId(frm.getApplyId());
				work.setJsr(frm.getJsrId());
				work.setJsrName(frm.getJsrName());
				work.setPsyj(frm.getPsyj());
				work.setZfdxType(frm.getZfdxType());
				work.setZfdxmc(frm.getZfdxmc());
				work.setXfdjbId(xfdj.getId());
				work.setSecurity("1");//密级
				work.setSource("10");//任务来源
				work.setEmergency("1");//紧急程度
				work.setXfbcjsrId(curUser.getId());
				work.setXfbcjsrName(curUser.getName());

        		work.setEndTime(frm.getBjsx());
        		//默认值
    			work.setDjrId(CtxUtil.getCurUser().getId());
    			work.setDjrName(CtxUtil.getCurUser().getName());
				work.setRwmcgs("0");//组合
				//信访投诉直接派发的任务名称 teset2016年09月20日信访投诉
				Date dt=new Date();
				work.setStartTime(dt.toString());
				String date = sdf.format(dt);
				work.setRwmcrq(date);
				String workName = frm.getZfdxmc() + date + TaskTypeCode.XFTS.getText();
				work.setWorkName(workName);
				work.setWorkNote(workName+"任务");
				work.setXfts("Y");
    			Work w1 = workPf.savePfInfo(work, frm.getJsrId());
    			if(StringUtils.isNotBlank(frm.getApplyId())){
    				model.put("applyId",frm.getApplyId());
    			}else{
    				model.put("applyId",w1.getId());
    			}
    			if(StringUtils.isNotBlank(frm.getApplyId())){
            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
            		if(StringUtils.isNotBlank(w1.getZfdxType())){
            			commWorkManager.saveZfdxTypeOnChange(frm.getApplyId(), w1.getZfdxType(),rwlxIds);
            			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
            		}
            		commWorkManager.saveTaskTypeMultiple(frm.getApplyId(), TaskTypeCode.XFTS.getCode(), curUser);
            		if(StringUtils.isNotBlank(zfdxmcs)){
        				JSONArray array = new JSONArray(zfdxmcs);
                		commonManager.saveChoseeLawobj(frm.getApplyId(), w1.getZfdxType(), array, curUser);
        			}
    			}else{
    				if(StringUtils.isNotBlank(w1.getId())){
                		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
                		if(StringUtils.isNotBlank(w1.getZfdxType())){
                			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
                			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
                		}
                		commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
                		if(StringUtils.isNotBlank(zfdxmcs)){
            				JSONArray array = new JSONArray(zfdxmcs);
                    		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
            			}
                	}
    			}
			}
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 
	 * 函数介绍：打印登记表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportXfdjb.json")
	public void exportXfdjb(ModelMap model,HttpServletResponse response,String xfdjId){
		xfdjManager.exportXfdjb(response,xfdjId);
	}
	
	/**
	 * 
	 * 函数介绍：打印办结单
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportXfbjd.json")
	public void exportXfbjd(ModelMap model,HttpServletResponse response,String xfdjId, String rwid){
		xfdjManager.exportXfbjd(response,xfdjId,rwid);
	}
	
	/**
	 * 信访登记派发
	 * @param frm
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/xfdjpf.json", produces = "application/json")
	public void xfdjpf(XfdjForm frm, String xfdjId, String rwlxIds, String zfdxmcs,ModelMap model) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			TSysUser curUser = CtxUtil.getCurUser();
			if(StringUtil.isNotBlank(xfdjId)){
				model.put("id",xfdjId);
				WorkDto work =  new WorkDto();
				work.setJsr(frm.getJsrId());
				if(StringUtils.isNotBlank(frm.getApplyId())){
					work.setId(frm.getApplyId());
				}
				work.setJsrName(frm.getJsrName());
				work.setPsyj(frm.getPsyj());
				work.setZfdxType(frm.getZfdxType());
				work.setZfdxmc(frm.getZfdxmc());
				work.setXfdjbId(xfdjId);
				work.setSecurity("1");//默认值
				work.setSource("10");//默认值
				work.setEmergency("1");//默认值
				work.setXfbcjsrId(curUser.getId());
				work.setXfbcjsrName(curUser.getName());
				
        		work.setEndTime(frm.getBjsx());
        		//默认值
    			work.setDjrId(CtxUtil.getCurUser().getId());
    			work.setDjrName(CtxUtil.getCurUser().getName());
				work.setRwmcgs("0");//组合
				//信访投诉直接派发的任务名称 teset2016年09月20日信访投诉
				Date dt=new Date();
				work.setStartTime(dt.toString());
				String date = sdf.format(dt);
				work.setRwmcrq(date);
				String workName = frm.getZfdxmc() + date + TaskTypeCode.XFTS.getText();
				work.setWorkName(workName);
				work.setWorkNote(workName+"任务");
				work.setXfts("Y");
    			Work w1 = workPf.savePf(work, frm.getJsrId(), CtxUtil.getCurUser(), "xfts");
    			if(StringUtils.isNotBlank(frm.getApplyId())){
            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
            		if(StringUtils.isNotBlank(w1.getZfdxType())){
            			commWorkManager.saveZfdxTypeOnChange(frm.getApplyId(), w1.getZfdxType(),rwlxIds);
            			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
            		}
            		commWorkManager.saveTaskTypeMultiple(frm.getApplyId(), TaskTypeCode.XFTS.getCode(), curUser);
            		if(StringUtils.isNotBlank(zfdxmcs)){
        				JSONArray array = new JSONArray(zfdxmcs);
                		commonManager.saveChoseeLawobj(frm.getApplyId(), w1.getZfdxType(), array, curUser);
        			}
    			}else{
    				if(StringUtils.isNotBlank(w1.getId())){
                		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
                		if(StringUtils.isNotBlank(w1.getZfdxType())){
                			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
                			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
                		}
                		commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
                		if(StringUtils.isNotBlank(zfdxmcs)){
            				JSONArray array = new JSONArray(zfdxmcs);
                    		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
            			}
            			//派发附件
            			List<TDataFile> pfFileList= commonManager.queryFileList(frm.getApplyId(), "RWGLPFFJ");
            			if(pfFileList!=null && pfFileList.size()>0){
            				for(int k=0;k<pfFileList.size();k++){
            					TDataFile t= new TDataFile(w1.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
            					commonManager.saveFile(t);
                			}
            			}
            			//派发成功后删掉保存的派发任务列表中的任务
            			Work w = xfdjManager.getWorkById(xfdjId,"1");
            			if(w != null){
        					rwglManager.saveDelwork(w.getId());
        				}
                	}
    			}
			}else{
				TBizXfdj xfdj = xfdjManager.saveXfdj(frm);
				model.put("id",xfdj.getId());
				//任务数据的赋值
				if(StringUtils.isNotBlank(xfdj.getId())){
					WorkDto work =  new WorkDto();
					work.setJsr(frm.getJsrId());
					if(StringUtils.isNotBlank(frm.getApplyId())){
						work.setId(frm.getApplyId());
					}
					work.setJsrName(frm.getJsrName());
					work.setPsyj(frm.getPsyj());
					work.setZfdxType(frm.getZfdxType());
					work.setZfdxmc(frm.getZfdxmc());
					work.setXfdjbId(xfdj.getId());
					work.setSecurity("1");//默认值
					work.setSource("10");//默认值
					work.setEmergency("1");//默认值
					work.setXfbcjsrId(curUser.getId());
					work.setXfbcjsrName(curUser.getName());
					
	        		work.setEndTime(frm.getBjsx());
	        		//默认值
	    			work.setDjrId(CtxUtil.getCurUser().getId());
	    			work.setDjrName(CtxUtil.getCurUser().getName());
					work.setRwmcgs("0");//组合
					//信访投诉直接派发的任务名称 teset2016年09月20日信访投诉
					Date dt=new Date();
					work.setStartTime(dt.toString());
					String date = sdf.format(dt);
					work.setRwmcrq(date);
					String workName = frm.getZfdxmc() + date + TaskTypeCode.XFTS.getText();
					work.setWorkName(workName);
					work.setWorkNote(workName+"任务");
					work.setXfts("Y");
	    			Work w1 = workPf.savePf(work, frm.getJsrId(), CtxUtil.getCurUser(), "xfts");
	    			if(StringUtils.isNotBlank(frm.getApplyId())){
	            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
	            		if(StringUtils.isNotBlank(w1.getZfdxType())){
	            			commWorkManager.saveZfdxTypeOnChange(frm.getApplyId(), w1.getZfdxType(),rwlxIds);
	            			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
	            		}
	            		commWorkManager.saveTaskTypeMultiple(frm.getApplyId(), TaskTypeCode.XFTS.getCode(), curUser);
	            		if(StringUtils.isNotBlank(zfdxmcs)){
	        				JSONArray array = new JSONArray(zfdxmcs);
	                		commonManager.saveChoseeLawobj(frm.getApplyId(), w1.getZfdxType(), array, curUser);
	        			}
	    			}else{
	    				if(StringUtils.isNotBlank(w1.getId())){
	                		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
	                		if(StringUtils.isNotBlank(w1.getZfdxType())){
	                			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
	                			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
	                		}
	                		commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
	                		if(StringUtils.isNotBlank(zfdxmcs)){
	            				JSONArray array = new JSONArray(zfdxmcs);
	                    		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
	            			}
	            			//派发附件
	            			List<TDataFile> pfFileList= commonManager.queryFileList(frm.getApplyId(), "RWGLPFFJ");
	            			if(pfFileList!=null && pfFileList.size()>0){
	            				for(int k=0;k<pfFileList.size();k++){
	            					TDataFile t= new TDataFile(w1.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
	            					commonManager.saveFile(t);
	                			}
	            			}
	            			//派发成功后删掉保存的派发任务列表中的任务
	            			Work w = xfdjManager.getWorkById(xfdjId,"1");
	        				if(w != null){
	        					rwglManager.saveDelwork(w.getId());
	        				}
	                	}
	    			}
	        	}
			}
			JsonResultUtil.suncess(model, "派发成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 信访登记下派
	 * @param frm
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/xfdjxp.json", produces = "application/json")
	public void xfdjxp(XfdjForm frm, String xfdjId, String rwlxIds, ModelMap model) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			TSysUser curUser = CtxUtil.getCurUser();
			if(StringUtil.isNotBlank(xfdjId)){
				model.put("id",xfdjId);
				WorkDto work =  new WorkDto();
				work.setJsr(frm.getJsrId());
				work.setJsrName(frm.getJsrName());
				work.setPsyj(frm.getPsyj());
				work.setZfdxType(frm.getZfdxType());
				work.setZfdxmc(frm.getZfdxmc());
				work.setXfdjbId(xfdjId);
				work.setSecurity("1");//默认值
				work.setSource("10");//默认值
				work.setEmergency("1");//默认值
				work.setXfbcjsrId(curUser.getId());
				work.setXfbcjsrName(curUser.getName());
				
        		work.setEndTime(frm.getBjsx());
        		//默认值
    			work.setDjrId(CtxUtil.getCurUser().getId());
    			work.setDjrName(CtxUtil.getCurUser().getName());
				work.setRwmcgs("0");//组合
				//信访投诉直接派发的任务名称 teset2016年09月20日信访投诉
				Date dt=new Date();
				work.setStartTime(dt.toString());
				String date = sdf.format(dt);
				work.setRwmcrq(date);
				String workName = frm.getZfdxmc() + date + TaskTypeCode.XFTS.getText();
				work.setWorkName(workName);
				work.setWorkNote(workName+"任务");
    			Work w1 = workPf.savePf(work, frm.getJsrId(), CtxUtil.getCurUser(), "xfts");
    			//先进行任务的派发工作进入流程，然后执行下派的操作
				TSysUser xfzy = userManager.getUser(frm.getJsrId());
    			if(StringUtils.isNotBlank(w1.getId())){
            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
            		if(StringUtils.isNotBlank(w1.getZfdxType())){
            			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
            			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
            		}
            		commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
        			if(StringUtils.isNotBlank(frm.getZfdxId())){
        				JSONObject obj = new JSONObject();
        				JSONArray array = new JSONArray();
        				obj.put("lawobjid", frm.getZfdxId());
        				obj.put("lawobjname", frm.getZfdxmc());
        				array.put(obj);
                		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
        			}
        			//派发附件
        			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
        			String fjIds = "";
        			if(pfFileList!=null && pfFileList.size()>0){
        				for(int k=0;k<pfFileList.size();k++){
        					TDataFile t= new TDataFile(w1.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
        					commonManager.saveFile(t);
        					if(k<pfFileList.size()-1){
        						fjIds += pfFileList.get(k).getId()+",";
    						}else{
    							fjIds += pfFileList.get(k).getId();
    						}
            			}
        			}
        			if(StringUtils.isNotBlank(xfzy.getAreaId())){
    					String taskId = xfdjManager.gettaskId(w1.getProcessId());
    					workManager.saveZpXpds(w1.getId(), taskId, xfzy.getAreaId(),w1.getPsyj(),fjIds,"","");
    				}
            	}
			}else{
				TBizXfdj xfdj = xfdjManager.saveXfdj(frm);
				model.put("id",xfdj.getId());
				//任务数据的赋值
				if(StringUtils.isNotBlank(xfdj.getId())){
					WorkDto work =  new WorkDto();
					work.setJsr(frm.getJsrId());
					work.setJsrName(frm.getJsrName());
					work.setPsyj(frm.getPsyj());
					work.setZfdxType(frm.getZfdxType());
					work.setZfdxmc(frm.getZfdxmc());
					work.setXfdjbId(xfdj.getId());
					work.setSecurity("1");//默认值
					work.setSource("10");//默认值
					work.setEmergency("1");//默认值
					work.setXfbcjsrId(curUser.getId());
					work.setXfbcjsrName(curUser.getName());
					
	        		work.setEndTime(frm.getBjsx());
	        		//默认值
	    			work.setDjrId(CtxUtil.getCurUser().getId());
	    			work.setDjrName(CtxUtil.getCurUser().getName());
					work.setRwmcgs("0");//组合
					//信访投诉直接派发的任务名称 teset2016年09月20日信访投诉
					Date dt=new Date();
					work.setStartTime(dt.toString());
					String date = sdf.format(dt);
					work.setRwmcrq(date);
					String workName = frm.getZfdxmc() + date + TaskTypeCode.XFTS.getText();
					work.setWorkName(workName);
					work.setWorkNote(workName+"任务");
	    			Work w1 = workPf.savePf(work, frm.getJsrId(), CtxUtil.getCurUser(), "xfts");
	    			//先进行任务的派发工作进入流程，然后执行下派的操作
    				TSysUser xfzy = userManager.getUser(frm.getJsrId());
	    			if(StringUtils.isNotBlank(w1.getId())){
	            		//任务生成时候进行任务类型，执法对象名称，执法对象类型的保存
	            		if(StringUtils.isNotBlank(w1.getZfdxType())){
	            			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
	            			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
	            		}
	            		commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
	        			if(StringUtils.isNotBlank(frm.getZfdxId())){
	        				JSONObject obj = new JSONObject();
	        				JSONArray array = new JSONArray();
	        				obj.put("lawobjid", frm.getZfdxId());
	        				obj.put("lawobjname", frm.getZfdxmc());
	        				array.put(obj);
	                		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
	        			}
	        			//派发附件
	        			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
	        			String fjIds = "";
	        			if(pfFileList!=null && pfFileList.size()>0){
	        				for(int k=0;k<pfFileList.size();k++){
	        					TDataFile t= new TDataFile(w1.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
	        					commonManager.saveFile(t);
	        					if(k<pfFileList.size()-1){
	        						fjIds += pfFileList.get(k).getId()+",";
	    						}else{
	    							fjIds += pfFileList.get(k).getId();
	    						}
	            			}
	        			}
	        			if(StringUtils.isNotBlank(xfzy.getAreaId())){
	    					String taskId = xfdjManager.gettaskId(w1.getProcessId());
	    					workManager.saveZpXpds(w1.getId(), taskId, xfzy.getAreaId(),w1.getPsyj(),fjIds,"","");
	    				}
	            	}
	        	}
			}
			JsonResultUtil.suncess(model, "下派成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
     * 
     * 函数介绍：跳转到：信访任务办理情况
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/xfdjrwList.htm")
    public String dbrwList(ModelMap model,String title, String xfdjId) {
    	model.put("title", title);
    	model.put("xfdjId", xfdjId);
        return "app/work/xfdj/xfrwList";
    }
    
    /**
	 * 判断是否可以派发或者修改
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return
	 */
	@RequestMapping(value = "/isShowXf.json")
	public void isShowXf(String xfdjId, ModelMap model) {
		try {
			model.put("isShowXf",xfdjManager.isShowXf(xfdjId));
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("显示操作信息错误！", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}
}
