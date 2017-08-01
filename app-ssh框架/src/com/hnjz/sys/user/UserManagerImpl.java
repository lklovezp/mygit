/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/**
 * 用户管理
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
@Service("userManager")
public class UserManagerImpl extends ManagerImpl implements UserManager{
	/** 日志 */
	private static final Log log = LogFactory.getLog(UserManagerImpl.class);
	/** 用户默认的密码 */
	private static final String DEFAULT_PASS = "888888";
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	/** 用户的缓存，key为usrid,value为用户对应的部门 */
	private Map<String, TSysUser> users = new ConcurrentHashMap<String, TSysUser>();
	/***/
	private TSysUser sjUser;
	/**
	 * 根据用户的登录名查询出符合条件的用户名称
	 * 
	 * @param name
	 *            用户登录名
	 * @return 用户名称，用","隔开
	 */
	public String getUserNames(List<String> names) {
		List<TSysUser> re = this.getUsersByLoginName(names);
		StringBuilder str = new StringBuilder();
		for (TSysUser ele : re) {
			str.append(ele.getName()).append(",");
		}
		if (str.length() > 0) {
			return str.substring(0, str.length() - 1);
		}
		return str.toString();
	}

	/**
	 * 根据用户的登录名查询出符合条件的用户信息(后续改成一次in查询的方式)
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public List<TSysUser> getUsersByLoginName(List<String> names) {
		List<TSysUser> re = new ArrayList<TSysUser>();
		String hsql = "from TSysUser where isActive = 'Y' and username = ?";
		for (String ele : names) {
			List<TSysUser> r = this.dao.find(hsql, ele);
			re.addAll(r);
		}
		return re;
	}

	/**
	 * 根据用户的登录名查询出符合条件的用户信息
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public TSysUser getUserByLoginName(String name) {
		String hsql = "from TSysUser where isActive = 'Y' and username = ?";
		List<TSysUser> r = this.dao.find(hsql, name);
		if (!r.isEmpty()) {
			return r.get(0);
		}
		return null;
	}

	/**
	 * 根据用户Id获取用户
	 * 
	 * @param id
	 *            用户ID
	 * @return 用户
	 */
	public TSysUser getUser(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		TSysUser user = users.get(id);
		if (null == user) {
			user = (TSysUser) this.get(TSysUser.class, id);
			users.put(id, user);
		}
		return user;
	}

	/**
	 * 查询用户列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryUser(String name, String isActive, String page, String pageSize)
			throws Exception {

		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.id_,a.name_,a.username_,b.name_ as orgname_ ");
		sql.append(" from t_sys_user a left join t_sys_org b on a.orgid_ = b.id_ ");
		sql.append(" where a.id_!= 'a0000000000000000000000000000000' ");
		//过滤掉当前用户
		sql.append(" and a.id_ != :id");
		data.put("id", CtxUtil.getUserId());
		
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and (a.name_ like :name  or a.username_ like :name)");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by a.orderby_ asc  ");
		FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> users = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : users) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("name", String.valueOf(ele[1]));
			dataObject.put("userName", String.valueOf(ele[2]));
			dataObject.put("orgname", String.valueOf(ele[3]).equals("null")?" ":String.valueOf(ele[3]));
			StringBuilder operate = new StringBuilder();
			operate.append(" <a onclick='edit(this)' style='color:#0088cc;cursor:pointer;' id='"+ele[0]+"' >修改</a>  ");
	        operate.append(" <a onclick='del(this)' style='color:#0088cc;cursor:pointer;' id='"+ele[0]+"' >删除</a>  ");
			dataObject.put("operate", operate.toString());
			rows.add(dataObject);
		}
		fy.setRows(rows);
		return fy;
	}
	
	/**
	 * 保存一个用户
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void saveUser(UserForm frm, MultipartFile file) throws AppException {
		// 用户登录名不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TSysUser where isActive = 'Y' and username = :username ");
		QueryCondition data = new QueryCondition();
		data.put("username", frm.getUsername());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("用户登录名称不能重复！");
		}

		TSysUser po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysUser) this.get(TSysUser.class, frm.getId());
		} else {
			po = new TSysUser();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		if (StringUtil.isNotBlank(frm.getPassword())) {
			String password = md5PasswordEncoder.encodePassword(frm.getPassword(), null);
			po.setPassword(password);
		}
		po.setName(frm.getName());
		po.setUsername(frm.getUsername());
		if(StringUtils.isNotBlank(frm.getGxOrg())){
			po.setOrgId(frm.getGxOrg());
		}
		if (StringUtils.isNotBlank(frm.getIssys())) {
			po.setIssys(YnEnum.Y.getCode());
		} else {
			po.setIssys(YnEnum.N.getCode());
		}
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		
		po.setUpdateby(user);
		po.setUpdated(cur);
		po.setOrderby(frm.getOrderby());
		po = (TSysUser) this.dao.save(po);
		// 如果当前用户是要修改的用户，将系统初始化的CtxUtil中的user修改。
		if (CtxUtil.getCurUser().getId().equals(frm.getId())){
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(po, po.getPassword(), po.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		if (StringUtils.isNotBlank(po.getId())) {
			users.remove(po.getId());
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void resetPas(String id) throws AppException {
		TSysUser po = (TSysUser) this.get(TSysUser.class, id);
		// 判断当前密码是否正确
		String password = md5PasswordEncoder.encodePassword(DEFAULT_PASS, null);
		po.setPassword(password);
		this.dao.save(po);

	}

	/**
	 * 保存密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public String savePas(UserForm frm) throws AppException {
		if (!frm.getPassword().equals(frm.getConfirmPassword())) {
			throw new AppException("两次新密码不一致，请重新输入!");
		}
		TSysUser po = null;
		TSysUser user = CtxUtil.getCurUser();
		po = (TSysUser) this.get(TSysUser.class, user.getId());
		// 判断当前密码是否正确
		String old = md5PasswordEncoder.encodePassword(frm.getOldPas(), null);
		if (user.getPassword().equals(old)) {
			// 判断密码是否符合规则
			// 字母开头长度为6-16 英文数字下划线
			if (Pattern.compile("^[a-zA-Z]\\w{5,15}$").matcher(frm.getPassword()).matches()){
				String password = md5PasswordEncoder.encodePassword(frm
						.getPassword(), null);
				po.setPassword(password);
				this.dao.save(po);
				return "修改密码成功!";
			} else {
				throw new AppException("密码不符合规则!");
			}
		} else {
			throw new AppException("当前密码不正确!");
		}
	}
	
	/**
	 * 根据用户id获取部门
	 * 
	 * @param userId
	 *            用户Id
	 * @return 部门对象
	 */
	public TSysOrg getOrgbyUser(String userId) {
		TSysOrg org = null;
		String hsql = "from TSysOrg r where r.isActive = 'Y' and r.id in(select ur.orgId from TSysUser ur where ur.id = ?)";
		List<TSysOrg> re = this.dao.find(hsql, userId);
		if (re.isEmpty()) {
			return null;
		} else {
			org = re.get(0);
		}
		return org;
	}

	/**
	 * 删除用户，用户和角色关系
	 * 
	 * @param id
	 *            用户ID
	 * @throws IOException 
	 */
	public void removeUser(String id) throws IOException {
		TSysUser po = (TSysUser) this.dao.get(TSysUser.class, id);
		po.setIsActive(YnEnum.N.getCode());
		this.dao.save(po);
	}

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder) {
		this.md5PasswordEncoder = md5PasswordEncoder;
	}

	public TSysUser getSj() {
		return sjUser;
	}
	
	public UserForm editUser(String id) {
			TSysUser po = (TSysUser) this.get(TSysUser.class, id);
			UserForm userForm = new UserForm();
			userForm.setId(po.getId());
			userForm.setIssys(po.getIssys());
			userForm.setName(po.getName());
			userForm.setUsername(po.getUsername());
			userForm.setOrderby(po.getOrderby());
			userForm.setIsActive(po.getIsActive());
			//用户部门的取值
			TSysOrg o = this.getOrgbyUser(po.getId());
			if (null != o) {
				userForm.setGxOrg(o.getId());
				userForm.setGxOrgName(o.getName());
			}
			return userForm;
	}

}
