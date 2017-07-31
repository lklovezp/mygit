package com.hnjz.app.data.zfdx.jsxm;



import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataJsxm;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.zfdx.gywry.GywryForm;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;


/**
 * Controller
 * 
 * @author xuyaxing
 * @Datetime 2017年6月7日
 * @version $Id: TdatajsxmController.java, 
 */
@Controller
public class JsxmController {


    /** 日志 */
	private static final Log log = LogFactory.getLog(JsxmController.class);

	@Autowired
	private JsxmManager tdatajsxmManager;
    
	@Autowired
	private ZfdxManager zfdxManager;
	/**
	 * 保存一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            区域的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 区域的初始界面
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/savejsxm.json", produces = "application/json")
	public void saveTdatagywry(ModelMap model, TDataLawobjf lawobjf,JsxmForm jsxmForm) throws ParseException {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(jsxmForm));
			}
			
			lawobjf=tdatajsxmManager.saveTDataJsxm(jsxmForm);
			TDataJsxm jsxm=tdatajsxmManager.getJsxm(lawobjf.getId());
			TDataIndustry tDataIndustry = (TDataIndustry) tdatajsxmManager.get(TDataIndustry.class, lawobjf.getHylx());

			TDataLawobjtype type=zfdxManager.gettype(tDataIndustry.getTolawobjtype());
			JsonResultUtil.suncess(model, "保存成功！");
			model.put("id", lawobjf.getId());
			model.put("name", lawobjf.getDwmc());
    		model.put("type", lawobjf.getLawobjtype().getId());
    		model.put("hylx", type.getId());
    		model.put("typename",type.getName());
    		model.put("jsjd",jsxm.getJsjdjsczt());
		} catch (AppException e) {
			log.error("保存菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * 编辑一个菜单信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/editjsxm.htm")
	public String editTdatagywry(ModelMap model, JsxmForm frm) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			model.addAttribute("title", "新建");
			return "sys/tdatagywry/editTdatagywry";
		}
		tdatajsxmManager.editJsxm(frm);
		model.addAttribute("title", "修改");
		return "sys/tdatagywry/editTdatagywry";
	}
   



}







