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
 * 任务的接受操作
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
        //更新WORK的更新时间做为下一步步骤的开始时间
        Date startTime = work.getUpdateTime();
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        //将当前人记录为执行人
        work.setZxrId(cur.getId());
        work.setZxrName(cur.getName());
        //下步操作
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.ZX.getCode()).append(",");
        //nextAction.append(WorkProcessEnum.SBYJ.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        //流程参数
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
        
        //任务接受时保存主办人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(applyId);
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.ZBR.getCode());//主办人
        //批示意见为“接受”
        //work.setPsyj("");
        
        this.dao.save(taskuser);
        
        //联动任务退回操作调取历史办理信息
        if(StringUtils.isNotBlank(work.getSjid())){
        	Work w = workManager.getWorkbySjid(work.getSjid(),work.getId());
        	if(w!=null){//存在下派的任务
        		//执法对象信息（执法对象类型+执法对象）
    			work.setZfdxType(w.getZfdxType());
    			//保存WORK对象
    	        workDao.save(work);
        		//准备资料、办理资料等附件信息
    			List<TDataFile> fileList= commonManager.queryNoSomeFileList(w.getId(),FileTypeEnums.RWGLPFFJ.getCode(),FileTypeEnums.ZXXDZRWYSB.getCode());
    			
    			if(fileList!=null && fileList.size()>0){
    				for(int k=0;k<fileList.size();k++){
    					TDataFile t= new TDataFile(work.getId(),fileList.get(k).getOsname(),fileList.get(k).getName(),fileList.get(k).getSize(),fileList.get(k).getType(),fileList.get(k).getRelativepath(),CtxUtil.getCurUser());
    					TDataFile newFile = commonManager.saveFile(t);
    					//多次检查的监察笔录
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
    			//调取保存执法对象info
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
            			tBizTaskandlawobj2.setZwtitle(StringUtils.isNotBlank(listMap.get(i).get("zwtitle"))?listMap.get(i).get("zwtitle"):"法定代表人");
            			tBizTaskandlawobj2.setLxdh(listMap.get(i).get("lxdh"));
            			tBizTaskandlawobj2.setIsActive("Y");
            			tBizTaskandlawobj2.setCreateby(cur);
            			tBizTaskandlawobj2.setUpdateby(cur);
            			tBizTaskandlawobj2.setCreated(new Date());
            			tBizTaskandlawobj2.setUpdated(new Date());
            			this.dao.save(tBizTaskandlawobj2);
    				}
    			}
    			//任务类型信息
    			List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(w.getId());
    			for (int i = 0; i < rwlxlistMap.size(); i++) {
    				//调取保存检查时间、检查地点、检查模板、监察结论等信息
        			List<TBizTaskandtasktype> pos = this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", w.getId(),rwlxlistMap.get(i).get("id"));
        			if(pos!=null && pos.size()>=0){
        				TBizTaskandtasktype taskandtasktype = new TBizTaskandtasktype();
        				taskandtasktype.setTaskid(work.getId());
        				taskandtasktype.setTasktypeid(rwlxlistMap.get(i).get("id"));
        				taskandtasktype.setJcdd(pos.get(0).getJcdd());//检查地点
        				taskandtasktype.setJcjl(pos.get(0).getJcjl());//监察结论
        				taskandtasktype.setJcmb(pos.get(0).getJcmb());//检查模板
        				taskandtasktype.setJcsj1(pos.get(0).getJcsj1());//检查时间
        				taskandtasktype.setJcsj2(pos.get(0).getJcsj2());//检查时间
        				//信访投诉
        				if("13".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setXftsly(pos.get(0).getXftsly());//信访投诉来源
        					//信访投诉办理情况
        					//根据任务得到信访办结单
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
        						//退回后接受改变的主要地方要对应
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
        				//专项行动
        				if("15".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setJcmd(pos.get(0).getJcmd());//检查目的
            				taskandtasktype.setJcyq(pos.get(0).getJcyq());//检查要求
        				}
        				//违法案件
        				if("16".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setLadjh(pos.get(0).getLadjh());//立案登记时间
        					taskandtasktype.setLadjsj(pos.get(0).getLadjsj());//立案登记号
        					taskandtasktype.setWfajmc(pos.get(0).getWfajmc());//违法案件名称
        					taskandtasktype.setDcsj(pos.get(0).getDcsj());//调查时间
        					taskandtasktype.setJzzlr(pos.get(0).getJzzlr());//卷宗整理人
        				}
        				//日常办公
        				if("24".equals(rwlxlistMap.get(i).get("id"))){
        					taskandtasktype.setDesc(pos.get(0).getDesc());//日常办公备注
        				}
        				taskandtasktype.setCreateby(cur);
        				taskandtasktype.setUpdateby(cur);
        				taskandtasktype.setCreated(new Date());
        				taskandtasktype.setUpdated(new Date());
        				taskandtasktype.setIsActive("Y");
        				this.dao.save(taskandtasktype);
        			}
    			}
    			//主办人没变的话，调取保存协办人、检查人、记录人信息
    			if(StringUtils.equals(cur.getId(), w.getZxrId())){
    				//查询协办人、检查人、记录人
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
