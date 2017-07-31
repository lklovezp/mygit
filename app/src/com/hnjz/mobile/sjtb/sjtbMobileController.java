package com.hnjz.mobile.sjtb;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

@Controller
public class sjtbMobileController  extends AbsJbpmController{

 @Autowired
 private sjtbMoManager    sjtbMoManager;
 
 @Autowired
 private RwglManager    rwglManager;
 
 	/**
	 * ��ȡ�������͵ķ�����������������
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/allrwlxData.mo")
	public void allrwlxData(String strUpdate, ModelMap model) {
		try {
			//���������б�
			List<Map<String, String>> rwlxlistMap = sjtbMoManager.getTaskType(strUpdate);
	        model.put("rwlxlistMap", rwlxlistMap);
		}catch (Exception e) {
	        log.error("��ѯ������", e);
	    }
	}
	
	/**
	 * ��ȡִ������ķ�����ִ�����������Ϣ
	 * @param model
	 */
	@RequestMapping(value ="/allLawobj.mo")
	public void queryAllLawobj(String strUpdate, ModelMap model){
		try{
			//����ִ������
			List<Map<String,String>> AllLawobj= sjtbMoManager.queryAllLawobj(strUpdate);
			model.put("AllLawobj", AllLawobj);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	 
	/**
	 * ����ҳ�棺ִ������������Ϣ
	 * @param model
	 */
	@RequestMapping(value ="/Lawobjtype.mo")
	public void queryLawobjtype(String strUpdate, ModelMap model){
		try{
			//����ִ����������
			List<Map<String,String>> Lawobjtype= sjtbMoManager.queryLawobjtype(strUpdate);
			model.put("Lawobjtype", Lawobjtype);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡȫ����Ա�ķ�������Ա�б�����
	 * 
	 * @param model
	 */
	@RequestMapping(value ="/alluserData.mo")
	public void queryAllUser(String strUpdate, ModelMap model){
		try{
			//������Ա���
			List<Map<String,String>> AllUserMap = sjtbMoManager.queryAllUser(strUpdate);
			model.put("AllUserMap", AllUserMap);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е�ִ����������
	 * @param model
	 */
	@RequestMapping(value ="/allDataFile.mo")
	public void queryallDataFile(String strUpdate, ModelMap model){
		try{
			//����ִ���ļ�����
			List<Map<String,String>> allDataFile= sjtbMoManager.queryallDataFile(strUpdate);
			model.put("allDataFile", allDataFile);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е���������
	 * @param model
	 */
	@RequestMapping(value ="/allDataArea.mo")
	public void queryallDataArea(String strUpdate, ModelMap model){
		try{
			//������������
			List<Map<String,String>> allDataArea= sjtbMoManager.queryallDataArea(strUpdate);
			model.put("allDataArea", allDataArea);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е�������������
	 * @param model
	 */
	@RequestMapping(value ="/allDataRegion.mo")
	public void queryallDataRegion(String strUpdate, ModelMap model){
		try{
			//����������������
			List<Map<String,String>> allDataRegion= sjtbMoManager.queryallDataRegion(strUpdate);
			model.put("allDataRegion", allDataRegion);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е�ִ���ļ�����
	 * @param model
	 */
	@RequestMapping(value ="/allLawdocData.mo")
	public void queryallLawdocData(String strUpdate, ModelMap model){
		try{
			//����ִ���ļ�����
			List<Map<String,String>> allLawdocData= sjtbMoManager.queryallLawdocData(strUpdate);
			model.put("allLawdocData", allLawdocData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������еĽ�ɫ����
	 * @param model
	 */
	@RequestMapping(value ="/allRoleData.mo")
	public void queryallRoleData(String strUpdate, ModelMap model){
		try{
			//���н�ɫ����
			List<Map<String,String>> allRoleData= sjtbMoManager.queryallRoleData(strUpdate);
			model.put("allRoleData", allRoleData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������еĲ�������
	 * @param model
	 */
	@RequestMapping(value ="/allOrgData.mo")
	public void queryallOrgData(String strUpdate, ModelMap model){
		try{
			//���в�������
			List<Map<String,String>> allOrgData= sjtbMoManager.queryallOrgData(strUpdate);
			model.put("allOrgData", allOrgData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е���ҵ��������
	 * @param model
	 */
	@RequestMapping(value ="/allIndustryData.mo")
	public void queryallIndustryData(String strUpdate, ModelMap model){
		try{
			//������ҵ��������
			List<Map<String,String>> allIndustryData= sjtbMoManager.queryallIndustryData(strUpdate);
			model.put("allIndustryData", allIndustryData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е�Υ����������
	 * @param model
	 */
	@RequestMapping(value ="/allIllegaltypeData.mo")
	public void queryallIllegaltypeData(String strUpdate, ModelMap model){
		try{
			//����Υ����������
			List<Map<String,String>> allIllegaltypeData= sjtbMoManager.queryallIllegaltypeData(strUpdate);
			model.put("allIllegaltypeData", allIllegaltypeData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е���Ա���Ź�������
	 * @param model
	 */
	@RequestMapping(value ="/allUserOrgData.mo")
	public void queryallUserOrgData(String strUpdate, ModelMap model){
		try{
			//������Ա�����������
			List<Map<String,String>> AllUserOrgMap = sjtbMoManager.queryAllUserOrg(strUpdate);
			model.put("AllUserOrgMap", AllUserOrgMap);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������е���Ա��ɫ��������
	 * @param model
	 */
	@RequestMapping(value ="/allUserRoleData.mo")
	public void queryallUserRoleData(String strUpdate, ModelMap model){
		try{
			//������Ա�����������
			List<Map<String,String>> AllUserRoleMap = sjtbMoManager.queryAllUserRole(strUpdate);
			model.put("AllUserRoleMap", AllUserRoleMap);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������еĴ������������
	 * @param model
	 */
	@RequestMapping(value ="/allDbrwData.mo")
	public void queryallDbrwData(String strUpdate, ModelMap model){
		try{
			//���д���������ص�����
			List<Map<String,String>> allDbrwData = sjtbMoManager.queryallDbrwData(strUpdate);
			model.put("allDbrwData", allDbrwData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������������������
	 * @param model
	 */
	@RequestMapping(value ="/allDataRecordData.mo")
	public void queryallDataRecordData(String strUpdate, ModelMap model){
		try{
			//������Ա�����������
			List<Map<String,String>> allDataRecordData = sjtbMoManager.queryallDataRecordData(strUpdate);
			model.put("allDataRecordData", allDataRecordData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ���ݷ��������еĹ�����������
	 * @param model
	 */
	@RequestMapping(value ="/allRelateData.mo")
	public void queryallallRelateData(String strUpdate, ModelMap model){
		try{
			//���й�����������
			List<Map<String,String>> allRelateData= sjtbMoManager.queryallIllegaltypeData(strUpdate);
			model.put("allRelateData", allRelateData);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ��ȡ�Ƿ�Ҫͬ���ķ��������еı������Ƿ�Ҫͬ�����ж�
	 * @param model
	 */
	@RequestMapping(value ="/TableDataIsSynch.mo")
	public void TableDataIsSynch(String strUpdate, String tableName, ModelMap model){
		try{
			//���й�����������
			List<Map<String,String>> TableDataIsSynch= sjtbMoManager.TableDataIsSynch(strUpdate, tableName);
			model.put("TableDataIsSynch", TableDataIsSynch);
		}catch(Exception e){
			log.error("��ѯ������", e);
		}
	}
	
	/**
     * 
     * �������ܣ���ѯ��ִ�������������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getZfjcDbrwList.mo", produces = "application/json")
    public void getZfjcDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getZfjcDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * 
     * �������ܣ���ѯ���ŷ�Ͷ�ߴ��������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getXftsDbrwList.mo", produces = "application/json")
    public void getXftsDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXftsDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
    
    /**
     * 
     * �������ܣ���ѯ���ճ��칫���������б���
     * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
     * ����ֵ��
     */
    @RequestMapping(value = "/getRcbgDbrwList.mo", produces = "application/json")
    public void getRcbgDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRcbgDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("��ѯ������", e);
        }
    }
	
} 

	
