/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.area;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.hnjz.common.util.StringUtil;
import com.hnjz.sal.WorkClientFactoty;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysOrgArea;
import com.hnjz.sys.po.TSysServer;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 区域管理的manager
 * 
 * @author wumi
 * @version $Id: AreaManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("areaManagerImpl")
public class AreaManagerImpl extends ManagerImpl implements AreaManager {

	/** 日志 */
	private static final Log log = LogFactory.getLog(AreaManagerImpl.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private WorkClientFactoty workClientFactoty;
	
	@Autowired
    private IndexManager     indexManager;

	/**
	 * 根据用户id获取用户所属部门的区域
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户所属部门的区域
	 */
	public TSysArea getAreaByUser(String userId) {
		TSysOrg org = this.userManager.getOrgbyUser(userId);
		if (null == org.getArea()) {
			throw new IllegalArgumentException("系统初始化错误，部门:" + org.getName()
					+ "没有设置区域");
		}
		TSysArea a = (TSysArea) this.dao.get(TSysArea.class, org.getArea()
				.getId());
		return a;
	}

	/**
	 * 查询出所有的区域
	 * 
	 * @return 区域列表
	 * @throws Exception
	 */
	public JSONArray querySelectArea(String id) throws Exception {
		JSONArray re = new JSONArray();
		TSysUser curUser = CtxUtil.getCurUser();
		List<TSysArea> areas = new ArrayList<TSysArea>();
		TSysArea curArea = new TSysArea();
		if (!curUser.getId().equals("a0000000000000000000000000000000")){
			curArea = (TSysArea) this.dao.get(TSysArea.class, CtxUtil.getAreaId());
			areas.add(curArea);
		}
		/**  1，当是admin时查询系统中所有部门 */
		if (curUser.getId().equals("a0000000000000000000000000000000")){
			areas = dao.find("from TSysArea where isActive='Y' order by orderby");
		} 
		/**  2，当是非admin的普通管理员时查询此管理员所属区域的所有部门 */
		else if (curUser.getIssys().equals("Y") && !curUser.getId().equals("a0000000000000000000000000000000")) {
			this.getChs(curArea.getId(), areas);
		}
		
		JSONObject jsonObj = null;
		for (TSysArea ele : areas) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getArea()) {
				jsonObj.put("pid", ele.getArea().getId());
			}
			jsonObj.put("selected", StringUtils.equals(id, ele.getId()));
			jsonObj.put("name", ele.getName());
			jsonObj.put("code", ele.getCode());
			re.put(jsonObj);
		}
		return re;
	}
	
	/**
	 * 获取某个区域下的所有区域
	 * 
	 * @param areaId
	 *            区域ID
	 * @param areas
	 *            递归时传入的结果
	 * @return
	 */
	public void getChs(String areaId, List<TSysArea> areas) {
		List<TSysArea> area = this.dao.find("from TSysArea where isActive = 'Y' and area.id = ? order by orderby", areaId);
		areas.addAll(area);
		for (TSysArea ele : area) {
			this.getChs(ele.getId(), areas);
		}
	}
	
	/**
	 * 获取某个区域下的所有区域
	 * 
	 * @param areaId
	 *            区域ID
	 * @return
	 */
	public List<TSysArea> getChAreas(String areaId) {
		List<TSysArea> areas = new ArrayList<TSysArea>();
		areas.add((TSysArea) this.get(TSysArea.class, areaId));
		this.getChs(areaId, areas);
		return areas;
	}

	/**
	 * 查询功能菜单
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public JSONArray queryArea(String name, String isActive) throws Exception {
		//添加离线版标识
		String biaoshi = indexManager.sysVer;
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysArea where 1=1 ");
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
		sql.append(" order by orderby desc");
		List<TSysArea> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysArea ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if("0".equals(biaoshi)){
				if(null != ele.getArea()){
					if(!"".equals(ele.getArea().getId())){
						if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
							if (StringUtils.isBlank(name) && null != ele.getArea()) {
								jsonObj.put("pid", ele.getArea().getId());
							}
						}
					}
				}
			}else{
				if (!StringUtil.isNotBlank(isActive) || isActive.equals(YnEnum.Y.getCode())){
					if (StringUtils.isBlank(name) && null != ele.getArea()) {
						jsonObj.put("pid", ele.getArea().getId());
					}
				}
			}
			
			JSONObject dataObject = new JSONObject();
			dataObject.put("id", ele.getId());
			dataObject.put("name", ele.getName());
			dataObject.put("code", ele.getCode());
			dataObject.put("orderby", ele.getOrderby());
			if (null != ele.getServer()) {
				dataObject.put("ip", ele.getServer().getUrl());
			}
			dataObject.put("isActive", "Y".equals(ele.getIsActive())?"是":"否");
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			jsonObj.put("dataObject", dataObject);
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * 保存区域
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public void saveArea(AreaForm frm) throws AppException {
		if (StringUtils.equals(frm.getId(), frm.getPid())) {
			throw new AppException("不能选择自己作为父节点！");
		}
		// 区域名称不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TSysArea where isActive = 'Y' and name = :areaName ");
		QueryCondition data = new QueryCondition();
		data.put("areaName", frm.getAreaName());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			log.warn("区域名称不能重复！");
			throw new AppException("区域名称不能重复！");
		}
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		TSysArea po = (TSysArea) this.dao.get(TSysArea.class, frm.getId());
		if (null == po) {
			po = new TSysArea();
			po.setId(frm.getId());
		}

		if (StringUtils.isNotBlank(frm.getPid())) {
			TSysArea p = (TSysArea) this.dao.get(TSysArea.class, frm.getPid());
			po.setArea(p);
		}
		if (StringUtils.isNotBlank(frm.getServer())) {
			TSysServer s = (TSysServer) this.dao.get(TSysServer.class, frm.getServer());
			po.setServer(s);
		}
		po.setCode(frm.getCode());
		po.setOrderby(frm.getOrderby());
		po.setName(frm.getAreaName());
		po.setType(frm.getType());
		po.setUnitname(frm.getDeptName());
		po.setShortunitname(frm.getShortName());
		po.setCreateby(curUser);
		po.setUpdateby(curUser);
		po.setCreated(cur);
		po.setUpdated(cur);
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUnitname(frm.getUnitName());
		po.setDescribe(frm.getDescribe());
		this.save(po);
		workClientFactoty.ref();
	}

	/**
	 * 删除区域
	 * 
	 * @param id
	 *            区域的ID
	 */
	public void removeArea(String id) throws AppException {
		String hsql = "from TSysArea where isActive = 'Y' and area.id = ?";
		List<TSysArea> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			log.warn("当前区域有子节点，不允许删除。");
			throw new AppException("当前区域有子节点，不允许删除。");
		}

		hsql = "from TSysOrgArea where area.id = ?";
		List<TSysOrgArea> orgs = this.dao.find(hsql, id);
		if (!orgs.isEmpty()) {
			log.warn("当前区域和部门有关联，不允许删除。");
			throw new AppException("当前区域和部门有关联，不允许删除。");
		}
		TSysArea po = (TSysArea) this.dao.get(TSysArea.class, id);
		po.setIsActive(YnEnum.N.getCode());
		this.dao.save(po);
		//this.dao.remove(TSysArea.class, id);
	}

	@Override
	public void areaInfo(AreaForm frm){
		//添加离线版标识
		String biaoshi = indexManager.sysVer;
		TSysArea po = (TSysArea) this.get(TSysArea.class, frm.getId());
		frm.setType(po.getType());
		frm.setAreaName(po.getName());
		frm.setOrderby(po.getOrderby());
		frm.setUnitName(po.getUnitname());
		frm.setName(po.getName());
		frm.setCode(po.getCode());
		frm.setDescribe(po.getDescribe());
		frm.setIsActive(po.getIsActive());
		if("0".equals(biaoshi)){
			if (po.getArea() != null) {
				if (StringUtils.isNotBlank(po.getArea().getId())) {
					TSysArea p = (TSysArea) this.get(TSysArea.class, po.getArea().getId());
					frm.setPid(p.getId());
					frm.setPname(p.getName());
				}
			}
		}else{
			if (null != po.getArea()) {
				TSysArea p = (TSysArea) this.get(TSysArea.class, po
						.getArea().getId());
				frm.setPid(p.getId());
				frm.setPname(p.getName());
			}
		}
		if (null != po.getServer()) {
			frm.setServer(po.getServer().getId());
		}
	}
	
	public String getPAreaServerByAreaId(String areaId)throws AppException {
		String hsql = "select area.server.url from TSysArea where isActive = 'Y' and id =:areaId ";
		QueryCondition data = new QueryCondition();
		data.put("areaId", areaId);
		String url = (String) this.dao.find(hsql.toString(), data).get(0);
		if (StringUtils.isBlank(url)) {
			throw new AppException("没有上级区域！");
		}
		return url;
	}
	
	public String getPAreaIdByAreaId(String areaId)throws AppException {
		String hsql = "select area.id from TSysArea where isActive = 'Y' and id =:areaId ";
		QueryCondition data = new QueryCondition();
		data.put("areaId", areaId);
		String pAreaId = (String) this.dao.find(hsql.toString(), data).get(0);
		if (StringUtils.isBlank(pAreaId)) {
			throw new AppException("没有上级区域！");
		}
		return pAreaId;
	}

	@Override
	public TSysArea getAreaByOrgid(String oid) {
		
		TSysArea a = ((TSysOrg) this.dao.get(TSysOrg.class, oid)).getArea();
		return a;
	}

}
