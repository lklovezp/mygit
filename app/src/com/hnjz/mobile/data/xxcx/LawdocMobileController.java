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
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ�������ֵ����
 *
 */
@Controller
public class LawdocMobileController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawdocMobileController.class);
    
    @Autowired
    private LawdocManager lawdocManager;
    
    @Autowired
    private LawdocdirManager lawdocDirManager;
    
    
    /**
     * ��ת��ִ���ļ�����ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
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
     * �������ܣ��ն˲�ѯִ���ļ��б�
    
     * ���������title-���⡢keywords-�ؼ���
    
     * ����ֵ��
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
     * �������ܣ��ն˲�ѯִ���ļ��б�
    
     * ���������title-���⡢keywords-�ؼ���
    
     * ����ֵ��
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
     * �������ܣ�����ѡ���ִ���ļ���������ظ���
    
     * ����������������͡�����id��ѡ����ļ�id���ö��Ÿ�����
    
     * ����ֵ��
     */
    @RequestMapping(value = "/saveChooseeLawdoc.mo")
    public void saveChooseeLawdoc(ModelMap model,String fileType,String applyId,String fileid){
    	try {
			lawdocManager.saveChooseeLawdoc(fileType, applyId, fileid);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    
}