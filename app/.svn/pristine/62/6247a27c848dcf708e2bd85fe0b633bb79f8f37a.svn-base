package com.hnjz.mobile.data.xxcx;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.app.data.xxgl.zyclbz.DiscreManager;
import com.hnjz.sys.po.TSysDic;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-4-16
 * 功能描述：
		自由裁量标准控制层(手持终端控制层)
 *
 */
@Controller
public class DiscreMobileController {

	/**日志*/
	private static final Log log = LogFactory.getLog(DiscreMobileController.class);

	@Autowired
	private DiscreManager discreManager;
	
	@Autowired
	private DiscreMobileManager discreMobileManager;

	/**
	 * 
	 * 函数介绍：自有裁量标准起始界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/zyclbzTree.mo")
	public void zyclbzTreeMobile(ModelMap model,HttpServletResponse response) {
		try {
			JSONArray array = discreMobileManager.getZyclbzTreeMobile();
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 函数介绍：自由裁量权裁详情
	
	 * 输入参数：情形分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/zyclqcInfo.mo")
	public void zyclqcInfo(ModelMap model,String level,String id,HttpServletResponse response){
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray array = new JSONArray();
			jsonObj.put("baseInfo", array);
			JSONObject obj = null;
			switch (Integer.parseInt(level)) {
				case 1:
					TSysDic dic = (TSysDic) discreMobileManager.get(TSysDic.class, id);
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "制度分类：");
					obj.put("value", dic.getName());
					break;
				case 2:
					TDataDiscreacts tDataDiscreacts = discreManager.getTDataDiscreactsInfo(id);
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "制度分类：");
					obj.put("value", tDataDiscreacts.getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "违法类型：");
					obj.put("value", tDataDiscreacts.getIllegaltype().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "违法行为：");
					obj.put("value", tDataDiscreacts.getContent());
					break;
				case 3:
					TDataDiscremerit tDataDiscremerit = discreManager.getTDataDiscremeritInfo(id);
					if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
						tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
					}
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "制度分类：");
					obj.put("value", tDataDiscremerit.gettDataDiscreacts().getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "违法行为：");
					obj.put("value", tDataDiscremerit.gettDataDiscreacts().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "法律依据别名：");
					obj.put("value", tDataDiscremerit.getAlias());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "法律依据：");
					obj.put("value", tDataDiscremerit.getContent());
					break;
				case 4:
					TDataDiscrecaseclass tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(id);
					if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
						tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
					}
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "制度分类：");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().gettDataDiscreacts().getType().getName());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "违法行为：");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().gettDataDiscreacts().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "法律依据：");
					obj.put("value", tDataDiscrecaseclass.gettDataDiscremerit().getContent());
					obj = new JSONObject();
					array.put(obj);
					obj.put("key", "情形分类：");
					obj.put("value", tDataDiscrecaseclass.getName());
					
					jsonObj.put("dataList", tDataDiscrecaseclass.getContent());
					break;
				default:
					break;
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObj.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
