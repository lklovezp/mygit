/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.TBizXfbjd;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.hzws.WorkParaBeanBase;
import com.hnjz.hzws.WorkflowService;
import com.hnjz.hzws.WorkflowServiceService;
import com.hnjz.sal.WorkClient;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPosition;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.wf.enums.ProcessEnum;

/**
 * 任务的派发操作<br/>
 * 保存任务对象；启动流程、执行第一步派发
 * 
 * @author wumi
 * @version $Id: WorkDto.java, v 0.1 Jan 29, 2013 10:45:25 AM wumi Exp $
 */
@Service(value = "workPf")
public class WorkPf extends BaseNode {

    @Autowired
    private WorkClient    workClient;

    @Autowired
    private UserManager userManager;
    
    @Autowired
    private CommWorkManager commWorkManager;
    
    /**工作流的名称（通用的工作流）*/
    String KEY = ProcessEnum.GENERAL_TASK.getProcessKey();
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    protected WorkManagerImpl workManager;
    
    @Autowired
	protected Dao dao;
    
    @Autowired
    private IndexManager     indexManager;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;
    
    /**
     * 任务生成
     * @param frm 任务信息
     * @param jsrId 接收人Id
     * @throws Exception
     */
    public Work savePfInfo(WorkDto frm, String jsrIds) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = Calendar.getInstance().getTime();
        //填充WORK对象
        Work work = null;
        if (StringUtil.isBlank(frm.getId())) {
        	work = new Work();
            work.setCreateTime(new Date());
            work.setCreateUser(cur);
        }else{
        	work = workDao.get(frm.getId());
        }
        
        //保存字段
        work.setName(frm.getWorkName());//任务名称
        work.setWorkNote(frm.getWorkNote());//主要内容
        work.setSource(frm.getSource());//任务来源
        work.setSecurity(frm.getSecurity());//任务密级
        work.setEmergency(frm.getEmergency());//紧急程度
        work.setDjrId(frm.getDjrId());//登记人id
        work.setRwmcgs(frm.getRwmcgs());//任务名称格式
        //信访投诉 保存登记表、报出接收人等字段
        work.setXfdjbId(frm.getXfdjbId());//信访登记表id
        work.setXfbcjsrId(frm.getXfbcjsrId());
        work.setXfbcjsrName(frm.getXfbcjsrName());
        work.setSfdb(frm.getSfdb());
        work.setBlrsfbc(frm.getBlrsfbc());
        
        //离线版和后台判断地方
        String biaoshi = indexManager.sysVer;
        if(("0").equals(biaoshi)){
        	 if(StringUtils.isNotBlank(frm.getJsrId())){
             	work.setJsr(frm.getJsrId());//接受人id
             }else{
             	work.setJsr(cur.getId());//接受人id,在单机版的功能里默认是当前登录人
             }
        }
        
        if(StringUtils.isNotBlank(jsrIds)){
        	String [] jsr = jsrIds.split(",");
        	work.setJsrIds(jsrIds);//一组接受人id
        	if(jsr.length==1){
        		work.setJsr(jsrIds);//接受人id
        	}
        }
        Date end = DateUtil.getEndDatetime(frm.getEndTime());
        work.setEndTime(end);//要求完成时间
        if(("0").equals(frm.getRwmcgs())){
        	Date rwrq = DateUtil.getEndDatetime(frm.getRwmcrq().replace('年','-').replace('月','-').replace('日',' '));
            work.setRwmcrq(rwrq);//任务名称日期
        }
        work.setPsyj(frm.getPsyj());//批示意见
        
        work.setIsActive(YnEnum.Y.getCode());
        work.setAreaid(cur.getAreaId());
        //work.setState(WorkState.PF.getCode());
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        //执法对象类型
        work.setZfdxType(frm.getZfdxType());
        //保存WORK对象
        workDao.save(work);
        
        //任务生成时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.SCR.getCode());//生成人
        this.dao.save(taskuser);
        return work;
    }

    /**
     * 任务派发
     * @param frm 任务信息
     * @param jsrId 接收人Id
     * @return 
     * @throws Exception
     */
    public Work savePf(WorkDto frm, String jsrId, TSysUser cur, String lx) throws Exception {
        Date date = Calendar.getInstance().getTime();
        TSysUser curUser = CtxUtil.getCurUser();
        String backresult = "";
        Document document = null;
        String workid = "";
        String trackid = "";
        String userid = "";
        //师级专员派发任务添加专员审核人
        //List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
        //List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
        //填充WORK对象
        Work work = null;
        if (StringUtils.isBlank(frm.getId())) {
        	work = new Work();
        	work.setCreateTime(new Date());
            work.setCreateUser(cur);
        }else{
        	work = workDao.get(frm.getId());
        }
        //保存字段
        work.setName(frm.getWorkName());//任务名称
        work.setWorkNote(frm.getWorkNote());//主要内容
        work.setSource(frm.getSource());//任务来源
        work.setSecurity(frm.getSecurity());//任务密级
        work.setEmergency(frm.getEmergency());//紧急程度
        work.setDjrId(frm.getDjrId());//登记人id
        work.setRwmcgs(frm.getRwmcgs());//任务名称格式
        //信访投诉 保存登记表、报出接收人等字段
        work.setXfdjbId(frm.getXfdjbId());//信访登记表id
        work.setXfbcjsrId(frm.getXfbcjsrId());
        work.setXfbcjsrName(frm.getXfbcjsrName());
        work.setSfdb(frm.getSfdb());
        work.setBlrsfbc(frm.getBlrsfbc());
        Date end = DateUtil.getEndDatetime(frm.getEndTime());
        work.setEndTime(end);//要求完成时间
        work.setPsyj(frm.getPsyj());//批示意见
        work.setZfdxType(frm.getZfdxType());//执法对象类型
        
        work.setJsr(frm.getJsrIds());
        if(StringUtil.isNotBlank(frm.getJsr())){
        	work.setJsr(frm.getJsr());
        }
        if(StringUtil.isNotBlank(frm.getZfdxmc())){
        	work.setZfdxmc(frm.getZfdxmc());
        }
        
        if(("0").equals(frm.getRwmcgs())){
        	Date rwrq = DateUtil.getEndDatetime(frm.getRwmcrq().replace('年','-').replace('月','-').replace('日',' '));
            work.setRwmcrq(rwrq);//任务名称日期
        }
        work.setIsActive(YnEnum.Y.getCode());
        work.setStartTime(date);//任务派发时间作为任务开始时间
        work.setAreaid(cur.getAreaId());
        /***********下步操作**********/
        //科员不能转派
        StringBuffer nextAction = new StringBuffer();
	   nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
       if (!orgManager.getPosition(jsrId).equals(UserPosition.KY)) {
           nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
       }
       
       //2015-3-17 添加“下派”（接受、转派、下派是同一级别的操作），如果没有下级单位则不显示“下派”操作。
       Boolean hasXJDW = true;//默认可以下派。
       List<Combobox> xjdslist = commonManager.queryAreaCombo(cur.getAreaId());
       if(xjdslist==null||xjdslist.size()==0){
       	hasXJDW = false;
       }
       if(hasXJDW){
       	nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
       }
        work.setNextActions(nextAction.toString());
        /***********下步操作**********/
        StringBuffer shrIds = new StringBuffer();
        //将跨级人员添加到审核人员集合中
        List<TSysUser> userList = null;
        if(cur!=null && curUser !=null && !StringUtils.equals(cur.getId(), curUser.getId())){
        	userList = orgManager.getUsers(null, jsrId, Boolean.FALSE);
            for (int i = 0; i < userList.size(); i++) {
                String userId = userList.get(i).getId();
                shrIds.append(userId).append(",");
            }
        	work.setShrids(frm.getJsrIds());
            if (log.isDebugEnabled()) {
                String[] rys = work.getShrids().split(",");
                for (String ele : rys) {
                	TSysUser s = this.userManager.getUser(ele);
                    log.debug("审核用户：" + s.getName());
                }
            }
        }else{
        	userList = orgManager.getUsers(null, jsrId, Boolean.FALSE);
            for (int i = 0; i < userList.size(); i++) {
                String userId = userList.get(i).getId();
                shrIds.append(userId).append(",");
            }
            //添加上专员派发对应任务的审核权限
            /*if(rczydata != null && "Y".equals(frm.getRcbg()) && cur.getId() != jsrId){
            	shrIds.append(cur.getId()).append(",");
            }
            if(xfzydata != null && "Y".equals(frm.getXfts()) && cur.getId() != jsrId){
            	shrIds.append(cur.getId()).append(",");
            }*/
            work.setShrids(shrIds.toString());
            if (log.isDebugEnabled()) {
                String[] rys = work.getShrids().split(",");
                for (String ele : rys) {
                	TSysUser s = this.userManager.getUser(ele);
                    log.debug("审核用户：" + s.getName());
                }
            }
        }
        
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        //保存WORK对象
        workDao.save(work);
        
        /************流程实例的创建***********/
		//根据任务类型判断实例调用的流程
        //结合慧正工作流新建实例的方法
        if(StringUtils.isNotBlank(cur.getId())){
        	backresult = workflowoperator.create("generalTask1", cur.getId());
        }else{
        	backresult = workflowoperator.create("generalTask1", "");
        }
        try {
            document = DocumentHelper.parseText(backresult);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        workid = rootElt.element("workid").getText();
        //userid = rootElt.element("curUser").element("IRunUser").element("id").getText();
        trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
        workflowoperator.close(cur.getId(), workid);
        //慧正工作流相关联字段
        work.setFlowid("generalTask1");
        work.setShiliid(workid);
        work.setTrackid(trackid);
    	//workflowoperator.submit(jsrId, workid, trackid, "", "", cur.getName(),"JS");
        //System.out.println("\n=======================创建流程返回xml打印======================================================================================\n");
        //System.out.println(backresult);
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(date);
        
        work.setState(WorkState.YPF.getCode());
     	//任务派发时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
	     TBizTaskuser taskuser=new TBizTaskuser();
	     taskuser.setTaskid(work.getId());
	     taskuser.setUserid(cur.getId());
	     taskuser.setUsername(cur.getName());
	     taskuser.setType(TaskUserEnum.PFR.getCode());//派发人
	     this.dao.save(taskuser);
	     
	     
    	int zj = -99;
        for (int i = 0; i < userList.size(); i++) {
        	TSysUser ele = userList.get(i);
            if (StringUtils.equals(ele.getId(), cur.getId())) {
                zj = i;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("zj:" + zj + "--to:" + userList.size());
        }
        if (zj == -99) {
            this.saveLog(cur, cStart.getTime(), WorkLogType.PF, WorkState.YPF, work, cStart
                .getTime());
        } else {
            for (int i = zj; i < userList.size(); i++) {
            	TSysUser ele = userList.get(i);
                WorkLogType ltype = WorkLogType.ZF;
                if (StringUtils.equals(cur.getId(), ele.getId())) {
                    ltype = WorkLogType.PF;
                }
                this.saveLog(ele, cStart.getTime(), ltype, WorkState.YPF, work, cStart.getTime());
                cStart.add(Calendar.SECOND, 1);
            }
        }
        return work;
    }
    
    /**
     * 批量派发
     * @param frm 任务信息
     * @param jsrId 接收人Id
     * @return 
     * @throws Exception
     */
    public String savePlpf(String jsrId,JSONArray array) throws Exception {
    	
        Date date = Calendar.getInstance().getTime();
        //填充WORK对象
        Work work = null;
        TSysUser cur = CtxUtil.getCurUser();
        for(int i=0;i<array.length();i++){
			JSONObject obj = array.getJSONObject(i);
			if(obj.has("id") && StringUtils.isNotBlank(obj.getString("id"))){
				work = workDao.get(obj.getString("id"));
				//保存字段
	        	work.setCreateTime(new Date());//创建时间
	        	work.setName(obj.getString("rwmc"));//任务名称
	        	work.setAreaid(cur.getAreaId());
	        	work.setIsActive(YnEnum.Y.getCode());//状态
	        	work.setStartTime(date);//任务派发时间作为任务开始时间
	        	work.setState(WorkState.YPF.getCode());
	        	work.setJsr(jsrId);
	        	
	        	/***********下步操作**********/
	            //科员不能转派
	            StringBuffer nextAction = new StringBuffer();
	            nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
	            if (!orgManager.getPosition(jsrId).equals(UserPosition.KY)) {
	                nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
	            }
	            
	            //判断是否可以下派
	            Boolean hasXJDW = true;//默认可以下派。
	            List<Combobox> xjdslist = commonManager.queryAreaCombo(cur.getAreaId());
	            if(xjdslist==null||xjdslist.size()==0){
	            	hasXJDW = false;
	            }
	            if(hasXJDW){
	            	nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
	            }
	            
	            work.setNextActions(nextAction.toString());
	            /***********下步操作**********/
	            
	            StringBuffer shrIds = new StringBuffer();
	            //将跨级人员添加到审核人员集合中
	            List<TSysUser> userList = orgManager.getUsers(null, jsrId, Boolean.FALSE);
	            for (int j = 0; j < userList.size(); j++) {
	                String userId = userList.get(j).getId();
	                shrIds.append(userId).append(",");
	            }
	            work.setShrids(shrIds.toString());
	            if (log.isDebugEnabled()) {
	                String[] rys = work.getShrids().split(",");
	                for (String ele : rys) {
	                	TSysUser s = this.userManager.getUser(ele);
	                    log.debug("审核用户：" + s.getName());
	                }
	            }
	            
	            //更新WORK的更新时间做为下一步步骤的开始时间
	            work.setUpdateTime(date);
	            work.setUpdateUser(cur);
	            //保存WORK对象
	            workDao.save(work);
	            
	            //任务派发时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
	            TBizTaskuser taskuser=new TBizTaskuser();
	            taskuser.setTaskid(work.getId());
	            taskuser.setUserid(cur.getId());
	            taskuser.setUsername(cur.getName());
	            taskuser.setType(TaskUserEnum.PFR.getCode());//派发人
	            
	            this.dao.save(taskuser);
	            
	            /************流程参数***********/
	            ProcessArgsBean bean = new ProcessArgsBean();
	            //申请单编号
	            bean.setApplyId(work.getId());
	            //当前操作人
	            bean.setCurrentOper(work.getCreateUser());
	            //下步操作人
	            TSysUser tuser = (TSysUser) userManager.get(TSysUser.class, jsrId);
	            bean.getNextOpers().add(tuser);
	            //转向
	            bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
	            bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
	            /************流程参数***********/
	            //启动流程
	            ProcessInstance pi = processManager.saveStart(this.KEY,
	                work, bean);
	            //当前任务
	            Task task = processManager.getActTaskFromPiId(pi.getId());
	            if (task != null) {
	                processManager.saveNext(this.KEY, task.getId(), work,
	                    bean);
	            }

	            Calendar cStart = Calendar.getInstance();
	            cStart.setTime(date);
	            int zj = -99;
	            for (int k = 0; k < userList.size(); k++) {
	            	TSysUser ele = userList.get(k);
	                if (StringUtils.equals(ele.getId(), cur.getId())) {
	                    zj = k;
	                }
	            }
	            if (log.isDebugEnabled()) {
	                log.debug("zj:" + zj + "--to:" + userList.size());
	            }
	            if (zj == -99) {
	                this.saveLog(cur, cStart.getTime(), WorkLogType.PF, WorkState.YPF, work, cStart
	                    .getTime());
	            } else {
	                for (int j = zj; j < userList.size(); j++) {
	                	TSysUser ele = userList.get(j);
	                    WorkLogType ltype = WorkLogType.ZF;
	                    if (StringUtils.equals(cur.getId(), ele.getId())) {
	                        ltype = WorkLogType.PF;
	                    }
	                    this.saveLog(ele, cStart.getTime(), ltype, WorkState.YPF, work, cStart.getTime());
	                    cStart.add(Calendar.SECOND, 1);
	                }
	            }
			}
		}
        
        return "success";
    }

    /**
     * 派发-接收下派<br/>
     * 下派的任务为组任务，可进行的操作不分权限（转派）
     * @param frm 任务信息
     * @param areaId 区域Id
     * @throws Exception
     */
    public void savePfJsxp(WorkDto frm, String areaId, String newresults) throws Exception {
    	//2015-3-17 添加 根据下派区域的id，查询出下派区域的领导，然后以该领导身份根据下派任务信息，创建一条新的任务。
    	TSysUser cur = userManager.getLeaderByAreaId(areaId);
    	
    	/*TSysUser cur = userManager.getSj();*/
        Date date = Calendar.getInstance().getTime();

		//慧正工作流相关的下派节点传值
		//2015-3-17 添加 根据下派区域的id，查询出下派区域的领导，然后以该领导身份根据下派任务信息，创建一条新的任务。
        Document document = null;
        String result = "";
		String workid = "";
		String userid = "";
		String trackid = "";
        try {
            document = DocumentHelper.parseText(newresults);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        result = rootElt.element("result").getText();
        workid = rootElt.element("workid").getText();
        userid = rootElt.element("curUser").element("IRunUser").element("userid").getText();
        trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
        
        //填充WORK对象
        Work work = new Work();
        
        //存入流程相关的值
        work.setShiliid(workid);
        work.setTrackid(trackid);
        work.setFlowid("zfjczlc");//专员去掉后，下派直接到地市启动执法检查的子流程
        
        work.setCreateTime(date);
        work.setCreateUser(cur);
        Date start = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getStartTime());
        Date end = DateUtil.getEndDatetime(frm.getEndTime());
        work.setEndTime(end);
        work.setIsActive(YnEnum.Y.getCode());
        work.setStartTime(start);
        work.setName(frm.getWorkName());
        work.setWorkNote(frm.getWorkNote());
        
        work.setSource("1");//任务来源（下派任务默认为“上级来文”）
        work.setSecurity(frm.getSecurity());//任务密级
        work.setEmergency(frm.getEmergency());//紧急程度
        work.setDjrId(cur.getId());//登记人（为任务接受人）
        //下派的时候原来直接选择地市，现在可以直接选择接受人
        if(StringUtils.isNotBlank(frm.getJsrId())){
        	 work.setJsr(frm.getJsrId());//接受人
        }

        //下派相关
        work.setIsxp(true);
        work.setAreaid(areaId);
        work.setSjid(frm.getId());
        Work w = workManager.getWorkbySjid(frm.getId(),work.getId());
    	if(w!=null){//存在下派的任务,退回操作
    		work.setState(WorkState.YTH.getCode());
    	}else{
    		work.setState(WorkState.YPF.getCode());
    	}
        
        /***********下步操作**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
        nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
        //2015-3-17 添加“下派”（接受、转派、下派是同一级别的操作），如果没有下级单位则不显示“下派”操作。
        Boolean hasXJDW = true;//默认可以下派。
        List<Combobox> xjdslist = commonManager.queryAreaComboByAreaId(cur.getAreaId());
        if(xjdslist==null||xjdslist.size()==0){
        	hasXJDW = false;
        }
        if(hasXJDW){
        	nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
        }
        
        work.setNextActions(nextAction.toString());
        /***********下步操作**********/
        
        StringBuffer shrIds = new StringBuffer();
        //将跨级人员添加到审核人员集合中
        List<TSysUser> userList = orgManager.getUsers(null, cur.getId(), Boolean.FALSE);
        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i).getId();
            shrIds.append(userId).append(",");
        }
        work.setShrids(shrIds.toString());
        if (log.isDebugEnabled()) {
            String[] rys = work.getShrids().split(",");
            for (String ele : rys) {
            	TSysUser s = this.userManager.getUser(ele);
                log.debug("审核用户：" + s.getName());
            }
        }

        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        //执法对象信息（执法对象类型+执法对象）
        Work sjWork = workDao.get(frm.getId());
		work.setZfdxType(sjWork.getZfdxType());
		
        //保存WORK对象
        workDao.save(work);

        //组合形式：保存任务类型和执法对象信息
        if("01".equals(work.getState())){
        	if("0".equals(frm.getRwmcgs())){
            	//任务类型
            	List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(sjWork.getId());
            	if(rwlxlistMap!=null && rwlxlistMap.size()>0){
            		String rwlxIds = "";
            		for (int i = 0; i < rwlxlistMap.size(); i++) {
            			if (i < rwlxlistMap.size() - 1) {
            				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
            			} else {
            				rwlxIds += rwlxlistMap.get(i).get("id");
            			}
            		}
            		commWorkManager.saveTaskTypeMultiple(work.getId(), rwlxIds, cur);
            	}
            	//执法对象类型
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //调取保存执法对象info
          		List<Map<String, String>> listMap = commWorkManager
          				.zfdxTableData(sjWork.getId());
          		if(listMap!=null && listMap.size() > 0){
          			for(int i=0;i<listMap.size();i++){
          				TBizTaskandlawobj tBizTaskandlawobj2 = new TBizTaskandlawobj();
          				tBizTaskandlawobj2.setTaskid(work.getId());
          				tBizTaskandlawobj2.setLawobjid(listMap.get(i).get("lawobjid"));
          				tBizTaskandlawobj2.setLawobjname(listMap.get(i).get("lawobjname"));
          				tBizTaskandlawobj2.setLawobjtype(listMap.get(i).get("lawobjtype"));
          				tBizTaskandlawobj2.setManager(listMap.get(i).get("manager"));
          				tBizTaskandlawobj2.setManagermobile(listMap.get(i).get("managermobile"));
              			tBizTaskandlawobj2.setBjcr(listMap.get(i).get("bjcr"));
              			tBizTaskandlawobj2.setRegionid(listMap.get(i).get("region"));
              			tBizTaskandlawobj2.setAddress(listMap.get(i).get("address"));
              			tBizTaskandlawobj2.setZw(listMap.get(i).get("zw"));
              			tBizTaskandlawobj2.setZwtitle(StringUtils.isNotBlank(listMap.get(i).get("zwtitle"))?listMap.get(i).get("zwtitle"):"法定代表人");
              			tBizTaskandlawobj2.setLxdh(listMap.get(i).get("lxdh"));
              			tBizTaskandlawobj2.setIsActive("Y");
              			tBizTaskandlawobj2.setCreateby(cur);
              			tBizTaskandlawobj2.setUpdateby(cur);
              			tBizTaskandlawobj2.setCreated(new Date());
              			tBizTaskandlawobj2.setUpdated(new Date());
              			this.dao.save(tBizTaskandlawobj2);
          			}
          		}       
            }else{
            	//任务类型
            	List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(sjWork.getId());
            	if(rwlxlistMap!=null && rwlxlistMap.size()>0){
            		String rwlxIds = "";
            		for (int i = 0; i < rwlxlistMap.size(); i++) {
            			if (i < rwlxlistMap.size() - 1) {
            				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
            			} else {
            				rwlxIds += rwlxlistMap.get(i).get("id");
            			}
            		}
            		commWorkManager.saveTaskTypeMultiple(work.getId(), rwlxIds, cur);
            	}
            	//执法对象类型
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //调取保存执法对象info
          		List<Map<String, String>> listMap = commWorkManager
          				.zfdxTableData(sjWork.getId());
          		if(listMap!=null && listMap.size() > 0){
          			for(int i=0;i<listMap.size();i++){
          				TBizTaskandlawobj tBizTaskandlawobj2 = new TBizTaskandlawobj();
          				tBizTaskandlawobj2.setTaskid(work.getId());
          				tBizTaskandlawobj2.setLawobjid(listMap.get(i).get("lawobjid"));
          				tBizTaskandlawobj2.setLawobjname(listMap.get(i).get("lawobjname"));
          				tBizTaskandlawobj2.setLawobjtype(listMap.get(i).get("lawobjtype"));
          				tBizTaskandlawobj2.setManager(listMap.get(i).get("manager"));
          				tBizTaskandlawobj2.setManagermobile(listMap.get(i).get("managermobile"));
              			tBizTaskandlawobj2.setBjcr(listMap.get(i).get("bjcr"));
              			tBizTaskandlawobj2.setRegionid(listMap.get(i).get("region"));
              			tBizTaskandlawobj2.setAddress(listMap.get(i).get("address"));
              			tBizTaskandlawobj2.setZw(listMap.get(i).get("zw"));
              			tBizTaskandlawobj2.setZwtitle(StringUtils.isNotBlank(listMap.get(i).get("zwtitle"))?listMap.get(i).get("zwtitle"):"法定代表人");
              			tBizTaskandlawobj2.setLxdh(listMap.get(i).get("lxdh"));
              			tBizTaskandlawobj2.setIsActive("Y");
              			tBizTaskandlawobj2.setCreateby(cur);
              			tBizTaskandlawobj2.setUpdateby(cur);
              			tBizTaskandlawobj2.setCreated(new Date());
              			tBizTaskandlawobj2.setUpdated(new Date());
              			this.dao.save(tBizTaskandlawobj2);
          			}
          		}
            }
        }
        
        //任务派发时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.PFR.getCode());//派发人
        
        this.dao.save(taskuser);
        
        
        /////////////下载下派附件并存储/////////////
        saveDownloadXPFile(work.getId(),frm.getPFileInfo(),cur);
        /////////////下派历史批示信息存储/////////////
        saveXPLsps(work.getId(),frm.getPLspsInfo());
        
        
        /************流程参数***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //申请单编号
        bean.setApplyId(work.getId());
        //当前操作人
        bean.setCurrentOper(work.getCreateUser());
        //下步操作人 （2015-3-17 修改 下一步操作人为下派区域的领导）  
        bean.getNextOpers().add(cur);
        /*bean.setNextOpers(userManager.getXpUsers(areaId));*/
        //转向
        bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
        bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
        /************流程参数***********/
        /*//启动流程
        ProcessInstance pi = processManager.saveStart(this.KEY, work, bean);
        //当前任务
        Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(this.KEY, task.getId(), work,
                bean);
        }*/
        
        //加一秒
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);    
        calendar.add(Calendar.SECOND, 1);    
        Date curDate=calendar.getTime();  
        //this.saveLog(cur, curDate, WorkLogType.PF, WorkState.YPF, work, curDate);
    }

    /**
     * 派发-下派地市
     * @param frm 任务信息
     * @param areaId 区域id
     * @throws Exception
     */
    public void savePfXpds(WorkDto frm, String areaId) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = Calendar.getInstance().getTime();
        //填充WORK对象
        Work work = new Work();
        work.setCreateTime(date);
        work.setCreateUser(cur);
        Date start = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getStartTime());
        Date end = DateUtil.getEndDatetime(frm.getEndTime());
        work.setEndTime(end);
        work.setIsActive(YnEnum.Y.getCode());
        work.setStartTime(start);
        work.setName(frm.getWorkName());
        work.setWorkNote(frm.getWorkNote());
        work.setAreaid(cur.getAreaId());
        work.setState(WorkState.YXP.getCode());
        work.setXpCity(areaId);
        /**审核人**/
        work.setShrids(CtxUtil.getCurUser().getId().concat(","));
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        workDao.save(work);
        /************流程参数***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //申请单编号
        bean.setApplyId(work.getId());
        //当前操作人
        bean.setCurrentOper(work.getCreateUser());
        //下步操作人
        bean.getNextOpers().add(cur);
        //转向
        bean.setDirection(WorkTransferDirectionEnum.PF_XPDS);
        bean.setResult(WorkTransferDirectionEnum.PF_XPDS.getText());
        /************流程参数***********/
        //启动流程
        ProcessInstance pi = processManager.saveStart(this.KEY,
            work, bean);
        //当前任务
        Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(this.KEY, task.getId(), work,
                bean);
        }
        this.saveLog(cur, date, WorkLogType.PF, WorkState.YPF, work, date);
        //下派操作
        workClient.createRwToArea(new WorkDto(work), areaId,"");
    }

    /**
     * 只有办公室人员能下派任务
     * 
     * @return 
     */
    public boolean hasXpds() {
        UserPosition p = orgManager.getPosition(CtxUtil.getUserId());
        return p == UserPosition.BGS;
    }
    
    /**
     * 
     * 函数介绍：下载下派附件并存储
     * 输入参数：
     * 返回值：
     */
    public void saveDownloadXPFile(String workid,List<Map<String, String>> pFileInfo,TSysUser giveUser){
    	if(null!=pFileInfo){
        	for(int i=0;i<pFileInfo.size();i++){
        		Map<String, String> map=pFileInfo.get(i);
        		String name=map.get("name");//文件真实名称
        		String size=map.get("size");//文件size
        		String osname=map.get("osname");//磁盘上显示的名称（32位字符）
        		String url=map.get("url");//ip
        		String path=map.get("path");//相对路径
        		String ftpuser=map.get("ftpuser");//ftpuser
        		String ftppass=map.get("ftppass");//ftppass
        		String ftpport=map.get("ftpport");//ftpport
        		
        		InputStream is=commonManager.downFtpFile(url, path, osname, ftpuser, ftppass,Integer.valueOf(ftpport));
        		//下载附件保存为下派任务的“派发附件”
        		try {
					commonManager.saveFile(is, workid, FileTypeEnums.RWGLPFFJ.getCode(), "work", name, Long.valueOf(size),giveUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
        		
        	}
        }
    }
    /**
     * 
     * 函数介绍：下派历史批示信息存储
     * 输入参数：
     * 返回值：
     * @throws ParseException 
     */
    public void saveXPLsps(String workid,List<Map<String, String>> pLspsInfo) throws ParseException{
    	if(null!=pLspsInfo){
        	for(int i=0;i<pLspsInfo.size();i++){
        		Map<String, String> map=pLspsInfo.get(i);
            	
        		String czrId=map.get("czrId");//操作人id
        		String czrName=map.get("czrName");//操作人name
        		String czsj=map.get("czsj");//操作时间
        		String note=map.get("note");//批示意见
        		String operateType=map.get("operateType");//操作类型
        		String workSate=map.get("workSate");//当时任务状态
        		String startTime=map.get("startTime");//开始时间
        		
        		Work work = workManager.get(workid);
        		
        		WorkLog lo = new WorkLog();
                lo.setCzrId(czrId);
                lo.setCzrName(czrName);
                lo.setCzsj(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",czsj));
                lo.setWork(work);
                lo.setOperateType(operateType);
                lo.setWorkSate(workSate);
                lo.setStartTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",startTime));
                lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
                
                //批示意见
                lo.setNote(note);
                
                this.workDao.save(lo);
        		
        	}
        }
    }
    
}
