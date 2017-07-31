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
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�������ֵ����
 *
 */
@Controller
public class LawobjDicController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawobjDicController.class);
    
    @Autowired
    private LawobjDicManager lawobjDicManager;
    
    
    /**
     * ��ת��ִ�������ֵ�༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
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
	 * �������ܣ���ȡִ��������ֶ��б�
	 * 
	 * ���������ִ���������ͱ��
	 * 
	 * ����ֵ��
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
	 * �������ܣ���ȡִ��������ֶ� ���뷽ʽ�б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/dataSourceList.json")
	@ResponseBody
	public List<Combobox> dataSourceList(ModelMap model,String inputType) {
		return DataSourceEnum.getDataSourceList(inputType);
	}
	
	/**
	 * 
	 * �������ܣ���ȡִ��������ֶ� ���뷽ʽ�б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/inputTypeList.json")
	@ResponseBody
	public List<Combobox> inputTypeList(ModelMap model) {
		return InputTypeEnum.getInputTypeList();
	}
	
	/**
	 * 
	 * �������ܣ���ȡִ��������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/lawobjColumnList.json")
	@ResponseBody
	public List<Combobox> lawobjColumnList(ModelMap model) {
		return lawobjDicManager.queryLawobjColumnList();
	}

	/**
	 * 
	 * �������ܣ���������ִ�����������ֵ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/saveOrUpdateLawobjDic.json")
	public void saveOrUpdateLawobjDic(ModelMap model,String lawobjtypeid,String[] id,String[] orderby,String[] colengname,String[] enumname,String[] colchiname,String[] inputtype,String[] datasource,String[] mandatory,String[] istwoitem){
		try {
			lawobjDicManager.saveOrUpdateLawobjDic(lawobjtypeid, id, orderby, colengname, enumname, colchiname, inputtype, datasource, mandatory, istwoitem);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("����ִ�������ֵ����", e);
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