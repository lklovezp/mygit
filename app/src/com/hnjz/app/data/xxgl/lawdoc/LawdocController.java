package com.hnjz.app.data.xxgl.lawdoc;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawdocdir;
import com.hnjz.app.data.xxgl.lawdocdir.LawdocdirManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法文件管理
 *
 */
@Controller
public class LawdocController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(LawdocController.class);
    
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
    @RequestMapping(value = "/lawdocList.htm")
    public String lawdocList(ModelMap model,String title) {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    	model.put("uuid", uuid);
    	model.put("title", title);
    	model.put("menu", lawdocDirManager.queryLawdicdirTree());
        return "app/data/zfwj/zfwjList";
    }
    
    /**
     * 
     * 函数介绍：跳转到添加界面
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/addLawdoc.htm")
    public String addLawdoc(ModelMap model,String uuid,TDataLawdocdir lawdocdir){
    	model.put("uuid", uuid);
    	model.put("lawdocdir", lawdocdir);
    	return "app/data/xxgl/lawdoc/addLawdoc";
    }
    
    
    /**
     * 
     * 函数介绍：附件列表
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/addLawdoc.json")
    @ResponseBody
    public List<LawdocForm> queryAddLawdoc(ModelMap model,TDataLawdocdir lawdocdir,String uuid){
    	List<LawdocForm> listDoc = null;
    	if(StringUtils.isNotBlank(uuid)){
    		listDoc = lawdocManager.queryNewUploadLawdoc(uuid);
    	}
    	return listDoc;
    }
    
    /**
     * 
     * 函数介绍：保存执法文件
    
     * 输入参数：pid-目录id、uuid-临时目录id、data-json字符串[id-执法文件id数值、keywords-执法文件关键字数值、orderby-排序数组]
    
     * 返回值：
     */
    @RequestMapping(value = "/saveLawdoc.json")
    public void saveLawdoc(ModelMap model,String pid,String uuid,String data){
    	try {
			lawdocManager.saveLawdoc(pid, uuid, data);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存执法文件信息错误：", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * 函数介绍：删除单个执法文件
    
     * 输入参数：id
    
     * 返回值：
     */
    @RequestMapping(value = "/deleteLawdoc.json")
    public void deleteLawdoc(ModelMap model,String pid,String id){
    	try {
    		if(StringUtils.isNotBlank(pid)){
    			lawdocManager.removeLawdocByPid(pid);
    		}else{
    			lawdocManager.removeLawdoc(id);
    		}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除执法文件信息错误：", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * 函数介绍：查询执法文件列表数据
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryLawdocList.json")
    public void queryLawdocList(ModelMap model,String pid,String title,String keywords,String canDel,String page,String pageSize){
    	try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = lawdocManager.queryLawdocList(pid, title, keywords, canDel, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * 函数介绍：到编辑界面
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/editLawdoc.htm")
    public String editLawdoc(ModelMap model,String id){
    	LawdocForm lawdoc = lawdocManager.getLawdocInfo(id);
    	model.put("lawdoc", lawdoc);
    	return "app/data/xxgl/lawdoc/editLawdoc";
    }
    
    /**
     * 
     * 函数介绍：更新单个执法文件
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/updateLawdoc.json")
    public void updateLawdoc(ModelMap model,LawdocForm lawdocForm,@RequestParam(value = "file",required=false)MultipartFile file,HttpServletResponse response){
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			lawdocManager.updateLawdoc(lawdocForm, file);
			writer.print("{\"state\":true,\"msg\":\"保存成功\"}");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存单个执法文件信息错误：", e);
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
    }
    
    /**
     * 跳转到执法文件管理页面(信息查询界面)
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/xxcx_lawdocList.htm")
    public String xxcx_lawdocList(ModelMap model,String title) {
    	model.put("title", title);
    	model.put("menu", lawdocDirManager.queryLawdicdirTree());
    	return "app/data/xxcx/lawdoc/xxcx_lawdocList";
    }
    
    
    /**
	 * 
	 * 函数介绍：跳转到选择执法文件界面
	
	 * 输入参数：filetype-要保存的文件类型，applyId-任务id
	
	 * 返回值：
	 */
    @RequestMapping(value = "/chooseLawdoc.htm")
	public String chooseLawdocForWork(ModelMap model,String fileType,String applyId,String tasktypeCode){
		model.put("fileType", fileType);
		model.put("applyId", applyId);
		model.put("menu", lawdocDirManager.queryLawdicdirTreeByTasktype(tasktypeCode));
		return "app/data/xxcx/lawdoc/chooseLawdoc";
	}
    
    /**
     * 
     * 函数介绍：保存选择的执法文件到任务相关附件
    
     * 输入参数：附件类型、任务id、选择的文件id（用逗号隔开）
    
     * 返回值：
     */
    @RequestMapping(value = "/saveChooseeLawdoc.json")
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
