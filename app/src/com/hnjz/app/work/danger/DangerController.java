package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.work.sgdw.BuilderForm;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
@Controller
public class DangerController {
	private static final Log log=LogFactory.getLog(DangerController.class);
	@Autowired
	private DangerManager dangerManager;
	@RequestMapping(value="/weiHuaPing.htm")
	public String weiHuaPing(ModelMap model,TDataLawobjf lawobjf){
		String pid=lawobjf.getId();
		model.addAttribute("pid", pid);
		return "app/work/danger/weiHuaPin";
	}
	

	
	@RequestMapping(value="/whpContentFind.htm")
	public String whpContentFind(ModelMap model,TDataLawobj lawobj){
		String pid=lawobj.getId();
		model.addAttribute("pid", pid);
		return "app/work/danger/whpContent";
	}
	
	@RequestMapping(value="/whpAdd.htm")
	public String whpAdd(ModelMap model,QyhxwzqkzycpForm qyhxwzqkzycpForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/whpSaveContent";
	}
	
	@RequestMapping(value="/whpAddFcp.htm")
	public String whpAddFcp(ModelMap model,QyhxwzqkfcpForm qyhxwzqkfcpForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/whpSaveFcpContent";
	}
	@RequestMapping(value="/whpAddYl.htm")
	public String whpAddYl(ModelMap model,QyhxwzqkylForm qyhxwzqkylForm){
		
		//model.addAttribute("pid", pid);
		return "app/work/danger/whpSaveYlContent";
	}
	/**
	 * 
	 * 函数介绍：物理状态下拉列表
	 */
	@RequestMapping(value ="/wlstate.json")
	@ResponseBody
	public List<Combobox> wlsState(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=dangerManager.queryWlStateList();
		return listResult;
	}
	
	/**
	 * 
	 * 函数介绍：物理状态下拉列表
	 */
	@RequestMapping(value ="/ysfs.json")
	@ResponseBody
	public List<Combobox> ysfs(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=dangerManager.ysfs();
		return listResult;
	}
	/**
	 * 
	 * 函数介绍：物理状态下拉列表
	 */
	@RequestMapping(value ="/sbState.json")
	@ResponseBody
	public List<Combobox> sbState(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=dangerManager.sbState();
		return listResult;
	}
	/**
	 * 
	 * 函数介绍：物理状态下拉列表
	 */
	@RequestMapping(value ="/scType.json")
	@ResponseBody
	public List<Combobox> scType(ModelMap model) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult=dangerManager.scType();
		return listResult;
	}
	
	/**
	 * 保存一个主要产品
	 */
	@RequestMapping(value = "/saveQyhxwzqkzycpForm.json")
	public void saveQyhxwzqkzycpForm(ModelMap model, QyhxwzqkzycpForm qyhxwzqkzycpForm) {
		try {
			dangerManager.saveQyhxwzqkzycpForm(qyhxwzqkzycpForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			e.printStackTrace();
		}			
			
		
	}
	/**
	 * 保存一个副产品
	 */
	@RequestMapping(value = "/saveQyhxwzqkfcpForm.json")
	public void saveQyhxwzqkfcpForm(ModelMap model, QyhxwzqkfcpForm qyhxwzqkfcpForm) {
		try {
			dangerManager.saveQyhxwzqkfcpForm(qyhxwzqkfcpForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			e.printStackTrace();
		}			
			
		
	}
	/**
	 * 保存一个原料
	 */
	@RequestMapping(value = "/saveQyhxwzqkylForm.json")
	public void saveQyhxwzqkylForm(ModelMap model, QyhxwzqkylForm qyhxwzqkylForm) {
		try {
			dangerManager.saveQyhxwzqkylForm(qyhxwzqkylForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			e.printStackTrace();
		}			
		
		
	}
	
	/**
	 *危化品信息列表查询
	 */
	@RequestMapping(value = "/whpContentMainList.json", produces = "application/json")
	public void whpContentMainList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = dangerManager.whpContentMainList(pid,isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 *危化品副产品信息列表查询
	 */
	@RequestMapping(value = "/whpContentFcpList.json", produces = "application/json")
	public void whpContentFcpList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = dangerManager.whpContentFcpList(pid, isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 *危化品原料信息列表查询
	 */
	@RequestMapping(value = "/whpContentYlList.json", produces = "application/json")
	public void whpContentYlList(ModelMap model,String pid,String isActive, String page,String rows) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = dangerManager.whpContentYlList(pid, isActive, page,rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	
	
	/**
	 *危化品信息修改
	 * 
	 */
	@RequestMapping(value = "/editMain.htm")
	public String whpEditMain(ModelMap model,QyhxwzqkzycpForm qyhxwzqkzycpForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkzycpForm.getId());
			}
			this.dangerManager.editQyhxwzqkzycpForm(qyhxwzqkzycpForm);
			model.addAttribute("title", "编辑主要产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/whpSaveContent";
		}
	/**
	 *危化品副产品信息修改
	 * 
	 */
	@RequestMapping(value = "/editFcp.htm")
	public String whpEditFcp(ModelMap model,QyhxwzqkfcpForm qyhxwzqkfcpForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkfcpForm.getId());
			}
			this.dangerManager.editQyhxwzqkfcpForm(qyhxwzqkfcpForm);
			model.addAttribute("title", "编辑副产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/whpSaveFcpContent";
		}
	/**
	 *危化品原料信息修改
	 * 
	 */
	@RequestMapping(value = "/editYl.htm")
	public String whpEditYl(ModelMap model,QyhxwzqkylForm qyhxwzqkylForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkylForm.getId());
			}
			this.dangerManager.editQyhxwzqkylForm(qyhxwzqkylForm);
			model.addAttribute("title", "编辑副产品");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/whpSaveYlContent";
	}
	
	
	/**
	 *危化品信息查询
	 * 
	 */
	@RequestMapping(value = "/infoYzcp.htm")
	public String infoYzcp(ModelMap model,QyhxwzqkzycpForm qyhxwzqkzycpForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkzycpForm.getId());
			}
			this.dangerManager.infoQyhxwzqkzycpForm(qyhxwzqkzycpForm);
			model.addAttribute("title", "主要产品信息");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoYzcp";
	}
	/**
	 *危化品副产品信息查询
	 * 
	 */
	@RequestMapping(value = "/infoFcp.htm")
	public String infoFcp(ModelMap model,QyhxwzqkfcpForm qyhxwzqkfcpForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkfcpForm.getId());
			}
			this.dangerManager.infoQyhxwzqkfcpForm(qyhxwzqkfcpForm);
			model.addAttribute("title", "主要产品信息");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoFcp";
	}
	/**
	 *危化品副产品信息查询
	 * 
	 */
	@RequestMapping(value = "/infoYl.htm")
	public String infoYl(ModelMap model,QyhxwzqkylForm qyhxwzqkylForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("id:" + qyhxwzqkylForm.getId());
			}
			this.dangerManager.infoQyhxwzqkylForm(qyhxwzqkylForm);
			model.addAttribute("title", "主要产品信息");
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "app/work/danger/infoYl";
	}
	
	/**
	 * 删除一个主要产品
	 * 
	 */
	@RequestMapping(value = "/delZycp.json", produces = "application/json")
	public void delZycp(ModelMap model, String id) {
		try {
			dangerManager.removeZycp(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除用户信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	/**
	 * 删除一个副产品
	 * 
	 */
	@RequestMapping(value = "/delFcp.json", produces = "application/json")
	public void delFcp(ModelMap model, String id) {
		try {
			dangerManager.removeFcp(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除用户信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	/**
	 * 删除一个原料
	 * 
	 */
	@RequestMapping(value = "/delYl.json", produces = "application/json")
	public void delYl(ModelMap model, String id) {
		try {
			dangerManager.removeYl(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除用户信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
}
