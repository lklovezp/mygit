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
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
		ִ���ļ�����
 *
 */
@Controller
public class LawdocController {
	 /**��־*/
    private static final Log log = LogFactory.getLog(LawdocController.class);
    
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
     * �������ܣ���ת�����ӽ���
    
     * ���������
    
     * ����ֵ��
     */
    @RequestMapping(value = "/addLawdoc.htm")
    public String addLawdoc(ModelMap model,String uuid,TDataLawdocdir lawdocdir){
    	model.put("uuid", uuid);
    	model.put("lawdocdir", lawdocdir);
    	return "app/data/xxgl/lawdoc/addLawdoc";
    }
    
    
    /**
     * 
     * �������ܣ������б�
    
     * ���������
    
     * ����ֵ��
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
     * �������ܣ�����ִ���ļ�
    
     * ���������pid-Ŀ¼id��uuid-��ʱĿ¼id��data-json�ַ���[id-ִ���ļ�id��ֵ��keywords-ִ���ļ��ؼ�����ֵ��orderby-��������]
    
     * ����ֵ��
     */
    @RequestMapping(value = "/saveLawdoc.json")
    public void saveLawdoc(ModelMap model,String pid,String uuid,String data){
    	try {
			lawdocManager.saveLawdoc(pid, uuid, data);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("����ִ���ļ���Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * �������ܣ�ɾ������ִ���ļ�
    
     * ���������id
    
     * ����ֵ��
     */
    @RequestMapping(value = "/deleteLawdoc.json")
    public void deleteLawdoc(ModelMap model,String pid,String id){
    	try {
    		if(StringUtils.isNotBlank(pid)){
    			lawdocManager.removeLawdocByPid(pid);
    		}else{
    			lawdocManager.removeLawdoc(id);
    		}
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("ɾ��ִ���ļ���Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * �������ܣ���ѯִ���ļ��б�����
    
     * ���������
    
     * ����ֵ��
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
     * �������ܣ����༭����
    
     * ���������
    
     * ����ֵ��
     */
    @RequestMapping(value = "/editLawdoc.htm")
    public String editLawdoc(ModelMap model,String id){
    	LawdocForm lawdoc = lawdocManager.getLawdocInfo(id);
    	model.put("lawdoc", lawdoc);
    	return "app/data/xxgl/lawdoc/editLawdoc";
    }
    
    /**
     * 
     * �������ܣ����µ���ִ���ļ�
    
     * ���������
    
     * ����ֵ��
     */
    @RequestMapping(value = "/updateLawdoc.json")
    public void updateLawdoc(ModelMap model,LawdocForm lawdocForm,@RequestParam(value = "file",required=false)MultipartFile file,HttpServletResponse response){
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			lawdocManager.updateLawdoc(lawdocForm, file);
			writer.print("{\"state\":true,\"msg\":\"����ɹ�\"}");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���浥��ִ���ļ���Ϣ����", e);
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
    }
    
    /**
     * ��ת��ִ���ļ�����ҳ��(��Ϣ��ѯ����)
     * 
     * @param model {@link ModelMap}
     * @return ��ѯ���ҳ��
     */
    @RequestMapping(value = "/xxcx_lawdocList.htm")
    public String xxcx_lawdocList(ModelMap model,String title) {
    	model.put("title", title);
    	model.put("menu", lawdocDirManager.queryLawdicdirTree());
    	return "app/data/xxcx/lawdoc/xxcx_lawdocList";
    }
    
    
    /**
	 * 
	 * �������ܣ���ת��ѡ��ִ���ļ�����
	
	 * ���������filetype-Ҫ������ļ����ͣ�applyId-����id
	
	 * ����ֵ��
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
     * �������ܣ�����ѡ���ִ���ļ���������ظ���
    
     * ����������������͡�����id��ѡ����ļ�id���ö��Ÿ�����
    
     * ����ֵ��
     */
    @RequestMapping(value = "/saveChooseeLawdoc.json")
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