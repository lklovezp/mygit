/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.function.FunForm;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;

/**
 * 角色管理类
 * 
 * @author wumi
 * @version $Id: RoleManager.java, v 0.1 Dec 28, 2011 9:22:27 AM Administrator
 *          Exp $
 */
@Service("roleManagerImpl")
public class RoleManagerImpl extends ManagerImpl implements RoleManager {

	/**
	 * 查询出所有有效的角色列表
	 * 
	 * @return 所有有效的角色列表
	 * @throws Exception
	 */
	//后台操作常量
	private static final String operatej="<a id='%s' class='b-link' onclick='htcz(this)'>后台操作设置</a>";
	
	//终端操作常量
	private static final String operatez="<a id='%s' class='b-link' onclick='zdcz(this)'>终端操作设置</a>";

	public List<Combobox> queryRole() throws Exception {
		String hsql = "from TSysRole where isActive = ?";
		List<TSysRole> roles = this.dao.find(hsql, YnEnum.Y.getCode());
		List<Combobox> re = new ArrayList<Combobox>();
		if (CtxUtil.getCurUser().getIssys().equals("Y")){
			if (CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
				for (TSysRole ele : roles) {
					re.add(new Combobox(ele.getId(), ele.getName()));
				}
			} else {
				for (TSysRole ele : roles) {
					if (!ele.getIssys().equals(YnEnum.Y.getCode())){
						re.add(new Combobox(ele.getId(), ele.getName()));
					}
				}
			}
		}
		
		return re;
	}

	/**
	 * 查询角色
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 角色列表
	 * @throws Exception
	 */
	public FyWebResult queryRole(String name, String isActive, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysRole where id != 'a0000000000000000000000000000000'");
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
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TSysRole> roles = pos.getRe();
		Map<String, String> row = null;
		for (TSysRole ele : roles) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("name", ele.getName());
			row.put("note", ele.getDescribe());
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
		//	row.put("operate", OperateUtil.getOperate(ele.getId())+String.format(operatej, String.valueOf(ele.getId()+","+ele.getName()))+String.format(operatez, String.valueOf(ele.getId()+","+ele.getName())));
			row.put("operate", OperateUtil.getOperate(ele.getId())+String.format(operatej, String.valueOf(ele.getId()+","+ele.getName())));
			
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存一个功能菜单
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	@Transactional(readOnly = false)
	public void saveRole(RoleForm frm) throws AppException {

		// 角色名称不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TSysRole where isActive = 'Y' and name = :name ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getName());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("角色名称不能重复！");
		}
		TSysRole po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysRole) this.get(TSysRole.class, frm.getId());
		} else {
			po = new TSysRole();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setName(frm.getName());
		po.setDescribe(frm.getNote());
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		if (StringUtils.isNotBlank(frm.getIsSys())) {
			po.setIssys(YnEnum.Y.getCode());
		} else {
			po.setIssys(YnEnum.N.getCode());
		}
		
		po.setUpdateby(user);
		po.setUpdated(cur);
		po.setOrderby(frm.getOrderby());
		this.dao.save(po);
	}

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 *            角色信息的ID
	 */
	public void removeRole(String id) throws AppException {
		String hsql = "from TSysUserRole where role.id = ?";
		List<TSysUserRole> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			throw new AppException("角色下有用户，请先删除用户信息。");
		}
		TSysRole del = (TSysRole) this.dao.get(TSysRole.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
}
