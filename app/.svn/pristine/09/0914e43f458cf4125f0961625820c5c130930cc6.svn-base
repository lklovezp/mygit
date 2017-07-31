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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.po.TSysUserRole;

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
	
	@Autowired
	private AreaManager areaManager;

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
		String areaId = CtxUtil.getAreaId();
		
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
		
		// 二，用户部门关联表查询
		List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
		// 循环部门查询部门用户关系
		for (int i = 0; i < pos.size(); i++) {
			List<TSysUserOrg> userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and org.id = ? order by user.orderby", pos.get(i).getId());
			userOrgs.addAll(userOrg);
		}

		JSONObject obj = null;
		for (TSysUserOrg ele : userOrgs) {
			// 可以不显示自己
			if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
				continue;
			}
			// 不显示管理员
			if (isNotShowSys && StringUtils.equals(ele.getUser().getIssys(), YnEnum.Y.getCode())){
				continue;
			}
			obj = new JSONObject();
			obj.put("id", ele.getUser().getId());
			obj.put("pid", ele.getOrg().getId());
			//obj.put("iconSkin", "iconuser");
			obj.put("type", "u");
			obj.put("nocheck", false);
			obj.put("selected", false);
			obj.put("checked", false);
			if (null != id) {
				for (String mx : id) {
					if (StringUtils.equals(mx, ele.getUser().getId())) {
						obj.put("selected", true);
						obj.put("checked", true);
						break;
					}
				}
			}
			obj.put("name", ele.getUser().getName());
			if (log.isDebugEnabled()) {
				log.debug("obj:" + obj);
			}
			re.put(obj);
		}

		for (TSysOrg ele : pos) {
			obj = new JSONObject();
			obj.put("id", ele.getId());

			if (null != ele.getOrg()) {
				obj.put("pid", ele.getOrg().getId());
			}
			if (!isAll && StringUtils.equals(curOrg.getId(), ele.getId())) {
				obj.put("pid", "");
			}
			obj.put("nocheck", true);
			//obj.put("iconSkin", "icondept");
			obj.put("name", ele.getName());
			obj.put("isorg", true);
			re.put(obj);
		}
		return re;
	}

	public JSONArray taskUserPubQuery(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist, String condition) {
		JSONArray re = new JSONArray();
		try{
			// 不显示自己
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// 查看所有
			boolean isAll = StringUtils.equals(all, "true");
			boolean isDisplayAll = StringUtils.equals(displayAll, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//是否显示同级部门的人
			boolean isShowBj = StringUtils.equals(showBj, "true");
			//是否是专员
			String Zy = "";
			boolean isZy = StringUtils.equals(Zy, "true");
			// 当前用户
			TSysUser curUser = CtxUtil.getCurUser();
			String area = curUser.getAreaId();
			// 当前用户所属部门
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// 是否是部门领导
			boolean isLeader = false;
			// 查询当前用户是否是当前的师级领导
			List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? and type = '0'", curOrg.getId(), curUser.getId());
			if (orgs.size() > 0){
				isLeader = true;
			}
			List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", curUser.getId());
			List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", curUser.getId());
			if(xfzydata.size() != 0 || rczydata.size() != 0){
				isZy = true;
			}
			if (isAll){
				if(isDisplayAll){
					/**查询本区域及所有下级区域 */
					List<TSysArea> areas = areaManager.getChAreas(area);
					List<TSysOrg> temp = new ArrayList<TSysOrg>();
					for(int i = 0; i < areas.size(); i++){
						temp = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areas.get(i).getId());
						pos.addAll(temp);
					}
				}else{
					if(isLeader){
						isLeader = false;
						pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
					}else{
						if("0".equals(condition)){
							pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
						}else{
							if(isZy){
								pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? and type != '0' order by orderby",area);
							}else{
								pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
							}
						}
					}
				}
			} else {
				if(StringUtils.isNotBlank(areaid) && !StringUtils.equals(areaid, CtxUtil.getAreaId())){//所选区域不是本区域
					/** 添加该区域内所有部门 */
					pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areaid);
				}else{
					// 一，部门查询
					// 查询当前用户是否是当前的师级领导
					List<TSysOrg> orgsl = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? ", curOrg.getId(), curUser.getId());
					
					/** 1，当是领导时查询本级部门及下级部门 */
					if (orgsl.size() > 0){
						isLeader = true;
						// 添加下级部门
						for (int i = 0; i < orgsl.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					}
					/** 2，当是管理员时查询本级部门及下级部门*/
					else if(isShowBj){
						// 添加下级部门
						List<TSysOrg> org = this.dao.find("from TSysOrg where isActive='Y' and id = ? ", curOrg.getId());
						isLeader = true;
						for (int i = 0; org!=null && i < org.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					} 
					/** 3，当不是领导时只查询下级部门 */
					else {
						orgManager.getChs(curOrg.getId(), pos);
						TSysOrg o = (TSysOrg) this.dao.get(TSysOrg.class, curOrg.getId());
						pos.add(o);
					}
				}
				
			}
			
			// 二，用户部门关联表查询
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// 循环部门查询部门用户关系
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// 查询业务类型为执法的人员
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// 查询业务类型为执法的人员
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// 查询业务类型为执法的人员
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// 可以不显示自己
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
	
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	private Integer jsonHelp(JSONArray array,String pid,Integer count){
		try {
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				if(obj.getString("id").equals(pid) && StringUtils.isNotBlank(obj.getString("pid"))){
					count++;
					count = this.jsonHelp(array, obj.getString("pid"), count);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public JSONArray taskUserPubQueryByRole(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// 不显示自己
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// 查看所有
			boolean isAll = StringUtils.equals(all, "true");
			boolean isDisplayAll = StringUtils.equals(displayAll, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//是否显示同级部门的人
			boolean isShowBj = StringUtils.equals(showBj, "true");
			// 当前用户
			TSysUser curUser = CtxUtil.getCurUser();
			String area = curUser.getAreaId();
			// 当前用户所属部门
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// 是否是部门领导
			boolean isLeader = false;
			// 查询当前用户是否是当前的师级领导
			List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? and type = '0'", curOrg.getId(), curUser.getId());
			if (orgs.size() > 0){
				isLeader = true;
			}
			//总队可以选择所有的部门
			if (isAll){
				if(isDisplayAll){
					/**查询本区域及所有下级区域 */
					List<TSysArea> areas = areaManager.getChAreas(area);
					List<TSysOrg> temp = new ArrayList<TSysOrg>();
					for(int i = 0; i < areas.size(); i++){
						temp = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
						pos.addAll(temp);
					}
				}else{
					pos = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
				}
			} else {
				if(StringUtils.isNotBlank(areaid) && !StringUtils.equals(areaid, CtxUtil.getAreaId())){//所选区域不是本区域
					/** 添加该区域内所有部门 */
					pos = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
				}else{
					// 一，部门查询
					List<TSysOrg> orgsl = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ?", curOrg.getId(), curUser.getId());
					/** 1，当是领导时查询本级部门及下级部门 */
					if (orgsl.size() > 0){
						isLeader = true;
						// 添加下级部门
						for (int i = 0; i < orgsl.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					}
					/** 2，当是管理员时查询本级部门及下级部门*/
					else if(isShowBj){
						// 添加下级部门
						List<TSysOrg> org = this.dao.find("from TSysOrg where isActive='Y' and id = ? ", curOrg.getId());
						isLeader = true;
						for (int i = 0; org!=null && i < org.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					} 
					/** 3，当不是领导时只查询下级部门 */
					else {
						orgManager.getChs(curOrg.getId(), pos);
						TSysOrg o = (TSysOrg) this.dao.get(TSysOrg.class, curOrg.getId());
						pos.add(o);
					}
				}
			}
			
			// 二，用户部门关联表查询
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// 循环部门查询部门用户关系
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// 查询业务类型为执法的人员
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// 查询业务类型为执法的人员
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// 查询业务类型为执法的人员
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
			
			//添加除总队外部门单位以及对应的信访专员
			List<TSysOrg> orglow = null;
			//把备注改为标识的判断  XFZY 和 RCZY方便查询构造用户数据
			List<TSysRole> roledata = null;
			if("1".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'XFZY')))");
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'XFZY'");
			}else if("2".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'RCZY')))");
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'RCZY'");
			}
			pos.addAll(orglow);
			for (int i = 0; i < orglow.size(); i++) {
				String xfzyid = roledata.get(0).getId();
				// 查询业务类型为执法的人员
				if (pos.get(i).getId().equals(curOrg.getId())){
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", xfzyid, curUser.getId(), pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", xfzyid, pos.get(i).getId());
					userOrgs.addAll(userOrg);
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// 可以不显示自己
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	public JSONArray taskUserPubQueryByRoleWithout(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// 不显示自己
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// 查看所有
			boolean isAll = StringUtils.equals(all, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//是否显示同级部门的人
			// 当前用户
			TSysUser curUser = CtxUtil.getCurUser();
			// 当前用户所属部门
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// 是否是部门领导
			boolean isLeader = false;
			
			// 二，用户部门关联表查询
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// 循环部门查询部门用户关系
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// 查询业务类型为执法的人员
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// 查询业务类型为执法的人员
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// 查询业务类型为执法的人员
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
			
			//添加除总队外部门单位以及对应的信访专员
			List<TSysOrg> orglow = null;
			TSysOrg orgup = null;
			//把备注改为标识的判断  XFZY 和 RCZY方便查询构造用户数据
			List<TSysRole> roledata = null;
			if("3".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'XFZY')))");
				for (int i = 0; i < orglow.size(); i++) {
					if(orglow.get(i).getOrg() != null){
						orgup = orgManager.getOrg(orglow.get(i).getOrg().getId());
						orglow.add(orgup);
					}
				}
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'XFZY'");
			}else if("4".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'RCZY')))");
				for (int i = 0; i < orglow.size(); i++) {
					if(orglow.get(i).getOrg() != null){
						orgup = orgManager.getOrg(orglow.get(i).getOrg().getId());
						orglow.add(orgup);
					}
				}
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'RCZY'");
			}
			//把添加的重复的上级区域部门去掉
            for(int i=0; i < orglow.size();i++){
            	for(int j=orglow.size()-1;j > i;j--){
					if(String.valueOf(orglow.get(i).getId()).equals(String.valueOf(orglow.get(j).getId()))){
						orglow.remove(j);
	                 }
            	}
            }
			pos.addAll(orglow);
			for (int i = 0; i < orglow.size(); i++) {
				String xfzyid = roledata.get(0).getId();
				// 查询业务类型为执法的人员
				if (pos.get(i).getId().equals(curOrg.getId())){
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", xfzyid, curUser.getId(), pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", xfzyid, pos.get(i).getId());
					userOrgs.addAll(userOrg);
				}
			}
			
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				if(!"a0000000000000000000000000000000".equals(ele.getOrg().getId())){
					// 可以不显示自己
					if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
						continue;
					}
					if(!isShowExist){
						if (ids.contains(ele.getUser().getId())) {
							continue;
						}
					}
					obj = new JSONObject();
					obj.put("id", ele.getUser().getId());
					obj.put("pid", ele.getOrg().getId());
					obj.put("iconSkin", "iconuser");
					obj.put("type", "u");
					obj.put("nocheck", false);
					obj.put("isorg", false);
					if (null != ids) {
						for (String mx : ids) {
							if (StringUtils.equals(mx, ele.getUser().getId())) {
								obj.put("selected", true);
								obj.put("checked", true);
								break;
							}
						}
					}
					obj.put("name", ele.getUser().getName());
					if (log.isDebugEnabled()) {
						log.debug("obj:" + obj);
					}
					re.put(obj);
				}
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				if(!"a0000000000000000000000000000000".equals(ele.getId())){
					obj.put("id", ele.getId());
					if (null != ele.getOrg()) {
						obj.put("pid", ele.getOrg().getId());
					} else {
						obj.put("pid", "");
					}
					obj.put("nocheck", true);
					obj.put("iconSkin", "icondept");
					obj.put("name", ele.getName());
					obj.put("isorg", true);
					re.put(obj);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	public JSONArray ZdtaskUserPubQueryByRoleWithout(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// 不显示自己
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// 查看所有
			boolean isAll = StringUtils.equals(all, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			//总队可以选择所有的部门
			if (isAll){
				List<TSysOrg> temp = new ArrayList<TSysOrg>();
				temp = this.dao.find("from TSysOrg where id = 'a0000000000000000000000000000000' and isActive='Y'");
				pos.addAll(temp);
			}
			
			// 二，用户部门关联表查询
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// 循环部门查询部门用户关系
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// 查询业务类型为执法的人员
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					for(int k=0;k<userOrg.size();k++){
						UserPosition pzd = orgManager.getPosition(userOrg.get(k).getUser().getId());
						if(pzd == UserPosition.ZD){
							userOrg.remove(k);
						}
					}
					userOrgs.addAll(userOrg);
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// 可以不显示自己
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
}
