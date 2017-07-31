package com.hnjz.mobile.data.jxkh;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.jxkh.orgstatistics.RwzxgcfxForm;
import com.hnjz.app.jxkh.orgstatistics.StageAnalysis;
import com.hnjz.app.jxkh.orgstatistics.StatisticsOrgManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	����ۼ����� ���Ʋ�(�ֻ���)
 *
 */
@Controller
public class TaskTraceMobileController {

	 /**��־*/
    private static final Log log = LogFactory.getLog(TaskTraceMobileController.class);
    
	@Autowired
	private TaskTraceMobileManager taskTraceMobileManager;
	
	@Autowired
	private StatisticsOrgManager statisticsOrgManager;

	/**
	 * 
	 * �������ܣ�ͳ���б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryTaskTraceList.mo")
	public void queryTaskTraceList(ModelMap model,String areaid,String rwmc,String czlx,String starttime,String endtime,String tasktype,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = taskTraceMobileManager.queryTaskTraceList(areaid,rwmc, starttime, endtime, tasktype, czlx, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	
	/**
	 * 
	 * �������ܣ�����ִ�й��̷���
	
	 * �������������id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/rwzxgcfx.mo")
	public void rwzxgcfx(ModelMap model,String id){
		RwzxgcfxForm rwzxgcfxForm = statisticsOrgManager.getRwzxgcfxFormByRwid(id);
		model.addAttribute("rwzxgcfx",rwzxgcfxForm);
		StageAnalysis stageAnalysis = statisticsOrgManager.getMaxStageAnalysis(id);
		model.addAttribute("stageAnalysis",stageAnalysis);
		StageAnalysis yqjdForm = statisticsOrgManager.getYqStageAnalysis(id);
		model.addAttribute("yqjdForm",yqjdForm);
	}
	
	/**
	 * 
	 * �������ܣ��׶���ʱ�����б�
	
	 * �������������id
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryJdysfxList.mo")
	@ResponseBody
	public List<StageAnalysis> queryJdysfxList(ModelMap model,String id){
		return statisticsOrgManager.querystageAnalysisList(id);
	}
	
	
}