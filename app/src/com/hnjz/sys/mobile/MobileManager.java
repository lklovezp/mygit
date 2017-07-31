package com.hnjz.sys.mobile;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 手机功能管理
 * 
 * @author Administrator
 * @version $Id: MobileManager.java, v 0.1 Apr 22, 2013 2:46:05 PM Administrator
 *          Exp $
 */
public interface MobileManager extends Manager{

	/**
	 * 手机权限管理，获取所有手机功能，并将角色所有的功能勾上
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	public JSONArray queryQx(String roleId);

	/**
	 * 获取用户手机端具有的菜单
	 * 
	 * @return
	 */
	public List<Map<String, String>> queryMo();

	/**
	 * 保存手机端权限,保存前先删除角色具有的权限
	 * 
	 * @param mobileId
	 *            手机功能Id
	 * @param roleId
	 *            角色Id
	 */
	public void saveRight(String[] mobileId, String roleId);

	/**
	 * 查询手机功能
	 * 
	 * @param name
	 * @param isActive 
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryMobile(String name, String isActive) throws Exception;

	/**
	 * 
	 * 保存手机功能
	 * 
	 * @param form
	 * @throws AppException
	 */
	public void saveMobile(MobileForm form) throws AppException;

	/**
	 * @return 列表
	 * @throws Exception
	 */
	public JSONArray querySelectMobile(String id) throws Exception;

	/**
	 * 删除手机功能信息
	 * 
	 * @param id
	 */
	public void removeMobile(String id) throws AppException;
	
	/**
	 * 
	 * 函数介绍：查询当前用户的终端菜单
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray queryMobileMenu();

}
