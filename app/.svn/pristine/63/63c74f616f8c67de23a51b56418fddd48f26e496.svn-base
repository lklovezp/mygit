/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.illegaltype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 违法类型管理的manager
 * 
 * @author wumi
 * @version $Id: IllegalTypeManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("illegalTypeManagerImpl")
public class IllegalTypeManagerImpl extends ManagerImpl implements
		IllegalTypeManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(IllegalTypeManagerImpl.class);

	@Override
	public JSONArray queryIllegalType() throws Exception {
		JSONArray arr = new JSONArray();
		JSONObject top = new JSONObject();
		top.put("id", "topid");
		top.put("pId", "0");
		top.put("name", "违法类型");
		top.put("href", "");
		arr.put(top);
		try {
			String hsql = "from TDataIllegaltype where isActive = 'Y' order by created";
			List<TDataIllegaltype> type = dao.find(hsql);
			// 任务类型
			for (TDataIllegaltype ele : type) {
				JSONObject obj = new JSONObject();
				obj.put("id", ele.getId());
				if (StringUtil.isNotBlank(ele.getPid())){
					obj.put("pId", ele.getPid());
				} else {
					obj.put("pId", "topid");
				}
				obj.put("name", String.valueOf(ele.getName()));
				obj.put("href", "editIllegalType.htm?id=" + ele.getId());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				arr.put(obj);
			}
		} catch (JSONException e) {
			log.error("用户登录查询菜单错误：", e);
		}
		return arr;
	}

	@Override
	public void saveIllegalType(IllegalTypeForm frm) throws AppException{
		TDataIllegaltype po = null;
		// 违法类型不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataIllegaltype where isActive = 'Y' and name = :name ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getName());
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (StringUtils.isNotBlank(frm.getId())){
			po = (TDataIllegaltype)this.get(TDataIllegaltype.class, frm.getId());
		} else {
			if (count > 0) {
				throw new AppException("违法类型名称不能重复。");
			} else {
				po = new TDataIllegaltype();
			}
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		po.setName(frm.getName());
		po.setPid(frm.getPid());
		po.setCreated(cur);
		po.setCreateby(curUser);
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		po.setDescribe(frm.getDescribe());
		po.setOrderby(frm.getOrderby());
		this.dao.save(po);
	}

	@Override
	public JSONArray IllegalTypeInfo(IllegalTypeForm frm) {
		JSONArray arr = new JSONArray();
		try {
			TDataIllegaltype po = new TDataIllegaltype();
			po = (TDataIllegaltype) this.dao.get(TDataIllegaltype.class, frm.getId());
			
			frm.setDescribe(po.getDescribe());
			frm.setName(po.getName());
			frm.setOrderby(po.getOrderby());
			frm.setPid(po.getPid());
			
			JSONObject obj = new JSONObject();
			obj.put("describe", po.getDescribe());
			obj.put("name", po.getName());
			obj.put("orderby", po.getOrderby());
			obj.put("pid", po.getPid());
			arr.put(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	// 递归查找下级违法类型
	private void getChs(String id, List<TDataIllegaltype> pos){
		List<TDataIllegaltype> re = this.dao.find("from TDataIllegaltype where isActive = 'Y' and pid = ?", id);
		pos.addAll(re);
		for (TDataIllegaltype ele : re) {
			this.getChs(ele.getId(), pos);
		}
	}
	
	@Override
	public void removeIllegalType(String id) throws AppException {
		List<TDataIllegaltype> pos = new ArrayList<TDataIllegaltype>();
		this.getChs(id, pos);
		if (pos.size() > 0){
			throw new AppException("此违法类型下有子节点不能删除。");
		}
		pos.add((TDataIllegaltype)this.dao.get(TDataIllegaltype.class, id));
		for (int i = 0; i < pos.size(); i++) {
			pos.get(i).setIsActive(YnEnum.N.getCode());
			this.dao.save(pos.get(i));
		}
		
//		this.dao.remove(this.dao.get(TDataIllegaltype.class, id));
	}
}
