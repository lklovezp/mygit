package com.hnjz.app.data.xxgl.dataCrud;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.IndexManager;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：zhangqingfeng
 * 生成日期：2016-3-07
 * 功能描述：数据库数据同步的功能
 *
 */
@Controller
public class DataCrudController {
	/**日志*/
    private static final Log log = LogFactory.getLog(DataCrudController.class);
    
    @Autowired
    private DataCrudManager dataCrudManager;
    @Autowired
    private IndexManager     indexManager;
    
    /**
     * 跳转到数据同步页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/dataCrud.htm")
    public String lawdocList(ModelMap model,String title) {
    	String webUrl = indexManager.webURL;
    	DataCrudForm data = dataCrudManager.getUpdateTimeValue(webUrl);
    	model.put("dataForm", data);
        return "app/data/xxgl/dataCrud/dataCrud";
    }
    
    /**
     * 函数介绍：查询数据同步的数据库数据
     * 输入参数：
     * 返回值：
     */
    @RequestMapping(value = "/queryDataList.json")
    public void queryDataList(ModelMap model,String type){
    	try {
    		String webUrl = indexManager.webURL;
    		dataCrudManager.saveDataNameList(type, webUrl);
    		JsonResultUtil.suncess(model, "同步成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("同步数据报错！", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 进行同步后，返回最新的时间值
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/dataCrudtime.json")
    public void queryUpdateddata(ModelMap model,String type) {
    	try {
    		String date = dataCrudManager.queryUpdateddata(type);
			model.put("date", date);
		} catch (Exception e) {
			log.error("同步数据报错：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
}
