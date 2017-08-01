/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysUser;

/**
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: DicManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
@Service("DicManagerImpl")
public class DicManagerImpl extends ManagerImpl implements DicManager {

	/**
	 * 查询不可编辑分页列表
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public JSONArray queryDic(String type) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		if("0".equals(type)){
			sql.append("from TSysDic where type = '0' ");
		}else{
			sql.append("from TSysDic where type = '1' ");
		}
		sql.append(" and isActive = 'Y' order by orderby");
		List<TSysDic> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysDic ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getPid()) {
				jsonObj.put("pid", ele.getPid());
			}
			
			jsonObj.put("order", ele.getOrderby());
			jsonObj.put("name", ele.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("title", ele.getName());
			
			//operate暂时先自己写，回头替换成OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <input class='bTn orange' value='修改' type='button' onclick='edit(this)' id='"+ele.getId()+"'>");
            operate.append("<input class='bTn orange' value='删除' type='button' onclick='del(this)' id='"+ele.getId()+"'>");
            dataObject.put("ops", operate.toString());
			jsonObj.put("dataObject", dataObject);
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * 删除功能
	 * 
	 * @param id
	 * @throws AppException 
	 */
	public void removeDic(String id) throws AppException {
		String hsql = "from TSysDic where pid = ? and isActive = 'Y'";
		List<TSysDic> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			throw new AppException("当前字典数据有子节点，不允许删除。");
		}
		TSysDic del = (TSysDic) this.dao.get(TSysDic.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	/**
	 * 清空表
	 * 
	 * @param id
	 */
	public void removeDicData() {
		this.dao.removeFindObjs("from TSysDic");
	}

	/**
	 * 保存
	 * 
	 * @param {@link DicForm}
	 */
	public void saveDicData(DicForm frm) throws AppException {
		TSysDic po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysDic) this.get(TSysDic.class, frm.getId());
		} else {
			po = new TSysDic();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setType(frm.getType());
		po.setPid(frm.getPid());
		po.setName(frm.getName());
		po.setDescribe(frm.getNote());
		po.setOrderby(frm.getOrder());
		if (StringUtil.isNotBlank(frm.getIsActive())){
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
	}

	/**
	 * 保存
	 * 
	 * @param {@link DicForm}
	 */
	public int saveBatchDicData(List<DicForm> dicFormList) throws AppException {
		List<TSysDic> dicDataList = new ArrayList<TSysDic>();
		for (DicForm dicForm : dicFormList) {
			TSysDic po = new TSysDic();
			TSysUser user = CtxUtil.getCurUser();
			Date cur = new Date();

			po.setCreateby(user);
			po.setCreated(cur);

			po.setType(dicForm.getType());
			po.setPid(dicForm.getPid());
			po.setName(dicForm.getName());
			po.setDescribe(dicForm.getNote());
			po.setOrderby(dicForm.getOrder());

			po.setIsActive(YnEnum.Y.getCode());
			po.setUpdateby(user);
			po.setUpdated(cur);
			dicDataList.add(po);
		}

		return this.dao.batchSave(dicDataList.toArray());
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public JSONArray dicTypeQuery() throws Exception {
		JSONArray re = new JSONArray();
		List<DicTypeEnum> dics = DicTypeEnum.getListEnum();
		JSONObject jsonObj = null;
		for (DicTypeEnum ele : dics) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getCode());
			jsonObj.put("name", ele.getText());
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * 无分页查询,根据type
	 * 
	 * @throws Exception
	 */
	public JSONArray dicDateQuery(String type) throws Exception {
		JSONArray re = new JSONArray();
		JSONObject jsonObj = null;

		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysDic where type = :type");
		data.put("type", type);
		sql.append("  order by orderby");
		List<TSysDic> pos = dao.find(sql.toString(), data);
		for (TSysDic ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			jsonObj.put("dicType", ele.getType());
			jsonObj.put("code", ele.getPid());
			jsonObj.put("name", ele.getName());
			jsonObj.put("note", ele.getDescribe());
			jsonObj.put("type", "u");
			jsonObj.put("pid", "0");
			re.put(jsonObj);
		}

		return re;
	}

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicData(DicTypeEnum dicType) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, dicType.getCode());
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getPid(), ele.getName()));
		}
		return re;
	}

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicDataByCode(String diccode) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, diccode);
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getPid(), ele.getName()));
		}
		return re;
	}

	/**
	 * 查询违法类型
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryIllegalType() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", "0");
		root.put("name", "违法类型");
		root.put("type", "o");
		list.add(root);
		String hql = "from TSysDic where type = 1 order by orderby";
		List<TSysDic> dicList = dao.find(hql);
		for (TSysDic ele : dicList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ele.getId());
			map.put("name", ele.getName());
			map.put("type", "u");
			map.put("pid", "0");
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicDataId(DicTypeEnum dicType) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, dicType.getCode());
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}

	/**
	 * 通过code查询某个字典数据(code)
	 * 
	 * @param code
	 *            字典编号
	 * @return 某个字典数据
	 */
	public TSysDic queryDicByCode(String type, String code){
		String hsql = "from TSysDic where  type=? and code = ? ";
		List<TSysDic> list = dao.find(hsql, type, code);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询出所有的字典节点
	 * 
	 * @return 字典列表
	 * @throws JSONException 
	 */
	public JSONArray querySelectDic(String id, String type) throws JSONException {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		if("0".equals(type)){
			sql.append("from TSysDic where type = '0' ");
		}else{
			sql.append("from TSysDic where type = '1' ");
		}
		if(null != id && "" != id){
			sql.append(" and id != :type and isActive = 'Y' order by orderby ");
			data.put("type", id);
		}else{
			sql.append(" and isActive = 'Y' order by orderby ");
		}
		List<TSysDic> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysDic ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getPid()) {
				jsonObj.put("pid", ele.getPid());
			}
			if (StringUtils.equals(id, ele.getId())){
				jsonObj.put("checked", "true");
			}
			jsonObj.put("name", ele.getName());
			re.put(jsonObj);
		}
		return re;
	}
	
	/**
	 * 查询所有字典根节点（文档总分类）
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public List<Combobox> queryWdfl(String type){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		List<Combobox> list = new ArrayList<Combobox>();
		sql.append("from TSysDic where pid is null and isActive='Y' ");
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type");
			data.put("type", type);
		}else 
			return list;
		sql.append(" order by orderby ");
		List<TSysDic> pos = dao.find(sql.toString(), data);
		for (TSysDic ele : pos) {
			list.add(new Combobox(ele.getId(),ele.getName()));
		}
		return list;
	}
	/**
	 * 根据文档分类查询所有子类型
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public List<Combobox> queryWdzlx(String pid){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		List<Combobox> list = new ArrayList<Combobox>();
		sql.append("from TSysDic where isActive='Y' ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" and pid = :pid");
			data.put("pid", pid);
		}else 
			return list;
		sql.append(" order by orderby ");
		List<TSysDic> pos = dao.find(sql.toString(), data);
		for (TSysDic ele : pos) {
			list.add(new Combobox(ele.getId(),ele.getName()));
		}
		return list;
	}
}
