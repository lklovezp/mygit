/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.sjtb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hnjz.common.manager.Manager;

/**
 * 数据同步的manager
 * 
 * @author zhangqingfeng
 * @version $Id: SjtbManager.java, v 0.1 2017-6-13 下午03:28:05 zhangqingfeng Exp $
 */
public interface sjtbMoManager extends Manager {

	/**
	 * 手机端获取任务类型接口
	 */
	public List<Map<String, String>> getTaskType(String strUpdate);
	
	/**
	 * 查询所有执法对象基本信息
	 */
	public List<Map<String,String>> queryAllLawobj(String strUpdate);
	
	/**
	 * 查询所有执法对象类型
	 */
	public List<Map<String,String>> queryLawobjtype(String strUpdate);
	
	/**
	 * 查询所有用户
	 */
	public List<Map<String, String>> queryAllUser(String strUpdate);
	
	/**
	 * 查询所有用户关联的部门
	 */
	public List<Map<String, String>> queryAllUserOrg(String strUpdate);
	
	/**
	 * 查询所有用户关联的角色
	 */
	public List<Map<String, String>> queryAllUserRole(String strUpdate);
	
	/**
	 * 查询所有用户
	 */
	public ArrayList<String>  queryAllUserList(String strUpdate);
	
	/**
	 * 查询所有附件
	 */
	public List<Map<String, String>> queryallDataFile(String strUpdate);
	
	/**
	 * 查询所有区域
	 */
	public List<Map<String, String>> queryallDataArea(String strUpdate);
	
	/**
	 * 查询所有行政区划
	 */
	public List<Map<String, String>> queryallDataRegion(String strUpdate);
	
	/**
	 * 查询所有执法文件
	 */
	public List<Map<String, String>> queryallLawdocData(String strUpdate);
	
	/**
	 * 查询所有角色
	 */
	public List<Map<String, String>> queryallRoleData(String strUpdate);
	
	/**
	 * 查询所有部门
	 */
	public List<Map<String, String>> queryallOrgData(String strUpdate);
	
	/**
	 * 查询所有行业类型
	 */
	public List<Map<String, String>> queryallIndustryData(String strUpdate);
	
	/**
	 * 查询所有违法类型
	 */
	public List<Map<String, String>> queryallIllegaltypeData(String strUpdate);
	
	/**
	 * 查询所有待办任务数据
	 */
	public List<Map<String, String>> queryallDbrwData(String strUpdate);
	
	/**
	 * 查询所有问题项数据
	 */
	public List<Map<String, String>> queryallDataRecordData(String strUpdate);
	
	/**
	 * 判断是否同步的通用方法
	 */
	public List<Map<String, String>> TableDataIsSynch(String strUpdate, String tableName);
	
}
