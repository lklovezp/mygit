package com.hnjz.mobile.data.xxcx;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.TaskandlawobjForm;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.xxgl.complaint.ComplaintForm;
import com.hnjz.app.data.xxgl.complaint.ComplaintManager;
import com.hnjz.app.data.xxgl.lawobj.HpxxForm;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.mobile.common.CommonMobileManager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-4-14
 * 功能描述：执法对象控制层(终端)
 *
 */
@Controller
public class LawobjMobileController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(LawobjMobileController.class);
    
    @Autowired
    private LawobjMobileManager lawobjMobileManager;
    @Autowired
    private LawobjManager lawobjManager;
    @Autowired
    private CommonManager commonManager;
    @Autowired
    private CommonMobileManager commonMobileManager;
    @Autowired
    private ComplaintManager complaintManager;
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryGywryList.mo")
    public void queryGywryListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryGywryList(name, regionId, orgId,  kzlx, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryJsxmList.mo")
    public void queryJsxmListMo(ModelMap model,String name,String regionId,String orgId,String hylx,String qyzt,String lawobjId,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryJsxmList(name, regionId, orgId, hylx, null, lawobjId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
    
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryYyxxList.mo")
    public void queryYyxxListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYyxxList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryJzgdList.mo")
    public void queryJzgdListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String sgdwmc,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryJzgdList(name, regionId, orgId, qyzt,sgdwmc,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryGlxxList.mo")
    public void queryGlxxListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryGlxxList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryScxxList.mo")
    public void queryScxxListMo(ModelMap model,String name,String industryId,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryScxxList(name, industryId, regionId, orgId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
    
    /**
     * 加载列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryXqyzList.mo")
    public void queryXqyzListMo(ModelMap model,String name,String regionId,String orgId,String qyzt, String kzlx,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryXqyzList(name, regionId, orgId, qyzt, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
    
    
    /**
     * 执法对象详情界面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/lawobjInfo.mo")
    public void getLawobjBaseInfo(ModelMap model,String id){
    	TDataLawobj lawobj = (TDataLawobj) lawobjMobileManager.get(TDataLawobj.class, id);
    	JSONArray array = lawobjMobileManager.getLawobjInfo(lawobj);
    	model.addAttribute("baseInfo", array.toString());//基本信息
    	model.addAttribute("baseInfoLawobjtype", lawobj.getLawobjtype());//企业所属行业类型值
    	Map<String,String> map = null;
    	//单个附件
		map = lawobjMobileManager.getOneFileListByPid(lawobj.getId());
		model.addAttribute("oneFileInfo",map==null?"":map);//附件列表
		FyWebResult result = lawobjManager.queryHpxxList(lawobj.getId(),"1","1000");
		model.addAttribute("hpxxList",result.getRows());//附件列表
    	//单个投诉信息
		map = complaintManager.queryOneComplaint(lawobj.getId());
		model.addAttribute("oneComplaint",map==null?"":map);
    	//单个执法历史信息
    	map = lawobjMobileManager.getOneZfHistory(lawobj.getId());
    	model.addAttribute("oneZfHistory",map==null?"":map);
    }
    
    
    /**
     * 跳转到环评信息详情页面
     * 
     * @param model {@link ModelMap}
     * @return 
     */
    @RequestMapping(value = "/hpxxInfo.mo")
    public void hpxxInfo(ModelMap model,HpxxForm hpxxForm) {
    	if(hpxxForm!=null && null != hpxxForm.getId()){
    		hpxxForm = lawobjManager.getHpxxInfo(hpxxForm);
    		model.put("hpxxForm", hpxxForm);
    		model.put("fileList", commonMobileManager.queryFileList(hpxxForm.getId(), null).toString());
    	}
    }
    
    /**
     * 执法对象详情界面（更多执法历史记录）
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryZfHistoryList.mo")
    public void queryZfHistoryList(ModelMap model, String lawobjid, String page, String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = lawobjMobileManager.queryZfHistoryList(lawobjid, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
    
    
    /**
	 * 
	 * 函数介绍：保存任务中选择的执法对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/querySelectLawobjList.mo")
	@ResponseBody
	public List<TaskandlawobjForm> querySelectLawobjList(ModelMap model,String rwid,String lawobjtype){
		return commonManager.querySelectLawobjList(rwid,lawobjtype);
	}
	
	/**
	 * 
	 * 函数介绍：选择执法对象界面，列表数据
	 * 输入参数：任务id、执法对象类型、当前页、每页显示条数
	 * 返回值：
	 */
	@RequestMapping(value = "/queryLawobjList.mo")
	public void queryLawobjList(ModelMap model,String name,String regionId,String orgId,String lawobjtype,String industryId,String qyzt, String isActive, String kzlx,String lawobjId,String sgdwmc,String isTcPage,String page, String pageSize){
		page = StringUtils.defaultIfBlank(page, "1");
		FyWebResult re = null;
		if(StringUtils.isNotBlank(industryId) && "Y".equals(isTcPage)){
			TDataIndustry tDataIndustry = (TDataIndustry) lawobjManager.get(TDataIndustry.class, industryId);
	    	lawobjtype = tDataIndustry.getTolawobjtype();
		}
		try {
			if("01".equals(lawobjtype)){
				re = lawobjMobileManager.queryGywryList(name, regionId, orgId, kzlx, qyzt, page, pageSize);
			}else if("02".equals(lawobjtype)){
				re = lawobjMobileManager.queryJsxmList(name, regionId, orgId, industryId, "Y", lawobjId, qyzt, page, pageSize);
			}else if("03".equals(lawobjtype)){
				re = lawobjMobileManager.queryYyxxList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("04".equals(lawobjtype)){
				re = lawobjMobileManager.queryGlxxList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("05".equals(lawobjtype)){
				re = lawobjMobileManager.queryJzgdList(name, regionId, orgId, qyzt, sgdwmc, page, pageSize);
			}else if("06".equals(lawobjtype)){
				re = lawobjMobileManager.queryScxxList(name, industryId, regionId, orgId, qyzt, page, pageSize);
			}else if("07".equals(lawobjtype)){
				re = lawobjMobileManager.queryXqyzList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("08".equals(lawobjtype)){
				re = lawobjMobileManager.queryFwyList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("09".equals(lawobjtype)){
				re = lawobjMobileManager.queryYsyList(name, regionId, orgId, qyzt, page, pageSize);
			}else if("10".equals(lawobjtype)){
				re = lawobjMobileManager.queryZzyList(name, regionId, orgId,qyzt, page, pageSize);
			}else if("11".equals(lawobjtype)){
				re = lawobjMobileManager.queryYlyList(name, regionId, orgId, qyzt, page, pageSize);
			}
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 函数介绍：保存选择的执法对象（终端专用）
	
	 * 输入参数：任务id、执法对象类型、执法对象id（用逗号隔开）
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveChoseeLawobj.mo")
	public void saveChoseeLawobj(ModelMap model,String rwid,String lawobjtype,String lawobjid){
		try {
			lawobjMobileManager.saveChoseeLawobj(rwid, lawobjtype, lawobjid);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
     * 
     * 函数介绍：三产信息查询 投诉信息列表数据
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryComplaintList.mo")
    public void queryComplaintList(ModelMap model,String lawobjid,String page,String pageSize){
    	try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = complaintManager.queryComplaintList(null,null, null, null, null, "Y", lawobjid, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 跳转到详情页面
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/complaintInfo.mo")
    public void complaintInfo(ModelMap model,ComplaintForm complaintForm){
    	if(complaintForm!=null && StringUtils.isNotBlank(complaintForm.getId())){
    		complaintForm = complaintManager.getComplaintInfo(complaintForm);
    	}
    	model.addAttribute("complaintForm", complaintForm);
    }
    
    /**
     * 加载列表数据：服务业信息
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryFwyList.mo")
    public void queryFwyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryFwyList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据：饮食业信息
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryYsyList.mo")
    public void queryYsyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYsyList(name, regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据：制造业信息
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryZzyList.mo")
    public void queryZzyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryZzyList(name, regionId, orgId,qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
     * 加载列表数据：娱乐业信息
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/queryYlyList.mo")
    public void queryYlyList(ModelMap model,String name,String regionId, String orgId, String qyzt,String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawobjMobileManager.queryYlyList(name,regionId, orgId, qyzt, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
	
}
