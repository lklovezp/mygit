package com.hnjz.app.tjbb;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-27
 * 功能描述：
		统计排污单位数量
 *
 */
@Controller
public class StatisticalController {

	@Autowired
	private StatisticalManager statisticalManager;

	/**
     * 跳转到列表页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/tjpwdwslList.htm")
	public String tjpwdwslList(ModelMap model,String title){
    	model.put("title", title);
		return "app/tjbb/tjpwdwslList";
	}
    
    
    
    /**
     * 跳转到列表页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/zfdxtjbbList.htm")
	public String zfdxtjbbList(ModelMap model,String title,String lawobjtypeid,String regionid){
    	model.put("title", title);
    	model.put("lawobjtypeid", lawobjtypeid);
    	model.put("regionid", regionid);
		return "app/tjbb/zfdxtjbbList";
	}
    
    
    
	
    /**
     * 
     * 函数介绍：统计数据
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryPwdwslList.json")
    public void queryPwdwslList(ModelMap model,String regionId,String lawobjtype,String page,String pageSize){
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = statisticalManager.tjpwdwsl(regionId, lawobjtype, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * 函数介绍：跳转到执法对象列表
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryLawobjList.htm")
    public String queryLawobjList(ModelMap model,String regionid,String lawobjtype){
    	model.put("regionid", regionid);
    	if("01".equals(lawobjtype)){
    		return "app/tjbb/queryGywryList";
    	}else if("02".equals(lawobjtype)){
    		return "app/tjbb/queryJsxmList";
    	}else if("03".equals(lawobjtype)){
    		return "app/tjbb/queryYyxxList";
    	}else if("04".equals(lawobjtype)){
    		return "app/tjbb/queryGlxxList";
    	}else if("05".equals(lawobjtype)){
    		return "app/tjbb/queryJzgdList";
    	}else if("06".equals(lawobjtype)){
    		return "app/tjbb/queryScxxList";
    	}else if("07".equals(lawobjtype)){
    		return "app/tjbb/queryXqyzList";
    	}else if("08".equals(lawobjtype)){
    		return "app/tjbb/queryFwyList";
    	}else if("09".equals(lawobjtype)){
    		return "app/tjbb/queryYsyList";
    	}else if("10".equals(lawobjtype)){
    		return "app/tjbb/queryZzyList";
    	}else if("11".equals(lawobjtype)){
    		return "app/tjbb/queryYlyList";
    	}
    	return null;
    }
    
    /**
	 * 
	 * 函数介绍：导出统计（任务列表）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/exportStatisticalList")
	public void exportStatisticalList(ModelMap model,String regionId,String lawobjtype,HttpServletResponse res){
		statisticalManager.exportStatisticalList( regionId, lawobjtype, res);
	}
}
