package com.hnjz.app.data.zfdx.lawobjtype;

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
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.industry.IndustryManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;


/**
 * Controller
 * 
 * @author xuyaxing
 * @Datetime 2017年6月7日
 * @version $Id: TdatalawobjtypeController.java, 
 */
@Controller
public class ZfdxController {


    /** 日志 */
	private static final Log log = LogFactory.getLog(ZfdxController.class);

	@Autowired
	private ZfdxManager zfdxManager;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private IndustryManager industryManager;


   /**
	 * 到区域的初始界面
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return 区域的初始界面
 * @throws Exception 
	 */
	@RequestMapping(value = "/zfdxList.htm")
	public String tdatalawobjtypeList(ModelMap model, String title,String name) throws Exception {
		this.tdatalawobjtypeQuery(model);
		//默认显示本部门信息
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
		model.addAttribute("title", title);
		return "app/data/zfdx/zfdxList";
	}

	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/tdatalawobjtypeQuery.json", produces = "application/json")
	public void tdatalawobjtypeQuery(ModelMap model) {
		try {
			JSONArray re = zfdxManager.queryTdatalawobjtype("Y");
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/typeQuery.json", produces = "application/json")
	public String lawobjtypeQuery(ModelMap model) {
		try {
			JSONArray re = zfdxManager.queryTdatalawobjtype("Y");
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
		return "common/choseeZfdxType";
	}

	/**
	 * 删除一个菜单信息
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param funForm
	 *            区域的form表单
	 * @param result
	 *            {@link BindingResult}
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/delzfdx.json", produces = "application/json")
	public void removeTdatalawobjtype(ModelMap model, String id) {
		try {
			zfdxManager.removeTdatalawobjtype(id);
			List<TDataIndustry> list=industryManager.getIndustry(id);
			for(int i=0;i<list.size();i++){
				industryManager.removeIndustry(list.get(i).getId());
			}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

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
	 */
	@RequestMapping(value = "/savezfdx.json", produces = "application/json")
	public void saveTdatalawobjtype(ModelMap model, ZfdxForm tdatalawobjtypeForm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(tdatalawobjtypeForm));
			}
			zfdxManager.saveTdatalawobjtype(tdatalawobjtypeForm);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存菜单信息错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	

	/**
	 * 编辑一个执法对象类型
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/editZfdx.htm")
	public String editTdatalawobjtype(ModelMap model, ZfdxForm frm,String lawobjtypeid) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isNotBlank(frm.getId())) {
			this.zfdxManager.editLawObjType(frm);
			model.addAttribute("title", "编辑执法对象类型");
			model.addAttribute("flawobjtypeid", frm.getLawobjtypeid());
			
		} else {
			model.addAttribute("title", "新建执法对象类型");
			model.addAttribute("lawobjtypeid", lawobjtypeid);
		}
		return "app/data/zfdx/zfdxEdit";
	}
	
	/**
     * 
     * 函数介绍：更新执法文件目录
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/lawtypeTree.json")
    @ResponseBody
    public List<ComboboxTree> lawdocdirTree(ModelMap model){
		return zfdxManager.queryLawobjtypeComboTree();
    }

}







