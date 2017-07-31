package com.hnjz.app.data.xxgl.zyclbz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		公共功能Manager实现层
 *
 */
@Service("discreManager")
public class DiscreManagerImpl extends ManagerImpl implements DiscreManager {

	@Override
	public JSONArray getZyclbzTree() {
		JSONArray array = new JSONArray();
		try {
			//制度分类列表
			List<TSysDic> zdflList = this.find("from TSysDic c where c.type=7 and c.isActive = 'Y' ");
			//违法行为列表
			List<TDataDiscreacts> wfxwList = this.find("from TDataDiscreacts c where c.isActive = 'Y' and c.type.id != null ");
			//法律依据列表
			List<TDataDiscremerit> flyjList = this.find("from TDataDiscremerit c where c.isActive = 'Y' and  c.tDataDiscreacts.id != null ");
			//情形分类列表
			List<TDataDiscrecaseclass> qxflList = this.find("from TDataDiscrecaseclass c where c.isActive = 'Y' and  c.tDataDiscremerit.id != null ");
			for(TSysDic tSysDic : zdflList){
				JSONObject obj = new JSONObject();
				obj.put("id", tSysDic.getId());
				obj.put("pId", "0");
				obj.put("name", tSysDic.getName());
				obj.put("href", "wfxwList.htm?id="+tSysDic.getId());
				obj.put("children", this.getWfxwByPid(tSysDic.getId(), wfxwList, flyjList, qxflList));
				array.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	@Override
	public JSONArray getZyclbzTreeByWflxId(String wflxId) {
		JSONArray array = new JSONArray();
		try {
			//违法行为列表
			StringBuffer hql = new StringBuffer("from TDataDiscreacts c where c.isActive = 'Y' and c.type.id != null ");
			if(StringUtils.isNotBlank(wflxId)){
				String[] arg = wflxId.split(",");
				String ids = "";
				for (int i = 0; i < arg.length; i++) {
					ids += "'" + arg[i] + "'";
					if (i != arg.length - 1) {
						ids += ",";
					}
				}
				hql.append(" and c.illegaltype.id in (").append(ids).append(") ");
			}
			List<TDataDiscreacts> wfxwList = this.find(hql.toString());
			//法律依据列表
			List<TDataDiscremerit> flyjList = this.find("from TDataDiscremerit c where c.isActive = 'Y' and  c.tDataDiscreacts.id != null ");
			//情形分类列表
			List<TDataDiscrecaseclass> qxflList = this.find("from TDataDiscrecaseclass c where c.isActive = 'Y' and  c.tDataDiscremerit.id != null ");
			for(TDataDiscreacts tDataDiscreacts : wfxwList){
				JSONObject obj = new JSONObject();
				obj.put("id", tDataDiscreacts.getId());
				obj.put("pId", tDataDiscreacts.getType().getId());
				obj.put("name", tDataDiscreacts.getContent());
				obj.put("href", "flyjList.htm?id="+tDataDiscreacts.getId());
				obj.put("children", this.getFlyjByPid(tDataDiscreacts.getId(), flyjList, qxflList));
				array.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * 
	 * 函数介绍：通过pid获得违法行为列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private JSONArray getWfxwByPid(String pid,List<TDataDiscreacts> wfxwList,List<TDataDiscremerit> flyjList,List<TDataDiscrecaseclass> qxflList){
		JSONArray array = new JSONArray();
		try {
			for(TDataDiscreacts tDataDiscreacts : wfxwList){
				if(tDataDiscreacts.getType().getId().equals(pid)){
					JSONObject obj = new JSONObject();
					obj.put("id", tDataDiscreacts.getId());
					obj.put("pId", tDataDiscreacts.getType().getId());
					obj.put("name", tDataDiscreacts.getContent());
					obj.put("href", "flyjList.htm?id="+tDataDiscreacts.getId());
					obj.put("children", this.getFlyjByPid(tDataDiscreacts.getId(), flyjList, qxflList));
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	/**
	 * 
	 * 函数介绍：通过pid获得法律依据列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private JSONArray getFlyjByPid(String pid,List<TDataDiscremerit> flyjList,List<TDataDiscrecaseclass> qxflList){
		JSONArray array = new JSONArray();
		try {
			for(TDataDiscremerit tDataDiscremerit : flyjList){
				if(tDataDiscremerit.gettDataDiscreacts().getId().equals(pid)){
					JSONObject obj = new JSONObject();
					obj.put("id", tDataDiscremerit.getId());
					obj.put("pId", tDataDiscremerit.gettDataDiscreacts().getId());
					obj.put("name", tDataDiscremerit.getAlias());
					obj.put("href", "qxflList.htm?id="+tDataDiscremerit.getId());
					obj.put("children", this.getQxflByPid(tDataDiscremerit.getId(), qxflList));
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 
	 * 函数介绍：通过pid获得情形分类列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	private JSONArray getQxflByPid(String pid,List<TDataDiscrecaseclass> qxflList){
		JSONArray array = new JSONArray();
		try {
			for(TDataDiscrecaseclass tDataDiscrecaseclass : qxflList){
				if(tDataDiscrecaseclass.gettDataDiscremerit().getId().equals(pid)){
					JSONObject obj = new JSONObject();
					obj.put("id", tDataDiscrecaseclass.getId());
					obj.put("pId", tDataDiscrecaseclass.gettDataDiscremerit().getId());
					obj.put("name", tDataDiscrecaseclass.getName());
					obj.put("href", "qxflInfo.htm?id="+tDataDiscrecaseclass.getId());
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	@Override
	public TSysDic zdflInfo(String id){
		TSysDic tSysDic =  (TSysDic) this.get(TSysDic.class, id);
		return tSysDic;
	}
	
	@Override
	public TDataDiscreacts getTDataDiscreactsInfo(String id){
		TDataDiscreacts tDataDiscreacts = (TDataDiscreacts) this.get(TDataDiscreacts.class, id);
		if(!Hibernate.isInitialized(tDataDiscreacts.getType())){
			Hibernate.initialize(tDataDiscreacts.getType());
		}
		if(!Hibernate.isInitialized(tDataDiscreacts.getIllegaltype())){
			Hibernate.initialize(tDataDiscreacts.getIllegaltype());
		}
		return tDataDiscreacts;
	}
	
	@Override
	public TDataDiscremerit getTDataDiscremeritInfo(String id){
		TDataDiscremerit tDataDiscremerit =  (TDataDiscremerit) this.get(TDataDiscremerit.class, id);
		if(!Hibernate.isInitialized(tDataDiscremerit.gettDataDiscreacts())){
			Hibernate.initialize(tDataDiscremerit.gettDataDiscreacts());
			Hibernate.initialize(tDataDiscremerit.gettDataDiscreacts().getIllegaltype());
			Hibernate.initialize(tDataDiscremerit.gettDataDiscreacts().getType());
		}
		return tDataDiscremerit;
	}
	
	@Override
	public TDataDiscrecaseclass getTDataDiscrecaseclassInfo(String id){
		TDataDiscrecaseclass tDataDiscrecaseclass =  (TDataDiscrecaseclass) this.get(TDataDiscrecaseclass.class, id);
		return tDataDiscrecaseclass;
	}

	@Override
	public void saveOrUpdateWfxw(TDataDiscreacts tDataDiscreacts) {
		TSysUser user = CtxUtil.getCurUser();
		TDataDiscreacts discreacts = null;
		if(StringUtils.isNotBlank(tDataDiscreacts.getId())){
			discreacts = (TDataDiscreacts) this.get(TDataDiscreacts.class, tDataDiscreacts.getId());
			discreacts.setContent(tDataDiscreacts.getContent());
			discreacts.setOrderby(tDataDiscreacts.getOrderby());
		}else{
			discreacts = tDataDiscreacts;
			tDataDiscreacts.setCreateby(user);
			tDataDiscreacts.setCreated(new Date(System.currentTimeMillis()));
		}
		discreacts.setIsActive("Y");
		discreacts.setUpdateby(user);
		discreacts.setUpdated(new Date(System.currentTimeMillis()));
		discreacts.setType((TSysDic) this.get(TSysDic.class, tDataDiscreacts.getType().getId()));
		discreacts.setIllegaltype((TDataIllegaltype) this.get(TDataIllegaltype.class, tDataDiscreacts.getIllegaltype().getId()));
		this.save(discreacts);
	}

	@Override
	public FyWebResult queryWflxList(String pid, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuffer hql = new StringBuffer(" from TDataDiscreacts o where o.isActive='Y' ");
		if(StringUtils.isNotBlank(pid)){
			hql.append(" and o.type.id = :pid ");
			data.put("pid", pid);
		}
		hql.append(" order by o.orderby ");
		FyResult pos = dao.find(hql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<TDataDiscreacts> list = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataDiscreacts ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("content", ele.getContent());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public void saveOrUpdateFlyj(TDataDiscremerit tDataDiscremerit) {
		TSysUser user = CtxUtil.getCurUser();
		TDataDiscremerit discremerit = null;
		if(StringUtils.isNotBlank(tDataDiscremerit.getId())){
			discremerit = (TDataDiscremerit) this.get(TDataDiscremerit.class, tDataDiscremerit.getId());
			discremerit.setAlias(tDataDiscremerit.getAlias());
			discremerit.setContent(tDataDiscremerit.getContent());
			discremerit.setOrderby(tDataDiscremerit.getOrderby());
		}else{
			discremerit = tDataDiscremerit;
			discremerit.setCreateby(user);
			discremerit.setCreated(new Date(System.currentTimeMillis()));
		}
		discremerit.setIsActive("Y");
		discremerit.setUpdateby(user);
		discremerit.setUpdated(new Date(System.currentTimeMillis()));
		discremerit.settDataDiscreacts((TDataDiscreacts) this.get(TDataDiscreacts.class, tDataDiscremerit.gettDataDiscreacts().getId()));
		this.save(discremerit);
	}

	@Override
	public FyWebResult queryFlyjList(String pid, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuffer hql = new StringBuffer(" from TDataDiscremerit o where o.isActive='Y' ");
		if(StringUtils.isNotBlank(pid)){
			hql.append(" and o.tDataDiscreacts.id = :pid ");
			data.put("pid", pid);
		}
		hql.append(" order by o.orderby ");
		FyResult pos = dao.find(hql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<TDataDiscremerit> list = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataDiscremerit ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getAlias());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public void saveOrUpdateQxfl(TDataDiscrecaseclass tDataDiscrecaseclass) {
		TSysUser user = CtxUtil.getCurUser();
		TDataDiscrecaseclass discrecaseclass = null;
		if(StringUtils.isNotBlank(tDataDiscrecaseclass.getId())){
			discrecaseclass = (TDataDiscrecaseclass) this.get(TDataDiscrecaseclass.class, tDataDiscrecaseclass.getId());
			discrecaseclass.setName(tDataDiscrecaseclass.getName());
			discrecaseclass.setContent(tDataDiscrecaseclass.getContent());
			discrecaseclass.setOrderby(tDataDiscrecaseclass.getOrderby());
		}else{
			discrecaseclass = tDataDiscrecaseclass;
			discrecaseclass.setCreateby(user);
			discrecaseclass.setCreated(new Date(System.currentTimeMillis()));
		}
		discrecaseclass.setIsActive("Y");
		discrecaseclass.setUpdateby(user);
		discrecaseclass.setUpdated(new Date(System.currentTimeMillis()));
		discrecaseclass.settDataDiscremerit((TDataDiscremerit) this.get(TDataDiscremerit.class, tDataDiscrecaseclass.gettDataDiscremerit().getId()));
		this.save(discrecaseclass);
	}

	@Override
	public FyWebResult queryQxflList(String pid, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuffer hql = new StringBuffer(" from TDataDiscrecaseclass o where o.isActive='Y' ");
		if(StringUtils.isNotBlank(pid)){
			hql.append(" and o.tDataDiscremerit.id = :pid ");
			data.put("pid", pid);
		}
		hql.append(" order by o.orderby ");
		FyResult pos = dao.find(hql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<TDataDiscrecaseclass> list = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataDiscrecaseclass ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getName());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryQxflListByWflx(String wflxId, String page,String rows) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(rows);
		StringBuffer hql = new StringBuffer(" from TDataDiscrecaseclass o where o.isActive='Y' ");
		if(StringUtils.isNotBlank(wflxId)){
			String[] arg = wflxId.split(",");
			String ids = "";
			for (int i = 0; i < arg.length; i++) {
				ids += "'" + arg[i] + "'";
				if (i != arg.length - 1) {
					ids += ",";
				}
			}
			hql.append(" and o.tDataDiscremerit.tDataDiscreacts.illegaltype.id in (").append(ids).append(") ");
		}
		hql.append(" order by o.orderby ");
		FyResult pos = dao.find(hql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		fy.setRows(result);
		List<TDataDiscrecaseclass> list = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataDiscrecaseclass ele : list) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getName());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			result.add(dataObject);
		}
		return fy;
	}

	@Override
	public void removeWfxw(String id) {
		TDataDiscreacts tDataDiscreacts = (TDataDiscreacts) this.get(TDataDiscreacts.class, id);
		tDataDiscreacts.setIsActive("N");
		this.dao.save(tDataDiscreacts);
	}

	@Override
	public void removeFlyj(String id) {
		TDataDiscremerit tDataDiscremerit = (TDataDiscremerit) this.get(TDataDiscremerit.class, id);
		tDataDiscremerit.setIsActive("N");
		this.dao.save(tDataDiscremerit);
	}

	@Override
	public void removeQxfl(String id) {
		TDataDiscrecaseclass tDataDiscrecaseclass = (TDataDiscrecaseclass) this.get(TDataDiscrecaseclass.class, id);
		tDataDiscrecaseclass.setIsActive("N");
		this.dao.save(tDataDiscrecaseclass);
	}

}
