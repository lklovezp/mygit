package com.hnjz.app.data.xxgl.lawobj;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjeia;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.xxgl.lawobjdic.LawobjDicManager;
import com.hnjz.app.data.zfdx.lawobjf.LawobjfManager;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPubQueryManager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-3
 * ����������
		ִ��������Ʋ�
 *
 */
@Controller
public class LawobjController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawobjController.class);
    
    @Autowired
    private LawobjManager lawobjManager;
    
    @Autowired
    private LawobjDicManager lawobjDicManager;
    
    @Autowired
	private UserManager userManager;
    
    @Autowired
	private OrgManager orgManager;
    
    @Autowired
	private UserPubQueryManager userPubQueryManager;
    
    @Autowired
	private LawobjfManager tdatalawobjfManager;
    
    /**
     * ��ת��ִ�������ۺϲ�ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawobjList.htm")
    public String lawobjList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxcx/lawobj/xxcx_lawobjList";
    }
    
    /**
     * ����ִ�������б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryLawobjList.json")
    public void queryLawobjList(ModelMap model,String name,String lawobjType,String regionId,String orgId, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryLawobjList(name, lawobjType,regionId,orgId, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת����ȾԴ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/gywryList.htm")
    public String gywryList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/gywryList";
    }
    
    /**
     * ��ת����ȾԴ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ȾԴ���ӱ༭ҳ��
     */
    @RequestMapping(value = "/gywryEdit.htm")
    public String gywryEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("01");
			model.put("lawobj", lawobj);
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/gywryEdit";
    }
    
    /**
     * ��������ִ������
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/saveOrUpdateLawobj.json", produces = "application/json")
    public void saveOrUpdateLawobj(ModelMap model,TDataLawobj lawobj,String jsxmid) {
    	try {
    		boolean nameIsCz = lawobjManager.checkLawobjname(lawobj);
    		if(nameIsCz){
    			JsonResultUtil.fail(model, "�����Ѵ��ڣ������ظ����ӣ���������д��");
    			return;
    		}
			lawobj = lawobjManager.saveOrUpdateLawobj(lawobj,jsxmid);
    		model.put("id", lawobj.getId());
    		model.put("name", lawobj.getName());
    		model.put("type", lawobj.getLawobjtype());
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("����ִ��������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    /**
     * ����ѡ�񴴽��˽���
     * @param model
     * @param all
     * @param id
     * @param methodname
     * @param multi
     * @return
     */
    @RequestMapping(value = "/lawobjUserPubQuery.htm")
	public String lawobjUserPubQuery(ModelMap model, String all, String id,String lawobjtypeid, String lawobjId, String notShowZj, String multi) {
		try {
			String[] ids = null;
			if (StringUtil.isNotBlank(id)){
				 ids = id.split(",");
			}
			JSONArray re = userPubQueryManager.queryUser(ids, all,notShowZj,"true");
			model.addAttribute("menu", re.toString());
			model.addAttribute("ids", id);
			model.addAttribute("multi", multi);
			model.addAttribute("lawobjtypeid", lawobjtypeid);
			model.addAttribute("lawobjId", lawobjId);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return "common/lawObjUserPubQuery";
    }
    /**
     * ������ת��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/transferCjr.json", produces = "application/json")
    public void transferCjr(ModelMap model,String lawobjtypeid, String lawobjId,String userId) {
    	try {
    		String code = null;
    		if("01".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.gywry_cjr.getCode();
    		}else if("02".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.jsxm_cjr.getCode();
    		}else if("03".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.yy_cjr.getCode();
    		}else if("04".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.gl_cjr.getCode();
    		}else if("05".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.jzgd_cjr.getCode();
    		}else if("06".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.sc_cjr.getCode();
    		}else if("07".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.xqyz_cjr.getCode();
    		}else if("08".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.fwy_cjr.getCode();
    		}else if("09".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.ysy_cjr.getCode();
    		}else if("10".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.zzy_cjr.getCode();
    		}else if("11".equals(lawobjtypeid)){
    			code = LawobjOutColunmEnum.yly_cjr.getCode();
    		}
			lawobjManager.transferCjr(lawobjtypeid,lawobjId,code,userId);
			JsonResultUtil.suncess(model, "ת�Ƴɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ת�ƴ����˴���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryGywryList.json")
    public void queryGywryList(ModelMap model,String name,String regionId,String orgId, String qyzt, String kzlx,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryGywryList("","",name, regionId,orgId, qyzt,kzlx, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת����ȾԴ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/gywryInfo.htm")
    public String gywryInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/gywryInfo";
    }
    
    /**
     * ��ת����ȾԴ�鿴������棨������Ϣ���棩
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawobjDetail.htm")
    public String lawobjDetail(ModelMap model,TDataLawobj lawobj) {
    	if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
    		lawobj = lawobjManager.getLawobjInfo(lawobj);
    		List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList(lawobj.getLawobjtype());
    		model.put("innerHtml", lawobjManager.lawobjDetailInnerHtml(list,lawobj));
    	}
    	model.put("lawobj", lawobj);
    	return "app/data/xxgl/lawobj/lawobjDetail";
    }
    /**
     * ��ת����ȾԴ�����鿴����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/infoFj.htm")
    public String lawobInfoFj(ModelMap model,TDataLawobjf lawobjf) {
    	if(lawobjf!=null && StringUtils.isNotBlank(lawobjf.getId())){
    		lawobjf = tdatalawobjfManager.getLawobjfInfo(lawobjf);
    		//List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList(lawobj.getLawobjtype());
    		//model.put("innerHtml", lawobjManager.lawobjDetailInnerHtml(list,lawobj));
    	}
    	
    	model.put("lawobj", lawobjf);
    	log.info("ceshiname��===="+lawobjf.getDwmc());
    	return "app/data/xxgl/lawobj/infoGywryFjList";
    }
    
    /**
     * ��ҵ��ȾԴ��Ϊ��Ч
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/delLawobj.json")
    public void delLawobj(ModelMap model,String id) {
    	try {
    		lawobjManager.removeLawobj(id);
    		JsonResultUtil.suncess(model, "ɾ���ɹ���");
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("ɾ��������", e);
    	}
    }
    
    /**
     * 
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/fileupload.htm")
    public String fileupload(ModelMap model) {
        return "app/data/xxgl/lawobj/index";
    }
    
    @RequestMapping(value = "/uploadFile.json",method=RequestMethod.POST, produces = "application/json")
    public void uploadFile(ModelMap model,@RequestParam MultipartFile[] myfiles, String fileType){
    	lawobjManager.saveUploadFile(myfiles[0], fileType);
    }
    
    /**
     * ��ת��������Ŀ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/jsxmList.htm")
    public String jsxmList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/jsxmList";
    }
    
    /**
     * ��ת��������Ŀѡ��ִ���������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/choseeLawobj.htm")
    public String choseeLawobj(ModelMap model,String industryId,String type) {
    	TDataIndustry tDataIndustry = (TDataIndustry) lawobjManager.get(TDataIndustry.class, industryId);
    	String lawobjtype = tDataIndustry.getTolawobjtype();
    	model.put("type", type);
    	if("01".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeGywry";
    	}else if("03".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeYyxx";
    	}else if("04".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeGlxx";
    	}else if("05".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeJzgd";
    	}else if("06".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeScxx";
    	}else if("07".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeXqyz";
    	}else if("08".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeFwy";
    	}else if("09".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeYsy";
    	}else if("10".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeZzy";
    	}else if("11".equals(lawobjtype)){
    		return "app/data/xxgl/lawobj/jsxm_choseeYly";
    	}else{
    		return null;
    	}
    }
    
    /**
     * ��ת����������ѡ��ʩ����λ����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/choseeSgdw.htm")
    public String choseeSgdw(ModelMap model,String type) {
    	model.put("type", type);
    	return "app/work/sgdw/jzgd_choseeSgdw";	
    }
    
    /**
     * ��ת��������Ŀ�༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/jsxmEdit.htm")
    public String jsxmEdit(ModelMap model,TDataLawobj lawobj, String ismobile) {
    	if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
    		lawobj = lawobjManager.getLawobjInfo(lawobj);
    	}
    	List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("02");
    	model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
    	model.put("lawobj", lawobj);
    	Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
    	model.put("innerHtml", map.get("html").toString());
    	model.put("innerJs", map.get("js").toString());
    	return "app/data/xxgl/lawobj/jsxmEdit";
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryJsxmList.json")
    public void queryJsxmList(ModelMap model,String name,String regionId,String orgId,String lawobjId,String industryId,String isActive,String isChoose,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryJsxmList("","",name, regionId, orgId, lawobjId, industryId, isActive,isChoose, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת��������Ŀ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/jsxmInfo.htm")
    public String jsxmInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/jsxmInfo";
    }
    
    /**
     * ��ת��������Ϣ�༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/hpxxEdit.htm")
    public String hpxxEdit(ModelMap model,HpxxForm hpxxForm) {
    	if(hpxxForm!=null && null != hpxxForm.getId()){
    		hpxxForm = lawobjManager.getHpxxInfo(hpxxForm);
    		model.put("title", "�༭������Ϣ");
    	}else{
    		model.put("title", "�½�������Ϣ");
    	}
    	model.put("hpxxForm", hpxxForm);
    	return "app/data/xxgl/lawobj/hpxxEdit";
    }
   
    /**
     * ��ת��������Ϣ����ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/hpxxInfo.htm")
    public String hpxxInfo(ModelMap model,HpxxForm hpxxForm) {
    	if(hpxxForm!=null && null != hpxxForm.getId()){
    		hpxxForm = lawobjManager.getHpxxInfo(hpxxForm);
    	}
    	model.put("hpxxForm", hpxxForm);
    	return "app/data/xxgl/lawobj/hpxxInfo";
    }
    
    /**
     * ��ҵ��ȾԴ��Ϊ��Ч
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/ delHpxx.json")
    public void delHpxx(ModelMap model,String id) {
    	try {
    		lawobjManager.removeHpxx(id);
    		JsonResultUtil.suncess(model, "ɾ���ɹ���");
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("ɾ��������", e);
    	}
    }
    
    /**
     * �������»�����Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ����������ȾԴ
     */
    @RequestMapping(value = "/saveOrUpdateHpxx.json", produces = "application/json")
    public void saveOrUpdateHpxx(ModelMap model,HpxxForm hpxxForm) {
    	try {
    		TDataLawobjeia tDataLawobjeia = lawobjManager.saveOrUpdateHpxx(hpxxForm);
    		model.put("id", tDataLawobjeia.getId());
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���滷����Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/hpxxList.htm")
    public String hpxxListHtm(ModelMap model,String pid) {
    	model.put("pid", pid);
    	return "app/data/xxgl/lawobj/hpxxList";
    }
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_hpxxList.htm")
    public String xxcx_hpxxList(ModelMap model,String pid) {
    	model.put("pid", pid);
    	return "app/data/xxcx/lawobj/xxcx_hpxxList";
    }
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/hpxxList.json")
    public void hpxxList(ModelMap model,String name,String pid,String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjManager.queryHpxxList(pid, page, rows);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
    
    /**
     * ��ת��ҽԺ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/yyxxList.htm")
    public String yyxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/yyxxList";
    }
    
    /**
     * ��ת��ҽԺ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ȾԴ���ӱ༭ҳ��
     */
    @RequestMapping(value = "/yyxxEdit.htm")
    public String yyxxEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("03");
			model.put("lawobj", lawobj);
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/yyxxEdit";
    }
    
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYyxxList.json")
    public void queryYyxxList(ModelMap model,String name,String regionid,String orgId,String qyzt,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryYyxxList("","",name, regionid, orgId, qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת��ҽԺ��Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/yyxxInfo.htm")
    public String yyxxInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/yyxxInfo";
    }
    
    /**
     * ��ת����¯��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/glxxList.htm")
    public String glxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/glxxList";
    }
    
    /**
     * ��ת������������Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ����������Ϣ���ӱ༭ҳ��
     */
    @RequestMapping(value = "/glxxEdit.htm")
    public String glxxEdit(ModelMap model,TDataLawobj lawobj, String ismobile) {
    	if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
    		lawobj = lawobjManager.getLawobjInfo(lawobj);
    	}
    	List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("04");
    	model.put("lawobj", lawobj);
    	model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
    	Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
    	model.put("innerHtml", map.get("html").toString());
    	model.put("innerJs", map.get("js").toString());
    	return "app/data/xxgl/lawobj/glxxEdit";
    }
    
    /**
     * �����б����� ��¯��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryGlxxList.json")
    public void queryGlxxList(ModelMap model,String name,String regionid,String orgId, String qyzt, String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryGlxxList("","",name, regionid, orgId, qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת����¯�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/glxxInfo.htm")
    public String glxxInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/glxxInfo";
    }
    
    /**
     * ��ת������������Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/jzgdList.htm")
    public String jzgdList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/jzgdList";
    }
    
    /**
     * ��ת������������Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ����������Ϣ���ӱ༭ҳ��
     */
    @RequestMapping(value = "/jzgdEdit.htm")
    public String jzgdEdit(ModelMap model,TDataLawobj lawobj, String ismobile) {
    	if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
    		lawobj = lawobjManager.getLawobjInfo(lawobj);
    	}
    	List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("05");
    	model.put("lawobj", lawobj);
    	model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
    	Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
    	model.put("innerHtml", map.get("html").toString());
    	model.put("innerJs", map.get("js").toString());
    	return "app/data/xxgl/lawobj/jzgdEdit";
    }
    
    
    /**
     * �����б����� ����������Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryJzgdList.json")
    public void queryJzgdList(ModelMap model,String name,String regionid,String orgId,String qyzt,String isActive, String sgdwmc, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryJzgdList("","",name, regionid, orgId, qyzt, isActive, sgdwmc, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת���������ز鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/jzgdInfo.htm")
    public String jzgdInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/jzgdInfo";
    }
    
    /**
     * ��ת��������Ϣ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/scxxList.htm")
    public String scxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/scxxList";
    }
    
    /**
     * ������Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/scxxEdit.htm")
    public String ScxxEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("06");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/scxxEdit";
    }
    
    
    /**
     * �����б�����������Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryScxxList.json")
    public void queryScxxList(ModelMap model,String name,String regionId,String orgId,String qyzt,String industryId,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryScxxList("","",name, regionId, orgId, qyzt, industryId, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת��������Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/scxxInfo.htm")
    public String scxxInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/scxxInfo";
    }
    
    /**
     * ��ת������ҵ��Ϣ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/fwyList.htm")
    public String fwyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/fwyList";
    }
    
    /**
     * ����ҵ��Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/fwyEdit.htm")
    public String FwyEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("08");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/fwyEdit";
    }
    
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryFwyList.json")
    public void queryFwyList(ModelMap model,String name,String orgId, String qyzt, String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryFwyList("","",name, orgId, qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת������ҵ��Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/fwyInfo.htm")
    public String fwyInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/fwyInfo";
    }
    
    /**
     * ��ת����ʳҵ��Ϣ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/ysyList.htm")
    public String ysyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/ysyList";
    }
    
    /**
     * ��ʳҵ��Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/ysyEdit.htm")
    public String YsyEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("09");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/ysyEdit";
    }
    
    
    /**
     * �����б����ݣ���ʳҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYsyList.json")
    public void queryYsyList(ModelMap model,String name,String orgId, String qyzt,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryYsyList("","",name, orgId,qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת����ʳҵ��Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/ysyInfo.htm")
    public String ysyInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/ysyInfo";
    }
    
    /**
     * ��ת������ҵ��Ϣ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/zzyList.htm")
    public String zzyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/zzyList";
    }
    
    /**
     * ����ҵ��Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/zzyEdit.htm")
    public String ZzyEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("10");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/zzyEdit";
    }
    
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryZzyList.json")
    public void queryZzyList(ModelMap model,String name,String orgId, String qyzt,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryZzyList("","",name, orgId,qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת������ҵ��Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/zzyInfo.htm")
    public String zzyInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/zzyInfo";
    }
    
    /**
     * ��ת������ҵ��Ϣ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/ylyList.htm")
    public String ylyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/ylyList";
    }
    
    /**
     * ����ҵ��Ϣ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/ylyEdit.htm")
    public String ylyEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("11");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/ylyEdit";
    }
    
    
    /**
     * �����б����ݣ�����ҵ��Ϣ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryYlyList.json")
    public void queryYlyList(ModelMap model,String name,String orgId, String qyzt,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryYlyList("","",name, orgId,qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת������ҵ��Ϣ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/ylyInfo.htm")
    public String ylyInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/ylyInfo";
    }
    
    /**
     * ������ֳ��Ϣ��ѯҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xqyzList.htm")
    public String xqyzList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxgl/lawobj/xqyzList";
    }
    
    /**
     * ������ֳ ���ӱ༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/xqyzEdit.htm")
    public String xqyzEdit(ModelMap model,TDataLawobj lawobj,String jsxmid, String ismobile) {
    	try {
			if(lawobj!=null && StringUtils.isNotBlank(lawobj.getId())){
				lawobj = lawobjManager.getLawobjInfo(lawobj);
			}
			List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList("07");
			model.put("lawobj", lawobj);
			model.put("ismobile", StringUtils.endsWith(ismobile, "Y") ? "Y" : "N");
			Map<String,StringBuffer> map = lawobjManager.lawobjEditInnerHtml(list,lawobj);
			model.put("innerHtml", map.get("html").toString());
			model.put("innerJs", map.get("js").toString());
			if(StringUtils.isNotBlank(jsxmid)){//������Ŀת��ȾԴʹ��
				JsxmForm jsxmForm = lawobjManager.getJsxmInfo(jsxmid);
				model.put("jsxmForm", jsxmForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "app/data/xxgl/lawobj/xqyzEdit";
    }
    
    
    /**
     * �����б����� ������ֳ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryXqyzList.json")
    public void queryXqyzList(ModelMap model,String name,String regionid,String orgId,String qyzt,String isActive,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryXqyzList("","",name, regionid, orgId, qyzt, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
     * ��ת��������ֳ�鿴�������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xqyzInfo.htm")
    public String xqyzInfo(ModelMap model,TDataLawobj lawobj) {
    	model.put("lawobj", lawobj);
        return "app/data/xxgl/lawobj/xqyzInfo";
    }
    
    
    /**
     * ��ת����ȾԴ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_gywryList.htm")
    public String xxcx_gywryList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/xxcx/lawobj/xxcx_gywryList";
    }
    
    /**
     * ��ת��������Ŀ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_jsxmList.htm")
    public String xxcx_jsxmList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_jsxmList";
    }
    
    /**
     * ��ת��ҽԺ��Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_yyxxList.htm")
    public String xxcx_yyxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_yyxxList";
    }
    
    /**
     * ��ת����¯��Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_glxxList.htm")
    public String xxcx_glxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_glxxList";
    }
    
    /**
     * ��ת������������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_jzgdList.htm")
    public String xxcx_jzgdList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_jzgdList";
    }
    
    /**
     * ��ת��������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_scxxList.htm")
    public String xxcx_scxxList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_scxxList";
    }
    /**
     * ��ת��������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_fwyList.htm")
    public String xxcx_fwyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_fwyList";
    }/**
     * ��ת��������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_ysyList.htm")
    public String xxcx_ysyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_ysyList";
    }/**
     * ��ת��������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_zzyList.htm")
    public String xxcx_zzyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_zzyList";
    }/**
     * ��ת��������Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_ylyList.htm")
    public String xxcx_ylyList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_ylyList";
    }
    
    /**
     * ��ת��������ֳ��Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_xqyzList.htm")
    public String xxcx_xqyzList(ModelMap model,String title) {
    	model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
    	return "app/data/xxcx/lawobj/xxcx_xqyzList";
    }
   
    /**
     * ��ת��������ֳ��Ϣ��ѯҳ�棨��Ϣ��ѯ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawHistory.htm")
    public String lawHistory(ModelMap model,String id) {
    	model.put("id", id);
    	return "app/data/xxgl/lawobj/lawHistory";
    }
    
    /**
     * �����б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/queryLawHistoryList.json")
    public void queryLawHistoryList(ModelMap model,String id,String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjManager.queryLawHistoryList(id, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    
    /**
     * ��ת��ִ�������������
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawobjInfo.htm")
    public String lawobjInfo(ModelMap model,TDataLawobj lawobj) {
    	if(StringUtils.isNotBlank(lawobj.getId())){
    		lawobj = (TDataLawobj) lawobjManager.get(TDataLawobj.class, lawobj.getId());
    	}
    	model.put("lawobj", lawobj);
    	if("01".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/gywryInfo";
    	}else if("02".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/jsxmInfo";
    	}else if("03".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/yyxxInfo";
    	}else if("04".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/glxxInfo";
    	}else if("05".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/jzgdInfo";
    	}else if("06".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/scxxInfo";
    	}else if("07".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/xqyzInfo";
    	}else if("08".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/fwyInfo";
    	}else if("09".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/ysyInfo";
    	}else if("10".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/zzyInfo";
    	}else if("11".equals(lawobj.getLawobjtype())){
    		return "app/data/xxgl/lawobj/ylyInfo";
    	}else{
    		return null;
    	}
    }
    
    /**
	 * 
	 * �������ܣ�����ִ����������ѡ��ִ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/selectLawobj.json")
	public void choseeLawobj(ModelMap model,String year,String quarter, String lawobjtype, String name, String orgId, String page,String pageSize){
		try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = new FyWebResult();
    		if(StringUtils.isBlank(orgId)){
    			orgId = orgManager.getOrgByUserid(CtxUtil.getUserId()).getId();
    		}
    		//��ҵ��ȾԴ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.GYWRY.getCode())){
    			re = lawobjManager.queryGywryList(year,quarter,name, "", orgId, "0", "", "Y", page, pageSize);
    		}
    		//������Ŀ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.JSXM.getCode())){
    			re = lawobjManager.queryJsxmList(year,quarter,name, "", orgId, "", "", "Y", "",  page, pageSize);
    		}
    		//ҽԺ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.YY.getCode())){
    			re = lawobjManager.queryYyxxList(year,quarter,name, "", orgId, "0", "Y", page, pageSize);
    		}
    		//��¯
    		if(StringUtils.equals(lawobjtype, ZfdxLx.GL.getCode())){
    			re = lawobjManager.queryGlxxList(year,quarter,name, "", orgId, "0", "Y", page, pageSize);
    		}
    		//��������
    		if(StringUtils.equals(lawobjtype, ZfdxLx.JZGD.getCode())){
    			re = lawobjManager.queryJzgdList(year,quarter,name, "", orgId, "0", "Y", "",page, pageSize);
    		}
    		//����
    		if(StringUtils.equals(lawobjtype, ZfdxLx.SC.getCode())){
    			re = lawobjManager.queryScxxList(year,quarter,name, "", orgId, "0", "", "Y", page, pageSize);
    		}
    		//������ֳ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.XQYZ.getCode())){
    			re = lawobjManager.queryXqyzList(year,quarter,name, "", orgId, "0", "Y", page, pageSize);
    		}
    		//����ҵ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.FWY.getCode())){
    			re = lawobjManager.queryFwyList(year,quarter,name, orgId, "0", "Y", page, pageSize);
    		}
    		//��ʳҵ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.YSY.getCode())){
    			re = lawobjManager.queryYsyList(year,quarter,name, orgId, "0", "Y", page, pageSize);
    		}
    		//����ҵ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.YLY.getCode())){
    			re = lawobjManager.queryYlyList(year,quarter,name, orgId, "0", "Y", page, pageSize);
    		}
    		//��������ҵ
    		if(StringUtils.equals(lawobjtype, ZfdxLx.ZZY.getCode())){
    			re = lawobjManager.queryZzyList(year,quarter,name, orgId, "0", "Y", page, pageSize);
    		}
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * 
	 * �������ܣ�����ִ�������ۺ���Ϣ
	 * ���������
	 * ����ֵ��
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportLawobjList.json")
	public void exportLawobjList(ModelMap model,String name, String lawobjType, String regionId, String regionName,String orgId,String orgName,HttpServletResponse res) throws Exception{
		lawobjManager.exportLawobjList(name,lawobjType,regionId, regionName,orgId,orgName, res);
	}
}