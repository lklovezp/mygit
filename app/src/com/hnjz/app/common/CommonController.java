package com.hnjz.app.common;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.data.enums.JcxEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * ���ߣ�renzhengquan �������ڣ�2015-3-9 ���������� �������ܿ��Ʋ�
 * 
 */
@Controller
public class CommonController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(CommonController.class);
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
    private IndexManager     indexManager;
	/**
	 * 
	 * �������ܣ��������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/kzlxList.json")
	@ResponseBody
	public List<Combobox> kzlxList(ModelMap model) {
		return commonManager.queryKzlxList();
	}

	
	/**
	 * 
	 * �������ܣ��������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/typetree.json")
	@ResponseBody
	public List<Combobox> typeList(ModelMap model) {
		return commonManager.queryLawobjtypeTree();
	}
	/**
	 * 
	 * �������ܣ���ȡ�ֵ������������б� �� ͨ�ýӿڣ�
	 * 
	 * ���������type:�ֵ�����
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/dicList.json")
	@ResponseBody
	public List<Combobox> dicList(ModelMap model, String type) {
		return commonManager.queryTSysDicList(type);
	}

	/**
	 * 
	 * �������ܣ�״̬����Ч��Ч�������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/ztList.json")
	@ResponseBody
	public List<Combobox> ztList(ModelMap model) {
		return commonManager.queryZtList();
	}
	
	/**
	 * 
	 * �������ܣ���ҵ״̬����Ӫ�С��ѹرա���ͣ���������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/qyztList.json")
	@ResponseBody
	public List<Combobox> qyztList(ModelMap model) {
		return commonManager.queryQyztList();
	}
	
	/**
	 * 
	 * �������ܣ�״̬(�Ƿ�)�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/sfList.json")
	@ResponseBody
	public List<Combobox> sfList(ModelMap model) {
		return commonManager.querySfList();
	}

	/**
	 * 
	 * �������ܣ���ҵ�����б�
	 * 
	 * ���������lawobjType-ִ����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/industryList.json")
	@ResponseBody
	public List<Combobox> industryList(ModelMap model, String lawobjType) {
		return commonManager.queryIndustryList(lawobjType);
	}

	/**
	 * 
	 * �������ܣ����������б�
	 * 
	 * ����������������ͱ�ţ�֧��ģ����ѯ
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/fjlxList.json")
	@ResponseBody
	public List<Combobox> fjlxList(ModelMap model, String enumName) {
		return commonManager.queryFjlxList(enumName);
	}

	/**
	 * 
	 * �������ܣ�ִ������������������
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/regionTree.json")
	@ResponseBody
	public List<ComboboxTree> regionTree(ModelMap model) {
		return commonManager.queryRegionTree();
	}
    
	/**
	 * 
	 * �������ܣ�������
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/wgTree.json")
	@ResponseBody
	public List<Combobox> wgTree(ModelMap model) {
		return commonManager.queryWgTree();
	}
	/**
	 * 
	 * �������ܣ�ִ������������ܲ�����
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/orgTree.json")
	@ResponseBody
	public List<ComboboxTree> orgTree(ModelMap model) {
		return commonManager.queryOrgTree();
	}
	
	/**
	 * 
	 * �������ܣ�Υ������������
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/illegalTypeList.json")
	@ResponseBody
	public List<ComboboxTree> illegalTypeList(ModelMap model, String ids) {
		return commonManager.queryIllegalTypeList(ids);
	}
	
	@RequestMapping(value = "/illegalTypeByTasktypeList.json")
	@ResponseBody
	public List<ComboboxTree> illegalTypeByTasktypeList(ModelMap model, String ids,String tasktype) {
		return commonManager.queryIllegalTypeByTasktypeList(ids,tasktype);
	}

	/**
	 * 
	 * �������ܣ���������������
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/taskTypeTreeCombo.json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeCombo(ModelMap model, String oper, String markId) {
		return commonManager.queryTaskTypePubTree(markId);
	}
	
	/**
	 * ��ѯ��ǰ�û��µ�����
	 */
	@RequestMapping(value = "/queryAreaCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryArea() {
		try {
			return commonManager.queryAreaCombo(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
	/**
	 * ��ѯ��ǰ�û������µ�����
	 */
	@RequestMapping(value = "/queryAreaComboWithCur.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaComboWithCur() {
		try {
			return commonManager.queryAreaComboWithCur(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
	
	/**
	 * ��ѯ��ǰ�õ�����,�����admin��ѯ���е�����
	 */
	@RequestMapping(value = "/queryAreaCur.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaCur() {
		try {
			return commonManager.queryAreaCur(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
	
	/**
	 * 
	 * �������ܣ���ȡ����״̬�����б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryRwztCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryRwztCombo() {
		return commonManager.queryRwztCombo();
	}

	/**
	 * 
	 * �������ܣ��򿪹����ϴ�ҳ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/uploadPage.htm")
	public String uploadPage(ModelMap model, FileForm frm, HttpServletRequest request) {
		String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
		frm.setExtension(commonManager.getExtension(code));
		
		String JSESSIONID = request.getSession().getId();
		model.addAttribute("JSESSIONID", JSESSIONID);
		return "common/upload";
	}
	
	/**
	 * 
	 * �������ܣ��򿪵������ϴ�ҳ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/singleUploadPage.htm")
	public String singleUploadPage(ModelMap model, FileForm frm) {
		String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
		String ext = commonManager.getExtension(code);
		String e = "";
		for (int i = 0; i < ext.split(";").length; i++) {
			if (i > 0){
				e += ";";
			}
			e += ext.split(";")[i].substring(2, ext.split(";")[i].length());
		}
		frm.setExtension(e);
		return "common/singleUpload";
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadfiles")
	public void fileupload(ModelMap model, @RequestParam(value = "upload_file", required = true) MultipartFile multipartFile,
			HttpServletResponse httpServletResponse, HttpServletRequest request, PrintWriter writer) {
		try {
			commonManager.uploadFile(multipartFile, request);
			writer.print("{\"resultcode\":0,\"msg\":\"�ϴ��ɹ���\"}");
		} catch (AppException e) {
			e.printStackTrace();
			writer.print("{\"resultcode\":1,\"msg\":\"�ϴ�ʧ�ܡ�\"}");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadSingleFile")
	public void uploadSingleFile(ModelMap model, @RequestParam(value = "file", required = true) MultipartFile multipartFile,
			HttpServletResponse response, HttpServletRequest request) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			writer = response.getWriter();
			commonManager.uploadSingleFile(multipartFile, request);
			writer.print("{\"state\":\"true\",\"msg\":\"�ϴ��ɹ���\"}");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("{\"state\":\"false\",\"msg\":\"�ϴ�ʧ�ܡ�\"}");
		}
	}
	
	/**
	 * 
	 * �������ܣ��򿪹��õ���ҳ��
	 * 
	 * ���������ִ����������
	 * 
	 * ����ֵ��ִ����������
	 */
	@RequestMapping(value = "/importPage.htm")
	public String importPage(ModelMap model, String lawObjType) {
		model.addAttribute("lawObjType", lawObjType);
		return "common/import";
	}
	
	/**
	 * 
	 * �������ܣ�����ģ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/downTemplate")
	public void downTemplate(String lawObjType, HttpServletResponse res) {
		commonManager.downTemplate(lawObjType, res);
	}

	/**
	 * 
	 * �������ܣ�����ģ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/importTemplate.htm")
	public void importTemplate(ModelMap model, String lawObjType, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			commonManager.importTemplate(lawObjType, file);
			
			writer.print("{\"state\":true,\"msg\":\"�ϴ��ɹ�\"}");
		} catch (Exception e) {
			log.error("������Ϣ����", e);
			writer.print("{\"state\":false,\"msg\":\"" + e.getMessage() + "\"}");
		}
	}
	
	
	/**
	 * �ļ�����ͨ�÷���
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(String id, HttpServletResponse res) {
		try {
			commonManager.downloadFile(id, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ļ�������������Ϣͨ�÷���
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/exportFile")
	public void exportFile(String id, String applyId, HttpServletResponse res) {
		try {
			commonManager.exportFile(id, applyId, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ļ�ɾ��ͨ�÷���
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/removeFile.json")
	public void removeFile(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ������¼
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/removeJcbl.json")
	public void removeJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//ɾ���ļ�
			//ɾ��
			TBizMoreCheck mc = commWorkManager.getMoreCheck(id);
			if(mc!=null){
				commWorkManager.removeMoreCheck(mc);
			}
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ɾ��������������¼
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/removeZxzzJcbl.json")
	public void removeZxzzJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//ɾ���ļ�
			//ɾ��
			TBizBlmbcs bc = commWorkManager.getBlmbc(id);
			if(bc!=null){
				commWorkManager.removeBlmbc(bc);
			}
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ͨ��pidɾ���ļ� ͨ�÷���
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/removeFileByPid")
	public void removeFileByPid(String pid) {
		try {
			commonManager.removeFileByPid(pid);
		} catch (Exception e) {
			log.error("��������", e);
		}
	}
	
	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid �� ����id
	 * 	canDel ���Ƿ���ɾ����ť
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryFileList.json")
    public void queryFileList(ModelMap model,String pid, String canDel,String fileType,String tableId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileList(pid, canDel,fileType,tableId, page, rows);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
	
	/**
	 * 
	 * �������ܣ�ͨ��pid��ѯ�����б�
	
	 * ���������pid �� ����id
	 * 	canDel ���Ƿ���ɾ����ť
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryFileListMulfileType.json")
    public void queryFileListMulfileType(ModelMap model,String pid, String canDel,String fileType,String tableId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileListMulfileType(pid, canDel,fileType,tableId, page, rows);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("��ѯ������", e);
    	}
    }
	
	/**
	 * 
	 * �������ܣ�ѡ��ִ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/selectLawobj.htm")
	public String choseeLawobj(ModelMap model,String lawobjtype,String rwid,String rwlxIds,String fzbs){
		String biaoshi = indexManager.sysVer;
		model.put("rwid", rwid);
		List<Map<String, String>> rwlxlistMap = commWorkManager
				.getTaskTypeByTaskId(rwid);
		model.put("rwlxlistMap", rwlxlistMap);
		String rwlxId = rwlxIds;
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
		if(StringUtils.isNotBlank(rwlxIds)){
			model.put("rwlxIds", rwlxIds);
		}else{
			model.put("rwlxIds", rwlxId);
		}
		
		model.put("lawobjtype", lawobjtype);
		if(StringUtils.isNotBlank(fzbs)){
			model.put("ymbiaoshi", fzbs);
		}
		//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
		if("1".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeGywry";
			}else{
				return "app/data/xxgl/lawobj/choseeGywry";
			}
		}else if("2".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeJsxm";
			}else{
				return "app/data/xxgl/lawobj/choseeJsxm";
			}
		}else if("3".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYyxx";
			}else{
				return "app/data/xxgl/lawobj/choseeYyxx";
			}
		}else if("4".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeGlxx";
			}else{
				return "app/data/xxgl/lawobj/choseeGlxx";
			}
		}else if("5".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeJzgd";
			}else{
				return "app/data/xxgl/lawobj/choseeJzgd";
			}
		}else if("6".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeScxx";
			}else{
				return "app/data/xxgl/lawobj/choseeScxx";
			}
		}else if("7".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeXqyz";
			}else{
				return "app/data/xxgl/lawobj/choseeXqyz";
			}
		}else if("8".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeFwy";
			}else{
				return "app/data/xxgl/lawobj/choseeFwy";
			}
		}else if("9".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYsy";
			}else{
				return "app/data/xxgl/lawobj/choseeYsy";
			}
		}else if("10".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeZzy";
			}else{
				return "app/data/xxgl/lawobj/choseeZzy";
			}
		}else if("11".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYly";
			}else{
				return "app/data/xxgl/lawobj/choseeYly";
			}
		}
		return null;
	}
	
	/**
	 * 
	 * �������ܣ�����������ѡ���ִ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveChoseeLawobj.json")
	public void saveChoseeLawobj(ModelMap model,String rwid,String lawobjtype,String rows){
		try {
			JSONArray array = new JSONArray(rows);
			String result = commonManager.saveChoseeLawobj(rwid, lawobjtype, array, CtxUtil.getCurUser());
			if(result.equals("success")){
				JsonResultUtil.suncess(model, "����ɹ���");
			}else{
				JsonResultUtil.fail(model, result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * �������ܣ�����������ѡ���ִ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/querySelectLawobjList.json")
	@ResponseBody
	public List<TaskandlawobjForm> querySelectLawobjList(ModelMap model,String rwid,String lawobjtype){
		return commonManager.querySelectLawobjList(rwid,lawobjtype);
	}
	
	/**
	 * 
	 * �������ܣ���ת��ѡ��ִ��������ҳ��
	
	 * ���������lawobjtype ִ����������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/selectLawobjPage.htm")
	public String selectLawobjPage(ModelMap model, String lawobjtype){
		model.put("lawobjtype", lawobjtype);
		if("01".equals(lawobjtype)){
			return "common/zdpf_choseeGywry";
		}else if("02".equals(lawobjtype)){
			return "common/zdpf_choseeJsxm";
		}else if("03".equals(lawobjtype)){
			return "common/zdpf_choseeYyxx";
		}else if("04".equals(lawobjtype)){
			return "common/zdpf_choseeGlxx";
		}else if("05".equals(lawobjtype)){
			return "common/zdpf_choseeJzgd";
		}else if("06".equals(lawobjtype)){
			return "common/zdpf_choseeScxx";
		}else if("07".equals(lawobjtype)){
			return "common/zdpf_choseeXqyz";
		}else if("08".equals(lawobjtype)){
			return "common/zdpf_choseeFwy";
		}else if("09".equals(lawobjtype)){
			return "common/zdpf_choseeYsy";
		}else if("10".equals(lawobjtype)){
			return "common/zdpf_choseeZzy";
		}else if("11".equals(lawobjtype)){
			return "common/zdpf_choseeYly";
		}
		return null;
	}
	
	/**
	 * 
	 * �������ܣ�������־�����������б�
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryWorkLogTypeList.json")
	@ResponseBody
	public List<Combobox> queryWorkLogTypeList(){
		return WorkLogType.getTimeTypes();
	}
	
	
	/**
	 * �ļ�����ͨ�÷���
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/downZipFile")
	public void downZipFile(String id, HttpServletResponse res) {
		try {
			List<String> ids = new ArrayList<String>();
			for (int i = 0; i < id.split(",").length; i++) {
				ids.add(id.split(",")[i]);
			}
			commonManager.downZipFile(ids, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ļ�Ԥ��
	 * 
	 * @param id:�ļ�id
	 */
	@RequestMapping(value = "/preview.htm")
	public String preview(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.preview(id, request);
			model.addAttribute("content", data.get("content"));
			model.addAttribute("ext", data.get("ext"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/preview";
	}
	
	/**
	 * 
	 * �������ܣ������ö���б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getJcxEnumList.json")
	@ResponseBody
	public List<Combobox> getJcxEnumList(ModelMap model) {
		return JcxEnum.getJcxEnumList();
	}
	
	/**
	 * 
	 * �������ܣ������ö���б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getJcmbByTaskType.json")
	@ResponseBody
	public List<Combobox> getJcmbByTaskType(ModelMap model, String tasktype) {
		return commonManager.getJcmbByTaskType(tasktype);
	}
	
	/**
	 * 
	 * �������ܣ���γ�ȿؼ�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/jwdPubWindow.htm")
	public String jwdPubWindow() {
		return "common/jwdPubWindow";
	}
	/**
	 * 
	 * �������ܣ���ȡִ���������������б� �� ͨ�ýӿڣ�
	 * 
	 * ���������type:�ֵ�����
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/zfdxmcList.json")
	@ResponseBody
	public List<Combobox> zfdxmcList(ModelMap model) {
		return commonManager.queryzfdxmcList();
	}
	
	/**
	 * ͼƬԤ��
	 *
	 * @param id:�ļ�id
	 */
	
	@RequestMapping(value = "/imgView.htm")
	public String imgView(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.imgView(id,request);
			model.addAttribute("filePath",data.get("filePath"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/imgView";
	}

}