/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 任务的审核操作
 * 
 * @author wumi
 * @version $Id: ShNode.java, v 0.1 Jan 29, 2013 9:47:31 AM wumi Exp $
 */
@Service(value = "shNode")
public class ShNode extends BaseNode implements WorkNode {

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
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
       
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setOpinion(ctx.get("opinion") == null ? "" : ctx.get("opinion").toString());
        Boolean passed = (Boolean) ctx.get("passed");
        if("zfjczlc".equals(work.getFlowid())){
            Work sjwork = workDao.get(work.getSjid());//上级任务
        	if (!passed) {//上级审核
           	 /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               work.setZxrId(shrIds[shrIds.length - 1]);
               work.setJsr(shrIds[shrIds.length - 1]);
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               if(shrIds.length > 1){
               	TSysUser po = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 2]);
               	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line4~Node4", po.getId() , po.getName(),"");
               	work.setJsr(po.getId());
               	work.setZxrId(po.getId());
               }else{
               	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", cur.getId(), cur.getName(),"");
               }
               StringBuffer nextAction = new StringBuffer();
               nextAction.append(WorkProcessEnum.SH.getCode()).append(",");
               work.setNextActions(nextAction.toString());
               bean.setDirection(WorkTransferDirectionEnum.SH_SJ);
               bean.setResult(WorkTransferDirectionEnum.SH_SJ.getText());
               work.setState(WorkState.YSH.getCode());
           } else {//审核通过(归档)
           	 /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               TSysUser po = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 1]);
               //子流程上报提交结束
               workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line6~End6", shrIds[shrIds.length - 1] , po.getName(),"");
           	   //主流程的继续进行下一步操作
               workflowoperator.submit(sjwork.getCreateUser().getId(), sjwork.getShiliid(), sjwork.getTrackid(), "Line23~Node5", work.getCreateUser().getId() , work.getCreateUser().getName(),"");
               bean.setDirection(WorkTransferDirectionEnum.SH_TG);
               bean.setResult(WorkTransferDirectionEnum.SH_TG.getText());
               work.setState(WorkState.YGD.getCode());
               work.setGdsj(date);
               work.setGdUser(cur);
               work.setIsOver(true);
           }
        }else{
        	if (!passed) {//上级审核
           	 /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               work.setZxrId(shrIds[shrIds.length - 1]);
               work.setJsr(shrIds[shrIds.length - 1]);
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               if(shrIds.length > 1){
               	TSysUser po = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 2]);
               	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", po.getId() , po.getName(),"");
               	work.setJsr(po.getId());
               	work.setZxrId(po.getId());
               }else{
               	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", cur.getId(), cur.getName(),"");
               }
               StringBuffer nextAction = new StringBuffer();
               nextAction.append(WorkProcessEnum.SH.getCode()).append(",");
               work.setNextActions(nextAction.toString());
               bean.setDirection(WorkTransferDirectionEnum.SH_SJ);
               bean.setResult(WorkTransferDirectionEnum.SH_SJ.getText());
               work.setState(WorkState.YSH.getCode());
           } else {//审核通过(归档)
           	 /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
               TSysUser po = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 1]);
               workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line11~End7", shrIds[shrIds.length - 1] , po.getName(),"");
           	
               bean.setDirection(WorkTransferDirectionEnum.SH_TG);
               bean.setResult(WorkTransferDirectionEnum.SH_TG.getText());
               work.setState(WorkState.YGD.getCode());
               work.setGdsj(date);
               work.setGdUser(cur);
               work.setIsOver(true);
           }
        }
        //processManager.saveNext(key, taskId, work, bean);
        //批示意见
        work.setPsyj(ctx.get("opinion") == null ? "" : ctx.get("opinion").toString());
        
        this.workDao.save(work);
        WorkLogType opType = WorkLogType.SH;
        WorkState state = WorkState.YSH;
        if (passed) {
            opType = WorkLogType.GD;
            state = WorkState.YGD;
        }
        this.saveLog(cur, date, opType, state, work, stepStartTime);
        
        //记录转派操作到短信提醒或通知
//        WorkProcesssStaff wps = workProcesssStaffFactory.getInstance(WorkProcessEnum.SH.getCode());
//        //设置人员
//        List<String> list = wps.getUsers(bean);
//        for(String uid : list) {
//        	if(!StringUtils.isBlank(uid)) {
//        		User tuser = (User) userManager.get(User.class, uid);
//        		if(null != tuser) {
//                	gotoneManager.saveWorkStep(tuser.getId(), "任务("+work.getName()+")已经提交给您审核，请尽快处理");
//                }
//        	}
//        }
    }
}
