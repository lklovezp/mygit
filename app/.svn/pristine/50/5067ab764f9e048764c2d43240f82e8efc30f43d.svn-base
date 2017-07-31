package com.hnjz.app.data.zfdx.ysy;

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

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.zfdx.lawobjf.LawobjfManager;
import com.hnjz.app.data.zfdx.yly.YlyForm;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.util.LogUtil;


/**
 * Controller
 * 
 * @author XUYAXING
 * @Datetime 2017年6月7日
 * @version $Id: TdataysyController.java, 
 */
@Controller
public class YsyController {


    /** 日志 */
	private static final Log log = LogFactory.getLog(YsyController.class);

	@Autowired
	private YsyManager tdataysyManager;
	
	@Autowired
	private LawobjfManager tdatalawobjfManager;

   
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
	@RequestMapping(value = "/saveysy.json", produces = "application/json")
	public void saveTdataysy(ModelMap model, TDataLawobjf lawobjf, YsyForm ysyForm) throws ParseException {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(ysyForm));
			}
			if(StringUtils.isNotBlank(ysyForm.getJsxmid())){
				tdatalawobjfManager.removeTdatalawobjf(ysyForm.getJsxmid());
			}
			lawobjf=tdataysyManager.saveysy(ysyForm);
			JsonResultUtil.suncess(model, "保存成功！");
			model.put("id", lawobjf.getId());
			model.put("name", lawobjf.getDwmc());
    		model.put("type", lawobjf.getLawobjtype().getId());
		} catch (AppException e) {
			log.error("保存菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}


}







