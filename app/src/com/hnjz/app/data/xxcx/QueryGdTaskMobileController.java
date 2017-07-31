package com.hnjz.app.data.xxcx;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.mobile.common.CommonMobileManager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
		归档任务查询
 *
 */
@Controller
public class QueryGdTaskMobileController {

	@Autowired
	private QueryGdTaskManager queryGdTaskManager;
	
	@Autowired
	private CommonMobileManager commonMobileManager;

	
    /**
     * 
     * 函数介绍：统计数据
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryGdrwList.mo")
    public void queryGdrwlList(ModelMap model,String lawobjtype,String tasktype,String workname,String lawobjname,String regionId,String zbUsername,String starttime,String endtime,String page,String pageSize){
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = queryGdTaskManager.queryGdrwListForMobile(lawobjtype, tasktype, workname, lawobjname, regionId, zbUsername, starttime, endtime, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * 函数介绍：获取终端任务详情
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/getTaskInfoForMobile.mo")
	public void getTaskInfoForMobile(ModelMap model, String id, String page, String pageSize) {
		try {
			JSONArray array = queryGdTaskManager.getTaskInfoForMobile(id);
			model.addAttribute("baseInfoList", array.toString());
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = commonMobileManager.queryFileList(id, FileTypeEnums.RWGLPFFJ.getCode()+","+FileTypeEnums.RWGLZPFJ.getCode()+","+FileTypeEnums.RWGLXPFJ.getCode(), page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
