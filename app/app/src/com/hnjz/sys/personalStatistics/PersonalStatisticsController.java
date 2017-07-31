package com.hnjz.sys.personalStatistics;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.jxkh.orgstatistics.StatisticsDocForm;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.permission.PermissionController;
import com.hnjz.sys.po.TSysUser;
/**
 * ���˹�����ͳ�Ƶ�controller
 * @author Administrator
 *
 */
@Controller
public class PersonalStatisticsController {
	
	/** ��־ */
	private static final Log log = LogFactory
			.getLog(PermissionController.class);
	@Autowired
	private PersonalStatisticsManager psManager;
	/**
	 * ���˵�¼�����������������ͼ���¼������ҳ���ʼ��
	 * @param model
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "personalList.htm")
	public String findPersonalStatisticsList(ModelMap model, String title,String areaid){
		TSysUser u = CtxUtil.getCurUser();
		String userId = CtxUtil.getUserId();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		model.addAttribute("userId", userId);
		model.addAttribute("title", title);
		return "sys/personalStatistics/personalStatistics";
	}
	
	
	
	@RequestMapping(value = "queryPersonalName.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryPersonalName(ModelMap model, String title,String id){
		try {
			return psManager.createZterr(id);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return null;
	}
	@RequestMapping(value = "queryPersonalList.json" , produces = "application/json")
	public void queryPersonalList(ModelMap model, String title,String areaid,String starttime,String endtime,String date,String cid,String userId,String time) {
		try {
		
			//Ĭ�ϵ�ǰ����
			TSysUser u = CtxUtil.getCurUser();
			if(StringUtils.isBlank(date)){
				date = u.getAreaId();
				Calendar a=Calendar.getInstance();
				starttime = String.valueOf(a.get(Calendar.YEAR));
			}
			
			endtime = StringUtils.defaultIfBlank(endtime, DateUtil.getDate(new Date()));
			String emList =psManager.findEachStatisticsManager(userId,time,cid);//��¼����echarts����
			String emrw = psManager.findEachStatisticsManagerRW(userId,time,cid);//�������echarts����
			List<StatisticsDocForm> list = psManager.statisticsUser(date,null, null, null, null, null, time, endtime,userId);
			String embl = psManager.findEachStatisticsManagerBL(userId,list,cid);//��¼����¼echarts����
			model.addAttribute("emList",emList);
			model.addAttribute("emrw",emrw);
			model.addAttribute("embl",embl);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
}