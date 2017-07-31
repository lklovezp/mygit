package com.hnjz.app.data.xxcx;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-27
 * ����������
		�鵵�����ѯ
 *
 */
@Controller
public class QueryGdTaskController {

	@Autowired
	private QueryGdTaskManager queryGdTaskManager;


	/**
     * ��ת���б�ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/gdrwList.htm")
	public String gdrwList(ModelMap model,String title){
    	model.put("title", title);
		return "app/data/xxcx/gdrwcx/gdrwList";
	}
	
    /**
     * 
     * �������ܣ�ͳ������
    
     * ���������
    
     * ����ֵ��
     */
    @RequestMapping(value = "/queryGdrwList.json")
    public void queryGdrwlList(ModelMap model,String taskname,String lawobjtype,String tasktype,String areaid, String zbOrgId,String regionId,String lawobjname,String zbUsername,String rwly,String starttime,String endtime,String yqwcStarttime,String yqwcEndtime,String page,String pageSize){
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = queryGdTaskManager.queryGdrwList(taskname,lawobjtype, tasktype, areaid, zbOrgId, regionId, lawobjname, zbUsername, rwly,starttime,endtime, yqwcStarttime, yqwcEndtime, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}