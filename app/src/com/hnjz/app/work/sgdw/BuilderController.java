package com.hnjz.app.work.sgdw;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.TDataSgdw;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.util.LogUtil;

@Controller
public class BuilderController {
    /*日志*/	
	private static final Log log=LogFactory.getLog(BuilderController.class);
	@Autowired
	private BuilderManager builderManager;
	/**
	 * 到施工单位管理的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 施工单位管理的初始界面
	 */
	@RequestMapping(value = "/builderList.htm")
	public String builderList(ModelMap model, String title,String id,String areaId,String starttime,String endtime) {
		model.addAttribute("title", title);
		return "app/work/sgdw/builderList";
	}
	@RequestMapping(value="/queryBuilderList.json")
	public void queryBuilderList(ModelMap model, String name, String isActive, String page,String pageSize){
		page = StringUtils.defaultIfBlank(page, "1");
	}
	/**
	 * 编辑一个施工单位信息
	 * 
	 * @param id
	 *            施工单位Id
	 * @return 施工单位管理的初始界面
	 */
	@RequestMapping(value = "/editBuilder.htm")
	public String editBuilder(ModelMap model, BuilderForm builderForm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + builderForm.getId());
		}
		if (StringUtils.isNotBlank(builderForm.getId())) {
			try {
				this.builderManager.editBuilder(builderForm);
			} catch (AppException e) {
				e.printStackTrace();
			}
			model.addAttribute("title", "编辑施工单位");
		} else {
			model.addAttribute("title", "新建施工单位");
		}
		return "app/work/sgdw/editBuilder";
	}
	
	/**
	 * 保存一个施工单位信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param builderForm
	 *            施工单位的form表单
	 * 
	 */
	@RequestMapping(value = "/saveBuilder.json")
	public void saveBuilder(ModelMap model, BuilderForm builderForm,String isActive) {
		
		try {
			log.info("得到isAtive===="+isActive);
			builderManager.saveBuilder(builderForm);
			log.info("得到isAtive===="+isActive);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
		}
	}
	/**
	 * 施工单位管理的查询
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            搜索条件，可以按名称查询
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 */
	@RequestMapping(value = "/builderQuery.json", produces = "application/json")
	public void builderQuery(ModelMap model, String name, String isActive, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = builderManager.queryBuilderList(name, isActive, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 * 删除一个施工单位
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 *            施工单位id
	 */
	@RequestMapping(value = "/delBuilder.json", produces = "application/json")
	public void delBuilder(ModelMap model, String id) {
		try {
			builderManager.removeBuilder(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除用户信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
	}
	
	
	/**
	 * 查询一个施工单位
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param builderForm
	 *            施工单位builderForm
	 */
	@RequestMapping(value = "/builderInfo.htm")
	public String builderInfo(ModelMap model,BuilderForm builderForm) {
		try {
			this.builderManager.editBuilder(builderForm);
			model.addAttribute("title", "施工单位信息");
		} catch (Exception e) {
			log.error("查询错误！", e);
			
		}
		return "app/work/sgdw/chaKanBuilder";
	}


}
