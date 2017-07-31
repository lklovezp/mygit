package com.hnjz.app.data.xxgl.fsaq;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.common.JsonResultUtil;
@Controller
public class FsaqController {
	private static final Log log=LogFactory.getLog(FsaqController.class);
	@Autowired
	private FsaqManager fsaqManager;
	/**
	 * ��ѯ���䰲ȫ������Ϣ���Լ���ת�����ӻ����޸�ҳ�棬���һ�������
	 * */
	@RequestMapping(value="/fsaqxxEdit.htm")
	public String fsaqxxEdit(ModelMap model,String lawobjId,String lawobjTypeId){
		
		try {
			String id="";
			FsaqForm fsaqForm=fsaqManager.queryFsaqForm(lawobjId, id, lawobjTypeId);
			model.addAttribute("fsaqForm", fsaqForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/xxgl/fsaq/fsaqEdit";
	}
	/**
	 * ��������޸ķ��䰲ȫ������Ϣ
	 * */
	@RequestMapping(value="/saveFsaqxx.json")
	public void saveFsaqForm(ModelMap model,FsaqForm fsaqForm){
		try {
			fsaqManager.saveFsaqForm(fsaqForm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * �������䰲ȫ������Ϣ��
	 * */
	@RequestMapping(value="/buildFsaqWord.json")
	public void buildFsaqWord(HttpServletResponse response,ModelMap model,String lawobjId,String lawobjType,String biaoshi,String appleId){
		try {
			fsaqManager.createFsaqWord(response, lawobjId, lawobjType, biaoshi,appleId);
			JsonResultUtil.suncess(model, "�����ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
}