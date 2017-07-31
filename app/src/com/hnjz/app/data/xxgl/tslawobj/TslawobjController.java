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
	 * ��ת��������ҵ���ҳ��
	 * 
	 * 
	 * */
	@RequestMapping(value="teShuLawObj.htm")
	public String speailLawobjList(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/sjcc/specialLawobjList";
	}
	/**
	 * ��ת������������ҵ����������ƽ���
	 * 
	 * 
	 * */
	@RequestMapping(value="jumpSpecialLawobj.htm")
	public String jumpSpecialLawobj(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/sjcc/jumpSpecialLawobj";
	}
	/**
	 * ��ת������������ҵ��ܽ���
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
	 * ����������������ҵ��Ϣ
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/specialLawobjSave.json", produces = "application/json")
	public void specialLawobjSave(ModelMap model,String ids,String names,String year,String quarter) throws Exception {
		try {
			tslawobjManager.saveSpecialLawobj(ids, names, year, quarter);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	
	/**
	 * ��ѯ��������ҵ�б�
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
			log.error("��ѯ������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * ɾ����������ҵ
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delSpecialLawobj.json", produces = "application/json")
	public void delSpecialLawobj(ModelMap model,String id) throws Exception {
		try {
			tslawobjManager.removeSpecialLawobj(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("��ѯ������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * ��ת������������趨����
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
	 * ������������ҵ���ù���
	 * @param po
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/configCheckSave.json", produces = "application/json")
	public void configCheckSave(ModelMap model,ConfigCheckForm configCheckForm,String areaid) throws Exception {
		
		try {
			tslawobjManager.configCheckSave(configCheckForm,areaid);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * ��ת���������ɷ����ý���
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
	 * �ɷ����ý������ݼ���
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
			
			log.error("��ѯ����", e);
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * ��������ɷ�������Ϣ
	 * 
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/configPfSave.json", produces = "application/json")
	public void configPfSave(ModelMap model,ConfigPfForm configPfForm,String areaid) throws Exception {
		try {
			//tslawobjManager.configCheckSave(configCheckForm);
			tslawobjManager.configPfSave(configPfForm,areaid);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * ����������ҵ����ɷ�������Ϣ
	 * 
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/specliallConfigPfSave.json", produces = "application/json")
	public void specliallConfigPfSave(ModelMap model,ConfigPfForm configPfForm,String areaid) throws Exception {
		try {
			//tslawobjManager.configCheckSave(configCheckForm);
			tslawobjManager.specliallConfigPfSave(configPfForm,areaid);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	

	/**
	 * ��ת�����ͳ��ҳ��
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
			log.error("��ѯ����", e);
			e.printStackTrace();
		}
		model.addAttribute("title", title);
		return "app/data/sjcc/checkedCount";
	}
	/**
	 * �������ܣ�ͳ���б�����
	 * ���������
	 * ����ֵ��
	 */
	@RequestMapping(value = "/exportCheckedCountForm.json")
	public void exportCheckedCountForm(ModelMap model,String year,String quarter,String areaid,HttpServletResponse res){
		try {
			tslawobjManager.exportCheckedCountForm(year, quarter, areaid, res);
		} catch (Exception e) {
			log.error("����������", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * �������ܣ�ͳ���б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryLowPfbl.json")
	public void queryLowPfbl(ModelMap model,String year,String quarter,String areaid){
		try {
			ConfigCheckForm tf=tslawobjManager.queryConfigCheck(areaid);
			
			String zs= tslawobjManager.queryLowPfbl(year,quarter,areaid);
			model.addAttribute("zs", zs);
			model.addAttribute("tsqybl", tf.getTsjgqybl());
		} catch (Exception e) {
			log.error("����������", e);
			e.printStackTrace();
		}
	}
	/**
	 * ��ת��������������ҵ�ɷ����ý���
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
		    	
		    //������ҵ���ù���
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
	 * �ɷ����ý������ݼ���
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
			
			log.error("��ѯ����", e);
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * 
	 * �������ܣ��������б�
	 * 
	 */
	@RequestMapping(value = "/monthList.json")
	@ResponseBody
	public List<Combobox> quarterList(ModelMap model,String quarter) {
		List<Combobox> list=new ArrayList<Combobox>();
		try {
			list= tslawobjManager.queryMonthByQuarter(quarter);
		} catch (Exception e) {
			log.error("��ѯ������", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 
	 * �������ܣ��������б�
	 * 
	 */
	@RequestMapping(value = "/newMonthList.json")
	@ResponseBody
	public List<Combobox> newMonthList(ModelMap model,String quarter) {
		List<Combobox> list=new ArrayList<Combobox>();
		try {
			list= MonthEnum.getMonthEnumList();
		} catch (Exception e) {
			log.error("��ѯ������", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ��ѯ�����ɷ������ǰ��»��ǰ�����
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
	 * �������Υ����ҵתΪyear��������ҵ
	 * 
	 * 
	 * */
	@RequestMapping(value="	toSpecialLawobj.json")
	public void toSpecialLawobj(ModelMap model,String year){
		try {
			if(StringUtil.isNotBlank(year)){
				tslawobjManager.saveForYeartoSpecail(year);
				JsonResultUtil.suncess(model, "�����ĸ������������");
			}
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ��ѯ������ҵ
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
	 * ��ת���������ɷ����ý���
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
			s="ȫ��";
		}else if("1".equals(quarter)){
			s="��һ����";
		}else if("2".equals(quarter)){
			s="�ڶ�����";
		}else if("3".equals(quarter)){
			s="��������";
		}else if("4".equals(quarter)){
			s="���ļ���";
		}
		if(StringUtil.isNotBlank(type)){
			if(type.equals("0")){
				
			}else if(type.equals("1")){
				s=s+"һ�����۵�λ";
			}else if(type.equals("2")){
				s=s+"�ص����۵�λ";
			}else if(type.equals("3")){
				s=s+"�������۵�λ";
			}else if(type.equals("4")){
				s=s+"��������Ϣ��������";
			}
		}
		model.addAttribute("title", year+"��"+s+"��������");
		return "app/data/sjcc/zjcList";
	}
	/**
	 * ��ѯ��ҵ��Ϣ
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
			log.error("��ѯ������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		
	}
	
	/**
	 * ��תΥ��ͳ������ҳ��
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
			s="ȫ��";
		}else if("1".equals(quarter)){
			s="��һ����";
		}else if("2".equals(quarter)){
			s="�ڶ�����";
		}else if("3".equals(quarter)){
			s="��������";
		}else if("4".equals(quarter)){
			s="���ļ���";
		}
		model.addAttribute("title", year+"��"+s+"�����鷢��Υ����ҵ");
		return "app/data/sjcc/queryWfLawobjList";
	}
	/**
	 * ��ѯ��ҵ��Ϣ
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
			log.error("��ѯ������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
		
	}
	
	/**
	 * ��ת������������ҳ��
	 * 
	 * 
	 * */
	@RequestMapping(value="sjccybcx.htm")
	public String sjccybcxList(ModelMap model,String title){
		model.addAttribute("title", title);
		model.put("title", title);
    	//Ĭ����ʾ��������Ϣ
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
        return "app/data/sjcc/sjcc_lawobjList";
	}
	
	/**
     * ��������������б�����
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/querySjccLawobjList.json")
    public void querySjccLawobjList(ModelMap model,String name,String lawobjType,String regionId,String orgId, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = tslawobjManager.querySjccLawobjList(name, lawobjType,regionId,orgId, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ������", e);
		}
    }
    
    /**
	 * 
	 * �������ܣ�����ִ�����������ۺ���Ϣ
	 * ���������
	 * ����ֵ��
	 * @throws Exception 
	 */
	@RequestMapping(value = "/exportYbLawobjList.json")
	public void exportYbLawobjList(ModelMap model,String name, String lawobjType, String regionId, String regionName,String orgId,String orgName,HttpServletResponse res) throws Exception{
		tslawobjManager.exportYbLawobjList(name,lawobjType,regionId, regionName,orgId,orgName, res);
	}
	
	/**
	 * ��ת����������ҵ����ͳ�ƽ���
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