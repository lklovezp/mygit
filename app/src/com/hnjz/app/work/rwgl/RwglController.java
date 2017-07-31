/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.rwgl;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkSh;
import com.hnjz.app.work.hjgl.HjglManager;
import com.hnjz.app.work.hjgl.Vhjgl;
import com.hnjz.app.work.manager.WorkReportManager;
import com.hnjz.app.work.manager.nodes.WorkFlowOperator;
import com.hnjz.app.work.manager.nodes.WorkPf;
import com.hnjz.app.work.manager.nodes.ZxNode;
import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.xfdj.XfdjManager;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.facade.domain.WorkDto;
import com.hnjz.quartz.JobType;
import com.hnjz.quartz.service.JobManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.role.RoleController;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.AbsJbpmController;

/**
 * �������Controller
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ���������������������ɷ����Ѱ����񡢴�������
 *
 */
@Controller
public class RwglController extends AbsJbpmController{

    /**��־*/
    private static final Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private RwglManager    rwglManager;
    
    @Autowired
	private CommWorkManager commWorkManager;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**�����ɷ�**/
    @Autowired
    private WorkPf           workPf;
    
    @Autowired
    private JobManager      jobManager;
    
    @Autowired
	private UserManager userManager;
    
    @Autowired
	private CommonManager commonManager;
    
    @Autowired
    private HjglManager      hjglManager;
    
    @Autowired
    protected ZxNode zxNode;
    
    @Autowired
    private IndexManager     indexManager;
    
	@Autowired
	private XfdjManager xfdjManager;
	
	 /** ���Ź��� */
    @Autowired
    private OrgManager              orgManager;
    
    @Autowired
    protected WorkDao             workDao;
    
    @Autowired
	private Dao dao;
    
    @Autowired
    private WorkFlowOperator     workflowoperator;
    
    @Autowired
    private WorkReportManager workReportManager;

    
    
/////////////////////////////////////////�����ɷ�ģ��//////////////////////////////////////////////
    /**
     * 
     * �������ܣ� ��ת���������ɷ��б�ҳ�档
     * �������:
     * ����ֵ��
     */
    @RequestMapping(value = "/rwpfList.htm")
    public String rwpfList(ModelMap model,String title) {
    	model.put("title", title);
    	return "app/work/rwgl/rwpfList";
    }

    /**
     * 
     * �������ܣ���ѯ�������ɷ��б���
     * ���������rwmc���������ƣ�rwly��������Դ��
     * ����ֵ��
     */
    @RequestMapping(value = "/getRwpfList.json", produces = "application/json")
    public void getRwpfList(ModelMap model, String rwmc,String rwly,String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRwpfList(rwmc,rwly, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    /**
     * 
     * �������ܣ���ѯ�������ɷ��б���
     * ���������rwmc���������ƣ�rwly��������Դ��
     * ����ֵ��
     */
    @RequestMapping(value = "/getRcbgRwpfList.json", produces = "application/json")
    public void getRcbgRwpfList(ModelMap model, String rwmc,String rwly,String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRcbgRwpfList(rwmc,rwly,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    /**
     * ���������ɷ�ҳ��
     * @param applyId   ���뵥���;isCp �ǣ�1��������
     * @param model
     */
    @RequestMapping(value = "/pfPage.htm")
    public String pfPage(String applyId,String isCp,ModelMap model,String rwlxType,String lx) {
    	try {
        	Work work = null;
        	if(StringUtil.isNotBlank(rwlxType)){
        		model.put("rwlxType", rwlxType);
        	}
        	model.addAttribute("lx", lx);
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			//Ĭ��ֵ
        			work.setDjrId(CtxUtil.getCurUser().getId());
        			work.setDjrName(CtxUtil.getCurUser().getName());
        		}
    			if(StringUtils.isNotBlank(work.getJsr())){
        			TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getJsr());
        			work.setJsrNames(jsrObj.getName());
        		}
    			if(StringUtils.isNotBlank(work.getJsrIds())){
        			String [] jsrIds = work.getJsrIds().split(",");
        			String jsrNames = "";
        			for(String jsr : jsrIds){
        				TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, jsr);
        				jsrNames = jsrNames+jsrObj.getName() + ",";
        			}
        			jsrNames = jsrNames.substring(0,jsrNames.length()-1);
        			work.setJsrNames(jsrNames);
        		}
        		
        		//��ȡִ����������
        		String zfdxmcs = "";
        		List<Map<String, String>> listMap = commWorkManager
    					.zfdxTableData(applyId);
        		for(int i = 0; i < listMap.size(); i++){
        			if(i<listMap.size()-1){
        				zfdxmcs += listMap.get(i).get("lawobjname")+',';
        			}else{
        				zfdxmcs += listMap.get(i).get("lawobjname");
        			}
        		}
        		work.setZfdxmc(zfdxmcs);
        		if(listMap.size() > 0){
        			String rowString = "";
            		for(int i=0;i<listMap.size();i++){
            			rowString += "{'lawobjid':'"+listMap.get(i).get("lawobjid")+"'";
            			rowString += ",'lawobjname':'"+listMap.get(i).get("lawobjname")+"'";
            			rowString += ",'fddbr':'"+listMap.get(i).get("manager")+"'";
            			rowString += ",'id':'"+listMap.get(i).get("id")+"'";
            			rowString += ",'fddbrlxdh':'"+listMap.get(i).get("managermobile")+"'";
            			rowString += ",'hbfzr':'"+listMap.get(i).get("bjcr")+"'";
            			rowString += ",'hbfzrlxdh':'"+listMap.get(i).get("lxdh")+"'";
            			rowString += ",'regionid':'"+listMap.get(i).get("region")+"'";
            			rowString += ",'address':'"+listMap.get(i).get("address")+"'}";
            			if(i!=listMap.size()-1){
            				rowString += ",";
            			}
            		}
        			model.addAttribute("mcs", '['+rowString+']');//�ڱ������뵽�ɷ������ִ����������ݶ�������
        		}
        		
        		// ���������б�
    			List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(applyId);
    			model.put("rwlxlistMap", rwlxlistMap);
    			String rwlxIds = "";
    			String rwlxNames = "";
    			for (int i = 0; i < rwlxlistMap.size(); i++) {
    				if (i < rwlxlistMap.size() - 1) {
    					rwlxIds += rwlxlistMap.get(i).get("id") + ",";
    					rwlxNames += rwlxlistMap.get(i).get("name") + ",";
    				} else {
    					rwlxIds += rwlxlistMap.get(i).get("id");
    					rwlxNames += rwlxlistMap.get(i).get("name");
    				}
    			}
    			if(StringUtils.isNotBlank(work.getRwmcgs())){
    				work.setRwmcgs(work.getRwmcgs());
    			}else{
    				work.setRwmcgs("0");
    			}
    			model.put("rwlxIds", rwlxIds);
    			if(StringUtil.isNotBlank(rwlxType)){
    				model.put("rwlxType", rwlxType);
    			}else{
    				model.put("rwlxType", rwlxIds);
    			}
    			model.put("rwlxNames", rwlxNames);
    			
    			//����ŷõǼǱ�
    			if(StringUtils.isNotBlank(work.getXfdjbId())){
    				TBizXfdj xfdjb = (TBizXfdj) xfdjManager.get(TBizXfdj.class, work.getXfdjbId());
    				if(xfdjb!=null){
    					model.put("xfbh", xfdjb.getXfbh());
    				}
    			}
    			//�ŷñ���������
    			if(StringUtils.isNotBlank(work.getXfbcjsrId())){
        			TSysUser xfdjbcrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getXfbcjsrId());
        			work.setXfbcjsrName(xfdjbcrObj.getName());
        		}
        	}else{//����
        		work = new Work();
        		work.setCreateTime(null);//Ĭ�ϴ���ʱ��Ϊ��
        		Calendar endC = Calendar.getInstance();
        		//������������Ĭ��ֵ
        		work.setRwmcrq(endC.getTime());
                endC.add(Calendar.DATE, 20);//Ĭ�Ͻ����̶�һ��20��
        		work.setEndTime(endC.getTime());
        		
        		//Ĭ��ֵ
    			work.setDjrId(CtxUtil.getCurUser().getId());
    			work.setDjrName(CtxUtil.getCurUser().getName());
    			work.setRwmcgs("0");//Ĭ�����Ƹ�ʽΪ���
        	}
        	
        	////Ĭ��ֵ///
        	if(StringUtils.isBlank(work.getSource())){
        		work.setSource("8");//�ֳ����
        	}
        	if(StringUtils.isBlank(work.getSecurity())){
        		work.setSecurity("1");//������
        	}
        	if(StringUtils.isBlank(work.getEmergency())){
        		work.setEmergency("1");//һ��
        	}
        	if(StringUtils.isBlank(work.getSfdb())){
        		work.setSfdb(YnEnum.N.getCode());//�ŷõǼ������Ƿ���죺Ĭ�Ϸ�
        	}
        	if(StringUtils.isBlank(work.getBlrsfbc())){
        		work.setBlrsfbc(YnEnum.Y.getCode());//�ŷõǼ������Ƿ���Ҫ������Ĭ����
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        	
        	/*TSysOrg p = orgManager.getOrgByUserid(CtxUtil.getCurUser().getId());
            if(p.getId().equals("a0000000000000000000000000000000")){
            	model.addAttribute("isXp", "1");
            }*/
        	
        	if(StringUtil.isNotBlank(isCp)){//�Ƿ�����
        		model.addAttribute("isCp", "1");
        		//�����衰�����ˡ�Ϊ��
        		work.setJsrIds("");
        		work.setJsrNames("");
        		
        		Calendar endC = Calendar.getInstance();
        		work.setRwmcrq(endC.getTime());
        		//��ȡ�ɷ����ɷ�ʱ����ʾ���
        		work.setPsyj(rwglManager.getPsyj(work.getId()));
        	}else{
        		model.addAttribute("isCp", "0");
        	}
        	model.addAttribute("taskId", work.getTaskId());
        } catch (Exception e) {
            log.error("", e);
        }
    	return "app/work/pf/pfPage";
    }
    /**
     * ���������ɷ�ҳ��
     * @param applyId   ���뵥���;isCp �ǣ�1��������
     * @param model
     */
    @RequestMapping(value = "/rcbgpfPage.htm")
    public String rcbgpfPage(String title,String isCp,ModelMap model,String rwlxType,String lx) {
    	model.put("title", title);
    	return "app/work/rwgl/rcbgpfList";
    }
    /**
     * ���������ɷ�ҳ��
     * @param model
     */
    @RequestMapping(value = "/plpfPage.htm")
    public String plpfPage(ModelMap model) {
        return "app/work/pf/plpfPage";
    }
    
    /**
     * ���������ɷ��鿴ҳ��
     * @param applyId   ���뵥���
     * @param model
     */
    @RequestMapping(value = "/pfPageInfo.htm")
    public String pfPageInfo(String applyId,ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//������
        		if(StringUtils.isNotBlank(work.getJsr())){
        			TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getJsr());
        			work.setJsrNames(jsrObj.getName());
        		}
        		if(StringUtils.isNotBlank(work.getJsrIds())){
        			String [] jsrIds = work.getJsrIds().split(",");
        			String jsrNames = "";
        			for(String jsr : jsrIds){
        				TSysUser jsrObj = (TSysUser) this.userManager.get(TSysUser.class, jsr);
        				jsrNames = jsrNames+jsrObj.getName() + ",";
        			}
        			jsrNames = jsrNames.substring(0,jsrNames.length()-1);
        			work.setJsrNames(jsrNames);
        		}
        		
        		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
        		if(work.getSource()!=null){
        			String str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
            		//work.setSource(str1);
        			model.addAttribute("source", str1);
        		}
        		//�����ܼ�����
        		if(work.getSecurity()!=null){
        			String str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
            		//work.setSecurity(str2);
        			model.addAttribute("security", str2);
        		}
        		//�����̶�����
        		if(work.getEmergency()!=null){
        			String str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
            		//work.setEmergency(str3);
        			model.addAttribute("emergency", str3);
        		}
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/pfPageInfo";
    }
    
    /**
     * ��������
     * @param model
     */
    @RequestMapping(value = "/sc.json", produces = "application/json")
    public void sc(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds,String zfdxmcs, String lx) {
        try {
        	Work w=workPf.savePfInfo(frm, frm.getJsrIds());
        	String applyId = w.getId();//����ID
        	TSysUser curUser = CtxUtil.getCurUser();
        	if("Y".equals(frm.getRcbg())){//�ճ��칫
    			commWorkManager.saveTaskTypeMultiple(w.getId(), TaskTypeCode.RCBG.getCode(), curUser);
    		}else if("Y".equals(frm.getXfts())){//�ŷ�Ͷ��
    			commWorkManager.saveTaskTypeMultiple(w.getId(), TaskTypeCode.XFTS.getCode(), curUser);
    		}else{
    			commWorkManager.saveDelRWLX(w.getId());
    		}
        	if(("0").equals(frm.getRwmcgs())){
        		if(StringUtils.isNotBlank(applyId)){
            		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
            		if(StringUtils.isNotBlank(w.getZfdxType())){
            			commWorkManager.saveZfdxTypeOnChange(applyId, w.getZfdxType(),rwlxIds);
            			commWorkManager.saveZfdxType(applyId, w.getZfdxType());
            		}
            		if(StringUtils.isNotBlank(checkedNodesIds)){
            			commWorkManager.saveTaskTypeMultiple(applyId, checkedNodesIds, curUser);
            		}
        			if(StringUtils.isNotBlank(zfdxmcs)){
        				JSONArray array = new JSONArray(zfdxmcs);
                		commonManager.saveChoseeLawobj(applyId, w.getZfdxType(), array, curUser);
        			}
            	}
        	}
        	model.put("id", w.getId());
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * ������������
     * @param model
     */
    @RequestMapping(value = "/rwyq.json", produces = "application/json")
    public void rwyq(ModelMap model, WorkDto frm) {
        try {
        	Work work = workDao.get(frm.getId());
        	Work xpWork = rwglManager.getXpWork(work.getId());//�������������
        	Date end = DateUtil.getEndDatetime(frm.getEndTime());
            work.setEndTime(end);//Ҫ�����ʱ��
            if(xpWork!=null){
            	xpWork.setEndTime(end);
            	rwglManager.save(xpWork);
            }
            rwglManager.save(work);
            JsonResultUtil.suncess(model, "����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * �����ɷ�-ɾ��
     * @param model
     */
    @RequestMapping(value = "/delpf.json", produces = "application/json")
    public void delpf(String applyId,ModelMap model) {
    	try {
    		rwglManager.saveDelwork(applyId);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ���˵���Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }

    /**
     * �����ɷ�����
     * @param model
     */
    @RequestMapping(value = "/pf.json", produces = "application/json")
    public void pf(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds,String zfdxmcs, String lx) {
        try {
        	TSysUser curUser = CtxUtil.getCurUser();
            if (log.isDebugEnabled()) {
                log.debug("�ɷ�������" + LogUtil.m(frm));
            }
            if (frm.getWorkName().contains("\\") || frm.getWorkName().contains("/")
    				|| frm.getWorkName().contains(":") || frm.getWorkName().contains("*")
    				|| frm.getWorkName().contains("?") || frm.getWorkName().contains("\"")
    				|| frm.getWorkName().contains("<") || frm.getWorkName().contains(">")
    				|| frm.getWorkName().contains("|")){
    			throw new Exception("�������Ʋ��ܰ�����\\/:*?\"<>|���Ƿ��ַ���");
    		}
            if (StringUtils.equals(frm.getXptype(), "ds")) {
                workPf.savePfXpds(frm, areaId);
            } else {
            	if(StringUtils.isNotBlank(frm.getJsrIds())){
            		String [] jsrIds = frm.getJsrIds().split(",");
            		if(jsrIds.length>0){
            			Work w1 = workPf.savePf(frm, jsrIds[0], CtxUtil.getCurUser(), lx);
            			//String taskid = w1.getProcessId();
            			//String[] taskId = taskid.split("\\.");
            			//int a = Integer.parseInt(taskId[1]);
            			//int b = a + 8;
            			if("Y".equals(frm.getRcbg())){//�ճ��칫
                			commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.RCBG.getCode(), curUser);
                		}else if("Y".equals(frm.getXfts())){//�ŷ�Ͷ��
                			commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
                		}else {
                			commWorkManager.saveDelRWLX(w1.getId());
                		}
            			if(("0").equals(frm.getRwmcgs())){
                			if(StringUtils.isNotBlank(w1.getId())){
                        		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
                        		if(StringUtils.isNotBlank(w1.getZfdxType())){
                        			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),rwlxIds);
                        			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
                        		}
                        		
                        		if(StringUtils.isNotBlank(checkedNodesIds)){
                        			commWorkManager.saveTaskTypeMultiple(w1.getId(), checkedNodesIds, curUser);
                        		}
                    			if(StringUtils.isNotBlank(zfdxmcs)){
                    				JSONArray array = new JSONArray(zfdxmcs);
                            		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
                    			}
                        	}
                		}
                		for(int i=1;i<jsrIds.length;i++){
                			frm.setId("");
                			Work w2 = workPf.savePf(frm, jsrIds[i], CtxUtil.getCurUser(), lx);
                			//�ɷ�����
                			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
                			if(pfFileList!=null && pfFileList.size()>0){
                				for(int k=0;k<pfFileList.size();k++){
                					TDataFile t= new TDataFile(w2.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
                					commonManager.saveFile(t);
                    			}
                			}
                			if("Y".equals(frm.getRcbg())){//�ճ��칫
                    			commWorkManager.saveTaskTypeMultiple(w2.getId(), TaskTypeCode.RCBG.getCode(), curUser);
                    		}else if("Y".equals(frm.getXfts())){//�ŷ�Ͷ��
                    			commWorkManager.saveTaskTypeMultiple(w2.getId(), TaskTypeCode.XFTS.getCode(), curUser);
                    		}else{
                    			commWorkManager.saveDelRWLX(w2.getId());
                    		}
                			if(("0").equals(frm.getRwmcgs())){
                    			if(StringUtils.isNotBlank(w2.getId())){
                            		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
                            		if(StringUtils.isNotBlank(w1.getZfdxType())){
                            			commWorkManager.saveZfdxTypeOnChange(w2.getId(), w2.getZfdxType(),rwlxIds);
                            			commWorkManager.saveZfdxType(w2.getId(), w2.getZfdxType());
                            		}
                            		
                            		if(StringUtils.isNotBlank(checkedNodesIds)){
                            			commWorkManager.saveTaskTypeMultiple(w2.getId(), checkedNodesIds, curUser);
                            		}
                        			if(StringUtils.isNotBlank(zfdxmcs)){
                        				JSONArray array = new JSONArray(zfdxmcs);
                                		commonManager.saveChoseeLawobj(w2.getId(), w2.getZfdxType(), array, curUser);
                        			}
                            	}
                    		}
                		}
                		model.put("id", w1.getId());
                		//model.put("taskId", String.valueOf(b));
            		}
            	}
            }
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }
    /**
     * �����ɷ�����
     * @param model
     */
    @RequestMapping(value = "/plpf.json", produces = "application/json")
    public void pf(ModelMap model, String jsrId, String rows) {
        try {
        	JSONArray array = new JSONArray(rows);
        	workPf.savePlpf(jsrId , array);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }
    
    
    
////////////////////////////////////////////////////��������ģ��////////////////////////////////////////////////////  
    /**
     * 
     * �������ܣ���ת����ִ����������б�ҳ�棨�������죬���ڰ������Ѱ�����ִ��������񣩡�
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/dbrwList.htm")
    public String dbrwList(ModelMap model,String title, String xfdjId, String lx) {
    	model.put("title", title);
    	model.put("lx", lx);
    	/*FyWebResult re = null;
    	try {
			re = rwglManager.getDbrwList("","","", "", "", "", "", "", "", "", "1", "20");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	int a = rwglManager.getDbrwCount(CtxUtil.getCurUser());
    	model.put("total", a);
		//ִ������б�ҳ��
		return "app/work/rwgl/newzfjcrwList";
    }
    
    /**
     * 
     * �������ܣ���ѯ�����������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getDbrwList.json", produces = "application/json")
    public void getDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String lx, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, xfdjId, lx, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * 
     * �������ܣ���ת�����ֳ���������б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/xcjcrwList.htm")
    public String xcjcrwList(ModelMap model,String title) {
    	model.put("title", title);
    	FyWebResult re = null;
    	try {
			re = rwglManager.getXcjcrwList("","","", "", "", "", "", "", "", "", "1", "20");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//int a = rwglManager.getDbrwCount(CtxUtil.getCurUser());
    	if(null == re){
    		model.put("total", "0");
    	}else{
    		model.put("total", re.getTotal());
    	}
        return "app/work/rwgl/xcjcrwList";
    }
    
    /**
     * 
     * �������ܣ���ѯ���ֳ���������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getXcjcrwList.json", produces = "application/json")
    public void getXcjcrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String page, String pageSize) {
        try {
        	//���������жϱ�ʶ�����з����ѯ�����ʱ�����0,1�Ĵ�ֵ��
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXcjcrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * 
     * �������ܣ���ת�����ŷ�Ͷ�������б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/xftsdbRwList.htm")
    public String xftsdbRwList(ModelMap model,String title) {
    	model.put("title", title);
    	FyWebResult re = null;
    	try {
			re = rwglManager.getXftsrwList("","","", "", "", "", "", "", "", "", "", "1", "20");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//int a = rwglManager.getDbrwCount(CtxUtil.getCurUser());
    	if(null == re){
    		model.put("total", "0");
    	}else{
    		model.put("total", re.getTotal());
    	}
        return "app/work/rwgl/newxftsrwList";
    }
    /**
     * 
     * �������ܣ���ת�����ճ��칫�����б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/rcbgdbRwList.htm")
    public String rcbgdbRwList(ModelMap model,String title) {
    	model.put("title", title);
    	FyWebResult re = null;
    	try {
			re = rwglManager.getXftsrwList("","","", "", "", "", "", "", "", "", "", "1", "20");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//int a = rwglManager.getDbrwCount(CtxUtil.getCurUser());
    	if(null == re){
    		model.put("total", "0");
    	}else{
    		model.put("total", re.getTotal());
    	}
        return "app/work/rwgl/newrcbgrwList";
    }
    
    /**
     * 
     * �������ܣ���ѯ���ֳ���������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getXftsrwList.json", produces = "application/json")
    public void getXftsrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String xfdjbId,String page, String pageSize) {
        try {
        	//���������жϱ�ʶ�����з����ѯ�����ʱ�����0,1�Ĵ�ֵ��
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXftsrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, xfdjbId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    /**
     * 
     * �������ܣ��ճ��칫�����б�
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getRcbgrwList.json", produces = "application/json")
    public void getRcbgrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String xfdjbId,String page, String pageSize) {
        try {
        	//���������жϱ�ʶ�����з����ѯ�����ʱ�����0,1�Ĵ�ֵ��
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRcbgrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, xfdjbId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    /**
     * �������ܣ���ҳ��ѯ��������ǰ6��
     */
    @RequestMapping(value = "/dbQuery.json", produces = "application/json")
    public void dbQuery(ModelMap model) {
        try {
            String dbString = "";
            dbString = rwglManager.dbQuery();
            model.addAttribute("arr", dbString);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * ת��ҳ��
     * @param applyId   ���뵥���
     * @param taskId    JBPM������
     * @param model
     */
    @RequestMapping(value = "/zpPage.htm")
    public String zpPage(String applyId, String taskId,String lx,String sy, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            TSysUser cur = CtxUtil.getCurUser();
            TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
    		//List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
    		//List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
    		/*if(xfzydata.size() != 0){
    			model.put("isXfzy", "0");
    		}
    		if(rczydata.size() != 0){
    			model.put("isRczy", "0");
    		}*/
    		if(curOrg != null){
    			if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
    				model.put("isZd", "0");
    			}
    		}
    		
    		List<Map<String, String>> rwlxlistMap = commWorkManager
					.getTaskTypeByTaskId(applyId);
			model.put("rwlxlistMap", rwlxlistMap);
			String rwlxType = "";
			for (int i = 0; i < rwlxlistMap.size(); i++) {
				if (i < rwlxlistMap.size() - 1) {
					rwlxType += rwlxlistMap.get(i).get("id") + ",";
				} else {
					rwlxType += rwlxlistMap.get(i).get("id");
				}
			}
			model.put("rwlxType", rwlxType);
            
            //�Ǽ���
            if(StringUtils.isNotBlank(work.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
    			work.setDjrName(djrObj.getName());
    		}else{
    			work.setDjrName("");
    		}
    		
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		if(work.getSource()!=null){
    			String str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
        		//work.setSource(str1);
    			model.addAttribute("source", str1);
    		}
    		//�����ܼ�����
    		if(work.getSecurity()!=null){
    			String str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
        		//work.setSecurity(str2);
    			model.addAttribute("security", str2);
    		}
    		//�����̶�����
    		if(work.getEmergency()!=null){
    			String str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
        		//work.setEmergency(str3);
    			model.addAttribute("emergency", str3);
    		}
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            model.addAttribute("lx", lx);
            model.addAttribute("sy", sy);
            
            ////////���������¼����ʷ��ʾ��///////
            List<Vhjgl> lsps = hjglManager.getRwhj(applyId);
            model.addAttribute("lsps", lsps);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/zpPage";
    }

    /**
     * ����ת��
     * @param model
     */
    @RequestMapping(value = "/zp.json", produces = "application/json")
    public void zp(String applyId, String taskId, String jsrId,String psyj, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
        	workManager.saveZp(applyId, taskId, jsrId, psyj, cur);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }

    /**
     * ��������
     * @param model
     */
    @RequestMapping(value = "/cp.json", produces = "application/json")
    public void cp(ModelMap model, WorkDto frm, String areaId, String checkedNodesIds,String rwlxIds,String zfdxmcs) {
        try {
        	if(StringUtils.isNotBlank(frm.getJsrIds())){
        		String [] jsrIds = frm.getJsrIds().split(",");
        		if(jsrIds!=null && jsrIds.length>0){
        			TSysUser curUser = CtxUtil.getCurUser();
        			Work w1 = workManager.saveCp(frm, jsrIds[0], CtxUtil.getCurUser());
        			//��������
        			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(frm.getId());
        			String rwlxids = "";
        			for (int i = 0; i < rwlxlistMap.size(); i++) {
        				if (i < rwlxlistMap.size() - 1) {
        					rwlxids += rwlxlistMap.get(i).get("id") + ",";
        				} else {
        					rwlxids += rwlxlistMap.get(i).get("id");
        				}
        			}
        			if("Y".equals(frm.getRcbg()) && !"24".equals(rwlxids)){//�ճ��칫
            			commWorkManager.saveTaskTypeMultiple(frm.getId(), TaskTypeCode.RCBG.getCode(), curUser);
            		}
            		if("Y".equals(frm.getXfts())&& !"13".equals(rwlxids)){//�ŷ�Ͷ��
            			commWorkManager.saveTaskTypeMultiple(frm.getId(), TaskTypeCode.XFTS.getCode(), curUser);
            		}
        			if(("0").equals(frm.getRwmcgs())){
                		if(StringUtils.isNotBlank(frm.getId())){
                    		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
                    		if(StringUtils.isNotBlank(frm.getZfdxType())){
                    			Work work = (Work) commWorkManager.get(Work.class, frm.getId());
                    			if(work!=null && !StringUtils.equals(work.getZfdxType(), frm.getZfdxType())){
                    				commWorkManager.saveZfdxTypeOnChange(frm.getId(), frm.getZfdxType(),rwlxIds);
                        			commWorkManager.saveZfdxType(frm.getId(), frm.getZfdxType());
                    			}
                    		}
                    		if(StringUtils.isNotBlank(checkedNodesIds)){
                    			if(StringUtils.isBlank(rwlxids) || !StringUtils.equals(rwlxids, checkedNodesIds)){
                    				commWorkManager.saveTaskTypeMultiple(frm.getId(), checkedNodesIds, curUser);
                    			}
                    		}
                    		String zfdx="";
                    		List<Map<String, String>> listMap = commWorkManager
                					.zfdxTableData(frm.getId());
                    		for(int i = 0; i < listMap.size(); i++){
                    			if(i<listMap.size()-1){
                    				zfdx += listMap.get(i).get("lawobjname")+',';
                    			}else{
                    				zfdx += listMap.get(i).get("lawobjname");
                    			}
                    		}
                			if(StringUtils.isNotBlank(zfdxmcs) && StringUtils.equals(zfdxmcs, zfdx)){
                				JSONArray array = new JSONArray(zfdxmcs);
                				commonManager.saveChoseeLawobj(frm.getId(), frm.getZfdxType(),array, curUser);
                			}
                    	}
                	}else{
                		// �������
                		// 1��ִ������
                		commWorkManager.saveDelZFDX(frm.getId());
                		// 2����������
                		if(!"13".equals(rwlxids) && !"24".equals(rwlxids)){
                			commWorkManager.saveDelRWLX(frm.getId());
                		}
                		// 3������ҳ���Լ�������
                		commWorkManager.saveDelBL(frm.getId());
                		// 4��ִ����������
                		commWorkManager.saveDelZFDXLX(frm.getId());
                	}
            		for(int i=1;i<jsrIds.length;i++){
            			frm.setId("");
            			Work w2 = workManager.saveCp(frm, jsrIds[i], CtxUtil.getCurUser());
            			//�ɷ�����
            			List<TDataFile> pfFileList= commonManager.queryFileList(w1.getId(), "RWGLPFFJ");
            			if(pfFileList!=null && pfFileList.size()>0){
            				for(int k=0;k<pfFileList.size();k++){
            					TDataFile t= new TDataFile(w2.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
            					commonManager.saveFile(t);
                			}
            			}
            			if(("0").equals(frm.getRwmcgs())){
                			if(StringUtils.isNotBlank(w2.getId())){
                        		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
                        		if(StringUtils.isNotBlank(w1.getZfdxType())){
                        			commWorkManager.saveZfdxTypeOnChange(w2.getId(), w2.getZfdxType(),rwlxIds);
                        			commWorkManager.saveZfdxType(w2.getId(), w2.getZfdxType());
                        		}
                        		
                        		if(StringUtils.isNotBlank(checkedNodesIds)){
                        			commWorkManager.saveTaskTypeMultiple(w2.getId(), checkedNodesIds, curUser);
                        		}
                    			if(StringUtils.isNotBlank(zfdxmcs)){
                    				JSONArray array = new JSONArray(zfdxmcs);
                            		commonManager.saveChoseeLawobj(w2.getId(), w2.getZfdxType(), array, curUser);
                    			}
                        	}
                		}
                		if("Y".equals(frm.getRcbg())){//�ճ��칫
                			commWorkManager.saveTaskTypeMultiple(w2.getId(), TaskTypeCode.RCBG.getCode(), curUser);
                		}
                		if("Y".equals(frm.getXfts())){//�ŷ�Ͷ��
                			commWorkManager.saveTaskTypeMultiple(w2.getId(), TaskTypeCode.XFTS.getCode(), curUser);
                		}
            		}
        		}
        	}
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * ����ҳ��
     * @param applyId   ���뵥���
     * @param taskId    JBPM������
     * @param model
     */
    @RequestMapping(value = "/xpPage.htm")
    public String xpPage(String applyId, String taskId, String rcbg,String xfts,String sy,String lx,ModelMap model) {
        try {
            Work work = workManager.get(applyId);
           /* if(StringUtil.isBlank(taskId)){
            	taskId = work.getProcessId().split(".")[1];
            }*/
            TSysUser cur = CtxUtil.getCurUser();
    		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
    		//List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
    		//List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
    		/*if(xfzydata.size() != 0){
    			model.put("isXfzy", "0");
    		}
    		if(rczydata.size() != 0){
    			model.put("isRczy", "0");
    		}*/
    		if(curOrg != null){
    			if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
    				model.put("isZd", "0");
    			}
    		}
            //�����ŷ�������ж�
            /*if(StringUtils.isNotBlank(work.getId())){
            	// ���������б�
    			List<Map<String, String>> rwlxlistMap = commWorkManager
    					.getTaskTypeByTaskId(work.getId());
    			String rwlxIds = "";
    			for (int i = 0; i < rwlxlistMap.size(); i++) {
    				if (i < rwlxlistMap.size() - 1) {
    					rwlxIds += rwlxlistMap.get(i).get("id") + ",";
    				} else {
    					rwlxIds += rwlxlistMap.get(i).get("id");
    				}
    			}
    			if("24".equals(rwlxIds)){
    				model.addAttribute("isRcrw", "0");
    			}
    			//�����ŷ�������ж�
                if("13".equals(rwlxIds)){
                	model.addAttribute("isXfrw", "0");
                }
            }*/
            if(StringUtils.isNotBlank(work.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
    			work.setDjrName(djrObj.getName());
    		}else{
    			work.setDjrName("");
    		}
    		
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		if(work.getSource()!=null){
    			String str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
        		//work.setSource(str1);
        		model.addAttribute("source", str1);
    		}
    		//�����ܼ�����
    		if(work.getSecurity()!=null){
    			String str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
        		//work.setSecurity(str2);
        		model.addAttribute("security", str2);
    		}
    		//�����̶�����
    		if(work.getEmergency()!=null){
    			String str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
        		//work.setEmergency(str3);
        		model.addAttribute("emergency", str3);
    		}
    		
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            model.addAttribute("rcbg", rcbg);
            model.addAttribute("xfts", xfts);
            model.addAttribute("sy", sy);
            model.addAttribute("lx", lx);
            ////////���������¼����ʷ��ʾ��///////
            List<Vhjgl> lsps = hjglManager.getRwhj(applyId);
            model.addAttribute("lsps", lsps);

        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/xpPage";
    }
    
    /**
     * ��������
     * @param model
     */
    @RequestMapping(value = "/xp.json", produces = "application/json")
    public void xp(String applyId, String taskId, String areaId,String psyj,String xpfjIds,String xplspsIds, String jsrId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
    		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
            if (StringUtils.isNotBlank(areaId)) {
                workManager.saveZpXpds(applyId, taskId, areaId,psyj,xpfjIds,xplspsIds,"");
            }/*else{
            	if(StringUtils.isNotBlank(jsrId)){
    				TSysUser xfzy = userManager.getUser(jsrId);
    				//�Ƚ���������ɷ������������̣�Ȼ��ִ�����ɵĲ���
    				//workManager.saveZpXpds(applyId, taskId, xfzy.getAreaId(),psyj,xpfjIds,xplspsIds);
    				//�����Ƚ������������ɵ��������ˣ�Ȼ����ִ��ת�ɸ�ѡ��Ľ����ˣ������������������������ӣ�
    				//Work work = workManager.getXpRw(applyId);
    				//workManager.saveZp(work.getId(), taskId, jsrId, psyj, cur);
    	    		if(StringUtils.isNotBlank(applyId)){
	        			if(StringUtils.isNotBlank(xfzy.getAreaId())){
	        				Work w1 = workManager.get(applyId);
	    					String taskIds = xfdjManager.gettaskId(w1.getProcessId());
	    					if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
	    						//�Ȱ����������ɣ������������������
	    						workManager.saveZpXpds(w1.getId(), taskIds, xfzy.getAreaId(),w1.getPsyj(),xpfjIds,xplspsIds,jsrId);
	    						//����������ĵ�������
	    						Work w2 = workManager.getXpRw(applyId);
	    						String taskIdss = xfdjManager.gettaskId(w2.getProcessId());
	    						//��ȡ���ɵ��е�֧�ӳ�
	    						//String zdz = w2.getJsr();
	    						TSysUser pfr = userManager.getLeaderByAreaId(xfzy.getAreaId());
	    						//ͨ��������ת�ɸ��ܶ�ѡ��רԱ
	    						workManager.saveZp(w2.getId(), taskIdss, jsrId, w1.getPsyj(), pfr);
	                    	}else{
	                    		//רԱͨ�����ɵ�ҳ��ʵ��ͬ�����ת��
	                        	workManager.saveZp(w1.getId(), taskIds, jsrId, w1.getPsyj(), cur);
	                        }
	    				}
    	            }
            	}else{
            		if(curOrg != null){
                    	if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
                    		workManager.saveZpXpds(applyId, taskId, areaId,psyj,xpfjIds,xplspsIds,"");
                    	}else{
                        	workManager.saveZp(applyId, taskId, jsrId, psyj, cur);
                        }
                    }else{
                    	workManager.saveZpXpds(applyId, taskId, areaId,psyj,xpfjIds,xplspsIds,"");
                    }
            	}
            }*/
            JsonResultUtil.suncess(model, "���ɳɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }

    /**
     * ����ҳ��
     * @param applyId   ���뵥���
     * @param taskId    JBPM������
     * @param model
     */
    @RequestMapping(value = "/jsPage.htm")
    public String jsPage(String applyId, String taskId, String lx,String sy, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            //�Ǽ���
            if(StringUtils.isNotBlank(work.getDjrId())){
    			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
    			work.setDjrName(djrObj.getName());
    		}else{
    			work.setDjrName("");
    		}
    		
    		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
    		if(work.getSource()!=null){
    			String str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
        		//work.setSource(str1);
        		model.addAttribute("source", str1);
    		}
    		//�����ܼ�����
    		if(work.getSecurity()!=null){
    			String str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
        		//work.setSecurity(str2);
        		model.addAttribute("security", str2);
    		}
    		//�����̶�����
    		if(work.getEmergency()!=null){
    			String str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
        		//work.setEmergency(str3);
        		model.addAttribute("emergency", str3);
    		}
            
            model.addAttribute("work", work);
            model.addAttribute("applyId", applyId);
            model.addAttribute("taskId", taskId);
            model.addAttribute("lx", lx);
            model.addAttribute("sy", sy);
            ////////���������¼����ʷ��ʾ��///////
            List<Vhjgl> lsps = hjglManager.getRwhj(applyId);
            model.addAttribute("lsps", lsps);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/jsPage";
    }

    /**
     * �������
     * @param model
     */
    @RequestMapping(value = "/js.json", produces = "application/json")
    public void js(String applyId, String taskId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveJs(applyId, taskId, cur);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * �������
     * @param model
     */
  /*  @RequestMapping(value = "/bl.json", produces = "application/json")
    public void bl(String applyId, ModelMap model) {
        try {
            if(StringUtils.isNotBlank(applyId)){
            	List<Map<String, String>> zfdxList = commWorkManager.zfdxTableData(applyId);//ִ������
            	if(zfdxList!=null && zfdxList.size()>0){
            		String lawobjid = zfdxList.get(0).get("lawobjid");
            		if(StringUtils.isNotBlank(lawobjid)){
            			TDataLawobj lawobj = (TDataLawobj) lawobjManager.get(TDataLawobj.class, lawobjid);
            			if(lawobj!=null){
            				String sczt = lawobjManager.getScztByLawobj(lawobj);//��ҵ����״̬
            				//�õ���ǰ��
                			Date currTime = new Date();
                    		int month = currTime.getMonth()+1;//��
                    		String mon = String.valueOf(month);
                    		if(month !=10 && month !=11 && month !=12){
                    			mon = "0"+String.valueOf(month);
                    		}
                    		if(StringUtils.isNotBlank(sczt) && sczt.indexOf("N") != -1 && !sczt.contains(mon)){//���������������ڱ�������
                    			JsonResultUtil.fail(model, "�ó�����Ϊ"+ZfdxLx.getByCode(lawobj.getLawobjtype()).getText()+"������������������ǰδ�����������ƺ��飡");
                    			return ;
                    		}
            			}
            		}
            	}
            }
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    */
    
    /**
     * ִ�У�������ҳ��
     * @param applyId   ���뵥���
     * @param taskId    JBPM������
     * @param model
     */
    @RequestMapping(value = "/zxPage.htm")
    public String zxPage(String applyId, String taskId,String lx,String sy, ModelMap model) {
        try {
        	//�������߰��жϱ�ʶ���������ͱ���ҳ����жϣ�
        	String biaoshi = indexManager.sysVer;
            Work work = workManager.get(applyId);
            rwglManager.saveRwzxStart(applyId);
            model.addAttribute("work", work);
            model.addAttribute("applyId", applyId);
            model.addAttribute("lx", lx);
            model.addAttribute("sy", sy);
            model.addAttribute("taskId", taskId);
    		model.addAttribute("rwlxIds", model.get("rwlxIds"));
            model.addAttribute("sysVer", biaoshi);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/zxPage";
    }
    
    /**
     * ���񱨸�
     * @param model
     */
    @RequestMapping(value = "/bg.json", produces = "application/json")
    public void bg(String applyId, String taskId, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveZx(applyId, taskId, cur);
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
			model.addAttribute("rwlxIds", rwlxIds);
			if(!"24".equals(rwlxIds)){	//���ճ��칫����
				//�������б����д�����������Զ��ɷ��󶽲�����������Ա�Ĵ����б���
	            rwglManager.saveHdcTask(applyId);
			}
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * ��ȡ��һ��������name
     * @param model
     */
    @RequestMapping(value = "/getNextOperName.json", produces = "application/json")
    public void getNextOperName(String applyId, String taskId, ModelMap model) {
    	try {
    		String nextOperName=zxNode.getNextOperName(applyId, taskId);
            model.put("nextOperName", nextOperName);
            
		} catch (Exception e) {
			log.error("��ȡ��һ��������name����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * ���ҳ��
     * @param applyId   ���뵥���
     * @param taskId    ������
     * @param model
     */
    @RequestMapping(value = "/shPage")
    public String shPage(String applyId, String taskId, ModelMap model,String lx,String sy) {
        try {
            Work work = workManager.get(applyId);
            List<WorkSh> acs = workManager.getShActions(applyId);
            model.addAttribute("acs", acs);
            model.addAttribute("work", work);
            model.addAttribute("applyId", applyId);
            model.addAttribute("taskId", taskId);
            model.addAttribute("curUser", CtxUtil.getCurUser());
            model.addAttribute("curDate", new Date());
            //��ʾ��Ϣ
            Map<String, Object> shInfo=rwglManager.getShInfo(applyId);
            model.addAttribute("shInfo", shInfo);
            model.addAttribute("sy", sy);
            model.addAttribute("lx", lx);
            String nextOperName=zxNode.getNextOperName(applyId, taskId);
            model.put("nextOperName", nextOperName);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/pf/shPage";
    }
    
    /**
     * �������ҳ��
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/shPageInfo.htm")
    public String shPageInfo(String applyId,ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            //�Ƿ��ϱ��ϼ�
            Boolean isSB=rwglManager.isSB(applyId);
            model.addAttribute("isSB", isSB);
            
            //�Ƿ����������ϼ���ˣ���������
            Boolean isXP=false;
            if(StringUtil.isNotBlank(work.getXpCity())){
            	isXP=true;
            }
            model.addAttribute("isXP", isXP);
            
            Map<String, Object> bgxqMap=rwglManager.taskDetailBGXQ(applyId);
            model.addAttribute("bgxqMap", bgxqMap);
            
            model.addAttribute("work", work);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/shPageInfo";
    }
    
    /**
     * ��˲���
     * @param applyId
     * @param taskId
     * @param passed
     * @param model
     */
    @RequestMapping(value = "/sh.json", produces = "application/json")
    public void sh(String applyId, String taskId, Boolean passed, String opinion, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveSh(applyId, taskId, passed, opinion, cur);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * �˻ز���
     * @param applyId
     * @param taskId
     * @param opinion
     * @param model
     */
    @RequestMapping(value = "/th.json", produces = "application/json")
    public void th(String applyId, String taskId, String opinion, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            workManager.saveTh(applyId, taskId, opinion, cur);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
    /**
     * ���������ϱ��ļ�
     * @param workId
     * @param model  ids�ϱ�����ids
     */
    @RequestMapping(value = "/create_report.json", produces = "application/json")
    public void createReport(String applyId, String taskId, String opinion,String ids, ModelMap model) {
        try {
        	TSysUser cur = CtxUtil.getCurUser();
            jobManager.saveJob(JobType.UPLOAD_FILE, applyId);
            
            //�����ϱ��������ͱ���+��������+�ϱ���ʾ��Ϣ
            workManager.saveSbRwlxAndFile(applyId,ids,opinion);
            
            workManager.saveSh(applyId, taskId, Boolean.TRUE, opinion, cur);
            
            //ֱ�ӽ����ϱ��������Ϣ�ļ�����
            Work xpwork = workDao.get(applyId);
            Work work = workManager.get(xpwork.getSjid());
            workReportManager.saveReportTask(work);
            JsonResultUtil.suncess(model, "�����ɹ�");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }
    }
    
////////////////////////////////////////////////////�Ѱ�����ģ��////////////////////////////////////////////////////  
    /**
     * 
     * �������ܣ���ת�����Ѱ������б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/ybrwList.htm")
    public String ybrwList(ModelMap model) {
        return "app/work/rwgl/ybrwList";
    }
    
    /**
     * 
     * �������ܣ���ѯ�����������б���
     * ���������rwmc���������ƣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getYbrwList.json", produces = "application/json")
    public void getYbrwList(ModelMap model, String zfdxType, String pfrId, String zbrId, String rwmc,String rwly,String rwzt,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String tasktype, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getYbrwList(zfdxType,rwmc,rwly, rwzt, pfStarttime, pfEndtime, gdStarttime, gdEndtime, tasktype, pfrId, zbrId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * �������ܣ���ҳ��ѯ�Ѱ�����ǰ6��
     */
    @RequestMapping(value = "/ybQuery.json", produces = "application/json")
    public void ybQuery(ModelMap model) {
        try {
            String ybString = "";
            ybString = rwglManager.ybQuery();
            model.addAttribute("arr", ybString);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    ///////////////////////////////////�����������start////////////////////////////////////
    /**
     * ����ҳ��
     * @param applyId   ���뵥���
     * @param taskId    JBPM������
     * @param model
     */
    @RequestMapping(value = "/taskDetail.htm")
    public String taskDetail(String applyId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);

            model.addAttribute("work", work);
            model.addAttribute("applyId", applyId);
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/taskDetail";
    }
    
    /**
     * ��������ҳ��
     * @param applyId   ����id
     * @param model
     */
    @RequestMapping(value = "/rwyq.htm")
    public String rwyq(String applyId, ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        		if(StringUtils.isNotBlank(work.getDjrId())){
        			TSysUser djrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getDjrId());
        			work.setDjrName(djrObj.getName());
        		}else{
        			work.setDjrName("");
        		}
        		//������Դ���ƣ���Ϊ�ǲ鿴ҳ�棬ֱ����ԭ�ֶ��ˣ�
        		if(work.getSource()!=null){
        			String str1=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWLY.getCode(),work.getSource());
            		//work.setSource(str1);
        			model.addAttribute("source", str1);
        		}
        		//�����ܼ�����
        		if(work.getSecurity()!=null){
        			String str2=commonManager.getDicNameByTypeAndCode(DicTypeEnum.RWMJ.getCode(),work.getSecurity());
            		//work.setSecurity(str2);
        			model.addAttribute("security", str2);
        		}
        		//�����̶�����
        		if(work.getEmergency()!=null){
        			String str3=commonManager.getDicNameByTypeAndCode(DicTypeEnum.JJCD.getCode(),work.getEmergency());
            		//work.setEmergency(str3);
        			model.addAttribute("emergency", str3);
        		}
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("applyId", applyId);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/rwyq";
    }
    /**
     * ����ҳ�棺������Ϣ
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/taskDetailJBXX.htm")
    public String taskDetailJBXX(String applyId,ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            Map<String, Object> jbxxMap=rwglManager.taskDetailJBXX(applyId);
            model.addAttribute("jbxxMap", jbxxMap);
            
            model.addAttribute("work", work);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/taskDetailJBXX";
    }
    /**
     * ����ҳ�棺������ת��¼
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/taskDetailRWLZJL.htm")
    public String taskDetailRWLZJL(String applyId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            Map<String, Object> rwlzjlMap=rwglManager.taskDetailRWLZJL(applyId);
            model.addAttribute("rwlzjlMap", rwlzjlMap);
            
            model.addAttribute("work", work);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/taskDetailRWLZJL";
    }
    /**
     * ����ҳ�棺��������
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/taskDetailBGXQ.htm")
    public String taskDetailBGXQ(String applyId, String taskId, ModelMap model) {
        try {
            Work work = workManager.get(applyId);
            
            //�Ƿ����������ϼ���ˣ���������
            Boolean isXP=false;
            if(StringUtil.isNotBlank(work.getXpCity())){
            	isXP=true;
            }
            model.addAttribute("isXP", isXP);
            
            Map<String, Object> bgxqMap=rwglManager.taskDetailBGXQ(applyId);
            model.addAttribute("bgxqMap", bgxqMap);
            
            model.addAttribute("work", work);
            model.addAttribute("taskId", taskId);
            
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/rwgl/taskDetailBGXQ";
    }
    ///////////////////////////////////�����������end///////////////////////////////////
    
    
    /**
     * 
     * �������ܣ���ѯ�����б���
     * ���������rwmc���������ƣ�createTime�����ڴ���ʱ�䡣
     * ����ֵ��
     */
    @RequestMapping(value = "/getJzList.json", produces = "application/json")
    public void getJzList(ModelMap model, String rwmc, String pfStarttime, String pfEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getJzList(rwmc, pfStarttime, pfEndtime, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * �����½�����ҳ��
     * @param applyId  ����Id;isCp �ǣ�1��������
     * @param model
     */
    @RequestMapping(value = "/xjjzPage.htm")
    public String xjjzPage(String applyId,String isCp,ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        	}else{//����
        		work = new Work();
        		Date d = new Date();
        		work.setCreateTime(null);//Ĭ�ϴ���ʱ��Ϊ��
        		String jzName = DateUtil.getDateTime("yyyy-MM-dd", d) + "�ֳ�ִ����¼";
        		work.setName(jzName);//�������ƣ��ȸ���Ĭ��ֵ
        		work.setEndTime(d);//���ڴ���ʱ��
        	}
        	model.addAttribute("work", work);
        	if(StringUtil.isNotBlank(isCp)){//�Ƿ�����
        		model.addAttribute("isCp", "1");
        	}else{
        		model.addAttribute("isCp", "0");
        	}
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/jzgl/jzPage";
    }
    
    /**
     * ������ڲ鿴ҳ��
     * @param applyId   ���뵥���
     * @param model
     */
    @RequestMapping(value = "/jzPageInfo.htm")
    public String jzPageInfo(String applyId,ModelMap model) {
        try {
        	Work work = null;
        	//�ɷ�
        	if(StringUtils.isNotBlank(applyId)){
        		work = workManager.get(applyId);
        	}
        	model.addAttribute("work", work);
        	model.addAttribute("curUser", CtxUtil.getCurUser());
        } catch (Exception e) {
            log.error("", e);
        }
        return "app/work/jzgl/jzPageInfo";
    }
    
/////////////////////////////////////////�����ֳ�ִ������ģ��////////////////////////////////////////////////
    /**
     * 
     * �������ܣ���ת���ֳ�ִ���б�ҳ�档
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/xczfList.htm")
    public String xczfList(ModelMap model,String title) {
    	model.put("title", title);
        return "app/work/xczf/xczfList";
    }
    
    /**
     * 
     * �������ܣ���ѯ�ֳ�ִ���б���
     * ���������rwmc���������ƣ�endtime����������ʱ�䡣
     * ����ֵ��
     */
    @RequestMapping(value = "/getXczfList.json", produces = "application/json")
    public void getXczfList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, "", "", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    
    /**
     * ��DOM4J����xml�ĵ�
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     * 
     */
    @RequestMapping(value = "/CreateXML.json", produces = "application/json")
    public void BuildDOM4JCreateXML(ModelMap model, String applyId) throws UnsupportedEncodingException, FileNotFoundException {
		try {
    		//���ɲ������Ӧ��Xml�ļ�
			rwglManager.saveXml(applyId);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * ��ת��ѡ���ŷõǼǱ�ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/choseeXfdjb.htm")
    public String choseeXfdjb(ModelMap model,String type) {
    	model.put("type", type);
    	return "app/work/xfdj/pf_choseeXfdjb";	
    }
    
    /**
     * �����ٴΰ������ɷ�
     * @param applyId   ����id
     * @param model
     */
    @RequestMapping(value = "/rwzbl.json")
    public void rwzbl(String applyId, ModelMap model, String slsj) {
    	try {
    		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Work work = workDao.get(applyId);
        	WorkDto wto = new WorkDto();
        	wto.setDjrId(work.getDjrId());
        	wto.setDjrName(work.getDjrName());
        	wto.setEmergency(work.getEmergency());
        	wto.setJsr(work.getJsr());
        	wto.setJsrId(work.getJsr());
        	wto.setJsrIds(work.getJsrIds());
        	wto.setJsrName(work.getJsrName());
        	wto.setJsrNames(work.getJsrNames());
        	wto.setPsyj(work.getPsyj());
        	wto.setRwmcgs(work.getRwmcgs());
        	if(null != work.getRwmcrq()){
        		wto.setRwmcrq(sdf.format(work.getRwmcrq()));
        	}
        	wto.setSecurity(work.getSecurity());
        	wto.setSfdb(work.getSfdb());
        	wto.setSource(work.getSource());
        	wto.setStartTime(sdf.format(work.getStartTime()));
        	wto.setWorkName(work.getName());
        	wto.setWorkNote(work.getWorkNote());
        	wto.setXfbcjsrId(work.getXfbcjsrId());
        	wto.setXfbcjsrName(work.getXfbcjsrName());
        	wto.setXfts("Y");
        	wto.setXptype(work.getXpCity());
        	wto.setZfdxType(work.getZfdxType());
        	wto.setJsrIds(work.getShrids());
            wto.setEndTime(slsj);//Ҫ�����ʱ��
            //����ŷõǼǱ�
			if(StringUtils.isNotBlank(work.getXfdjbId())){
				TBizXfdj xfdjb = (TBizXfdj) xfdjManager.get(TBizXfdj.class, work.getXfdjbId());
				if(xfdjb!=null){
					wto.setXfdjbId(xfdjb.getId());
				}
			}
			//�ŷñ���������
			if(StringUtils.isNotBlank(work.getXfbcjsrId())){
    			TSysUser xfdjbcrObj = (TSysUser) this.userManager.get(TSysUser.class, work.getXfbcjsrId());
    			wto.setXfbcjsrName(xfdjbcrObj.getName());
    		}
			TSysUser u = (TSysUser) rwglManager.getPfr(applyId);
			Work w1 = workPf.savePf(wto, work.getZxrId(), u, "");
        	TSysUser curUser = CtxUtil.getCurUser();
            commWorkManager.saveTaskTypeMultiple(w1.getId(), TaskTypeCode.XFTS.getCode(), curUser);
        	if(("0").equals(work.getRwmcgs())){
        			//ͨ����һ������id��ȡִ����������
            		String zfdxmcs = "";
            		List<Map<String, String>> listMap = commWorkManager
        					.zfdxTableData(applyId);
            		for(int i = 0; i < listMap.size(); i++){
            			if(i<listMap.size()-1){
            				zfdxmcs += listMap.get(i).get("lawobjname")+',';
            			}else{
            				zfdxmcs += listMap.get(i).get("lawobjname");
            			}
            		}
            		if(listMap.size() > 0){
            			String rowString = "";
                		for(int i=0;i<listMap.size();i++){
                			rowString += "{'lawobjid':'"+listMap.get(i).get("lawobjid")+"'";
                			rowString += ",'lawobjname':'"+listMap.get(i).get("lawobjname")+"'";
                			rowString += ",'fddbr':'"+listMap.get(i).get("manager")+"'";
                			rowString += ",'id':'"+listMap.get(i).get("id")+"'";
                			rowString += ",'fddbrlxdh':'"+listMap.get(i).get("managermobile")+"'";
                			rowString += ",'hbfzr':'"+listMap.get(i).get("bjcr")+"'";
                			rowString += ",'hbfzrlxdh':'"+listMap.get(i).get("lxdh")+"'";
                			rowString += ",'regionid':'"+listMap.get(i).get("region")+"'";
                			rowString += ",'address':'"+listMap.get(i).get("address")+"'}";
                			if(i!=listMap.size()-1){
                				rowString += ",";
                			}
                		}
                		zfdxmcs = '['+rowString+']';//�ڱ������뵽�ɷ������ִ����������ݶ�������
            		}
            		
            		// ���������б�
        			List<Map<String, String>> rwlxlistMap = commWorkManager
        					.getTaskTypeByTaskId(applyId);
        			model.put("rwlxlistMap", rwlxlistMap);
        			if(StringUtils.isNotBlank(w1.getId())){
                		//��������ʱ������������ͣ�ִ���������ƣ�ִ���������͵ı���
                		if(StringUtils.isNotBlank(w1.getZfdxType())){
                			commWorkManager.saveZfdxTypeOnChange(w1.getId(), w1.getZfdxType(),TaskTypeCode.XFTS.getCode());
                			commWorkManager.saveZfdxType(w1.getId(), w1.getZfdxType());
                		}
                		
            			if(StringUtils.isNotBlank(zfdxmcs)){
            				JSONArray array = new JSONArray(zfdxmcs);
                    		commonManager.saveChoseeLawobj(w1.getId(), w1.getZfdxType(), array, curUser);
            			}
                	}
        			//�ɷ�����
        			List<TDataFile> pfFileList= commonManager.queryFileList(work.getId(), "RWGLPFFJ");
        			if(pfFileList!=null && pfFileList.size()>0){
        				for(int k=0;k<pfFileList.size();k++){
        					TDataFile t= new TDataFile(w1.getId(),pfFileList.get(k).getOsname(),pfFileList.get(k).getName(),pfFileList.get(k).getSize(),pfFileList.get(k).getType(),pfFileList.get(k).getRelativepath());
        					commonManager.saveFile(t);
            			}
        			}
        	}
            JsonResultUtil.suncess(model, "�����ٴΰ����ɷ���ɣ�");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "�����ٴΰ����ɷ�ʧ�ܣ�");
        }
    }
    
    /**
     * 
     * �������ܣ���ѯ������ʵ���б���
     * ���������rwmc���������ƣ�rwly��������Դ��
     * ����ֵ��
     */
    @RequestMapping(value = "/getRwslList.json", produces = "application/json")
    public void getRwslList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRwslList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime, xfdjId, page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    @RequestMapping(value = "/preSubmitNodePubQuery.htm")
	public String preSubmitNodePubQuery(ModelMap model, String applyId) {
		try {
			/*JSONArray re = new JSONArray();
			re = rwglManager.preSubmitNodePubQuery();
			model.addAttribute("menu", re.toString());*/
			/*String workid = "";
        	String trackid = "";
        	Work w = workDao.get(applyId);
        	workid = w.getShiliid();
        	trackid = w.getTrackid();
        	Map<String, Object> map = new HashMap<String, Object>();
        	TSysUser curUser = CtxUtil.getCurUser();
        	if(workid == null || "".equals(workid)) {
            	log.debug("�ɷ�������" + LogUtil.m(curUser.getId()));
    		}else {
    			map.put("workId", workid);
    			//submit(curUser.getId(), workid, trackid, "Line3~Node2", curUser.getId(), curUser.getName());
    			map = workflowoperator.preSubmit(workid, trackid, curUser.getId(), curUser.getName());
    		}
            model.addAttribute("workId", workid);
            map.toString().replace("=", ":");
    		model.addAttribute("str", map);*/
			model.addAttribute("applyId", applyId);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return "common/preSubmitNodePubQuery";
	}
    
    /**
     * �����ɷ�����
     * @param model
     */
    @RequestMapping(value = "/node.json", produces = "application/json")
    public void node(ModelMap model, String applyId) {
        try {
        	String workid = "";
        	String trackid = "";
        	Work w = workDao.get(applyId);
        	workid = w.getShiliid();
        	trackid = w.getTrackid();
        	Map<String, Object> map = new HashMap<String, Object>();
        	TSysUser curUser = CtxUtil.getCurUser();
        	if(workid == null || "".equals(workid)) {
            	log.debug("�ɷ�������" + LogUtil.m(curUser.getId()));
    		}else {
    			map.put("workId", workid);
    			//submit(curUser.getId(), workid, trackid, "Line3~Node2", curUser.getId(), curUser.getName());
    			map = workflowoperator.preSubmit(workid, trackid, curUser.getId(), curUser.getName());
    		}
            model.addAttribute("workId", workid);
    		model.addAttribute("str", map);
            JsonResultUtil.suncess(model, "�����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }
    
}