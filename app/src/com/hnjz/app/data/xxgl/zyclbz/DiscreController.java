package com.hnjz.app.data.xxgl.zyclbz;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		自由裁量标准控制层
 *
 */
@Controller
public class DiscreController {

	/**日志*/
	private static final Log log = LogFactory.getLog(DiscreController.class);

	@Autowired
	private DiscreManager discreManager;

	/**
	 * 
	 * 函数介绍：自有裁量标准起始界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/zyclbzPage.htm")
	public String zyclbzPage(ModelMap model,String title) {
		model.put("title", title);
		JSONArray array = discreManager.getZyclbzTree();
		model.put("array", array);
		return "app/data/xxgl/zyclbz/zyclbzPage";
	}
	
	/**
	 * 
	 * 函数介绍：自有裁量标准起始界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/xxcx_zyclbzPage.htm")
	public String xxcx_zyclbzPage(ModelMap model,String title) {
		model.put("title", title);
		JSONArray array = discreManager.getZyclbzTree();
		model.put("array", array);
		return "app/data/xxcx/zyclbz/zyclbzPage";
	}

	/**
	* 
	* 函数介绍：违法行为列表
	
	* 输入参数：id:制度分类id
	
	* 返回值：
	*/
	@RequestMapping(value = "/wfxwList.htm")
	public String wfxwList(ModelMap model,String id) {
		if(id!=null){
			model.put("zdfl", discreManager.zdflInfo(id));
		}
		return "app/data/xxgl/zyclbz/wfxwList";
	}
	/**
	 * 
	 * 函数介绍：违法行为列表
	
	 * 输入参数：id:制度分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/xxcx_wfxwList.htm")
	public String xxcx_wfxwList(ModelMap model,String id) {
		if(id!=null){
			model.put("zdfl", discreManager.zdflInfo(id));
		}
		return "app/data/xxcx/zyclbz/wfxwList";
	}
	
	/**
	 * 
	 * 函数介绍：违法行为编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/wfxwEdit.htm")
	public String wfxwEdit(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		if(tDataDiscreacts!=null && tDataDiscreacts.getId()!=null){
			tDataDiscreacts = discreManager.getTDataDiscreactsInfo(tDataDiscreacts.getId());
		}
		model.put("tDataDiscreacts", tDataDiscreacts);
		return "app/data/xxgl/zyclbz/wfxwEdit";
	}
	/**
	 * 
	 * 函数介绍：违法行为编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/wfxwInfo.htm")
	public String wfxwInfo(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		if(tDataDiscreacts!=null && tDataDiscreacts.getId()!=null){
			tDataDiscreacts = discreManager.getTDataDiscreactsInfo(tDataDiscreacts.getId());
		}
		model.put("tDataDiscreacts", tDataDiscreacts);
		return "app/data/xxgl/zyclbz/wfxwInfo";
	}
	
	/**
	 * 
	 * 函数介绍：更新违法行为
	
	 * 输入参数：id:制度分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveOrUpdateWfxw.htm")
	public String saveOrUpdateWfxw(ModelMap model,TDataDiscreacts tDataDiscreacts) {
		try {
			discreManager.saveOrUpdateWfxw(tDataDiscreacts);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存违法行为错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/wfxwEdit";
	}
	
	/**
	 * 
	 * 函数介绍：删除违法行为
	
	 * 输入参数：id:违法行为id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/deleteWfxw.json")
	public void deleteWfxw(ModelMap model,String id){
		try {
			discreManager.removeWfxw(id);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除违法行为错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * 加载列表数据 违法类型
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryWfxwList.json")
    public void queryWfxwList(ModelMap model,String pid, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryWflxList(pid, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    
    
    
    
    
    
    
    
    /**
	* 
	* 函数介绍：法律依据列表
	
	* 输入参数：id:制度分类id
	
	* 返回值：
	*/
	@RequestMapping(value = "/flyjList.htm")
	public String flyjList(ModelMap model,String id) {
		if(id!=null){
			model.put("wfxw", discreManager.getTDataDiscreactsInfo(id));
		}
		return "app/data/xxgl/zyclbz/flyjList";
	}
	/**
	 * 
	 * 函数介绍：法律依据列表
	
	 * 输入参数：id:制度分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/xxcx_flyjList.htm")
	public String xxcx_flyjList(ModelMap model,String id) {
		if(id!=null){
			model.put("wfxw", discreManager.getTDataDiscreactsInfo(id));
		}
		return "app/data/xxcx/zyclbz/flyjList";
	}
	
	/**
	 * 
	 * 函数介绍：法律依据编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/flyjEdit.htm")
	public String flyjEdit(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			if(tDataDiscremerit!=null && tDataDiscremerit.getId()!=null){
				tDataDiscremerit = discreManager.getTDataDiscremeritInfo(tDataDiscremerit.getId());
			}
			if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
				tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
			}
			model.put("tDataDiscremerit", tDataDiscremerit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/zyclbz/flyjEdit";
	}
	
	/**
	 * 
	 * 函数介绍：法律依据编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/flyjInfo.htm")
	public String flyjInfo(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			if(tDataDiscremerit!=null && tDataDiscremerit.getId()!=null){
				tDataDiscremerit = discreManager.getTDataDiscremeritInfo(tDataDiscremerit.getId());
			}
			if(tDataDiscremerit!=null && tDataDiscremerit.gettDataDiscreacts()!=null && StringUtils.isNotBlank(tDataDiscremerit.gettDataDiscreacts().getId())){
				tDataDiscremerit.settDataDiscreacts(discreManager.getTDataDiscreactsInfo(tDataDiscremerit.gettDataDiscreacts().getId()));
			}
			model.put("tDataDiscremerit", tDataDiscremerit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/zyclbz/flyjInfo";
	}
	
	/**
	 * 
	 * 函数介绍：更新法律依据
	
	 * 输入参数：id:制度分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveOrUpdateFlyj.htm")
	public String saveOrUpdateFlyj(ModelMap model,TDataDiscremerit tDataDiscremerit) {
		try {
			discreManager.saveOrUpdateFlyj(tDataDiscremerit);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存法律依据错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/flyjEdit";
	}
	
	/**
	 * 
	 * 函数介绍：删除法律依据
	
	 * 输入参数：id:法律依据id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/deleteFlyj.json")
	public void deleteFlyj(ModelMap model,String id){
		try {
			discreManager.removeFlyj(id);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除法律依据错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * 加载列表数据 法律依据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryFlyjList.json")
    public void queryFlyjList(ModelMap model,String pid, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryFlyjList(pid, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    
    
    
    
    
    
    
    
    
    /**
	* 
	* 函数介绍：情形分类列表
	
	* 输入参数：id:法律依据id
	
	* 返回值：
	*/
	@RequestMapping(value = "/qxflList.htm")
	public String qxflList(ModelMap model,String id) {
		if(id!=null){
			model.put("flyj", discreManager.getTDataDiscremeritInfo(id));
		}
		return "app/data/xxgl/zyclbz/qxflList";
	}
	/**
	 * 
	 * 函数介绍：情形分类列表
	
	 * 输入参数：id:法律依据id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/xxcx_qxflList.htm")
	public String xxcx_qxflList(ModelMap model,String id) {
		if(id!=null){
			model.put("flyj", discreManager.getTDataDiscremeritInfo(id));
		}
		return "app/data/xxcx/zyclbz/qxflList";
	}
	
	/**
	 * 
	 * 函数介绍：情形分类编辑界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/qxflEdit.htm")
	public String qxflEdit(ModelMap model,TDataDiscrecaseclass tDataDiscrecaseclass) {
		if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.getId()!=null){
			tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(tDataDiscrecaseclass.getId());
		}
		if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
			tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
		}
		model.put("tDataDiscrecaseclass", tDataDiscrecaseclass);
		return "app/data/xxgl/zyclbz/qxflEdit";
	}
	
	/**
	 * 
	 * 函数介绍：更新情形分类
	
	 * 输入参数：id:制度分类id
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveOrUpdateQxfl.htm")
	public String saveOrUpdateQxfl(ModelMap model,TDataDiscrecaseclass tDataDiscrecaseclass) {
		try {
			discreManager.saveOrUpdateQxfl(tDataDiscrecaseclass);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存情形分类错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		return "app/data/xxgl/zyclbz/qxflEdit";
	}
	
	
	/**
	 * 
	 * 函数介绍：删除情形分类
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/deleteQxfl.json")
	public void deleteQxfl(ModelMap model,String id){
		try {
			discreManager.removeQxfl(id);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除情形分类错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	 /**
     * 加载列表数据 情形分类
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryQxflList.json")
    public void queryQxflList(ModelMap model,String pid, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryQxflList(pid, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据 情形分类
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/qxflInfo.htm")
    public String qxflInfo(ModelMap model,String id){
    	TDataDiscrecaseclass tDataDiscrecaseclass = discreManager.getTDataDiscrecaseclassInfo(id);
    	if(tDataDiscrecaseclass!=null && tDataDiscrecaseclass.gettDataDiscremerit()!=null && StringUtils.isNotBlank(tDataDiscrecaseclass.gettDataDiscremerit().getId())){
			tDataDiscrecaseclass.settDataDiscremerit(discreManager.getTDataDiscremeritInfo(tDataDiscrecaseclass.gettDataDiscremerit().getId()));
		}
    	model.put("tDataDiscrecaseclass", tDataDiscrecaseclass);
    	return "app/data/xxgl/zyclbz/qxflInfo";
    }

    
    /**
	* 
	* 函数介绍：情形分类列表
	
	* 输入参数：id:法律依据id
	
	* 返回值：
	*/
	@RequestMapping(value = "/wflx_qxflList.htm")
	public String wflx_qxflList(ModelMap model,String wflxId) {
		JSONArray array = discreManager.getZyclbzTreeByWflxId(wflxId);
		model.put("array", array);
		model.put("title", "相关法律依据");
		return "app/data/xxcx/zyclbz/zyclbzPage";
	}
	
	/**
     * 根据违法类型查询情形分类列表
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryQxflListBywflx.json")
    public void queryQxflListByWflx(ModelMap model,String wflxId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = discreManager.queryQxflListByWflx(wflxId, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
}
