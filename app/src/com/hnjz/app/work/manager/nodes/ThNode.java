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
 * �˻ز���
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
        //���ϴ��������ʱ����Ϊ���˲���Ŀ�ʼʱ��
        Date startTime = work.getUpdateTime();
        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        work.setState(WorkState.YTH.getCode());
        // �������в�ѯ�˻����ֶ����Ƿ���ֵ��
        // �����ֵ����˲��˻ص����˻���ˡ���
        // ���û��ֵ����˲��˻ص����˻�ִ�С���
        String thrids = work.getThrids();
        //���̲���
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setOpinion(note);
        //Ĭ��Ϊ���˻�ִ�С�,�²�ִ�в����У�ִ��
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.ZX.getCode()).append(",");
        //nextAction.append(WorkProcessEnum.SBYJ.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        //�����²�ת��Ϊ���˻�ִ�С�
        bean.setDirection(WorkTransferDirectionEnum.SH_THZX);
        //���ô˲�����ִ�н��
        bean.setResult(WorkTransferDirectionEnum.SH_THZX.getText());
        if (StringUtils.isNotBlank(thrids)) {
            String[] thridArr = thrids.split(",");
            if (thridArr.length > 0) {//�˻ص����
                //���˻���ˡ�,�²�ִ�в����У����
                nextAction = new StringBuffer();
                nextAction.append(WorkProcessEnum.SH.getCode()).append(",");
                work.setNextActions(nextAction.toString());
                //�����²�ת��Ϊ���˻�ִ�С�
                bean.setDirection(WorkTransferDirectionEnum.SH_THSH);
                //���ô˲�����ִ�н�����˲�ת��ͬ���ϼ���ˡ������ִ�н������Ϊ���˻���ˡ���
                bean.setResult("�˻����");
                work.setZxrId(thridArr[thridArr.length - 1]);
                work.setJsr(thridArr[thridArr.length - 1]);
                StringBuffer thrIdsBuffer = new StringBuffer();
                for (int i = 0; i < thridArr.length - 1; i++) {
                	thrIdsBuffer.append(thridArr[i]).append(",");
                }
                work.setThrids(thrIdsBuffer.toString());
            }
        }else{
        	//���������ô������
        	TSysUser user = null;
    		String hsql = "select ur from TSysUser ur where ur.isActive = 'Y' and ur.name = ?";
    		List<TSysUser> re = this.dao.find(hsql, work.getZxrName());
    		user = re.get(0);
        	work.setZxrId(user.getId());
            work.setJsr(user.getId());
        }
        //processManager.saveNext(key, taskId, work, bean);
        
        //��ʾ���
        work.setPsyj(note == null ? "" : note);
        
        this.workDao.save(work);
        this.saveLog(cur, date, WorkLogType.TH, WorkState.YTH, work, startTime);
    }
}