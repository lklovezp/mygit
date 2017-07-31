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
 * ����ת�ɵ�ʵ��
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
        //���ϴ��������ʱ����Ϊ���˲���Ŀ�ʼʱ��
        Date stepStartTime = work.getUpdateTime();
        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        String jsr = String.valueOf(ctx.get("jsrId"));
        work.setJsr(jsr);
        if(StringUtils.isNotBlank(String.valueOf(ctx.get("psyj"))) && null != ctx.get("psyj")){
        	work.setPsyj(String.valueOf(ctx.get("psyj")));//��ʾ���
        }
        
        //��¼�ɷ��˼����缶����Ա��Ϊ���������
        //StringBuffer shrIds = new StringBuffer(work.getShrids() == null ? "" : work.getShrids());
        /**
         * 2015-5-28�޸ģ�ת�ɵ�ʱ����жϣ�������ܶӳ�ת�ɣ��Ǿ�Ҫȥ��֮ǰ�ɷ�ʱ�򱣴��shrIds��Ҳ�����ܶӳ��Լ�����
         * ������ת�ɵ�ʱ����¼һ������ˣ��ܶӳ��Լ�������ɺ������ʱ�ܶӳ����ܡ��ύ�ϼ���ˡ�Ҳ�ܡ��鵵����
         */
        UserPosition p = orgManager.getPosition(cur.getId());
        
        StringBuffer shrIds = new StringBuffer();
        //TSysUser xfzy = userManager.getUser(cur.getId());
        //List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
        //List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
        //List<TSysUserRole> jsrxfzy = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", jsr);
        //List<TSysUserRole> jsrrczy = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", jsr);
        String rwlxIds = "";
        // ���������б�
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
        //���缶��Ա���ӵ������Ա������
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
        
        //����������ˣ����˵���Ϊ��ɫ���µ��ظ�����
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
                log.debug("����û���" + s.getName());
            }
        }
        
        /***********�²�����**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
        //��Ա����ת��
        if (!orgManager.getPosition(String.valueOf(ctx.get("jsrId"))).equals(UserPosition.KY)) {
            nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
        }
        //��Ա��ɫ��רԱ��Ϊ�����˵��ŷã��ճ�����������ת�ɵĲ���
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
        
        //2015-3-17 ���ӡ����ɡ������ܡ�ת�ɡ�������ͬһ����Ĳ����������û���¼���λ����ʾ�����ɡ�������
        Boolean hasXJDW = true;//Ĭ�Ͽ������ɡ�
        List<Combobox> xjdslist = commonManager.queryAreaCombo(cur.getAreaId());
        if(xjdslist==null||xjdslist.size()==0){
        	hasXJDW = false;
        }
        if(hasXJDW){
            nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
        }
        
        work.setNextActions(nextAction.toString());
        /***********�²�����**********/
        /************���̲���***********/
        //�²�������
        TSysUser tuser = (TSysUser) userManager.get(TSysUser.class, ctx.get("jsrId").toString());
        //workflowoperator.open(cur.getId(), work.getShiliid());//�ȴ�ʵ��
        if("zfjczlc".equals(work.getFlowid())){
        	if(StringUtils.isNotBlank(work.getShiliid())){
            	//���ӻ����������е�ת�ɲ���
                workflowoperator.submit(tuser.getId(), work.getShiliid(), work.getTrackid(), "Line4~Node4", "" , tuser.getUsername(), "");
            }else{
            	//���ӻ����������е�ת�ɲ���
            	workflowoperator.create("zfjczlc", tuser.getId());
            }
        }else{
        	if(StringUtils.isNotBlank(work.getShiliid())){
            	//���ӻ����������е�ת�ɲ���
                workflowoperator.submit(tuser.getId(), work.getShiliid(), work.getTrackid(), "", "", tuser.getUsername(), "");
            }else{
            	//���ӻ����������е�ת�ɲ���
            	workflowoperator.create("generalTask1", tuser.getId());
            }
        }
        
        //ProcessArgsBean bean = new ProcessArgsBean();
        //bean.getNextOpers().add(tuser);
        //ת��
        //bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
        //bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
        /************���̲���***********/
        //processManager.saveNext(key, taskId, work, bean);

        this.workDao.save(work);
        
        //����ת��ʱ������Ա��Ϣ��������Ա��Ϣ T_BIZ_TASKUSER��
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.ZPR.getCode());//ת����
        
        this.dao.save(taskuser);
        
        
        Calendar cStart = Calendar.getInstance();//������ʼʱ��
        cStart.setTime(stepStartTime);
        Calendar cOper = Calendar.getInstance();//����ʱ��
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
                        log.debug("ת���ˣ�" + ele.getName() + "�ͽ�������ͬ��������");
                    }
                    continue;
                }
                saveLog(ele, cOper.getTime(), WorkLogType.ZF, WorkState.YPF, work, cStart.getTime());
                //��������ʱ��Ϊ������ÿ���˵Ŀ�ʼ�ͽ���ʱ�䶼+1��
                cStart.add(Calendar.SECOND, 1);
                cOper.add(Calendar.SECOND, 1);
            }
        }

        /*TSysUser l = userManager.getUser(jsr);
        workRyManager.saveRy(l, WorkLogType.ZF, applyId);
        
        if(null != tuser) {
        	gotoneManager.saveWorkStep(tuser.getId(), "����("+work.getName()+")�Ѿ�ת�ɸ������뾡�촦��");
        }*/
        
    }

}