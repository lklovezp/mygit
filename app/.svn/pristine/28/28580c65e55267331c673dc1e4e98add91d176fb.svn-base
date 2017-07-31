/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.app.data.po.TDataChecklistitem;
import com.hnjz.app.data.po.TDataChecklistitemans;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 监察模板管理的manager
 * 
 * @author wumi
 * @version $Id: JcdTemplateManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi
 *          Exp $
 */
@Service("jcdTemplateManagerImpl")
public class JcdTemplateManagerImpl extends ManagerImpl implements
		JcdTemplateManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(JcdTemplateManagerImpl.class);
	
	@Autowired
	private IndexManager     indexManager;

	/**
	 * 查询模板树
	 */
	@Override
	public JSONArray queryJcdTemplate(String name) throws Exception {
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		String ver = "";
		JSONArray arr = new JSONArray();
		try {
			JSONObject obj = new JSONObject();
			obj.put("id", "tasktypenode");
			obj.put("name", "任务类型");
			obj.put("href", "#");
			arr.put(obj);
			// 查询所有任务类型
			List<TDataTasktype> tasktypes = this.dao.find("from TDataTasktype where isActive = 'Y' order by orderby");
			// 根节点模板
			List<Object[]> parents = this.find("select distinct a.name, a.industryid, a.tasktypeid, b.orderby from TDataChecklisttemplate a, TDataIndustry b where a.industryid = b.id and a.isActive = 'Y' and a.tasktypeid is not null order by a.name asc");
			// 版本
			List<Object[]> versions = this.find("select id, name, industryid, tasktypeid, release, iscurver from TDataChecklisttemplate where isActive = 'Y' and tasktypeid is not null order by orderby");
			// 子模板
			List<Object[]> sons = this.find("select id, name, pid from TDataChecklisttemplate where isActive = 'Y' and tasktypeid is null or tasktypeid = ''  order by orderby");
			// 添加任务类型节点数据
			for (TDataTasktype tasktype : tasktypes) {
				obj = new JSONObject();
				obj.put("id", tasktype.getCode());
				obj.put("name", tasktype.getName());
				obj.put("pId", "tasktypenode");
				obj.put("href", "newTemplatePage.htm");
				arr.put(obj);
			}
			// 添加监察模板父节点数据
			for (Object[] parent : parents) {
				obj = new JSONObject();
				obj.put("id", String.valueOf(parent[1]) + "_" + String.valueOf(parent[2]));
				obj.put("name", parent[0]);
				obj.put("pId", parent[2]);
				obj.put("href", "editTemplatePage.htm");
				arr.put(obj);
			}
			if("0".equals(biaoshi)){
				// 添加监察模板版本节点数据
				for (Object[] version : versions) {
					if (version[5].equals("Y")){
						obj = new JSONObject();
						obj.put("id", version[0]);
						obj.put("name", version[4] + "（当前版本）");
						obj.put("pId", String.valueOf(version[2]) + "_" + String.valueOf(version[3]));
						obj.put("href", "newVersionPage.htm");
						arr.put(obj);
					} else {
						if(!version[4].equals(Integer.parseInt("99999"))){
							obj = new JSONObject();
							obj.put("id", version[0]);
							obj.put("name", version[4]);
							obj.put("pId", String.valueOf(version[2]) + "_" + String.valueOf(version[3]));
							obj.put("href", "newVersionPage.htm");
							arr.put(obj);
						}
					}
					// 添加子模板节点数据
					for (Object[] son : sons) {
						if(version[0].equals(son[2])){
							obj = new JSONObject();
							obj.put("id", son[0]);
							obj.put("name", son[1]);
							obj.put("pId", son[2]);
							obj.put("href", "newSubTemplatePage.htm");
							arr.put(obj);
						}
					}
				}
			}else{
				// 添加监察模板版本节点数据
				for (Object[] version : versions) {
					obj = new JSONObject();
					obj.put("id", version[0]);
					if (version[5].equals("Y")){
						obj.put("name", version[4] + "（当前版本）");
					} else {
						obj.put("name", version[4]);
					}
					obj.put("pId", String.valueOf(version[2]) + "_" + String.valueOf(version[3]));
					obj.put("href", "newVersionPage.htm");
					arr.put(obj);
				}
				// 添加子模板节点数据
				for (Object[] son : sons) {
					obj = new JSONObject();
					obj.put("id", son[0]);
					obj.put("name", son[1]);
					obj.put("pId", son[2]);
					obj.put("href", "newSubTemplatePage.htm");
					arr.put(obj);
				}
			}
		} catch (JSONException e) {
			log.error("用户登录查询菜单错误：", e);
		}
		log.debug(arr);
		return arr;
	}

	/**
	 *  保存模板
	 */
	@Override
	public void saveTemplate(TemplateForm frm) throws AppException{
		
		List<TDataChecklisttemplate> listPo = new ArrayList<TDataChecklisttemplate>();
		// 编辑模板
		if(frm.getOperate().equals("2")){
			//long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ?", frm.getIndustry(), frm.getTasktype()).get(0);
			//if (count > 0){
			//	throw new AppException("相同的任务类型和行业类型只能有一个监察模板。");
			//} else {
			//	listPo = this.find("from TDataChecklisttemplate where id = ?", frm.getId());
			//}
			
			if (StringUtils.isNotBlank(frm.getId())){
				long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? and id!=?", frm.getIndustry(), frm.getTasktype(),frm.getId()).get(0);
				TDataChecklisttemplate template= (TDataChecklisttemplate)this.get(TDataChecklisttemplate.class, frm.getId());
				if (count > 0){
						throw new AppException("相同的任务类型和行业类型只能有一个监察模板。");
				}else{
						template.setName(frm.getName());
						template.setIndustryid(frm.getIndustry());
						this.dao.save(template);
				}
			}
			
		} else {
			if (StringUtils.isNotBlank(frm.getId())){
				listPo.add((TDataChecklisttemplate)this.get(TDataChecklisttemplate.class, frm.getId()));
			} else {
				// 判断重复
				long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ?", frm.getIndustry(), frm.getTasktype()).get(0);
				if (count > 0){
					throw new AppException("相同的任务类型和行业类型只能有一个监察模板。");
				} else {
					listPo.add(new TDataChecklisttemplate());
				}
			}
		}
		
		for (int i = 0; i < listPo.size(); i++) {
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			
			listPo.get(i).setName(frm.getName());
			listPo.get(i).setIndustryid(frm.getIndustry());
			listPo.get(i).setTasktypeid(frm.getTasktype());
			listPo.get(i).setUpdateby(curUser);
			listPo.get(i).setUpdated(cur);
			
			if(frm.getOperate().equals("2")){
				listPo.get(i).setDescribe(listPo.get(i).getDescribe());
				listPo.get(i).setIsActive(listPo.get(i).getIsActive());
				listPo.get(i).setIscurver(listPo.get(i).getIscurver());
				listPo.get(i).setIsrequired(listPo.get(i).getIsrequired());
				listPo.get(i).setOrderby(listPo.get(i).getOrderby());
				listPo.get(i).setRelease(listPo.get(i).getRelease());
				listPo.get(i).setVersion(listPo.get(i).getVersion());
			} else {
				listPo.get(i).setCreateby(curUser);
				listPo.get(i).setDescribe("");
				listPo.get(i).setIsActive(YnEnum.Y.getCode());
				listPo.get(i).setIscurver(YnEnum.Y.getCode());
				listPo.get(i).setIsrequired(YnEnum.Y.getCode());
				listPo.get(i).setOrderby(0);
				listPo.get(i).setChildnum(0);
				listPo.get(i).setCreated(cur);
				listPo.get(i).setRelease(0);
			}
			
			this.dao.save(listPo.get(i));
		}
	}
	
	/**
	 *  保存版本
	 */
	@Override
	public void saveTemplateVersion(TemplateForm frm) throws AppException{
		TDataChecklisttemplate po = new TDataChecklisttemplate();
		// 判断重复
		long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? and release = ?", frm.getIndustry(), frm.getTasktype(), frm.getRelease()).get(0);
		// 编辑模板
		if(StringUtil.isNotBlank(frm.getId())){
			po = (TDataChecklisttemplate)this.find("from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ?", frm.getIndustry(), frm.getTasktype()).get(0);
		} else {
			if (count > 0){
				throw new AppException("版本号不能重复。");
			} else {
				po = new TDataChecklisttemplate();
			}
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		po.setName(frm.getName());
		po.setIndustryid(frm.getIndustry());
		po.setTasktypeid(frm.getTasktype());
		po.setChildnum(0);
		po.setDescribe(frm.getDescribe());
		po.setIsActive(YnEnum.Y.getCode());
		po.setIsrequired(YnEnum.Y.getCode());
		po.setOrderby(frm.getOrderby());
		po.setRelease(frm.getRelease());
		
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		po.setIscurver("N");
		if(!StringUtil.isNotBlank(frm.getId())){
			po.setCreateby(curUser);
			po.setCreated(cur);
		}
		
		this.dao.save(po);
	}

	/**
	 * 复制版本
	 */
	@Override
	public void saveCopyVersion(TemplateForm frm) throws AppException {
		TDataChecklisttemplate oldPo = new TDataChecklisttemplate();
		TDataChecklisttemplate po = new TDataChecklisttemplate();
		// 判断重复
		long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? and release = ?", frm.getIndustry(), frm.getTasktype(), frm.getRelease()).get(0);
		if (count > 0){
			throw new AppException("版本号不能重复。");
		} else {
			oldPo = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getId());
			po = new TDataChecklisttemplate();
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		po.setName(oldPo.getName());
		po.setChildnum(oldPo.getChildnum());
		po.setIndustryid(oldPo.getIndustryid());
		po.setIsActive(oldPo.getIsActive());
		po.setIscurver(YnEnum.N.getCode());
		po.setIsrequired(oldPo.getIsrequired());
		po.setTasktypeid(oldPo.getTasktypeid());
		po.setDescribe(frm.getDescribe());
		po.setOrderby(frm.getOrderby());
		po.setRelease(frm.getRelease());
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		po.setCreateby(curUser);
		po.setCreated(cur);
		// 保存版本信息
		this.dao.save(po);
		
		this.saveSubTemplate(oldPo.getId(), po.getId());
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
	 * 查询检查项
	 */
	private void getCheckItems(String id, List<TDataChecklistitem> oldCheckItems){
		List<TDataChecklistitem> re = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ?", id);
		oldCheckItems.addAll(re);
	}
	
	/**
	 * 点击树节点跳转页面
	 */
	@Override
	public String treeClickJumpPage(TemplateForm frm) {
		String ret = "";
		// 页面操作
		String operate = frm.getOperate();
		if(operate.equals("1")){
			frm.setTitle("新建模板");
			frm.setTasktype(frm.getTasktype());
			ret = "sys/jcdtemplate/newTemplate";
		} else if (operate.equals("2")){
			TDataChecklisttemplate po = queryTemplateWithNoId(frm);
			frm.setTitle("编辑模板");
			frm.setId(po.getId());
			frm.setName(po.getName());
			frm.setIndustry(po.getIndustryid());
			frm.setTasktype(po.getTasktypeid());
			ret = "sys/jcdtemplate/newTemplate";
		} else if (operate.equals("3")){
			TDataChecklisttemplate po = queryTemplateWithId(frm);
			frm.setTitle("编辑版本");
			frm.setId(po.getId());
			frm.setName(po.getName());
			frm.setDescribe(po.getDescribe());
			frm.setIndustry(po.getIndustryid());
			frm.setOrderby(po.getOrderby());
			frm.setRelease(po.getRelease());
			frm.setTasktype(po.getTasktypeid());
			ret = "sys/jcdtemplate/newVersion";
		} else if (operate.equals("4")){
			frm.setTitle("编辑子模板");
			TDataChecklisttemplate po = queryTemplateWithId(frm);
			frm.setOperate("4");
			frm.setId(po.getId());
			frm.setName(po.getName());
			frm.setPid(po.getPid());
			frm.setDescribe(po.getDescribe());
			frm.setOrderby(po.getOrderby());
			frm.setNamevisiable(po.getNamevisiable());
			ret = "sys/jcdtemplate/newSubTemplate";
		} else if (operate.equals("5")){
			TDataChecklisttemplate po = queryTemplateWithId(frm);
			// 查询是否有检查项
			long count = (Long) this.dao.find("select count(id) from TDataChecklistitem where isActive = 'Y' and template.id = ?", frm.getId()).get(0);
			// 如果存在检查项跳转到列表页面
			if (count > 0) {
				frm.setTitle("检查项");
				ret = "sys/jcdtemplate/checkItemList";
			} else {
				frm.setTitle("编辑子模板");
				ret = "sys/jcdtemplate/newSubTemplate";
			}
			frm.setId(po.getId());
			frm.setName(po.getName());
			frm.setPid(po.getPid());
			frm.setDescribe(po.getDescribe());
			frm.setOrderby(po.getOrderby());
			frm.setNamevisiable(po.getNamevisiable());
		}
		return ret;
	}
	
	/**
	 * 跳转到版本页面
	 */
	@Override
	public String editTemplateVersion(TemplateForm frm) {
		TDataChecklisttemplate po = null; 
		if (frm.getIsCopy().equals(YnEnum.Y.getCode())){
			frm.setTitle("复制版本");
			po = queryTemplateWithId(frm);
			frm.setId(po.getId());
			frm.setDescribe(po.getDescribe());
			frm.setOrderby(po.getOrderby());
		} else {
			frm.setTitle("新建版本");
			po = queryTemplateWithNoId(frm);
		}
		frm.setName(po.getName());
		frm.setIndustry(po.getIndustryid());
		frm.setTasktype(po.getTasktypeid());
		return "sys/jcdtemplate/newVersion";
	}
	
	/**
	 * 使用模板id查询模板信息 一般用于查询版本信息
	 * @param frm
	 * @return
	 */
	private TDataChecklisttemplate queryTemplateWithId(TemplateForm frm){
		TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getId());
		return po;
	}
	
	/** 使用行业和任务类型查询监察模板*/
	public TDataChecklisttemplate queryTemplateWithNoId(TemplateForm frm){
		TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.find(
			"from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? ", frm.getIndustry(), frm.getTasktype()
		).get(0);
		return po;
	}

	/**
	 * 跳转到新建有检查项列表的子模板页面 查询出子模板信息
	 */
	@Override
	public void queryCheckListList(CheckItemForm frm) {
		TDataChecklistitem itemPo = new TDataChecklistitem();
		TDataChecklisttemplate templatePo = new TDataChecklisttemplate();
		if (StringUtil.isNotBlank(frm.getId())){
			itemPo = (TDataChecklistitem) this.dao.get(TDataChecklistitem.class, frm.getId());
			frm.setTemplateid(itemPo.getTemplate().getId());
			frm.setTemplatename(itemPo.getTemplate().getName());
		} else {
			templatePo = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getTemplateid());
			frm.setTemplateid(templatePo.getId());
			frm.setTemplatename(templatePo.getName());
		}
		frm.setId(itemPo.getId());
		frm.setContents(itemPo.getContents());
		frm.setCode(itemPo.getCode());
		frm.setContentsunit(itemPo.getContentsunit());
		frm.setGetlawobjvalue(itemPo.getGetlawobjvalue());
		frm.setInputtype(itemPo.getInputtype());
		frm.setIsActive(itemPo.getIsActive());
		frm.setDescribe(itemPo.getDescribe());
		frm.setIsanswernewline(itemPo.getIsanswernewline());
		frm.setIsrequired(itemPo.getIsrequired());
		frm.setOrderby(itemPo.getOrderby());
	}

	/**
	 * 保存检查项
	 */
	@Override
	public void saveCheckListItem(CheckItemForm frm) throws AppException {
		try {
			TDataChecklistitem po = null;
			if (StringUtils.isNotBlank(frm.getId())){
				po = (TDataChecklistitem)this.get(TDataChecklistitem.class, frm.getId());
			} else {
				po = new TDataChecklistitem();
			}
			
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			
			po.setTemplate((TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getTemplateid()));
			po.setContents(frm.getContents());
			po.setCode(frm.getCode());
			po.setContentsunit(frm.getContentsunit());
			po.setInputtype(frm.getInputtype());
			if (StringUtils.isNotBlank(frm.getIsanswernewline())) {
				po.setIsanswernewline(YnEnum.Y.getCode());
			} else {
				po.setIsanswernewline(YnEnum.N.getCode());
			}
			if (StringUtils.isNotBlank(frm.getIsrequired())) {
				po.setIsrequired(YnEnum.Y.getCode());
			} else {
				po.setIsrequired(YnEnum.N.getCode());
			}
			if (StringUtils.isNotBlank(frm.getIsActive())) {
				po.setIsActive(YnEnum.Y.getCode());
			} else {
				po.setIsActive(YnEnum.N.getCode());
			}
			po.setCreated(cur);
			po.setCreateby(curUser);
			po.setUpdateby(curUser);
			po.setUpdated(cur);
			po.setDescribe(frm.getDescribe());
			po.setOrderby(frm.getOrderby());
			this.dao.save(po);
			
			List<String> ids = this.dao.find("select id from TDataChecklistitemans where isActive = 'Y' and itemid = ? ", po.getId());
			List<String> webids = new ArrayList<String>();
			
			JSONObject objs = new JSONObject(frm.getData());
			JSONArray arr = objs.getJSONArray("rows");
			JSONObject obj = new JSONObject();
			for (int i = 0; i < arr.length(); i++) {
				obj = arr.getJSONObject(i);
				String id = obj.getString("id");
				
				TDataChecklistitemans ansPo = new TDataChecklistitemans();
				// 如果oid不为空则为编辑此操作
				if (StringUtils.isNotBlank(id)) {
					ansPo = (TDataChecklistitemans) this.get(TDataChecklistitemans.class, id);
				} else {
					ansPo.setCreateby(curUser);
					ansPo.setCreated(cur);
				}
				ansPo.setItemid(po.getId());
				ansPo.setAnswer(obj.getString("answer"));
				ansPo.setAnswerdesc(obj.getString("answerdesc"));
				ansPo.setDescribe(obj.getString("describe"));
				ansPo.setIsActive(obj.getString("isActive"));
				ansPo.setIsnormal(obj.getString("isnormal"));
				ansPo.setOrderby(Integer.parseInt(obj.getString("orderby")));
				
				ansPo.setUpdateby(curUser);
				ansPo.setUpdated(cur);
				ansPo.setOrderby(Integer.parseInt(obj.getString("orderby")));
				
				ansPo = (TDataChecklistitemans) this.dao.save(ansPo);
				webids.add(ansPo.getId());
			}
			for (String ele : ids) {
				// 如果操作表中的记录没有在之前保存的记录中 说明此操作已被删除
				if (!webids.contains(ele)) {
					List<TDataChecklistitemans> dels = this.dao.find("from TDataChecklistitemans where id = ?", ele);
					for (int i = 0; i < dels.size(); i++) {
						dels.get(i).setIsActive(YnEnum.N.getCode());
						this.dao.save(dels.get(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询检查项列表数据
	 */
	@Override
	public FyWebResult queryCheckListItem(String templateId, String page,
			String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		
		sql.append("from TDataChecklistitem where isActive = 'Y' and template.id = :templateId order by orderby");
		data.put("templateId", templateId);
		
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataChecklistitem> versions = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataChecklistitem ele : versions) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("contents", ele.getContents());
			if (ele.getInputtype().equals("0")){
				dataObject.put("inputtype", "填空");
			} else if(ele.getInputtype().equals("1")) {
				dataObject.put("inputtype", "单选");
			} else if(ele.getInputtype().equals("2")) {
				dataObject.put("inputtype", "多选");
			}
			
			dataObject.put("isanswernewline", ele.getIsanswernewline().equals(YnEnum.Y.getCode()) ? "是" : "否");
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public FyWebResult queryCheckItemAns(String itemId) {
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataChecklistitemans where isActive = 'Y' and itemid = :itemId");
		data.put("itemId", itemId);
		
		FyResult pos = dao.find(sql.toString(), data, 1);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataChecklistitemans> versions = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataChecklistitemans ele : versions) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("answer", ele.getAnswer());
			dataObject.put("answerdesc", ele.getAnswerdesc());
			dataObject.put("isnormal", ele.getIsnormal());
			dataObject.put("describe", ele.getDescribe());
			dataObject.put("orderby", String.valueOf(ele.getOrderby()));
			dataObject.put("isActive", ele.getIsActive());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}
	
	/**
	 * 跳转到添加子模板页面
	 */
	@Override
	public void addSubTemplate(TemplateForm frm) {
		TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getPid());
		frm.setPid(po.getId());
	}

	/**
	 * 设置默认版本
	 */
	@Override
	public void saveDefaultVersion(TemplateForm frm) {
		// 设置默认版本
		TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, frm.getId());
		po.setIscurver(YnEnum.Y.getCode());
		this.dao.save(po);
		// 将其他版本设置为不是默认版本
		List<TDataChecklisttemplate> listPo = new ArrayList<TDataChecklisttemplate>();
		listPo = this.find("from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? and id != ?", po.getIndustryid(), po.getTasktypeid(), po.getId());
		for (int i = 0; i < listPo.size(); i++) {
			listPo.get(i).setIscurver(YnEnum.N.getCode());
			this.dao.save(listPo.get(i));
		}
	}

	/**
	 * 保存子模板
	 */
	@Override
	public void saveSubTemplate(TemplateForm frm) throws AppException {
		TDataChecklisttemplate po = new TDataChecklisttemplate();
		// 编辑子模板
		if(StringUtil.isNotBlank(frm.getId())){
			po = (TDataChecklisttemplate) this.get(TDataChecklisttemplate.class, frm.getId());
		} else {
			// 判断重复
			long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where name = ? and pid = ? ", frm.getName(), frm.getPid()).get(0);
			if (count > 0){
				throw new AppException("子模板名称不能重复。");
			} else {
				po = new TDataChecklisttemplate();
			}
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		po.setPid(frm.getPid());
		po.setName(frm.getName());
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		po.setCreateby(curUser);
		po.setDescribe(frm.getDescribe());
		po.setIsActive(YnEnum.Y.getCode());
		po.setIsrequired(YnEnum.Y.getCode());
		po.setOrderby(frm.getOrderby());
		po.setChildnum(0);
		po.setCreated(cur);
		if (StringUtil.isNotBlank(frm.getNamevisiable())){
			po.setNamevisiable(YnEnum.Y.getCode());
		} else {
			po.setNamevisiable(YnEnum.N.getCode());
		}
		
		this.dao.save(po);
	}

	/**
	 * 删除检查项
	 */
	@Override
	public void removeCheckListItme(CheckItemForm frm) throws AppException {
		TDataChecklistitem del = (TDataChecklistitem) this.dao.get(TDataChecklistitem.class, frm.getId());
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	@Override
	public void removeTemplate(TemplateForm frm) throws AppException {
		// 查询版本
		List<TDataChecklisttemplate> po = new ArrayList<TDataChecklisttemplate>();
		
		po = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ? ", 
			frm.getIndustry(), frm.getTasktype()
		);
		if (po.size() > 1){
			throw new AppException("该模板有多个版本，不能删除。");
		}
		for (int i = 0; i < po.size(); i++) {
			frm.setId(po.get(i).getId());
			this.removeVersion(frm);
		}
	}
	
	@Override
	public void removeVersion(TemplateForm frm) throws AppException {
		// 查询版本
		this.removeSubTemplate(frm);
	}

	@Override
	public void removeSubTemplate(TemplateForm frm) throws AppException {
		// 查询子模板
		List<TDataChecklisttemplate> subTmps = new ArrayList<TDataChecklisttemplate>();
		TDataChecklisttemplate subTmp = (TDataChecklisttemplate) this.get(TDataChecklisttemplate.class, frm.getId());
		subTmps.add(subTmp);
		this.getSubTemplate(frm.getId(), subTmps);
		if(subTmps.size() > 1){
			throw new AppException("存在子模板，不能删除");
		}
		// 查询检查项
		List<TDataChecklistitem> items = new ArrayList<TDataChecklistitem>();
		if (items.size() > 0){
			throw new AppException("存在检查项，不能删除");
		} 
		for (int i = 0; i < subTmps.size(); i++) {
			this.getCheckItems(subTmps.get(i).getId(), items);
		}
		// 删除
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setIsActive(YnEnum.N.getCode());
			this.dao.save(items.get(i));
		}
		for (int i = 0; i < subTmps.size(); i++) {
			subTmps.get(i).setIsActive(YnEnum.N.getCode());
			this.dao.save(subTmps.get(i));
		}
	}
	
	@Override
	public JSONArray templateTree(String templateId) throws AppException {
		JSONArray arr = new JSONArray();
		try {
			JSONObject obj = new JSONObject();
			// 当前版本的模板
			TDataChecklisttemplate po = (TDataChecklisttemplate) this.dao.get(TDataChecklisttemplate.class, templateId);
			// 查询子模板
			List<TDataChecklisttemplate> subTmps = new ArrayList<TDataChecklisttemplate>();
			this.getSubTemplate(templateId, subTmps);
			// 构建json数据
			obj = new JSONObject();
			obj.put("id", po.getId());
			obj.put("name", po.getName());
			obj.put("href", "");
			arr.put(obj);
			// 添加子模板节点数据
			for (TDataChecklisttemplate subTmp : subTmps) {
				obj = new JSONObject();
				obj.put("id", subTmp.getId());
				obj.put("name", subTmp.getName());
				obj.put("pId", subTmp.getPid());
				obj.put("orderby", subTmp.getOrderby());
				obj.put("href", "getJcx.json");
				arr.put(obj);
			}
		} catch (JSONException e) {
			log.error("用户登录查询菜单错误：", e);
		}
		return arr;
	}

	
	/**
	 * 递归保存子模板
	 * @param id 父节点id
	 */
	public void saveSubTemplate(String vid, String pid){
		List<TDataChecklisttemplate> re = this.dao.find("from TDataChecklisttemplate where pid = ? order by orderby", vid);
		TSysUser curUser = CtxUtil.getCurUser();
		for (TDataChecklisttemplate ele : re) {
			Date cur = new Date();
			// 用于保存子模板的po
			TDataChecklisttemplate subPo = new TDataChecklisttemplate();
			// 用于保存检查项的po
			TDataChecklistitem itemPo = new TDataChecklistitem();
			subPo = new TDataChecklisttemplate();
			subPo.setPid(pid);
			subPo.setName(ele.getName());
			subPo.setChildnum(ele.getChildnum());
			subPo.setIndustryid(ele.getIndustryid());
			subPo.setIsActive(ele.getIsActive());
			subPo.setIscurver(ele.getIscurver());
			subPo.setIsrequired(ele.getIsrequired());
			subPo.setTasktypeid(ele.getTasktypeid());
			subPo.setDescribe(ele.getDescribe());
			subPo.setOrderby(ele.getOrderby());
			subPo.setRelease(ele.getRelease());
			subPo.setUpdateby(curUser);
			subPo.setUpdated(cur);
			subPo.setCreateby(curUser);
			subPo.setCreated(cur);
			subPo.setNamevisiable(ele.getNamevisiable());
			// 保存子模板
			this.dao.save(subPo);
			List<TDataChecklistitem> oldCheckItems = this.dao.find("from TDataChecklistitem where isActive = 'Y' and template.id = ?", ele.getId());
			
			for (int j = 0; j < oldCheckItems.size(); j++) {
				if (oldCheckItems.get(j).getTemplate().getId().equals(ele.getId())){
					List<TDataChecklistitemans> oldCheckItemans = this.dao.find("from TDataChecklistitemans where itemid = ?", oldCheckItems.get(j).getId());
					
					itemPo = new TDataChecklistitem();
					itemPo.setTemplate(subPo);
					itemPo.setContents(oldCheckItems.get(j).getContents());
					itemPo.setContentsunit(oldCheckItems.get(j).getContentsunit());
					itemPo.setCreateby(curUser);
					itemPo.setCreated(cur);
					itemPo.setGetlawobjvalue(oldCheckItems.get(j).getGetlawobjvalue());
					itemPo.setInputtype(oldCheckItems.get(j).getInputtype());
					itemPo.setIsActive(oldCheckItems.get(j).getIsActive());
					itemPo.setIsanswernewline(oldCheckItems.get(j).getIsanswernewline());
					itemPo.setOrderby(oldCheckItems.get(j).getOrderby());
					itemPo.setUpdated(cur);
					itemPo.setUpdateby(curUser);
					itemPo.setIsrequired(oldCheckItems.get(j).getIsrequired());
					// 保存检查项
					this.dao.save(itemPo);
					TDataChecklistitemans oldans = null;
					TDataChecklistitemans newans = null;
					for (int i = 0; i < oldCheckItemans.size(); i++) {
						oldans = oldCheckItemans.get(i);
						newans = new TDataChecklistitemans();
						newans.setItemid(itemPo.getId());
						newans.setAnswer(oldans.getAnswer());
						newans.setAnswerdesc(oldans.getAnswerdesc());
						newans.setDescribe(oldans.getDescribe());
						newans.setIsnormal(oldans.getIsnormal());
						newans.setOrderby(oldans.getOrderby());
						newans.setIsActive(oldans.getIsActive());
						newans.setVersion(oldans.getVersion());
						newans.setCreateby(curUser);
						newans.setCreated(cur);
						newans.setUpdateby(curUser);
						newans.setUpdated(cur);
						this.dao.save(newans);
					}
				}
			}
			
			this.saveSubTemplate(ele.getId(), subPo.getId());
		}
	}
	
	@Override
	public void saveCopyTemplate(String name, String oldIndustry, String oldTaskType, String newIndustry, String newTaskType) throws AppException {
		List<TDataChecklisttemplate> n = this.dao.find("from TDataChecklisttemplate where isActive = 'Y' and industryid = ? and tasktypeid = ?", newIndustry, newTaskType);
		if (n.size() > 0){
			throw new AppException("相同的任务类型和行业类型只能有一个监察模板。");
		}
		long count = (Long) this.dao.find("select count(id) from TDataChecklisttemplate where name = ? and tasktypeid = ? and isActive = 'Y' ", name, newTaskType).get(0);
		if (count > 0){
			throw new AppException("此任务类型下己经存在此名称的模板。");
		}
		
		List<TDataChecklisttemplate> ts = this.dao.find("from TDataChecklisttemplate where industryid = ? and tasktypeid = ? and isActive = 'Y' ", oldIndustry, oldTaskType);
		for (int k = 0; k < ts.size(); k++) {
			TDataChecklisttemplate po = new TDataChecklisttemplate();
			// 取子检查项
			po = new TDataChecklisttemplate();
			
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			
			po.setPid(ts.get(k).getPid());
			po.setName(name);
			po.setChildnum(ts.get(k).getChildnum());
			po.setIndustryid(newIndustry);
			po.setIsActive(ts.get(k).getIsActive());
			po.setIscurver(ts.get(k).getIscurver());
			po.setIsrequired(ts.get(k).getIsrequired());
			po.setTasktypeid(newTaskType);
			po.setDescribe(ts.get(k).getDescribe());
			po.setOrderby(ts.get(k).getOrderby());
			po.setRelease(ts.get(k).getRelease());
			po.setUpdateby(curUser);
			po.setUpdated(cur);
			po.setCreateby(curUser);
			po.setCreated(cur);
			// 保存版本信息
			this.dao.save(po);
			
			this.saveSubTemplate(ts.get(k).getId(), po.getId());
		}
	}
	
	/**
	 * 根据检查模板id获取模板
	 * @param id
	 * @return
	 */
	public TDataChecklisttemplate getChecklisttemplate(String id) {
		TDataChecklisttemplate template = new TDataChecklisttemplate();
		if (null == id) {
			template = (TDataChecklisttemplate) this.get(TDataChecklisttemplate.class, id);
		}
		return template;
	}
	
	/**
	 * 获取某个模板下的所有子模板
	 * @param templateId
	 * @return
	 */
	public List<TDataChecklisttemplate> getChTemplates(String templateId){
		List<TDataChecklisttemplate> templates = new ArrayList<TDataChecklisttemplate>();
		templates.add(this.getChecklisttemplate(templateId));
		this.getSubTemplate(templateId, templates);
		return templates;
	}
}
