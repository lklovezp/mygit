package com.hnjz.mobile.data.xxcx;

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
@Service("discreMobileManager")
public class DiscreMobileManagerImpl extends ManagerImpl implements DiscreMobileManager {

	@Override
	public JSONArray getZyclbzTreeMobile() {
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
//				obj.put("href", "");
				obj.put("level", "1");
				obj.put("children", this.getWfxwByPid(tSysDic.getId(), wfxwList, flyjList, qxflList));
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
//					obj.put("href", "");
					obj.put("level", "2");
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
//					obj.put("href", "");
					obj.put("level", "3");
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
					obj.put("level", "4");
//					obj.put("href", "qxflInfo.mo?id="+tDataDiscrecaseclass.getId());
					array.put(obj);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	
}
