package com.hnjz.app.jxkh.tasktrace;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-31
 * ����������
	����ۼ����� ���Ʋ�
 *
 */
@Controller
public class TaskTraceController {

	 /**��־*/
    private static final Log log = LogFactory.getLog(TaskTraceController.class);
	@Autowired
	private TaskTraceManager taskTraceManager;

	/**
	 * 
	 * �������ܣ��б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/taskTraceList.htm")
	public String taskTraceList(ModelMap model,String title,String areaid){
		model.put("title", title);
		//Ĭ�ϵ�ǰ����
		TSysUser u = CtxUtil.getCurUser();
		if(StringUtils.isBlank(areaid)){
			areaid = u.getAreaId();
		}
		model.addAttribute("areaid", areaid);
		return "app/jxkh/tasktrace/taskTraceList";
	}
	
	/**
	 * 
	 * �������ܣ�ͳ���б�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryTaskTraceList.json")
	public void queryTaskTraceList(ModelMap model,String areaid,String rwmc,String rwly,String starttime,String endtime,String tasktype,String pfrid,String czlx,String page,String pageSize){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = taskTraceManager.queryTaskTraceList(areaid,rwmc, rwly, starttime, endtime, tasktype, pfrid, czlx, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
}