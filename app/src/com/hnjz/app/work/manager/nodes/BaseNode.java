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
 * һЩ����ڵ�Ĺ�������
 * 
 * @author wumi
 * @version $Id: BaseNode.java, v 0.1 Jul 19, 2013 2:12:37 PM wumi Exp $
 */
public class BaseNode {

    /**��־*/
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
     * ��¼����Ĳ�����־
     * 
     * @param czr ������
     * @param czsj ����ʱ��
     * @param opType �������� {@link WorkLogType}
     * @param state ��ǰ����״̬
     * @param work ����
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
        
        //��ʾ���
        if(WorkState.JS.getCode().equals(work.getState())){
        	lo.setNote("");
        }else{
        	lo.setNote(work.getPsyj());
        }
        
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("���������־��" + lo);
        }
    }
    
    
    /**
     * ��¼����Ĳ�����־
     * 
     * @param czrId ������Id
     * @param czrName ������Name
     * @param czsj ����ʱ��
     * @param opType �������� {@link WorkLogType}
     * @param state ��ǰ����״̬
     * @param work ����
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
            log.debug("���������־��" + lo);
        }
    }

}