package com.hnjz.app.work.manager;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.DataFileType;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkSh;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.hjgl.HjglManager;
import com.hnjz.app.work.manager.nodes.WorkFlowOperator;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkCommFile;
import com.hnjz.app.work.po.WorkJcinfo;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.FtpUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.hzws.WorkParaBeanBase;
import com.hnjz.hzws.WorkflowService;
import com.hnjz.hzws.WorkflowServiceService;
import com.hnjz.sal.WorkClient;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPosition;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.wf.entity.Wfdb;
import com.hnjz.wf.enums.ProcessEnum;
import com.hnjz.wf.process.ApplyProcessManager;

@Service(value = "workManager")
public class WorkManagerImpl {
	/**工作流的名称*/
    String key = ProcessEnum.GENERAL_TASK.getProcessKey();

    /**日志*/
    private Logger                  log = Logger.getLogger(this.getClass());
    /** 任务 */
    @Autowired
    private WorkDao                 workDao;
    /** 流程处理 */
    @Autowired
    private ApplyProcessManager     processManager;
    /** 用户管理 */
    @Autowired
    private UserManager             userManager;
    /** 部门管理 */
    @Autowired
    private OrgManager              orgManager;

    /**WorkNode的代理**/
    @Autowired
    private WorkProxy               workProxy;
    @Autowired
    private WorkClient              workClient;

    @Autowired
    private WorkCommFileManagerImpl commFileManager;
    
    @Autowired
	protected Dao dao;
    
    @Autowired
    private HjglManager      hjglManager;
    
    @Autowired
    private CommWorkManager    commWorkManager;
    
    @Autowired
    private CommonManager    commonManager;
    
    @Autowired
	private AreaManager areaManager;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;
    
    /**任务派发**/
    @Autowired
    private WorkPf           workPf;
    
    public void saveZp(String applyId, String taskId, String jsrId, String psyj, TSysUser cur) throws Exception {
        Assert.notNull(jsrId, "接收人不能为空！");
        Work work = workDao.get(applyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jsrId", jsrId);
        map.put("psyj", psyj);
        workProxy.execute("zpNode", applyId, taskId, null, map, cur);
    }
    
    /**
     * 
     * 函数介绍：任务重派（相当于删除除任务表中的其他任务关联表中相关信息，然后进行任务派发）
     * 输入参数：
     * 返回值：
     * @return 
     */
    public Work saveCp(WorkDto frm, String jsrId, TSysUser cur) throws Exception {
    	/**
    	 * 如果是联动任务，上级下派的任务，进行重派时：1、上级的任务id要保留关联。2、上级流转记录要保留。
    	 */
    	String sjid="";
    	List<WorkLog> list_sjlzjl=new ArrayList<WorkLog>();
    	 //填充WORK对象
        Work work = null;
        if (StringUtils.isBlank(frm.getId())) {
        	work = new Work();
        }else{
        	work = workDao.get(frm.getId());
        }
    	if(StringUtil.isNotBlank(work.getSjid())){
    		sjid=work.getSjid();
    		String sql_sjlzjl="from WorkLog t where t.work.id=? and t.czsj<(select a.czsj from WorkLog a where a.work.id=? and a.czrId=? and a.operateType=?) order by t.czsj asc";
    		list_sjlzjl = this.dao.find(sql_sjlzjl,work.getId(),work.getId(),cur.getId(),WorkLogType.PF.getCode());
    	}
    	
        //1、任务人员信息表
    	String sql1=" from TBizTaskuser where taskid=? and type!='"+TaskUserEnum.SCR.getCode()+"'";
		this.dao.removeFindObjs(sql1, frm.getId());
    	//2、任务日志表
		String sql2=" from WorkLog where work.id=? ";
		this.dao.removeFindObjs(sql2, frm.getId());
    	//3、待办任务表
		String sql3=" from Wfdb where workId=? ";
		this.dao.removeFindObjs(sql3, frm.getId());
    	//4、任务流程节点操作信息表
		String sql4=" from WfApplyStep where applyId=? ";
		this.dao.removeFindObjs(sql4, frm.getId());
		
		//5、调用派发
		Work newWork=workPf.savePf(frm, jsrId, cur, "");
		
		///////
		if(StringUtil.isNotBlank(work.getSjid())){
			//1、上级的任务id要保留关联。
			newWork.setSjid(sjid);
			this.dao.save(newWork);
			//2、上级流转记录要保留。
			for(int i=0;i<list_sjlzjl.size();i++){
				WorkLog workLog=new WorkLog();
				workLog.setCzrId(list_sjlzjl.get(i).getCzrId());
				workLog.setCzrName(list_sjlzjl.get(i).getCzrName());
				workLog.setWorkSate(list_sjlzjl.get(i).getWorkSate());
				workLog.setOperateType(list_sjlzjl.get(i).getOperateType());
				workLog.setCzsj(list_sjlzjl.get(i).getCzsj());
				workLog.setNote(list_sjlzjl.get(i).getNote());
				workLog.setWork(newWork);
				workLog.setStartTime(list_sjlzjl.get(i).getStartTime());
				workLog.setUserTime(list_sjlzjl.get(i).getUserTime());
				this.dao.save(workLog);
			}
		}
		return newWork;
    }

    /**
     * 
     * 函数介绍：任务下派
     * 输入参数：applyId:任务id;taskId:流程id;areaId:下派区域id;psyj:批示意见;xpfjIds:下派附件ids;xplspsIds:下派历史批示ids;
     * 返回值：
     */
    public void saveZpXpds(String applyId, String taskId, String areaId,String psyj,String xpfjIds,String xplspsIds, String jsrId) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        
        //改变任务状态为“地市处理中”
        work.setState(WorkState.YXP.getCode());
        
        //保存下派区域id
        work.setXpCity(areaId);
        
        work.setXpfjIds(xpfjIds);//下派附件ids
        work.setXplspsIds(xplspsIds);//下派历史批示ids
        
        StringBuffer shrIds = new StringBuffer();
        //将跨级人员添加到审核人员集合中
        List<TSysUser> userList = orgManager.getUsers(null, cur.getId(), Boolean.FALSE);
        
        //2015-6-9 修改 如果是总队直接下派 不用再把自己加上，要不重复。
        UserPosition p = orgManager.getPosition(cur.getId());
        if(p == UserPosition.ZD){
        }else{
        	userList.add(cur);//下派地市时自己是第一个审核人，把自己也加上去。
        }
        
        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i).getId();
            shrIds.append(userId).append(",");
        }
        
        work.setShrids(shrIds.toString());
        if (log.isDebugEnabled()) {
            String[] rys = work.getShrids().split(",");
            for (String ele : rys) {
            	TSysUser s = this.userManager.getUser(ele);
                log.debug("审核用户：" + s.getName());
            }
        }
        
        work.setPsyj(psyj);//批示意见
        work.setNextActions("");
        this.workDao.save(work);
        
        //任务下派时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.XPR.getCode());//下派人
        
        this.dao.save(taskuser);
        
        
        /************流程参数***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //转向
        bean.setDirection(WorkTransferDirectionEnum.PF_XPDS);
        bean.setResult(WorkTransferDirectionEnum.PF_XPDS.getText());
        /************流程参数***********/
        //processManager.saveNext(key, taskId, work, bean);
        
        List<WorkLog> listLog = this.dao.find(" from WorkLog w where work.id=? order by w.czsj desc ",work.getId());
        WorkLog lo = new WorkLog();
        if(listLog.size()>0){
        	lo.setStartTime(listLog.get(0).getCzsj());
        }else{
        	lo.setStartTime(new Date());
        }
        lo.setCzrId(cur.getId());
        lo.setCzrName(cur.getName());
        lo.setCzsj(date);
        lo.setWork(work);
        lo.setOperateType(WorkLogType.XP.getCode());
        lo.setWorkSate(WorkState.YXP.getCode());
        lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
        
        //批示意见
        lo.setNote(work.getPsyj());
        
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("保存日志成功：");
        }
        /**下派操作**/
        WorkDto workDto=new WorkDto(work);
        
        //下派附件相关
        List<Map<String, String>> pFileInfo=new ArrayList<Map<String,String>>();
        //获取附件信息
        String ip="";
        TSysArea po = (TSysArea) this.dao.get(TSysArea.class, cur.getAreaId());
        if (null != po.getServer()) {
        	String curUrl=po.getServer().getUrl();
        	String reg = ".*\\/\\/([^\\/\\:]*).*";
        	ip=curUrl.replaceAll (reg, "$1");
		}
        
        if(StringUtils.isNotBlank(xpfjIds)){
        	String[] xpfjIdsArr=xpfjIds.split(",");
            for(int i=0;i<xpfjIdsArr.length;i++){
            	TDataFile file = (TDataFile)this.dao.get(TDataFile.class,xpfjIdsArr[i]);
            	Map<String, String> map=new HashMap<String, String>();
            	map.put("name", file.getName());
            	map.put("size", String.valueOf(file.getSize()));
            	map.put("osname", file.getOsname());
            	map.put("url", ip);
            	map.put("path", file.getRelativepath());
            	map.put("ftpuser", FtpUtil.ftpuser);
            	map.put("ftppass", FtpUtil.ftppass);
            	map.put("ftpport", String.valueOf(FtpUtil.ftpport));
            	pFileInfo.add(map);
            }
        }
        workDto.setPFileInfo(pFileInfo);
        
        //下派历史批示相关
        List<Map<String, String>> pLspsInfo=new ArrayList<Map<String,String>>();
        //获取历史批示信息
        if(StringUtils.isNotBlank(xplspsIds)){
        	String[] xplspsIdsArr=xplspsIds.split(",");
            for(int i=0;i<xplspsIdsArr.length;i++){
            	WorkLog workLog = (WorkLog)this.dao.get(WorkLog.class,xplspsIdsArr[i]);
            	Map<String, String> map=new HashMap<String, String>();
            	map.put("czrId", workLog.getCzrId());
            	map.put("czrName", workLog.getCzrName());
            	map.put("czsj", DateUtil.getDateTime(workLog.getCzsj()));
            	map.put("note", workLog.getNote());
            	map.put("operateType", workLog.getOperateType());
            	map.put("workSate", workLog.getWorkSate());
            	map.put("startTime", DateUtil.getDateTime(workLog.getStartTime()));
            	pLspsInfo.add(map);
            }
        }
        //加上当前下派人的批示意见
        Map<String, String> mapXpr=new HashMap<String, String>();
        mapXpr.put("czrId", lo.getCzrId());
        mapXpr.put("czrName", lo.getCzrName());
        mapXpr.put("czsj", DateUtil.getDateTime(lo.getCzsj()));
        mapXpr.put("note", lo.getNote());
        mapXpr.put("operateType", lo.getOperateType());
        mapXpr.put("workSate", lo.getWorkSate());
        mapXpr.put("startTime", DateUtil.getDateTime(lo.getStartTime()));
        pLspsInfo.add(mapXpr);
        
        workDto.setPLspsInfo(pLspsInfo);
        
        //任务类型
		List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
		String rwlxIds = "";
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
		if("24".equals(rwlxIds)){	//日常办公任务
			workDto.setRcbg("Y");
			workDto.setJsrId(jsrId);
		}else if("13".equals(rwlxIds)){	 //信访投诉任务
			workDto.setXfts("Y");
			workDto.setJsrId(jsrId);
		}
		Document document = null;
		//慧正工作流相关的下派节点传值
		//2015-3-17 添加 根据下派区域的id，查询出下派区域的领导，然后以该领导身份根据下派任务信息，创建一条新的任务。
		String backresult = ""; 
		String newresults = "";
		String result = "";
		String workid = "";
		String userid = "";
		String trackid = "";
		//if(!"13".equals(rwlxIds) && !"24".equals(rwlxIds)){
			backresult = workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line3~Node10", cur.getId(), cur.getName(), "");
			try {
	            document = DocumentHelper.parseText(backresult);
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	        Element rootElt = document.getRootElement();
	        result = rootElt.element("result").getText();
	        workid = rootElt.element("workid").getText();
	        userid = rootElt.element("curUser").element("IRunUser").element("userid").getText();
	        trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
	        workflowoperator.close(cur.getId(), workid);
	        //TSysUser cur1 = userManager.getLeaderByAreaId(areaId);
        	newresults = workflowoperator.operate_subflow(workid, trackid, cur.getId(), "zfjczlc");
        	//需要创建新的任务进行支队的流转然后跟老的任务还要关联
    		this.saveZrw(workDto, areaId, newresults, "zfjczlc", jsrId);
            /**下派操作**/
		/*}else{
        	backresult = workflowoperator.submit(work.getCreateUser().getId(), work.getShiliid(), work.getTrackid(), "Line13~Node10", cur.getId(), cur.getName(), "");
			try {
	            document = DocumentHelper.parseText(backresult);
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	        Element rootElt = document.getRootElement();
	        result = rootElt.element("result").getText();
	        workid = rootElt.element("workid").getText();
	        userid = rootElt.element("curUser").element("IRunUser").element("userid").getText();
	        trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
	        workflowoperator.close(cur.getId(), workid);
	        //TSysUser cur1 = userManager.getLeaderByAreaId(areaId);
        	newresults = workflowoperator.operate_subflow(workid, trackid, cur.getId(), "sjxftsTask");
        	//需要创建新的任务进行支队的流转然后跟老的任务还要关联
    		this.saveZrw(workDto, areaId, newresults, "sjxftsTask", jsrId);
            *//**下派操作**//*
		}*/
    }
    
    /**
     * 派发-接收下派<br/>
     * 下派的任务为组任务，可进行的操作不分权限（转派）
     * @param frm 任务信息
     * @param areaId 区域Id
     * @throws Exception
     */
    public void saveZrw(WorkDto frm, String areaId, String newresults,String flowId ,String jsrId) throws Exception {
    	//2015-3-17 添加 根据下派区域的id，查询出下派区域的领导，然后以该领导身份根据下派任务信息，创建一条新的任务。
    	TSysUser cur = userManager.getLeaderByAreaId(areaId);
    	
    	/*TSysUser cur = userManager.getSj();*/
        Date date = Calendar.getInstance().getTime();

		//慧正工作流相关的下派节点传值
		//2015-3-17 添加 根据下派区域的id，查询出下派区域的领导，然后以该领导身份根据下派任务信息，创建一条新的任务。
        Document document = null;
        String result = "";
		String workid = "";
		String userid = "";
		String trackid = "";
        try {
            document = DocumentHelper.parseText(newresults);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElt = document.getRootElement();
        result = rootElt.element("result").getText();
        workid = rootElt.element("subflowWorkList").element("DBRelationSubflow").element("subworkid").getText();
        userid = rootElt.element("curUser").element("IRunUser").element("userid").getText();
        trackid = rootElt.element("subflowWorkList").element("DBRelationSubflow").element("trackid").getText();
        //trackid = rootElt.element("curTrackInfo").element("DBTrack").element("id").getText();
        
        workflowoperator.close(userid, workid);
        //填充WORK对象
        Work work = new Work();
        
        //存入流程相关的值
        work.setShiliid(workid);
        work.setTrackid(trackid);
        work.setFlowid(flowId);
        
        work.setCreateTime(date);
        work.setCreateUser(cur);
        Date start = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getStartTime());
        Date end = DateUtil.getEndDatetime(frm.getEndTime());
        work.setEndTime(end);
        work.setIsActive(YnEnum.Y.getCode());
        work.setStartTime(start);
        work.setName(frm.getWorkName());
        work.setWorkNote(frm.getWorkNote());
        
        work.setSource("1");//任务来源（下派任务默认为“上级来文”）
        work.setSecurity(frm.getSecurity());//任务密级
        work.setEmergency(frm.getEmergency());//紧急程度
        work.setDjrId(cur.getId());//登记人（为任务接受人）
        //下派的时候原来直接选择地市，现在可以直接选择接受人
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, userid);
        if(StringUtils.isNotBlank(jsrId)){
        	work.setJsr(frm.getJsrId());//信访日常任务的接受人
        	//TSysUser po = (TSysUser) userManager.get(TSysUser.class, jsrId);
        	//result = workflowoperator.submit(userid, workid, trackid, "Line2~Node2", jsrId, user.getName(),"");
        }else{
        	work.setJsr(cur.getId());//一般任务的接收人
        	result = workflowoperator.submit(userid, workid, trackid, "Line2~Node2", cur.getId(), user.getName(),"");
        }
        //下派相关
        work.setIsxp(true);
        work.setAreaid(areaId);
        work.setSjid(frm.getId());
        Work w = this.getWorkbySjid(frm.getId(),work.getId());
    	if(w!=null){//存在下派的任务,退回操作
    		work.setState(WorkState.YTH.getCode());
    	}else{
    		work.setState(WorkState.YPF.getCode());
    	}
        
        /***********下步操作**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
        nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
        //2015-3-17 添加“下派”（接受、转派、下派是同一级别的操作），如果没有下级单位则不显示“下派”操作。
        Boolean hasXJDW = true;//默认可以下派。
        List<Combobox> xjdslist = commonManager.queryAreaComboByAreaId(cur.getAreaId());
        if(xjdslist==null||xjdslist.size()==0){
        	hasXJDW = false;
        }
        if(hasXJDW){
        	nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
        }
        
        work.setNextActions(nextAction.toString());
        /***********下步操作**********/
        
        StringBuffer shrIds = new StringBuffer();
        //将跨级人员添加到审核人员集合中
        List<TSysUser> userList = orgManager.getUsers(null, cur.getId(), Boolean.FALSE);
        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i).getId();
            shrIds.append(userId).append(",");
        }
        work.setShrids(shrIds.toString());
        if (log.isDebugEnabled()) {
            String[] rys = work.getShrids().split(",");
            for (String ele : rys) {
            	TSysUser s = this.userManager.getUser(ele);
                log.debug("审核用户：" + s.getName());
            }
        }

        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        //执法对象信息（执法对象类型+执法对象）
        Work sjWork = workDao.get(frm.getId());
		work.setZfdxType(sjWork.getZfdxType());
		
        //保存WORK对象
        workDao.save(work);

        //组合形式：保存任务类型和执法对象信息
        if("01".equals(work.getState())){
        	if("0".equals(frm.getRwmcgs())){
            	//任务类型
            	List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(sjWork.getId());
            	if(rwlxlistMap!=null && rwlxlistMap.size()>0){
            		String rwlxIds = "";
            		for (int i = 0; i < rwlxlistMap.size(); i++) {
            			if (i < rwlxlistMap.size() - 1) {
            				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
            			} else {
            				rwlxIds += rwlxlistMap.get(i).get("id");
            			}
            		}
            		commWorkManager.saveTaskTypeMultiple(work.getId(), rwlxIds, cur);
            	}
            	//执法对象类型
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //调取保存执法对象info
          		List<Map<String, String>> listMap = commWorkManager
          				.zfdxTableData(sjWork.getId());
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
            }else{
            	//任务类型
            	List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(sjWork.getId());
            	if(rwlxlistMap!=null && rwlxlistMap.size()>0){
            		String rwlxIds = "";
            		for (int i = 0; i < rwlxlistMap.size(); i++) {
            			if (i < rwlxlistMap.size() - 1) {
            				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
            			} else {
            				rwlxIds += rwlxlistMap.get(i).get("id");
            			}
            		}
            		commWorkManager.saveTaskTypeMultiple(work.getId(), rwlxIds, cur);
            	}
            	//执法对象类型
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //调取保存执法对象info
          		List<Map<String, String>> listMap = commWorkManager
          				.zfdxTableData(sjWork.getId());
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
            }
        }
        
        //任务派发时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.PFR.getCode());//派发人
        
        this.dao.save(taskuser);
        
        
        /////////////下载下派附件并存储/////////////
        workPf.saveDownloadXPFile(work.getId(),frm.getPFileInfo(),cur);
        /////////////下派历史批示信息存储/////////////
        workPf.saveXPLsps(work.getId(),frm.getPLspsInfo());
        
        
        /************流程参数***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //申请单编号
        bean.setApplyId(work.getId());
        //当前操作人
        bean.setCurrentOper(work.getCreateUser());
        //下步操作人 （2015-3-17 修改 下一步操作人为下派区域的领导）  
        bean.getNextOpers().add(cur);
        /*bean.setNextOpers(userManager.getXpUsers(areaId));*/
        //转向
        bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
        bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
        /************流程参数***********/
        /*//启动流程
        ProcessInstance pi = processManager.saveStart(this.KEY, work, bean);
        //当前任务
        Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(this.KEY, task.getId(), work,
                bean);
        }*/
        
        //加一秒
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);    
        calendar.add(Calendar.SECOND, 1);    
        Date curDate=calendar.getTime();  
        //this.saveLog(cur, curDate, WorkLogType.PF, WorkState.YPF, work, curDate);
    }
    
    /**
     * 
     * 函数介绍：处理上报任务类型保存+附件保存
     * 输入参数：applyId:任务id;sbfjIds:上报附件ids;opinion:上报批示信息;
     * 返回值：
     */
    public void saveSbRwlxAndFile(String applyId, String sbfjIds,String opinion) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
    	Date date = new Date();
        Work work = workDao.get(applyId);
        
        //1、上级任务id
        String sjid=work.getSjid();
        
        //2、上级区域id
        String sbAreaId=areaManager.getPAreaIdByAreaId(cur.getAreaId());
        
        //3、任务类型列表
        String rwlxIds="";
        List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
        //存储监察结论 任务ID+"-"+任务类型  ：  监察结论
        Map<String, String> jcjlMap = new HashMap<String,String>();
        //存储监察结论 任务ID+"-"+任务类型  ：  日常办公备注
        Map<String, String> rcbgDescMap = new HashMap<String,String>();
        if(rwlxlistMap!=null && rwlxlistMap.size()>0 && !StringUtils.equals(rwlxlistMap.get(0).get("id"), "24")){//非日常办公
        	 for(int i=0;i<rwlxlistMap.size();i++){
             	if(i<rwlxlistMap.size()-1){
             		rwlxIds+=rwlxlistMap.get(i).get("id")+",";
             	}else{
             		rwlxIds+=rwlxlistMap.get(i).get("id");
             	}
             	String jcjl = commWorkManager.getJcjl(applyId, rwlxlistMap.get(i).get("id"));
             	//key值为上级的任务ID+分隔符号+任务类型ID
             	jcjlMap.put(sjid+"-"+rwlxlistMap.get(i).get("id"), jcjl);
             }
        }else{
        	//日常办公备注
         	String rcbgDesc = commWorkManager.getRcbgDesc(applyId, rwlxlistMap.get(0).get("id"));
         	rcbgDescMap.put(sjid+"-"+rwlxlistMap.get(0).get("id"), rcbgDesc);
         	rwlxIds=rwlxlistMap.get(0).get("id");
        	
        }
        
        //4、上报附件相关
        List<Map<String, String>> sbFileInfo=new ArrayList<Map<String,String>>();
        //先删除历史上报附件（退回任务上报时清楚历史上报信息）
        commonManager.removeAllBlFileByPid(work.getSjid());
        //获取附件信息
        String ip="";
        TSysArea po = (TSysArea) this.dao.get(TSysArea.class, cur.getAreaId());
        if (null != po.getServer()) {
        	String curUrl=po.getServer().getUrl();
        	String reg = ".*\\/\\/([^\\/\\:]*).*";
        	ip=curUrl.replaceAll (reg, "$1");
		}
        
        //List<TDataFile> tfiles = commonManager.queryFileList(applyId, FileTypeEnums.RWGLPFFJ.getCode());
        //List<TDataFile> tfiles1 = commonManager.queryFileList(applyId, FileTypeEnums.RWGLZPFJ.getCode());
        //List<TDataFile> tfiles2 = commonManager.queryFileList(applyId, FileTypeEnums.RWGLXPFJ.getCode());
        if(StringUtils.isNotBlank(sbfjIds)){
        	String[] sbfjIdsArr=sbfjIds.split(",");
            for(int i=0;i<sbfjIdsArr.length;i++){
            	TDataFile file = (TDataFile)this.dao.get(TDataFile.class,sbfjIdsArr[i]);
            	Map<String, String> map=new HashMap<String, String>();
            	map.put("name", file.getName());
            	map.put("size", String.valueOf(file.getSize()));
            	map.put("osname", file.getOsname());
            	map.put("url", ip);
            	map.put("path", file.getRelativepath());
            	map.put("ftpuser", FtpUtil.ftpuser);
            	map.put("ftppass", FtpUtil.ftppass);
            	map.put("ftpport", String.valueOf(FtpUtil.ftpport));
            	map.put("type", file.getType());
            	sbFileInfo.add(map);
            }
        }
        
        /*if(null != tfiles){
        	for(int a=0; a < tfiles.size(); a++){
        		Map<String, String> map=new HashMap<String, String>();
        		map.put("name", tfiles.get(a).getName());
            	map.put("size", String.valueOf(tfiles.get(a).getSize()));
            	map.put("osname", tfiles.get(a).getOsname());
            	map.put("url", ip);
            	map.put("path", tfiles.get(a).getRelativepath());
            	map.put("ftpuser", FtpUtil.ftpuser);
            	map.put("ftppass", FtpUtil.ftppass);
            	map.put("ftpport", String.valueOf(FtpUtil.ftpport));
            	map.put("type", tfiles.get(a).getType());
            	sbFileInfo.add(map);
        	}
        }*/
        
        /*if(null != tfiles1){
        	for(int a=0; a < tfiles1.size(); a++){
        		Map<String, String> map=new HashMap<String, String>();
        		map.put("name", tfiles1.get(a).getName());
            	map.put("size", String.valueOf(tfiles1.get(a).getSize()));
            	map.put("osname", tfiles1.get(a).getOsname());
            	map.put("url", ip);
            	map.put("path", tfiles1.get(a).getRelativepath());
            	map.put("ftpuser", FtpUtil.ftpuser);
            	map.put("ftppass", FtpUtil.ftppass);
            	map.put("ftpport", String.valueOf(FtpUtil.ftpport));
            	map.put("type", tfiles1.get(a).getType());
            	sbFileInfo.add(map);
        	}
        }
        
        if(null != tfiles2){
        	for(int a=0; a < tfiles2.size(); a++){
        		Map<String, String> map=new HashMap<String, String>();
        		map.put("name", tfiles2.get(a).getName());
            	map.put("size", String.valueOf(tfiles2.get(a).getSize()));
            	map.put("osname", tfiles2.get(a).getOsname());
            	map.put("url", ip);
            	map.put("path", tfiles2.get(a).getRelativepath());
            	map.put("ftpuser", FtpUtil.ftpuser);
            	map.put("ftppass", FtpUtil.ftppass);
            	map.put("ftpport", String.valueOf(FtpUtil.ftpport));
            	map.put("type", tfiles2.get(a).getType());
            	sbFileInfo.add(map);
        	}
        }*/
        
        //5、上报历史批示相关
        List<Map<String, String>> bLspsInfo=new ArrayList<Map<String,String>>();
        //获取历史批示信息（就上报一条，即上报人批示那一条）
        Map<String, String> map=new HashMap<String, String>();
    	map.put("czrId", cur.getId());
    	map.put("czrName", cur.getName());
    	map.put("czsj", DateUtil.getDateTime(date));
    	map.put("note", opinion);
    	map.put("operateType", WorkLogType.SB.getCode());
    	map.put("workSate", WorkState.YGD.getCode());
    	map.put("startTime", DateUtil.getDateTime(date));
    	bLspsInfo.add(map);
        
        //调用上级服务器接口保存信息
        workClient.saveSbRwlxAndFile(sjid, sbAreaId,rwlxIds,sbFileInfo,bLspsInfo,jcjlMap,rcbgDescMap);
        /**下派操作**/
    }

    /**
     * 任务接受
     * 
     * @param applyId 任务id
     * @param taskId 工作流中的id,存在work表中的 taskId
     * @throws Exception
     */
    public void saveJs(String applyId, String taskId, TSysUser cur) throws Exception {
        workProxy.execute("jsNode", applyId, taskId, null, null, cur);
    }

    public void saveZx(String applyId, String taskId, TSysUser cur) throws Exception {
        workProxy.execute("zxNode", applyId, taskId, null, null, cur);
    }

    public void saveAddUser(String applyId, String taskId, String ptrIds) throws Exception {
        //查询陪同人集合
        Object[] objs = ptrIds.split(",");
        StringBuffer ptrIdArrStr = new StringBuffer();
        for (int i = 0; i < objs.length; i++) {
            ptrIdArrStr.append("'").append(objs[i]).append("'");
            if (i < objs.length - 1) {
                ptrIdArrStr.append(",");
            }
        }
        //List<User> userList = userManager.find("from User m where m.id in (?)", ptrIdArrStr.toString());
        List<TSysUser> userList = workDao.find("from TSysUser m where m.id in (" + ptrIdArrStr + ")");
        /***************陪同人姓名集合、用户名集合***************/
        StringBuffer ptrIdsBuffer = new StringBuffer();
        StringBuffer ptrNamesBuffer = new StringBuffer();
        String[] ptrUserNames = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            ptrIdsBuffer.append(userList.get(i).getId()).append(",");
            ptrNamesBuffer.append(userList.get(i).getName()).append(",");
            ptrUserNames[i] = userList.get(i).getUsername();
        }
        /***************陪同人姓名集合、用户名集合***************/
        Work work = workDao.get(applyId);
        if (StringUtils.isNotBlank(work.getPtrIds())) {
            //得到原陪同人编号集合
            String oldUserIds = work.getPtrIds();
            //查询原陪同人用户名
            objs = oldUserIds.split(",");
            ptrIdArrStr = new StringBuffer();
            for (int i = 0; i < objs.length; i++) {
                ptrIdArrStr.append("'").append(objs[i]).append("'");
                if (i < objs.length - 1) {
                    ptrIdArrStr.append(",");
                }
            }
            userList = workDao.find("from User m where m.id in (" + ptrIdArrStr + ")");
        }
        String[] oldUserNames = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            oldUserNames[i] = userList.get(i).getUsername();
        }
        work.setPtrIds(ptrIdsBuffer.toString());
        work.setPtrNames(ptrNamesBuffer.toString());
        //原记录人如果存在新选择的陪同人组中（无操作），不存在（删除）        
        if (work.getJlr() != null && ptrIdsBuffer.indexOf(work.getJlr().getId()) == -1) {
            work.setJlr(null);
        }
        processManager.saveAddGroupUser(taskId, oldUserNames, ptrUserNames);
        Wfdb db = new Wfdb();
        db.setTaskId(Integer.parseInt(taskId));
        db.setWorkId(applyId);
        this.workDao.save(db);
    }

    public void saveJlr(String applyId, String userId) throws Exception {
        Work work = workDao.get(applyId);
        TSysUser jlr = (TSysUser) userManager.get(TSysUser.class, userId);
        work.setJlr(jlr);
        this.workDao.save(work);
    }

    public void saveSh(String applyId, String taskId, Boolean passed, String opinion, TSysUser cur)
                                                                                     throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("passed", passed);
        map.put("opinion", opinion);
        workProxy.execute("shNode", applyId, taskId, null, map, cur);
    }

    public Work get(String id){
        return workDao.get(id);
    }
    
    public Work getXpRw(String id){
    	Work w = this.get(id);
    	List<Work> workList = this.dao.find(" from Work where state != null and areaid != 'a0000000000000000000000000000000' and name = ? order by createTime desc", w.getName());
    	return workList.get(0);
    }

    public Boolean alreadyExist(String workName) throws Exception {
        String hql = "select count(m) from Work m where m.name=?";
        List<Object> list = workDao.find(hql, workName);
        return (Long) list.get(0) > 0;
    }

    public void saveAction(String id, String action) throws Exception {
        Work work = get(id);
        work.setNextActions(action);
    }

    public TSysUser getAuditUser(String applyId) throws Exception {
        Work work = get(applyId);
        //得到需要审核人集合
        String[] shrIds = work.getShrids().split(",");
        if (log.isDebugEnabled()) {
            log.debug("审核人：" + work.getShrids());
        }
        //取出最后一个做为审核人
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 1]);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (log.isDebugEnabled()) {
            log.debug("下步审核人：" + user.getName());
        }
        return user;
    }

    public TSysUser getBackUser(String applyId) throws Exception {
        Work work = get(applyId);
        //得到需要审核人集合
        String[] thrIds = work.getThrids().split(",");
        //取出最后一个做为审核人
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, thrIds[thrIds.length - 1]);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        return user;
    }

    public void saveTh(String applyId, String taskId, String opinion, TSysUser cur) throws Exception {
        workProxy.execute("thNode", applyId, taskId, opinion, null, cur);
    }

    public void saveWorkAlreadyBack(String applyId) throws Exception {
        Work work = get(applyId);
        /**将此任务的“退回人集合”中去掉此步操作人，将此操作人加入“审核人集合”中**/
        String[] thrIds = work.getThrids().split(",");
        StringBuffer thrIdsBuffer = new StringBuffer();
        for (int i = 0; i < thrIds.length - 1; i++) {
            thrIdsBuffer.append(thrIds[i]).append(",");
        }
        work.setShrids((work.getShrids() == null ? "" : work.getShrids())
                       + thrIds[thrIds.length - 1] + ",");
        work.setThrids(thrIdsBuffer.toString());
    }

    /**
     * 任务上报验证（不知道任务类型时使用）
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean shangbaoV(String workId) throws Exception {
        Work work = get(workId);
        ResultBean rb = null;
//        if (work.getWorkType().getName().indexOf("通用") != -1) {
//            rb = allowShangbao_comm(workId);
//        } else if (work.getWorkType().getName().indexOf("违法") != -1) {
//            rb = allowShangbao_dc(workId);
//        } else if (work.getWorkType().getName().indexOf("日常") != -1) {
//            rb = allowShangbao_rc(workId);
//        }
        return rb;
    }

    /**
     * 通用任务上报验证
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_comm(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        List<WorkCommFile> fileList = commFileManager.getFileList(workId,
            DataFileType.TONGYONGRENWUFUJIAN);
        if (fileList.size() == 0) {
            rb.setMsg("附件未上传，请补充附件！");
        } else {
            rb.setResult(true);
            rb.setMsg("验证成功！");
        }
        return rb;
    }

    /**
     * 调查任务上报验证
     * modify by xugh 2013-07-10增加对违法类型 陪同人 记录人的验证
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_dc(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        StringBuffer msgBuffer = new StringBuffer();
        Work work = get(workId);
//        if(StringUtils.isBlank(work.getWflxId()) ){
//            msgBuffer.append("请选择违法类型！");
//        }
        if(StringUtils.isBlank(work.getPtrIds()) ){
            msgBuffer.append("请选择陪同人！");
        }
        if(null == work.getJlr() ){
            msgBuffer.append("请选择记录人！");
        }
        List<WorkCommFile> allFileList = commFileManager.getFileList(workId);
        List<WorkCommFile> xwblList = commFileManager.getFileList(allFileList,
            DataFileType.XUNWENBILU);
        if (xwblList.size() == 0) {
            msgBuffer.append("询问笔录、");
        }
        List<WorkCommFile> kcblList = commFileManager.getFileList(allFileList,
            DataFileType.KANCHABILU);
        if (kcblList.size() == 0) {
            msgBuffer.append("勘查笔录、");
        }
        List<WorkCommFile> zjxxList = commFileManager.getFileList(allFileList,
            DataFileType.ZHENGJUXINXI);
        if (zjxxList.size() == 0) {//书证等证据信息
            msgBuffer.append("书证等证据信息、");
        }
        List<WorkCommFile> xckcList = commFileManager.getFileList(allFileList,
            DataFileType.XIANCHANGKANCHATU);
        if (xckcList.size() == 0) {//现场勘查示意图
            msgBuffer.append("现场勘查示意图、");
        }
        List<WorkCommFile> tupianList = commFileManager.getFileList(allFileList,
            DataFileType.TUPIAN);
        List<WorkCommFile> yinpinList = commFileManager.getFileList(allFileList,
            DataFileType.YINPIN);
        List<WorkCommFile> shipinList = commFileManager.getFileList(allFileList,
            DataFileType.SHIPIN);
        if (tupianList.size() == 0 && yinpinList.size() == 0 && shipinList.size() == 0) {//视听资料
            msgBuffer.append("图片/音频/视频资料、");
        }
        if (msgBuffer.length() > 0) {
            rb.setMsg(msgBuffer.substring(0, msgBuffer.length() - 1) + "未上传,请补充附件！");
        } else {
            rb.setResult(true);
            rb.setMsg("验证成功！");
        }
        return rb;
    }

    /**
     * 日常任务上报验证
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_rc(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        StringBuffer msgBuffer = new StringBuffer();
        //检查信息
        WorkJcinfo jcInfo = (WorkJcinfo) workDao.get(WorkJcinfo.class, workId);
        if (jcInfo == null) {
            msgBuffer.append("未保存检查信息,请先完善检查信息！");
        }
        //检查单
        if (jcInfo != null && !StringUtils.isNotBlank(jcInfo.getUrl())) {
            msgBuffer.append("未生成检查单,请先完善检查信息！");
        }
        //附件
        List<WorkCommFile> fileList = commFileManager.getFileList(workId,
            DataFileType.JIANCHAJILUSAOMIAOJIAN);
        if (fileList.size() == 0) {//书证等证据信息
            msgBuffer.append("检查记录扫描件未上传,请补充附件！");
        }
        if (msgBuffer.length() > 0) {
            rb.setMsg(msgBuffer.toString());
        } else {
            rb.setResult(true);
            rb.setMsg("验证成功！");
        }
        return rb;
    }

    /**
     * 是否生成了检查记录文件
     * 
     * @param workId
     * @return
     */
    public boolean isCompleteCheckList(String workId) {
        boolean flag = false;
        //检查信息
        WorkJcinfo jcInfo = (WorkJcinfo) workDao.get(WorkJcinfo.class, workId);
        if (jcInfo != null && StringUtils.isNotBlank(jcInfo.getUrl())) {
            flag = true;
        }
        return flag;
    }

    public List<WorkSh> getShActions(String workId) throws Exception {
        List<WorkSh> re = new ArrayList<WorkSh>();
        Work work = this.get(workId);
        if (log.isDebugEnabled()) {
            log.debug("任务状态：" + work.getState());
            log.debug("退回人：" + work.getThrids());
            log.debug("审核人：" + work.getShrids());
        }
        String[] shrIds = work.getShrids().split(",");
        String userId = CtxUtil.getCurUser().getId();
		/*if (StringUtils.isBlank(work.getSjid()) && work.getIsxp()) {
            if (StringUtils.equals(work.getCreateUser().getId(), userId)) {
                re.add(WorkSh.GD);
                re.add(WorkSh.TH);
                return re;
            }
        }*/
        //当前用户的审核级别
        UserPosition p = orgManager.getPosition(userId);
        //2015-3-17 修改 当任务有“上级id”并且为“总队”时执行上报或退回
        if (StringUtils.isNotBlank(work.getSjid()) && p == UserPosition.ZD) {
            re.add(WorkSh.SB);
            re.add(WorkSh.TH);
            return re;
        }
        /*if (StringUtils.isNotBlank(work.getSjid()) && p == UserPosition.BGS) {
            re.add(WorkSh.SB);
            re.add(WorkSh.TH);
            return re;
        }*/

        /*WorkType type = work.getWorkType();
        if (StringUtils.isBlank(type.getShjb())) {
            throw new Exception("任务类型：" + type.getId() + "的审核级别没有设置");
        }*/
        
        //////////2015-3-13 设置默认审核级别为到总队长/////////
        int shjb=UserPosition.ZD.getCode();
        
        //int shjb = Integer.parseInt(type.getShjb());
        //退回人的级别,取最高的
        int thjb = -9999;
        String hsql = "select czrId from WorkLog where operateType = ?  and work.id = ? order by czsj";
        List<String> ids = this.workDao.find(hsql, WorkLogType.TH.getCode(), workId);
        if (!ids.isEmpty()) {
            for (String thId : ids) {
                UserPosition c = orgManager.getPosition(thId);
                if (null != c && c.getCode() >= thjb) {
                    thjb = c.getCode();
                }
            }

        }

        if (log.isDebugEnabled()) {
            log.debug("thjb:" + thjb);
        }
        //任务创建人的级别，退回人的级别，审核级别 3个决定这条任务是否能归档
        //当前用户比这3个级别都相等或者高的时候才能归档
        if (work.getCreateUser() != null) {
            /*//下派到地市的任务，创建人为上级
        	TSysUser sj  = userManager.getSj();
            if(!StringUtils.equals(work.getCreateUser().getId(), sj.getId())){
                UserPosition c = orgManager.getPosition(work.getCreateUser().getId());
                //任务第一次退回时，当前用户的级别和创建人的级别都比配置的高时
                boolean flag = p.getCode() >= shjb && c.getCode() <= p.getCode();
                if (thjb == -9999 && flag) {
                    re.add(WorkSh.GD);
                }
                //任务第二次退回时，需要加上最高级别退回人的判断
                boolean th = p.getCode() >= thjb;
                if (thjb != -9999 && flag && th) {
                    re.add(WorkSh.GD);
                }
            }*/
        	UserPosition c = orgManager.getPosition(work.getCreateUser().getId());
            //任务第一次退回时，当前用户的级别和创建人的级别都比配置的高时
            boolean flag = p.getCode() >= shjb && c.getCode() <= p.getCode();
            if (thjb == -9999 && flag) {
                re.add(WorkSh.GD);
            }
            //任务第二次退回时，需要加上最高级别退回人的判断
            boolean th = p.getCode() >= thjb;
            if (thjb != -9999 && flag && th) {
                re.add(WorkSh.GD);
            }
        }
        //只有总队长无法上级审核,
        if (p != UserPosition.ZD && shrIds.length > 1) {
        	re.add(WorkSh.SJSH);
        }
        //当有审核或者归档的操作时，可以退回
        if (!re.isEmpty()) {
            re.add(WorkSh.TH);
        }

        return re;
    }

    public String saveCreateReportInfo(String workId, TSysUser sbr) throws Exception {
        Work work = get(workId);
        work.setSbr(sbr.getName());
        try {
            //生成任务信息文件
            /*WorkJcinfo jcInfo = (WorkJcinfo) workDao.get(WorkJcinfo.class, workId);
            String hsql = "from WorkCommFile where work.id = ? ";
            List<WorkCommFile> files = workDao.find(hsql, workId);
            Document doc = work.toXml(jcInfo, files);*/
            Document doc = work.toXml(null, null);
            /** 将document中的内容写入文件中（当前任务文件夹下） */
            //任务所有文件存放位置
            String dirPath = FileUtil.getRealPath(UploadFileType.WORK.getPath(), workId);
            String fileName = "workInfo.xml";
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File workInfoFile = new File(dirPath.concat(File.separator).concat(fileName));
            if (workInfoFile.exists()) {
                workInfoFile.delete();
            }
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("GBK");
            XMLWriter writer = new XMLWriter(new FileWriter(workInfoFile), outputFormat);
            writer.write(doc);
            writer.close();
            //将所有任务相关文件打包
            Long time = Calendar.getInstance().getTimeInMillis();
            String zipPath = FileUtil.getRealPath(UploadFileType.TEMP.getPath(), work.getId().concat("_").concat(
                "report").concat("_").concat(time.toString()).concat(".zip"));
            FileZipUtil zipUtil = new FileZipUtil();
            zipUtil.zipFolder(dirPath, zipPath);
            this.workDao.save(work);
            return zipPath;
        } catch (Exception ex) {
            log.error("", ex);
        }
        return null;
    }

    public void saveSjid(String workId, String sjid) throws Exception {
        Work work = get(workId);
        work.setSjid(sjid);
        work.setSbsj(Calendar.getInstance().getTime());
        this.workDao.save(work);
    }

    public void saveWflx(String applyId, String wflxId, String wflxName) throws Exception {
        Work work = get(applyId);
//        work.setWflxId(wflxId);
//        work.setWflxName(wflxName);
        this.workDao.save(work);
    }

    /**
     * 根据执法对象id和违法类型id取得最新的已上报违法任务
     * 
     * @param zfdxId 执法对象id
     * @param wflxId 违法类型id
     * @return
     */
    public Work getLatestWorkByZfdxId(String zfdxId,String wflxId){
        Work work = null;
        String hql = "from Work t where t.zfdxId = ? and t.wflxId = ?  and t.state = ? order by t.updateTime desc ";
        List<Work> list = workDao.find(hql, zfdxId,wflxId,WorkState.YGD.getCode());
        if(null != list && list.size()>0){
            work = list.get(0);
        }
        return work;
    }

    /**
     * 根据执法对象id和检查单类型得到已归档任务id
     * 
     * @param zfdxId
     * @param jcdTypeId
     * @return
     */
    public String getWorkIdByZfdxIdAndJcdTypeId(String zfdxId,String jcdTypeId){
        String hql = "select t.id from Work t, WorkJcdrcjg m where t.id=m.work.id and t.zfdxId = ? and m.jcdTemplate.jcdType.id = ?  and t.state = ? order by t.updateTime desc ";
        List<String> list = workDao.find(hql, zfdxId,jcdTypeId,WorkState.YGD.getCode());
        if(null != list && list.size()>0){
            return list.get(0);
        }else{
            return null;    
        }
    }

    /**
     * 根据执法对象查询已归档的日常任务
     * @param work Work对象中需存在zfdxId或zfdxName，如果两者都不存在，则返回null
     * @return Work
     */
	public Work getNewJianchaWork(Work work) {
		StringBuffer hsb = new StringBuffer(" from Work w where w.state=:state and w.code=:code ");
		QueryCondition data = new QueryCondition();
		data.put("state", WorkState.YGD.getCode());
		data.put("code", TaskTypeCode.RCJC.getCode());
//		if(StringUtils.isNotBlank(work.getZfdxId())) {
//			hsb.append(" and w.zfdxId=:zfdxId ");
//			data.put("zfdxId", work.getZfdxId());
//		} else if(StringUtils.isNotBlank(work.getZfdxName())) {
//			hsb.append(" and w.zfdxName=:zfdxName ");
//			data.put("zfdxName", work.getZfdxName());
//		} else {
//			return null;
//		}
		List<Work> list = workDao.find(hsb.toString(), data);
		if(null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	//根据上级id获取下派的任务id，不包含当前任务
    public Work getWorkbySjid(String sjid,String id){
    	String sql = "";
    	List<Work> list = null;
    	QueryCondition data = new QueryCondition();
    	if(StringUtils.isNotBlank(id)){
    		sql = " from Work w where w.sjid=:sjid and w.id!=:id order by createTime desc";
    		StringBuffer hsb = new StringBuffer(sql);
    		data.put("sjid", sjid);
    		data.put("id", id);
    		list = workDao.find(hsb.toString(), data);
    	}else{
    		sql = " from Work w where w.sjid=:sjid order by createTime desc";
    		StringBuffer hsb = new StringBuffer(sql);
    		data.put("sjid", sjid);
    		list = workDao.find(hsb.toString(), data);
    	}
		if(null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
    }
}
