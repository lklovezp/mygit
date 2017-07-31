/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.manager.GotoneManager;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.wf.handler.target.WorkProcesssStaffFactory;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.process.ApplyProcessManager;

/**
 * 一些任务节点的公共操作
 * 
 * @author wumi
 * @version $Id: BaseNode.java, v 0.1 Jul 19, 2013 2:12:37 PM wumi Exp $
 */
public class BaseNode {

    /**日志*/
    protected  final Log      log = LogFactory.getLog(getClass());

    @Autowired
    protected WorkDao             workDao;

    @Autowired
    protected ApplyProcessManager processManager;
    
    @Autowired
    protected UserManager         userManager;

    @Autowired
    protected OrgManager          orgManager;
    
    @Autowired
    protected GotoneManager       gotoneManager;
    
    @Autowired
    protected WorkProcesssStaffFactory workProcesssStaffFactory;

    /**
     * 记录任务的操作日志
     * 
     * @param czr 操作人
     * @param czsj 操作时间
     * @param opType 操作类型 {@link WorkLogType}
     * @param state 当前任务状态
     * @param work 任务
     */
    protected void saveLog(TSysUser czr, Date czsj, WorkLogType opType, WorkState state, Work work,Date startTime) {
        WorkLog lo = new WorkLog();
        lo.setCzrId(czr.getId());
        lo.setCzrName(czr.getName());
        lo.setCzsj(czsj);
        lo.setWork(work);
        lo.setOperateType(opType.getCode());
        lo.setWorkSate(state.getCode());
        lo.setStartTime(startTime);
        lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
        
        //批示意见
        if(WorkState.JS.getCode().equals(work.getState())){
        	lo.setNote("");
        }else{
        	lo.setNote(work.getPsyj());
        }
        
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("插入操作日志：" + lo);
        }
    }
    
    
    /**
     * 记录任务的操作日志
     * 
     * @param czrId 操作人Id
     * @param czrName 操作人Name
     * @param czsj 操作时间
     * @param opType 操作类型 {@link WorkLogType}
     * @param state 当前任务状态
     * @param work 任务
     */
    protected void saveLog(String czrId,String czrName, Date czsj, WorkLogType opType, WorkState state, Work work,Date startTime) {
        WorkLog lo = new WorkLog();
        lo.setCzrId(czrId);
        lo.setCzrName(czrName);
        lo.setCzsj(czsj);
        lo.setWork(work);
        lo.setOperateType(opType.getCode());
        lo.setWorkSate(state.getCode());
        lo.setStartTime(startTime);
        lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("插入操作日志：" + lo);
        }
    }

}
