package com.hnjz.app.data.xxgl.tslawobj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.doublerandom.DoubleRandomManager;
import com.hnjz.app.data.enums.MonthEnum;
import com.hnjz.app.data.po.TBizHistoryconfigcheck;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataSpeciallawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

@Controller
public class TslawobjController {
	private static final Log log=LogFactory.getLog(TslawobjController.class);
	@Autowired
	private TslawobjManager tslawobjManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private DoubleRandomManager doubleRandomManager;
	@Autowired
	protected Dao dao;
	/**
	 * 跳转到特殊企业监管页面
	 * 
	 * 
	 * */
	@RequestMapping(value="teShuLawObj.htm")
	public String speailLawobjList(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/sjcc/specialLawobjList";
	}
	/**
	 * 跳转到添加特殊企业监管条件限制界面
	 * 
	 * 
	 * */
	@RequestMapping(value="jumpSpecialLawobj.htm")
	public String jumpSpecialLawobj(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/sjcc/jumpSpecialLawobj";
	}
	/**
	 * 跳转到添加特殊企业监管界面
	 * 
	 * 
	 * */
	@RequestMapping(value="jumpSpecialLawobjAdd.htm")
	public String jumpSpecialLawobjAdd(ModelMap model,String year,String quarter){
		model.addAttribute("quarter", quarter);
		model.addAttribute("year", year);		
		return "app/data/sjcc/changeSpeciallawobj";
	}
	/**
	 * 批量保存特殊监管企业信息
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/specialLawobjSave.json", produces = "application/json")
	public void specialLawobjSave(ModelMap model,String ids,String names,String year,String quarter) throws Exception {
		try {
			tslawobjManager.saveSpecialLawobj(ids, names, year, quarter);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	
	/**
	 * 查询特殊监管企业列表
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/specialLawobjlist.json", produces = "application/json")
	public void specialLawobjlist(ModelMap model,String year,String quarter, String lawobjname, String lawobjtype,  String page,
			String pageSize) throws Exception {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re =tslawobjManager.querySpecialLawobj(year, quarter, lawobjname, lawobjtype, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (AppException e) {
			log.error("查询出错：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 删除特殊监管企业
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delSpecialLawobj.json", produces = "application/json")
	public void delSpecialLawobj(ModelMap model,String id) throws Exception {
		try {
			tslawobjManager.removeSpecialLawobj(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("查询出错：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 跳转到随机抽查比例设定界面
	 * 
	 * 
	 * */
	@RequestMapping(value="randomSet.htm")
	public String configCheck(ModelMap model,String areaid,String title){
		try {
			if(StringUtil.isBlank(areaid)){
				areaid=CtxUtil.getAreaId();
			}
			ConfigCheckForm configCheckForm=tslawobjManager.queryConfigCheck(areaid);
			model.addAttribute("configCheckForm", configCheckForm);
			model.addAttribute("areaid", areaid);
			List<TSysUser> users=tslawobjManager.queryUserByAreaidAndIsZaiBianZaiGang(areaid);
			model.addAttribute("userSize", users.size());
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/sjcc/configCheck";
	}
	
	/**
	 * 保存随机抽查企业设置规则
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/configCheckSave.json", produces = "application/json")
	public void configCheckSave(ModelMap model,ConfigCheckForm configCheckForm,String areaid) throws Exception {
		
		try {
			tslawobjManager.configCheckSave(configCheckForm,areaid);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (AppException e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 跳转到随机抽查派发设置界面
	 * 
	 * 
	 * */
	@RequestMapping(value="configPf.htm")
	public String configPf(ModelMap model,String areaid,String title){
		try {
			TSysUser u = CtxUtil.getCurUser();
			if(StringUtils.isBlank(areaid)){
				areaid = u.getAreaId();
			}
			model.addAttribute("areaid", areaid);
			Calendar calendar = new GregorianCalendar();  
			Calendar now = Calendar.getInstance();
			String year= String.valueOf(now.get(Calendar.YEAR));
		   // int month = tslawobjManager.getQuarterInMonth(calendar.get(Calendar.MONTH), true);  
			model.addAttribute("title", title);
			ConfigPfForm con= tslawobjManager.queryConfigPfForm(year, String.valueOf("1"),areaid);
			model.addAttribute("configPfForm", con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/sjcc/configPf";
	}
	/**
	 * 派发设置界面数据加载
	 * 
	 * 
	 * */
	@RequestMapping(value="configPfList.json")
	@ResponseBody
	public List<ConfigPfSjForm> configPfList(ModelMap model,String year,String quarter,String areaid,String type,String month){
		List<ConfigPfSjForm> consjList=new ArrayList<ConfigPfSjForm>();
		ConfigPfForm con=new ConfigPfForm();
		try {
			con=tslawobjManager.queryConfigPfFormByType(year, month, type, areaid);
			if(con!=null){
			
				consjList= con.getList();
				//String bjsx=con.getBjsx();
				//model.addAttribute("bjsx",bjsx);
			}
			
			return consjList;
		} catch (Exception e) {
			
			log.error("查询错误：", e);
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 保存随机派发设置信息
	 * 
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/configPfSave.json", produces = "application/json")
	public void configPfSave(ModelMap model,ConfigPfForm configPfForm,String areaid) throws Exception {
		try {
			//tslawobjManager.configCheckSave(configCheckForm);
			tslawobjManager.configPfSave(configPfForm,areaid);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 保存特殊企业随机派发设置信息
	 * 
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/specliallConfigPfSave.json", produces = "application/json")
	public void specliallConfigPfSave(ModelMap model,ConfigPfForm configPfForm,String areaid) throws Exception {
		try {
			//tslawobjManager.configCheckSave(configCheckForm);
			tslawobjManager.specliallConfigPfSave(configPfForm,areaid);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	

	/**
	 * 跳转到抽查统计页面
	 * 
	 * 
	 * */
	@RequestMapping(value="checkedCount.htm")
	public String toCheckedCount(ModelMap model,String year,String quarter,String month,String areaid,String title){
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		if(StringUtil.isBlank(year)){
			Calendar calendar = Calendar.getInstance();
			year = String.valueOf(calendar.get(Calendar.YEAR));
			quarter = "0";
		}
		try {
			List<CheckedCountForm> list=tslawobjManager.queryCheckedCountFormList(year, quarter,month, areaid);
			model.put("year", year);
			model.put("quarter", quarter);
			model.addAttribute("cfs", list);
		} catch (Exception e) {
			log.error("查询错误：", e);
			e.printStackTrace();
		}
		model.addAttribute("title", title);
		return "app/data/sjcc/checkedCount";
	}
	/**
	 * 函数介绍：统计列表界面
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/exportCheckedCountForm.json")
	public void exportCheckedCountForm(ModelMap model,String year,String quarter,String areaid,HttpServletResponse res){
		try {
			tslawobjManager.exportCheckedCountForm(year, quarter, areaid, res);
		} catch (Exception e) {
			log.error("导出出错：", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 函数介绍：统计列表界面
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryLowPfbl.json")
	public void queryLowPfbl(ModelMap model,String year,String quarter,String areaid){
		try {
			ConfigCheckForm tf=tslawobjManager.queryConfigCheck(areaid);
			
			String zs= tslawobjManager.queryLowPfbl(year,quarter,areaid);
			model.addAttribute("zs", zs);
			model.addAttribute("tsqybl", tf.getTsjgqybl());
		} catch (Exception e) {
			log.error("导出出错：", e);
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到随机抽查特殊企业派发设置界面
	 * 
	 * 
	 * */
	@RequestMapping(value="specialPfList.htm")
	public String specialPfList(ModelMap model,String areaid,String title){
		try {
			TSysUser u = CtxUtil.getCurUser();
			if(StringUtils.isBlank(areaid)){
				areaid = u.getAreaId();
			}
			model.addAttribute("areaid", areaid);
			Calendar calendar = new GregorianCalendar();  
			Calendar now = Calendar.getInstance();
			String year= String.valueOf(now.get(Calendar.YEAR));
		   // int month = tslawobjManager.getQuarterInMonth(calendar.get(Calendar.MONTH), true);  
		    TBizHistoryconfigcheck thcc=tslawobjManager.queryTBizHistoryconfigcheck(year, String.valueOf("1"), areaid); 
		    ConfigCheckForm ccfs= tslawobjManager.queryConfigCheck(areaid);
		    if(StringUtil.isNotBlank(ccfs.getTsjgqybl())){
		    	if(!ccfs.getTsjgqybl().equals(thcc.getTsjgqybl())){
		    		thcc.setTsjgqybl(ccfs.getTsjgqybl());
		    	}
		    }
		    	
		    //特殊企业设置规则
		    model.addAttribute("tssz", thcc.getTsjgqybl());
		    ConfigPfForm con=new ConfigPfForm();
		    if(StringUtil.isNotBlank(thcc.getTsjgqybl())){
				    con= tslawobjManager.specliallQueryConfigPfForm(year, String.valueOf("1"), "", "2", areaid);
		    }
            if(StringUtil.isNotBlank(thcc.getTsjgqybl())&&"2".equals(thcc.getTsjgqybl())){
		    	con.setMonth("1");
		    }
		    model.addAttribute("configPfForm", con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("title", title);
		return "app/data/sjcc/specialPfList";
	}
	
	/**
	 * 派发设置界面数据加载
	 * 
	 * 
	 * */
	@RequestMapping(value="speciallConfigPfList.json")
	@ResponseBody
	public List<ConfigPfSjForm> speciallConfigPfList(ModelMap model,String year,String quarter,String areaid,String type,String month){
		List<ConfigPfSjForm> consjList=new ArrayList<ConfigPfSjForm>();
		ConfigPfForm con=new ConfigPfForm();
		try {
			con= tslawobjManager.specliallQueryConfigPfForm(year, quarter, month, "2", areaid);
			if(con!=null){
			
				consjList= con.getList();
			}
			
			return consjList;
		} catch (Exception e) {
			
			log.error("查询错误：", e);
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * 
	 * 函数介绍：月下拉列表
	 * 
	 */
	@RequestMapping(value = "/monthList.json")
	@ResponseBody
	public List<Combobox> quarterList(ModelMap model,String quarter) {
		List<Combobox> list=new ArrayList<Combobox>();
		try {
			list= tslawobjManager.queryMonthByQuarter(quarter);
		} catch (Exception e) {
			log.error("查询出错：", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 
	 * 函数介绍：月下拉列表
	 * 
	 */
	@RequestMapping(value = "/newMonthList.json")
	@ResponseBody
	public List<Combobox> newMonthList(ModelMap model,String quarter) {
		List<Combobox> list=new ArrayList<Combobox>();
		try {
			list= MonthEnum.getMonthEnumList();
		} catch (Exception e) {
			log.error("查询出错：", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 查询特殊派发设置是按月还是按季度
	 * 
	 * 
	 * */
	@RequestMapping(value="configCheckTszz.json")
	public void configCheckTszz(ModelMap model,String year,String quarter,String areaid){
		try {
		    TBizHistoryconfigcheck thcc=tslawobjManager.queryTBizHistoryconfigcheck(year, quarter, areaid); 
		    if(StringUtil.isBlank(thcc.getTsjgqybl())){
		    	ConfigCheckForm ccfs= tslawobjManager.queryConfigCheck(areaid);
		    	
		    	if(StringUtil.isNotBlank(ccfs.getTsjgqybl())){
		    		thcc.setTsjgqybl(ccfs.getTsjgqybl());
		    	}
		    	
		    }
			model.addAttribute("tszz", thcc.getTsjgqybl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 将上年度违法企业转为year的特殊企业
	 * 
	 * 
	 * */
	@RequestMapping(value="	toSpecialLawobj.json")
	public void toSpecialLawobj(ModelMap model,String year){
		try {
			if(StringUtil.isNotBlank(year)){
				tslawobjManager.saveForYeartoSpecail(year);
				JsonResultUtil.suncess(model, "本年四个季度设置完成");
			}
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查询特殊企业
	 * 
	 * 
	 * */
	@RequestMapping(value="queryForSpecialLawobj.json")
	public void queryForSpecialLawobj(ModelMap model,String year){
		try {
		    List<TDataSpeciallawobj> list=tslawobjManager.queryForSpecialLawobj(year);
		    if(list.size()>0){
				model.addAttribute("tsize", "1");
		    }else{
		    	model.addAttribute("tsize", "0");
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转到随机抽查派发设置界面
	 * 
	 * 
	 * */
	@RequestMapping(value="queryZjcList.htm")
	public String queryZjcList(ModelMap model,String areaid,String quarter,String year,String type){
		/*TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}*/
		model.addAttribute("areaid", areaid);
		if(StringUtil.isBlank(year)){
			Calendar calendar = Calendar.getInstance();
			year = String.valueOf(calendar.get(Calendar.YEAR));
		}
		model.addAttribute("year", year);
		model.addAttribute("quarter", quarter);
		model.addAttribute("type", type);
		String s="";
		if("0".equals(quarter)){
			s="全年";
		}else if("1".equals(quarter)){
			s="第一季度";
		}else if("2".equals(quarter)){
			s="第二季度";
		}else if("3".equals(quarter)){
			s="第三季度";
		}else if("4".equals(quarter)){
			s="第四季度";
		}
		if(StringUtil.isNotBlank(type)){
			if(type.equals("0")){
				
			}else if(type.equals("1")){
				s=s+"一般排污单位";
			}else if(type.equals("2")){
				s=s+"重点排污单位";
			}else if(type.equals("3")){
				s=s+"特殊排污单位";
			}else if(type.equals("4")){
				s=s+"随机抽查信息公开数量";
			}
		}
		model.addAttribute("title", year+"年"+s+"随机抽查结果");
		return "app/data/sjcc/zjcList";
	}
	/**
	 * 查询企业信息
	 * 
	 * 
	 * */
	@RequestMapping(value="queryCountLawObjList.json")
	public void queryCountLawObjList(ModelMap model,String areaid,String year,String quarter,String type,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re =tslawobjManager.queryCountLawObjList(areaid, year, quarter,type,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		
	}
	
	/**
	 * 跳转违法统计详情页面
	 * 
	 * 
	 * */
	@RequestMapping(value="queryWfLawobjList.htm")
	public String queryWfLawobjList(ModelMap model,String areaid,String quarter,String year,String type){
		model.addAttribute("year", year);
		model.addAttribute("quarter", quarter);
		model.addAttribute("areaid", areaid);
		model.addAttribute("type", type);
		String s="";
		if("0".equals(quarter)){
			s="全年";
		}else if("1".equals(quarter)){
			s="第一季度";
		}else if("2".equals(quarter)){
			s="第二季度";
		}else if("3".equals(quarter)){
			s="第三季度";
		}else if("4".equals(quarter)){
			s="第四季度";
		}
		model.addAttribute("title", year+"年"+s+"随机抽查发现违法企业");
		return "app/data/sjcc/queryWfLawobjList";
	}
	/**
	 * 查询企业信息
	 * 
	 * 
	 * */
	@RequestMapping(value="queryWfLawobjListInfo.json")
	public void queryWfLawobjListInfo(ModelMap model,String areaid,String year,String quarter,String type,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re =tslawobjManager.queryCountWFLawObjList(areaid, year, quarter, type, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		
	}
	
	/**
	 * 跳转到随机抽查样本页面
	 * 
	 * 
	 * */
	@RequestMapping(value="sjccybcx.htm")
	public String sjccybcxList(ModelMap model,String title){
		model.addAttribute("title", title);
		model.put("title", title);
    	//默认显示本部门信息
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/sjcc/sjcc_lawobjList";
	}
	
	/**
     * 加载随机抽查对象列表数据
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/querySjccLawobjList.json")
    public void querySjccLawobjList(ModelMap model,String name,String lawobjType,String regionId,String orgId, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = tslawobjManager.querySjccLawobjList(name, lawobjType,regionId,orgId, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
    }
    
    /**
	 * 
	 * 函数介绍：导出执法对象样本综合信息
	 * 输入参数：
	 * 返回值：
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportYbLawobjList.json")
	public void exportYbLawobjList(ModelMap model,String name, String lawobjType, String regionId, String regionName,String orgId,String orgName,HttpServletResponse res) throws Exception{
		tslawobjManager.exportYbLawobjList(name,lawobjType,regionId, regionName,orgId,orgName, res);
	}
	
	/**
	 * 跳转到随机抽查企业类型统计界面
	 * 
	 * 
	 * */
	@RequestMapping(value="lawobjtypeStatistics.htm")
	public String toLawobjtypeStatistics(ModelMap model,String title,String year,String quarter,String areaid){
		if(StringUtil.isBlank(year)){
			Calendar calendar = Calendar.getInstance();
			year = String.valueOf(calendar.get(Calendar.YEAR));
			
		}
		if(StringUtil.isBlank(quarter)){
			quarter = "0";
		}
		if(StringUtil.isBlank(areaid)){
			areaid=CtxUtil.getAreaId();
		}
		
		//List<String> listSize=new ArrayList<String>();
		String lawobjs="";
		List<TDataLawobjtype> list=this.dao.find("from TDataLawobjtype t where t.isactive='Y' order by t.id asc ");
		try {
				for(int j=0;j<list.size();j++){
				   int size=doubleRandomManager.queryCheckedLawobjByType(year, quarter, list.get(j).getId(), areaid);
				   //listSize.add();
				   lawobjs=lawobjs+size+","+list.get(j).getName()+";";
				}
		} catch (Exception e) {
				e.printStackTrace();
		}
		model.addAttribute("quarter", quarter);
		model.addAttribute("year", year);	
		//areaid=CtxUtil.getCurUser().getAreaId();
		model.addAttribute("areaid", areaid);
		model.addAttribute("list", lawobjs);
		model.addAttribute("title",title);
		return "app/data/sjcc/lawobjtypeStatistics";
	}
}
