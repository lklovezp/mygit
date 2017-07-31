/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.zx;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.manager.nodes.ZxNode;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.po.WorkLog;
import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.AbsJbpmController;

/**
 * ͨ������ִ�е�Controller ���ߣ�xb �������ڣ�Mar 12, 2015 ����������
 * 
 * 
 */
@Controller
public class CommWorkController extends AbsJbpmController {

	@Autowired
	private CommWorkManager commWorkManager;
	
	@Autowired
	private CommonManager commonManager;

	@Autowired
	protected ZxNode zxNode;
	
	@Autowired
    private IndexManager    indexManager;
	
	@Autowired
    protected Dao dao;
	
	/**
	 * ͨ�������ִ�н���-step1��׼��
	 * 
	 * @param applyId
	 *            ����Id
	 * @param model
	 */
	@RequestMapping(value = "/commworkzxZB.htm")
	public String commworkzxZB(String applyId, String taskId, ModelMap model) {
		try {
			Work work = workManager.get(applyId);
			
			// �Ǽ���
			if (StringUtils.isNotBlank(work.getDjrId())) {
				TSysUser djrObj = (TSysUser) this.userManager.get(
						TSysUser.class, work.getDjrId());
				work.setDjrName(djrObj.getName());
			} else {
				work.setDjrName("");
			}
			model.addAttribute("work", work);
			model.addAttribute("taskId", taskId);

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
			model.put("rwlxIds", rwlxIds);
			model.put("rwlxNames", rwlxNames);
			
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/commworkzxZB";
	}

	/**
	 * ��֤����ҳ����ת��true��ר�false��������
	 * 
	 * @param applyId
	 * @param model
	 */
	@RequestMapping(value = "/showBlPage.json", produces = "application/json")
	public void showBlPage(String applyId, ModelMap model) {
		try {
			ResultBean rb = commWorkManager.showBlPage(applyId);
			model.addAttribute("state", rb.getResult());
			model.addAttribute("msg", rb.getMsg());
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
		}
	}

	/**
	 * ͨ�������ִ�н���-step2������
	 * 
	 * @param applyId
	 *            ����Id
	 * @param model
	 */
	@RequestMapping(value = "/commworkzxBL.htm")
	public String commworkzxBL(String applyId, String taskId, String tasktypeId, String oper,
			ModelMap model, BlMainForm blMainForm,XfbjdForm xfbjdForm) {
		try {
			String sysVer = indexManager.sysVer;
			Work work = workManager.get(applyId);
			model.addAttribute("work", work);
			if("Y".equals(work.getBlrsfbc())){
				model.addAttribute("sfsb", "����ϱ�");
			}else{
				model.addAttribute("sfsb", "���");
			}
			model.addAttribute("taskId", taskId);
			model.addAttribute("oper", oper);
			//�������߰汾��ʶ��ҳ�棬���Կ��ư�����ťֱ����ʾ�������
			model.addAttribute("sysVer", sysVer);
			//������б�
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			
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
			if(!"24".equals(rwlxIds)){
				List<Map<String, String>> rows = commWorkManager.zfdxTableData(applyId);
				if(rows!=null && rows.size()>0){
					model.addAttribute("zfdxInfo", "Y");	
					blMainForm = commWorkManager.getBlMainFormData(applyId);
				}else{
					model.addAttribute("zfdxInfo", "N");	//��ִ������
				}
				model.addAttribute("blMainForm", blMainForm);
				if("13".equals(rwlxIds)){
					xfbjdForm = commWorkManager.getXfbjdform(applyId);
					if(StringUtils.isNotBlank(xfbjdForm.getSlsj())){
						xfbjdForm.setSlsj(xfbjdForm.getSlsj().substring(0, 10));
					}else{
						xfbjdForm.setSlsj(DateUtil.getDateTime("yyyy-MM-dd", work.getEndTime()));
					}
					model.addAttribute("xfbjdForm", xfbjdForm);
				}
			}else{
				//�ճ��칫Form����
				blMainForm =commWorkManager.FindRcbg(applyId, "24");
				model.addAttribute("blMainForm", blMainForm);
			}		
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/commworkzxBL";
	}

	/**
	 * �������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return
	 */
	@RequestMapping(value = "/saveWorkzxBL.json")
	public void saveWorkzxBL(String applyId, ModelMap model,BlMainForm blMainForm) {
		try {
			String xftsId = commWorkManager.saveWorkzxBL(applyId, blMainForm);
			model.addAttribute("xftsId", xftsId);
			Work work = workManager.get(applyId);
			model.addAttribute("xfdjbId",work.getXfdjbId());//�ŷõǼǱ�id
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���������Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}

	/**
	 * ͨ�������ִ�н���-step3������
	 * 
	 * @param applyId
	 *            ����Id
	 * @param model
	 */
	@RequestMapping(value = "/commworkzxBG.htm")
	public String commworkzxBG(String applyId, String taskId, String tasktypeId ,String oper,
			ModelMap model, BlMainForm blMainForm) {
		try {
			//�������߰��жϱ�ʶ���������ͱ���ҳ����жϣ�
        	String biaoshi = indexManager.sysVer;
			Work work = workManager.get(applyId);
			model.addAttribute("work", work);
			model.addAttribute("taskId", taskId);
			model.addAttribute("oper", oper);
			model.addAttribute("sysVer", biaoshi);

			/*
			 * //String nextOperName=zxNode.getNextOperName(applyId, taskId);
			 * String nextOperName = commWorkManager.getFirstShrName(applyId);
			 * 
			 * model.put("nextOperName", nextOperName);
			 */
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
			if(!"24".equals(rwlxIds)){
				blMainForm = commWorkManager.getBlMainFormData(applyId);

				model.addAttribute("blMainForm", blMainForm);
			}else{
				//�ճ��칫Form����
				blMainForm =commWorkManager.FindRcbg(applyId, "24");
				model.addAttribute("blMainForm", blMainForm);
			}
			if("13".equals(rwlxIds)){
				//�ж������Ƿ�����˻����ύ����
				StringBuffer sql = new StringBuffer();
				sql.append(" from WorkLog where work.id =? or (work.sjid = ? and czrId not in (select id from TSysUser where areaId='a0000000000000000000000000000000')) order by czsj asc, operateType ");
				List<WorkLog> list = this.dao.find(sql.toString(),applyId,applyId);
				String yth = "";
				for(int i=0; i<list.size();i++){
					if("08".equals(list.get(i).getWorkSate())){
						yth = "0";
					}
				}
				if(yth != "0"){
					//������worklog�Ĳ�ѯ���ж��ǲ����Ѿ��˻����������ٴΰ�����������ж����ɷ�
					XfbjdForm xfbjdForm = commWorkManager.getXfbjdform(applyId);
					if("999".equals(xfbjdForm.getBjqk())){
						xfbjdForm.setSlsj(work.getEndTime().toString());
						model.addAttribute("slsj", xfbjdForm.getSlsj());
						model.addAttribute("bjqk", xfbjdForm.getBjqk());
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/commworkzxBG";
	}

	/**
	 * ��֤�Ƿ�����ϱ�
	 * 
	 * @param workId
	 * @param model
	 */
	@RequestMapping(value = "/comm_shangbao.json", produces = "application/json")
	public void commShangbao(String workId, ModelMap model) {
		try {
			ResultBean rb = workManager.allowShangbao_comm(workId);
			model.addAttribute("state", rb.getResult());
			model.addAttribute("msg", rb.getMsg());
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
		}
	}

	/**
	 * ִ���������ͱ���
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/zfdxTypeSave.json", produces = "application/json")
	public void zfdxTypeSave(String applyId, String zfdxType,String rwlxIds, ModelMap model) {
		try {
			commWorkManager.saveZfdxTypeOnChange(applyId, zfdxType, rwlxIds);

			commWorkManager.saveZfdxType(applyId, zfdxType);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("����ִ���������ʹ���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ������������
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/taskTypeSaveMultiple.json", produces = "application/json")
	public void taskTypeSaveMultiple(String applyId, String checkedNodesIds,
			ModelMap model) {
		try {
			TSysUser curUser = CtxUtil.getCurUser();
			commWorkManager.saveTaskTypeMultiple(applyId, checkedNodesIds,
					curUser);

			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("����ִ���������ʹ���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getTaskTypeMultiple.json", produces = "application/json")
	public void getTaskTypeMultiple(String applyId, ModelMap model) {
		try {
			// �����ids,codes����ҳ����ȥ
			// ���������б�
			List<Map<String, String>> rwlxlistMap = commWorkManager
					.getTaskTypeByTaskId(applyId);
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
			model.put("rwlxIds", rwlxIds);
			model.put("rwlxNames", rwlxNames);

		} catch (Exception e) {
			log.error("��ѯִ���������ʹ���", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * �������ܣ�ִ������table��׼����
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/zfdxTable.json", produces = "application/json")
	public void zfdxTable(String applyId, ModelMap model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, String>> listMap = commWorkManager
					.zfdxTableData(applyId);
			map.put("total", listMap.size());
			map.put("rows", listMap);
			JsonResultUtil.show(model, map);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 
	 * �������ܣ��������������������������ִ����������+ִ�����������������������ѡ��
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/taskTypeTreeComboByTaskId.json", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx,ModelMap model) {
		return commWorkManager.taskTypeTreeComboByTaskId(applyId, zfdxlx);
	}
	
	/**
	 * 
	 * �������ܣ������������������˵��ŷ�Ͷ�ߣ����������ִ����������+ִ�����������������������ѡ��
	 * 
	 * �����������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/taskTypeTreeComboByTaskIdWithoutXf.json", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx,ModelMap model) {
		return commWorkManager.taskTypeTreeComboByTaskIdWithoutXf(applyId, zfdxlx);
	}

	/**
	 * 
	 * �������ܣ���Ա�滮table��׼����
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/ryghTable.json", produces = "application/json")
	public void ryghTable(String applyId, ModelMap model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, String>> listMap = commWorkManager
					.ryghTableData(applyId);
			map.put("total", listMap.size());
			map.put("rows", listMap);
			JsonResultUtil.show(model, map);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 
	 * �������ܣ�Э����Աtable
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/xbryTable.json", produces = "application/json")
	public void xbryTable(String applyId, ModelMap model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, String>> listMap = commWorkManager
					.xbryTableData(applyId);
			map.put("total", listMap.size());
			map.put("rows", listMap);
			JsonResultUtil.show(model, map);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * ��Ա�滮-ɾ��
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/delrygh.json", produces = "application/json")
	public void delrygh(String applyId, String id, ModelMap model) {
		try {
			commWorkManager.saveDelrygh(applyId, id);
			//������б�
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ����Ա�滮����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * �������ܣ���Ա�滮�����򣨰�����
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/ryghCombo.json")
	@ResponseBody
	public List<Combobox> ryghCombo(String applyId, ModelMap model) {
		return commWorkManager.ryghCombo(applyId);
	}

	/**
	 * ��Ա����
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/rySave.json", produces = "application/json")
	public void rySave(String applyId, String ryid, String type, ModelMap model) {
		try {
			commWorkManager.saveRy(applyId, ryid, type);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("������Ա����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ��Ա����(���)
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/rySaveMul.json", produces = "application/json")
	public void rySaveMul(String applyId, String ryid, String type,
			ModelMap model) {
		try {
			commWorkManager.saveRyMul(applyId, ryid, type);
			 //������б�
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("������Ա����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ��ȡ����ѯ�ʱ�¼
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getKcxwblFile.json", produces = "application/json")
	public void getKcxwblFile(String applyId, String bllx, ModelMap model) {
		try {
			Map<String, String> kcxwblFileMap = new HashMap<String, String>();
			kcxwblFileMap = commWorkManager.getKcxwblFile(applyId, bllx);
			model.put("kcxwblFileMap", kcxwblFileMap);
		} catch (Exception e) {
			log.error("��ѯ����ѯ�ʱ�¼����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ͨ�û�ȡ��������Ϣ
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getCommonFile.json", produces = "application/json")
	public void getCommonFile(String applyId, String fileTypeEnumName,
			ModelMap model) {
		try {
			Map<String, String> commonFileMap = new HashMap<String, String>();
			commonFileMap = commWorkManager.getCommonFile(applyId,
					fileTypeEnumName);

			model.put("commonFileMap", commonFileMap);

		} catch (Exception e) {
			log.error("��ѯ��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ����¼������Ϣ
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getJcblFile.json", produces = "application/json")
	public void getJcblFile(String applyId, String fileTypeEnumName,String moreCheckFiletype,String tasktypeCode,
			ModelMap model) {
		try {
			List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
			if(StringUtils.equals(tasktypeCode, "10")){//�ֳ����
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.RCJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "RCJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "RCJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.RCJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "RCJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "RCJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "11")){//��Ⱥ˲�
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.NDHCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "NDHCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "NDHCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.NDHCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "NDHCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "NDHCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "12")){//�󶽲�
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.HDCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "HDCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "HDCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.HDCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "HDCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "HDCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "13")){//�ŷ�Ͷ��
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.XFTSJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "XFTSJCJL");
					rowsList.get(0).put("moreCheckFiletype", "XFTSMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.XFTSMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "XFTSJCJL");
					rowsList.get(k).put("moreCheckFiletype", "XFTSJCJL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "14")){//��������֤���
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.PWXKZJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "PWXKZJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "PWXKZJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.PWXKZJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "PWXKZJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "PWXKZJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "15")){//ר���ж�
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.ZXXDZRWJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(0).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.ZXXDZRWMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "ZXXDZRWJCJL");
					rowsList.get(k).put("moreCheckFiletype", "ZXXDZRWMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "17")){//��������
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.XQZLJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "XQZLJCJL");
					rowsList.get(0).put("moreCheckFiletype", "XQZLMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.XQZLMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "XQZLJCJL");
					rowsList.get(k).put("moreCheckFiletype", "XQZLMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "18")){//���ټ��
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.GZJCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "GZJCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "GZJCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.GZJCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "GZJCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "GZJCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "20")){//Σ�շ���
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WXFWJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "WXFWJCJL");
					rowsList.get(0).put("moreCheckFiletype", "WXFWMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.WXFWMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "WXFWJCJL");
					rowsList.get(k).put("moreCheckFiletype", "WXFWMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "22")){//���䰲ȫ
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.FSAQJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "FSAQJCJL");
					rowsList.get(0).put("moreCheckFiletype", "FSAQMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.FSAQMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "FSAQJCJL");
					rowsList.get(k).put("moreCheckFiletype", "FSAQMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}else if(StringUtils.equals(tasktypeCode, "23")){//��Ⱦ�¹��ֳ�����
				FyWebResult re = commonManager.queryFileList(applyId, "N", FileTypeEnums.WRSGXCDCJCJL.getCode(), "1", null);
				List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
				rowsList = re.getRows();
				if (rowsList.size() > 0) {
					rowsList.get(0).put("fileTypeEnumName", "WRSGXCDCJCJL");
					rowsList.get(0).put("moreCheckFiletype", "WRSGXCDCMOREJCBL");
					rowsList.get(0).put("type", "0");
					rowsList.get(0).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(0));
				}
				re = commWorkManager.queryJcblFileList(applyId, "N", FileTypeEnums.WRSGXCDCMOREJCBL.getCode(), "1", null);
				rowsList = re.getRows();
				if(rowsList!=null && rowsList.size()>0)
				for(int k=0;k<rowsList.size();k++){
					rowsList.get(k).put("fileTypeEnumName", "WRSGXCDCJCJL");
					rowsList.get(k).put("moreCheckFiletype", "WRSGXCDCMOREJCBL");
					rowsList.get(k).put("type", "1");
					rowsList.get(k).put("tasktypeCode", tasktypeCode);
					jcjlFileMap.add(rowsList.get(k));
				}
			}
			model.put("jcjlFileMap", jcjlFileMap);

		} catch (Exception e) {
			log.error("��ѯ��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��μ��ʱͨ������id�õ�������
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getJcqk.json", produces = "application/json")
	public void getJcqk(String fileId,ModelMap model) {
		try {
			if(commWorkManager.getMoreCheck(fileId)!=null){
				model.put("jcqk",commWorkManager.getMoreCheck(fileId).getContent());
			}else{
				model.put("jcqk","");
			}
		} catch (Exception e) {
			log.error("��ѯ��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	// ////////////////////////////////////��������У��start/////////////////////////////////////////
	/**
	 * ��֤"׼��"��true��ͨ����false����ͨ����
	 * 
	 * @param applyId
	 * @param model
	 */
	@RequestMapping(value = "/checkBlZB.json", produces = "application/json")
	public void checkBlZB(String applyId, ModelMap model) {
		try {
			ResultBean rb = commWorkManager.checkBlZB(applyId);
			model.addAttribute("state", rb.getResult());
			model.addAttribute("msg", rb.getMsg());
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
		}
	}

	/**
	 * ��֤"����"��true��ͨ����false����ͨ����
	 * 
	 * @param applyId
	 * @param model
	 */
	@RequestMapping(value = "/checkBlBL.json", produces = "application/json")
	public void checkBlBL(String applyId,String zfdxInfo, ModelMap model) {
		try {
			ResultBean rb = commWorkManager.checkBlBL(applyId,zfdxInfo);
			model.addAttribute("state", rb.getResult());
			model.addAttribute("msg", rb.getMsg());
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
		}
	}

	// ////////////////////////////////////��������У��end/////////////////////////////////////////

	/**
	 * 
	 * �������ܣ���ȡ��������������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryBlFileTypeCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo) {
		return commWorkManager.queryBlFileTypeCombo(rwlx,zfdxInfo);
	}

	/**
	 * 
	 * �������ܣ���ȡ��������������������б���ר���ж�������
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryBlFileTypeComboZXXDZRW.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeComboZXXDZRW() {
		return commWorkManager.queryBlFileTypeComboZXXDZRW();
	}

	/**
	 * 
	 * �������ܣ���ת�������ϴ�����
	 * 
	 * ���������applyId������id
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getDjMessage.htm")
	public String getDjMessage(ModelMap model, String applyId) {
		model.addAttribute("applyId", applyId);
		return "app/work/zx/getDjMessage";
	}

	/**
	 * 
	 * �������ܣ����浥��ִ����Ϣ
	 * 
	 * ���������applyId-����id��
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveDjMessage.htm")
	public void saveDjMessage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, String applyId,
			@RequestParam(value = "file", required = true) MultipartFile file) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			commWorkManager.saveDjMessage(applyId, file);
			model.remove("versionForm");
			writer.print("{\"state\":true,\"msg\":\"�ϴ��ɹ�\"}");
		} catch (Exception e) {
			writer
					.print("{\"state\":false,\"msg\":\"" + e.getMessage()
							+ "\"}");
		}
	}

	/**
	 * ��ȡ������
	 * 
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param jcjl
	 */
	@RequestMapping(value = "/jcjl.json", produces = "application/json")
	public void jcjl(ModelMap model, String taskid, String tasktype) {
		try {
			String jcjl = commWorkManager.jcjl(taskid, tasktype);
			model.addAttribute("jcjl", StringUtil.isBlank(jcjl) ? "" : jcjl);
			JsonResultUtil.suncess(model, "��ѯ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�ճ��칫��ע
	 * 
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param desc
	 */
	@RequestMapping(value = "/rcbgDesc.json", produces = "application/json")
	public void rcbgDesc(ModelMap model, String taskid, String tasktype) {
		try {
			//�ճ��칫Form����
			BlMainForm blMainForm= commWorkManager.FindRcbg(taskid, tasktype);
			String desc=blMainForm.getBlRcbgForm().getDesc();
			model.addAttribute("desc", StringUtil.isBlank(desc) ? "" : desc);
			JsonResultUtil.suncess(model, "��ѯ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������
	 * 
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param jcjl
	 */
	@RequestMapping(value = "/saveJcjl.json", produces = "application/json")
	public void saveJcjl(ModelMap model, String taskid, String tasktype,
			String jcjl) {
		try {
			commWorkManager.saveJcjl(taskid, tasktype, jcjl);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * �������ܣ�����ģ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveDownJcbgmb")
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType,
			HttpServletResponse res) {
		commWorkManager.saveDownJcbgmb(jcmbId, applyId, taskType, res);
	}

}