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
//	 * 跳转到企业基本情况信息列表
//	 * */
//	@RequestMapping(value="/whpQyjbqkFind.htm")
//	public String WhpQyjbqkFind(ModelMap model,TDataLawobj lawobj){
//		//model.addAttribute("lawobjId", lawobj.getId());
//		return "app/work/danger/QyjbqkContent";
//	}
	/**
	 * 跳转到添加企业基本信息页面
	 * 把企业已经存在的基本信息回填到添加页面
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
	 * 保存企业基本信息
	 * */
	@RequestMapping(value="/saveQyFrom.json")
	public void saveQyFrom(ModelMap model,QyjbqkForm qyjbqkForm){
		try {
			qyjbqkManager.saveQyFrom(qyjbqkForm);
			JsonResultUtil.suncess(model, "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 保存企业基本信息
	 * */
	@RequestMapping(value="/delJbxx.json")
	public void delJbxx(ModelMap model,String id){
		try {
			qyjbqkManager.delJbxx(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *查询所有危化品基本信息根据lawobjId
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
	 * 跳转到添加企业基本信息页面
	 * 把企业已经存在的基本信息回填到添加页面
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
	 * 跳转到添加企业基本信息页面
	 * 把企业已经存在的基本信息回填到添加页面
	 * */
	@RequestMapping(value="/infoJbxx.htm")
	public String infoJbxx(ModelMap model,String id,String lawobjId){
		try {
			QyjbqkForm qy=new QyjbqkForm();
			qy.setId(id);
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(lawobjId);
			QyjbqkForm qyFrom=qyjbqkManager.queryQyjbqkForm(tl, qy);
			qyFrom.setSfbzya("1".equals(qyFrom.getSfbzya())?"有":"无");
			qyFrom.setSfpjwj("1".equals(qyFrom.getSfpjwj())?"是":"否");
			qyFrom.setSftf("1".equals(qyFrom.getSftf())?"有":"无");
			model.addAttribute("qyFrom",qyFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "app/work/danger/qyjbqkInfo";
	}
	
}
