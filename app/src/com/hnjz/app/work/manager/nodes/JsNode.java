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

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.manager.WorkNode;
import com.hnjz.app.work.manager.WorkRyManager;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.TBizXfbjd;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ����Ľ��ܲ���
 * 
 * @author wumi
 * @version $Id: JsNode.java, v 0.1 Jan 29, 2013 9:52:29 AM wumi Exp $
 */
@Service(value = "jsNode")
public class JsNode extends BaseNode implements WorkNode {

    @Autowired
    private WorkRyManager workRyManager;
    
    @Autowired
    private WorkManagerImpl workManager;
    
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
    
    @Autowired
	protected Dao dao;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;

    @Override
    public void execute(String applyId, String taskId, String note, Map<String, Object> ctx, TSysUser cur)
                                                                                            throws AppException,
                                                                                            Exception {
        Date date = new Date();
        Work work = workDao.get(applyId);
        work.setState(WorkState.JS.getCode());
        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        Date startTime = work.getUpdateTime();
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        //����ǰ�˼�¼Ϊִ����
        work.setZxrId(cur.getId());
        work.setZxrName(cur.getName());
        //�²�����
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.ZX.getCode()).append(",");
        //nextAction.append(WorkProcessEnum.SBYJ.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        //���̲���
        workflowoperator.open(work.getCreateUser().getId(), work.getShiliid());
        //workflowoperator.preSubmit(work.getShiliid(), work.getTrackid(), work.getCreateUser().getId(), cur.getName());
        String result = "";
        if("zfjczlc".equals(work.getFlowid())){
        	result = workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line3~Node3", cur.getId(), cur.getName(),"");
        }else{
        	result = workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line4~Node4", cur.getId(), cur.getName(),"");
        }
        /*ProcessArgsBean bean = new ProcessArgsBean();
        bean.setDirection(WorkTransferDirectionEnum.PF_JS);
        bean.setResult(WorkTransferDirectionEnum.PF_JS.getText());
        processManager.saveNext(key, taskId, work, bean);*/
        this.workDao.save(work);
        
        //�������ʱ����������Ա��Ϣ��������Ա��Ϣ T_BIZ_TASKUSER��
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(applyId);
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.ZBR.getCode());//������
        //��ʾ���Ϊ�����ܡ�
        //work.setPsyj("");
        
        this.dao.save(taskuser);
        
        //���������˻ز�����ȡ��ʷ������Ϣ
        if(StringUtils.isNotBlank(work.getSjid())){
        	Work w = workManager.getWorkbySjid(work.getSjid(),work.getId());
        	if(w!=null){//�������ɵ�����
        		//ִ��������Ϣ��ִ����������+ִ������
    			work.setZfdxType(w.getZfdxType());
    			//����WORK����
    	        workDao.save(work);
        		//׼�����ϡ��������ϵȸ�����Ϣ
    			List<TDataFile> fileList= commonManager.queryNoSomeFileList(w.getId(),FileTypeEnums.RWGLPFFJ.getCode(),FileTypeEnums.ZXXDZRWYSB.getCode());
    			
    			if(fileList!=null && fileList.size()>0){
    				for(int k=0;k<fileList.size();k++){
    					TDataFile t= new TDataFile(work.getId(),fileList.get(k).getOsname(),fileList.get(k).getName(),fileList.get(k).getSize(),fileList.get(k).getType(),fileList.get(k).getRelativepath(),CtxUtil.getCurUser());
    					TDataFile newFile = commonManager.saveFile(t);
    					//��μ��ļ���¼
    					List<TBizMoreCheck> mcs= this.dao.find("from TBizMoreCheck where fileid = ? ", fileList.get(k).getId());
    					if(mcs!=null && mcs.size()>0){
    						TBizMoreCheck mc = new TBizMoreCheck();
        					mc.setWork(work);
        					mc.setFileid(newFile.getId());
        					mc.setTimes(mcs.get(0).getTimes());
        					mc.setTasktypecode(mcs.get(0).getTasktypecode());
        					mc.setContent(mcs.get(0).getContent());
        					mc.setIsActive("Y");
        					mc.setUpdateby(CtxUtil.getCurUser());
        					mc.setUpdated(new Date());
        					mc.setCreateby(CtxUtil.getCurUser());
        					mc.setCreated(new Date());
        					this.dao.save(mc);
    					}
        			}
    			}
    			//��ȡ����ִ������info
    			List<Map<String, String>> listMap = commWorkManager
    					.zfdxTableData(w.getId());
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
            			tBizTaskandlawobj2.setZwtitle(StringUtils.isNotBlank(listMap.get(i).get("zwtitle"))?listMap.get(i).get("zwtitle"):"����������");
            			tBizTaskandlawobj2.setLxdh(listMap.get(i).get("lxdh"));
            			tBizTaskandlawobj2.setIsActive("Y");
            			tBizTaskandlawobj2.setCreateby(cur);
            			tBizTaskandlawobj2.setUpdateby(cur);
            			tBizTaskandlawobj2.setCreated(new Date());
            			tBizTaskandlawobj2.setUpdated(new Date());
            			this.dao.save(tBizTaskandlawobj2);
    				}
    			}
    			//����������Ϣ
    			List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(w.getId());
    			for (int i = 0; i < rwlxlistMap.size(); i++) {
    				//��ȡ������ʱ�䡢���ص㡢���ģ�塢�����۵���Ϣ
        			List<TBizTaskandtasktype> pos = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", w.getId(),rwlxlistMap.get(i).get("id"));
        			if(pos!=null && pos.size()>=0){
        				TBizTaskandtasktype taskandtasktype = new TBizTaskandtasktype();
        				taskandtasktype.setTaskid(work.getId());
        				taskandtasktype.setTasktypeid(rwlxlistMap.get(i).get("id"));
        				taskandtasktype.setJcdd(pos.get(0).getJcdd());//���ص�
        				taskandtasktype.setJcjl(pos.get(0).getJcjl());//������
        				taskandtasktype.setJcmb(pos.get(0).getJcmb());//���ģ��
        				taskandtasktype.setJcsj1(pos.get(0).getJcsj1());//���ʱ��
        				taskandtasktype.setJcsj2(pos.get(0).getJcsj2());//���ʱ��
        				//�ŷ�Ͷ��
        				if("13".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setXftsly(pos.get(0).getXftsly());//�ŷ�Ͷ����Դ
        					//�ŷ�Ͷ�߰������
        					//��������õ��ŷð�ᵥ
        					List<TBizXfbjd> xfbjds = this.dao.find(" from TBizXfbjd where rwid= ? ", w.getId());
        					if(xfbjds!=null && xfbjds.size()>0){
        						TBizXfbjd xfbjd = new TBizXfbjd();
    							xfbjd.setArea(xfbjds.get(0).getArea());
        						xfbjd.setBcqk(xfbjds.get(0).getBcqk());
        						xfbjd.setBcr(xfbjds.get(0).getBcr());
        						xfbjd.setBcrq(xfbjds.get(0).getBcrq());
        						xfbjd.setBjqk(xfbjds.get(0).getBjqk());
        						xfbjd.setDescribe(xfbjds.get(0).getDescribe());
        						xfbjd.setFhr(xfbjds.get(0).getFhr());
        						xfbjd.setFhrq(xfbjds.get(0).getFhrq());
        						xfbjd.setHfr(xfbjds.get(0).getHfr());
        						xfbjd.setHfrq(xfbjds.get(0).getHfrq());
        						xfbjd.setHfxs(xfbjds.get(0).getHfxs());
        						xfbjd.setHfxsdyrn(xfbjds.get(0).getHfxsdyrn());
        						xfbjd.setHfxsxm(xfbjds.get(0).getHfxsxm());
        						xfbjd.setHjxfblqk(xfbjds.get(0).getHjxfblqk());
        						xfbjd.setJsr(xfbjds.get(0).getJsr());
        						xfbjd.setJssj(xfbjds.get(0).getJssj());
        						//�˻غ���ܸı����Ҫ�ط�Ҫ��Ӧ
        						xfbjd.setRwid(work.getId());
        						xfbjd.setSlsj(xfbjds.get(0).getSlsj());
        						xfbjd.setIsActive(xfbjds.get(0).getIsActive());
        						xfbjd.setVersion(xfbjds.get(0).getVersion());
        						xfbjd.setCreateby(cur);
        						xfbjd.setCreated(new Date());
        						xfbjd.setUpdateby(cur);
        						xfbjd.setUpdated(new Date());
        						this.dao.save(xfbjd);
        					}
        				}
        				//ר���ж�
        				if("15".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setJcmd(pos.get(0).getJcmd());//���Ŀ��
            				taskandtasktype.setJcyq(pos.get(0).getJcyq());//���Ҫ��
        				}
        				//Υ������
        				if("16".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setLadjh(pos.get(0).getLadjh());//�����Ǽ�ʱ��
        					taskandtasktype.setLadjsj(pos.get(0).getLadjsj());//�����ǼǺ�
        					taskandtasktype.setWfajmc(pos.get(0).getWfajmc());//Υ����������
        					taskandtasktype.setDcsj(pos.get(0).getDcsj());//����ʱ��
        					taskandtasktype.setJzzlr(pos.get(0).getJzzlr());//����������
        				}
        				//�ճ��칫
        				if("24".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setDesc(pos.get(0).getDesc());//�ճ��칫��ע
        				}
        				taskandtasktype.setCreateby(cur);
        				taskandtasktype.setUpdateby(cur);
        				taskandtasktype.setCreated(new Date());
        				taskandtasktype.setUpdated(new Date());
        				taskandtasktype.setIsActive("Y");
        				this.dao.save(taskandtasktype);
        			}
    			}
    			//������û��Ļ�����ȡ����Э���ˡ�����ˡ���¼����Ϣ
    			if(StringUtils.equals(cur.getId(), w.getZxrId())){
    				//��ѯЭ���ˡ�����ˡ���¼��
    				List<Map<String, String>> rylistMap = commWorkManager
    						.ryData(w.getId());
    				if(rylistMap!=null && rylistMap.size()>0){
    					for(int i=0;i<rylistMap.size();i++){
    						TBizTaskuser taskuser2=new TBizTaskuser();
    	    				taskuser2.setTaskid(work.getId());
    	    				taskuser2.setUserid(rylistMap.get(i).get("userid"));
    	    				taskuser2.setUsername(rylistMap.get(i).get("name"));
    	    				taskuser2.setType(rylistMap.get(i).get("type"));
    	    				taskuser2.setCreateby(cur);
    	    				taskuser2.setUpdateby(cur);
    	    				taskuser2.setCreated(new Date());
    	    				taskuser2.setUpdated(new Date());
    	    		        this.dao.save(taskuser2);
    					}
    				}
    			}
        	}
        }
        this.saveLog(cur, date, WorkLogType.JS, WorkState.JS, work, startTime);
        /*workRyManager.saveRy(cur, WorkLogType.JS, applyId);*/
    }

}