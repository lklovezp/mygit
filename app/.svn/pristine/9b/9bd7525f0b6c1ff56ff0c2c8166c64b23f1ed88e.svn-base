package com.hnjz.mobile.sjtb;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.rwgl.RwglManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.wf.AbsJbpmController;

@Controller
public class sjtbMobileController  extends AbsJbpmController{

 @Autowired
 private sjtbMoManager    sjtbMoManager;
 
 @Autowired
 private RwglManager    rwglManager;
 
 	/**
	 * 获取任务类型的方法：任务类型数据
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/allrwlxData.mo")
	public void allrwlxData(String strUpdate, ModelMap model) {
		try {
			//任务类型列表
			List<Map<String, String>> rwlxlistMap = sjtbMoManager.getTaskType(strUpdate);
	        model.put("rwlxlistMap", rwlxlistMap);
		}catch (Exception e) {
	        log.error("查询出错：", e);
	    }
	}
	
	/**
	 * 获取执法对象的方法：执法对象基础信息
	 * @param model
	 */
	@RequestMapping(value ="/allLawobj.mo")
	public void queryAllLawobj(String strUpdate, ModelMap model){
		try{
			//所有执法对象
			List<Map<String,String>> AllLawobj= sjtbMoManager.queryAllLawobj(strUpdate);
			model.put("AllLawobj", AllLawobj);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	 
	/**
	 * 详情页面：执法对象类型信息
	 * @param model
	 */
	@RequestMapping(value ="/Lawobjtype.mo")
	public void queryLawobjtype(String strUpdate, ModelMap model){
		try{
			//所有执法对象类型
			List<Map<String,String>> Lawobjtype= sjtbMoManager.queryLawobjtype(strUpdate);
			model.put("Lawobjtype", Lawobjtype);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取全部人员的方法：人员列表数据
	 * 
	 * @param model
	 */
	@RequestMapping(value ="/alluserData.mo")
	public void queryAllUser(String strUpdate, ModelMap model){
		try{
			//所有人员类别
			List<Map<String,String>> AllUserMap = sjtbMoManager.queryAllUser(strUpdate);
			model.put("AllUserMap", AllUserMap);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的执法附件数据
	 * @param model
	 */
	@RequestMapping(value ="/allDataFile.mo")
	public void queryallDataFile(String strUpdate, ModelMap model){
		try{
			//所有执法文件附件
			List<Map<String,String>> allDataFile= sjtbMoManager.queryallDataFile(strUpdate);
			model.put("allDataFile", allDataFile);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的区域数据
	 * @param model
	 */
	@RequestMapping(value ="/allDataArea.mo")
	public void queryallDataArea(String strUpdate, ModelMap model){
		try{
			//所有区域数据
			List<Map<String,String>> allDataArea= sjtbMoManager.queryallDataArea(strUpdate);
			model.put("allDataArea", allDataArea);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的行政区划数据
	 * @param model
	 */
	@RequestMapping(value ="/allDataRegion.mo")
	public void queryallDataRegion(String strUpdate, ModelMap model){
		try{
			//所有行政区划数据
			List<Map<String,String>> allDataRegion= sjtbMoManager.queryallDataRegion(strUpdate);
			model.put("allDataRegion", allDataRegion);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的执法文件数据
	 * @param model
	 */
	@RequestMapping(value ="/allLawdocData.mo")
	public void queryallLawdocData(String strUpdate, ModelMap model){
		try{
			//所有执法文件附件
			List<Map<String,String>> allLawdocData= sjtbMoManager.queryallLawdocData(strUpdate);
			model.put("allLawdocData", allLawdocData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的角色数据
	 * @param model
	 */
	@RequestMapping(value ="/allRoleData.mo")
	public void queryallRoleData(String strUpdate, ModelMap model){
		try{
			//所有角色数据
			List<Map<String,String>> allRoleData= sjtbMoManager.queryallRoleData(strUpdate);
			model.put("allRoleData", allRoleData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的部门数据
	 * @param model
	 */
	@RequestMapping(value ="/allOrgData.mo")
	public void queryallOrgData(String strUpdate, ModelMap model){
		try{
			//所有部门数据
			List<Map<String,String>> allOrgData= sjtbMoManager.queryallOrgData(strUpdate);
			model.put("allOrgData", allOrgData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的行业类型数据
	 * @param model
	 */
	@RequestMapping(value ="/allIndustryData.mo")
	public void queryallIndustryData(String strUpdate, ModelMap model){
		try{
			//所有行业类型数据
			List<Map<String,String>> allIndustryData= sjtbMoManager.queryallIndustryData(strUpdate);
			model.put("allIndustryData", allIndustryData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的违法类型数据
	 * @param model
	 */
	@RequestMapping(value ="/allIllegaltypeData.mo")
	public void queryallIllegaltypeData(String strUpdate, ModelMap model){
		try{
			//所有违法类型数据
			List<Map<String,String>> allIllegaltypeData= sjtbMoManager.queryallIllegaltypeData(strUpdate);
			model.put("allIllegaltypeData", allIllegaltypeData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的人员部门关联数据
	 * @param model
	 */
	@RequestMapping(value ="/allUserOrgData.mo")
	public void queryallUserOrgData(String strUpdate, ModelMap model){
		try{
			//所有人员相关联表数据
			List<Map<String,String>> AllUserOrgMap = sjtbMoManager.queryAllUserOrg(strUpdate);
			model.put("AllUserOrgMap", AllUserOrgMap);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的人员角色关联数据
	 * @param model
	 */
	@RequestMapping(value ="/allUserRoleData.mo")
	public void queryallUserRoleData(String strUpdate, ModelMap model){
		try{
			//所有人员相关联表数据
			List<Map<String,String>> AllUserRoleMap = sjtbMoManager.queryAllUserRole(strUpdate);
			model.put("AllUserRoleMap", AllUserRoleMap);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的待办任务的数据
	 * @param model
	 */
	@RequestMapping(value ="/allDbrwData.mo")
	public void queryallDbrwData(String strUpdate, ModelMap model){
		try{
			//所有待办任务相关的数据
			List<Map<String,String>> allDbrwData = sjtbMoManager.queryallDbrwData(strUpdate);
			model.put("allDbrwData", allDbrwData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有问题项的数据
	 * @param model
	 */
	@RequestMapping(value ="/allDataRecordData.mo")
	public void queryallDataRecordData(String strUpdate, ModelMap model){
		try{
			//所有人员相关联表数据
			List<Map<String,String>> allDataRecordData = sjtbMoManager.queryallDataRecordData(strUpdate);
			model.put("allDataRecordData", allDataRecordData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取数据方法：所有的关联类型数据
	 * @param model
	 */
	@RequestMapping(value ="/allRelateData.mo")
	public void queryallallRelateData(String strUpdate, ModelMap model){
		try{
			//所有关联表的数据
			List<Map<String,String>> allRelateData= sjtbMoManager.queryallIllegaltypeData(strUpdate);
			model.put("allRelateData", allRelateData);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 获取是否要同步的方法：所有的表进行是否要同步的判断
	 * @param model
	 */
	@RequestMapping(value ="/TableDataIsSynch.mo")
	public void TableDataIsSynch(String strUpdate, String tableName, ModelMap model){
		try{
			//所有关联表的数据
			List<Map<String,String>> TableDataIsSynch= sjtbMoManager.TableDataIsSynch(strUpdate, tableName);
			model.put("TableDataIsSynch", TableDataIsSynch);
		}catch(Exception e){
			log.error("查询出错：", e);
		}
	}
	
	/**
     * 
     * 函数介绍：查询：执法检查待办任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getZfjcDbrwList.mo", produces = "application/json")
    public void getZfjcDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getZfjcDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 
     * 函数介绍：查询：信访投诉待办任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getXftsDbrwList.mo", produces = "application/json")
    public void getXftsDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getXftsDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
    
    /**
     * 
     * 函数介绍：查询：日常办公待办任务列表。
     * 输入参数：rwmc：任务名称；rwly：任务来源；pfr：派发人；rwzt：任务状态。
     * 返回值：
     */
    @RequestMapping(value = "/getRcbgDbrwList.mo", produces = "application/json")
    public void getRcbgDbrwList(ModelMap model, String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String page, String pageSize) {
        try {
            page = StringUtils.defaultIfBlank(page, "1");
            FyWebResult re = rwglManager.getRcbgDbrwList(rwmc,rwly,pfrId, rwzt, tasktype, zfdxType, pfStarttime, pfEndtime, gdStarttime, gdEndtime,"", page, pageSize);
            JsonResultUtil.fyWeb(model, re);
        } catch (Exception e) {
            log.error("查询出错：", e);
        }
    }
	
} 

	

