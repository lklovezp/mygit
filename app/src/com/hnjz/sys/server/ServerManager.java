/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sal.WorkClientFactoty;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysServer;

/**
 * 服务器管理的Manager
 * 
 * @author wumi
 * @version $Id: ServerManager.java, v 0.1 2013-3-26 下午02:21:34 wumi Exp $
 */
@Service("serverManager")
public class ServerManager extends ManagerImpl {

	@Autowired
	private AreaManager areaManager;
	@Autowired
	private WorkClientFactoty workClientFactoty;

	/**
	 * 获取当前登录用户所属部门的区域的服务器
	 * 
	 * @param userId
	 *            用户Id
	 * @return 用户所属部门的区域的服务器
	 */
	public TSysServer getServerByUserId(String userId) {
		TSysArea a = this.areaManager.getAreaByUser(userId);
		return a.getServer();
	}

	/**
	 * 查询出所有有效的服务器列表
	 * 
	 * @return 所有有效的服务器列表
	 * @throws Exception
	 */
	public List<Combobox> queryServer() throws Exception {
		String hsql = "from TSysServer where isActive = 'Y'";
		List<TSysServer> da = this.dao.find(hsql);
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysServer ele : da) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}

	/**
	 * 查询服务器
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @param pageSize2 
	 * @return 服务器列表
	 * @throws Exception
	 */
	public FyWebResult queryServer(String name, String isActive, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysServer where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and (name like :name or url like:name)");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TSysServer> s = pos.getRe();
		Map<String, String> row = null;
		for (TSysServer ele : s) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("name", ele.getName());
			row.put("ip", ele.getUrl());
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存一个服务器对象
	 * 
	 * @param frm
	 *            服务器表单对象
	 * @throws AppException
	 *             名称或ip重复抛出此异常
	 */
	public void saveServer(ServerForm frm) throws AppException {

		// 服务器名称不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TSysServer where isActive = 'Y' and name = :name ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getName());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("服务器名称不能重复！");
		}

		// 服务器ip不能重复
		hsq = new StringBuilder();
		hsq.append("select count(id) from TSysServer where isActive = 'Y' and url = :url ");
		data = new QueryCondition();
		data.put("url", frm.getUrl());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("服务器ip不能重复！");
		}

		TSysServer po = null;
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysServer) this.get(TSysServer.class, frm.getId());
		} else {
			po = new TSysServer();
			po.setCreated(cur);
			po.setCreateby(CtxUtil.getCurUser());
		}
		po.setName(frm.getName());
		po.setUrl(frm.getUrl());
		
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdated(cur);
		po.setOrderby(0);
		po.setUpdateby(CtxUtil.getCurUser());
		
		this.dao.save(po);
		workClientFactoty.ref();
	}

	/**
	 * 删除服务器信息
	 * 
	 * @param id
	 *            服务器信息的ID
	 */
	public void removeServer(String id) throws AppException {
		String hsql = "from TSysArea where isActive = 'Y' and server.id=?";
		List<TSysArea> areas = this.dao.find(hsql, id);
		if (!areas.isEmpty()) {
			throw new AppException("服务器目前被使用，不能删除！");
		}
		TSysServer del = (TSysServer) this.dao.get(TSysServer.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

	/**
	 * 
	 * 函数介绍：查询服务器信息
	 * 
	 * 输入参数：frm:服务器表单实体
	 * 
	 * 返回值：ServerForm：服务器表单实体
	 */
	public void serverInfo(ServerForm frm) {
		TSysServer po = (TSysServer) this.get(TSysServer.class, frm.getId());
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setUrl(po.getUrl());
	}
}
