package com.hnjz.mobile.data.xxcx;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.xxgl.lawdoc.LawdocManager;
import com.hnjz.app.data.xxgl.lawdocdir.LawdocdirManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字典管理
 *
 */
@Controller
public class LawdocMobileController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(LawdocMobileController.class);
    
    @Autowired
    private LawdocManager lawdocManager;
    
    @Autowired
    private LawdocdirManager lawdocDirManager;
    
    
    /**
     * 跳转到执法文件管理页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/lawdocAndDirList.mo")
    public void lawdocList(ModelMap model,String pid,String page,String pageSize) {
    	try {
			JSONArray dirArray = lawdocDirManager.getLawdocDirListByPid(pid);
			model.addAttribute("lawdocdirList", dirArray.toString());
			if(StringUtils.isNotBlank(pid)){
				FyWebResult re = lawdocManager.queryLawdocListForMobile(pid, null, null, page, pageSize);
				JsonResultUtil.fyWeb(model, re);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * 函数介绍：终端查询执法文件列表
    
     * 输入参数：title-标题、keywords-关键词
    
     * 返回值：
     */
    @RequestMapping(value = "/queryLawdocList.mo")
    public void queryLawdocList(ModelMap model,String title,String keywords,String page,String pageSize){
    	try {
			FyWebResult re = lawdocManager.queryLawdocListForMobile(null, title, keywords, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * 函数介绍：终端查询执法文件列表
    
     * 输入参数：title-标题、keywords-关键词
    
     * 返回值：
     */
    @RequestMapping(value = "/queryLawdocListByTasktype.mo")
    public void queryLawdocListByTasktype(ModelMap model,String tasktype,String title,String keywords,String page,String pageSize){
    	try {
    		FyWebResult re = lawdocManager.queryLawdocListByTasktype(tasktype, title, keywords, page, pageSize);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * 
     * 函数介绍：保存选择的执法文件到任务相关附件
    
     * 输入参数：附件类型、任务id、选择的文件id（用逗号隔开）
    
     * 返回值：
     */
    @RequestMapping(value = "/saveChooseeLawdoc.mo")
    public void saveChooseeLawdoc(ModelMap model,String fileType,String applyId,String fileid){
    	try {
			lawdocManager.saveChooseeLawdoc(fileType, applyId, fileid);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    
}
