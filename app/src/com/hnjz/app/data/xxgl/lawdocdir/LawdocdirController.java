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
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ���ļ�Ŀ¼����
 *
 */
@Controller
public class LawdocdirController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawdocdirController.class);
    
    @Autowired
    private LawdocdirManager lawdocdirManager;
    
    
    /**
     * ��ת��ִ���ļ�Ŀ¼ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/lawdocdirList.htm")
    public String lawdocdirList(ModelMap model,String title) {
    	model.put("title", title);
    	model.put("menu", lawdocdirManager.queryLawdicdirTree());
        return "app/data/xxgl/lawdocdir/lawdocdirList";
    }
    
    /**
     * ��ת��ִ���ļ���Ŀ¼�༭ҳ��
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
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
     * �������ܣ�����ִ���ļ�Ŀ¼
    
     * ���������
    
     * ����ֵ��
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
				JsonResultUtil.fail(model, "��Ŀ¼����ִ���ļ�������ɾ��ִ���ļ���");
			}else{
				lawdocdirManager.removeLawdocdir(id);
				JsonResultUtil.suncess(model, "ɾ���ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ɾ���ļ�Ŀ¼��", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    
    
    /**
     * 
     * �������ܣ�����ִ���ļ�Ŀ¼
    
     * ���������
    
     * ����ֵ��
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