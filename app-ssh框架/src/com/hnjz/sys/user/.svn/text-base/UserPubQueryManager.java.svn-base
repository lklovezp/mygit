/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/**
 * 用户公共选择的帮助类
 * 
 * @author wumi
 * @version $Id: UserPubQueryManager.java, v 0.1 Jan 28, 2013 8:49:46 AM wumi
 *          Exp $
 */
@Service("userPubQueryManager")
public class UserPubQueryManager {

	/** 日志 */
	private static final Log log = LogFactory.getLog(UserPubQueryManager.class);

	@Autowired
	private Dao dao;

	@Autowired
	private UserManager userManager;

	@Autowired
	private OrgManager orgManager;
	
	/**
	 * 选择人员
	 * 
	 * @param id
	 *            用户已经选中的人员id数组
	 * @param all
	 *            true显示所有用户(办公室可以选择所有人员)；为空或其他不是true的字符串 显示自己部门及下级部门的用户
	 * @param notShowZj
	 *            true不显示自己；为空或其他不是true的字符串 把自己显示出来
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryUser(String[] id, String all, String notShowZj, String notShowSys)
			throws Exception {
		boolean isAll = StringUtils.equals(all, "true");
		boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
		boolean isNotShowSys = StringUtils.equals(notShowSys, "true");
		JSONArray re = new JSONArray();
		// 当前用户
		TSysUser curUser = CtxUtil.getCurUser();
		// 当前用户所属部门
		TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
		// 当前用户所在区域
		String areaId = CtxUtil.getOrgId();
		
		String hsql = null;
		
		List<TSysOrg> pos = new ArrayList<TSysOrg>();
		// 一，部门查询
		/**  1，当是admin时查询系统中所有部门 */
		if (curUser.getId().equals("a0000000000000000000000000000000")){
			hsql = "from TSysOrg where isActive=? order by orderby";
			pos = dao.find(hsql, YnEnum.Y.getCode());
		} else {
			/**  2，当是非admin的普通管理员时查询此管理员所属区域的所有部门 */
			if (curUser.getIssys().equals("Y")){
				hsql = "from TSysOrg where isActive=? and area.id = ? order by orderby";
				pos = dao.find(hsql, YnEnum.Y.getCode(), areaId);
			} else {
				/**  3，当是普通用户时  先按照条件isAll是否全部显示查询 */
				if (isAll) {
					// 查询所有可用的部门
					hsql = "from TSysOrg where isActive=? and area.id = ? order by orderby";
					pos = dao.find(hsql, YnEnum.Y.getCode(), areaId);
				} 
				/** 4，按照是否是部门领导查询 */
				else {
					// 查询部门领导是当前用户的部门
					List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and leader1.id = ?", curUser.getId());
					/** 4.1，当是领导时查询本级部门及下级部门 */
					if (orgs.size() >  0){
						// 查询上级部门是这些部门的部门
						for (int i = 0; i < orgs.size(); i++) {
							List<TSysOrg> lowerOrgs = this.dao.find("from TSysOrg where isActive='Y' and org.id = ?", orgs.get(i).getId());
							pos.addAll(lowerOrgs);
						}
						pos.addAll(orgs);
					} 
					/** 4.2，当不是领导时只查询下级部门 */
					else {
						pos = orgManager.getChs(curOrg.getId());
					}
				}
			}
		}
		return re;
	}
	
}
