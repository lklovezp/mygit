package com.hnjz.app.work.manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.nodes.BaseNode;
import com.hnjz.app.work.manager.nodes.WorkFlowOperator;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkCommFile;
import com.hnjz.app.work.po.WorkJcinfo;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateDifferenceUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.FtpUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.sal.WorkClient;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.bean.ProcessArgsBean;
import com.hnjz.wf.enums.ProcessEnum;
import com.hnjz.wf.process.ApplyProcessManager;

/**
 * 任务上报业务
 * @author zn
 * @version $Id: WorkReportManager.java, v 0.1 2013-4-2 上午02:35:46 zn Exp $
 */
@Service(value = "workReportManager")
public class WorkReportManager extends BaseNode{
    private Logger              log = Logger.getLogger(this.getClass());
    @Autowired
    private WorkDao             workDao;
    @Autowired
    private ApplyProcessManager processManager;

    @Autowired
    private WorkClient          workClient;
    
    @Autowired
	protected Dao dao;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;

    /**
     * 解析保存上报任务信息
     * @param filePath
     * @param outputPath
     * @throws Exception
     */
    public String saveParseReportInfo(String filePath, String outputPath) throws Exception {
        FileZipUtil zipUtil = new FileZipUtil();
        String unzipPath = zipUtil.unZip(filePath, outputPath);
        File unzip = new File(unzipPath);
        String workId = "";
        if (unzip.exists() && unzip.isDirectory()) {
            File[] fileArr = unzip.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                if (fileArr[i].getName().equals("workInfo.xml")) {
                    //解析保存任务相关
                    Work work = saveWorkForXml(fileArr[i]);
                    workId = work.getId();
                    //将文件存入指定目录
                    FileUtil.copyDir(unzipPath, FileUtil.getRealPath(UploadFileType.WORK.getPath(),
                        work.getId()));
                    //启动工作流
                    saveReportTask(work);
                    break;
                }
            }
        }
        return workId;
    }

    /**
     * 启动上报任务工作流程<br/>
     * 转向：非下派任务-归档；下派任务-审核
     * @param work
     * @throws Exception
     */
    public void saveReportTask(Work work) throws Exception {
        //传入的就是主流程任务，更改主流程的任务状态
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
        
        bean.setDirection(WorkTransferDirectionEnum.REPORT_XPRW);
        bean.setResult(WorkTransferDirectionEnum.REPORT_XPRW.getText());
        /***********下步操作**********/
        StringBuffer nextAction = new StringBuffer();
        nextAction.append(WorkProcessEnum.REPORT_SH.getCode()).append(",");
        work.setNextActions(nextAction.toString());
        work.setState(WorkState.YSH.getCode());
        /***********下步操作**********/
        /*//转向
        if (work.getIsxp()) {
            bean.setDirection(WorkTransferDirectionEnum.REPORT_XPRW);
            bean.setResult(WorkTransferDirectionEnum.REPORT_XPRW.getText());
            *//***********下步操作**********//*
            StringBuffer nextAction = new StringBuffer();
            nextAction.append(WorkProcessEnum.REPORT_SH.getCode()).append(",");
            work.setNextActions(nextAction.toString());
            work.setState(WorkState.YSH.getCode());
            *//***********下步操作**********//*
        } else {
            bean.setDirection(WorkTransferDirectionEnum.REPORT_FXPRW);
            bean.setResult(WorkTransferDirectionEnum.REPORT_FXPRW.getText());
            *//***********下步操作**********//*
            StringBuffer nextAction = new StringBuffer();
            nextAction.append(WorkProcessEnum.REPORT_GD.getCode()).append(",");
            work.setNextActions(nextAction.toString());
            work.setState(WorkState.YGD.getCode());
            *//***********下步操作**********//*

            WorkLog lo = new WorkLog();
            lo.setCzsj(Calendar.getInstance().getTime());
            lo.setWork(work);
            lo.setOperateType(WorkLogType.GD.getCode());
            lo.setWorkSate(WorkState.YGD.getCode());
            lo.setStartTime(stepStartTime);
            lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo
                .getCzsj()));
            this.workDao.save(lo);
        }*/
        /************流程参数***********/
        /*ProcessInstance pi = processManager.saveStart(ProcessEnum.REPORT_TASK.getProcessKey(),
            work, bean);*/
        //当前任务
        /*Task task = processManager.getActTaskFromPiId(pi.getId());
        if (task != null) {
            processManager.saveNext(ProcessEnum.REPORT_TASK.getProcessKey(), task.getId(), work,
                bean);
        }*/
    }

    /**
     * 保存归档
     * @param applyId
     * @param taskId
     * @throws Exception
     */
    public void saveGd(String applyId, String taskId) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);

        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        processManager.saveNext(ProcessEnum.REPORT_TASK.getProcessKey(), taskId, work, bean);

        WorkLog lo = new WorkLog();
        lo.setCzrId(cur.getId());
        lo.setCzrName(cur.getName());
        lo.setCzsj(date);
        lo.setWork(work);
        lo.setOperateType(WorkLogType.GD.getCode());
        lo.setWorkSate(WorkState.YGD.getCode());
        lo.setStartTime(stepStartTime);
        lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("保存日志成功：");
        }
    }

    /**
     * 任务退回到地市
     * @param applyId
     * @param taskId
     * @throws Exception
     */
    public void saveTh(String applyId, String taskId,String opinion) throws Exception {
    	TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);

        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setDirection(WorkTransferDirectionEnum.SH_THZX);
        bean.setResult(WorkTransferDirectionEnum.SH_THZX.getText());
        processManager.saveNext(ProcessEnum.REPORT_TASK.getProcessKey(), taskId, work, bean);
        work.setState(WorkState.YTH.getCode());
        this.workDao.save(work);
        
        WorkLog lo = new WorkLog();
        lo.setCzrId(cur.getId());
        lo.setCzrName(cur.getName());
        lo.setCzsj(date);
        lo.setWork(work);
        lo.setNote(opinion);
        lo.setOperateType(WorkLogType.TH.getCode());
        lo.setWorkSate(WorkState.YTH.getCode());
        lo.setStartTime(stepStartTime);
        lo.setUserTime(DateDifferenceUtil.getTimeDifferenceValue(lo.getStartTime(), lo.getCzsj()));
        this.workDao.save(lo);
        if (log.isDebugEnabled()) {
            log.debug("保存日志成功：");
        }
        
        /**下派操作**/
        WorkDto workDto=new WorkDto(work);

        String xpfjIds=work.getXpfjIds();
        String xplspsIds=work.getXplspsIds();
        
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
            	if(null != file){
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
        }
        workDto.setPFileInfo(pFileInfo);
        
        //下派历史批示相关
        List<Map<String, String>> pLspsInfo=new ArrayList<Map<String,String>>();
        //获取历史批示信息
        if(StringUtils.isNotBlank(xplspsIds)){
        	String[] xplspsIdsArr=xplspsIds.split(",");
            for(int i=0;i<xplspsIdsArr.length;i++){
            	WorkLog workLog = (WorkLog)this.dao.get(WorkLog.class,xplspsIdsArr[i]);
            	if(null != workLog){
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
        
        workClient.createRwToArea(workDto, work.getXpCity(), "");
    }

    /**
     * 根据XML文件保存任务信息
     * @param xmlFile
     * @throws DocumentException
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    private Work saveWorkForXml(File xmlFile) throws DocumentException, ParseException {
        //解析XML
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xmlFile);
        Element root = doc.getRootElement();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Work work = new Work();
        if (null != root.selectSingleNode("sjid")) {
            work = this.workDao.get(root.selectSingleNode("sjid").getText());
        }
        //原任务编号（附件路径存在任务编号为名称的文件夹中，新任务需要替换）
        String oldWorkId = root.selectSingleNode("id") == null ? "" : root.selectSingleNode("id")
            .getText();
        work.setName(root.selectSingleNode("name") == null ? "" : root.selectSingleNode("name")
            .getText());
//        if (root.selectSingleNode("worktypeid") != null) {
//            WorkType workType = (WorkType) workDao.get(WorkType.class, root.selectSingleNode(
//                "worktypeid").getText());
//            work.setWorkType(workType);
//        }
        work.setWorkNote(root.selectSingleNode("note") == null ? "" : root.selectSingleNode("note")
            .getText());
        work.setStartTime(root.selectSingleNode("starttime") == null ? null : sdf.parse(root
            .selectSingleNode("starttime").getText()));
        work.setEndTime(root.selectSingleNode("endtime") == null ? null : sdf.parse(root
            .selectSingleNode("endtime").getText()));
//        work.setZfdxId(root.selectSingleNode("zfdxid") == null ? "" : root.selectSingleNode(
//            "zfdxid").getText());
//        work.setZfdxName(root.selectSingleNode("zfdxname") == null ? "" : root.selectSingleNode(
//            "zfdxname").getText());
        work.setPtrIds(root.selectSingleNode("ptrids") == null ? "" : root.selectSingleNode(
            "ptrids").getText());
        work.setPtrNames(root.selectSingleNode("ptrnames") == null ? "" : root.selectSingleNode(
            "ptrnames").getText());
        work.setZxrId(root.selectSingleNode("zxrid") == null ? "" : root.selectSingleNode("zxrid")
            .getText());
        work.setZxrName(root.selectSingleNode("zxrname") == null ? "" : root.selectSingleNode(
            "zxrname").getText());
    	if(null!=root.selectSingleNode("zxStartTime") &&!"".equals(root.selectSingleNode("zxStartTime").getText())){
    		 work.setZxStartTime(sdf.parse(root.selectSingleNode("zxStartTime").getText()));
    	}else{
    		work.setZxStartTime(null);
    	}
       
        work.setZxtime(root.selectSingleNode("zxtime") == null ? null : sdf.parse(root
            .selectSingleNode("zxtime").getText()));
        if (root.selectSingleNode("jlrid") != null) {
        	TSysUser jlr = (TSysUser) workDao.get(TSysUser.class, root.selectSingleNode("jlrid").getText());
            work.setJlr(jlr);
        }
        if (StringUtils.isBlank(work.getId())) {
            work.setSjid(root.selectSingleNode("sjid") == null ? "" : root.selectSingleNode("sjid")
                .getText());
        }
        //        work.setAreaid(root.selectSingleNode("areaid") == null ? "" : root.selectSingleNode(
        //            "areaid").getText());
        work.setIsxp(root.selectSingleNode("isxp") == null ? false : root.selectSingleNode("isxp")
            .getText().equals("true"));
        //        work.setSbr(root.selectSingleNode("sbr") == null ? "" : root.selectSingleNode("sbr")
        //            .getText());
        //        work.setSbsj(root.selectSingleNode("sbsj") == null ? null : sdf.parse(root
        //            .selectSingleNode("sbsj").getText()));

        //存在“上级编号”时，将上级的任务中审核人存入新建任务中
        //        if (StringUtils.isNotBlank(work.getSjid())) {
        //            Work sjWork = workDao.get(work.getSjid());
        //            work.setShrids(sjWork.getShrids());
        //        }
        //保存WORK
        workDao.save(work);
        log.debug("workId:".concat(work.getId()));
        //附件
        Node file = null;
        List<Node> nodes = null;
        //通用
        file = root.selectSingleNode("commfiles");
        if (file != null) {
            nodes = file.selectNodes("file");
            WorkCommFile workCommFile = null;
            for (int i = 0; i < nodes.size(); i++) {
                workCommFile = new WorkCommFile(oldWorkId, work, nodes.get(i));
                workDao.save(workCommFile);
            }
        }
        Node jcinfo = root.selectSingleNode("jcinfo");
        if (jcinfo != null) {
            workDao.remove(WorkJcinfo.class, work.getId());
            WorkJcinfo workJcinfo = new WorkJcinfo(oldWorkId, work, jcinfo);
            workDao.save(workJcinfo);
        }
        return work;
    }
    
    
    public void saveReportSh(String applyId, String taskId, Boolean passed,
			String opinion) throws Exception {
        TSysUser cur = CtxUtil.getCurUser();
        Date date = new Date();
        Work work = workDao.get(applyId);
        //将上次任务更新时间做为本此步骤的开始时间
        Date stepStartTime = work.getUpdateTime();
        //更新WORK的更新时间做为下一步步骤的开始时间
        work.setUpdateTime(date);
        work.setUpdateUser(cur);
        
        /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
        String[] shrIds = work.getShrids().split(",");
        StringBuffer shrIdsBuffer = new StringBuffer();
        for (int i = 0; i < shrIds.length - 1; i++) {
            shrIdsBuffer.append(shrIds[i]).append(",");
        }
        work.setShrids(shrIdsBuffer.toString());
        work.setThrids((work.getThrids() == null ? "" : work.getThrids())
                       + shrIds[shrIds.length - 1] + ",");
        /**将此任务的“审核人集合”中去掉此步审核人，将此审核人加入“退回人集合”中**/
        
        ProcessArgsBean bean = new ProcessArgsBean();
        bean.setApplyId(applyId);
        bean.setOpinion(opinion == null ? "" : opinion);
        
        
        if (!passed) {//上级审核
        	bean.setDirection(WorkTransferDirectionEnum.SH_SJ);
            bean.setResult(WorkTransferDirectionEnum.SH_SJ.getText());
            /***********下步操作**********/
            StringBuffer nextAction = new StringBuffer();
            nextAction.append(WorkProcessEnum.REPORT_SH.getCode()).append(",");
            work.setNextActions(nextAction.toString());
            work.setState(WorkState.YSH.getCode());
            /***********下步操作**********/
        } else {//审核通过(归档)
            bean.setDirection(WorkTransferDirectionEnum.SH_TG);
            bean.setResult(WorkTransferDirectionEnum.SH_TG.getText());
            work.setState(WorkState.YGD.getCode());
            work.setGdsj(date);
            work.setGdUser(cur);
            work.setIsOver(true);
        }
        
        processManager.saveNext(ProcessEnum.REPORT_TASK.getProcessKey(), taskId, work, bean);
        
        //批示意见
        work.setPsyj(opinion == null ? "" : opinion);
        
        this.workDao.save(work);
        
        WorkLogType opType = WorkLogType.SH;
        WorkState state = WorkState.YSH;
        if (passed) {
            opType = WorkLogType.GD;
            state = WorkState.YGD;
        }
        this.saveLog(cur, date, opType, state, work, stepStartTime);
        
    }

}
