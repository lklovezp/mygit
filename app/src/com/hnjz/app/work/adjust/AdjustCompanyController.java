package com.hnjz.app.work.adjust;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.org.OrgManager;


@Controller
public class AdjustCompanyController {
	//日志
	private static final Log log=LogFactory.getLog(AdjustCompanyController.class);
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private AdjustCompanyManager adjustCompanyManager;
	@Autowired
	private DicManager dicManager;
	@Autowired
	private ZfdxManager zfdxManager;
	@RequestMapping(value="/adjustCompanys.htm")
	public String queryInformation(ModelMap model,String title){
		this.orgQuery(model, "", "");
		model.addAttribute("title", title);
		return "app/work/adjust/adjustCompany";
	}
	
	/**
	 * 查询所有节点
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/orgQuerylist.json", produces = "application/json")
	public void orgQuery(ModelMap model, String name, String isActive) {
				try {
					JSONArray re = orgManager.queryOrg(name, isActive);//部门树
					model.addAttribute("menu", re.toString());
			
			
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	@RequestMapping(value="/queryAllCompanyByOrgId.htm")
	public String queryAllCompanyByOrgId(ModelMap model,String name,String code,String orgId, String qyzt, String kzlx,String isActive,String page,String pageSize){
		
		try {
			model.put("orgId",orgId);
		//	log.info("进入dsfhklaewirowerisdfj方法====="+code);
			TDataLawobjtype type=zfdxManager.gettype(code);
			String codeName=type.getName();
			String headTitle="";
			if(name==null||codeName==null){
				 headTitle="";
			}else{
				headTitle=name+"监管"+codeName;
			}
			 
			model.put("headTitle", headTitle);
			//FyWebResult fr= lawobjManager.queryGywryList("","","",orgId, "", "", "Y", "-1", "-1");
		//	log.info("进入方法=====");
			model.put("code", code);
			//model.addAttribute("",fr.getRows());
		} catch (Exception e) {
			log.error("查询企业出错",e);
		}
		return "app/work/adjust/queryCompanyList";
	}
	/*
	@RequestMapping(value="/queryDicEnum.json")
    public void queryDicEnum(ModelMap model){
    	try {
    		FyWebResult frFyWebResult=adjustCompanyManager.queryDicTypeEnum();
    		
    		JsonResultUtil.fyWeb(model, frFyWebResult);
    		
		} catch (Exception e) {
			log.error("查询企业出错",e);
		}
    	
    }*/
	
	@RequestMapping(value = "/queryCompanyByCodeAndOrgId.json")
    public void queryCompanyByCodeAndOrgId(ModelMap model,String code,String name,String regionId,String orgId, String qyzt, String kzlx,String isActive,String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = adjustCompanyManager.queryCompanyByCodeAndOrgId(code, orgId, name, regionId, qyzt, kzlx, isActive, page, rows);
			JsonResultUtil.fyWeb(model, re);
    	//	log.info("orgId=sdfdsfgfh======"+orgId);
//    		if(orgId.equals("")){
//    			FyWebResult re=new FyWebResult();
//    		}else{
//    			FyWebResult re = adjustCompanyManager.queryCompanyByCodeAndOrgId(code, orgId, name, regionId, qyzt, kzlx, isActive, page, rows);
//    			JsonResultUtil.fyWeb(model, re);
//    		}
//			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
	@RequestMapping(value = "/queryAllCompany.htm")
	public String queryAllCompany(ModelMap model,String code,String name,String orgId,String shaiXuan,String orgName){
		model.put("orgName",orgName);
		model.put("orgId",orgId);
		//log.info("orgId=sdfdsfgfh======"+orgId);
		model.put("name", name);
		model.put("shaiXuan", shaiXuan);
		model.put("code", code);
		return "app/work/adjust/queryAllCompany";
	}
	@RequestMapping(value = "/queryAllCompanyByCode.json")
	public void queryAllCompanyByCode(ModelMap model,String code,String page,String rows,String orgId,String name,String shaiXuan){
		try {
			model.put("orgId",orgId);
			//log.info("orgId======="+orgId);
			model.put("name", name);			
			model.put("code", code);
			//log.info("进入方法====="+code);
			if(shaiXuan.equals("")){
				FyWebResult re =adjustCompanyManager.queryCompanyByCode(code, "", "",page,rows);
				JsonResultUtil.fyWeb(model, re);
			}else{
				FyWebResult re=adjustCompanyManager.shaiXuanCompanyByCode(code, name, "", page, rows);
				JsonResultUtil.fyWeb(model, re);
     		}
			
		} catch (Exception e) {
			log.error("查询出错：", e);
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/saveChoseeCompany.json")
	public void updateOrg(ModelMap model,String rows,String orgId,String code){
		try {
			JSONArray array=new JSONArray(rows);
			String result=adjustCompanyManager.updateOrg(orgId, array, code);
			if(result.equals("success")){
				JsonResultUtil.suncess(model, "保存成功！");
				
				//response.sendRedirect("queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code);
			}else{
				JsonResultUtil.fail(model, result);
			}
		} catch (Exception e) {
			log.error("查询出错：", e);
			e.printStackTrace();
		}
		//this.queryAllCompanyByOrgId(model, "", code, orgId, "", "", "Y", "1", "20");

	}
	
	@RequestMapping(value="/updateOrgToNull.json")
	public void updateOrgToNull(ModelMap model,String companyId,String code,String orgId){
		try {
			//JSONArray array=new JSONArray(rows);
			String result=adjustCompanyManager.deleteOrgByCompanyId(orgId, companyId, code);
			if(result.equals("success")){
				JsonResultUtil.suncess(model, "删除成功！");
				
				//response.sendRedirect("queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code);
			}else{
				JsonResultUtil.fail(model, result);
			}
		} catch (Exception e) {
			log.error("查询出错：", e);
			e.printStackTrace();
		}
		//this.queryAllCompanyByOrgId(model, "", code, orgId, "", "", "Y", "1", "20");

	}
	
	//筛选无监管部门的执法对象
	
	
	
}
