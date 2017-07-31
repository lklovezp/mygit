package com.hnjz.app.work.zx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.PublicColumnEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.configchecktemplate.ConfigManager;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.wf.enums.ProcessEnum;
import com.hnjz.wf.process.ApplyProcessManager;

@Service("zxWorkManagerImpl")
public class ZxWorkManagerImpl extends ManagerImpl implements ZxWorkManager  {
	
	/**日志*/
    protected  final Log      log = LogFactory.getLog(getClass());
	
	/** 任务 */
    @Autowired
    private WorkDao                 workDao;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    protected WorkManagerImpl workManager;
    
    @Autowired
    private CommWorkManager    commWorkManager;
    
    @Autowired
    private ApplyProcessManager processManager;
    
    @Autowired
    private LawobjManager    lawobjManager;
    
    @Autowired
	private ConfigManager configManager;
    
    @Autowired
    private IndexManager     indexManager;
    
    
    @Override
	public BlZxxdForm getBlZxxdFormData(String applyId) {
    	BlZxxdForm blZxxdForm=new BlZxxdForm();
    	
    	String sql3=" from TBizTaskandtasktype where taskid=? and tasktypeid='"+TaskTypeCode.ZXXD.getCode()+"'";
		List<TBizTaskandtasktype> ttplist=this.dao.find(sql3,applyId);
		if(ttplist!=null&&ttplist.size()>0){
			blZxxdForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// 检查开始时间（默认）
			//手机端时间值的处理
			if(StringUtils.isNotBlank(blZxxdForm.getJcsj1())){
				blZxxdForm.setJcsj1(blZxxdForm.getJcsj1().substring(0, blZxxdForm.getJcsj1().length()-3));
			}
			//默认显示时截止时间比开始时间大两个小时
			Date date = new Date();     
			Calendar   dar=Calendar.getInstance();     
			dar.setTime(date);     
			dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
			Date sdf = dar.getTime();
			blZxxdForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// 检查截止时间（默认）
			//手机端时间值的处理
			if(StringUtils.isNotBlank(blZxxdForm.getJcsj2())){
				blZxxdForm.setJcsj2(blZxxdForm.getJcsj2().substring(0, blZxxdForm.getJcsj2().length()-3));
			}
			//blZxxdForm.setJcsj1(DateUtil.getDate(ttplist.get(0).getJcsj1()));//检查时间end
			//blZxxdForm.setJcsj2(DateUtil.getDate(ttplist.get(0).getJcsj2()));//检查时间end
			blZxxdForm.setJcyq(ttplist.get(0).getJcyq());//检查要求
			blZxxdForm.setJcmd(ttplist.get(0).getJcmd());//检查目的
		}
		
		return blZxxdForm;
	}

	@Override
	public void saveZxWorkzxBL(String applyId, BlZxxdForm blZxxdForm) {
		//离线版标识添加，进行离线版数据保存，然后打包
		String biaoshi = indexManager.sysVer;
		//3、检查时间；4、监察模板
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_BIZ_TASKANDTASKTYPE t");
		if("0".equals(biaoshi)){
			sql.append(" set t.JCSJ1_='" + blZxxdForm.getJcsj1()
					+ "', t.JCSJ2_='" + blZxxdForm.getJcsj2()
					+ "'");
		}else{
			sql.append(" set t.JCSJ1_=to_date('"+blZxxdForm.getJcsj1()+"','yyyy-MM-dd hh24:mi:ss')");
			sql.append(" ,t.JCSJ2_=to_date('"+blZxxdForm.getJcsj2()+"','yyyy-MM-dd hh24:mi:ss')");
		}
		sql.append(" ,t.JCYQ_='").append(blZxxdForm.getJcyq()).append("'");
		sql.append(" ,t.JCMD_='").append(blZxxdForm.getJcmd()).append("'");
		sql.append(" where t.TASKID_='").append(applyId).append("'");
		sql.append(" and t.TASKTYPEID_='").append(TaskTypeCode.ZXXD.getCode()).append("'");
		this.dao.exec(sql.toString());
		
	}
	
	@Override
	public List<Map<String, String>> zxZfdxTableData(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		StringBuilder sqlstr = new StringBuilder();
		sqlstr.append("select t1.id_,t1.lawobjname_,case when t1.newtaskid_ is null then '未分派' else t2.state_ end,t2.zxr_name_,t2.end_time_,t3.jcsj1_,t3.jcsj2_,t1.newtaskid_ " +
				"from T_BIZ_TASKANDLAWOBJ t1 " +
				"left join WORK_ t2 on t1.newtaskid_=t2.id_ " +
				"left join T_BIZ_TASKANDTASKTYPE t3 " +
				"on t2.id_=t3.taskid_ " +
				"where t1.taskid_=?");
		List<Object[]> listobj = dao.findBySql(sqlstr.toString(),applyId);
		Map<String, String> dataObject = null;
		String sj1 = "";
		String bijiao = "";
		String sj2 = "";
		for (Object[] ele : listobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele[0].toString());//id
			dataObject.put("lawobjname", ele[1].toString());//执法对象名称
			dataObject.put("rwztCode", ele[2]==null?"":ele[2].toString());//任务状态code
			dataObject.put("rwzt", ele[2]==null?"":WorkState.getNote(ele[2].toString()));//任务状态
			dataObject.put("zbry", ele[3]==null?"":ele[3].toString());//主办人员
			dataObject.put("yqwcsx", ele[4]==null?"":ele[4].toString());//要求完成时限
			//专项子任务时间段值的获取方法
			String sql3 = " from TBizTaskandtasktype where taskid=? and tasktypeid='" + TaskTypeCode.ZXXD.getCode() + "'";
			List<TBizTaskandtasktype> ttplist = this.dao.find(sql3, ele[7]==null?"":ele[7].toString());
			if(ttplist!=null && ttplist.size()>0){
				if(ttplist.get(0).getJcsj1() != null){
					//sj1 = DateUtil.getDateTime(ele[5] == null ? new Date() : ele[5]) + "至";
					sj1 = ttplist.get(0).getJcsj1().toString().substring(0, 16) +"至";//检查开始时间转换格式
					bijiao = ttplist.get(0).getJcsj1().toString().substring(0, 10);
					//sj1 = ele[5].toString()+"至";
				}
				if(ttplist.get(0).getJcsj2() != null){
					if(bijiao.equals(ttplist.get(0).getJcsj2().toString().substring(0, 10))){
						sj2 = ttplist.get(0).getJcsj2().toString().substring(10, 16);//检查结束时间转换格式
					}else{
						sj2 = ttplist.get(0).getJcsj2().toString().substring(0, 16);//检查结束时间转换格式
					}
					//sj2 = ele[6].toString();
				}
			}
			dataObject.put("jcsj", sj1+sj2);//检查专项子任务的时间段值
			StringBuilder operate = new StringBuilder();
			StringBuilder operateFileUrl = new StringBuilder();
			String operateFileName="";
			if(null!=ele[7]&&StringUtil.isNotBlank(ele[7].toString())){
				//子任务压缩包
        		Map<String, String> zipFileMap = null;
        		FyWebResult re = commonManager.queryFileList(ele[7].toString(), "N",FileTypeEnums.ZXXDZRWYSB.getCode(), "1", null);
        		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        		rowsList=re.getRows();
        		if(rowsList.size()>0){
        			zipFileMap=rowsList.get(0);
        			operate.append(" <a onclick='download1(this)' id='"+zipFileMap.get("id")+"' >检查详情</a>  ");
        			
        			operateFileUrl.append("/download.mo?id="+zipFileMap.get("id"));
        			operateFileName=zipFileMap.get("filename");
        		}
			}
			dataObject.put("operate", operate.toString());
			dataObject.put("operateFileUrl", operateFileUrl.toString());
			dataObject.put("operateFileName", operateFileName);
			
			rows.add(dataObject);
		}
		
		return rows;
	}
	
	@Override
	public List<Map<String, String>> zxZfdxTableData_wfp(String applyId) {
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		StringBuilder sqlstr = new StringBuilder();
		sqlstr.append("select t1.id_,t1.lawobjname_,case when t1.newtaskid_ is null then '未分派' else t2.state_ end,t2.zxr_name_,t3.jcsj1_ " +
				"from T_BIZ_TASKANDLAWOBJ t1 " +
				"left join WORK_ t2 on t1.newtaskid_=t2.id_ " +
				"left join T_BIZ_TASKANDTASKTYPE t3 " +
				"on t2.id_=t3.taskid_ " +
				"where t1.taskid_=? and t1.newtaskid_ is null");
		List<Object[]> listobj = dao.findBySql(sqlstr.toString(),applyId);
		Map<String, String> dataObject = null;
		for (Object[] ele : listobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele[0].toString());//id
			dataObject.put("lawobjname", ele[1].toString());//执法对象名称
			dataObject.put("rwzt", WorkState.getNote(ele[2].toString()));//任务状态
			dataObject.put("zbry", ele[3]==null?"":ele[3].toString());//主办人员
			dataObject.put("jcsj", ele[4]==null?"":ele[4].toString());//检查时间
			rows.add(dataObject);
		}
		
		return rows;
	}
	
	@Override
	public void saveZxrwfg(String applyId,String[] zfdxid,String[] zbry,String[] yqwcsx) throws Exception{
		/**
		 * 1、派发一条新任务，任务名称为：当前任务名称+（子任务）；任务类型为：“专项行动”；主办人为：zbry；要求完成时限为：yqwcsx；
		 * 2、执法对象任务关联表重新插入：信息拷贝与zfdxid对应记录；
		 * 3、新生成的任务id回写至zfdxid对应字段；
		 * 4、启动新的任务流程；
		 */
		if(zfdxid!=null){
			for(int i=0;i<zfdxid.length;i++){
				if(StringUtil.isNotBlank(zbry[i])){
					String newWorkId=saveNewWorkInfo(applyId,zfdxid[i],zbry[i],yqwcsx[i]);//生成任务
					saveNewWorkTaskType(newWorkId,TaskTypeCode.ZXXD.getCode());//保存任务类型
					saveNewWorkZfdx(newWorkId,zfdxid[i]);//保存执法对象
					saveNewWorkZbr(newWorkId);//保存任务主办人
					Work zrwwork = workManager.get(newWorkId);
					saveZxZrwTask(zrwwork);//启动流程
				}
			}
		}
		
	}
	/**
	 * 生成新的专项子任务
	 */
	private String saveNewWorkInfo(String oldWorkId,String zfdxid,String zbry,String yqwcsx){
		Work oldwork = workManager.get(oldWorkId);
		TSysUser cur = CtxUtil.getCurUser();
        Date date = Calendar.getInstance().getTime();
        
        TBizTaskandlawobj tBizTaskandlawobj=(TBizTaskandlawobj)this.dao.get(TBizTaskandlawobj.class, zfdxid);
		
		//新work
	    Work work = new Work();
        work.setCreateTime(date);
        work.setCreateUser(cur);
        Date end = DateUtil.getEndDatetime(yqwcsx);
        work.setEndTime(end);//要求完成时限
        work.setIsActive(YnEnum.Y.getCode());
        work.setAreaid(cur.getAreaId());
        work.setStartTime(date);
        work.setName(tBizTaskandlawobj.getLawobjname()+"专项行动");//任务名称(执法对象名称+专项行动)
        work.setWorkNote(tBizTaskandlawobj.getLawobjname()+"专项行动");
        work.setPsyj(tBizTaskandlawobj.getLawobjname()+"专项行动");
        work.setDjrId(cur.getId());
        work.setState(WorkState.YPF.getCode());
        work.setZxrId(zbry);//主办人
        TSysUser zbUser=(TSysUser)this.dao.get(TSysUser.class, zbry);
        work.setZxrName(zbUser!=null?zbUser.getName():"");
        work.setPid(oldWorkId);
        
        //其他带过来的字段
        //work.setWorkNote(oldwork.getWorkNote());//主要内容
        work.setSource(oldwork.getSource());//任务来源
        work.setSecurity(oldwork.getSecurity());//任务密级
        work.setEmergency(oldwork.getEmergency());//紧急程度
        
        work.setZfdxType(oldwork.getZfdxType());//执法对象类型
        
        //保存WORK对象
        Work w=(Work)workDao.save(work);
		return w.getId();
	}
	/**
	 * 保存任务类型
	 */
	private void saveNewWorkTaskType(String newWorkId,String taskType){
		try {
			TSysUser curUser = CtxUtil.getCurUser();
			commWorkManager.saveTaskTypeMultiple(newWorkId,taskType,curUser);
		} catch (AppException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存执法对象
	 */
	private void saveNewWorkZfdx(String newWorkId,String zfdxid){
		TBizTaskandlawobj tBizTaskandlawobj_old=(TBizTaskandlawobj)this.dao.get(TBizTaskandlawobj.class, zfdxid);
		//修改旧的
		tBizTaskandlawobj_old.setNewtaskid(newWorkId);
		this.dao.save(tBizTaskandlawobj_old);
		
		//保存新的
		TBizTaskandlawobj tBizTaskandlawobj_new=new TBizTaskandlawobj();
		
		tBizTaskandlawobj_new.setTaskid(newWorkId);

		tBizTaskandlawobj_new.setLawobjtype(tBizTaskandlawobj_old.getLawobjtype());
		tBizTaskandlawobj_new.setLawobjid(tBizTaskandlawobj_old.getLawobjid());
		tBizTaskandlawobj_new.setLawobjname(tBizTaskandlawobj_old.getLawobjname());
		tBizTaskandlawobj_new.setRegionid(tBizTaskandlawobj_old.getRegionid());
		tBizTaskandlawobj_new.setAddress(tBizTaskandlawobj_old.getAddress());
		tBizTaskandlawobj_new.setManager(tBizTaskandlawobj_old.getManager());
		tBizTaskandlawobj_new.setManagermobile(tBizTaskandlawobj_old.getManagermobile());
		tBizTaskandlawobj_new.setNewtaskid(tBizTaskandlawobj_old.getNewtaskid());
		tBizTaskandlawobj_new.setBjcr(tBizTaskandlawobj_old.getBjcr());
		tBizTaskandlawobj_new.setZw(tBizTaskandlawobj_old.getZw());
		tBizTaskandlawobj_new.setLxdh(tBizTaskandlawobj_old.getLxdh());
		
		tBizTaskandlawobj_new.setCreateby(tBizTaskandlawobj_old.getCreateby());
		tBizTaskandlawobj_new.setCreated(tBizTaskandlawobj_old.getCreated());
		tBizTaskandlawobj_new.setIsActive(tBizTaskandlawobj_old.getIsActive());
		tBizTaskandlawobj_new.setUpdateby(tBizTaskandlawobj_old.getUpdateby());
		tBizTaskandlawobj_new.setUpdated(tBizTaskandlawobj_old.getUpdated());
		
		this.dao.save(tBizTaskandlawobj_new);
	}
	/**
	 * 保存任务主办人
	 */
	private void saveNewWorkZbr(String newWorkId){
		Work newwork = workManager.get(newWorkId);
		TSysUser cur = CtxUtil.getCurUser();
		//任务接受时保存主办人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(newWorkId);
        taskuser.setUserid(newwork.getZxrId());
        taskuser.setUsername(newwork.getZxrName());
        taskuser.setType("05");//主办人
        this.dao.save(taskuser);
	}
	/**
	 * 启动专项子任务工作流程
	 */
	public void saveZxZrwTask(Work work) throws Exception {
        TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getCreateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        /************流程参数***********/
        ProcessArgsBean bean = new ProcessArgsBean();
        //申请单编号
        bean.setApplyId(work.getId());
        //当前操作人
        bean.setCurrentOper(work.getCreateUser());
        //下步操作人
        TSysUser tuser = (TSysUser) userManager.get(TSysUser.class, work.getZxrId());
        bean.getNextOpers().add(tuser);
        /***********下步操作**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.ZXZRW_ZX.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        work.setState(WorkState.BLZ.getCode());
        //保存WORK对象
        workDao.save(work);
        /***********下步操作**********/

        /************流程参数***********/
        //启动流程
        ProcessInstance pi = processManager.saveStart(ProcessEnum.ZXZRW_TASK.getProcessKey(),
            work, bean);
        //当前任务
        Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(ProcessEnum.ZXZRW_TASK.getProcessKey(), task.getId(), work,
                bean);
        }
        
        //任务派发时保存人员信息到任务人员信息 T_BIZ_TASKUSER表
        TBizTaskuser taskuser=new TBizTaskuser();
        taskuser.setTaskid(work.getId());
        taskuser.setUserid(cur.getId());
        taskuser.setUsername(cur.getName());
        taskuser.setType(TaskUserEnum.PFR.getCode());//派发人
        this.dao.save(taskuser);
        
        //流转记录
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(date);
        this.saveLog(cur, cStart.getTime(), WorkLogType.PF, WorkState.YPF, work, cStart.getTime());//派发
    }
	
	/**
     * 记录任务的操作日志
     * 
     * @param czr 操作人
     * @param czsj 操作时间
     * @param opType 操作类型 {@link WorkLogType}
     * @param state 当前任务状态
     * @param work 任务
     */
    public void saveLog(TSysUser czr, Date czsj, WorkLogType opType, WorkState state, Work work,Date startTime) {
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
        if(WorkState.BLZ.getCode().equals(work.getState())){
        	lo.setNote("");
        }else{
        	lo.setNote(work.getPsyj());
        }
        
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("插入操作日志：" + lo);
        }
    }
	
	
	@Override
	public BlZxxdZrwMainForm getBlZxxdZrwMainFormData(String applyId) {
		BlZxxdZrwMainForm blZxxdZrwMainForm=new BlZxxdZrwMainForm();
		
		///////第一部分，企业信息///////
		//执法对象信息
		BlZfdxForm blZfdxForm=new BlZfdxForm();
        Map<String, String> zfdxMap = new HashMap<String, String>();
        List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        if(zfdxlistMap!=null&&zfdxlistMap.size()==1){
        	zfdxMap=zfdxlistMap.get(0);
        	blZfdxForm.setId(zfdxMap.get("id"));
        	blZfdxForm.setLawobjid(zfdxMap.get("lawobjid"));
        	blZfdxForm.setLawobjname(zfdxMap.get("lawobjname"));
        	blZfdxForm.setAddress(zfdxMap.get("address"));
        	blZfdxForm.setManager(zfdxMap.get("manager"));
        	blZfdxForm.setManagermobile(zfdxMap.get("managermobile"));
        	blZfdxForm.setBjcr(zfdxMap.get("bjcr"));
        	blZfdxForm.setZw(zfdxMap.get("zw"));
        	blZfdxForm.setLxdh(zfdxMap.get("lxdh"));
        	blZfdxForm.setZwtitle(zfdxMap.get("zwtitle"));
        }
        blZxxdZrwMainForm.setBlZfdxForm(blZfdxForm);
        
        String lawobjid = zfdxMap.get("lawobjid");
		String lawobjtype = zfdxMap.get("lawobjtype");
		String industryId = "";
		//污染源、建设项目、三产有行业字段
		if("01,02,06".contains(lawobjtype)){
			industryId=lawobjManager.getLawobjColumnValue(lawobjtype+PublicColumnEnum.hylx.getCode(), lawobjid);//行业id。
		}else{//其他执法对象类型获取默认行业
			List<TDataIndustry> list = this.dao.find("from TDataIndustry i where isActive = 'Y' AND lawobjtype = ?", lawobjtype);
			if(list.size()>0){
				industryId = list.get(0).getId();
			}
		}
        
        ///////第二部分，分开放各个任务类型的数据信息///////
        List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);//任务类型列表
        for(int i=0;i<rwlxlistMap.size();i++){
        	if(rwlxlistMap.get(i).get("id").equals(TaskTypeCode.ZXXD.getCode())){//a、专项行动子任务
        		BlZxxdZrwForm blZxxdZrwForm=new BlZxxdZrwForm();
        		
        		Map<String, String> jcmbInfo=configManager.queryJcmbInfo(TaskTypeCode.ZXXD.getCode(), lawobjtype);
        		if(null!=jcmbInfo){
        			blZxxdZrwForm.setIsexecchecklist(jcmbInfo.get("isexecchecklist"));
        			blZxxdZrwForm.setJcmbFileId(jcmbInfo.get("jcmbFileId"));
        		}
        		
        		TSysUser curu = CtxUtil.getCurUser();
        		String getTemplateSql = 
        			"select " +
        				"distinct cl.pid_ " +
        			"from " +
        				"T_Data_Checklisttemplate cl " +
        			"where " +
        				"cl.id_ in " +
        				"(" +
        					"select distinct c.templateid_ " +
        					"from " +
        						"(select * from work_ w1 where w1.areaid_ = '" + curu.getAreaId()+ "') w, " +
        						"T_Biz_Taskandlawobj l, " +
        						"(select * from T_Biz_Taskandtasktype tt1 where tt1.isactive_ = 'Y' order by tt1.updated_ ) tt, " +
        						"T_Biz_Checklist c " +
        					"where " +
        						"w.id_ = l.taskid_ and " +
        						"w.id_ = tt.taskid_ and " +
        						"w.id_ = c.taskid_ and " +
        						"w.isactive_ = 'Y' and " +
        						"l.isactive_ = 'Y' and " +
        						"l.lawobjid_ = '" + lawobjid + "' " +
        						"and tt.tasktypeid_ = '?' " +
        				")";
        		
        		//info
        		String sql1=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.ZXXD_JCR.getCode()+"'";
        		List<TBizTaskuser> taskuserlist1=this.dao.find(sql1,applyId);
        		if(taskuserlist1!=null&&taskuserlist1.size()>0){
//        			blZxxdZrwForm.setJcr(taskuserlist1.get(0).getUserid());//检查人
//        			blZxxdZrwForm.setJcrName(taskuserlist1.get(0).getUsername());//检查人name
        			//2015-7-15 修改 “现场监察”的检查人改为多选。
        			String jcrIds = "";
        			String jcrNames = "";
        			for (int k = 0; k < taskuserlist1.size(); k++) {
        				if (k > 0){
        					jcrIds += ",";
        					jcrNames += ",";
        				}
        				jcrIds += taskuserlist1.get(k).getUserid();
        				jcrNames += taskuserlist1.get(k).getUsername();
        			}
        			blZxxdZrwForm.setJcr(jcrIds);//检查人
        			blZxxdZrwForm.setJcrName(jcrNames);//检查人name
        		}
        		String sql2=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.ZXXD_JLR.getCode()+"'";
        		List<TBizTaskuser> taskuserlist2=this.dao.find(sql2,applyId);
        		if(taskuserlist2!=null&&taskuserlist2.size()>0){
        			blZxxdZrwForm.setJlr(taskuserlist2.get(0).getUserid());//记录人
        			blZxxdZrwForm.setJlrName(taskuserlist2.get(0).getUsername());//记录人name
        		}
        		String sql3=" from TBizTaskandtasktype where taskid=? and tasktypeid='"+TaskTypeCode.ZXXD.getCode()+"'";
        		List<TBizTaskandtasktype> ttplist=this.dao.find(sql3,applyId);
        		if(ttplist!=null&&ttplist.size()>0){
        			blZxxdZrwForm.setJcsj1(DateUtil.getDateTime(ttplist.get(0).getJcsj1() == null ? new Date() : ttplist.get(0).getJcsj1()));// 检查开始时间（默认）
					//手机端时间值的处理
					if(StringUtils.isNotBlank(blZxxdZrwForm.getJcsj1())){
						blZxxdZrwForm.setJcsj1(blZxxdZrwForm.getJcsj1().substring(0, blZxxdZrwForm.getJcsj1().length()-3));
					}
					//默认显示时截止时间比开始时间大两个小时
					Date date = new Date();     
					Calendar   dar=Calendar.getInstance();     
					dar.setTime(date);     
					dar.add(java.util.Calendar.HOUR_OF_DAY, 2);
					Date sdf = dar.getTime();
					blZxxdZrwForm.setJcsj2(DateUtil.getDateTime(ttplist.get(0).getJcsj2() == null ? sdf : ttplist.get(0).getJcsj2()));// 检查截止时间（默认）
					//手机端时间值的处理
					if(StringUtils.isNotBlank(blZxxdZrwForm.getJcsj2())){
						blZxxdZrwForm.setJcsj2(blZxxdZrwForm.getJcsj2().substring(0, blZxxdZrwForm.getJcsj2().length()-3));
					}
        			//blZxxdZrwForm.setJcsj(DateUtil.getDate(ttplist.get(0).getJcsj1()==null?new Date():ttplist.get(0).getJcsj1()));//检查时间
        			if (StringUtil.isNotBlank(ttplist.get(0).getJcmb())) {// 如果之前做过检查单
        				blZxxdZrwForm.setJcmbId(ttplist.get(0).getJcmb());
        				TDataChecklisttemplate tc = (TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, ttplist.get(0).getJcmb());
        				//zhwm 封装检查模板名称
        				blZxxdZrwForm.setJcmbName(tc.getName());
					} else {
						List<String> tempid = this.dao.findBySql(getTemplateSql.replace("?", TaskTypeCode.ZXXD.getCode()));
						if (tempid.size() > 0 && StringUtil.isNotBlank(tempid.get(0))){
							blZxxdZrwForm.setJcmbId(tempid.get(0));
							blZxxdZrwForm.setJcmbName(((TDataChecklisttemplate)this.dao.get(TDataChecklisttemplate.class, tempid.get(0))).getName());
						} else {
							List<TDataChecklisttemplate> clts = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and iscurver = 'Y' and industryid = ? and tasktypeid = ? ", industryId, rwlxlistMap.get(i).get("id"));
							if (clts.size() > 0){
								blZxxdZrwForm.setJcmbId(clts.get(0).getId());
								blZxxdZrwForm.setJcmbName(clts.get(0).getName());
							}
						}
					}
        			//检查地点
        			blZxxdZrwForm.setJcdd(StringUtils.isNotBlank(ttplist.get(0).getJcdd())?ttplist.get(0).getJcdd():"");
        		}
        		
				// 检查记录
				List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZXXDZRWJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(0).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(0).put("type","0");
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.ZXXDZRWMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(k).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(k).put("type","1");
					jcjlFileMap.add(rowsList.get(k));
				}
				blZxxdZrwForm.setJcjlFileMap(jcjlFileMap);
        		//监察报告
        		/*Map<String, String> jcbgFileMap = null;
        		FyWebResult re2 = commonManager.queryFileList(applyId, "N",FileTypeEnums.ZXXDZRWJCBG.getCode(), "1", null);
        		List<Map<String, String>> rowsList2 = new ArrayList<Map<String, String>>();
        		rowsList2=re2.getRows();
        		if(rowsList2.size()>0){
        			jcbgFileMap=rowsList2.get(0);
        		}
        		blZxxdZrwForm.setJcbgFileMap(jcbgFileMap);*/
        		/*监察结论*/
        		String jcjl = commWorkManager.getJcjl(applyId, TaskTypeCode.ZXXD.getCode());
        		blZxxdZrwForm.setJcjl(jcjl);
        		blZxxdZrwForm.setRwlx(TaskTypeCode.ZXXD.getCode());
        		blZxxdZrwMainForm.setBlZxxdZrwForm(blZxxdZrwForm);
        	}
        
        }
		
		return blZxxdZrwMainForm;
	}
	
	@Override
	public void saveZxzrw_zxPage(String applyId,BlZxxdZrwMainForm blZxxdZrwMainForm){
		//离线版标识添加，进行离线版数据保存，然后打包
		String biaoshi = indexManager.sysVer;
		//执法对象info
		if(blZxxdZrwMainForm.getBlZfdxForm()!=null){
			BlZfdxForm blZfdxForm = blZxxdZrwMainForm.getBlZfdxForm();
			TBizTaskandlawobj tBizTaskandlawobj=(TBizTaskandlawobj)this.dao.get(TBizTaskandlawobj.class, blZfdxForm.getId());
			tBizTaskandlawobj.setBjcr(blZfdxForm.getBjcr());
			tBizTaskandlawobj.setZw(blZfdxForm.getZw());
			tBizTaskandlawobj.setLxdh(blZfdxForm.getLxdh());
			tBizTaskandlawobj.setZwtitle(blZfdxForm.getZwtitle());
			this.dao.save(tBizTaskandlawobj);
		}
		
		//专项子任务
		if(blZxxdZrwMainForm.getBlZxxdZrwForm()!=null){
			BlZxxdZrwForm blZxxdZrwForm = blZxxdZrwMainForm.getBlZxxdZrwForm();
			//1、检查人
			if(StringUtils.isNotBlank(blZxxdZrwForm.getJcr())){
		        //2015-7-15 修改 检查人多选
				String sql=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.ZXXD_JCR.getCode()+"'";
				this.dao.removeFindObjs(sql, applyId);
				for (int i = 0; i < blZxxdZrwForm.getJcr().split(",").length; i++) {
					TBizTaskuser taskuser=new TBizTaskuser();
			        taskuser.setTaskid(applyId);
			        taskuser.setUserid(blZxxdZrwForm.getJcr().split(",")[i]);
			        TSysUser user=(TSysUser)this.dao.get(TSysUser.class, blZxxdZrwForm.getJcr().split(",")[i]);
			        taskuser.setUsername(user.getName());
			        taskuser.setType(TaskUserEnum.ZXXD_JCR.getCode());
			        this.dao.save(taskuser);
				}
			}
			//2、记录人
			if(StringUtils.isNotBlank(blZxxdZrwForm.getJlr())){
				String sql=" from TBizTaskuser where taskid=? and type='"+TaskUserEnum.ZXXD_JLR.getCode()+"'";
				this.dao.removeFindObjs(sql, applyId);
				TBizTaskuser taskuser=new TBizTaskuser();
		        taskuser.setTaskid(applyId);
		        taskuser.setUserid(blZxxdZrwForm.getJlr());
		        TSysUser user=(TSysUser)this.dao.get(TSysUser.class, blZxxdZrwForm.getJlr());
		        taskuser.setUsername(user.getName());
		        taskuser.setType(TaskUserEnum.ZXXD_JLR.getCode());
		        this.dao.save(taskuser);
			}
	        //3、检查时间；4、监察模板
			StringBuffer sql = new StringBuffer();
			sql.append(" update T_BIZ_TASKANDTASKTYPE t");
			if("0".equals(biaoshi)){
				sql.append(" set t.JCSJ1_='" + blZxxdZrwForm.getJcsj1()
						+ "', t.JCSJ2_='" + blZxxdZrwForm.getJcsj2()
						+ "'");
			}else{
				sql.append(" set t.JCSJ1_=to_date('" + blZxxdZrwForm.getJcsj1()
						+ "','yyyy-MM-dd hh24:mi:ss') , t.JCSJ2_=to_date('" + blZxxdZrwForm.getJcsj2()
						+ "','yyyy-MM-dd hh24:mi:ss')");
			}
			//sql.append(" set t.JCSJ1_=to_date('"+blZxxdZrwForm.getJcsj()+"','yyyy-MM-dd hh24:mi:ss')");
			sql.append(" ,t.JCMB_='").append(blZxxdZrwForm.getJcmbId()).append("'");
			sql.append(" ,t.JCDD_='").append(blZxxdZrwForm.getJcdd())
			.append("'");
			sql.append(" where t.TASKID_='").append(applyId).append("'");
			sql.append(" and t.TASKTYPEID_='").append(TaskTypeCode.ZXXD.getCode()).append("'");
			this.dao.exec(sql.toString());
			// 5、监察结论
			try {
				commWorkManager.saveJcjl(applyId,TaskTypeCode.ZXXD.getCode(), blZxxdZrwForm.getJcjl());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void saveZxzrw_zxPageBlwb(String applyId,String taskId) throws Exception{
		TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        work.setZxtime(Calendar.getInstance().getTime());
        
        work.setState(WorkState.YGD.getCode());
        work.setGdsj(date);
        work.setGdUser(cur);
        work.setIsOver(true);
        
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        
        processManager.saveNext(ProcessEnum.ZXZRW_TASK.getProcessKey(), taskId, work, bean);
        this.workDao.save(work);
        
        //更新办理的结束时间
        List<WorkLog> listLog = this.dao.find("from WorkLog w where w.work.id= ? and w.operateType=?",work.getId(),WorkLogType.ZX.getCode());
        if(listLog.size()>0){
        	WorkLog blLog = listLog.get(0);
        	blLog.setCzsj(date);
        	blLog.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(blLog.getStartTime(), blLog.getCzsj()));
        	this.dao.save(blLog);
        }
        //流转记录
        this.saveLog(cur, date, WorkLogType.GD, WorkState.YGD, work, date);//归档
	}
	
	@Override
	public ResultBean checkZxBlBL(String applyId) {
        ResultBean rb = new ResultBean();
        
        StringBuffer msg = new StringBuffer();
        BlZxxdForm blZxxdForm=this.getBlZxxdFormData(applyId);
        
        //专项行动
        if(blZxxdForm != null){
        	//1、检查要求
        	if(StringUtils.isBlank(blZxxdForm.getJcyq())){
        		msg.append("'专项行动:检查要求',\n");
        	}
        	//2、检查目的
        	if(StringUtils.isBlank(blZxxdForm.getJcmd())){
        		msg.append("'专项行动:检查目的',\n");
        	}
        	
            //////////////////附件必填校验////////////////
        	String fileType="ZXXDJCBG,ZXXDQTZL";
        	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        	list=commonManager.getIsBtlist(fileType);
        	for(int i=0;i<list.size();i++){
        		if("Y".equals(list.get(i).get("isBT"))){//必填
        			FyWebResult re = commonManager.queryFileList(applyId, "N",list.get(i).get("fileTypeEnumName"),"", "1", null);
        			List<Map<String, String>> reList=re.getRows();
        			if(reList==null || reList.size()<=0){
        				msg.append("'专项行动:"+FileTypeEnums.getNameByCode(FileTypeEnums.getTypeByEnumName(list.get(i).get("fileTypeEnumName")))+"',\n");
        			}
        		}
        	}
        	
        	//3、任务分工(子任务)
        	List<Map<String, String>> listMap = this.zxZfdxTableData(applyId);
        	if(listMap!=null){
        		Boolean isZrwWc=true;//子任务是否完成
        		for(int i=0;i<listMap.size();i++){
        			if(!listMap.get(i).get("rwztCode").equals(WorkState.YGD.getCode())){
        				isZrwWc=false;
        			}
        		}
        		if(!isZrwWc){
        			msg.append("'专项行动:子任务尚未办理完成',\n");
        		}
        	}
        }

        if(StringUtils.isNotBlank(msg.toString())){
        	rb.setResult(false);
        	rb.setMsg("办理缺少必填项：\n"+msg.toString()+"请重新办理！");
        	return rb;
    	}else{
    		rb.setResult(true);
        	rb.setMsg("验证通过");
        	return rb;
    	}
    }
	
	@Override
	public ResultBean checkZxZrwBlBL(String applyId) {
        ResultBean rb = new ResultBean();
        
        StringBuffer msg = new StringBuffer();
        BlZxxdZrwMainForm blZxxdZrwMainForm=this.getBlZxxdZrwMainFormData(applyId);
        
        //专项行动子任务
        if(blZxxdZrwMainForm.getBlZxxdZrwForm() != null){
        	//1、检查人
        	if(StringUtils.isBlank(blZxxdZrwMainForm.getBlZxxdZrwForm().getJcr())){
        		msg.append("'专项行动子任务:检查人',\n");
        	}
        	//2、记录人
        	if(StringUtils.isBlank(blZxxdZrwMainForm.getBlZxxdZrwForm().getJlr())){
        		msg.append("'专项行动子任务:记录人',\n");
        	}
        	//3、检查时间
        	if(StringUtils.isBlank(blZxxdZrwMainForm.getBlZxxdZrwForm().getJcsj1())){
        		msg.append("'专项行动子任务:检查开始时间',\n");
        	}
        	//4、检查时间
        	if(StringUtils.isBlank(blZxxdZrwMainForm.getBlZxxdZrwForm().getJcsj2())){
        		msg.append("'专项行动子任务:检查结束时间',\n");
        	}

        	//5、监察结论
        	if(StringUtils.isBlank(blZxxdZrwMainForm.getBlZxxdZrwForm().getJcjl())){
        		msg.append("'专项行动子任务:监察结论',\n");
        	}
        	//6、监察模板
        	//////////////////附件必填校验////////////////
        	String fileType="ZXXDZRWJCJL,ZXXDZRWJCJLSMJ,ZXXDZRWXZWS,ZXXDZRWQTZL,ZXXDZRWCLYJS";
        	List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        	list=commonManager.getIsBtlist(fileType);
        	for(int i=0;i<list.size();i++){
        		if("Y".equals(list.get(i).get("isBT"))){//必填
        			FyWebResult re = commonManager.queryFileList(applyId, "N",list.get(i).get("fileTypeEnumName"),"", "1", null);
        			List<Map<String, String>> reList=re.getRows();
        			if(reList==null || reList.size()<=0){
        				msg.append("'专项行动子任务:"+FileTypeEnums.getNameByCode(FileTypeEnums.getTypeByEnumName(list.get(i).get("fileTypeEnumName")))+"',\n");
        			}
        		}
        	}
        }

        if(StringUtils.isNotBlank(msg.toString())){
        	rb.setResult(false);
        	rb.setMsg("办理缺少必填项：\n"+msg.toString()+"请重新办理！");
        	return rb;
    	}else{
    		rb.setResult(true);
        	rb.setMsg("验证通过");
        	return rb;
    	}
    }
	
	
	@Override
	public void saveZxzrw_zip(String applyId){
		try{
			Work work = workDao.get(applyId);
	        
	        //执法对象info
	        Map<String, String> zfdxMap = new HashMap<String, String>();
	        List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
	        if(zfdxlistMap!=null&&zfdxlistMap.size()==1){
	        	zfdxMap=zfdxlistMap.get(0);
	        }
	        
	        List<String> ids=new ArrayList<String>();
	        String canDel="N,N,N,N,N,N,N,N,N,N,N,N";
	        String fileTypeEnumName="ZXXDZRWJCJL,ZXXDZRWJCJLSMJ,ZXXDZRWJCBG,ZXXDZRWXZWS,ZXXDZRWQTZL,ZXXDZRWCLYJS,ZXXDZRWMOREJCBL,ZXXDZRWSPZL,ZXXDZRWLYZL,ZXXDZRWZP,ZXXDZRWHPPFWJ,ZXXDZRWYSPFWJ";
	        FyWebResult re = commonManager.queryFileListMulfileType(applyId, canDel,fileTypeEnumName, "1", null);
			List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
			rowsList=re.getRows();
			for(int i=0;i<rowsList.size();i++){
				ids.add(rowsList.get(i).get("id"));
			}
			
			String in = "";
			for (int i = 0; i < ids.size(); i++) {
				if (i > 0){
					in += ",";	
				}
				in += "'" + ids.get(i) + "'";
			}
			List<Object[]> files = this.dao.find("select a.name, a.osname, a.type, a.relativepath, b.orderby, '' as num from TDataFile a, TSysDic b where a.type = b.code and a.id in (" + in + ") order by b.orderby");
			
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String time = sdf.format(date);
			// 压缩文件夹路径
			String path = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + zfdxMap.get("lawobjname")+time;
			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdir();
			}
			String sourcePath = "";
			// 将源文件拷贝到压缩目录下
			int num = 1;
			Object order = null;
			String flag = String.valueOf(files.get(0)[4]);
			for (int i = 0; i < files.size(); i++) {
				if (i > 0){
					if (!flag.equals(String.valueOf(files.get(i)[4]))){
						num++;
						flag = String.valueOf(files.get(i)[4]);
					}
					if (num < 10){
						order = "0" + String.valueOf(num);
					} else {
						order = String.valueOf(num);
					}
				} else {
					order = "0" + String.valueOf(num);
				}
				files.get(i)[5] = order;
				sourcePath = FileUpDownUtil.path + File.separator + String.valueOf(files.get(i)[3]) + File.separator + String.valueOf(files.get(i)[1]);
				FileUtil.copyFile(sourcePath, path, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
			}
			// 压缩
			FileZipUtil zip = new FileZipUtil();
			zip.zipFolder(path, path + ".zip");
			
			File file = new File(path + ".zip");
			if (file.exists()) {
				//////保存的两份记录“文件类型”都用一个枚举//////
				//子任务保存一份
				InputStream is1 = new FileInputStream(file);
				commonManager.saveFile(is1, applyId, FileTypeEnums.ZXXDZRWYSB.getCode(), "work", zfdxMap.get("lawobjname")+time + ".zip", ((Integer) is1.available()).longValue());
				//父任务保存一份
				InputStream is2 = new FileInputStream(file);
				commonManager.saveFile(is2, work.getPid(), FileTypeEnums.ZXXDZRWYSB.getCode(), "work", zfdxMap.get("lawobjname")+time + ".zip", ((Integer) is2.available()).longValue());
			}
		}catch (Exception e) {
			//System.out.println("子任务打包出错："+ e);
			log.error("", e);
        }
	}

}
