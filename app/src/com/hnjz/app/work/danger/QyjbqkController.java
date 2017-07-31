package com.hnjz.app.work.danger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

@Controller
public class QyjbqkController {
	
	private static final Log log=LogFactory.getLog(QyjbqkController.class);
	@Autowired
	private QyjbqkManager qyjbqkManager;
	
//	/**
//	 * ��ת����ҵ���������Ϣ�б�
//	 * */
//	@RequestMapping(value="/whpQyjbqkFind.htm")
//	public String WhpQyjbqkFind(ModelMap model,TDataLawobj lawobj){
//		//model.addAttribute("lawobjId", lawobj.getId());
//		return "app/work/danger/QyjbqkContent";
//	}
	/**
	 * ��ת��������ҵ������Ϣҳ��
	 * ����ҵ�Ѿ����ڵĻ�����Ϣ�������ҳ��
	 * */
	@RequestMapping(value="/whpQyjbqkFind.htm")
	public String addQyjbqk(ModelMap model,QyjbqkForm qyjbqkForm,TDataLawobjf lawobjf){
		try {
			QyjbqkForm qyFrom=qyjbqkManager.queryQyjbqkForm(lawobjf, qyjbqkForm);
			model.addAttribute("qyFrom",qyFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "app/work/danger/qyjbqkAdd";
	}
	
	/**
	 * 
	 * ������ҵ������Ϣ
	 * */
	@RequestMapping(value="/saveQyFrom.json")
	public void saveQyFrom(ModelMap model,QyjbqkForm qyjbqkForm){
		try {
			qyjbqkManager.saveQyFrom(qyjbqkForm);
			JsonResultUtil.suncess(model, "���ӳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * ������ҵ������Ϣ
	 * */
	@RequestMapping(value="/delJbxx.json")
	public void delJbxx(ModelMap model,String id){
		try {
			qyjbqkManager.delJbxx(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *��ѯ����Σ��Ʒ������Ϣ����lawobjId
	 *
	 * */
	@RequestMapping(value="/queryJbxxList.json")
	public void queryJbxxList(ModelMap model,String lawobjId,String page,String rows){
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re=qyjbqkManager.queryQyjbqkFormList(lawobjId, page, rows);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ת��������ҵ������Ϣҳ��
	 * ����ҵ�Ѿ����ڵĻ�����Ϣ�������ҳ��
	 * */
	@RequestMapping(value="/editQyjbqk.htm")
	public String editQyjbqk(ModelMap model,String id,String lawobjId){
		try {
			QyjbqkForm qy=new QyjbqkForm();
			qy.setId(id);
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(lawobjId);
			QyjbqkForm qyFrom=qyjbqkManager.queryQyjbqkForm(tl, qy);
			model.addAttribute("qyFrom",qyFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "app/work/danger/qyjbqkAdd";
	}
	/**
	 * ��ת��������ҵ������Ϣҳ��
	 * ����ҵ�Ѿ����ڵĻ�����Ϣ�������ҳ��
	 * */
	@RequestMapping(value="/infoJbxx.htm")
	public String infoJbxx(ModelMap model,String id,String lawobjId){
		try {
			QyjbqkForm qy=new QyjbqkForm();
			qy.setId(id);
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(lawobjId);
			QyjbqkForm qyFrom=qyjbqkManager.queryQyjbqkForm(tl, qy);
			qyFrom.setSfbzya("1".equals(qyFrom.getSfbzya())?"��":"��");
			qyFrom.setSfpjwj("1".equals(qyFrom.getSfpjwj())?"��":"��");
			qyFrom.setSftf("1".equals(qyFrom.getSftf())?"��":"��");
			model.addAttribute("qyFrom",qyFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "app/work/danger/qyjbqkInfo";
	}
	
}