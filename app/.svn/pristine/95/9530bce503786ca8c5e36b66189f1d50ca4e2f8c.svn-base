/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.sys.po.TSysLog;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 用户登录
 * 
 * @author wumi
 * @version $Id: LoginManager.java, v 0.1 Apr 22, 2013 10:39:00 AM wumi Exp $
 */
@Service("loginManager")
public class LoginManager {

	@Autowired
	private UserDetailsService userDetailServiceImpl;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private UserManager userManager;
	@Autowired
	private Dao dao;
	/** 已经登录的用户列表 **/
	private Map<String, TSysUser> loginUsers = new ConcurrentHashMap<String, TSysUser>();

	/**
	 * 手机端用户登录，登录成功后，返回给客户端一个32位的uuid作为标示
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param request 
	 * @param name
	 *            用户名
	 * @param pwd
	 *            密码
	 */
	public void saveLogin(ModelMap model, HttpServletRequest request, String name, String pwd)
			throws UsernameNotFoundException {
		UserDetails d = this.userDetailServiceImpl.loadUserByUsername(name);
		String pass = d.getPassword();
		String p = md5PasswordEncoder.encodePassword(pwd, null);
		if (!StringUtils.equals(p, pass)) {
			JsonResultUtil.fail(model, "密码错误");
			this.saveLoginLog(request, name, "1", "N");
		} else {
			TSysUser u = (TSysUser) d;
			model.addAttribute("state", Boolean.TRUE);
			model.addAttribute("msg", "登录成功");
			model.addAttribute("userId", u.getId());
			model.addAttribute("userName", u.getName());
			TSysOrg org = userManager.getOrgbyUser(u.getId());
			if(org!=null){
				model.addAttribute("orgId", org.getId());
				model.addAttribute("orgName", org.getName());
			}
			List<Object> list = this.dao.findBySql("select g.unitname_ from t_sys_user u left join t_sys_userorg o on u.id_ = o.userid_ left join t_sys_org g on o.orgid_ = g.id_ where u.isactive_ = 'Y' and g.isactive_ = 'Y' and u.id_ = ? ", u.getId());
			if (list.size() > 0) {
				model.addAttribute("dwmc", String.valueOf(list.get(0)));
			} else {
				model.addAttribute("dwmc", "");
			}
			model.addAttribute("zfzh", u.getLawnumber());
			list = this.dao.find("select id from TDataFile f where f.pid = ?", u.getId());
			// 登录标示
			String biaoshi = UUID.randomUUID().toString();
			//System.out.println("loginuuid:---------------" + biaoshi);
			model.addAttribute("uuid", biaoshi);
			if (list.size() > 0) {
				model.addAttribute("imgurl", "/download.mo?uuid=" + biaoshi + "&id=" + String.valueOf(list.get(0)));
			} else {
				model.addAttribute("imgurl", "");
			}
			loginUsers.put(biaoshi, u);
			String JSESSIONID = request.getSession().getId();
			model.addAttribute("jsessionid", JSESSIONID);
			this.saveLoginLog(request, name, "1", "Y");
		}
	}

	/**
	 * 根据用户的登录标示获取已经登录的用户
	 * 
	 * @param uuid
	 * @return
	 */
	public TSysUser getLoginUser(String uuid) {
		if (StringUtils.isBlank(uuid)) {
			return null;
		}
		return loginUsers.get(uuid);
	}
	
	
	public void saveLoginLog(HttpServletRequest request, String username, String type, String result){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		TSysUser u = new TSysUser();
		List<TSysUser> us = this.dao.find("from TSysUser where isActive='Y' and username = ?", username);
		if (us.size() > 0){
			u = us.get(0);
		}
		TSysLog t = new TSysLog();
		t.setUsername(username);
		t.setUserid(u.getId());
		t.setName(u.getName());
		t.setLogintype(type);
		t.setLoginip(ip);
		t.setOpertime(new Date());
		t.setResult(result);
		
		this.dao.save(t);
	}
}
