package com.hnjz.app.data.zfdx.lawobjf;

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
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.zfdx.fwy.FwyForm;
import com.hnjz.app.data.zfdx.fwy.FwyManager;
import com.hnjz.app.data.zfdx.gl.GlForm;
import com.hnjz.app.data.zfdx.gl.GlManager;
import com.hnjz.app.data.zfdx.gywry.GywryForm;
import com.hnjz.app.data.zfdx.gywry.GywryManager;
import com.hnjz.app.data.zfdx.jsxm.JsxmForm;
import com.hnjz.app.data.zfdx.jsxm.JsxmManager;
import com.hnjz.app.data.zfdx.jzgd.JzgdForm;
import com.hnjz.app.data.zfdx.jzgd.JzgdManager;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.app.data.zfdx.sc.ScForm;
import com.hnjz.app.data.zfdx.sc.ScManager;
import com.hnjz.app.data.zfdx.xqyz.XqyzForm;
import com.hnjz.app.data.zfdx.xqyz.XqyzManager;
import com.hnjz.app.data.zfdx.yly.YlyForm;
import com.hnjz.app.data.zfdx.yly.YlyManager;
import com.hnjz.app.data.zfdx.ysy.YsyForm;
import com.hnjz.app.data.zfdx.ysy.YsyManager;
import com.hnjz.app.data.zfdx.yy.YyForm;
import com.hnjz.app.data.zfdx.yy.YyManager;
import com.hnjz.app.data.zfdx.zzy.ZzyForm;
import com.hnjz.app.data.zfdx.zzy.ZzyManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.po.TSysUser;


/**
 * Controller
 * 
 * @author xuyaxing  
 * @Datetime 2017年6月7日
 * @version $Id: TdatalawobjfController.java, 
 */
@Controller
public class LawobjfController {


    /** 日志 */
	private static final Log log = LogFactory.getLog(LawobjfController.class);

	@Autowired
	private LawobjfManager tdatalawobjfManager;
    
	@Autowired
	private ZfdxManager zfdxManager;
	
	@Autowired
	private GywryManager tdatagywryManager;
	
	@Autowired
	private JsxmManager tdatajsxmManager;
	
	@Autowired
	private GlManager tdataglManager;
	
	@Autowired
	private XqyzManager tdataxqyzManager;
	
	@Autowired
	private YyManager tdatayyManager;
	
	@Autowired
	private JzgdManager tdatajzgdManager;
	
	@Autowired
	private ScManager tdatascManager;
	
	@Autowired
	private ZzyManager tdatazzyManager;
	
	@Autowired
	private YlyManager tdataylyManager;
	
	@Autowired
	private FwyManager tdatafwyManager;
	
	@Autowired
	private YsyManager tdataysyManager;
	
	

	/**
     * 查询网格信息
     */
    @RequestMapping(value = "/queryZfdxList.json" , produces = "application/json")
    public void querywgxxList(ModelMap model,String name,String dwmc,String lawobjtypeid,String regionId,String orgId,String wgid,String page,String pageSize){ 
    	try {
    		System.out.println("gagagasgasgagr"+lawobjtypeid+","+regionId);
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = tdatalawobjfManager.queryTdatalawobjf(name,dwmc,lawobjtypeid,regionId,orgId, wgid,page,pageSize);
			
			model.addAttribute("arr", re.getRows());
			
			JsonResultUtil.fyWeb(model, re);

		} catch (Exception e) {
			log.error("查询出错：", e);
		}
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
	@RequestMapping(value = "/dellawobjf.json", produces = "application/json")
	public void removeTdatalawobjf(ModelMap model, String ids ,String lawobjtype) {
		try {
			String[] idArray=ids.split(",");
			for(String id:idArray){
				tdatalawobjfManager.removeTdatalawobjf(id);
				if(lawobjtype.equals("1")){
					tdatagywryManager.delGywry(id);
			    }else if(lawobjtype.equals("2")){
				    tdatajsxmManager.delJsxm(id);
				}else if(lawobjtype.equals("3")){
				    tdatayyManager.delYy(id);
				}else if(lawobjtype.equals("4")){
				    tdataglManager.delGl(id);
				}else if(lawobjtype.equals("5")){
				    tdatajzgdManager.delJzgd(id);
				}else if(lawobjtype.equals("6")){
				    tdataxqyzManager.delXqyz(id);
				}else if(lawobjtype.equals("7")){
				    tdatascManager.delSc(id);
				}else if(lawobjtype.equals("8")){
				    tdatafwyManager.delFwy(id);
				}else if(lawobjtype.equals("9")){
				    tdataysyManager.delYsy(id);
				}else if(lawobjtype.equals("10")){
				    tdatazzyManager.delZzy(id);
				}else if(lawobjtype.equals("11")){
				    tdataylyManager.delYly(id);
				}
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
	@RequestMapping(value = "/saveqt.json", produces = "application/json")
	public void saveTdatalawobjf(ModelMap model,TDataLawobjf lawobjf, LawobjfForm lawonjfForm) {
		
		
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(lawonjfForm));
			}
			if(StringUtils.isNotBlank(lawonjfForm.getJsxmid())){
				tdatalawobjfManager.removeTdatalawobjf(lawonjfForm.getJsxmid());
			}
			lawobjf=tdatalawobjfManager.saveTdatalawobjf(lawonjfForm);
			JsonResultUtil.suncess(model, "保存成功！");
			model.put("id", lawobjf.getId());
			model.put("name", lawobjf.getDwmc());
    		model.put("type", lawobjf.getLawobjtype().getId());
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
	@RequestMapping(value = "/editlawobjf.htm")
	public String editTdatalawobjf(ModelMap model, LawobjfForm frm,GywryForm gfrm,JsxmForm jfrm,
			GlForm glfrm,XqyzForm xfrm,YyForm yyfrm,JzgdForm jzfrm,ScForm sfrm,String lawobjtypeid,
			ZzyForm zfrm,YlyForm ylfrm,FwyForm ffrm,YsyForm ysfrm,String jsxmid) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		if (StringUtils.isBlank(gfrm.getId())) {
			TSysUser user=CtxUtil.getCurUser();
			model.addAttribute("cjrname", user.getName());
			model.addAttribute("cjr",user.getId());
			model.addAttribute("title", "新建"+lawobjtype.getName()+"基础信息");
			model.addAttribute("lawobjtypeid", lawobjtypeid);
			model.addAttribute("jsxmid",jsxmid);
		}else{
			if(lawobjtype.getId().equals("1")){
				tdatagywryManager.editGywry(gfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("2")){
				tdatajsxmManager.editJsxm(jfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("3")){
				tdatayyManager.edityy(yyfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("4")){
				tdataglManager.editGl(glfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("5")){
				tdatajzgdManager.editJzgd(jzfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("6")){
				tdataxqyzManager.editXqyz(xfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("7")){
				tdatascManager.editsc(sfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("8")){
				tdatafwyManager.editfwy(ffrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("9")){
				tdataysyManager.editysy(ysfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("10")){
				tdatazzyManager.editzzy(zfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else if(lawobjtype.getId().equals("11")){
				tdataylyManager.edityly(ylfrm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}else{
				tdatalawobjfManager.editLawobjf(frm);
				model.addAttribute("lawobjtypeid", lawobjtypeid);
			}
			
			model.addAttribute("title", "修改"+lawobjtype.getName()+"基础信息");
		}
		if(lawobjtype.getId().equals("1")){
			return "app/data/zfdx/zfdx_gywryEdit";
		}else if(lawobjtype.getId().equals("2")){
			return "app/data/zfdx/zfdx_jsxmEdit";
		}else if(lawobjtype.getId().equals("3")){
			return "app/data/zfdx/zfdx_yyEdit";
		}else if(lawobjtype.getId().equals("4")){
			return "app/data/zfdx/zfdx_glEdit";
		}else if(lawobjtype.getId().equals("5")){
			return "app/data/zfdx/zfdx_jzgdEdit";
		}else if(lawobjtype.getId().equals("6")){
			return "app/data/zfdx/zfdx_xqyzEdit";
		}else if(lawobjtype.getId().equals("7")){
			return "app/data/zfdx/zfdx_scEdit";
		}else if(lawobjtype.getId().equals("8")){
			return "app/data/zfdx/zfdx_fwyEdit";
		}else if(lawobjtype.getId().equals("9")){
			return "app/data/zfdx/zfdx_ysyEdit";
		}else if(lawobjtype.getId().equals("10")){
			return "app/data/zfdx/zfdx_zzyEdit";
		}else if(lawobjtype.getId().equals("11")){
			return "app/data/zfdx/zfdx_ylyEdit";
		}else{
			return "app/data/zfdx/zfdx_qtEdit";
		}
		
	}

	
	/**
	 * 查看一个工业污染源信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjgywry.htm")
	public String TdatagywryInfo(ModelMap model, LawobjfForm frm,GywryForm gfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatagywryManager.editGywry(gfrm);
		model.addAttribute("lawobjfid",gfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_gywryInfo";
		
		
	}
	

	/**
	 * 查看一个建设项目信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjjsxm.htm")
	public String TdatajsxmInfo(ModelMap model, LawobjfForm frm,JsxmForm jfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatajsxmManager.editJsxm(jfrm);
		model.addAttribute("lawobjfid",jfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_jsxmInfo";
		
		
	}
	
	/**
	 * 查看一个锅炉信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjgl.htm")
	public String TdataglInfo(ModelMap model, LawobjfForm frm,GlForm glfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdataglManager.editGl(glfrm);
		model.addAttribute("lawobjfid",glfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_glInfo";
		
		
	}
	
	/**
	 * 查看一个畜禽养殖信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjxqyz.htm")
	public String TdataxqyzInfo(ModelMap model, LawobjfForm frm,XqyzForm xfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdataxqyzManager.editXqyz(xfrm);
		model.addAttribute("lawobjfid",xfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_xqyzInfo";
		
		
	}
	
	/**
	 * 查看一个医院信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjyy.htm")
	public String TdatayyInfo(ModelMap model, LawobjfForm frm,YyForm yyfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatayyManager.edityy(yyfrm);
		model.addAttribute("lawobjfid",yyfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_yyInfo";
		
		
	}
	
	/**
	 * 查看一个建筑工地信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjjzgd.htm")
	public String TdatajzgdInfo(ModelMap model, LawobjfForm frm,JzgdForm jzfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatajzgdManager.editJzgd(jzfrm);
		model.addAttribute("lawobjfid",jzfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_jzgdInfo";
		
		
	}
	
	/**
	 * 查看一个三产信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjsc.htm")
	public String TdatascInfo(ModelMap model, LawobjfForm frm,ScForm sfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatascManager.editsc(sfrm);
		model.addAttribute("lawobjfid",sfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_scInfo";
		
		
	}
	
	/**
	 * 查看一个制造业信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjzzy.htm")
	public String TdatazzyInfo(ModelMap model, LawobjfForm frm,ZzyForm zfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatazzyManager.editzzy(zfrm);
		model.addAttribute("lawobjfid",zfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_zzyInfo";
		
		
	}
	
	/**
	 * 查看一个娱乐业信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjyly.htm")
	public String TdataylyInfo(ModelMap model, LawobjfForm frm,YlyForm ylfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdataylyManager.edityly(ylfrm);
		model.addAttribute("lawobjfid",ylfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_ylyInfo";
		
		
	}
	
	/**
	 * 查看一个饮食业信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjysy.htm")
	public String TdataysyInfo(ModelMap model, LawobjfForm frm,YsyForm ysfrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdataysyManager.editysy(ysfrm);
		model.addAttribute("lawobjfid",ysfrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_ysyInfo";
		
		
	}
	
	/**
	 * 查看一个服务业信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjfwy.htm")
	public String TdatafwyInfo(ModelMap model, LawobjfForm frm,FwyForm ffrm,String lawobjtypeid) {
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatafwyManager.editfwy(ffrm);
		model.addAttribute("lawobjfid",ffrm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_fwyInfo";
		
		
	}
	
	/**
	 * 查看一个其它执法对象信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjqt.htm")
	public String TdataqtInfo(ModelMap model, LawobjfForm frm,String lawobjtypeid) {
		
		TDataLawobjtype lawobjtype=zfdxManager.querylawobjtypeById(lawobjtypeid);
		tdatalawobjfManager.editLawobjf(frm);
		model.addAttribute("lawobjfid",frm.getId());
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		model.addAttribute("title", "查看"+lawobjtype.getName()+"基础信息");
		return "app/data/zfdx/zfdx_qtInfo";
		
		
	}
	
	/**
	 * 查看一个执法对象信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/lawobjfInfo.htm")
	public String TdatalawobjfInfo(ModelMap model, LawobjfForm frm,GywryForm gfrm,String lawobjtypeid) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}  
		    TDataLawobjtype type=zfdxManager.querylawobjtypeById(lawobjtypeid);
		    TDataLawobjf lawobjf=tdatalawobjfManager.getLawobjfById(gfrm.getId());
		   if(lawobjf.getJsxm() !=null){
			   TDataLawobjf jsxm=tdatalawobjfManager.getLawobjfById(lawobjf.getJsxm().getId());
			   model.addAttribute("jsxmid",jsxm.getId());	
		   }
			model.addAttribute("lawobjfid",gfrm.getId());
			model.addAttribute("lawobjtypeid", lawobjtypeid);
			if(type.getLawobjtype()!=null){
				model.addAttribute("lawobjftypeid", type.getLawobjtype().getId());
			}
			
			return "app/data/zfdx/zfdxInfo";
			
			
		}
		/*if(lawobjtype.getId().equals("1")){
			
		}else if(lawobjtype.getId().equals("2")){
			return "app/data/zfdx/zfdx_jsxmEdit";
		}else if(lawobjtype.getId().equals("3")){
			return "app/data/zfdx/zfdx_yyEdit";
		}else if(lawobjtype.getId().equals("4")){
			return "app/data/zfdx/zfdx_glEdit";
		}else if(lawobjtype.getId().equals("5")){
			return "app/data/zfdx/zfdx_jzgdEdit";
		}else if(lawobjtype.getId().equals("6")){
			return "app/data/zfdx/zfdx_xqyzEdit";
		}else if(lawobjtype.getId().equals("7")){
			return "app/data/zfdx/zfdx_scEdit";
		}else if(lawobjtype.getId().equals("8")){
			return "app/data/zfdx/zfdx_fwyEdit";
		}else if(lawobjtype.getId().equals("9")){
			return "app/data/zfdx/zfdx_ysyEdit";
		}else if(lawobjtype.getId().equals("10")){
			return "app/data/zfdx/zfdx_zzyEdit";
		}else if(lawobjtype.getId().equals("11")){
			return "app/data/zfdx/zfdx_ylyEdit";
		}else{
			return "app/data/zfdx/zfdx_qtEdit";
		}*/
	
	/**
	 * 查看一个执法对象信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/choseezfdx.htm" , produces = "application/json")
	public String TdatalawobjfChosee(ModelMap model, String lawobjtypeid, String fzbs) {
		model.addAttribute("ymbiaoshi", fzbs);
		model.addAttribute("lawobjtypeid", lawobjtypeid);
		return "app/data/zfdx/zfdxchosee";
	}
	
	/**
	 * 选择执法对象一个执法对象信息
	 * 
	 * @param id
	 *            菜单Id
	 * @return 区域的初始界面
	 */
	@RequestMapping(value = "/jsxmzfdx.htm" , produces = "application/json")
	public String jsxmlawobjfChosee(ModelMap model, String lawobjtypeid) {

		TDataIndustry tDataIndustry = (TDataIndustry) tdatalawobjfManager.get(TDataIndustry.class, lawobjtypeid);
    	if(tDataIndustry!=null){
    		String lawobjtype = tDataIndustry.getTolawobjtype();
    		model.addAttribute("lawobjtypeid", lawobjtype);
    	}else{
    		model.addAttribute("lawobjtypeid", lawobjtypeid);
    	}
		return "app/data/zfdx/jsxmchosee";
		
	}
}

   









