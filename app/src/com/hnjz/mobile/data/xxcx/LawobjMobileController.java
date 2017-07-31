package com.hnjz.mobile.data.xxcx;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.TaskandlawobjForm;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.xxgl.complaint.ComplaintForm;
import com.hnjz.app.data.xxgl.complaint.ComplaintManager;
import com.hnjz.app.data.xxgl.lawobj.HpxxForm;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.mobile.common.CommonMobileManager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-4-14
 * ����������ִ��������Ʋ�(�ն�)
 *
 */
@Controller
public class LawobjMobileController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawobjMobileController.class);
    
    @Autowired
    private LawobjMobileManager lawobjMobileManager;
    @Autowired
    private LawobjManager lawobjManager;
    @Autowired
    private CommonManager commonManager;
    @Autowired
    private CommonMobileManager commonMobileManager;
    @Autowired
    private ComplaintManager complaintManager;
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryGywryList.mo")
    public void queryGywryListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryGywryList(name, regionId, orgId,  kzlx, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryJsxmList.mo")
    public void queryJsxmListMo(ModelMap model,String name,String regionId,String orgId,String hylx,String qyzt,String lawobjId,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryJsxmList(name, regionId, orgId, hylx, null, lawobjId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
    
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYyxxList.mo")
    public void queryYyxxListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYyxxList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryJzgdList.mo")
    public void queryJzgdListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String sgdwmc,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryJzgdList(name, regionId, orgId, qyzt,sgdwmc,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryGlxxList.mo")
    public void queryGlxxListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryGlxxList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryScxxList.mo")
    public void queryScxxListMo(ModelMap model,String name,String industryId,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryScxxList(name, industryId, regionId, orgId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryXqyzList.mo")
    public void queryXqyzListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryXqyzList(name, regionId, orgId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
    
    
    /**
     * ִ�������������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawobjInfo.mo")
    public void getLawobjBaseInfo(ModelMap model,String id){
    	TDataLawobj lawobj = (TDataLawobj) lawobjMobileManager.get(TDataLawobj.class, id);
    	JSONArray array = lawobjMobileManager.getLawobjInfo(lawobj);
    	model.addAttribute("baseInfo", array.toString());//������Ϣ
    	model.addAttribute("baseInfoLawobjtype", lawobj.getLawobjtype());//��ҵ������ҵ����ֵ
    	Map<String,String> map = null;
    	//��������
		map = lawobjMobileManager.getOneFileListByPid(lawobj.getId());
		model.addAttribute("oneFileInfo",map==null?"":map);//�����б�
		FyWebResult result = lawobjManager.queryHpxxList(lawobj.getId(),"1","1000");
		model.addAttribute("hpxxList",result.getRows());//�����б�
    	//����Ͷ����Ϣ
		map = complaintManager.queryOneComplaint(lawobj.getId());
		model.addAttribute("oneComplaint",map==null?"":map);
    	//����ִ����ʷ��Ϣ
    	map = lawobjMobileManager.getOneZfHistory(lawobj.getId());
    	model.addAttribute("oneZfHistory",map==null?"":map);
    }
    
    
    /**
     * ��ת��������Ϣ����ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/hpxxInfo.mo")
    public void hpxxInfo(ModelMap model,HpxxForm hpxxForm) {
    	if(hpxxForm!=null && null != hpxxForm.getId()){
    		hpxxForm = lawobjManager.getHpxxInfo(hpxxForm);
    		model.put("hpxxForm", hpxxForm);
    		model.put("fileList", commonMobileManager.queryFileList(hpxxForm.getId(), null).toString());
    	}
    }
    
    /**
     * ִ������������棨����ִ����ʷ��¼��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryZfHistoryList.mo")
    public void queryZfHistoryList(ModelMap model, String lawobjid, String page, String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryZfHistoryList(lawobjid, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
    
    
    /**
	 * 
	 * �������ܣ�����������ѡ���ִ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/querySelectLawobjList.mo")
	@ResponseBody
	public List<TaskandlawobjForm> querySelectLawobjList(ModelMap model,String rwid,String lawobjtype){
		return commonManager.querySelectLawobjList(rwid,lawobjtype);
	}
	
	/**
	 * 
	 * �������ܣ�ѡ��ִ��������棬�б�����
	 * �������������id��ִ���������͡���ǰҳ��ÿҳ��ʾ����
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryLawobjList.mo")
	public void queryLawobjList(ModelMap model,String name,String regionId,String orgId,String lawobjtype,String industryId,String qyzt, String isActive, String kzlx,String lawobjId,String sgdwmc,String isTcPage,String page, String pageSize){
		page = StringUtils.defaultIfBlank(page, "1");
		FyWebResult re = null;
		if(StringUtils.isNotBlank(industryId) && "Y".equals(isTcPage)){
			TDataIndustry tDataIndustry = (TDataIndustry) lawobjManager.get(TDataIndustry.class, industryId);
	    	lawobjtype = tDataIndustry.getTolawobjtype();
		}
		try {
			if("01".equals(lawobjtype)){
				re = lawobjMobileManager.queryGywryList(name, regionId, orgId, kzlx, qyzt, page, pageSize);
			}else if("02".equals(lawobjtype)){
				re = lawobjMobileManager.queryJsxmList(name, regionId, orgId, industryId, "Y", lawobjId, qyzt, page, pageSize);
			}else if("03".equals(lawobjtype)){
				re = lawobjMobileManager.queryYyxxList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("04".equals(lawobjtype)){
				re = lawobjMobileManager.queryGlxxList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("05".equals(lawobjtype)){
				re = lawobjMobileManager.queryJzgdList(name, regionId, orgId, qyzt, sgdwmc, page, pageSize);
			}else if("06".equals(lawobjtype)){
				re = lawobjMobileManager.queryScxxList(name, industryId, regionId, orgId, qyzt, page, pageSize);
			}else if("07".equals(lawobjtype)){
				re = lawobjMobileManager.queryXqyzList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("08".equals(lawobjtype)){
				re = lawobjMobileManager.queryFwyList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("09".equals(lawobjtype)){
				re = lawobjMobileManager.queryYsyList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("10".equals(lawobjtype)){
				re = lawobjMobileManager.queryZzyList(name, regionId, orgId,qyzt, page, pageSize);
			}else if("11".equals(lawobjtype)){
				re = lawobjMobileManager.queryYlyList(name, regionId, orgId, qyzt, page, pageSize);
			}
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * �������ܣ�����ѡ���ִ�������ն�ר�ã�
	
	 * �������������id��ִ���������͡�ִ������id���ö��Ÿ�����
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveChoseeLawobj.mo")
	public void saveChoseeLawobj(ModelMap model,String rwid,String lawobjtype,String lawobjid){
		try {
			lawobjMobileManager.saveChoseeLawobj(rwid, lawobjtype, lawobjid);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
     * 
     * �������ܣ�������Ϣ��ѯ Ͷ����Ϣ�б�����
    
     * ���������
    
     * ����ֵ��
     */
    @RequestMapping(value = "/queryComplaintList.mo")
    public void queryComplaintList(ModelMap model,String lawobjid,String page,String pageSize){
    	try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = complaintManager.queryComplaintList(null,null, null, null, null, "Y", lawobjid, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * ��ת������ҳ��
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/complaintInfo.mo")
    public void complaintInfo(ModelMap model,ComplaintForm complaintForm){
    	if(complaintForm!=null && StringUtils.isNotBlank(complaintForm.getId())){
    		complaintForm = complaintManager.getComplaintInfo(complaintForm);
    	}
    	model.addAttribute("complaintForm", complaintForm);
    }
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryFwyList.mo")
    public void queryFwyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryFwyList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б����ݣ���ʳҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYsyList.mo")
    public void queryYsyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYsyList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryZzyList.mo")
    public void queryZzyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryZzyList(name, regionId, orgId,qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYlyList.mo")
    public void queryYlyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYlyList(name,regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
	
}