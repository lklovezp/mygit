package com.hnjz.app.data.xxgl.fsaq;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.JsonResultUtil;
@Controller
public class FsaqController {
	private static final Log log=LogFactory.getLog(FsaqController.class);
	@Autowired
	private FsaqManager fsaqManager;
	/**
	 * 查询辐射安全基本信息，以及跳转到添加或者修改页面，并且回显数据
	 * */
	@RequestMapping(value="/fsaqxxEdit.htm")
	public String fsaqxxEdit(ModelMap model,String lawobjId,String lawobjTypeId){
		
		try {
			String id="";
			FsaqForm fsaqForm=fsaqManager.queryFsaqForm(lawobjId, id, lawobjTypeId);
			model.addAttribute("fsaqForm", fsaqForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/fsaq/fsaqEdit";
	}
	/**
	 * 保存或者修改辐射安全基本信息
	 * */
	@RequestMapping(value="/saveFsaqxx.json")
	public void saveFsaqForm(ModelMap model,FsaqForm fsaqForm){
		try {
			fsaqManager.saveFsaqForm(fsaqForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "保存失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 导出辐射安全基本信息表
	 * */
	@RequestMapping(value="/buildFsaqWord.json")
	public void buildFsaqWord(HttpServletResponse response,ModelMap model,String lawobjId,String lawobjType,String biaoshi,String appleId){
		try {
			fsaqManager.createFsaqWord(response, lawobjId, lawobjType, biaoshi,appleId);
			JsonResultUtil.suncess(model, "导出成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "导出失败！");
			e.printStackTrace();
		}
	}
}
