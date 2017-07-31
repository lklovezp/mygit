/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.manager.WorkRyManager;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.hzws.WorkParaBeanBase;
import com.hnjz.hzws.WorkflowService;
import com.hnjz.hzws.WorkflowServiceService;
import com.hnjz.hzws.WorkParaBeanBase.FlowVarMap;
import com.hnjz.hzws.WorkParaBeanBase.SelectAuthorMap;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;
import com.hnjz.sys.user.UserPosition;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 任务转派的实现
 * 
 * @author wumi
 * @version $Id: ZpNode.java, v 0.1 Jan 29, 2013 9:37:32 AM wumi Exp $
 */
@Service(value = "zpNode")
public class ZpNode extends BaseNode implements WorkNode {

    @Autowired
	private CommonManager commonManager;
    
    @Autowired
	private CommWorkManager commWorkManager;
    
    @Autowired
	protected Dao dao;
    
    @Autowired
	private OrgManager orgManager;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;

    /**
     * 
     * @see com.hnjz.app.work.manager.WorkNode#execute(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
     */
    @Override
    public void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                            throws Exception {
        Date date = new Date();
        Work work = workDao.get(applyId);
        work.setState(WorkState.YZP.getCode());
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        String jsr = String.valueOf(ctx.get("jsrId"));
        work.setJsr(jsr);
        if(StringUtils.isNotBlank(String.valueOf(ctx.get("psyj"))) && null != ctx.get("psyj")){
        	work.setPsyj(String.valueOf(ctx.get("psyj")));//批示意见
        }
        
        //记录派发人及被跨级的人员做为将来审核人
        //StringBuffer shrIds = new StringBuffer(work.getShrids() == null ? "" : work.getShrids());
        /**
         * 2015-5-28修改：转派的时候加判断，如果是总队长转派，那就要去掉之前派发时候保存的shrIds（也就是总队长自己），
         * 以免在转派的时候多记录一份审核人（总队长自己），造成后续审核时总队长既能“提交上级审核”也能“归档”。
         */
        UserPosition p = orgManager.getPosition(cur.getId());
        
        StringBuffer shrIds = new StringBuffer();
        //TSysUser xfzy = userManager.getUser(cur.getId());
        //List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
        //List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
        //List<TSysUserRole> jsrxfzy = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", jsr);
        //List<TSysUserRole> jsrrczy = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", jsr);
        String rwlxIds = "";
        // 任务类型列表
		List<Map<String, String>> rwlxlistMap = commWorkManager
				.getTaskTypeByTaskId(work.getId());
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
        if(work.getShrids() == null || p == UserPosition.ZD){
        	shrIds=new StringBuffer("");
        }else{
        	shrIds=new StringBuffer(work.getShrids());
        }
        
        //shrIds.append(String.valueOf(ctx.get("jsrId"))).append(",");
        //将跨级人员添加到审核人员集合中
        String pfr = cur.getId();
        List<TSysUser> userList = orgManager.getUsers(pfr, jsr, work.getIsxp());
        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i).getId();
            if (StringUtils.equals(userId, String.valueOf(ctx.get("jsrId")))) {
                continue;
            }
            /*if("24".equals(rwlxIds) || "13".equals(rwlxIds)){
            	if(xfzydata.size() > 0 && "13".equals(rwlxIds)){
            		TSysOrg jsrorg = userManager.getOrgbyUser(jsr);
                    TSysOrg pfrorg = userManager.getOrgbyUser(pfr);
            		if (!StringUtils.equals(jsrorg.getId(), pfrorg.getId())){
                		shrIds.append(userId).append(",").append(pfr);
                	}else{
                		if(pfr.equals(userId)){
                			shrIds.append(userId);
                		}else{
                			shrIds.append(userId).append(",").append(pfr);
                		}
                	}
            	}else if(rczydata.size() > 0 && "24".equals(rwlxIds)){
            		TSysOrg jsrorg = userManager.getOrgbyUser(jsr);
                    TSysOrg pfrorg = userManager.getOrgbyUser(pfr);
            		if (!StringUtils.equals(jsrorg.getId(), pfrorg.getId())){
                		shrIds.append(userId).append(",").append(pfr);
                	}else{
                		if(pfr.equals(userId)){
                			shrIds.append(userId);
                		}else{
                			shrIds.append(userId).append(",").append(pfr);
                		}
                	}
            	}else{
            		shrIds.append(userId).append(",");
            	}
            }else{*/
            	shrIds.append(userId).append(",");
            //}
        }
        work.setShrids(shrIds.toString());
        
        //添加完审核人，过滤掉因为角色导致的重复添加
  		if(StringUtils.isNotBlank(work.getShrids())){
  			String[] wrlx = work.getShrids().split(",");
  			String aa = "";
  	        for(int i=0;i<wrlx.length;i++){
  	        	if(wrlx[i] != null && wrlx[i] != ""){
  	        		if(!aa.contains(wrlx[i])){
  						aa += wrlx[i]+",";
  					}
  	        	}
  	         }  
  	      work.setShrids(aa);
  		}
        
        if (log.isDebugEnabled()) {
            String[] rys = work.getShrids().split(",");
            for (String ele : rys) {
            	TSysUser s = this.userManager.getUser(ele);
                log.debug("审核用户：" + s.getName());
            }
        }
        
        /***********下步操作**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
        //科员不能转派
        if (!orgManager.getPosition(String.valueOf(ctx.get("jsrId"))).equals(UserPosition.KY)) {
            nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
        }
        //科员角色的专员作为接收人的信访，日常任务添加上转派的操作
        /*if(jsrxfzy.size() != 0 && "13".equals(rwlxIds)){
        	if(orgManager.getPosition(String.valueOf(ctx.get("jsrId"))).equals(UserPosition.KY)) {
                nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
            }
        }
        if(jsrrczy.size() != 0 && "24".equals(rwlxIds)){
        	if(orgManager.getPosition(String.valueOf(ctx.get("jsrId"))).equals(UserPosition.KY)) {
                nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
            }
        }*/
        
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
        /************流程参数***********/
        //下步操作人
        TSysUser tuser = (TSysUser) userManager.get(TSysUser.class, ctx.get("jsrId").toString());
        //workflowoperator.open(cur.getId(), work.getShiliid());//先打开实例
        if("zfjczlc".equals(work.getFlowid())){
        	if(StringUtils.isNotBlank(work.getShiliid())){
            	//增加慧正工作流中的转派操作
                workflowoperator.submit(tuser.getId(), work.getShiliid(), work.getTrackid(), "Line4~Node4", "" , tuser.getUsername(), "");
            }else{
            	//增加慧正工作流中的转派操作
            	workflowoperator.create("zfjczlc", tuser.getId());
            }
        }else{
        	if(StringUtils.isNotBlank(work.getShiliid())){
            	//增加慧正工作流中的转派操作
                workflowoperator.submit(tuser.getId(), work.getShiliid(), work.getTrackid(), "", "", tuser.getUsername(), "");
            }else{
            	//增加慧正工作流中的转派操作
            	workflowoperator.create("generalTask1", tuser.getId());
            }
        }
        
        //ProcessArgsBean bean = new ProcessArgsBean();
        //bean.getNextOpers().add(tuser);
        //转向
        //bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
        //bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
        /************流程参数***********/
        //processManager.saveNext(key, taskId, work, bean);

        this.workDao.save(work);
        
        //任务转派时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.ZPR.getCode());//转派人
        
        this.dao.save(taskuser);
        
        
        Calendar cStart = Calendar.getInstance();//此任务开始时间
        cStart.setTime(stepStartTime);
        Calendar cOper = Calendar.getInstance();//操作时间
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
        	TSysUser ele = CtxUtil.getCurUser();
            saveLog(ele, cOper.getTime(), WorkLogType.ZF, WorkState.YPF, work, cStart.getTime());
        } else {
            for (int i = zj; i < userList.size(); i++) {
            	TSysUser ele = userList.get(i);
                if (StringUtils.equals(ele.getId(), jsr)) {
                    if (log.isDebugEnabled()) {
                        log.debug("转派人：" + ele.getName() + "和接收人相同，不保存");
                    }
                    continue;
                }
                saveLog(ele, cOper.getTime(), WorkLogType.ZF, WorkState.YPF, work, cStart.getTime());
                //当跨多个人时，为了排序，每个人的开始和结束时间都+1秒
                cStart.add(Calendar.SECOND, 1);
                cOper.add(Calendar.SECOND, 1);
            }
        }

        /*TSysUser l = userManager.getUser(jsr);
        workRyManager.saveRy(l, WorkLogType.ZF, applyId);
        
        if(null != tuser) {
        	gotoneManager.saveWorkStep(tuser.getId(), "任务("+work.getName()+")已经转派给您，请尽快处理");
        }*/
        
    }

}
