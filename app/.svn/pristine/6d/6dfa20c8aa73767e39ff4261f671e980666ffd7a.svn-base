/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;

/**
 * 用户管理
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
public interface UserManager extends Manager {

	/**
	 * 根据用户的登录名查询出符合条件的用户名称
	 * 
	 * @param name
	 *            用户登录名
	 * @return 用户名称，用","隔开
	 */
	public String getUserNames(List<String> names);

	/**
	 * 根据用户的登录名查询出符合条件的用户信息(后续改成一次in查询的方式)
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public List<TSysUser> getUsersByLoginName(List<String> names);

	/**
	 * 根据用户的登录名查询出符合条件的用户信息
	 * 
	 * @param name
	 *            用户登录名
	 * @return 符合条件的用户信息
	 */
	public TSysUser getUserByLoginName(String name);

	/**
	 * 当任务下派到某个区域时，获取当前区域能接受任务的人员列表（目前是办公室人员）
	 * 
	 * @param areaId
	 *            区域Id
	 * @return 任务下派到某个区域时，获取当前区域能接受任务的人员列表
	 */
	public List<TSysUser> getXpUsers(String areaId);

	/**
	 * 可以处理上报任务的人员集合（目前是办公室人员）
	 * 
	 * @return 可以处理上报任务的人员集合
	 */
	public List<TSysUser> getSbUsers();

	/**
	 * 根据用户Id获取用户
	 * 
	 * @param id
	 *            用户ID
	 * @return 用户
	 */
	public TSysUser getUser(String id);

	/**
	 * 查询用户列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称，备注搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @param pageSize2 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryUser(String name, String isActive, String page, String pageSize,String orgid,String areaid)
			throws Exception;

	/**
	 * 保存一个用户
	 * 
	 * @param frm
	 *            {@link UserForm}
	 * @param file 
	 */
	public void saveUser(UserForm frm, MultipartFile file) throws AppException;

	/**
	 * 保存密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public void resetPas(String id) throws AppException;

	/**
	 * 保存密码
	 * 
	 * @param frm
	 *            {@link UserForm}
	 */
	public String savePas(UserForm frm) throws AppException;

	/**
	 * 根据用户id获取角色对象
	 * 
	 * @param userId
	 *            用户Id
	 * @return 角色对象
	 */
	public List<TSysRole> getRolebyUser(String userId);

	/**
	 * 根据用户id获取部门
	 * 
	 * @param userId
	 *            用户Id
	 * @return 部门对象
	 */
	public TSysOrg getOrgbyUser(String userId);

	/**
	 * 删除用户，用户和角色关系
	 * 
	 * @param id
	 *            用户ID
	 */
	public void removeUser(String id) throws IOException;

	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder);

	public TSysUser getSj();
	
	/**
	 * 根据下派区域的id，查询出下派区域的领导
	 * 函数介绍：
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TSysUser getLeaderByAreaId(String areaId);

	public void afterPropertiesSet() throws Exception;

	/**
	 * 插入系统用户”上级“
	 */
	void saveSys();

	/**
	 * 函数介绍：编辑一个用户信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void editUser(UserForm frm);

	/**
	 * 函数介绍：预览图片
	
	 * 输入参数：id：用户id
	
	 * 返回值：
	 */
	public void previewImage(String id, HttpServletResponse res);
	
	/**
	 * 查询用户数量
	 */
	public int queryUserNumber();
	/**
	 * 根据区域查询当前区域执法的所有用户，非管理员
	 * 
	 * */
	public List<TSysUser> queryUsersByAreaid(TDataLawobjf lawobj)throws Exception;
	
	/**
	 * 根据List<TSysUser>用户，随机抽选出来一个用户
	 * 
	 * */
	public TSysUser randomUser(List<TSysUser> users)throws Exception;
	
	/**
	 * 查询所有用户
	 */
	public List<Map<String, String>> queryAllUser();
	public List<ComboboxTree> queryUserComboTree(String areaid);
}
