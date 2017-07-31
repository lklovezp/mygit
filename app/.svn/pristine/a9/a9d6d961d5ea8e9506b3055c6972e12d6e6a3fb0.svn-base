package com.hnjz.app.data.xxgl.lawobjdic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.data.enums.DataSourceEnum;
import com.hnjz.app.data.enums.InputTypeEnum;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字典管理
 *
 */
@Controller
public class LawobjDicController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(LawobjDicController.class);
    
    @Autowired
    private LawobjDicManager lawobjDicManager;
    
    
    /**
     * 跳转到执法对象字典编辑页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/lawobjDicEdit.htm")
    public String lawobjDicEdit(ModelMap model,String lawobjtypeid,String title) {
    	model.put("title", title);
    	if(StringUtils.isNotBlank(lawobjtypeid)){
    		List<TDataLawobjdic> list = lawobjDicManager.queryLawobjDicList(lawobjtypeid);
    		model.put("list", list);
    		model.put("lawobjtypeid",lawobjtypeid);
    	}
        return "app/data/xxgl/lawobjdic/lawobjDicEdit";
    }
    
    
    /**
	 * 
	 * 函数介绍：获取执法对象表字段列表
	 * 
	 * 输入参数：执法对象类型编号
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/lawobjOutColunmList.json")
	@ResponseBody
	public List<Combobox> lawobjOutColunmList(ModelMap model,String type) {
		if(StringUtils.isNotBlank(type)){
			return LawobjOutColunmEnum.getListColumnByType(type);
		}else{
			return new ArrayList<Combobox>();
		}
	}
	/**
	 * 
	 * 函数介绍：获取执法对象表字段 输入方式列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/dataSourceList.json")
	@ResponseBody
	public List<Combobox> dataSourceList(ModelMap model,String inputType) {
		return DataSourceEnum.getDataSourceList(inputType);
	}
	
	/**
	 * 
	 * 函数介绍：获取执法对象表字段 输入方式列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/inputTypeList.json")
	@ResponseBody
	public List<Combobox> inputTypeList(ModelMap model) {
		return InputTypeEnum.getInputTypeList();
	}
	
	/**
	 * 
	 * 函数介绍：获取执法对象对外名称列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/lawobjColumnList.json")
	@ResponseBody
	public List<Combobox> lawobjColumnList(ModelMap model) {
		return lawobjDicManager.queryLawobjColumnList();
	}

	/**
	 * 
	 * 函数介绍：保存或更新执法对象数据字典
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveOrUpdateLawobjDic.json")
	public void saveOrUpdateLawobjDic(ModelMap model,String lawobjtypeid,String[] id,String[] orderby,String[] colengname,String[] enumname,String[] colchiname,String[] inputtype,String[] datasource,String[] mandatory,String[] istwoitem){
		try {
			lawobjDicManager.saveOrUpdateLawobjDic(lawobjtypeid, id, orderby, colengname, enumname, colchiname, inputtype, datasource, mandatory, istwoitem);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存执法对象字典错误：", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}
	
	public LawobjDicManager getLawobjDicManager() {
		return lawobjDicManager;
	}

	public void setLawobjDicManager(LawobjDicManager lawobjDicManager) {
		this.lawobjDicManager = lawobjDicManager;
	}
}
