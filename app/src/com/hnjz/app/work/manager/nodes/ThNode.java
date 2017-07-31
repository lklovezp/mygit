/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager.nodes;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.po.Work;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.common.dao.Dao;

/**
 * 退回操作
 * @author wumi
 * @version $Id: ThNode.java, v 0.1 Jan 29, 2013 9:49:43 AM wumi Exp $
 */
@Service(value = "thNode")
public class ThNode extends BaseNode implements WorkNode {

	@Autowired
	private Dao dao;
	
    @Override
    public void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                            throws Exception {
        Date date = new Date();
        Work work = workDao.get(applyId);
        //将上次任务更新时间做为本此步骤的开始时间
        Date startTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        work.setState(WorkState.YTH.getCode());
        // 从主表中查询退回人字段中是否有值，
        // 如果有值，则此步退回到“退回审核”；
        // 如果没有值，则此步退回到“退回执行”。
        String thrids = work.getThrids();
        //流程参数
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setOpinion(note);
        //默认为“退回执行”,下步执行操作有：执行
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.ZX.getCode()).append(",");
        //nextAction.append(WorkProcessEnum.SBYJ.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        //设置下步转向为“退回执行”
        bean.setDirection(WorkTransferDirectionEnum.SH_THZX);
        //设置此步任务执行结果
        bean.setResult(WorkTransferDirectionEnum.SH_THZX.getText());
        if (StringUtils.isNotBlank(thrids)) {
            String[] thridArr = thrids.split(",");
            if (thridArr.length > 0) {//退回到审核
                //“退回审核”,下步执行操作有：审核
                nextAction = new StringBuffer();
                nextAction.append(WorkProcessEnum.SH.getCode()).append(",");
                work.setNextActions(nextAction.toString());
                //设置下步转向为“退回执行”
                bean.setDirection(WorkTransferDirectionEnum.SH_THSH);
                //设置此步任务执行结果（此步转向同“上级审核”，因此执行结果设置为“退回审核”）
                bean.setResult("退回审核");
                work.setZxrId(thridArr[thridArr.length - 1]);
                work.setJsr(thridArr[thridArr.length - 1]);
                StringBuffer thrIdsBuffer = new StringBuffer();
                for (int i = 0; i < thridArr.length - 1; i++) {
                	thrIdsBuffer.append(thridArr[i]).append(",");
                }
                work.setThrids(thrIdsBuffer.toString());
            }
        }else{
        	//如果重名怎么调整？
        	TSysUser user = null;
    		String hsql = "select ur from TSysUser ur where ur.isActive = 'Y' and ur.name = ?";
    		List<TSysUser> re = this.dao.find(hsql, work.getZxrName());
    		user = re.get(0);
        	work.setZxrId(user.getId());
            work.setJsr(user.getId());
        }
        //processManager.saveNext(key, taskId, work, bean);
        
        //批示意见
        work.setPsyj(note == null ? "" : note);
        
        this.workDao.save(work);
        this.saveLog(cur, date, WorkLogType.TH, WorkState.YTH, work, startTime);
    }
}
