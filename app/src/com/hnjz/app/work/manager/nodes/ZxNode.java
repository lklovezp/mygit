/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.manager.WorkRyManager;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.AppException;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ����ִ����ϵĲ���(�ϱ��ƽ�)
 * 
 * @author wumi
 * @version $Id: ZxNode.java, v 0.1 Jan 29, 2013 9:54:33 AM wumi Exp $
 */
@Service(value = "zxNode")
public class ZxNode extends BaseNode implements WorkNode {
    @Autowired
    private WorkRyManager workRyManager;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;
    
    @Autowired
	private UserManager userManager;

    @Override
    public void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                            throws AppException,
                                                                                            Exception {
        Date date = new Date();
        Work work = workDao.get(applyId);
        work.setState(WorkState.YBL.getCode());
        //���ϴ��������ʱ����Ϊ���˲���Ŀ�ʼʱ��
        Date stepStartTime = work.getUpdateTime();
        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        work.setZxtime(Calendar.getInstance().getTime());
        /***********�²�����**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.SH.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        /***********�²�����**********/
        /*ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setResult(WorkProcessEnum.ZX.getText());
        processManager.saveNext(key, taskId, work, bean);
        work.getShrids();*/
        if("zfjczlc".equals(work.getFlowid())){
        	if(StringUtils.isNotBlank(work.getShrids())){
            	String[] ryIds = work.getShrids().split(",");
            	TSysUser po = (TSysUser) userManager.get(TSysUser.class, ryIds[ryIds.length - 1]);
            	if(ryIds.length == 2){
            		workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line3~Node3", ryIds[ryIds.length - 1], po.getName(),"");
            	}else if(ryIds.length == 1){
            		workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line4~Node4", ryIds[ryIds.length - 1], po.getName(),"");
            	}
            }else{
            	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", cur.getId(), cur.getName(),"");
            }
        }else{
        	if(StringUtils.isNotBlank(work.getShrids())){
            	String[] ryIds = work.getShrids().split(",");
            	TSysUser po = (TSysUser) userManager.get(TSysUser.class, ryIds[ryIds.length - 1]);
            	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", ryIds[ryIds.length - 1], po.getName(),"");
            }else{
            	workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line5~Node5", cur.getId(), cur.getName(),"");
            }
        }
        
        /**�������񱨸����Ӧ�������Ա**/
        String[] shrIds = work.getShrids().split(",");
        work.setZxrId(shrIds[shrIds.length - 1]);
        work.setJsr(shrIds[shrIds.length - 1]);
        //��ʾ���Ϊ��ִ�С�
        work.setPsyj(WorkLogType.ZX.getText());
        
        this.workDao.save(work);
        
        //��¼ִ����
        saveLog(work.getZxrId(), work.getZxrName(), date, WorkLogType.ZX, WorkState.YBL, work,
            stepStartTime);
//        //��¼��
//        if (null != work.getJlr()) {
//            saveLog(work.getJlr(), date, WorkLogType.JL, WorkState.YBL, work, stepStartTime);
//        }
//        //��ͬ��
//        if (StringUtils.isNotBlank(work.getPtrIds())) {
//            String[] ptrIds = work.getPtrIds().split(",");
//            TSysUser u = null;
//            for (String ele : ptrIds) {
//                u = this.userManager.getUser(ele);
//                saveLog(u, date, WorkLogType.PT, WorkState.YBL, work, stepStartTime);
//            }
//        }
        
//       workRyManager.saveZx(cur, work.getJlr(), work.getPtrIds(), applyId);

//        //��¼ת�ɲ������������ѻ�֪ͨ
//        WorkProcesssStaff wps = workProcesssStaffFactory.getInstance(WorkProcessEnum.SH.getCode());
//        //������Ա
//        List<String> list = wps.getUsers(bean);
//        for (String uid : list) {
//            User tuser = (User) userManager.getUserByLoginName(uid);
//            if (null != tuser) {
//                gotoneManager.saveWorkStep(tuser.getId(), "����(" + work.getName() + ")�Ѿ��ϱ��ƽ����뾡�촦��");
//            }
//        }
    }
    
    public String getNextOperName(String applyId, String taskId) throws Exception{
    	String name="";
    	Work work = workDao.get(applyId);
    	name=processManager.getNextOperName(work);
    	
    	return name;
    }
    
}