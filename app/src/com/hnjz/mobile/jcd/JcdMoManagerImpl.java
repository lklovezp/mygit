/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.jcd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.data.po.TDataChecklistitemans;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.work.po.TBizChecklist;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;

/**
 * 检查单的manager
 * 
 * @author wumi
 * @version $Id: JcdManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi
 *          Exp $
 */
@Service("jcdMoManagerImpl")
public class JcdMoManagerImpl extends ManagerImpl implements
		JcdMoManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdMoManagerImpl.class);

	/**
	 * 子节点list
	 * key值为终端访问的uuid
	 */
	private HashMap<String, List<HashMap<String, Object>>> subs = new HashMap<String, List<HashMap<String,Object>>>();
	
	@Override
	public List<HashMap<String, Object>> getJcx(String applyId,String selectTaskid, String templateId, String jcmbId) throws AppException {
		List<HashMap<String, Object>> arr = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> ansarr = new ArrayList<HashMap<String,Object>>();
		try {
			HashMap<String, Object> itemobj = new HashMap<String, Object>();
			HashMap<String, Object> ansobj = new HashMap<String, Object>();
			// 查询检查项
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
			// 判断此任务是否填写过检查单
			long count = (Long) this.dao.find("select count(id) from TBizChecklist where taskid = ? and templateid = ? ", applyId, jcmbId).get(0);
			// 如果填写过且选择的历史任务id为空
			if (count > 0 && StringUtils.isBlank(selectTaskid)){
				// 添加检查项节点数据
				for (TDataChecklistitem item : items) {
					// 查询检查项
					itemans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ? order by orderby", item.getId());
					itemobj = new HashMap<String, Object>();
					itemobj.put("id", item.getId());
					itemobj.put("pid", jcmbId);
					List<TBizChecklist> pos = this.dao.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, item.getId());
					//检查单存在该检查项，且该检查项内容不为空
					if(pos != null && pos.size()>0 && StringUtil.isNotBlank(pos.get(0).getItemcontent())){
						itemobj.put("contents",pos.get(0).getItemcontent());
					}else{
						itemobj.put("contents", item.getContents());
					}
					
					itemobj.put("inputtype", item.getInputtype());
					itemobj.put("isrequired", item.getIsrequired());
					itemobj.put("orderby", item.getOrderby());
					// 单选或多选
					if (pos.size() > 0){
						itemobj.put("note", pos.get(0).getDescribe());
					}
					
					ansarr = new ArrayList<HashMap<String,Object>>();
					for (int i = 0; i < itemans.size(); i++) {
						ansobj = new HashMap<String, Object>();
						
						// 单选或多选
						if (!item.getInputtype().equals("0")){
							for (int j = 0; j < pos.size(); j++) {
								if (itemans.get(i).getId().equals(pos.get(j).getAnswerid())){
									ansobj.put("check", true);
								}
							}
						}
						
						ansobj.put("id", itemans.get(i).getId());
						ansobj.put("answer", itemans.get(i).getAnswer());
						ansobj.put("answerdesc", itemans.get(i).getAnswerdesc());
						ansobj.put("orderby", itemans.get(i).getOrderby());
						ansarr.add(ansobj);
					}
					itemobj.put("ans", ansarr);
					itemobj.put("inputtype", item.getInputtype());
					arr.add(itemobj);
				}
			}
			// 未填写过取其他任务最近一次检查记录
			else {
				// 任务和执法对象
				TBizTaskandlawobj lawobjPo = (TBizTaskandlawobj) this.dao.find("from TBizTaskandlawobj where taskid = ?", applyId).get(0);
				// 找到本执法对象所有执行的任务
				//List<String> tasks = this.dao.find("select l.taskid from TBizTaskandlawobj l, WorkLog w where l.taskid = w.work.id and w.workSate ='" + WorkState.YGD.getCode() + "' and l.lawobjid = ? and w.work.areaid = ? order by l.updated desc", lawobjPo.getLawobjid(), CtxUtil.getCurUser().getAreaId());
				
				StringBuffer sql = new StringBuffer("SELECT d.taskid_ FROM T_Biz_Taskandlawobj d ");
				sql.append(" left join T_BIZ_TASKANDTASKTYPE t on t.TASKID_= d.TASKID_ ");
				sql.append(" left join WORK_ w on w.id_ = d.TASKID_  where w.isActive_='Y' and w.state_='09' and t.JCMB_='"+templateId+"'");
				sql.append(" and d.lawobjid_ = '"+lawobjPo.getLawobjid()+"'");
				sql.append(" order by w.archives_time_ desc");
				List<String> tasks = this.dao.findBySql(sql.toString());
				
				// 循环任务找最近一次执行的检查记录
				// 最近一次归档任务的检查记录
				String checkedTaskId = "";
				List<TBizChecklist> checks = new ArrayList<TBizChecklist>();
				if(tasks!=null && tasks.size()>0) {
					if(StringUtils.isNotBlank(selectTaskid)){//选择的历史任务id不为空
						checkedTaskId = selectTaskid;
					}else{
						checkedTaskId = tasks.get(0);
					}
					checks = this.dao.find("from TBizChecklist where taskid = ? and templateid = ?", checkedTaskId, jcmbId);
				}
				if (checks.size() > 0){
					for (TDataChecklistitem item : items) {
						itemans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ? order by orderby", item.getId());
						itemobj = new HashMap<String, Object>();
						itemobj.put("id", item.getId());
						itemobj.put("pid", jcmbId);
						checks = this.dao.find("from TBizChecklist where taskid = ? and itemid = ?", checkedTaskId, item.getId());
						//检查单存在该检查项，且该检查项内容不为空
						if(checks != null && checks.size()>0 && StringUtil.isNotBlank(checks.get(0).getItemcontent())){
							itemobj.put("contents",checks.get(0).getItemcontent());
						}else{
							itemobj.put("contents", item.getContents());
						}
						itemobj.put("inputtype", item.getInputtype());
						itemobj.put("isrequired", item.getIsrequired());
						itemobj.put("orderby", item.getOrderby());
						if (checks.size() > 0){
							itemobj.put("note", checks.get(0).getDescribe());
						}
						ansarr = new ArrayList<HashMap<String,Object>>();
						for (int i = 0; i < itemans.size(); i++) {
							ansobj = new HashMap<String, Object>();
							if (!item.getInputtype().equals("0")){
								for (int j = 0; j < checks.size(); j++) {
									if (itemans.get(i).getId().equals(checks.get(j).getAnswerid())){
										ansobj.put("check", true);
									}
								}
							}
							
							ansobj.put("id", itemans.get(i).getId());
							ansobj.put("answer", itemans.get(i).getAnswer());
							ansobj.put("answerdesc", itemans.get(i).getAnswerdesc());
							ansobj.put("orderby", itemans.get(i).getOrderby());
							ansarr.add(ansobj);
						}
						
						itemobj.put("ans", ansarr);
						arr.add(itemobj);
					}
				} else {
					// 添加检查项节点数据
					for (TDataChecklistitem item : items) {
						// 查询检查项
						itemans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ? order by orderby", item.getId());
						itemobj = new HashMap<String, Object>();
						itemobj.put("id", item.getId());
						itemobj.put("pid", jcmbId);
						List<TBizChecklist> pos = this.dao.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, item.getId());
						//检查单存在该检查项，且该检查项内容不为空
						if(pos != null && pos.size()>0 && StringUtil.isNotBlank(pos.get(0).getItemcontent())){
							itemobj.put("contents",pos.get(0).getItemcontent());
						}else{
							itemobj.put("contents", item.getContents());
						}
						itemobj.put("inputtype", item.getInputtype());
						itemobj.put("isrequired", item.getIsrequired());
						itemobj.put("orderby", item.getOrderby());
						// 单选或多选
						if (pos.size() > 0){
							itemobj.put("note", pos.get(0).getDescribe());
						}
						
						ansarr = new ArrayList<HashMap<String,Object>>();
						for (int i = 0; i < itemans.size(); i++) {
							ansobj = new HashMap<String, Object>();
							
							// 单选或多选
							if (!item.getInputtype().equals("0")){
								for (int j = 0; j < pos.size(); j++) {
									if (itemans.get(i).getId().equals(pos.get(j).getAnswerid())){
										ansobj.put("check", true);
									}
								}
							}
							
							ansobj.put("id", itemans.get(i).getId());
							ansobj.put("answer", itemans.get(i).getAnswer());
							ansobj.put("answerdesc", itemans.get(i).getAnswerdesc());
							ansobj.put("orderby", itemans.get(i).getOrderby());
							ansarr.add(ansobj);
						}
						itemobj.put("ans", ansarr);
						itemobj.put("inputtype", item.getInputtype());
						arr.add(itemobj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("");
		return arr;
	}
	
	@Override
	public void saveTemporary(String applyId, String jcmbId, String itemId,
			String type, String answer, String beizhu, String orderby) throws AppException {
		// 先删除在添加
		this.dao.remove(this.find("from TBizChecklist where itemid = ?", itemId));
		
		TBizChecklist po = new TBizChecklist();
		for (int i = 0; i < answer.split(",").length; i++) {
			po = new TBizChecklist();
			po.setTaskid(applyId);
			po.setTemplateid(jcmbId);
			po.setItemid(itemId);
			po.setItemorderby(Integer.parseInt(orderby));
			po.setAnswerid(answer.split(",")[i]);
			po.setDescribe(beizhu);
			this.dao.save(po);
		}
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
				JSONObject o = new JSONObject();
				for (int i = 0; i < onejcmb.length(); i++) {
					o = onejcmb.getJSONObject(i);
					beizhu = o.getString("beizhu");
					itemId = o.getString("id");
					orderby = o.getString("orderby");
					answer = o.getString("value");
					
					// 先删除在添加
					this.dao.remove(this.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, itemId));
					
					for (int j = 0; j < answer.split(",").length; j++) {
						po = new TBizChecklist();
						po.setTaskid(applyId);
						po.setTemplateid(jcmbIds[k]);
						po.setItemid(itemId);
						po.setItemorderby(Integer.parseInt(orderby));
						po.setItemcontent(o.getString("contents"));
						po.setAnswerid(answer.split(",")[j]);
						po.setDescribe(beizhu);
						this.dao.save(po);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
	
	@Override
	public void downloadCheckListRecord(String applyId, String taskType, HttpServletResponse res) throws AppException {
		if("15".equals(taskType)){//专项子任务的单独处理
			taskType = FileTypeEnums.ZXXDZRWJCJL.getCode();
		}else{
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

	private List<HashMap<String, Object>> templateTreeHelp(String uuid, List<HashMap<String, Object>> list, String pid, int level){
		List<HashMap<String, Object>> j = new ArrayList<HashMap<String, Object>>();
		try {
			List<HashMap<String, Object>> l = new ArrayList<HashMap<String,Object>>();
			for (int i = 0; i < list.size(); i++) {
				if((null == pid && null==list.get(i).get("pId")) || (null != pid && null != list.get(i).get("pId") && pid.equals(list.get(i).get("pId"))) ){
					HashMap<String, Object> obj = new HashMap<String, Object>();
					obj.put("id", list.get(i).get("id"));
					obj.put("name", list.get(i).get("name"));
					obj.put("pId", list.get(i).get("pId"));
					obj.put("orderby", list.get(i).get("orderby"));
					obj.put("level", level);
					level++;
					List<HashMap<String, Object>> a = this.templateTreeHelp(uuid, list, list.get(i).get("id").toString(), level);
					if (a.size() == 0){
						l.add(obj);
					}
					obj.put("children", a);
					j.add(obj);
					level--;
				}
			}
			subs.put(uuid, l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@Override
	public HashMap<String, Object> templateTree(String uuid, String templateId) throws AppException {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> obj = new HashMap<String, Object>();
		try {
			// 当前版本的模板
			TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, templateId);
			// 查询子模板
			List<TDataChecklisttemplate> subTmps = new ArrayList<TDataChecklisttemplate>();
			this.getSubTemplate(templateId, subTmps);
			// 构建json数据
			for (TDataChecklisttemplate subTmp : subTmps) {
				obj = new HashMap<String, Object>();
				obj.put("id", subTmp.getId());
				obj.put("name", subTmp.getName());
				obj.put("pId", subTmp.getPid());
				obj.put("orderby", subTmp.getOrderby());
				obj.put("href", "getJcx.mo");
				list.add(obj);
			}
			
			obj = new HashMap<String, Object>();
			obj.put("id", po.getId());
			obj.put("name", po.getName());
			obj.put("orderby", "0");
			obj.put("pId", "");
			obj.put("level", "0");
			subs.remove(uuid);
			obj.put("children", this.templateTreeHelp(uuid, list, templateId, 1));
		} catch (Exception e) {
			log.error("用户登录查询菜单错误：", e);
		}
		return obj;
	}
	
	@Override
	public HashMap<String, Object> getNextCheckItem(String uuid, String applyId, String selectTaskid, String templateId, String jcmbId) throws AppException{
		HashMap<String, Object> next = null;
		for (int i = 0; i < subs.get(uuid).size(); i++) {
			if (subs.get(uuid).get(i).get("id").equals(jcmbId) && subs.get(uuid).size() > i + 1){
				next = subs.get(uuid).get(i + 1);
			}
		}
		if (next == null) {
			return null;
		} else {
			HashMap<String, Object> o = new HashMap<String, Object>();
			
			o.put("data", this.getJcx(applyId,selectTaskid,templateId,next.get("id").toString()));
			o.put("next", next.get("id").toString());
			
			return o;
		}
	}

	@Override
	public List<TBizChecklist> getAllRecordOfTask(String applyId) {
		List<TBizChecklist> pos = this.dao.find("from TBizChecklist where taskid = ? ", applyId);
		return pos;
	}

	@Override
	public List<TDataChecklistitem> getAllCheckItems(String uuid, String jcmbId) {
		List<TDataChecklistitem> items = new ArrayList<TDataChecklistitem>();
		try {
			HashMap<String, Object> temp = this.templateTree(uuid, jcmbId);
			getAllJcxLoop(items, temp);
		} catch (AppException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	@SuppressWarnings("unchecked")
	private void getAllJcxLoop(List<TDataChecklistitem> items, HashMap<String, Object> temp){
		List<HashMap<String, Object>> subT = (List<HashMap<String, Object>>) temp.get("children");
		if (subT.size() == 0){
			List<TDataChecklistitem> item = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ? order by orderby ", temp.get("id"));
			items.addAll(item);
		} else {
			for (int i = 0; i < subT.size(); i++) {
				getAllJcxLoop(items, subT.get(i));
			}
		}
	}

	@Override
	public List<HashMap<String, Object>> getJcxForCheck(String applyId, String jcmbId) {
		List<HashMap<String, Object>> arr = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> ansarr = new ArrayList<HashMap<String,Object>>();
		try {
			HashMap<String, Object> itemobj = new HashMap<String, Object>();
			HashMap<String, Object> ansobj = new HashMap<String, Object>();
			// 查询检查项
			List<TDataChecklistitem> items = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ? order by orderby", jcmbId);
			// 查询检查项
			List<TDataChecklistitemans> itemans = new ArrayList<TDataChecklistitemans>();
			// 添加检查项节点数据
			for (TDataChecklistitem item : items) {
				// 查询检查项
				itemans = this.dao.find("from TDataChecklistitemans where isActive = 'Y' and itemid = ? order by orderby", item.getId());
				List<TBizChecklist> pos = this.dao.find("from TBizChecklist where taskid = ? and itemid = ?", applyId, item.getId());
				itemobj = new HashMap<String, Object>();
				itemobj.put("id", item.getId());
				itemobj.put("contents", item.getContents());
				itemobj.put("inputtype", item.getInputtype());
				itemobj.put("isrequired", item.getIsrequired());
				itemobj.put("orderby", item.getOrderby());
				// 单选或多选
				if (pos.size() > 0){
					itemobj.put("note", pos.get(0).getDescribe());
				}
				
				ansarr = new ArrayList<HashMap<String,Object>>();
				for (int i = 0; i < itemans.size(); i++) {
					ansobj = new HashMap<String, Object>();
					
					// 单选或多选
					if (!item.getInputtype().equals("0")){
						for (int j = 0; j < pos.size(); j++) {
							if (itemans.get(i).getId().equals(pos.get(j).getAnswerid())){
								ansobj.put("check", true);
							}
						}
					}
					
					ansobj.put("id", itemans.get(i).getId());
					ansobj.put("answer", itemans.get(i).getAnswer());
					ansobj.put("answerdesc", itemans.get(i).getAnswerdesc());
					ansobj.put("orderby", itemans.get(i).getOrderby());
					ansarr.add(ansobj);
				}
				itemobj.put("ans", ansarr);
				itemobj.put("inputtype", item.getInputtype());
				arr.add(itemobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("");
		return arr;
	}
}
