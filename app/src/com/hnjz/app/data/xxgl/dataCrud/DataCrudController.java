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
 * ���ߣ�zhangqingfeng
 * �������ڣ�2016-3-07
 * �������������ݿ�����ͬ���Ĺ���
 *
 */
@Controller
public class DataCrudController {
	/**��־*/
    private static final Log log = LogFactory.getLog(DataCrudController.class);
    
    @Autowired
    private DataCrudManager dataCrudManager;
    @Autowired
    private IndexManager     indexManager;
    
    /**
     * ��ת������ͬ��ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/dataCrud.htm")
    public String lawdocList(ModelMap model,String title) {
    	String webUrl = indexManager.webURL;
    	DataCrudForm data = dataCrudManager.getUpdateTimeValue(webUrl);
    	model.put("dataForm", data);
        return "app/data/xxgl/dataCrud/dataCrud";
    }
    
    /**
     * �������ܣ���ѯ����ͬ�������ݿ�����
     * ���������
     * ����ֵ��
     */
    @RequestMapping(value = "/queryDataList.json")
    public void queryDataList(ModelMap model,String type){
    	try {
    		String webUrl = indexManager.webURL;
    		dataCrudManager.saveDataNameList(type, webUrl);
    		JsonResultUtil.suncess(model, "ͬ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ͬ�����ݱ�����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * ����ͬ���󣬷������µ�ʱ��ֵ
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/dataCrudtime.json")
    public void queryUpdateddata(ModelMap model,String type) {
    	try {
    		String date = dataCrudManager.queryUpdateddata(type);
			model.put("date", date);
		} catch (Exception e) {
			log.error("ͬ�����ݱ�����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
}