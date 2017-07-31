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
 * 通用任务执行的Controller 作者：xb 生成日期：Mar 12, 2015 功能描述：
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
	 * 通用任务的执行界面-step1：准备
	 * 
	 * @param applyId
	 *            任务Id
	 * @param model
	 */
	@RequestMapping(value = "/commworkzxZB.htm")
	public String commworkzxZB(String applyId, String taskId, ModelMap model) {
		try {
			Work work = workManager.get(applyId);
			
			// 登记人
			if (StringUtils.isNotBlank(work.getDjrId())) {
				TSysUser djrObj = (TSysUser) this.userManager.get(
						TSysUser.class, work.getDjrId());
				work.setDjrName(djrObj.getName());
			} else {
				work.setDjrName("");
			}
			model.addAttribute("work", work);
			model.addAttribute("taskId", taskId);

			// 任务类型列表
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
	 * 验证办理页面跳转【true、专项；false、其他】
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
			JsonResultUtil.fail(model, "操作失败！");
		}
	}

	/**
	 * 通用任务的执行界面-step2：办理
	 * 
	 * @param applyId
	 *            任务Id
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
				model.addAttribute("sfsb", "办结上报");
			}else{
				model.addAttribute("sfsb", "办结");
			}
			model.addAttribute("taskId", taskId);
			model.addAttribute("oper", oper);
			//添加离线版本标识到页面，可以控制办理后按钮直接显示打包功能
			model.addAttribute("sysVer", sysVer);
			//检查人列表
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			
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
			model.addAttribute("rwlxIds", rwlxIds);
			if(!"24".equals(rwlxIds)){
				List<Map<String, String>> rows = commWorkManager.zfdxTableData(applyId);
				if(rows!=null && rows.size()>0){
					model.addAttribute("zfdxInfo", "Y");	
					blMainForm = commWorkManager.getBlMainFormData(applyId);
				}else{
					model.addAttribute("zfdxInfo", "N");	//无执法对象
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
				//日常办公Form数据
				blMainForm =commWorkManager.FindRcbg(applyId, "24");
				model.addAttribute("blMainForm", blMainForm);
			}		
		} catch (Exception e) {
			log.error("", e);
		}
		return "app/work/zx/commworkzxBL";
	}

	/**
	 * 保存办理
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
			model.addAttribute("xfdjbId",work.getXfdjbId());//信访登记表id
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存办理信息错误！", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}

	/**
	 * 通用任务的执行界面-step3：报告
	 * 
	 * @param applyId
	 *            任务Id
	 * @param model
	 */
	@RequestMapping(value = "/commworkzxBG.htm")
	public String commworkzxBG(String applyId, String taskId, String tasktypeId ,String oper,
			ModelMap model, BlMainForm blMainForm) {
		try {
			//添加离线版判断标识（进入打包和报告页面的判断）
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
			model.addAttribute("rwlxIds", rwlxIds);
			if(!"24".equals(rwlxIds)){
				blMainForm = commWorkManager.getBlMainFormData(applyId);

				model.addAttribute("blMainForm", blMainForm);
			}else{
				//日常办公Form数据
				blMainForm =commWorkManager.FindRcbg(applyId, "24");
				model.addAttribute("blMainForm", blMainForm);
			}
			if("13".equals(rwlxIds)){
				//判断任务是否进行退回再提交报告
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
					//添加上worklog的查询，判断是不是已经退回来的限制再次办理的任务进行二次派发
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
	 * 验证是否可以上报
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
			JsonResultUtil.fail(model, "操作失败！");
		}
	}

	/**
	 * 执法对象类型保存
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/zfdxTypeSave.json", produces = "application/json")
	public void zfdxTypeSave(String applyId, String zfdxType,String rwlxIds, ModelMap model) {
		try {
			commWorkManager.saveZfdxTypeOnChange(applyId, zfdxType, rwlxIds);

			commWorkManager.saveZfdxType(applyId, zfdxType);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存执法对象类型错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 保存任务类型
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

			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存执法对象类型错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 获取任务类型
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getTaskTypeMultiple.json", produces = "application/json")
	public void getTaskTypeMultiple(String applyId, ModelMap model) {
		try {
			// 查出来ids,codes带到页面中去
			// 任务类型列表
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
			log.error("查询执法对象类型错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * 函数介绍：执法对象table（准备）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
	 * 函数介绍：任务类型下拉树（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTypeTreeComboByTaskId.json", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx,ModelMap model) {
		return commWorkManager.taskTypeTreeComboByTaskId(applyId, zfdxlx);
	}
	
	/**
	 * 
	 * 函数介绍：任务类型下拉树过滤掉信访投诉（根据任务的执法对象类型+执法对象个数来决定任务类型选择）
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTypeTreeComboByTaskIdWithoutXf.json", produces = "application/json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx,ModelMap model) {
		return commWorkManager.taskTypeTreeComboByTaskIdWithoutXf(applyId, zfdxlx);
	}

	/**
	 * 
	 * 函数介绍：人员规划table（准备）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
	 * 函数介绍：协办人员table
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
	 * 人员规划-删除
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/delrygh.json", produces = "application/json")
	public void delrygh(String applyId, String id, ModelMap model) {
		try {
			commWorkManager.saveDelrygh(applyId, id);
			//检查人列表
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除人员规划错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * 函数介绍：人员规划下拉框（办理）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/ryghCombo.json")
	@ResponseBody
	public List<Combobox> ryghCombo(String applyId, ModelMap model) {
		return commWorkManager.ryghCombo(applyId);
	}

	/**
	 * 人员保存
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/rySave.json", produces = "application/json")
	public void rySave(String applyId, String ryid, String type, ModelMap model) {
		try {
			commWorkManager.saveRy(applyId, ryid, type);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存人员错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 人员保存(多个)
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/rySaveMul.json", produces = "application/json")
	public void rySaveMul(String applyId, String ryid, String type,
			ModelMap model) {
		try {
			commWorkManager.saveRyMul(applyId, ryid, type);
			 //检查人列表
			model.addAttribute("jcrList",commWorkManager.ryghCombo(applyId));
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存人员错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 获取勘察询问笔录
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
			log.error("查询勘察询问笔录错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 通用获取单附件信息
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
			log.error("查询附件错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 监察笔录附件信息
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getJcblFile.json", produces = "application/json")
	public void getJcblFile(String applyId, String fileTypeEnumName,String moreCheckFiletype,String tasktypeCode,
			ModelMap model) {
		try {
			List<Map<String, String>> jcjlFileMap = new ArrayList<Map<String, String>> ();
			if(StringUtils.equals(tasktypeCode, "10")){//现场检查
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
			}else if(StringUtils.equals(tasktypeCode, "11")){//年度核查
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
			}else if(StringUtils.equals(tasktypeCode, "12")){//后督察
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
			}else if(StringUtils.equals(tasktypeCode, "13")){//信访投诉
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
			}else if(StringUtils.equals(tasktypeCode, "14")){//排污许可证检查
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
			}else if(StringUtils.equals(tasktypeCode, "15")){//专项行动
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
			}else if(StringUtils.equals(tasktypeCode, "17")){//限期治理
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
			}else if(StringUtils.equals(tasktypeCode, "18")){//跟踪检查
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
			}else if(StringUtils.equals(tasktypeCode, "20")){//危险废物
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
			}else if(StringUtils.equals(tasktypeCode, "22")){//辐射安全
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
			}else if(StringUtils.equals(tasktypeCode, "23")){//污染事故现场调查
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
			log.error("查询附件错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 多次检查时通过附件id得到监察情况
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
			log.error("查询附件错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	// ////////////////////////////////////办理步骤校验start/////////////////////////////////////////
	/**
	 * 验证"准备"【true、通过；false、不通过】
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
			JsonResultUtil.fail(model, "操作失败！");
		}
	}

	/**
	 * 验证"办理"【true、通过；false、不通过】
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
			JsonResultUtil.fail(model, "操作失败！");
		}
	}

	// ////////////////////////////////////办理步骤校验end/////////////////////////////////////////

	/**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryBlFileTypeCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo) {
		return commWorkManager.queryBlFileTypeCombo(rwlx,zfdxInfo);
	}

	/**
	 * 
	 * 函数介绍：获取任务办理附件类型下拉列表（专项行动子任务）
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryBlFileTypeComboZXXDZRW.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryBlFileTypeComboZXXDZRW() {
		return commWorkManager.queryBlFileTypeComboZXXDZRW();
	}

	/**
	 * 
	 * 函数介绍：跳转到单机上传界面
	 * 
	 * 输入参数：applyId：任务id
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/getDjMessage.htm")
	public String getDjMessage(ModelMap model, String applyId) {
		model.addAttribute("applyId", applyId);
		return "app/work/zx/getDjMessage";
	}

	/**
	 * 
	 * 函数介绍：保存单机执法信息
	 * 
	 * 输入参数：applyId-任务id，
	 * 
	 * 返回值：
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
			writer.print("{\"state\":true,\"msg\":\"上传成功\"}");
		} catch (Exception e) {
			writer
					.print("{\"state\":false,\"msg\":\"" + e.getMessage()
							+ "\"}");
		}
	}

	/**
	 * 获取监察结论
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
			JsonResultUtil.suncess(model, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取日常办公备注
	 * 
	 * @param model
	 * @param taskid
	 * @param tasktype
	 * @param desc
	 */
	@RequestMapping(value = "/rcbgDesc.json", produces = "application/json")
	public void rcbgDesc(ModelMap model, String taskid, String tasktype) {
		try {
			//日常办公Form数据
			BlMainForm blMainForm= commWorkManager.FindRcbg(taskid, tasktype);
			String desc=blMainForm.getBlRcbgForm().getDesc();
			model.addAttribute("desc", StringUtil.isBlank(desc) ? "" : desc);
			JsonResultUtil.suncess(model, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存监察结论
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
			JsonResultUtil.suncess(model, "保存成功。");
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 
	 * 函数介绍：生成模板
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/saveDownJcbgmb")
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType,
			HttpServletResponse res) {
		commWorkManager.saveDownJcbgmb(jcmbId, applyId, taskType, res);
	}

}
