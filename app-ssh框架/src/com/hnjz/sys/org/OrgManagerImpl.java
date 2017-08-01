/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: OrgManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
@Service("orgManagerImpl")
public class OrgManagerImpl extends ManagerImpl implements OrgManager {

	/** 日志 */
	private static final Log log = LogFactory.getLog(OrgManagerImpl.class);

	@Autowired
	private UserManager userManager;

	/** 用户对应部门的缓存，key为orgid,value为用户对应的部门 */
	private Map<String, TSysOrg> orgs = new ConcurrentHashMap<String, TSysOrg>();

	/** 用户对应部门的缓存，key为orgId,value为部门对应的主管 */
	private Map<String, TSysUser> leaders = new ConcurrentHashMap<String, TSysUser>();

	/**
	 * 根据用户ID获取用户的部门
	 */
	public TSysOrg getOrgByUserid(String userId){
		return this.userManager.getOrgbyUser(userId);
	}

	/**
	 * 获取某个部门下的所有部门,及本部门
	 * 
	 * @param orgId
	 *            部门id
	 * @return 某个部门下的所有部门
	 */
	public List<TSysOrg> getChOrgs(String orgId) {
		List<TSysOrg> orgs = new ArrayList<TSysOrg>();
		orgs.add(this.getOrg(orgId));
		this.getChs(orgId, orgs);
		return orgs;
	}

	/**
	 * 获取某个部门下的所有部门
	 * 
	 * @param orgId
	 *            部门ID
	 * @param orgs
	 *            递归时传入的结果
	 * @return
	 */
	public void getChs(String orgId, List<TSysOrg> orgs) {
		String hsql = "from TSysOrg where isActive = 'Y' and org.id = ? order by orderby";
		List<TSysOrg> re = this.dao.find(hsql, orgId);
		orgs.addAll(re);
		for (TSysOrg ele : re) {
			this.getChs(ele.getId(), orgs);
		}
	}
	
	/**
	 * 获取某个部门下的所有部门
	 * 
	 * @param orgId
	 *            部门ID
	 * @param orgs
	 *            递归时传入的结果
	 * @return
	 */
	public List<TSysOrg> getChs(String orgId) {
		String hsql = "from TSysOrg where isActive = 'Y' and org.id = ?";
		return this.dao.find(hsql, orgId);
	}

	/**
	 * 获取部门
	 * 
	 * @param list
	 * @param orgid
	 * @param curOrgId
	 */
	public void digui(List<TSysOrg> list, String orgid, String curOrgId) {
		TSysOrg temp = this.getOrg(orgid);
		if (!curOrgId.equals(temp.getId()) && temp.getOrg() != null) {
			if (log.isDebugEnabled()) {
				log.debug("部门名称：" + temp.getName());
			}
			list.add(temp);
			this.digui(list, temp.getOrg().getId(), curOrgId);
		}
	}

	/**
	 * 获取用户的主管
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户的主管
	 */
	public TSysUser getLeaderByUser(String userId) {
		TSysOrg org = this.userManager.getOrgbyUser(userId);
		if (null != org) {
			return this.getLeaderByOrg(org.getId());
		}
		return null;
	}

	/**
	 * 根据部门获取部门的主管
	 * 
	 * @param orgId
	 *            部门id
	 * @return 部门的主管
	 */
	public TSysUser getLeaderByOrg(String orgId) {
		TSysUser leader = leaders.get(orgId);
		if (null == leader) {
			String hsql = "select t.leader1 from TSysOrg t where t.id = ?";
			List<TSysUser> users = this.dao.find(hsql, orgId);
			if (!users.isEmpty()) {
				leader = users.get(0);
				leaders.put(orgId, leader);
			}
		}
		return leader;
	}

	/**
	 * 根据部门Id获取部门
	 * 
	 * @param id
	 *            部门ID
	 * @return 部门
	 */
	public TSysOrg getOrg(String id) {
		TSysOrg org = orgs.get(id);
		if (null == org) {
			org = (TSysOrg) this.get(TSysOrg.class, id);
			orgs.put(id, org);
		}
		return org;
	}

	/**
	 * 查询出所有的部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray querySelectOrg(String id) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where isActive = 'Y' ");
		sql.append(" order by orderby ");
		List<TSysOrg> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysOrg ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getOrg()) {
				jsonObj.put("pid", ele.getOrg().getId());
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
	 * admin登录 查询出所有的部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray queryAdminOrg(String id,String areaid) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where isActive = 'Y' ");
		String areaId = CtxUtil.getOrgId();
		if(StringUtils.isNotBlank(areaid)){
			areaId = areaid;
		}
		if (!CtxUtil.getCurUser().getIssys().equals("Y") && StringUtils.isNotBlank(areaId)) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		// 如果是管理员但不是超级管理员 加区域限制
		if (CtxUtil.getCurUser().getIssys().equals("Y")) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		sql.append(" order by orderby ");
		List<TSysOrg> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysOrg ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getOrg()) {
				jsonObj.put("pid", ele.getOrg().getId());
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
	 * 查询部门
	 * 
	 * @return 部门列表
	 * @throws Exception
	 */
	public JSONArray queryOrg(String name, String isActive) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :name ");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by orderby ");
		List<TSysOrg> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysOrg ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
				if (StringUtils.isBlank(name) && null != ele.getOrg()) {
					jsonObj.put("pid", ele.getOrg().getId());
				}
			}
			
			jsonObj.put("order", ele.getOrderby());
			jsonObj.put("note", ele.getDescribe());
			jsonObj.put("name", ele.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("name", ele.getName());
			dataObject.put("gzdw", ele.getUnitname());
			
			//operate暂时先自己写，回头替换成OperateUtil.getOperate(ele.getId())
            StringBuilder operate = new StringBuilder();
            operate.append(" <a onclick='edit(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >修改</a>  ");
            operate.append(" <a onclick='del(this)' style='color:#0088cc;cursor:pointer;' id='"+ele.getId()+"' >删除</a>  ");
            dataObject.put("operate", operate.toString());
			dataObject.put("order", ele.getOrderby());
			dataObject.put("note", ele.getDescribe());
			dataObject.put("isActive", "Y".equals(ele.getIsActive())?"是":"否");
			jsonObj.put("dataObject", dataObject);
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * 保存一个部门
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void saveOrg(OrgForm frm) throws AppException {
		TSysOrg po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		// 一个人员不能做为多个部门的领导
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysOrg) this.get(TSysOrg.class, frm.getId());
		} else {
			po = new TSysOrg();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		if (StringUtils.isNotBlank(frm.getOrg())) {
			TSysOrg parent = (TSysOrg) this.get(TSysOrg.class, frm.getOrg());
			po.setOrg(parent);
		}
		po.setName(frm.getName());
		po.setUnitname(frm.getGzdw());
		po.setDescribe(frm.getNote());
		if (StringUtils.isEmpty(frm.getOrderby())) {
			po.setOrderby(0);
		} else {
			po.setOrderby(Integer.parseInt(frm.getOrderby()));
		}

		if (StringUtil.isNotBlank(frm.getIsActive())){
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
		orgs.remove(po.getId());
	}

	/**
	 * 删除部门信息
	 * 
	 * @param id
	 *            功能菜单信息的ID
	 */
	public void removeOrg(String id) throws AppException {
		String hsql = "from TSysOrg where org.id = ?";
		List<TSysOrg> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			throw new AppException("当前部门有子节点，不允许删除。");
		}
		TSysOrg del = (TSysOrg) this.dao.get(TSysOrg.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	public void orgInfo(OrgForm frm) {
		TSysOrg po = (TSysOrg) this.dao.get(TSysOrg.class, frm.getId());
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setNote(po.getDescribe());
		frm.setOrderby(String.valueOf(po.getOrderby()));
		if (null != po.getOrg()) {
			TSysOrg org = (TSysOrg) this.dao.get(TSysOrg.class, po
					.getOrg().getId());
			frm.setOrg(org.getId());
			frm.setOrgName(org.getName());
		}
		frm.setGzdw(po.getUnitname());
		frm.setIsActive(po.getIsActive());
		if (log.isDebugEnabled()) {
			log.debug("frm:" + LogUtil.m(frm));
		}		
	}

}
