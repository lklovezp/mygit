package com.hnjz.app.data.xxgl.lawdocdir;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.ComboboxTree;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法文件目录管理
 *
 */
@Controller
public class LawdocdirController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(LawdocdirController.class);
    
    @Autowired
    private LawdocdirManager lawdocdirManager;
    
    
    /**
     * 跳转到执法文件目录页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/lawdocdirList.htm")
    public String lawdocdirList(ModelMap model,String title) {
    	model.put("title", title);
    	model.put("menu", lawdocdirManager.queryLawdicdirTree());
        return "app/data/xxgl/lawdocdir/lawdocdirList";
    }
    
    /**
     * 跳转到执法文件夹目录编辑页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/editLawdocdir.htm")
    public String lawdocdirEdit(ModelMap model,LawdocdirForm lawdocdirForm,String pid,String id) {
    	if(StringUtils.isNotBlank(lawdocdirForm.getId())){
    		lawdocdirForm = lawdocdirManager.getLawdocdirInfo(lawdocdirForm);
    		
    	}
    	model.put("lawdocdirForm", lawdocdirForm);
    	//model.put("id", id);
    	//model.put("pid",pid);
    	return "app/data/xxgl/lawdocdir/editLawdocdir";
    }
    
    /**
     * 
     * 函数介绍：更新执法文件目录
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/saveOrUpdateLawdocdir.json")
    public String saveOrUpdateLawdocdir(ModelMap model,LawdocdirForm lawdocdirForm){
    	try {
			lawdocdirManager.saveOrUpdateLawdocdir(lawdocdirForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
    	return "app/data/xxgl/lawdocdir/editLawdocdir";
    }
    
    @RequestMapping(value = "/deleteLawdocdir.json")
    public void deleteLawdocdir(ModelMap model,String id){
    	try {
    		List<TDataLawdoc> list = lawdocdirManager.find("from TDataLawdoc d where d.dirid = ? and d.isActive = 'Y' ",id);
			if(list.size()>0){
				JsonResultUtil.fail(model, "该目录下有执法文件，请先删除执法文件！");
			}else{
				lawdocdirManager.removeLawdocdir(id);
				JsonResultUtil.suncess(model, "删除成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除文件目录：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    
    
    /**
     * 
     * 函数介绍：更新执法文件目录
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/lawdocdirTree.json")
    @ResponseBody
    public List<ComboboxTree> lawdocdirTree(ModelMap model){
		return lawdocdirManager.queryLawdicdirComboTree();
    }

	public LawdocdirManager getLawdocdirManager() {
		return lawdocdirManager;
	}

	public void setLawdocdirManager(LawdocdirManager lawdocdirManager) {
		this.lawdocdirManager = lawdocdirManager;
	}
}
