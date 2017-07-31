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

import com.hnjz.IndexManager;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.function.FunForm;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysOrgArea;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPosition;

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

	@Autowired
	private AreaManager areaManager;
	
	@Autowired
    private IndexManager     indexManager;

	/** 用户对应部门的缓存，key为orgid,value为用户对应的部门 */
	private Map<String, TSysOrg> orgs = new ConcurrentHashMap<String, TSysOrg>();

	/** 用户对应部门的缓存，key为orgId,value为部门对应的主管 */
	private Map<String, TSysUser> leaders = new ConcurrentHashMap<String, TSysUser>();

	private int zd = -9;

	private int leader_ = -4;

	private int zy = 20;

	/**
	 * 根据用户ID获取用户的职位，{@link UserPosition}
	 * 
	 * @param userId
	 *            用户id
	 * @return {@link UserPosition}
	 */
	public UserPosition getPosition(String userId) {
		TSysOrg org = this.userManager.getOrgbyUser(userId);
		return this.getPosition(org, userId);
	}

	/**
	 * 根据用户ID获取用户的部门
	 */
	public TSysOrg getOrgByUserid(String userId){
		return this.userManager.getOrgbyUser(userId);
	}

	
	/**
	 * 根据部门和用户获取用户的职位
	 * 
	 * @param org
	 * @param userId
	 * @return
	 */
	public UserPosition getPosition(TSysOrg org, String userId) {
		UserPosition pos = UserPosition.KY;
		if (null != org) {
			TSysUser l = this.getLeaderByOrg(org.getId());
			boolean isLeader = StringUtils.equals(l.getId(), userId);
			String type = org.getType();
			// 总队长，副总队
			if (StringUtils.equals(type, OrgType.ZD.getCode())) {
				if (isLeader) {
					pos = UserPosition.ZD;
				} else {
					String hsql = "from TSysOrg where isActive = 'Y' and org.id = ? ";
					List<TSysOrg> list = this.dao.find(hsql, org.getId());
					if(list.isEmpty()){
						pos = UserPosition.KY;
					}else{
						pos = UserPosition.FD;
					}
				}
				// 科长
			} else if (StringUtils.equals(type, OrgType.KS.getCode())) {
				if (isLeader) {
					pos = UserPosition.KZ;
				} else {
					pos = UserPosition.KY;
				}
			} else if (StringUtils.equals(type, OrgType.BGS.getCode())) {
				pos = UserPosition.BGS;
			} else if (StringUtils.equals(type, OrgType.CS.getCode())) {
				pos = UserPosition.FD;
			}
		}
		return pos;
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
	 * 跨级派发，转派时，获取出需要补全的用户
	 * 
	 * @param pfrId
	 *            派发人id(当前用户),为null表示从总队长开始
	 * @param jsrId
	 *            接收人id(任务接受人id)
	 * @return
	 */
	public List<TSysUser> getUsers(String pfrId, String jsrId, boolean isXp) {
		List<TSysUser> re = new ArrayList<TSysUser>();
		List<TSysOrg> list = new ArrayList<TSysOrg>();
		TSysOrg cur = null;
		if (StringUtils.isBlank(pfrId)) {
			String hsql = "from TSysOrg where isActive = 'Y' and type = ? and area.id = ?";
			TSysArea a = areaManager.getAreaByUser(jsrId);
			List<TSysOrg> org = this.dao.find(hsql, OrgType.ZD.getCode(), a
					.getId());
			cur = org.get(0);
			TSysUser l = this.getLeaderByOrg(cur.getId());
			re.add(l);
		} else {
			cur = userManager.getOrgbyUser(pfrId);
			// 办公室转派上级下派的任务时，需要将总队，副总队审核
			if (StringUtils.equals(cur.getType(), OrgType.BGS.getCode())
					&& isXp) {
				re.add(this.userManager.getUser(pfrId));
				re.addAll(this.getUsers(null, jsrId, isXp));
				return re;
			}
		}
		TSysOrg org = userManager.getOrgbyUser(jsrId);
		// 派发人转派人在同一部门,把当前部门的主管加上即可
		if (StringUtils.equals(org.getId(), cur.getId())) {
			TSysUser l = this.getLeaderByOrg(org.getId());
			if (!StringUtils.equals(jsrId, l.getId()) && !re.contains(l)) {
				re.add(l);
				if (log.isDebugEnabled()) {
					log.debug("姓名：" + l.getName());
				}
			}
			return re;
		}
		this.digui(list, org.getId(), cur.getId());
		// 总队长需特殊处理，
		if (StringUtils.equals(cur.getType(), OrgType.ZD.getCode())) {
			TSysUser l = this.getLeaderByOrg(cur.getId());
			if (StringUtils.equals(l.getId(), pfrId)) {
				list.add(cur);
			}
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			TSysOrg ele = list.get(i);
			TSysUser leader = this.getLeaderByOrg(ele.getId());
			if (StringUtils.equals(jsrId, leader.getId())
					|| re.contains(leader)) {
				continue;
			}
			re.add(leader);

		}
		if (log.isDebugEnabled()) {
			for (TSysUser ele : re) {
				log.debug("姓名：" + ele.getName());
			}
		}
		return re;

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
	public JSONArray querySelectOrg(String id,String areaid) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where isActive = 'Y' ");
		String areaId = CtxUtil.getAreaId();
		if(StringUtils.isNotBlank(areaid)){
			areaId = areaid;
		}
		if (!CtxUtil.getCurUser().getIssys().equals("Y") && StringUtils.isNotBlank(areaId)) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		// 如果是管理员但不是超级管理员 加区域限制
		if (CtxUtil.getCurUser().getIssys().equals("Y")&& !CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")) {
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
		String areaId = CtxUtil.getAreaId();
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
		String biaoshi = indexManager.sysVer;
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysOrg where 1=1 ");
		String areaId = CtxUtil.getAreaId();
		if (!CtxUtil.getCurUser().getIssys().equals("Y") && StringUtils.isNotBlank(areaId)) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
		// 如果是管理员但不是超级管理员 加区域限制
		if (CtxUtil.getCurUser().getIssys().equals("Y") && !CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")) {
			sql.append(" and area.id = :area");
			data.put("area", areaId);
		}
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
			if("0".equals(biaoshi)){
				if(null != ele.getOrg()){
					if(!"".equals(ele.getOrg().getId())){
						if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
							if (StringUtils.isBlank(name) && null != ele.getOrg()) {
								jsonObj.put("pid", ele.getOrg().getId());
							}
						}
					}
				}
			}else{
				if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
					if (StringUtils.isBlank(name) && null != ele.getOrg()) {
						jsonObj.put("pid", ele.getOrg().getId());
					}
				}
			}
			
			jsonObj.put("order", ele.getOrderby());
			jsonObj.put("name", ele.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("name", ele.getName());
			TSysUser leader = this.getLeaderByOrg(ele.getId());
			if (null != leader) {
				dataObject.put("leader", leader.getName());
			}
			dataObject.put("note", ele.getDescribe());
			dataObject.put("gzdw", ele.getUnitname());
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			dataObject.put("order", ele.getOrderby());
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
		String hsql = "from TSysOrg where isActive = 'Y' and leader1.id = ?";
		List<TSysOrg> re = null;
		if (StringUtils.isNotBlank(frm.getId())) {
			hsql = "from TSysOrg where isActive = 'Y' and leader1.id = ? and id != ?";
			re = this.dao.find(hsql, frm.getLeader(), frm.getId());
			po = (TSysOrg) this.get(TSysOrg.class, frm.getId());
		} else {
			hsql = "from TSysOrg where isActive = 'Y' and leader1.id = ? ";
			re = this.dao.find(hsql, frm.getLeader());
			po = new TSysOrg();
			po.setCreateby(user);
			po.setCreated(cur);
		}

		if (StringUtils.isNotBlank(frm.getOrg())) {
			TSysOrg parent = (TSysOrg) this.get(TSysOrg.class, frm.getOrg());
			po.setOrg(parent);
		}
		
		
		if (!re.isEmpty()) {
			TSysOrg l = re.get(0);
			String msg = frm.getLeaderName().concat("已经是部门").concat(
					l.getName()).concat("的主管");
			throw new AppException(msg);
		}
		
		TSysUser leaderUser1 = null;
		if (null != po.getLeader1()) {
			String hsql2 = "from TSysUser where isActive = 'Y' and id = ?";
			List<TSysUser> re2 = this.dao.find(hsql2, po.getLeader1()
					.getId());
			if (re2 != null && re2.size() > 0) {
				leaderUser1 = re2.get(0);
			}
		}
		TSysUser old = leaderUser1;
		if (null != old) {
			old.setOrderby(zy);
			this.dao.save(old);
		}

		TSysUser leader = (TSysUser) this.dao.get(TSysUser.class, frm
				.getLeader());
		po.setLeader1(leader);
		// 更新区域时，把用户的所属区域也更新了
		if (StringUtils.isNotBlank(frm.getArea())) {
			TSysArea a = (TSysArea) this.dao.get(TSysArea.class, frm.getArea());
			po.setArea(a);
			if (StringUtils.isNotBlank(po.getId())) {
				hsql = "select o from TSysUserOrg s,TSysUser o where o.id = s.user.id and s.org.id = ?";
				List<TSysUser> us = this.dao.find(hsql, po.getId());
				for (TSysUser ele : us) {
					ele.setAreaId(a.getId());
					this.dao.save(ele);
				}
			}
		}
		po.setType(frm.getBmlx());
		po.setName(frm.getName());
		po.setDescribe(frm.getNote());
		po.setUnitname(frm.getGzdw());
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
		po.setBizType(frm.getBiztype());
		this.dao.save(po);

		leaders.remove(po.getId());
		orgs.remove(po.getId());
		
		// 保存所辖区域
		this.dao.remove(this.find("from TSysOrgArea where org.id = ? ", po.getId()));
		TSysOrgArea p = null;
		if (StringUtil.isNotBlank(frm.getDominarea())){
			for (int i = 0; i < frm.getDominarea().split(",").length; i++) {
				p = new TSysOrgArea();
				p.setArea((TSysArea)this.dao.get(TSysArea.class, frm.getDominarea().split(",")[i]));
				p.setOrg(po);
				this.dao.save(p);
			}
		}
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

		hsql = "from TSysUserOrg where org.id = ?";
		List<TSysUserOrg> users = this.dao.find(hsql, id);
		if (!users.isEmpty()) {
			throw new AppException("当前部门下还有用户，不允许删除。");
		}
		TSysOrg del = (TSysOrg) this.dao.get(TSysOrg.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	/**
	 * 系统管理员可以更新用户所属的区域为所在部门的区域
	 * 
	 * @throws AppException
	 */
	public void saveUserArea() throws AppException {
		TSysUser u = CtxUtil.getCurUser();
		if (StringUtils.equals(u.getIssys(), YnEnum.Y.getCode())) {
			throw new AppException("您不是系统管理员，不能操作");
		}
		StringBuilder hsq = new StringBuilder();
		hsq.append("from TSysOrg where 1=1 ");
		String areaId = CtxUtil.getAreaId();
		QueryCondition data = new QueryCondition();
		if (StringUtils.isNotBlank(areaId)) {
			hsq.append("and area.id = :areaId");
			data.put("areaId", areaId);
		}
		List<TSysOrg> orgs = this.dao.find(hsq.toString(), data);
		for (TSysOrg ele : orgs) {
			if (null == ele.getArea()) {
				continue;
			}
			String hsql = "select o from TSysUserOrg s,TSysUser o where o.id = s.user and s.org = ?";
			List<TSysUser> us = this.dao.find(hsql, ele.getId());
			for (TSysUser mx : us) {
				mx.setAreaId(ele.getArea().getId());
				this.dao.save(mx);
			}
		}
	}

	@Override
	public void orgInfo(OrgForm frm) {
		//添加离线版标识
		String biaoshi = indexManager.sysVer;
		TSysOrg po = (TSysOrg) this.dao.get(TSysOrg.class, frm.getId());
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setNote(po.getDescribe());
		frm.setOrderby(String.valueOf(po.getOrderby()));
		frm.setBiztype(po.getBizType());
		if("0".equals(biaoshi)){
			if (StringUtils.isNotBlank(po.getOrg().getId())) {
				TSysOrg org = (TSysOrg) this.dao.get(TSysOrg.class, po
						.getOrg().getId());
				frm.setOrg(org.getId());
				frm.setOrgName(org.getName());
			}
		}else{
			if (null != po.getOrg()) {
				TSysOrg org = (TSysOrg) this.dao.get(TSysOrg.class, po
						.getOrg().getId());
				frm.setOrg(org.getId());
				frm.setOrgName(org.getName());
			}
		}
		frm.setBmlx(po.getType());
		frm.setGzdw(po.getUnitname());
		if (null != po.getLeader1()) {
			TSysUser leader = (TSysUser) this.userManager.get(TSysUser.class, po
					.getLeader1().getId());
			frm.setLeader(leader.getId());
			frm.setLeaderName(leader.getName());
		}
		if (null != po.getArea()) {
			TSysArea a = (TSysArea) this.areaManager.get(TSysArea.class, po
					.getArea().getId());
			frm.setArea(a.getId());
			frm.setAreaName(a.getName());
		}
		
		List<TSysOrgArea> p = this.dao.find("from TSysOrgArea where org.id = ?", frm.getId());
		String dominarea = "";
		String dominareaName = "";
		for (int i = 0; i < p.size(); i++) {
			if (i > 0){
				dominarea += ",";
				dominareaName += ",";
			}
			dominarea += p.get(i).getArea().getId();
			dominareaName += p.get(i).getArea().getName();
		}
		frm.setDominarea(dominarea);
		frm.setDominareaName(dominareaName);
		frm.setIsActive(po.getIsActive());
		if (log.isDebugEnabled()) {
			log.debug("frm:" + LogUtil.m(frm));
		}		
	}

	@Override
	public List<TSysUser> queryUsersByOrgId(String orgId) throws Exception {
		List<TSysUser> users=null;
		if(StringUtil.isNotBlank(orgId)){
			
			users=this.dao.find("select o from TSysUserOrg s,TSysUser o where o.id = s.user.id and o.isRecWork='Y' and o.isActive='Y' and s.org.id = ?", orgId);
		}
		return users;
	}
	/**
	 * 根据部门获取部门的主管
	 * 
	 * @param orgId
	 *            部门id
	 * @return 部门的主管
	 */
	@Override
	public TSysUser getLeaderByAreaId(String areaid)throws Exception{
		TSysUser leader = null;
		if (StringUtil.isNotBlank(areaid)) {
			String hsql = "select t.leader1 from TSysOrg t where t.type='0' and t.area.id = ? and t.isActive = 'Y' ";
			List<TSysUser> users = this.dao.find(hsql, areaid);
			if (!users.isEmpty()) {
				leader = users.get(0);
				//leaders.put(areaid, leader);
			}
		}
		return leader;
	}
}
