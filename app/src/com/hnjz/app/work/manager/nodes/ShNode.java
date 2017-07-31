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
 * �������˲���
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
        //���ϴ��������ʱ����Ϊ���˲���Ŀ�ʼʱ��
        Date stepStartTime = work.getUpdateTime();
        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
       
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setOpinion(ctx.get("opinion") == null ? "" : ctx.get("opinion").toString());
        Boolean passed = (Boolean) ctx.get("passed");
        if("zfjczlc".equals(work.getFlowid())){
            Work sjwork = workDao.get(work.getSjid());//�ϼ�����
        	if (!passed) {//�ϼ����
           	 /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
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
               /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
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
           } else {//���ͨ��(�鵵)
           	 /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
               TSysUser po = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 1]);
               //�������ϱ��ύ����
               workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line6~End6", shrIds[shrIds.length - 1] , po.getName(),"");
           	   //�����̵ļ���������һ������
               workflowoperator.submit(sjwork.getCreateUser().getId(), sjwork.getShiliid(), sjwork.getTrackid(), "Line23~Node5", work.getCreateUser().getId() , work.getCreateUser().getName(),"");
               bean.setDirection(WorkTransferDirectionEnum.SH_TG);
               bean.setResult(WorkTransferDirectionEnum.SH_TG.getText());
               work.setState(WorkState.YGD.getCode());
               work.setGdsj(date);
               work.setGdUser(cur);
               work.setIsOver(true);
           }
        }else{
        	if (!passed) {//�ϼ����
           	 /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
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
               /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
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
           } else {//���ͨ��(�鵵)
           	 /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
               String[] shrIds = work.getShrids().split(",");
               StringBuffer shrIdsBuffer = new StringBuffer();
               for (int i = 0; i < shrIds.length - 1; i++) {
                   shrIdsBuffer.append(shrIds[i]).append(",");
               }
               work.setShrids(shrIdsBuffer.toString());
               work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                              + shrIds[shrIds.length - 1] + ",");
               /**��������ġ�����˼��ϡ���ȥ���˲�����ˣ���������˼��롰�˻��˼��ϡ���**/
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
        //��ʾ���
        work.setPsyj(ctx.get("opinion") == null ? "" : ctx.get("opinion").toString());
        
        this.workDao.save(work);
        WorkLogType opType = WorkLogType.SH;
        WorkState state = WorkState.YSH;
        if (passed) {
            opType = WorkLogType.GD;
            state = WorkState.YGD;
        }
        this.saveLog(cur, date, opType, state, work, stepStartTime);
        
        //��¼ת�ɲ������������ѻ�֪ͨ
//        WorkProcesssStaff wps = workProcesssStaffFactory.getInstance(WorkProcessEnum.SH.getCode());
//        //������Ա
//        List<String> list = wps.getUsers(bean);
//        for(String uid : list) {
//        	if(!StringUtils.isBlank(uid)) {
//        		User tuser = (User) userManager.get(User.class, uid);
//        		if(null != tuser) {
//                	gotoneManager.saveWorkStep(tuser.getId(), "����("+work.getName()+")�Ѿ��ύ������ˣ��뾡�촦��");
//                }
//        	}
//        }
    }
}