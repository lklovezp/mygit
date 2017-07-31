/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSet;

import java.util.List;

import com.hnjz.app.drafter.po.TDataDrafterSet;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;

/**
 * 稿件审核人设置Manager
 * @author shiqiuhan
 * @created 2015-12-24,上午09:00:13
 */
public interface DrafterSetManager extends Manager {

	/**
	 * 保存稿件审核人设置
	 * @param orgId
	 *            部门
	 * @param jb
	 *            审核级别   
	 * @param shr
	 *            审核人           
	 */
	public void saveDrafterSet(String orgId,String jb,String shr) throws AppException;
	
	/**
	 * 根据部门和审核级别查询稿件审核人设置信息
	 * @param orgId
	 *            部门
	 * @param jb
	 *            审核级别   
	 */
	public List <TDataDrafterSet>  queryShr(String orgId,String jb) throws AppException;
	
	/**
	 * 根据审核人查询部门和审核级别
	 * @param auditid
	 *            审核人id
	 */
	public List <TDataDrafterSet> querySet(String auditid) throws AppException;
	
}
