/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.jcd;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.data.po.TDataChecklistitemans;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.TBizChecklist;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.NullControllHashMap;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.CnUpperCaser;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.jcdtemplate.JcdTemplateManager;
import com.hnjz.sys.po.TSysUserOrg;

/**
 * 检查单的manager
 * 
 * @author wumi
 * @version $Id: JcdManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi
 *          Exp $
 */
@Service("jcdManagerImpl")
public class JcdManagerImpl extends ManagerImpl implements
		JcdManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdManagerImpl.class);

	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	@Autowired
	private WorkManagerImpl workManagerImpl;
	
	@Autowired
	private JcdTemplateManager jcdTemplateManager;
	
	@Override
	public JSONArray getJcx(String jcmbId,String applyId) throws AppException {
		JSONArray arr = new JSONArray();
		JSONArray ansarr = new JSONArray();
		try {
			JSONObject itemobj = new JSONObject();
			Map<String, Object> ansobj = new HashMap<String, Object>();
			// 查询该检查模板下的检查项
			List<TDataChecklistitem> items = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ? order by orderby", jcmbId);
			// 查询该任务隐藏的检查项
			List<TBizChecklist> checklist = this.dao.find("from TBizChecklist where templateid = ? and taskid = ? and ishidden = 'Y'", jcmbId,applyId);
			// 移除隐藏的检查项
			// 用iterator 在并发时候防止错误
			Iterator<TDataChecklistitem> iter = items.iterator();
			while(iter.hasNext() && checklist!= null){  
				TDataChecklistitem item = iter.next();
				for(TBizChecklist checkItem:checklist){
					if(StringUtils.equals(checkItem.getItemid(),item.getId())){//该检查项被隐藏
						iter.remove(); //移除隐藏的检查项
					}
				}
			}
			// 查询检查项
			List<TDataChecklistitemans> itemans = new ArrayList<TDataChecklistitemans>();
			// 添加检查项节点数据
			for (TDataChecklistitem item : items) {
				// 查询检查项
				itemans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ? order by orderby", item.getId());
				
				itemobj = new JSONObject();
				itemobj.put("id", item.getId());
				// 查询检查单中检查项
				List<TBizChecklist> checkList = this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, item.getId());
				//检查单存在该检查项，且该检查项内容不为空
				if(checkList != null && checkList.size()>0 && StringUtil.isNotBlank(checkList.get(0).getItemcontent())){
					itemobj.put("contents",checkList.get(0).getItemcontent());
				}else{
					itemobj.put("contents", item.getContents());
				}
				itemobj.put("inputtype", item.getInputtype());
				itemobj.put("contentsunit", item.getContentsunit());
				itemobj.put("getlawobjvalue", item.getGetlawobjvalue());
				itemobj.put("isActive", item.getIsActive());
				itemobj.put("isrequired", item.getIsrequired());
				itemobj.put("isanswernewline", item.getIsanswernewline());
				itemobj.put("orderby", item.getOrderby());
				ansarr = new JSONArray();
				for (int i = 0; i < itemans.size(); i++) {
					ansobj = new HashMap<String, Object>();
					ansobj.put("answer", itemans.get(i).getAnswer());
					ansobj.put("answerdesc", itemans.get(i).getAnswerdesc());
					ansobj.put("id", itemans.get(i).getId());
					ansobj.put("isActive", itemans.get(i).getIsActive());
					ansobj.put("isnormal", itemans.get(i).getIsnormal());
					ansobj.put("orderby", itemans.get(i).getOrderby());
					ansarr.put(ansobj);
				}
				itemobj.put("ans", ansarr);
				arr.put(itemobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("");
		return arr;
	}

	@Override
	public JSONArray getCheckRecord(String applyId, String superJcmbId, String jcmbId, String taskType)
			throws AppException {
		JSONArray arr = new JSONArray();
		JSONObject o = new JSONObject();
		try {
			List<TBizChecklist> pos = this.dao.find("from TBizChecklist where taskid = ? and templateid = ?", applyId, jcmbId);
			// 加载本次办理任务时填写的内容
			for (int i = 0; i < pos.size(); i++) {
				o = new JSONObject();
				o.put("jcmbId", pos.get(i).getTemplateid());
				o.put("itemid", pos.get(i).getItemid());
				o.put("answer", pos.get(i).getAnswerid());
				o.put("describe", pos.get(i).getDescribe());
				arr.put(o);
			}
			// 加载此执法对象上一次（已归档的任务）的检查记录
			if (arr.length() == 0){
				// 任务和执法对象
				TBizTaskandlawobj lawobjPo = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ? ", applyId).get(0);
				//默认显示最近一次归档任务的检查记录
				// 找到本执法对象所有执行的任务
				//List<String> tasks = this.dao.find("select l.taskid from TBizTaskandlawobj l WorkLog w where l.taskid = w.work.id and w.workSate ='" + WorkState.YGD.getCode() + "' and l.lawobjid = ? and w.work.areaid = ? order by w.work.gdsj desc", lawobjPo.getLawobjid(), CtxUtil.getCurUser().getAreaId());
				StringBuffer sql = new StringBuffer("SELECT d.taskid_ FROM T_Biz_Taskandlawobj d ");
				sql.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= d.TASKID_ ");
				sql.append(" left join WORK_ w on w.id_ = d.TASKID_  where w.isActive_='Y' and w.state_='09' and t.JCMB_='"+superJcmbId+"'");
				sql.append(" and d.lawobjid_ = '"+lawobjPo.getLawobjid()+"'");
				sql.append(" order by w.archives_time_ desc");
				List<String> tasks = this.dao.findBySql(sql.toString());
				// 最近一次归档任务的检查记录
				String checkedTaskId = "";
				if(tasks!=null && tasks.size()>0) {
					checkedTaskId = tasks.get(0);
					List<TBizChecklist> checks = this.dao.find("from TBizChecklist where taskid = ? and templateid = ?", checkedTaskId, jcmbId);
					if (checks.size() > 0){
						for (int j = 0; j < checks.size(); j++) {
							o = new JSONObject();
							o.put("jcmbId", checks.get(j).getTemplateid());
							o.put("itemid", checks.get(j).getItemid());
							o.put("answer", checks.get(j).getAnswerid());
							o.put("describe", checks.get(j).getDescribe());
							arr.put(o);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	@Override
	public JSONArray getHistoryRecord(String superJcmbId, String workId) throws JSONException{
		JSONArray arr = new JSONArray();
		JSONObject o = new JSONObject();
		// 查询子模板
		List<TDataChecklisttemplate> subTmps = new ArrayList<TDataChecklisttemplate>();
		this.getSubTemplate(superJcmbId, subTmps);
		
		//选择加载任务id为workId的检查记录
		for (TDataChecklisttemplate subTmp : subTmps){
			List<TBizChecklist> checks = this.dao.find("from TBizChecklist where taskid = ? and templateid = ?", workId, subTmp.getId());
			if (checks.size() > 0){
				for (int j = 0; j < checks.size(); j++) {
					o = new JSONObject();
					o.put("jcmbId", checks.get(j).getTemplateid());
					o.put("itemid", checks.get(j).getItemid());
					o.put("answer", checks.get(j).getAnswerid());
					o.put("describe", checks.get(j).getDescribe());
					arr.put(o);
				}
			}
		}
		return arr;
	}
	
	@Override
	public void saveTemporary(String applyId, String jcmbId, String itemId,String itemcontent,
			String type, String answer, String beizhu, String orderby) throws AppException {
		// 先删除在添加
		this.dao.remove(this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId));
		
		TBizChecklist po = new TBizChecklist();
		for (int i = 0; i < answer.split(",").length; i++) {
			po = new TBizChecklist();
			po.setTaskid(applyId);
			po.setTemplateid(jcmbId);
			po.setItemid(itemId);
			po.setItemcontent(itemcontent);
			po.setItemorderby(Integer.parseInt(orderby));
			po.setAnswerid(answer.split(",")[i]);
			po.setDescribe(beizhu);
			po.setIshidden("N");
			po.setIsadd("N");
			this.dao.save(po);
		}
	}
	
	@Override
	public void editJcx(String applyId, String jcmbId, String itemId,String itemcontent,String orderby){
		// 查询检查单中检查项
		List<TBizChecklist> checkList = this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId);
		//检查单存在该检查项，修改检查单中检查项内容
		if(checkList != null && checkList.size()>0){
			TBizChecklist po = checkList.get(0);
			po.setItemcontent(itemcontent);
			this.dao.save(po);
		}else{//检查单中不存在该检查项
			TBizChecklist po = new TBizChecklist();
			po.setTaskid(applyId);
			po.setTemplateid(jcmbId);
			po.setItemid(itemId);
			po.setItemcontent(itemcontent);
			po.setItemorderby(Integer.parseInt(orderby));
			po.setIshidden("N");
			po.setIsadd("N");
			this.dao.save(po);
		}
	}
	
	@Override
	public String getJcxcontent(String applyId, String jcmbId, String itemId){
		List<TBizChecklist> checkList = this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId);
		//检查单存在该检查项，且检查项内容不为空时，取检查单中检查项内容
		if(checkList != null && checkList.size()>0 &&  StringUtil.isNotBlank(checkList.get(0).getItemcontent())){
			return checkList.get(0).getItemcontent();
		}else{
			// 查询检查项
			TDataChecklistitem item = (TDataChecklistitem) this.dao.get(TDataChecklistitem.class, itemId);
			return item.getContents();
		}
	}
	
	@Override
	public void hideJcx(String applyId, String jcmbId, String itemId,String orderby) throws AppException {
		// 将该检查项的ishidden置为“Y”
		// 先删除再添加
		this.dao.remove(this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId));
		TBizChecklist po = new TBizChecklist();
		po.setTaskid(applyId);
		po.setTemplateid(jcmbId);
		po.setItemid(itemId);
		po.setItemorderby(Integer.parseInt(orderby));
		po.setIshidden("Y");
		this.dao.save(po);
	}

	@Override
	public void saveCheckList(String applyId, String jcmbId, String subdata) throws AppException {
		try {
			String[] jcmbIds = jcmbId.split(",");
			JSONArray alljcmb = new JSONArray(subdata);
			for (int k = 0; k < alljcmb.length(); k++) {
				JSONArray onejcmb = alljcmb.getJSONArray(k);
				TBizChecklist po = new TBizChecklist();
				String itemId = "";
				String orderby = "";
				String answer = "";
				String beizhu = "";
				String content = "";
				JSONObject o = new JSONObject();
				//查找该检查模板下的所有检查项
				List <TBizChecklist> checkList= this.find("from TBizChecklist where taskid = ? and templateid = ? ", applyId,jcmbIds[k]);
				for (int i = 0; i < onejcmb.length(); i++) {
					o = onejcmb.getJSONObject(i);
					itemId = o.getString("id");
					TBizChecklist item = new TBizChecklist(); 
					for(int j=0;j<checkList.size();j++){
						if(checkList!=null && StringUtils.equals(itemId, checkList.get(j).getItemid())){
							item = checkList.get(j);
							break;
						}
					}
					//List<TBizChecklist> item = this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId);
					if(item!=null && StringUtils.equals(item.getIshidden(),"Y")){
						continue;
					}else{
						beizhu = o.getString("beizhu");
						orderby = o.getString("orderby");
						answer = o.getString("value");
						// 查询检查项问题
						//content = this.getJcxcontent(applyId, jcmbId, itemId);
						if(StringUtils.isNotBlank(item.getItemcontent())){
							content = item.getItemcontent();
						}else if(StringUtils.isNotBlank(item.getItemid())){
							TDataChecklistitem item1 = (TDataChecklistitem) this.dao.get(TDataChecklistitem.class, item.getItemid());
							content = item1.getContents();
						}
						
						// 先删除再添加
						this.dao.remove(item);
						for (int j = 0; j < answer.split(",").length; j++) {
							po = new TBizChecklist();
							po.setTaskid(applyId);
							po.setTemplateid(jcmbIds[k]);
							po.setItemid(itemId);
							po.setItemcontent(content);
							po.setItemorderby(Integer.parseInt(orderby));
							po.setAnswerid(answer.split(",")[j]);
							po.setDescribe(beizhu);
							this.dao.save(po);
						}
					}
				}
			}
		} catch (JSONException e) {
			log.error("", e);
		}
	}

	/**
	 * 递归查询子模板
	 * @param id 父节点id
	 */
	public void getSubTemplate(String id, List<TDataChecklisttemplate> oldSubPos){
		List<TDataChecklisttemplate> re = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and pid = ? order by orderby", id);
		oldSubPos.addAll(re);
		for (TDataChecklisttemplate ele : re) {
			this.getSubTemplate(ele.getId(), oldSubPos);
		}
	}
	
	/**
	 * 递归查询子模板
	 * @param id 父节点id
	 * @param type 0：生成检查记录
	 * 1：生成监察报告
	 * 
	 */
	public void getContents(List<String> order,String type, String applyId, String id, List<TDataChecklisttemplate> oldSubPos, StringBuffer sb){
		List<TDataChecklisttemplate> re = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and pid = ? order by orderby", id);
		oldSubPos.addAll(re);
		
		// 递归层级
		int loop = 1;
		// 查询该任务下所有的检查项
		List<TBizChecklist> list = this.dao.find("from TBizChecklist where taskid = ? ",applyId);
		for (TDataChecklisttemplate ele : re) {
			List<TDataChecklistitem> checklistPo = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ? order by orderby", ele.getId());
			List<TBizChecklist> cList = new ArrayList<TBizChecklist>();//存储所有子模板下的检查项
			// 查询该任务该模板下所有的检查项
			//List<TBizChecklist> checklist = this.dao.find("from TBizChecklist where templateid = ? and taskid = ? ", ele.getId(),applyId);
			List<TBizChecklist> checklist = new ArrayList<TBizChecklist>();
			for(int i=0;i<list.size();i++){
				if(list!=null && StringUtils.equals(ele.getId(), list.get(i).getTemplateid())){
					checklist.add(list.get(i));
				}
			}
			// 查询该任务隐藏的检查项
			//List<TBizChecklist> checklist = this.dao.find("from TBizChecklist where templateid = ? and taskid = ? and ishidden = 'Y'", ele.getId(),applyId);
			List<TBizChecklist> hidelist = new ArrayList<TBizChecklist>();
			for(int i=0;i<checklist.size();i++){
				if(checklist!=null && "Y".equals(checklist.get(i).getIshidden())){
					hidelist.add(checklist.get(i));
				}
			}
			
			// 移除隐藏的检查项
			// 用iterator 在并发时候防止错误
			Iterator<TDataChecklistitem> iter = checklistPo.iterator();
			while(iter.hasNext() && hidelist!= null){  
				TDataChecklistitem item = iter.next();
				for(TBizChecklist checkItem:hidelist){
					if(StringUtils.equals(checkItem.getItemid(),item.getId())){//该检查项被隐藏
						iter.remove(); //移除隐藏的检查项
						break;
					}
				}
			}
			// 移除未填写的检查项
			// 用iterator 在并发时候防止错误
			Iterator<TDataChecklistitem> iter1 = checklistPo.iterator();
			//List<TBizChecklist> deleteList = this.dao.find("from TBizChecklist where taskid = ? and templateid = ? ", applyId, ele.getId());
			while(iter1.hasNext()){  
				TDataChecklistitem item = iter1.next();
				//List<TBizChecklist> results = this.dao.find("from TBizChecklist where taskid = ? and templateid = ? and itemid = ?", applyId, ele.getId(), item.getId());
				List<TBizChecklist> results = new ArrayList<TBizChecklist>();
				for(int i=0;i<checklist.size();i++){
					if(checklist!=null && StringUtils.equals(item.getId(), checklist.get(i).getItemid())){
						results.add(checklist.get(i));
					}
				}
				if(results!=null && results.size()>0 && (StringUtils.isNotBlank(results.get(0).getDescribe()) || StringUtils.isNotBlank(results.get(0).getAnswerid()))){
					continue;
				}else{
					iter1.remove(); //移除未填写的检查项
				}
			}
			if(checklistPo==null || checklistPo.size()==0){
				List<TDataChecklisttemplate> childtemplates= jcdTemplateManager.getChTemplates(ele.getId());
				//List<TBizChecklist> ceAllList = this.dao.find("from TBizChecklist where taskid = ? ", applyId);
				for(TDataChecklisttemplate template:childtemplates){
					List<TDataChecklistitem> items = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ? order by orderby", template.getId());//该模板下所有配置检查项
					//List<TBizChecklist> ceList = this.dao.find("from TBizChecklist where taskid = ? and templateid = ? ", applyId, template.getId());
					List<TBizChecklist> ceList = new ArrayList<TBizChecklist>();
					for(int i=0;i<list.size();i++){
						if(list!=null && StringUtils.equals(template.getId(), list.get(i).getTemplateid())){
							ceList.add(list.get(i));
						}
					}
					for(TDataChecklistitem childitem:items){
						//List<TBizChecklist> ce = this.dao.find("from TBizChecklist where taskid = ? and templateid = ? and itemid = ?", applyId, template.getId(),childitem.getId());
						List<TBizChecklist> ce = new ArrayList<TBizChecklist>();
						for(int i=0;i<ceList.size();i++){
							if(ceList!=null && StringUtils.equals(childitem.getId(), ceList.get(i).getItemid())){
								ce.add(ceList.get(i));
							}
						}
						if(ce!=null && ce.size()>0 && (StringUtils.isNotBlank(ce.get(0).getDescribe()) || StringUtils.isNotBlank(ce.get(0).getAnswerid()))){
							cList.addAll(ce);
						}
					}
				}
			}
			//检查模板项
			if((checklistPo!=null && checklistPo.size()>0) || (cList!=null && cList.size()>0)){
				order.add(String.valueOf(loop));
				// 监察模板编号
				String num = order.get(0);
				String space = "";
				for (int i = 0; i < order.size(); i++) {
					if (i > 0){
						space += "  ";
						num += ".";
						num += order.get(i);
					}
				}
				if (order.size() == 1){
					num = CnUpperCaser.dateNumToUpper(Integer.parseInt(num));
				}
				
				if (ele.getNamevisiable().equals(YnEnum.Y.getCode())){
					sb.append("\r" + space + num + "、" + ele.getName());
				} else {
					sb.append("\r" + space + num + "、");
				}
				String one = "";
				// 当前已经存在的列数
				int col = 0;
				//List<TBizChecklist> resultList = this.dao.find("from TBizChecklist where taskid = ? and templateid = ?", applyId, ele.getId());
				for (int j = 0; j < checklistPo.size(); j++) {
					one = "";
					//String content = getJcxcontent(applyId,id,checklistPo.get(j).getId());
					String content = "";
					TBizChecklist item = new TBizChecklist();
					for(int i=0;i<list.size();i++){
						if(list!=null && StringUtils.equals(checklistPo.get(j).getId(), list.get(i).getItemid())){
							item = list.get(i);
							break;
						}
					}
					if(item!=null && StringUtils.isNotBlank(item.getItemcontent())){
						content = item.getItemcontent();
					}else{
						content = checklistPo.get(j).getContents();
					}
					if (ele.getNamevisiable().equals("Y")){
						one += space + "  (" + String.valueOf(j + 1) + ")";
					}
					one += content;
					//List<TBizChecklist> results = this.dao.find("from TBizChecklist where taskid = ? and templateid = ? and itemid = ?", applyId, ele.getId(), checklistPo.get(j).getId());
					List<TBizChecklist>  results = new ArrayList<TBizChecklist>();
					for(int i=0;i<checklist.size();i++){
						if(checklist!=null && StringUtils.equals(checklistPo.get(j).getId(), checklist.get(i).getItemid())){
							results.add(checklist.get(i));
						}
					}
					// 填空题
					if(checklistPo.get(j).getInputtype().equals("0") && results.size() != 0){
						if (checklistPo.get(j).getIsanswernewline().equals("Y")){
							one += space + "　";
						}
						
						one += results.get(0).getDescribe();
					}
					// 如果是单选或多选
					else {
						List<TDataChecklistitemans> ans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ?", checklistPo.get(j).getId());
						for (int k = 0; k < results.size(); k++) {
							if(k > 0 && k < results.size() && type.equals("0")){
								one += "、";
							} else if (k > 0 && k < results.size() && type.equals("1")) {
								one += ";";
							}
							for (int i = 0; i < ans.size(); i++) {
								if (ans.get(i).getId().equals(results.get(k).getAnswerid()) && type.equals("0")){
									one += "　" + (StringUtil.isBlank(ans.get(i).getAnswer()) ? "" : ans.get(i).getAnswer());
								} else if(ans.get(i).getId().equals(results.get(k).getAnswerid()) && type.equals("1")){
									one += "　" + (StringUtil.isBlank(ans.get(i).getAnswerdesc()) ? "" : ans.get(i).getAnswerdesc());
								}
							}
						}
						// 添加备注
						if (results.size() > 0){
							if (StringUtil.isNotBlank(results.get(0).getDescribe())){
								one += space + "　" + "备注：";
								one += results.get(0).getDescribe();
							}
						}
					}
					
					if (col == 2){
						col = 0;
					}
					
					if (one.length() <= 22){
						String s = "";
						if (col == 0){
							for (int i = 0; i < 22 - one.length(); i++) {
								s += "　";
							}
							if (!ele.getNamevisiable().equals("Y")){
								sb.append(one + s);
							} else {
								sb.append("\r" + one + s);
							}
						} else {
							sb.append(one + s);
						}
						col++;
					} else {
						if (!ele.getNamevisiable().equals("Y")){
							sb.append(one);
						} else {
							sb.append("\r" + one);
						}
						
						col = 0;
					}
				}
				
				this.getContents(order,type, applyId, ele.getId(), oldSubPos, sb);
				order.remove(order.size() - 1);
				loop++;
			}
		}
	}
	
	public static String AutoFitLineCharNum(String strSrc) {
		String strRet = "";
		String one = "";
		String two = "";
		int j = 0;
		int k = 0;
		String[] questArray = strSrc.split("\r");
		for (int i = 0; i < questArray.length;) {
			one = "";
			two = "";
			if (questArray[i].substring(0, 1).equals(" ")){
				if (xxx(questArray[i])){
					one += questArray[i];
					for (j = i + 1; j < questArray.length; j++) {
						if (!xxx(questArray[j])){
							one += questArray[j];
						}
						// 下一行
						else {
							two += questArray[j];
							for (k = j + 1; k < questArray.length; k++) {
								if (!xxx(questArray[k])){
									two += questArray[k];
								} else {
									break;
								}
							}
							break;
						}
					}
					if (StringUtil.isNotBlank(two)){
						if (one.length() <= 20){
							if (two.length() <= 20 && two.substring(0, 1).equals(" ")){
								strRet = strRet + space(one, 20 - one.length()) + two + "\r";
								i = k;
							} else {
								strRet += one + "\r";
								i = j;
							}
						} else {
							strRet += one + "\r";
							i = j;
						}
					} else {
						strRet += one + "\r";
						i = j;
					}
				} else {
					i++;
				}
			} else {
				strRet += questArray[i] + "\r";
				i++;
			}
			
		}
		strRet = strRet.replaceAll(" ", "　");
		return strRet;
	}
	
	private static String space(String str, int i){
		for (int j = 0; j < i; j++) {
			str += "　";
		}
		return str;
	}
	
	private static boolean xxx(String str) {
		boolean flag = false;
		if (!str.substring(0, 1).equals(" ")){
			return true;
		}
		if (str.contains("(") && str.contains(")")) {
			flag = isNumeric(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
		} else {
			flag = false;
		}

		return flag;
	}

	public static boolean isNumeric(String str) {
		if (str == null || str.equals("") || str.toLowerCase().equals("null")) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	//去除数组中重复的记录  
	public static String[] array_unique(String[] a) {
		// array_unique
		List<String> list = new LinkedList<String>();
		for(int i = 0; i < a.length; i++) {
			if(!list.contains(a[i])) {
				list.add(a[i]);
			}
		}
		return (String[])list.toArray(new String[list.size()]);
	}

	
	@Override
	public HashMap<String, Object> buildCheckListRecord(String applyId, String taskType, String jcmbId) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		List<TDataChecklisttemplate> templatePos = new ArrayList<TDataChecklisttemplate>();
		TDataChecklisttemplate subTmp = (TDataChecklisttemplate) this.get(TDataChecklisttemplate.class, jcmbId);
		templatePos.add(subTmp);
		// 获取所有模板
		this.getSubTemplate(jcmbId, templatePos);
		
		List<TDataChecklisttemplate> oldSubPos = new ArrayList<TDataChecklisttemplate>();
		StringBuffer sb = new StringBuffer();
		List<String> order = new ArrayList<String>();
		
		this.getContents(order,"0", applyId, jcmbId, oldSubPos, sb);
		String content = sb.toString();
		if (content.startsWith("\r")){
			content = content.substring(1, content.length());
		}
		if (content.endsWith("\r")){
			content = content.substring(0, content.length() - 1);
		}
		//String content = AutoFitLineCharNum(sb.toString());
		// 任务和任务类型表
		TBizTaskandtasktype tattPo = (TBizTaskandtasktype) this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId, taskType).get(0);
		// 检查人和记录人枚举值
		String jcrNames = "";
		String jcrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JCR");
		String jlrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JLR");
		List<TBizTaskuser> jcr = this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jcrCode);
		TBizTaskuser jlr = (TBizTaskuser) this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jlrCode).get(0);
		// 检查人
		TSysUserOrg jcrPo = null;
		for (int i = 0; i < jcr.size(); i++) {
			jcrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jcr.get(i).getUserid()).get(0);
			if (i > 0){
				jcrNames += "、";
			}
			jcrNames += jcrPo.getUser().getName() + "(" + jcrPo.getUser().getLawnumber() + ")";
		}
		
		// 记录人
		TSysUserOrg jlrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jlr.getUserid()).get(0);
		// 任务和执法对象
		TBizTaskandlawobj lawobjPo = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ?", applyId).get(0);
		// 执法对象类型
		String lawObjType = lawobjPo.getLawobjtype();
		// 生成检查单所用的数据
		Map<String, String> paraMap = new HashMap<String, String>();
		
		// 标题中xx环境保护局 可能是当前用户所在部门的名称
		TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
		paraMap.put("$biaoti$", curOrg.getOrg().getUnitname());
		paraMap.put("$rwlx$", "");
		// 检查情况
		paraMap.put("$sbjc$", content);
		// 检查时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH时mm分");
		if(null!=tattPo.getJcsj1()&&null!=tattPo.getJcsj2()){
			paraMap.put("$jcsj$", sdf.format(tattPo.getJcsj1())+"至"+sdf2.format(tattPo.getJcsj2()));
		}else{
			paraMap.put("$jcsj$","");
		}
		// 受检单位
		if (lawObjType.equals("07")){
			TDataLawobjdic xqyzDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '07' and enumname = ?", LawobjOutColunmEnum.xqyz_dwmc.getCode()).get(0);
			String xqyzDwmc = xqyzDic.getColengname();
			String sql = "select " + xqyzDwmc + " as xqyzDwmc from T_DATA_LAWOBJ where ID_ = ?";
			List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
			paraMap.put("$sjdw$", String.valueOf(data.get(0)));
		} else {
			paraMap.put("$sjdw$", lawobjPo.getLawobjname());
		}
		// 受检单位地址
		paraMap.put("$dwdz$", lawobjPo.getAddress());
		// 法定代表人标题
		paraMap.put("$fddbtit$", lawobjPo.getZwtitle());
		// 法定代表人
		paraMap.put("$fddb$", lawobjPo.getManager());
		// 联系电话
		paraMap.put("$fddbdh$", lawobjPo.getManagermobile());
		// 被检查人
		paraMap.put("$bjcr$", lawobjPo.getBjcr());
		// 部门职务
		paraMap.put("$bjcrzw$", lawobjPo.getZw());
		// 联系电话 
		paraMap.put("$bjcrdh$", lawobjPo.getLxdh());
		// 检查人
		paraMap.put("$jcr$", jcrNames);
		// 记录人
		paraMap.put("$jlr$", jlrPo.getUser().getName() + "(" + jlrPo.getUser().getLawnumber() + ")");
		// 工作单位
		paraMap.put("$gzdw$", jcrPo.getOrg().getUnitname());

		// 根据不同执法对象类型生成不同检查单
		String classPath = this.getClass().getResource("").getPath();
		classPath = java.net.URLDecoder.decode(classPath, "utf-8");
		// 模板路径
		String templatePath = null;
		// 建设项目
		if (lawObjType.equals("02")){
			TDataLawobjdic jsxmmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_jsxmmc.getCode()).get(0);
			String jsxmmc = jsxmmcDic.getColengname();
			TDataLawobjdic jsddDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_jsdd.getCode()).get(0);
			String jsdd = jsddDic.getColengname();
			TDataLawobjdic dwmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '02' and enumname = ?", LawobjOutColunmEnum.jsxm_dwmc.getCode()).get(0);
			String dwmc = dwmcDic.getColengname();
			
			String sql = "select " + jsxmmc + " as jsxmmc, " + jsdd + " as jsdd, " + dwmc + " as dwmc from T_DATA_LAWOBJ where ID_ = ?";
			List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
			// 建设项目名称
			paraMap.put("$jsxm$", StringUtil.isBlank(String.valueOf(data.get(0)[0])) ? "" : String.valueOf(data.get(0)[0]));
			// 建设地点
			paraMap.put("$jsdd$", StringUtil.isBlank(String.valueOf(data.get(0)[1])) ? "" : String.valueOf(data.get(0)[1]));
			// 单位名称
			paraMap.remove("$sjdw");
			paraMap.put("$sjdw$", StringUtil.isBlank(String.valueOf(data.get(0)[2])) ? "" : String.valueOf(data.get(0)[2]));
			templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcd//jsxmTemplate.doc";
		}
		// 建筑工地
		else if (lawObjType.equals("05")){
			TDataLawobjdic jzgdmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_sgxmmc.getCode()).get(0);
			String jzgdmc = jzgdmcDic.getColengname();
			TDataLawobjdic gcddDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_gcdd.getCode()).get(0);
			String gcdd = gcddDic.getColengname();
			TDataLawobjdic dwmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '05' and enumname = ?", LawobjOutColunmEnum.jzgd_sgdwmc.getCode()).get(0);
			String dwmc = dwmcDic.getColengname();
			String sql = "select " + jzgdmc + " as jzgdmc, " + gcdd + " as gcdd, " + dwmc + " as dwmc from T_DATA_LAWOBJ where ID_ = ?";
			List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
			// 建筑工地名称
			paraMap.put("$jzgd$", StringUtil.isBlank(String.valueOf(data.get(0)[0])) ? "" : String.valueOf(data.get(0)[0]));
			// 工程地点
			paraMap.put("$gcdd$", StringUtil.isBlank(String.valueOf(data.get(0)[1])) ? "" : String.valueOf(data.get(0)[1]));
			// 单位名称
			paraMap.remove("$sjdw");
			paraMap.put("$sjdw$", StringUtil.isBlank(String.valueOf(data.get(0)[2])) ? "" : String.valueOf(data.get(0)[2]));
			templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcd//jzgdTemplate.doc";
		}
		// 其他
		else {
			templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcd//jcdTemplate.doc";
		}
		
		// 生成的路径
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String filePath = PoiUtil.createWord(templatePath, dirPath, paraMap);
		
		File file = new File(filePath);
		
		String newFileType="";
		if("15".equals(taskType)){//专项子任务的单独处理
			newFileType=FileTypeEnums.ZXXDZRWJCJL.getCode();
		}else{
			newFileType=taskType + "00";
		}
		
		// 先删除旧检查单
		List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and type = ?", applyId, newFileType);
		if(listFile.size()>0){
			for(TDataFile filePo : listFile){
				// 删除文件
				new File(dirPath + "//" + filePo.getOsname()).delete();
				this.dao.remove(TDataFile.class, filePo.getId());
			}
		}
		// 保存数据库的名称
		String oName = null;
		
		oName = lawobjPo.getLawobjname() + "第1次检查现场监察笔录.doc";
		
		TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, applyId, newFileType, UploadFileType.WORK.getPath());
		
		ret.put("id", filePo.getId());
		ret.put("name", filePo.getName());
		
		return ret;
	}
	
	@Override
	public void buildJcbl(String applyId, String taskType, String jcmbId, String jcqk, String fileid) throws Exception {
		// 生成检查单所用的数据
		Map<String, String> paraMap = new HashMap<String, String>();
		//jcqk = java.net.URLDecoder.decode(jcqk, "UTF-8");
		
		if(StringUtils.isNotBlank(applyId)){
			String cjr="";//主办人
			List <Combobox> list = commWorkManager.ryghCombo(applyId);//协办人
			for(int i=0;list!=null && i<list.size();i++){
				cjr = cjr+list.get(i).getName()+"、";
			}
			if(StringUtils.isNotBlank(cjr)){
				cjr = cjr.substring(0,cjr.length()-1);//移除最后一个顿号
				paraMap.put("$cjr$", cjr);
			}
			
		}
		
		// 任务和任务类型表
		TBizTaskandtasktype tattPo = (TBizTaskandtasktype) this.dao.find("from TBizTaskandtasktype where taskid = ? and tasktypeid = ?", applyId, taskType).get(0);
		// 检查人和记录人枚举值
		String jcrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JCR");
		String jlrCode = TaskUserEnum.getCodeByEnum(TaskTypeCode.getEnumByCode(taskType) + "_JLR");
		String jcrNames = "";
		List<TBizTaskuser> jcr = this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jcrCode);
		TBizTaskuser jlr = (TBizTaskuser) this.dao.find("from TBizTaskuser where taskid = ? and type = ? ", applyId, jlrCode).get(0);
		
		// 检查人
		TSysUserOrg jcrPo = null;
		for (int i = 0; i < jcr.size(); i++) {
			jcrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jcr.get(i).getUserid()).get(0);
			if (i > 0){
				jcrNames += "、";
			}
			jcrNames += jcrPo.getUser().getName() + "(" + jcrPo.getUser().getLawnumber() + ")";
		}
		
		// 记录人
		TSysUserOrg jlrPo = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", jlr.getUserid()).get(0);
		// 任务和执法对象
		TBizTaskandlawobj lawobjPo = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ?", applyId).get(0);
		// 执法对象类型
		String lawObjType = lawobjPo.getLawobjtype();
		
		// 标题中xx环境保护局 可能是当前用户所在部门的名称
		TSysUserOrg curOrg = (TSysUserOrg) this.dao.find("from TSysUserOrg where user.id = ? ", CtxUtil.getCurUser().getId()).get(0);
		paraMap.put("$biaoti$", curOrg.getOrg().getUnitname());
		paraMap.put("$rwlx$", "");
		// 检查情况
		paraMap.put("$jcqk$", jcqk);
		// 检查时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH时mm分");
		if(null!=tattPo.getJcsj1()&&null!=tattPo.getJcsj2()){
			paraMap.put("$jcsj$", sdf.format(tattPo.getJcsj1())+"至"+sdf2.format(tattPo.getJcsj2()));
		}else{
			paraMap.put("$jcsj$","");
		}
		// 检查地点
		paraMap.put("$jcdd$", tattPo.getJcdd());
		// 受检单位
		if (lawObjType.equals("07")){
			TDataLawobjdic xqyzDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '07' and enumname = ?", LawobjOutColunmEnum.xqyz_dwmc.getCode()).get(0);
			String xqyzDwmc = xqyzDic.getColengname();
			String sql = "select " + xqyzDwmc + " as xqyzDwmc from T_DATA_LAWOBJ where ID_ = ?";
			List<Object[]> data = this.dao.findBySql(sql, lawobjPo.getLawobjid());
			paraMap.put("$sjdw$", String.valueOf(data.get(0)));
		} else {
			paraMap.put("$sjdw$", lawobjPo.getLawobjname());
		}
		
		// 受检单位地址
		paraMap.put("$dwdz$", lawobjPo.getAddress());

		// 法定代表人标题
		paraMap.put("$fddbtit$", lawobjPo.getZwtitle());
		// 法定代表人
		paraMap.put("$fddb$", lawobjPo.getManager());
		// 联系电话
		paraMap.put("$fddbdh$", lawobjPo.getManagermobile());
		// 被检查人
		paraMap.put("$bjcr$", lawobjPo.getBjcr());
		// 部门职务
		paraMap.put("$bjcrzw$", lawobjPo.getZw());
		// 联系电话 
		paraMap.put("$bjcrdh$", lawobjPo.getLxdh());
		// 检查人
		paraMap.put("$jcr$", jcrNames);
		// 记录人
		paraMap.put("$jlr$", jlrPo.getUser().getName() + "(" + jlrPo.getUser().getLawnumber() + ")");
		// 工作单位
		paraMap.put("$gzdw$", jcrPo.getOrg().getUnitname());

		// 根据不同执法对象类型生成不同检查单
		String classPath = this.getClass().getResource("").getPath();
		classPath = java.net.URLDecoder.decode(classPath, "utf-8");
		// 模板路径
		String templatePath = null;
		// 建设项目
		// 检查模板路径
		templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//jcd//moreCheckTemplate.doc";
		
		// 生成的路径
		String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		String filePath = PoiUtil.createWord(templatePath, dirPath, paraMap);
		File file = new File(filePath);

		String newFileType="";
		if("15".equals(taskType)){//专项子任务的单独处理
			newFileType=FileTypeEnums.ZXXDZRWMOREJCBL.getCode();
		}else{
			newFileType=taskType + "07";
		}

		TBizMoreCheck mc = null;
		// 保存文件
		String oName = null;
		Work work  = (Work) this.get(Work.class, applyId);
		List<TBizMoreCheck> mcs= this.dao.find("from TBizMoreCheck where fileid = ? ", fileid);
		// 先保存监察情况
		if(StringUtils.isNotBlank(fileid) && mcs!=null && mcs.size()>0){//此次检查监察笔录已存在，修改检查情况
			mc = mcs.get(0);
			// 先删除旧监察笔录
			List<TDataFile> listFile = this.find("from TDataFile f where f.id = ?", fileid);
			if(listFile.size()>0){
				for(TDataFile filePo : listFile){
					// 删除文件
					new File(dirPath + "//" + filePo.getOsname()).delete();
					this.dao.remove(TDataFile.class, filePo.getId());
				}
			}
			mc.setTimes(mc.getTimes());
			oName = lawobjPo.getLawobjname() + "第"+mc.getTimes()+"次检查现场监察笔录.doc";
			mc.setContent(jcqk);
		}else{
			mc = new TBizMoreCheck();
			mc.setWork(work);
			//得到当前数据库中最大的次数，加1
			int times = commWorkManager.getMaxTimes(applyId, taskType)+1;
			mc.setTimes(times);
			oName = lawobjPo.getLawobjname() + "第"+times+"次检查现场监察笔录.doc";
			mc.setTasktypecode(taskType);
			mc.setContent(jcqk);
			mc.setIsActive("Y");
			mc.setUpdateby(CtxUtil.getCurUser());
			mc.setUpdated(new Date());
			mc.setCreateby(CtxUtil.getCurUser());
			mc.setCreated(new Date());
		}
		
		TDataFile f = commonManager.saveFile(new TDataFile(), file, oName, applyId, newFileType, UploadFileType.WORK.getPath());
		mc.setFileid(f.getId());
		this.dao.save(mc);
	}
	
	@Override
	public void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException {
		if (taskType.equals("15")){
			taskType = FileTypeEnums.ZXXDZRWJCJL.getCode();
		} else {
			taskType = taskType + "00";
		}
		TDataFile filePo = (TDataFile) (this.dao.find("from TDataFile where type = ? and pid = ?", taskType, applyId)).get(0);
		try {
			String path = UploadFileType.WORK.getPath().concat("//" + filePo.getOsname());
			FileUpDownUtil.downloadFile(res, path, filePo.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkJcdExists(String applyId, String taskType)
			throws AppException {
		if (taskType.equals("15")){
			taskType = FileTypeEnums.ZXXDZRWJCJL.getCode();
		} else {
			taskType = taskType + "00";
		}
		
		List<TDataFile> filePo = this.dao.find("from TDataFile where type = ? and pid = ?", taskType, applyId);
		if (filePo.size() > 0){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public FyWebResult getJcmbRecordList(String applyId, String jcmbId, String page, String pageSize) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("SELECT d.taskid_,to_char(t.JCSJ1_,'yyyy-MM-dd hh24:mi:ss') jcsj1,to_char(t.JCSJ2_,'yyyy-MM-dd hh24:mi:ss') jcsj2, to_char(w.ARCHIVES_TIME_,'yyyy-MM-dd hh24:mi:ss') gdsj FROM T_Biz_Taskandlawobj d ");
		sql.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= d.TASKID_ ");
		sql.append(" left join WORK_ w on w.id_ = d.TASKID_  where w.isActive_='Y' and w.state_='09' and t.JCMB_='"+jcmbId+"'");
		List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
		Map<String, String> zfdxMap = new HashMap<String, String>();
		if (zfdxlistMap != null && zfdxlistMap.size() == 1) {
			zfdxMap = zfdxlistMap.get(0);
			sql.append(" and d.lawobjid_ = :zfdxid ");
			condition.put("zfdxid",zfdxMap.get("lawobjid"));
			sql.append(" and d.lawobjtype_ = :zfdxType ");
			condition.put("zfdxType",zfdxMap.get("lawobjtype"));
		}
		sql.append(" order by w.archives_time_ desc");
		FyResult pos = this.dao.find(sql.toString(),Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize),condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> objList = pos.getRe();
        NullControllHashMap d = null;
        for (Object[] obj : objList) {
        	d = new NullControllHashMap();
            Work work = workManagerImpl.get(obj[0]==null?"":String.valueOf(obj[0]));
            d.put("id", obj[0]==null?"":String.valueOf(obj[0]));//任务id
            d.put("taskname", work.getName());//任务名称
            d.put("zxrName", work.getZxrName());//上次归档的执行人
            String jcsj1 = obj[1]==null?"":String.valueOf(obj[1]);
            String jcsj2 = obj[2]==null?"":String.valueOf(obj[2]);
            if(StringUtils.isNotBlank(jcsj1) && StringUtils.isNotBlank(jcsj2)){
            	d.put("jcsj", jcsj1+" 至 "+jcsj2);//检查时间
            }else{
            	d.put("jcsj", "");//检查时间
            }
            d.put("gdsj", obj[3]==null?"":String.valueOf(obj[3]));//归档时间
            rows.add(d);
        }
        fy.setRows(rows);
        return fy;
    }
}
