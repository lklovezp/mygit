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
	/**������������*/
    String key = ProcessEnum.GENERAL_TASK.getProcessKey();

    /**��־*/
    private Logger                  log = Logger.getLogger(this.getClass());
    /** ���� */
    @Autowired
    private WorkDao                 workDao;
    /** ���̴��� */
    @Autowired
    private ApplyProcessManager     processManager;
    /** �û����� */
    @Autowired
    private UserManager             userManager;
    /** ���Ź��� */
    @Autowired
    private OrgManager              orgManager;

    /**WorkNode�Ĵ���**/
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
    
    /**�����ɷ�**/
    @Autowired
    private WorkPf           workPf;
    
    public void saveZp(String applyId, String taskId, String jsrId, String psyj, TSysUser cur) throws Exception {
        Assert.notNull(jsrId, "�����˲���Ϊ�գ�");
        Work work = workDao.get(applyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jsrId", jsrId);
        map.put("psyj", psyj);
        workProxy.execute("zpNode", applyId, taskId, null, map, cur);
    }
    
    /**
     * 
     * �������ܣ��������ɣ��൱��ɾ����������е���������������������Ϣ��Ȼ����������ɷ���
     * ���������
     * ����ֵ��
     * @return 
     */
    public Work saveCp(WorkDto frm, String jsrId, TSysUser cur) throws Exception {
    	/**
    	 * ��������������ϼ����ɵ����񣬽�������ʱ��1���ϼ�������idҪ����������2���ϼ���ת��¼Ҫ������
    	 */
    	String sjid="";
    	List<WorkLog> list_sjlzjl=new ArrayList<WorkLog>();
    	 //���WORK����
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
    	
        //1��������Ա��Ϣ��
    	String sql1=" from TBizTaskuser where taskid=? and type!='"+TaskUserEnum.SCR.getCode()+"'";
		this.dao.removeFindObjs(sql1, frm.getId());
    	//2��������־��
		String sql2=" from WorkLog where work.id=? ";
		this.dao.removeFindObjs(sql2, frm.getId());
    	//3�����������
		String sql3=" from Wfdb where workId=? ";
		this.dao.removeFindObjs(sql3, frm.getId());
    	//4���������̽ڵ������Ϣ��
		String sql4=" from WfApplyStep where applyId=? ";
		this.dao.removeFindObjs(sql4, frm.getId());
		
		//5�������ɷ�
		Work newWork=workPf.savePf(frm, jsrId, cur, "");
		
		///////
		if(StringUtil.isNotBlank(work.getSjid())){
			//1���ϼ�������idҪ����������
			newWork.setSjid(sjid);
			this.dao.save(newWork);
			//2���ϼ���ת��¼Ҫ������
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
     * �������ܣ���������
     * ���������applyId:����id;taskId:����id;areaId:��������id;psyj:��ʾ���;xpfjIds:���ɸ���ids;xplspsIds:������ʷ��ʾids;
     * ����ֵ��
     */
    public void saveZpXpds(String applyId, String taskId, String areaId,String psyj,String xpfjIds,String xplspsIds, String jsrId) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        
        //�ı�����״̬Ϊ�����д����С�
        work.setState(WorkState.YXP.getCode());
        
        //������������id
        work.setXpCity(areaId);
        
        work.setXpfjIds(xpfjIds);//���ɸ���ids
        work.setXplspsIds(xplspsIds);//������ʷ��ʾids
        
        StringBuffer shrIds = new StringBuffer();
        //���缶��Ա���ӵ������Ա������
        List<TSysUser> userList = orgManager.getUsers(null, cur.getId(), Boolean.FALSE);
        
        //2015-6-9 �޸� ������ܶ�ֱ������ �����ٰ��Լ����ϣ�Ҫ���ظ���
        UserPosition p = orgManager.getPosition(cur.getId());
        if(p == UserPosition.ZD){
        }else{
        	userList.add(cur);//���ɵ���ʱ�Լ��ǵ�һ������ˣ����Լ�Ҳ����ȥ��
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
                log.debug("����û���" + s.getName());
            }
        }
        
        work.setPsyj(psyj);//��ʾ���
        work.setNextActions("");
        this.workDao.save(work);
        
        //��������ʱ������Ա��Ϣ��������Ա��Ϣ T_BIZ_TASKUSER��
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.XPR.getCode());//������
        
        this.dao.save(taskuser);
        
        
        /************���̲���***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //ת��
        bean.setDirection(WorkTransferDirectionEnum.PF_XPDS);
        bean.setResult(WorkTransferDirectionEnum.PF_XPDS.getText());
        /************���̲���***********/
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
        
        //��ʾ���
        lo.setNote(work.getPsyj());
        
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("������־�ɹ���");
        }
        /**���ɲ���**/
        WorkDto workDto=new WorkDto(work);
        
        //���ɸ������
        List<Map<String, String>> pFileInfo=new ArrayList<Map<String,String>>();
        //��ȡ������Ϣ
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
        
        //������ʷ��ʾ���
        List<Map<String, String>> pLspsInfo=new ArrayList<Map<String,String>>();
        //��ȡ��ʷ��ʾ��Ϣ
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
        //���ϵ�ǰ�����˵���ʾ���
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
        
        //��������
		List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
		String rwlxIds = "";
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
		if("24".equals(rwlxIds)){	//�ճ��칫����
			workDto.setRcbg("Y");
			workDto.setJsrId(jsrId);
		}else if("13".equals(rwlxIds)){	 //�ŷ�Ͷ������
			workDto.setXfts("Y");
			workDto.setJsrId(jsrId);
		}
		Document document = null;
		//������������ص����ɽڵ㴫ֵ
		//2015-3-17 ���� �������������id����ѯ������������쵼��Ȼ���Ը��쵼���ݸ�������������Ϣ������һ���µ�����
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
        	//��Ҫ�����µ��������֧�ӵ���תȻ����ϵ�����Ҫ����
    		this.saveZrw(workDto, areaId, newresults, "zfjczlc", jsrId);
            /**���ɲ���**/
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
        	//��Ҫ�����µ��������֧�ӵ���תȻ����ϵ�����Ҫ����
    		this.saveZrw(workDto, areaId, newresults, "sjxftsTask", jsrId);
            *//**���ɲ���**//*
		}*/
    }
    
    /**
     * �ɷ�-��������<br/>
     * ���ɵ�����Ϊ�����񣬿ɽ��еĲ�������Ȩ�ޣ�ת�ɣ�
     * @param frm ������Ϣ
     * @param areaId ����Id
     * @throws Exception
     */
    public void saveZrw(WorkDto frm, String areaId, String newresults,String flowId ,String jsrId) throws Exception {
    	//2015-3-17 ���� �������������id����ѯ������������쵼��Ȼ���Ը��쵼���ݸ�������������Ϣ������һ���µ�����
    	TSysUser cur = userManager.getLeaderByAreaId(areaId);
    	
    	/*TSysUser cur = userManager.getSj();*/
        Date date = Calendar.getInstance().getTime();

		//������������ص����ɽڵ㴫ֵ
		//2015-3-17 ���� �������������id����ѯ������������쵼��Ȼ���Ը��쵼���ݸ�������������Ϣ������һ���µ�����
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
        //���WORK����
        Work work = new Work();
        
        //����������ص�ֵ
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
        
        work.setSource("1");//������Դ����������Ĭ��Ϊ���ϼ����ġ���
        work.setSecurity(frm.getSecurity());//�����ܼ�
        work.setEmergency(frm.getEmergency());//�����̶�
        work.setDjrId(cur.getId());//�Ǽ��ˣ�Ϊ��������ˣ�
        //���ɵ�ʱ��ԭ��ֱ��ѡ����У����ڿ���ֱ��ѡ�������
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, userid);
        if(StringUtils.isNotBlank(jsrId)){
        	work.setJsr(frm.getJsrId());//�ŷ��ճ�����Ľ�����
        	//TSysUser po = (TSysUser) userManager.get(TSysUser.class, jsrId);
        	//result = workflowoperator.submit(userid, workid, trackid, "Line2~Node2", jsrId, user.getName(),"");
        }else{
        	work.setJsr(cur.getId());//һ������Ľ�����
        	result = workflowoperator.submit(userid, workid, trackid, "Line2~Node2", cur.getId(), user.getName(),"");
        }
        //�������
        work.setIsxp(true);
        work.setAreaid(areaId);
        work.setSjid(frm.getId());
        Work w = this.getWorkbySjid(frm.getId(),work.getId());
    	if(w!=null){//�������ɵ�����,�˻ز���
    		work.setState(WorkState.YTH.getCode());
    	}else{
    		work.setState(WorkState.YPF.getCode());
    	}
        
        /***********�²�����**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkTransferDirectionEnum.PF_JS.getCode()).append(",");
        nextAction.append(WorkTransferDirectionEnum.PF_ZP.getCode()).append(",");
        //2015-3-17 ���ӡ����ɡ������ܡ�ת�ɡ�������ͬһ����Ĳ����������û���¼���λ����ʾ�����ɡ�������
        Boolean hasXJDW = true;//Ĭ�Ͽ������ɡ�
        List<Combobox> xjdslist = commonManager.queryAreaComboByAreaId(cur.getAreaId());
        if(xjdslist==null||xjdslist.size()==0){
        	hasXJDW = false;
        }
        if(hasXJDW){
        	nextAction.append(WorkTransferDirectionEnum.PF_XPDS.getCode()).append(",");
        }
        
        work.setNextActions(nextAction.toString());
        /***********�²�����**********/
        
        StringBuffer shrIds = new StringBuffer();
        //���缶��Ա���ӵ������Ա������
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
                log.debug("����û���" + s.getName());
            }
        }

        //����WORK�ĸ���ʱ����Ϊ��һ������Ŀ�ʼʱ��
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        //ִ��������Ϣ��ִ����������+ִ������
        Work sjWork = workDao.get(frm.getId());
		work.setZfdxType(sjWork.getZfdxType());
		
        //����WORK����
        workDao.save(work);

        //�����ʽ�������������ͺ�ִ��������Ϣ
        if("01".equals(work.getState())){
        	if("0".equals(frm.getRwmcgs())){
            	//��������
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
            	//ִ����������
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //��ȡ����ִ������info
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
            }else{
            	//��������
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
            	//ִ����������
            	commWorkManager.saveZfdxType(work.getId(), sjWork.getZfdxType());
                //��ȡ����ִ������info
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
            }
        }
        
        //�����ɷ�ʱ������Ա��Ϣ��������Ա��Ϣ T_BIZ_TASKUSER��
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.PFR.getCode());//�ɷ���
        
        this.dao.save(taskuser);
        
        
        /////////////�������ɸ������洢/////////////
        workPf.saveDownloadXPFile(work.getId(),frm.getPFileInfo(),cur);
        /////////////������ʷ��ʾ��Ϣ�洢/////////////
        workPf.saveXPLsps(work.getId(),frm.getPLspsInfo());
        
        
        /************���̲���***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //���뵥���
        bean.setApplyId(work.getId());
        //��ǰ������
        bean.setCurrentOper(work.getCreateUser());
        //�²������� ��2015-3-17 �޸� ��һ��������Ϊ����������쵼��  
        bean.getNextOpers().add(cur);
        /*bean.setNextOpers(userManager.getXpUsers(areaId));*/
        //ת��
        bean.setDirection(WorkTransferDirectionEnum.PF_ZP);
        bean.setResult(WorkTransferDirectionEnum.PF_ZP.getText());
        /************���̲���***********/
        /*//��������
        ProcessInstance pi = processManager.saveStart(this.KEY, work, bean);
        //��ǰ����
        Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(this.KEY, task.getId(), work,
                bean);
        }*/
        
        //��һ��
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime(date);    
        calendar.add(Calendar.SECOND, 1);    
        Date curDate=calendar.getTime();  
        //this.saveLog(cur, curDate, WorkLogType.PF, WorkState.YPF, work, curDate);
    }
    
    /**
     * 
     * �������ܣ������ϱ��������ͱ���+��������
     * ���������applyId:����id;sbfjIds:�ϱ�����ids;opinion:�ϱ���ʾ��Ϣ;
     * ����ֵ��
     */
    public void saveSbRwlxAndFile(String applyId, String sbfjIds,String opinion) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
    	Date date = new Date();
        Work work = workDao.get(applyId);
        
        //1���ϼ�����id
        String sjid=work.getSjid();
        
        //2���ϼ�����id
        String sbAreaId=areaManager.getPAreaIdByAreaId(cur.getAreaId());
        
        //3�����������б�
        String rwlxIds="";
        List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);
        //�洢������ ����ID+"-"+��������  ��  ������
        Map<String, String> jcjlMap = new HashMap<String,String>();
        //�洢������ ����ID+"-"+��������  ��  �ճ��칫��ע
        Map<String, String> rcbgDescMap = new HashMap<String,String>();
        if(rwlxlistMap!=null && rwlxlistMap.size()>0 && !StringUtils.equals(rwlxlistMap.get(0).get("id"), "24")){//���ճ��칫
        	 for(int i=0;i<rwlxlistMap.size();i++){
             	if(i<rwlxlistMap.size()-1){
             		rwlxIds+=rwlxlistMap.get(i).get("id")+",";
             	}else{
             		rwlxIds+=rwlxlistMap.get(i).get("id");
             	}
             	String jcjl = commWorkManager.getJcjl(applyId, rwlxlistMap.get(i).get("id"));
             	//keyֵΪ�ϼ�������ID+�ָ�����+��������ID
             	jcjlMap.put(sjid+"-"+rwlxlistMap.get(i).get("id"), jcjl);
             }
        }else{
        	//�ճ��칫��ע
         	String rcbgDesc = commWorkManager.getRcbgDesc(applyId, rwlxlistMap.get(0).get("id"));
         	rcbgDescMap.put(sjid+"-"+rwlxlistMap.get(0).get("id"), rcbgDesc);
         	rwlxIds=rwlxlistMap.get(0).get("id");
        	
        }
        
        //4���ϱ��������
        List<Map<String, String>> sbFileInfo=new ArrayList<Map<String,String>>();
        //��ɾ����ʷ�ϱ��������˻������ϱ�ʱ�����ʷ�ϱ���Ϣ��
        commonManager.removeAllBlFileByPid(work.getSjid());
        //��ȡ������Ϣ
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
        
        //5���ϱ���ʷ��ʾ���
        List<Map<String, String>> bLspsInfo=new ArrayList<Map<String,String>>();
        //��ȡ��ʷ��ʾ��Ϣ�����ϱ�һ�������ϱ�����ʾ��һ����
        Map<String, String> map=new HashMap<String, String>();
    	map.put("czrId", cur.getId());
    	map.put("czrName", cur.getName());
    	map.put("czsj", DateUtil.getDateTime(date));
    	map.put("note", opinion);
    	map.put("operateType", WorkLogType.SB.getCode());
    	map.put("workSate", WorkState.YGD.getCode());
    	map.put("startTime", DateUtil.getDateTime(date));
    	bLspsInfo.add(map);
        
        //�����ϼ��������ӿڱ�����Ϣ
        workClient.saveSbRwlxAndFile(sjid, sbAreaId,rwlxIds,sbFileInfo,bLspsInfo,jcjlMap,rcbgDescMap);
        /**���ɲ���**/
    }

    /**
     * �������
     * 
     * @param applyId ����id
     * @param taskId �������е�id,����work���е� taskId
     * @throws Exception
     */
    public void saveJs(String applyId, String taskId, TSysUser cur) throws Exception {
        workProxy.execute("jsNode", applyId, taskId, null, null, cur);
    }

    public void saveZx(String applyId, String taskId, TSysUser cur) throws Exception {
        workProxy.execute("zxNode", applyId, taskId, null, null, cur);
    }

    public void saveAddUser(String applyId, String taskId, String ptrIds) throws Exception {
        //��ѯ��ͬ�˼���
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
        /***************��ͬ���������ϡ��û�������***************/
        StringBuffer ptrIdsBuffer = new StringBuffer();
        StringBuffer ptrNamesBuffer = new StringBuffer();
        String[] ptrUserNames = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            ptrIdsBuffer.append(userList.get(i).getId()).append(",");
            ptrNamesBuffer.append(userList.get(i).getName()).append(",");
            ptrUserNames[i] = userList.get(i).getUsername();
        }
        /***************��ͬ���������ϡ��û�������***************/
        Work work = workDao.get(applyId);
        if (StringUtils.isNotBlank(work.getPtrIds())) {
            //�õ�ԭ��ͬ�˱�ż���
            String oldUserIds = work.getPtrIds();
            //��ѯԭ��ͬ���û���
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
        //ԭ��¼�����������ѡ�����ͬ�����У��޲������������ڣ�ɾ����        
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
        //�õ���Ҫ����˼���
        String[] shrIds = work.getShrids().split(",");
        if (log.isDebugEnabled()) {
            log.debug("����ˣ�" + work.getShrids());
        }
        //ȡ�����һ����Ϊ�����
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, shrIds[shrIds.length - 1]);
        if (user == null) {
            throw new Exception("�û�������");
        }
        if (log.isDebugEnabled()) {
            log.debug("�²�����ˣ�" + user.getName());
        }
        return user;
    }

    public TSysUser getBackUser(String applyId) throws Exception {
        Work work = get(applyId);
        //�õ���Ҫ����˼���
        String[] thrIds = work.getThrids().split(",");
        //ȡ�����һ����Ϊ�����
        TSysUser user = (TSysUser) userManager.get(TSysUser.class, thrIds[thrIds.length - 1]);
        if (user == null) {
            throw new Exception("�û�������");
        }
        return user;
    }

    public void saveTh(String applyId, String taskId, String opinion, TSysUser cur) throws Exception {
        workProxy.execute("thNode", applyId, taskId, opinion, null, cur);
    }

    public void saveWorkAlreadyBack(String applyId) throws Exception {
        Work work = get(applyId);
        /**��������ġ��˻��˼��ϡ���ȥ���˲������ˣ����˲����˼��롰����˼��ϡ���**/
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
     * �����ϱ���֤����֪����������ʱʹ�ã�
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean shangbaoV(String workId) throws Exception {
        Work work = get(workId);
        ResultBean rb = null;
//        if (work.getWorkType().getName().indexOf("ͨ��") != -1) {
//            rb = allowShangbao_comm(workId);
//        } else if (work.getWorkType().getName().indexOf("Υ��") != -1) {
//            rb = allowShangbao_dc(workId);
//        } else if (work.getWorkType().getName().indexOf("�ճ�") != -1) {
//            rb = allowShangbao_rc(workId);
//        }
        return rb;
    }

    /**
     * ͨ�������ϱ���֤
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_comm(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        List<WorkCommFile> fileList = commFileManager.getFileList(workId,
            DataFileType.TONGYONGRENWUFUJIAN);
        if (fileList.size() == 0) {
            rb.setMsg("����δ�ϴ����벹�丽����");
        } else {
            rb.setResult(true);
            rb.setMsg("��֤�ɹ���");
        }
        return rb;
    }

    /**
     * ���������ϱ���֤
     * modify by xugh 2013-07-10���Ӷ�Υ������ ��ͬ�� ��¼�˵���֤
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_dc(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        StringBuffer msgBuffer = new StringBuffer();
        Work work = get(workId);
//        if(StringUtils.isBlank(work.getWflxId()) ){
//            msgBuffer.append("��ѡ��Υ�����ͣ�");
//        }
        if(StringUtils.isBlank(work.getPtrIds()) ){
            msgBuffer.append("��ѡ����ͬ�ˣ�");
        }
        if(null == work.getJlr() ){
            msgBuffer.append("��ѡ���¼�ˣ�");
        }
        List<WorkCommFile> allFileList = commFileManager.getFileList(workId);
        List<WorkCommFile> xwblList = commFileManager.getFileList(allFileList,
            DataFileType.XUNWENBILU);
        if (xwblList.size() == 0) {
            msgBuffer.append("ѯ�ʱ�¼��");
        }
        List<WorkCommFile> kcblList = commFileManager.getFileList(allFileList,
            DataFileType.KANCHABILU);
        if (kcblList.size() == 0) {
            msgBuffer.append("�����¼��");
        }
        List<WorkCommFile> zjxxList = commFileManager.getFileList(allFileList,
            DataFileType.ZHENGJUXINXI);
        if (zjxxList.size() == 0) {//��֤��֤����Ϣ
            msgBuffer.append("��֤��֤����Ϣ��");
        }
        List<WorkCommFile> xckcList = commFileManager.getFileList(allFileList,
            DataFileType.XIANCHANGKANCHATU);
        if (xckcList.size() == 0) {//�ֳ�����ʾ��ͼ
            msgBuffer.append("�ֳ�����ʾ��ͼ��");
        }
        List<WorkCommFile> tupianList = commFileManager.getFileList(allFileList,
            DataFileType.TUPIAN);
        List<WorkCommFile> yinpinList = commFileManager.getFileList(allFileList,
            DataFileType.YINPIN);
        List<WorkCommFile> shipinList = commFileManager.getFileList(allFileList,
            DataFileType.SHIPIN);
        if (tupianList.size() == 0 && yinpinList.size() == 0 && shipinList.size() == 0) {//��������
            msgBuffer.append("ͼƬ/��Ƶ/��Ƶ���ϡ�");
        }
        if (msgBuffer.length() > 0) {
            rb.setMsg(msgBuffer.substring(0, msgBuffer.length() - 1) + "δ�ϴ�,�벹�丽����");
        } else {
            rb.setResult(true);
            rb.setMsg("��֤�ɹ���");
        }
        return rb;
    }

    /**
     * �ճ������ϱ���֤
     * @param workId
     * @return
     * @throws Exception
     */
    public ResultBean allowShangbao_rc(String workId) throws Exception {
        ResultBean rb = new ResultBean();
        StringBuffer msgBuffer = new StringBuffer();
        //�����Ϣ
        WorkJcinfo jcInfo = (WorkJcinfo) workDao.get(WorkJcinfo.class, workId);
        if (jcInfo == null) {
            msgBuffer.append("δ��������Ϣ,�������Ƽ����Ϣ��");
        }
        //��鵥
        if (jcInfo != null && !StringUtils.isNotBlank(jcInfo.getUrl())) {
            msgBuffer.append("δ���ɼ�鵥,�������Ƽ����Ϣ��");
        }
        //����
        List<WorkCommFile> fileList = commFileManager.getFileList(workId,
            DataFileType.JIANCHAJILUSAOMIAOJIAN);
        if (fileList.size() == 0) {//��֤��֤����Ϣ
            msgBuffer.append("����¼ɨ���δ�ϴ�,�벹�丽����");
        }
        if (msgBuffer.length() > 0) {
            rb.setMsg(msgBuffer.toString());
        } else {
            rb.setResult(true);
            rb.setMsg("��֤�ɹ���");
        }
        return rb;
    }

    /**
     * �Ƿ������˼���¼�ļ�
     * 
     * @param workId
     * @return
     */
    public boolean isCompleteCheckList(String workId) {
        boolean flag = false;
        //�����Ϣ
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
            log.debug("����״̬��" + work.getState());
            log.debug("�˻��ˣ�" + work.getThrids());
            log.debug("����ˣ�" + work.getShrids());
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
        //��ǰ�û�����˼���
        UserPosition p = orgManager.getPosition(userId);
        //2015-3-17 �޸� �������С��ϼ�id������Ϊ���ܶӡ�ʱִ���ϱ����˻�
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
            throw new Exception("�������ͣ�" + type.getId() + "����˼���û������");
        }*/
        
        //////////2015-3-13 ����Ĭ����˼���Ϊ���ܶӳ�/////////
        int shjb=UserPosition.ZD.getCode();
        
        //int shjb = Integer.parseInt(type.getShjb());
        //�˻��˵ļ���,ȡ��ߵ�
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
        //���񴴽��˵ļ����˻��˵ļ�����˼��� 3���������������Ƿ��ܹ鵵
        //��ǰ�û�����3��������Ȼ��߸ߵ�ʱ����ܹ鵵
        if (work.getCreateUser() != null) {
            /*//���ɵ����е����񣬴�����Ϊ�ϼ�
        	TSysUser sj  = userManager.getSj();
            if(!StringUtils.equals(work.getCreateUser().getId(), sj.getId())){
                UserPosition c = orgManager.getPosition(work.getCreateUser().getId());
                //�����һ���˻�ʱ����ǰ�û��ļ���ʹ����˵ļ��𶼱����õĸ�ʱ
                boolean flag = p.getCode() >= shjb && c.getCode() <= p.getCode();
                if (thjb == -9999 && flag) {
                    re.add(WorkSh.GD);
                }
                //����ڶ����˻�ʱ����Ҫ������߼����˻��˵��ж�
                boolean th = p.getCode() >= thjb;
                if (thjb != -9999 && flag && th) {
                    re.add(WorkSh.GD);
                }
            }*/
        	UserPosition c = orgManager.getPosition(work.getCreateUser().getId());
            //�����һ���˻�ʱ����ǰ�û��ļ���ʹ����˵ļ��𶼱����õĸ�ʱ
            boolean flag = p.getCode() >= shjb && c.getCode() <= p.getCode();
            if (thjb == -9999 && flag) {
                re.add(WorkSh.GD);
            }
            //����ڶ����˻�ʱ����Ҫ������߼����˻��˵��ж�
            boolean th = p.getCode() >= thjb;
            if (thjb != -9999 && flag && th) {
                re.add(WorkSh.GD);
            }
        }
        //ֻ���ܶӳ��޷��ϼ����,
        if (p != UserPosition.ZD && shrIds.length > 1) {
        	re.add(WorkSh.SJSH);
        }
        //������˻��߹鵵�Ĳ���ʱ�������˻�
        if (!re.isEmpty()) {
            re.add(WorkSh.TH);
        }

        return re;
    }

    public String saveCreateReportInfo(String workId, TSysUser sbr) throws Exception {
        Work work = get(workId);
        work.setSbr(sbr.getName());
        try {
            //����������Ϣ�ļ�
            /*WorkJcinfo jcInfo = (WorkJcinfo) workDao.get(WorkJcinfo.class, workId);
            String hsql = "from WorkCommFile where work.id = ? ";
            List<WorkCommFile> files = workDao.find(hsql, workId);
            Document doc = work.toXml(jcInfo, files);*/
            Document doc = work.toXml(null, null);
            /** ��document�е�����д���ļ��У���ǰ�����ļ����£� */
            //���������ļ����λ��
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
            //��������������ļ����
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
     * ����ִ������id��Υ������idȡ�����µ����ϱ�Υ������
     * 
     * @param zfdxId ִ������id
     * @param wflxId Υ������id
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
     * ����ִ������id�ͼ�鵥���͵õ��ѹ鵵����id
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
     * ����ִ�������ѯ�ѹ鵵���ճ�����
     * @param work Work�����������zfdxId��zfdxName��������߶������ڣ��򷵻�null
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
	//�����ϼ�id��ȡ���ɵ�����id����������ǰ����
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